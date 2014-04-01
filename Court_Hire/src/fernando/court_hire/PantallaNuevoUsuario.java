package fernando.court_hire;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PantallaNuevoUsuario extends Activity {
	
	 private RegisterUser registerUser;
	 private EditText campoNombre,campoDni,campoEmail,campoPassword,campoRePassword;	
	 private Button botonRegistrar;
	 private Button botonCancelar;
	
	 private String nombre,dni,email,pass,rePass;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_pantalla_nuevo_usuario);
		
		registerUser = new RegisterUser();
		
		campoNombre = (EditText)findViewById(R.id.RegCampoNombre);
		campoDni = (EditText)findViewById(R.id.RegCampoDni);
		campoEmail = (EditText)findViewById(R.id.RegCampoEmail);
		campoPassword = (EditText)findViewById(R.id.RegCampoPassword);
		campoRePassword = (EditText)findViewById(R.id.RegCampoPassword2);
				
		botonRegistrar = (Button)findViewById(R.id.botonValidarNuevoUsuario);
		botonCancelar = (Button)findViewById(R.id.botonCancelar);						
		
		//EVENTO DEL BOTON REGISTRAR, UNA VEZ INTRODUCIDOS LOS CAMPOS, SE COMPROBARÁN Y SI TODO ES CORRECTO
		//SE ENVIARA LA PETICION HTTP-POST PARA INSERTAR EL NUEVO USUARIO EN LA BD.
		
		botonRegistrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				nombre = campoNombre.getText().toString();
				dni = campoDni.getText().toString();
				email = campoEmail.getText().toString();
				pass = campoPassword.getText().toString();
				rePass = campoRePassword.getText().toString();
										
				if(nombre.equalsIgnoreCase("") || dni.equalsIgnoreCase("") || email.equalsIgnoreCase("") ||
					pass.equalsIgnoreCase("") || rePass.equalsIgnoreCase("")){
			
					Toast.makeText(getApplicationContext(), "Alguno de los campos está vacío, complételo", Toast.LENGTH_SHORT).show();							
				}
				
				if(!comprobarDni(dni)) {					
					
					Toast.makeText(getApplicationContext(), "El DNI introducido no es válido", Toast.LENGTH_SHORT).show();
					
				} else {					
					
					try {
					//SE HACE LA LLAMADA A POSTDATA PARA ENVIAR LOS DATOS
					registerUser.postData(nombre, dni, email, pass, rePass);					
					Boolean valido = registerUser.validOrNot();
										
					if(valido){
						
						Toast.makeText(getApplicationContext(), "Datos correctos, registrando usuario", Toast.LENGTH_SHORT).show();
						campoNombre.setText("");
						campoDni.setText("");
						campoEmail.setText("");
						campoPassword.setText("");
						campoRePassword.setText("");					
											
					} else {
						
						Toast.makeText(getApplicationContext(), "Ya hay un usuario con esa identificación", Toast.LENGTH_SHORT).show();
						
					}	
					} catch(Exception e){
						e.printStackTrace();
					}			
				}							
			}
		});
		
		
		botonCancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//ACCION DEL BOTON CANCELAR
				
			}
		});
		
		
		
		
	}
		
	//METODO PARA COMPROBAR SI EL DNI INTRODUCIDO ES UN DNI VALIDO O NO
	private Boolean comprobarDni(String dni) {
		
		Boolean valido = false;
		
		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		
		Matcher matcher = pattern.matcher(dni);
			
		if(matcher.matches()){
			
			String letra = matcher.group(2);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int index = Integer.parseInt(matcher.group(1));
			
			index = index % 23;
			
			String reference = letras.substring(index,index +1);
			
			if (reference.equalsIgnoreCase(letra)){
				
				valido = true;				
			}		
		}				
		return valido;	
	}	
}

