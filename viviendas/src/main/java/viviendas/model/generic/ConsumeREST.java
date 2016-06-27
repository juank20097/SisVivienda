package viviendas.model.generic;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConsumeREST {
	
	/**
	 * Consume un servicio web REST POST
	 * @param urlServicio
	 * @param objetoJSON
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject postClient(String urlServicio, JSONObject objetoJSON) throws Exception{
		JSONObject resp = null;
		//URL DEL SERVICIO
		URL url = new URL(urlServicio);
		//CREAR CONEXION
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		//AGREGAR DATOS Y ENVIO
		OutputStream os = conn.getOutputStream();
		os.write(objetoJSON.toJSONString().getBytes());
		os.flush();
		//VALIDACION DE RESPUESTA
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ conn.getResponseCode());
		}
		//LECTURA DE DATOS
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		String output;
		String objeto = "";
		while ((output = br.readLine()) != null) {
			objeto+=output;
		}
		//CASTEO DE RESPUESTA
		resp = (JSONObject) new JSONParser().parse(objeto);
		//CERRAR CONEXION
		conn.disconnect();
		//RESPUESTA
		return resp;
	}
}
