package fernando.court_hire;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaPistaSeleccionada extends Activity{
	
	//JSON NODE KEYS
	private static final String TAG_NAME = "nombre_pista";
	private static final String TAG_HORARIO = "horario_pista";
	SharedPreferences pref; 
	private String userName;
	
	private Button botonConfirmar;
	
			
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_pista_seleccionada);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		userName = pref.getString("key_name_user", null);
		botonConfirmar = (Button)findViewById(R.id.botonConfirmarPista);
		
		
		//GETTING INTENT DATA
		Intent intent = getIntent();
		
		//GET JSON VALUES FROM PREVIOUS INTENT
		String name = intent.getStringExtra(TAG_NAME);
		String horario = intent.getStringExtra(TAG_HORARIO);
		String fecha = intent.getStringExtra("TAG_FECHA");
		
		
		//DISPLAYING ALL VALUES ON THE SCREEN
		TextView lblUser = (TextView) findViewById(R.id.user_label);
		TextView lblName = (TextView) findViewById(R.id.name_label);
		TextView lblHorario = (TextView)findViewById(R.id.horario_label);		
		TextView lblFecha = (TextView)findViewById(R.id.fechaFinal);
				
		lblName.setText("Pista" + "\n" + name);
		lblHorario.setText("Horario" + "\n" + horario);
		lblUser.setText("Usuario: " +  "\n" +userName);
		lblFecha.setText("Fecha: " +  "\n" + fecha);
			
		botonConfirmar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
						
				Dialogs dialogo = new Dialogs();			
				dialogo.show(getFragmentManager(), null);
							
			}
		});			
	}
}
