package com.example.flocker;

import org.json.JSONObject;
import org.json.JSONException;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.flocker.R;

public class MainActivity extends Activity {

	TextView unameText ;
	RequestQueue queue ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		queue = Volley.newRequestQueue(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void login(View view){
		queue = Volley.newRequestQueue(this);
		String url = "http://10.0.2.2:4567/login";
		JSONObject obj = new JSONObject();
		try{
			obj.put("Username",((EditText) findViewById(R.id.username)).getText() );		
			obj.put("Password",((EditText) findViewById(R.id.password)).getText() );
		}
		catch(JSONException e){
  
		}
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url ,obj,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					// TODO Auto-generated method stub
					try{
						JSONObject obj2 = response;
						unameText = (TextView) findViewById(R.id.usernameText);
						unameText.setText(""+obj2.get("uname"));
					}
					catch(JSONException pe){				
					}
				}
			},new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
					unameText = (TextView) findViewById(R.id.usernameText);
					unameText.setText("niceone123");
				}
			});
		queue.add(jsObjRequest);	
	}
}
