package fernando.court_hire;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Toast;

public class Dialogs extends DialogFragment{
	
	public AlertDialog onCreateDialog(Bundle savedInstanceState){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("¿Desea reservar esta pista?");
		builder.setTitle("Información");
		builder.setPositiveButton("Si", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
				Toast.makeText(getActivity(), "Se realiza la reserva...", Toast.LENGTH_SHORT).show();						
				
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
