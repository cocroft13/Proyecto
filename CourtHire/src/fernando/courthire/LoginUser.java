package fernando.courthire;

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

public class LoginUser {
	
	private String URL;
	private Boolean valido = false;
	
	public void postData(String user,String pass) {
		URL = "http://10.0.2.2/login2.php";
		String code = "1";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		
		JSONObject jsonObj = new JSONObject();
				
		try {
			
			//Datos Json
			jsonObj.put("Nombre",user);
			jsonObj.put("Password", pass);
			
						
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(jsonObj);
			
			//Post the data
			httpPost.setHeader("json",jsonObj.toString());
			httpPost.getParams().setParameter("postJson", jsonArray);
			
			//Ejecución de petición
			HttpResponse httpResponse = httpClient.execute(httpPost);			
			
			HttpEntity entity = httpResponse.getEntity();
			
			InputStream inStream = entity.getContent();
			
			// De aquí obtenemos los datos del fichero php
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"iso-8859-1"),8);
					
			String line = reader.readLine();	
				
			inStream.close();
			reader.close();		
						
			//SI VALE UNO EL USUARIO EXISTE Y SE PUEDE PASAR A LA OTRA PANTALLA.
			if(line.equals(code)){	
				
				valido = true;
	
			//SI NO, EL USUARIO NO COINCIDE CON NINGUNO EN LA BD, POR LO QUE DEBE CREAR UNO.
			} else {
				
				valido = false;		
			}			
			validOrNot();
										
		} catch (JSONException e) {			
			e.printStackTrace();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
						
		} catch (IOException e) {						
			e.printStackTrace();
		}				
	}
		
	
	public Boolean validOrNot(){
		
		return valido;			
	}		
}
