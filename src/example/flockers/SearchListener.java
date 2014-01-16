package example.flockers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class SearchListener implements OnItemSelectedListener {
	public Activity activity;
	public Context context;
	public RequestQueue queue;
	String selected;
	
	public SearchListener(Activity activity,Context context)
	{
		this.activity = activity;
		this.context = context;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
		queue = Volley.newRequestQueue(context);
		String url = "http://10.0.2.2:4567/search";
		JSONObject obj = new JSONObject();
		try{
			selected = parent.getItemAtPosition(pos).toString();
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
						String ename = response.getString("ename");
						String date=response.getString("date");
						String time=response.getString("time");
						String place=response.getString("place");
						String fees=response.getString("fees");
						String prize=response.getString("prize");
						String description=response.getString("description");
						
						SendIntent obj=new SendIntent(context);
						obj.eventSearch(ename,date,time,place,fees,prize,description);
					    }
						catch(Exception e){
							e.printStackTrace();
						}
				}
			},new Response.ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
				}
			});
		queue.add(jsObjRequest);	
	}
	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
	}

}
