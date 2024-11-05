import cv2
import easyocr

# Función para detectar la placa a partir de un frame
def detectar_placa(frame):
    # Procesar la imagen capturada
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    # OCR para extraer los caracteres
    reader = easyocr.Reader(['en'])
    result = reader.readtext(gray)

    # Verificar si se detectó algún texto
    if result:
        placa_detectada = result[0][1]  # Asumiendo que el primer resultado es la placa
        return placa_detectada
    else:
        return "No se detectó ninguna placa"
