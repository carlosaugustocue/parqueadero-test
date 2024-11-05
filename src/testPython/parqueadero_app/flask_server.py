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

        if placa:
            # Filtrar solo letras y números en la placa detectada y solo mayusculas
            placa_limpia = re.sub(r'[^A-Z0-9]', '', placa.upper())


            # Enviar la placa al servidor
            # Mostrar por consola la placa
            print(f"Placa detectada: {placa}")
            respuesta = enviar_placa_al_servidor(placa_limpia)

            return jsonify({
                'placa': placa_limpia,
                'mensaje': 'Placa detectada y enviada al servidor',
                'respuesta_servidor': respuesta
            }), 200
        else:
            return jsonify({'error': 'No se pudo detectar la placa'}), 500
    except Exception as e:
        print(f"Error al procesar la solicitud: {e}")
        return jsonify({'error': str(e)}), 500

if __name__ == '__main__':
    app.run(port=5000)
