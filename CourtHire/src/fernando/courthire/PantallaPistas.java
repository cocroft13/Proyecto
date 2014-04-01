package fernando.courthire;


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
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint("NewApi")
public class PantallaPistas extends Activity implements ActionBar.OnNavigationListener {
	
	ListView list;
	TextView nombrePista;
	TextView reservado;
	Button botonConsulta;
	ArrayList<HashMap<String, String>> datosLista ;
	
	//URL PARA OBTENER EL ARRAY JSON
	private static String url = "http://10.0.2.2/";
	boolean flag;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_pistas);
		
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
