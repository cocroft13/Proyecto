package fernando.court_hire;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class MainActivity extends Activity {
	
	
	private ProgressDialog pDialog;
	private EditText campoUser;
	private EditText campoPassword;
	private Button validar;
	private Button registrarse;
	private LoginUser loginUser;	
	private Thread thr1;
	
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
						
																
						startDialog(userName, userPass);
						//loginUser.postData(userName, userPass);
						

						Boolean valido = loginUser.validOrNot();
												
						//SI ES CORRECTO, IREMOS A LA PANTALLA DONDE VER LAS PISTAS LIBRES, OCUPADAS, ETC
						if(valido) {
							campoUser.setText("");
							campoPassword.setText("");
							Intent intent = new Intent(MainActivity.this,PantallaPistas.class);
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
	
		
	private void startDialog(final String userName,final String pass){
			
		pDialog.setIcon(R.drawable.balon_icono);
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.setCancelable(false);
		pDialog = ProgressDialog.show(MainActivity.this, "Login de usuario", "Verificando...");
		
		//Lanzamos un nuevo hilo para el trabajo pesado.		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				loginUser.postData(userName,pass);
				pDialog.dismiss();
				handler.sendEmptyMessage(0);
			}
		}).start();
	
	}
	
	Handler handler = new Handler() {  
	    @Override  
	    public void handleMessage(Message msg) {
	         
	    }  
	};
	
	
		
}