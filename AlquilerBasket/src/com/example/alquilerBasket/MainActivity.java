package com.example.alquilerBasket;

import com.example.ejemplofruteria.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText campoUser;
	private EditText campoPassword;
	private Button validar;
	private Button registrarse;
	private LoginUser loginUser;
				
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);		
		
		loginUser = new LoginUser();
		
		campoUser = (EditText)findViewById(R.id.campoUser);
		campoPassword = (EditText)findViewById(R.id.campoPassword);
		validar = (Button)findViewById(R.id.botonInicioSesion);
		registrarse = (Button)findViewById(R.id.botonRegistrarse);
		
		validar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
									
				String userName = campoUser.getText().toString();
				String userPass = campoPassword.getText().toString();
				
				if(userName.equalsIgnoreCase("") || userPass.equalsIgnoreCase("")){
					
					Toast.makeText(getApplicationContext(), "Alguno de los campos están vacíos", Toast.LENGTH_SHORT).show();			
				
				} else {
											
					try {							
						loginUser.postData(userName, userPass);
						Boolean valido = loginUser.validOrNot();
												
						//SI ES CORRECTO, IREMOS A LA PANTALLA DONDE VER LAS PISTAS LIBRES, OCUPADAS, ETC
						if(valido) {						
							Toast.makeText(getApplicationContext(), "Usuario existente, vamos a la otra pantalla", Toast.LENGTH_LONG).show();
						
						//SI NO, AVISAMOS AL USUARIOS DE QUE ALGO HA IDO MAL
						} else {						
							Toast.makeText(getApplicationContext(), "El nombre de usuario no existe o la contraseña no es correcta, asegúrese o cree una una cuenta", Toast.LENGTH_LONG).show();						
						}
						
						campoUser.setText("");
						campoPassword.setText("");
																									
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


