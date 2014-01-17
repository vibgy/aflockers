package example.flockers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class CreateEvent extends Activity{
	Button Create;
	TextView abc;
	EditText ename,date,time,place,fees,prize,description;
	public RequestQueue queue;
	String message[] = new String[2];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		message = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
		setContentView(R.layout.create_event);
		queue = Volley.newRequestQueue(this);

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void create(View view){
		// TODO Auto-generated method stubqueue = Volley.newRequestQueue(this);
		String url = "http://10.0.2.2:9292/events";
		JSONObject obj = new JSONObject();
		try{
			ename=(EditText) findViewById(R.id.ename);
			obj.put("Ename",(""+ename.getText()));
			obj.put("Verb",message[0]);
			abc = (TextView) findViewById(R.id.txt1);
			abc.setText(message[1]);
			obj.put("Activity",message[1]);
			date=(EditText) findViewById(R.id.date);
			obj.put("Date",(""+date.getText()));
			time=(EditText) findViewById(R.id.time);
			obj.put("Time",(""+time.getText()));
			place=(EditText) findViewById(R.id.place);
			obj.put("Place",(""+place.getText()));
			fees=(EditText) findViewById(R.id.fees);
			obj.put("Fees",(""+fees.getText()));
			prize=(EditText) findViewById(R.id.prize);
			obj.put("Prize",(""+prize.getText()));
			description=(EditText) findViewById(R.id.description);
			obj.put("Description",(""+description.getText()));
		}
		catch(JSONException e){
		}
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url ,obj,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
				}
			},new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
				}
			});
		queue.add(jsObjRequest);	
		
	}

}
