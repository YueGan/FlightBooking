package b07.flightbooking;

import phaseII.BackEnd;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AdminHomePage extends ActionBarActivity {
	private Intent intent;
	private BackEnd login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home_page);
		this.intent = getIntent();
		this.login = (BackEnd) this.intent.getExtras().getSerializable(MainActivity.BACKENDKEY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_home_page, menu);
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
	
	public void showPersonalInfo(View view){
		Intent intent = new Intent(this,Test.class);
		startActivity(intent);
	}
	
	public void editPersonalInfo(View view){
		Intent intent = new Intent(this,Test.class);
		startActivity(intent);
	}
	
	public void search(View view){
		Intent intent = new Intent(this,Test.class);
		startActivity(intent);
	}

}
