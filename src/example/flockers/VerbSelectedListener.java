package example.flockers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

public class VerbSelectedListener implements OnItemClickListener{

	public Activity activity;
	public Context context;
	RequestQueue queue;
	
	public VerbSelectedListener(Activity activity,Context context){
		this.activity = activity;
		this.context = context;
	}
	@Override
    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
		Responses response = new Responses(activity,context);
		queue = Volley.newRequestQueue(context);
		Variables.verb = parent.getItemAtPosition(position).toString();
		String url = MainActivity.SERVER_ADDRESS + "/activities?verb="+ Variables.verb;
		JsonArrayRequest jsObjRequest = new JsonArrayRequest (url,response.activityShowlistener, response.activityShowErrorlistener);
		queue.add(jsObjRequest);	
	}
};
