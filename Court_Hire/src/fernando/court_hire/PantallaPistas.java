package fernando.court_hire;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PantallaPistas extends Activity implements ActionBar.OnNavigationListener {
	
	Spinner spinnerDias;
	ListView list;
	TextView nombrePista;
	TextView reservado;
	Button botonConsulta;
	ArrayList<HashMap<String, String>> datosLista ;
	
	
	
	boolean flag;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_pistas);
		
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
				
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.lista_dias, 
																 android.R.layout.simple_spinner_dropdown_item);
		
		actionBar.setListNavigationCallbacks(adapter, this);
	
		datosLista = new ArrayList<HashMap<String,String>>();
		botonConsulta = (Button)findViewById(R.id.nombrePista);
		list = (ListView)findViewById(R.id.miLista);
		
	}


	
	//METODO PARA MOSTRAR LA BARRA DE PROGRESO MIENTRAS SE CONSULTAN LOS DATOS
	
	/*private class JSONParse extends AsyncTask<String, String, JSONObject>{
		private ProgressDialog pDialog;

		
		
		protected void  onPreExecute(){
			super.onPreExecute();
				nombrePista = (TextView)findViewById(R.id.nombrePista);
				reservado = (TextView)findViewById(R.id.libreSiNo);
				
				pDialog = new ProgressDialog(PantallaPistas.this);
				pDialog.setMessage("Oteniendo datos");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
				
		}



		@Override
		protected JSONObject doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
	}*/
	

	

	@Override
	public boolean onNavigationItemSelected(int position, long itemId) {
		
		if(flag){
			flag = false;
		} else {
			
			switch (position) {
			case 0:
				Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
				
				break;

			default:
				break;
			}			
			
		}
		
		
		return false;
	}

		
	}
