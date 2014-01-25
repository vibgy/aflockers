package example.flockers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class ActivitySelectedListener implements OnItemClickListener {
	public Activity activity;
	public Context context;
	public RequestQueue queue;
	
	public ActivitySelectedListener(Activity activity,Context context)
	{
		this.activity = activity;
		this.context = context;
	}
	
	@Override
    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		Responses response = new Responses(activity,context);
		queue = Volley.newRequestQueue(context);
		Variables.selectedActivity = parent.getItemAtPosition(position).toString();
		String url = "http://10.0.2.2:9292/searchEventByActivity?record="+Variables.selectedActivity;
		JsonObjectRequest jsObjRequest = new JsonObjectRequest(url ,null,
				response.eventShowlistener,response.eventShowErrorlistener);
			queue.add(jsObjRequest);	
	
	}	
}