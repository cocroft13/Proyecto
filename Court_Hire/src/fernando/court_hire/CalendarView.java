package fernando.court_hire;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

public class CalendarView extends Activity{
	
	Button botonAceptarFecha;
	android.widget.CalendarView calendar;
	String curDate;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_view);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().build();
		StrictMode.setThreadPolicy(policy);
			
		botonAceptarFecha = (Button)findViewById(R.id.botonVolverPantallaPistas);
		
		calendar = (android.widget.CalendarView)findViewById(R.id.calendarioReservas);

		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			
		
			@Override
			public void onSelectedDayChange(android.widget.CalendarView view,
					int year, int month, int dayOfMonth) {
				
				int d = dayOfMonth;	
				int m = month + 1;
				int y = year;
				
				if (month < 10){					
					curDate = String.valueOf(y) + "-" + String.valueOf("0"+m) + "-" + String.valueOf(d);					
				
				} else {
								
					curDate = String.valueOf(y) + "-" + String.valueOf(m) + "-" + String.valueOf(d);				
					Toast.makeText(getApplicationContext(), curDate, Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		botonAceptarFecha.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					
				Bundle bundle = new Bundle();
				bundle.putString("fecha", curDate);
				
				Intent intent = new Intent(CalendarView.this,PantallaPistas.class);
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
				
			}
		});
	
	}

}
