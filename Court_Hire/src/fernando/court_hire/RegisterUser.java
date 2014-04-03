package fernando.court_hire;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUser {
		
	private String URL;
	private Boolean valido = false;
		
	public boolean postData(String nombre,String dni,String email,String password,String rePassword){
		
		URL = "http://169.254.118.110/login.php";
		String code = "1";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		
		JSONObject jsonObj = new JSONObject();
		
		try {
			
			//Datos Json
			jsonObj.put("Nombre", nombre);
			jsonObj.put("DNI", dni);
			jsonObj.put("Email", email);
			jsonObj.put("Pass", password);
			jsonObj.put("RePass", rePassword);
						
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(jsonObj);
						
			//Post
			httpPost.setHeader("json",jsonObj.toString());
			httpPost.getParams().setParameter("postJson", jsonArray);
			
			//Ejecución de petición de respuesta
			HttpResponse httpResponse = httpClient.execute(httpPost);			
			
			HttpEntity entity = httpResponse.getEntity();
			
			InputStream inStream = entity.getContent();
			
			// De aquí obtenemos los datos del fichero php
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"iso-8859-1"),8);
								
			String line = reader.readLine();	
							
			inStream.close();
			reader.close();
			
			//SI DEVUELVE 1(code), EL USUARIO SE HA PODIDO CREAR PORQUE NO EXISTIA ANTERIORMENTE.
			if(line.equals(code)){	
				
				valido = true;
				
			//SI NO, EL USUARIO YA EXISTE CON ESE DNI, ASI QUE DEBE LOGUEARSE CORRECTAMENTE.
			} else {
				
				valido = false;		
			}			
	
		
		} catch (JSONException e) {			
			e.printStackTrace();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
						
		} catch (IOException e) {						
			e.printStackTrace();
		}	
		
		return valido;
	}
				

}

