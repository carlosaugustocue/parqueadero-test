import easyocr
import cv2
import re
import json
import numpy as np

def order_points(pts):
    # Función para ordenar puntos para la transformación de perspectiva
    rect = np.zeros((4, 2), dtype="float32")
    s = pts.sum(axis=1)
    rect[0] = pts[np.argmin(s)]
    rect[2] = pts[np.argmax(s)]
    diff = np.diff(pts, axis=1)
    rect[1] = pts[np.argmin(diff)]
    rect[3] = pts[np.argmax(diff)]
    return rect

def detectar_placa(frame):
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.GaussianBlur(gray, (5, 5), 0)
    edges = cv2.Canny(gray, 50, 150)
    contours, _ = cv2.findContours(edges, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    
    plate_candidates = []
    
    for contour in contours:
        area = cv2.contourArea(contour)
        if area < 1000:
            continue
        approx = cv2.approxPolyDP(contour, 0.02 * cv2.arcLength(contour, True), True)
        if len(approx) == 4:
            x, y, w, h = cv2.boundingRect(approx)
            aspect_ratio = w / h
            if aspect_ratio < 2 or aspect_ratio > 6:
                continue
            
            rect = order_points(approx.reshape(4, 2))
            width = int(max(np.linalg.norm(rect[2] - rect[3]), np.linalg.norm(rect[1] - rect[0])))
            height = int(max(np.linalg.norm(rect[1] - rect[2]), np.linalg.norm(rect[0] - rect[3])))
            dst = np.array([[0, 0], [width - 1, 0], [width - 1, height - 1], [0, height - 1]], dtype="float32")
            M = cv2.getPerspectiveTransform(rect, dst)
            warp = cv2.warpPerspective(gray, M, (width, height))
            
            # Preprocesar warp para OCR
            warp = cv2.GaussianBlur(warp, (3, 3), 0)
            _, warp = cv2.threshold(warp, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
            
            reader = easyocr.Reader(['en', 'es'])
            result = reader.readtext(warp)
            
            for detection in result:
                text = re.sub(r'\W+', '', detection[1]).upper()
                if re.match(r'^[A-Z]{3}\d{3}$', text):
                    return json.dumps({"placa_detectada": text})
                elif re.match(r'^[A-Z0-9]{6}$', text):
                    plate_candidates.append(text)
    
    if plate_candidates:
        # Retornar el mejor candidato o aplicar más filtrado
        return json.dumps({"placa_detectada": plate_candidates[0]})
    else:
        return json.dumps({"error": "Placa no detectada"})
