package example.flockers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class activityshow implements OnItemSelectedListener {
	private Spinner spinner4;
	public Activity activity;
	public Context context;
	public TextView change ;
	public RequestQueue queue;
	String selected;
	
	public activityshow(Activity activity,Context context)
	{
		this.activity = activity;
		this.context = context;
	}
	
	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {		  
		queue = Volley.newRequestQueue(context);
		String url = "http://10.0.2.2:4567/event";
		JSONObject obj = new JSONObject();
		try{
			selected = parent.getItemAtPosition(pos).toString();
			Variables.selectedActivity=selected;
			change = (TextView) activity.findViewById(R.id.txt1);
			obj.put("name",selected);
		}
		catch(JSONException e){
		}
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url ,obj,
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					// TODO Auto-generated method stub
					try{
						  JSONArray events = response.getJSONArray("make");
						  spinner4 = (Spinner) activity.findViewById(R.id.spinner4);	
						  spinner4.setVisibility(View.VISIBLE);
						  Button CreateEvent=(Button)activity.findViewById(R.id.CreateEvent);
						  CreateEvent.setVisibility(0);
						  List<String> list = new ArrayList<String>();
						  
						  for (int i =0; i<events.length(); i++){
						  JSONObject obj2 = events.getJSONObject(i);
					      list.add(""+obj2.get("ename"));
						  }
						  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);
						  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
						  spinner4.setAdapter(dataAdapter);  
						  spinner4.setOnItemSelectedListener(new SearchListener(activity,context));
						}
					catch(JSONException pe){				
					}
				}
			},new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					// TODO Auto-generated method stub
				}
			});
		queue.add(jsObjRequest);	
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}