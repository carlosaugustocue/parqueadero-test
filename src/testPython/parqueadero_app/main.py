
import subprocess
import time

# Función para iniciar el servidor Flask ejecutando flask_server.py en segundo plano
def iniciar_servidor():
    print("Iniciando el servidor Flask para capturar placas...")
    subprocess.Popen(["python", "flask_server.py"])

# Programa principal
if __name__ == "__main__":
    # Iniciar el servidor Flask
    iniciar_servidor()

    # Mantener el programa principal corriendo indefinidamente
    try:
        while True:
            print("El servidor Flask está en ejecución. Presiona Ctrl+C para detener.")
            time.sleep(60)  # Pausa de 60 segundos antes de la siguiente verificación
    except KeyboardInterrupt:
        print("Deteniendo el servicio.")

