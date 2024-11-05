import requests
from config import SERVER_URL
from datetime import datetime

def enviar_placa_al_servidor(placa):
    url = f"{SERVER_URL}/api/vehiculos/entrada"
    data = {
        "placa": placa,
        "tipoVehiculo": "Carro"
    }
    response = requests.post(url, json=data)
    if response.status_code == 200:
        return response.json()
    else:
        raise Exception("Error al enviar la placa al servidor:", response.text)
