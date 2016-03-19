package b07.flightbooking;

import phaseII.BackEnd;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayFlights extends ActionBarActivity {
	private Intent intent;
	private BackEnd login;
	private String[] flights = new String[]{
			"Flight 1", "Flight 2", "Flight 3", "Flight 4", 
			"Flight 5", "Flight 6", "Flight 7", "Flight 8",
			"Flight 9", "Flight 10"
	};
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_flights);
		this.intent = getIntent();
		this.login = (BackEnd) this.intent.getExtras().getSerializable(MainActivity.BACKENDKEY);
		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);
        
        // Defined Array values to show in ListView
       

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
               android.R.layout.simple_list_item_1, flights);

        // Assign adapter to ListView
        listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_flights, menu);
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
}
