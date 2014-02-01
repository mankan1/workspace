package concertpass.app;

import java.util.ArrayList;

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
	private ArrayList<String> locations;
	
	//Initializing contextPlaceConnector here, I initialize PlaceEventListener below and the logic for when a geofence is broken is below.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextCoreConnector = ContextCoreConnectorFactory.get(this);
        contextPlaceConnector = ContextPlaceConnectorFactory.get(this);
        
        checkContextConnectorStatus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private PlaceEventListener placeEventListener = new PlaceEventListener() {

        @Override
        public void placeEvent(PlaceEvent event) {
        	String placeNameAndIdCurrent = " You are now at " + event.getPlace().getPlaceName() + ". The ID number of this location is " + event.getPlace().getId();
        	String placeNameId = "Location is "+event.getPlace().getPlaceName() +". The ID number of this location is " + event.getPlace().getId();
            Toast toast = Toast.makeText(getApplicationContext(), placeNameAndIdCurrent, Toast.LENGTH_LONG);//changed from Toast.LENGTH_LONG
            toast.show();
            Log.i("found place", placeNameAndIdCurrent);
            locations= new ArrayList<String>();
            locations.add(placeNameId);
        }
    };
    
    private void checkContextConnectorStatus() {
        if (contextCoreConnector.isPermissionEnabled()) {
            // Gimbal is already enabled
        	startListeningForGeofences();
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
    @Override
    protected void onResume() {
    super.onResume(); 
    contextCoreConnector.setCurrentActivity(this);
    }
    @Override
    protected void onPause() {
    super.onPause(); contextCoreConnector.setCurrentActivity(null);
    }
    
    public void sendHistory(View view){
		Intent intent = new Intent(this, HistoryActivity.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		intent.putStringArrayListExtra("key", locations);
		startActivity(intent);	
	}
    public void sendLogin(View view){
		Intent intent = new Intent(this, LoginActivity.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		startActivity(intent);	
	}
    
    // return arrayList so that it can be used in other classes
    public ArrayList<String> getList(){
    	return locations;
    }
    
}

