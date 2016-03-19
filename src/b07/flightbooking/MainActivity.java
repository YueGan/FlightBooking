package b07.flightbooking;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;	
import android.widget.EditText;
import android.widget.TextView;
import phaseII.Admin;
import phaseII.BackEnd;
import phaseII.Client;
import phaseII.Flight;
import phaseII.InvalidInputException;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{
	public static final String BACKENDKEY = "studentManagerKey";
	private File clientMapFile;
	public static final String SYSTEMDATADIR = "systemdata";
	public String passwordFilePath;
	public String userMapFilePath;
	public String flightMapFilePath;
	Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File passwordDir = this.getApplicationContext().getDir(SYSTEMDATADIR, MODE_PRIVATE);
		File passwordsFile = new File(passwordDir, "passwords.txt");
		File userMapFile = new File(passwordDir, "UserMap.ser");
		File flightMapFile = new File(passwordDir, "FlightMap.ser");
		this.passwordFilePath = passwordsFile.getPath();
		this.userMapFilePath = userMapFile.getPath();
		this.flightMapFilePath = flightMapFile.getPath();

		button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	private boolean checkAuthencity(String username, String password) throws IOException{
		boolean result = false;
		String line;
		String [] temp;
		BufferedReader br = new BufferedReader(new FileReader(this.passwordFilePath));
		line = br.readLine();
		while (line != null && line != ""){
			temp = line.split(",");
			if (temp[0].equals(username) && temp[1].equals(password)){
				br.close();
				return true;
			}
			line = br.readLine();
		}
		br.close();
		return false;
	}
	@Override
	public void onClick(View v){
		boolean result = false;
		BackEnd login = null;
		// TODO Auto-generated method stub
		switch (v.getId()) 
		{
		case  R.id.button1:
			EditText usernameText = (EditText) findViewById(R.id.username);
		    EditText passwordText = (EditText) findViewById(R.id.password);
		    String username = usernameText.getText().toString();
		    String password = passwordText.getText().toString();
		    try {
				result = checkAuthencity(username, password);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    if (result){
		    	try {
		    		try {
						login = new BackEnd(username, password, this.userMapFilePath, this.flightMapFilePath);
					} catch (InvalidInputException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		} catch (IOException e) {
		    			e.printStackTrace();
		    			}
		    	}
		    
			if (login != null){
				Client user = login.getLoggedInUser();
				if (user instanceof Admin){
					Intent intent = new Intent(this, AdminHomePage.class);
					intent.putExtra(BACKENDKEY, login);
					startActivity(intent);}
				else if (user instanceof Client){
					Intent intent = new Intent(this,ClientHomePage.class );
					intent.putExtra(BACKENDKEY, login);
					startActivity(intent);}
				}
			else{
				TextView LoginText = (TextView) findViewById(R.id.loginError);
				LoginText.setText("Invalid Login");
			}
		}
	}
}
