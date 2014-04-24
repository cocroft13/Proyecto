package fernando.court_hire;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	@SuppressWarnings("unused")
	private ProgressDialog pDialog;
	private EditText campoDni;
	private EditText campoPassword;
	private Button validar;
	private Button registrarse;
	private LoginUser loginUser;
	SharedPreferences pref;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		
		pref = getApplicationContext().getSharedPreferences("MyPref", 0);
		final Editor editor = pref.edit();
		
		loginUser = new LoginUser();
		
		campoDni = (EditText)findViewById(R.id.campoUser);
		campoPassword = (EditText)findViewById(R.id.campoPassword);
		validar = (Button)findViewById(R.id.botonInicioSesion);
		registrarse = (Button)findViewById(R.id.botonRegistrarse);
		pDialog = new ProgressDialog(getApplicationContext());		
				
			
		validar.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
																					
				final String userDni = campoDni.getText().toString();
				final String userPass = campoPassword.getText().toString();
							
				if(userDni.equalsIgnoreCase("") || userPass.equalsIgnoreCase("")){
					
					Toast.makeText(getApplicationContext(), "Alguno de los campos están vacíos", Toast.LENGTH_SHORT).show();			
				
				} else {
															
					try {
									
						editor.putString("key_name_user", userDni);
						editor.commit();
						loginUser.postData(userDni, userPass);
						boolean valido = loginUser.validOrNot();							
												
						//SI ES CORRECTO, IREMOS A LA PANTALLA DONDE VER LAS PISTAS LIBRES, OCUPADAS, ETC
						if(valido) {
							campoDni.setText("");
							campoPassword.setText("");
							Intent intent = new Intent(MainActivity.this,CalendarView.class);
							startActivity(intent);							
							
							
						//SI NO, AVISAMOS AL USUARIOS DE QUE ALGO HA IDO MAL
						} else {						
							Toast.makeText(getApplicationContext(), "El nombre de usuario no existe o la contraseña no es correcta, asegúrese o cree una una cuenta", Toast.LENGTH_LONG).show();						
						}
																																				
						} catch (Exception e) {
							e.printStackTrace();
					}													
			    }			
			}
		});		
															
		registrarse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MainActivity.this,PantallaNuevoUsuario.class);
				startActivity(intent);
						
			}
		});	                 				
	}		
}	
		

