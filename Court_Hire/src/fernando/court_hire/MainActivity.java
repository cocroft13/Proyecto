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

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	private ProgressDialog pDialog;
	private EditText campoUser;
	private EditText campoPassword;
	private Button validar;
	private Button registrarse;
	private LoginUser loginUser;

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);		
		
		loginUser = new LoginUser();
		
		campoUser = (EditText)findViewById(R.id.campoUser);
		campoPassword = (EditText)findViewById(R.id.campoPassword);
		validar = (Button)findViewById(R.id.botonInicioSesion);
		registrarse = (Button)findViewById(R.id.botonRegistrarse);
		pDialog = new ProgressDialog(getApplicationContext());		
			
		validar.setOnClickListener(new View.OnClickListener() {
				
			@Override
			public void onClick(View v) {
																					
				final String userName = campoUser.getText().toString();
				final String userPass = campoPassword.getText().toString();
							
				if(userName.equalsIgnoreCase("") || userPass.equalsIgnoreCase("")){
					
					Toast.makeText(getApplicationContext(), "Alguno de los campos están vacíos", Toast.LENGTH_SHORT).show();			
				
				} else {
															
					try {
											
						loginUser.postData(userName, userPass);
						boolean valido = loginUser.validOrNot();								
												
						//SI ES CORRECTO, IREMOS A LA PANTALLA DONDE VER LAS PISTAS LIBRES, OCUPADAS, ETC
						if(valido) {
							campoUser.setText("");
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
		
		

