async function capturarPlaca() {
    const mensaje = document.getElementById("mensaje");
    const textoPlaca = document.getElementById("texto-placa");

    // Mostrar el mensaje de procesamiento y ocultar el texto de la placa temporalmente
    mensaje.style.visibility = "visible";
    textoPlaca.style.visibility = "hidden";

    try {
        const response = await fetch("http://localhost:5000/capturar_placa");

        if (!response.ok) throw new Error("Error al capturar la placa");

        const data = await response.json();

        // Mostrar la placa capturada y ocultar el mensaje de procesamiento
        if (data.placa) {
            textoPlaca.innerText = data.placa;
        } else {
            textoPlaca.innerText = "No se detectó";
        }
    } catch (error) {
        console.error("Error:", error);
        textoPlaca.innerText = "Error";
    } finally {
        // Ocultar el mensaje de procesamiento y mostrar el texto de la placa
        mensaje.style.visibility = "hidden";
        textoPlaca.style.visibility = "visible";
    }
}
