package fernando.court_hire;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PantallaPistas extends Activity implements ActionBar.OnNavigationListener {
		
	private ListView lista;
	private Button botonConsulta;
	
	private ProgressDialog pDialog; 
	private static final String URL = "169.254.118.110/consultaPistas.php";			

	boolean flag;
	private String dia = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_pistas);
		
		lista = (ListView)findViewById(R.id.miLista);
		botonConsulta = (Button)findViewById(R.id.botonObtenerDatos);
				
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
						
		SpinnerAdapter adapter = ArrayAdapter.createFromResource(this, R.array.lista_dias, 
																 android.R.layout.simple_spinner_dropdown_item);	
		actionBar.setListNavigationCallbacks(adapter, this);
	
		
		
		
		
		
		
		
		
	}
	
	
	@Override
	public boolean onNavigationItemSelected(int position, long itemId) {
		
		if(flag){
			flag = false;
		} else {
			
			switch (position) {
			case 0:
						
				break;
			case 1:			
				dia = "lunes";
				Toast.makeText(getApplicationContext(),"Lunes", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				dia = "martes";
				Toast.makeText(getApplicationContext(),"Martes", Toast.LENGTH_SHORT).show();			
				break;
			case 3:
				dia = "miercoles";
				Toast.makeText(getApplicationContext(),"Miercoles", Toast.LENGTH_SHORT).show();
				break;				
			case 4:
				dia = "jueves";
				Toast.makeText(getApplicationContext(),"Jueves", Toast.LENGTH_SHORT).show();
				break;
			case 5:
				dia = "viernes";
				Toast.makeText(getApplicationContext(),"Viernes", Toast.LENGTH_SHORT).show();
			case 6:
				dia = "sabado";
				Toast.makeText(getApplicationContext(),"Sábado", Toast.LENGTH_SHORT).show();
			
			}						
		}	
		return false;
	}	
}
