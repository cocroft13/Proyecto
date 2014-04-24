package fernando.court_hire;

import java.io.IOException;
import java.sql.Date;

import org.apache.http.client.ClientProtocolException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class Dialogs extends DialogFragment{
	
	String dni;
	String pista;
	String fecha;
	String horario;
	
	public Dialogs(){}
	
	public Dialogs(String dni,String pista,String fecha,String horario){
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
				
		this.dni = dni;
		this.pista = pista;
		this.fecha = fecha;
		this.horario = horario;		
	}
	
	
	public AlertDialog onCreateDialog(Bundle savedInstanceState){
					
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("¿Desea reservar esta pista?");
		builder.setTitle("Información");
		builder.setPositiveButton("Si", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
								
				NewHire newHire = new NewHire();
				try {
					newHire.insertHire(dni, pista, fecha, horario);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});		
		
		
		builder.setNegativeButton("No", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(getActivity(), "No se realiza la reserva..", Toast.LENGTH_SHORT).show();
																
			}
		});
			
		return builder.create();
	}
}
