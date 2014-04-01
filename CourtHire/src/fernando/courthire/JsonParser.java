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
import org.apache.http.impl.conn.DefaultClientConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {
	
	private String URL;
	private boolean valido = false;
	
	public void consultarPistas(String dia,String horario) {
		
		URL = "http://10.0.2.2/court_request";
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(URL);
		
		JSONObject jsonObj = new JSONObject();
				
		try {
			
			jsonObj.put("dia", dia);
			jsonObj.put("horario", horario);
			
			JSONArray jsonArray = new JSONArray();
			jsonArray.put(jsonObj);
			
			httpPost.setHeader("json",jsonObj.toString());
			httpPost.getParams().setParameter("postJson", jsonArray);
			
			
			//Ejecución de petición
			HttpResponse httpResponse = httpClient.execute(httpPost);						
			HttpEntity entity = httpResponse.getEntity();			
			InputStream inStream = entity.getContent();
					
			
			// De aquí obtenemos los datos del fichero php
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"iso-8859-1"),8);
								
											
			inStream.close();
			reader.close();	
			
	
		} catch (JSONException e) {			
			e.printStackTrace();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
						
		} catch (IOException e) {						
			e.printStackTrace();
		}
		
	}
		
		
		
		
		
		
	
	
	
}