package fernando.court_hire;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.JsonReader;

public class checkCourts {
	
	private String URL;
	
	HttpClient httpClient;
	HttpPost httpPost;
	HttpResponse httpResponse;
	HttpEntity httpEntity;
	JSONObject jsonObj;
	JSONArray jsonArray;
	
	
	public ArrayList<String> obtenerPistas(String dia) {
		
		ArrayList<String> miArrayList = new ArrayList<String>();
		
		URL = "http://169.254.118.110/consultarPistas.php";
		
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(URL);
		jsonObj = new JSONObject();
		
		try {
						
			jsonObj.put("dia", dia);
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
			
			
			
		
			
			
			
			

			
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return null;
		
		
		
		
		
		
		
	}
	
}
