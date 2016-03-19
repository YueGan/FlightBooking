package b07.flightbooking;

import java.io.File;
import java.io.IOException;

import phaseII.BackEnd;
import phaseII.InvalidInputException;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UploadFlightInfo extends ActionBarActivity {
	public static final String FLIGHTFILE = "FlightFile.csv";

	private Intent intent;
	private BackEnd login;
	public String flightFilePath;
	Button uploadFlightButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_flight_info);
		this.intent = getIntent();
		this.login = (BackEnd) this.intent.getExtras().getSerializable(MainActivity.BACKENDKEY);
	    File clientsFile = new File("FLIGHTFILE");
	    this.flightFilePath = clientsFile.getPath();
	    
	    uploadFlightButton = (Button) findViewById(R.id.uploadFlight);
	    uploadFlightButton.setOnClickListener((OnClickListener) this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload_flight_info, menu);
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
	
	public void logout(View view){
		this.login.saveData();
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void uploadFlights(View v) throws NumberFormatException, IOException, InvalidInputException {
		switch(v.getId())
		{
		case R.id.uploadFlight:
			EditText filePath = (EditText) findViewById(R.id.filePath);
			String path = filePath.getText().toString();
			
			if (path == flightFilePath){
				login.loadFlights(path);
				TextView messageText = (TextView) findViewById(R.id.uploadMessage);
				messageText.setText("The Flights have been uploaded.");
			}else{
				TextView messageText = (TextView) findViewById(R.id.uploadMessage);
				messageText.setText("The file does not exist.");
			}					
		}
	}
	
}
