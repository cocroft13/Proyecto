package fernando.court_hire;

import java.io.IOException;
import java.sql.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;

public class NewHire {

	private String URL;	
	
	HttpClient httpClient;
	HttpPost httpPost;
	JSONObject jsonObj;
	JSONArray jsonArray;
			
	public void insertHire(String dni,String id_pista,String fecha,String horario) throws ClientProtocolException, IOException{
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		
		URL = "http://169.254.141.40/insertarPista.php";
		
		httpClient = new DefaultHttpClient();
		httpPost = new HttpPost(URL);
		jsonObj = new JSONObject();
		
		try {
		
		jsonObj.put("dni", dni);
		jsonObj.put("id_pista",id_pista);
		jsonObj.put("fecha", fecha);
		jsonObj.put("horario", horario);
		
		jsonArray = new JSONArray();
		jsonArray.put(jsonObj);
		
		//Post
		httpPost.setHeader("json",jsonObj.toString());
		httpPost.getParams().setParameter("postJson", jsonArray);
		
		HttpResponse response = httpClient.execute(httpPost);
		
		
		
		} catch(JSONException e){
			e.printStackTrace();			
		}
			
	}	
}
