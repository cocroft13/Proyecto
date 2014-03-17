package com.example.alquilerBasket;

import com.example.ejemplofruteria.R;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText campoUser;
	private EditText campoPassword;
	private Button validar;
	private Button registrarse;
	private RegisterUser registerUser;
				
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);			
		
		registerUser = new RegisterUser();
		
		campoUser = (EditText)findViewById(R.id.campoUser);
		campoPassword = (EditText)findViewById(R.id.campoPassword);
		validar = (Button)findViewById(R.id.botonInicioSesion);
		registrarse = (Button)findViewById(R.id.botonRegistrarse);
		
		validar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
								
				String userName = campoUser.getText().toString();
				String userPass = campoPassword.getText().toString();
											
				try {							
					registerUser.postData(userName, userPass);
					Boolean valido = registerUser.showToastMessage();
					
					if(valido) {						
						Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_LONG).show();
						
					} else {						
						Toast.makeText(getApplicationContext(), "Nombre de usuario no valido, introduzca otro nombre", Toast.LENGTH_LONG).show();						
					}
					
					campoUser.setText("");
					campoPassword.setText("");
																								
				} catch (Exception e) {
					e.printStackTrace();
				}													
			}	
		});		
	}	
}	


