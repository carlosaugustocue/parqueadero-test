package com.parqueadero.sistema_parqueadero.servicio;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MapService {
    public List<Map<String, Object>> obtenerParqueaderos(double latitud, double longitud, double radioKm) {
        List<Map<String, Object>> listaParqueaderos = new ArrayList<>();
        try {
            // Construir la consulta Overpass API
            String consulta = "[out:json];node[amenity=parking](around:" + (radioKm * 1000) + "," + latitud + "," + longitud + ");out;";
            String urlStr = "https://overpass-api.de/api/interpreter?data=" + URLEncoder.encode(consulta, "UTF-8");

            // Realizar la solicitud HTTP
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resultado = new StringBuilder();
            String linea;
            while ((linea = rd.readLine()) != null) {
                resultado.append(linea);
            }
            rd.close();

            // Parsear el JSON
            JSONObject json = new JSONObject(resultado.toString());
            JSONArray elementos = json.getJSONArray("elements");

            // Procesar los resultados
            for (int i = 0; i < elementos.length(); i++) {
                JSONObject elemento = elementos.getJSONObject(i);
                double lat = elemento.getDouble("lat");
                double lon = elemento.getDouble("lon");
                JSONObject tags = elemento.optJSONObject("tags");
                String nombre = (tags != null) ? tags.optString("name", "Parqueadero") : "Parqueadero";

                Map<String, Object> parqueadero = new HashMap<>();
                parqueadero.put("nombre", nombre);
                parqueadero.put("latitud", lat);
                parqueadero.put("longitud", lon);

                listaParqueaderos.add(parqueadero);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaParqueaderos;
    }
}
