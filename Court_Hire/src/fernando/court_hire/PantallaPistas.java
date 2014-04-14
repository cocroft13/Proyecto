package fernando.court_hire;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PantallaPistas extends ListActivity {
		
	TextView lblFechaElegida;
	
	private static final String URL = "169.254.200.70/consultaPistas.php";
	
	//ELEMENTOS VISUALES Y VARIABLES	
	private ProgressDialog pDialog; 			
	boolean flag;
	private String fecha;
	private ListAdapter adapter;
	private ListView lv;
	
	//NOMBRES DE NODOS JSON
	private static final String TAG_PISTAS = "Pistas";
	//private static final String TAG_ID = "id";
	private static final String TAG_NAME = "nombre_pista";
	private static final String TAG_HORARIO = "horario_pista";
	
	JSONArray courts = null;
		
	//HASMAP FOR LISTVIEW
	ArrayList<HashMap<String, String>> courtList;
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_pistas);
		
		lblFechaElegida = (TextView)findViewById(R.id.lblFechaElegida);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
			
		courtList = new ArrayList<HashMap<String, String>>();
		lv = getListView();
					
		try {
			
			Bundle bundle = getIntent().getExtras();
			fecha = bundle.getString("fecha");
			
			} catch (Exception e){
				e.printStackTrace();
			}
	
		lblFechaElegida.setText("Elegir pista para la fecha: " + fecha);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				//GETTING VALUES FROM SELECTED LISTITEM
				String name = ((TextView) view.findViewById(R.id.nombrePista)).getText().toString();
				String horario = ((TextView) view.findViewById(R.id.horarioPista)).getText().toString();
				
				//STARTING SINGLE CONTACT ACTIVITY
				Intent in = new Intent(getApplicationContext(),PantallaPistaSeleccionada.class);
				
				in.putExtra(TAG_NAME, name);
				in.putExtra(TAG_HORARIO, horario);
				in.putExtra("TAG_FECHA", fecha);
				startActivity(in);			
				
			}
		});	
		
		new GetContacts().execute();
		
	}
		
	private class GetContacts extends AsyncTask<Void, Void, Void>{
	
		protected void onPreExecute(){
			super.onPreExecute();
			
			//SHOWING PROGRESS DIALOG			
			pDialog = new ProgressDialog(PantallaPistas.this);
			pDialog.setMessage("Consultando...");
            pDialog.setCancelable(false);
            pDialog.show();
	
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			
			CheckCourts checkCourts = new CheckCourts();
			
			String pistasLibres = checkCourts.obtenerPistas(fecha);		
			
			if(pistasLibres != null){
				
				try {
					
					JSONObject jsonObj = new JSONObject(pistasLibres);
					
					//GETTING JSON ARRAY NODE
					courts = jsonObj.getJSONArray(TAG_PISTAS);
					
					//LOOPING THROUGH ALL CONTACTS
					for (int i=0; i < courts.length(); i++){
						
						JSONObject c = courts.getJSONObject(i);
												
						String nombre_pista = c.getString(TAG_NAME);
						String horario_pista = c.getString(TAG_HORARIO);
						
												
						//TEMPORAL HASMAP FOR SINGLE COURTS
						HashMap<String, String> court = new HashMap<String,String>();
						
						//ADDING EACH CHILD NODE TO HASHMAP KEY => VALUE
						//court.put(TAG_ID, id_pista);
						court.put(TAG_NAME, nombre_pista);
						court.put(TAG_HORARIO, horario_pista);
						court.put("FECHA", fecha);
											
						//ADDING COURTS TO COURTLIST
						courtList.add(court);					
					}
					
				} catch (JSONException e){
					e.printStackTrace();
				}
		
			} else {
				
				Log.e("CheckCourts", "No se han obtenido los datos");
			}				
			return null;
		}
				
		protected void onPostExecute(Void result){
			super.onPostExecute(result);
			
			//DISMISS THE PROGRESS DIALOG
			
			if (pDialog.isShowing()){
				pDialog.dismiss();
			}
			
			adapter = new SimpleAdapter(PantallaPistas.this, courtList, R.layout.list_item, 
													new String[]{ TAG_NAME,TAG_HORARIO,fecha}, new int[] 
															{R.id.nombrePista,R.id.horarioPista,R.id.fechaPista});
			
			setListAdapter(adapter);				
		}		
	}
	
	//ACCIONES PARA LOS MENUS
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_action, menu);
		
		return super.onCreateOptionsMenu(menu);	
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch (item.getItemId()){
			case R.id.action_search:
				
				Intent intent = new Intent(PantallaPistas.this,CalendarView.class);
				startActivity(intent);
				finish();
				return true;
			case R.id.action_refresh:
				courtList.clear();							
				new GetContacts().execute();
									
		default:
			return super.onOptionsItemSelected(item);
					
		}		
	}		
}