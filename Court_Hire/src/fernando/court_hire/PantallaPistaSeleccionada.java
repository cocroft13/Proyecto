package fernando.court_hire;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaPistaSeleccionada extends Activity{
	
	//JSON NODE KEYS
	private static final String TAG_NAME = "nombre_pista";
	private static final String TAG_HORARIO = "horario";
	private static final String TAG_ID_PISTA = "id_pista";
	
	SharedPreferences pref; 
	private String userDni;
	private String name;
	private String idPista;
	private String horario;
	private String fecha;
	
	private Button botonConfirmar;
	
			
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_pista_seleccionada);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		userDni = pref.getString("key_name_user", null);
		botonConfirmar = (Button)findViewById(R.id.botonConfirmarPista);
		
		
		//GETTING INTENT DATA
		Intent intent = getIntent();
		
		//GET JSON VALUES FROM PREVIOUS INTENT
		name = intent.getStringExtra(TAG_NAME);
		idPista = intent.getStringExtra(TAG_ID_PISTA);		
		horario = intent.getStringExtra(TAG_HORARIO);
		fecha = intent.getStringExtra("TAG_FECHA");
		
		
		//DISPLAYING ALL VALUES ON THE SCREEN
		TextView lblUser = (TextView) findViewById(R.id.user_label);
		TextView lblName = (TextView) findViewById(R.id.name_label);
		TextView lblHorario = (TextView)findViewById(R.id.horario_label);	
		TextView lblFecha = (TextView)findViewById(R.id.fechaFinal);
				
		lblName.setText("Pista" + "\n" + name);
		lblHorario.setText("Horario" + "\n" + horario);
		lblUser.setText("Usuario: " +  "\n" + userDni);
		lblFecha.setText("Fecha: " +  "\n" + fecha);
			
		botonConfirmar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				Toast.makeText(getApplicationContext(), name + ", " + idPista + ", " + horario + ", " + fecha,  Toast.LENGTH_SHORT).show();
				
				//Dialogs dialogo = new Dialogs();			
				//dialogo.show(getFragmentManager(), null);
				
				NewHire newHire = new NewHire();
				try {
					newHire.insertHire(userDni,idPista,fecha,horario);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							
			}
		});			
	}
	
}
