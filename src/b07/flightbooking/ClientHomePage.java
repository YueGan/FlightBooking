package b07.flightbooking;
import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import phaseII.BackEnd;
import phaseII.Client;
import Managers.FlightMap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ClientHomePage extends ActionBarActivity {
	private Intent intent;
	private BackEnd login;
	private FlightMap flights;
	public static final String FLIGHTSKEY = "flightMapKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_client_home_page);
		this.intent = getIntent();
		this.login = (BackEnd) this.intent.getExtras().getSerializable(MainActivity.BACKENDKEY);
		Client user = this.login.getLoggedInUser();
		TextView LoginUser = (TextView) findViewById(R.id.loggedInUser);
		LoginUser.setText("Welcome " + user.getFirstName() + " " + user.getLastName());
		this.flights = this.login.getFlights();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.client_home_page, menu);
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

	/**
	 *Saved all the Flight and User in the system. 
	 * @param view
	 */
	public void logout(View view){
		this.login.saveData();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * Start the activity related to View Personal Information
	 * @param view
	 */
	public void showPersonalInfo(View view){
		Intent intent = new Intent(this,Test.class);
		startActivity(intent);
	}
	
	public void editPersonalInfo(View view){
		Intent intent = new Intent(this,Test.class);
		startActivity(intent);
	}
	
	public void search(View view){
		Intent intent = new Intent(this,ViewFlightPage.class);
		intent.putExtra(FLIGHTSKEY, flights);
		startActivity(intent);
	}
}
