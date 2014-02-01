package concertpass.app;

import java.util.ArrayList;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HistoryActivity extends Activity {
	//private MainActivity Mac;
	private TextView myText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history);
		ArrayList<String> list = getIntent().getStringArrayListExtra("key");
		LinearLayout lView = new LinearLayout(this);
		
			myText= new TextView(this);
			
			for (int i=0; i<list.size();i++){
				myText.append(list.get(i));
				myText.append("\n");
				
			}
			lView.addView(myText);
			setContentView(lView);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history, menu);
		return true;
	}

}
