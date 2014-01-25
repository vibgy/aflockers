package example.flockers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class EventSelectedListener implements OnItemClickListener {
	public Activity activity;
	public Context context;
	public RequestQueue queue;
	String selected;
	
	public EventSelectedListener(Activity activity,Context context)
	{
		this.activity = activity;
		this.context = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos,long id) {
		Responses response = new Responses(activity,context);
		queue = Volley.newRequestQueue(context);
		Variables.selectedEvent = parent.getItemAtPosition(pos).toString();
		String url = "http://10.0.2.2:9292/eventDetail?record="+Variables.selectedEvent;
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(url,null, response.eventDetailShowListener,response.eventDetailShowErrorlistener);
		queue.add(jsObjRequest);
	}
		
}
