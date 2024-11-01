import cv2

def capturar_imagen():
    cap = cv2.VideoCapture(0)
    ret, frame = cap.read()
    cap.release()
    if not ret:
        raise Exception("No se pudo capturar la imagen.")
    return frame
