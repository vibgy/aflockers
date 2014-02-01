package example.flockers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Lauch extends Activity {

	RequestQueue queue = Volley.newRequestQueue(this);
	Activity activty= this;
	Context context=this;
	Responses response = new Responses(activty,context);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lauch);
		String url = "http://10.0.2.2:9292/auth";
			request jsObjRequest = new request(Request.Method.GET, url ,null,
							response.launchListener,response.launcherrorListener);
	//		jsObjRequest.getHeaders();
		queue.add(jsObjRequest);	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lauch, menu);
		return true;
	}

}
