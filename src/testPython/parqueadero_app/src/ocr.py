import easyocr

def detectar_placa(frame):
    reader = easyocr.Reader(['en'])
    result = reader.readtext(frame)
    license_plate = ""
    for detection in result:
        text = detection[1]
        if len(text) >= 6:  # Filtra texto basado en el tamaño de una placa típica
            license_plate = text
            break
    return license_plate
