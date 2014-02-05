package example.flockers;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

public class EventDetail extends Activity{
	String message[] = new String[8];
	TextView ename,date,time,place,fees,prize,description;
	public RequestQueue queue;
	Intent intent = getIntent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
		setContentView(R.layout.event_detail);
		
		ename=(TextView) findViewById(R.id.eventName);
		ename.setText(message[0]);
		date=(TextView) findViewById(R.id.Date);
		date.setText(message[1]);
		time=(TextView) findViewById(R.id.Time);
		time.setText(message[2]);
		place=(TextView) findViewById(R.id.Place);
		place.setText(message[3]);
		fees=(TextView) findViewById(R.id.Fees);
		fees.setText(message[4]);
		prize=(TextView) findViewById(R.id.Prize);
		prize.setText(message[5]);
		description=(TextView) findViewById(R.id.Description);
		description.setText(message[6]);
		
}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}