package concertpass1.app;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.qualcommlabs.usercontext.Callback;
import com.qualcommlabs.usercontext.ContextCoreConnector;
import com.qualcommlabs.usercontext.ContextCoreConnectorFactory;
import com.qualcommlabs.usercontext.ContextPlaceConnector;
import com.qualcommlabs.usercontext.ContextPlaceConnectorFactory;
import com.qualcommlabs.usercontext.PlaceEventListener;
import com.qualcommlabs.usercontext.protocol.PlaceEvent;

public class MainActivity extends Activity {
	private ContextCoreConnector contextCoreConnector;
	private ContextPlaceConnector contextPlaceConnector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contextCoreConnector = ContextCoreConnectorFactory.get(this);
		contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        checkContextConnectorStatus();
	}
	
	private PlaceEventListener placeEventListener = new PlaceEventListener() {

        @Override
        public void placeEvent(PlaceEvent event) {
            String placeNameAndId = " You are now at " + event.getPlace().getPlaceName() + ". The ID number of this location is " + event.getPlace().getId();
            Toast toast = Toast.makeText(getApplicationContext(), placeNameAndId, Toast.LENGTH_LONG);
            toast.show();
            Log.i("found place", placeNameAndId);
        }
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void checkContextConnectorStatus() {
        if (contextCoreConnector.isPermissionEnabled()) {
        	startListeningForGeofences();
            // Gimbal is already enabled
        }
        else {
            contextCoreConnector.enable(this, new Callback<Void>() {

                @Override
                public void success(Void arg0) {
                	startListeningForGeofences();
                    // Gimbal is ready
                }

                @Override
                public void failure(int arg0, String arg1) {
                    Log.e("failed to enable", arg1);
                }
            });
        }
    }
	
	private void startListeningForGeofences() {
        contextPlaceConnector.addPlaceEventListener(placeEventListener);
        
    }
	
	//sends to register page from initial page when Register button is clicked
	/*
	public void sendRegister(View view){
		Intent intent = new Intent(this, RegisterActivity.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		startActivity(intent);
			
		}
		*/
		
		//sends to register page from initial page when Log In button is clicked
		
	public void sendLogin(View view){
		Intent intent = new Intent(this, LoginActivity.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		startActivity(intent);
			
	}
	/*
	public void sendFiltersPage(View view){
		Intent intent = new Intent(this, FiltersPage.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		startActivity(intent);
			
	}
	*/
}
