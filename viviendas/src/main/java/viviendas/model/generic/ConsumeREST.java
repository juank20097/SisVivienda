package viviendas.model.generic;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.json.simple.JSONArray;
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
	
	/**
	 * CONSUME UN SERVICIO DE REST EASY TIPO GET DEVOLVIENDO UN ARRAY
	 * @param urlServicio
	 * @return JSONArray
	 * @throws Exception
	 */
	public static JSONArray consumeGetRestEasyArray(String urlServicio) throws Exception{
		JSONArray resp = null;
		ClientRequest request = new ClientRequest(urlServicio);
		request.accept("application/json");
		ClientResponse<String> response = request.get(String.class);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";

		while ((output = br.readLine()) != null) {
			objeto += output;
		}

		resp = (JSONArray) new JSONParser().parse(objeto);
		return resp;
	}
	
	/**
	 * CONSUME UN SERVICIO DE REST EASY TIPO GET DEVOLVIENDO UN STRING
	 * @param urlServicio
	 * @return JSONObject
	 * @throws Exception
	 */
	public static String consumeGetRestEasyObject2(String urlServicio) throws Exception{
		
		ClientRequest request = new ClientRequest(urlServicio);
		request.accept("text/plain");
		ClientResponse<String> response = request.get(String.class);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";
		while ((output = br.readLine()) != null) {
			objeto += output;
		}
		
		return objeto;
	}
	
	public static String postClientRestEasy2(String url, JSONObject peticion) throws Exception{
		//Define the API URI where API will be accessed
	    ClientRequest request = new ClientRequest(url);
        //Set the accept header to tell the accepted response format
	    request.body("application/json", peticion.toJSONString());
	    
	    //Send the request
	    ClientResponse<String> response = request.post(String.class);
	     
	    //First validate the api status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";

		while ((output = br.readLine()) != null) {
			objeto += output;
		}

		return objeto;
	}
	
	/**
	 * CONSUME UN SERVICIO DE REST EASY TIPO GET DEVOLVIENDO UN OBJECT
	 * @param urlServicio
	 * @return JSONObject
	 * @throws Exception
	 */
	public static JSONObject consumeGetRestEasyObject(String urlServicio) throws Exception{
		JSONObject resp = null;
		ClientRequest request = new ClientRequest(urlServicio);
		request.accept("application/json");
		ClientResponse<String> response = request.get(String.class);
		
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";

		while ((output = br.readLine()) != null) {
			objeto += output;
		}

		resp = (JSONObject) new JSONParser().parse(objeto);
		return resp;
	}
	
	public static JSONObject postClientRestEasy(String url, JSONObject peticion) throws Exception{
		JSONObject resp = null;
		//Define the API URI where API will be accessed
	    ClientRequest request = new ClientRequest(url);
        //Set the accept header to tell the accepted response format
	    request.body("application/json", peticion.toJSONString());
	    
	    //Send the request
	    ClientResponse<String> response = request.post(String.class);
	     
	    //First validate the api status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";

		while ((output = br.readLine()) != null) {
			objeto += output;
		}

		resp = (JSONObject) new JSONParser().parse(objeto);
		return resp;
	}
	
	public static JSONObject putClientRestEasy(String url, JSONObject peticion) throws Exception{
		JSONObject resp = null;
		//Define the API URI where API will be accessed
	    ClientRequest request = new ClientRequest(url);
	  //Set the accept header to tell the accepted response format
	    request.body("application/json", peticion.toJSONString());
	    
	    //Send the request
	    ClientResponse<String> response = request.post(String.class);
	     
	    //First validate the api status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));

		String output;
		String objeto = "";

		while ((output = br.readLine()) != null) {
			objeto += output;
		}

		resp = (JSONObject) new JSONParser().parse(objeto);
		return resp;
	}
}
