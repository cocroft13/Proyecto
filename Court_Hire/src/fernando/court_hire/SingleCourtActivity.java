package fernando.court_hire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

public class SingleCourtActivity extends Activity{
	
	//JSON NODE KEYS
	private static final String TAG_NAME = "nombre_pista";
	private static final String TAG_HORARIO = "horario_pista";
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_contact);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		
		//GETTING INTENT DATA
		Intent intent = getIntent();
		
		//GET JSON VALUES FROM PREVIOUS INTENT
		String name = intent.getStringExtra(TAG_NAME);
		String horario = intent.getStringExtra(TAG_HORARIO);
				
		
		//DISPLAYING ALL VALUES ON THE SCREEN
		TextView lblName = (TextView) findViewById(R.id.name_label);
		TextView lblHorario = (TextView)findViewById(R.id.horario_label);	
				
		lblName.setText(name);
		lblHorario.setText(horario);
		
		
	}
}
