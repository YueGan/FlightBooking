package b07.flightbooking;

import java.util.ArrayList;
import java.util.List;

import phaseII.BackEnd;
import phaseII.Flight;
import Managers.FlightMap;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class ViewFlightPage extends ActionBarActivity {
	private Intent intent;
	private FlightMap flights;
	private ArrayList searchFlights;
	private TextView textViewTime;
	private TimePicker timePicker;
	Button button1;

	private int hour;
	private int minute;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_flight_page);
		this.intent = getIntent();
		this.flights = (FlightMap) this.intent.getExtras().getSerializable(ClientHomePage.FLIGHTSKEY);

		button1 = (Button) findViewById(R.id.button1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_flight_page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
		
	}


	public void search(View view) {
		// Intent intent = new Intent(this,ViewFlightPage.class);
		// startActivity(intent);
		
		EditText destinationText = (EditText) findViewById(R.id.destinationText);
		EditText originText = (EditText) findViewById(R.id.originText);
		EditText dateText = (EditText) findViewById(R.id.dateText);
		String destination = destinationText.getText().toString();
		String origin = originText.getText().toString();
		String date = dateText.getText().toString();
		
		String hours = "";
		
		
		String [] searchKeys = {date, origin, destination};
		List<Flight> searchedFlights = flights.searchFlight(searchKeys);
		System.out.println(searchedFlights);
		

	}
}
