import re
from flask import Flask, jsonify
from src.captura import capturar_imagen
from src.ocr import detectar_placa
from src.api_client import enviar_placa_al_servidor

app = Flask(__name__)

@app.route('/capturar_placa', methods=['GET'])
def capturar_placa():
    try:
        # Capturar imagen y detectar placa
        frame = capturar_imagen()
        placa = detectar_placa(frame)

        # Filtrar solo letras y números en la placa detectada
        placa_limpia = re.sub(r'[^A-Za-z0-9]', '', placa)

        # Verificar si hay espacio en el parqueadero
        respuesta = enviar_placa_al_servidor(placa_limpia)

        return jsonify({
            'placa': placa_limpia,
            'mensaje': 'Placa detectada y enviada al servidor',
            'respuesta_servidor': respuesta
        }), 200
    except Exception as e:
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(port=5000)
