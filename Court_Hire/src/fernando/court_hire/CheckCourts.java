package fernando.court_hire;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class CheckCourts {
	
	private String URL;
	
	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	JSONObject jsonObj;
	JSONArray jsonArray;
	StringBuilder sb;
	
	
	public StringBuilder obtenerPistas(String dia) {
		
		String line = "";
				
		
		URL = "http://169.254.200.70/consultarPistas.php";
		
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(URL);
		jsonObj = new JSONObject();
		
		try {
						
			jsonObj.put("fecha", dia);
			jsonArray = new JSONArray();
			jsonArray.put(jsonObj);
						
			//POST THE DATA
			httpPost.setHeader("json",jsonObj.toString());
			httpPost.getParams().setParameter("postJson", jsonArray);
			
			//EJECUCION DE PETICION
			httpResponse = httpClient.execute(httpPost);
			httpEntity = httpResponse.getEntity();
			
			InputStream is = httpEntity.getContent();
			
			//OBTENEMOS LOS DATOS DEL FICHERO PHP
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"), 8);
			
			line = reader.readLine();
						
			
			
			//ESTA PARTE ES LA DE RELLENAR UN ARRAY CON TODAS LAS PISTAS
			
			jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject().getJSONObject(line);
			
			jsonArray.put(jsonObject);
			
			
			
			
			/*sb = new StringBuilder();
			
			while((line = reader.readLine()) != null){
				sb.append(line + "\n");
								
			}*/
					
			is.close();
			reader.close();
			
			//AHORA TENEMOS QUE CREAR UN ARRAYLIST A PARTIR DEL JSON QUE LLEGA PARA CONSTRUIR EL LISTVIEW
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return sb;
				
	}	
}