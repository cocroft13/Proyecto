package fernando.court_hire;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PantallaPistas extends Activity  {
		
	private Button botonVerJson;
	private CheckCourts checkCourts;
	
	private ProgressDialog pDialog; 
	private static final String URL = "169.254.200.70/consultaPistas.php";			

	boolean flag;
	private String fecha;
	private StringBuilder pistasLibres;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pantalla_pistas);
		
		try {
			
			Bundle bundle = getIntent().getExtras();
			fecha = bundle.getString("fecha");
			
			} catch (Exception e){
				e.printStackTrace();
			}
		
		
		checkCourts = new CheckCourts();		
		pistasLibres = checkCourts.obtenerPistas(fecha);
		botonVerJson = (Button)findViewById(R.id.botonMostrarJson);
					
		botonVerJson.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), pistasLibres, Toast.LENGTH_SHORT).show();
				
			}
		});
						
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_action, menu);
		
		return super.onCreateOptionsMenu(menu);		
	}
	
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch (item.getItemId()){
			case R.id.action_search:
				Toast.makeText(getApplicationContext(), fecha, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(PantallaPistas.this,CalendarView.class);
				startActivity(intent);
				finish();
				return true;
									
		default:
			return super.onOptionsItemSelected(item);
		
		
		}		
	}		
}