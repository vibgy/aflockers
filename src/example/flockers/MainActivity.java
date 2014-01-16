package example.flockers;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity implements OnItemSelectedListener {
	public Spinner verbspinner,activityspinner,spinner3;
	public TextView change ;
	public RequestQueue queue,eventqueue;
	int l;
	String selected;
	Activity a= this;
	Context c=this;
	public final static String EXTRA_MESSAGE = "example.flockers.MESSAGE";
	Button Create;
	ListView listview;
	Responses response = new Responses(a,c);
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();
		activityspinner = (Spinner)findViewById(R.id.activityspinner);
		activityspinner.setVisibility(View.GONE);
	
		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Search");
	
		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setContent(R.id.tab2);
		spec2.setIndicator("My Events");
	
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		
		addListenerOnSpinnerItemSelection();
		
		queue = Volley.newRequestQueue(this);
		String url = "http://10.0.2.2:9292/verbs";
	    JsonArrayRequest jsObjRequest = new JsonArrayRequest(url,response.verbShowlistener,response.verbShowErrorlistener );
		queue.add(jsObjRequest);
	
		/*my events tab*/
	
		String URL= "http://10.0.2.2:9292/myEvents.json";
        JsonArrayRequest jsArrRequest = new JsonArrayRequest(URL,response.myEventsOrganisedListlistener
			, response.myEventsOrganisedListErrorlistener);
		queue.add(jsArrRequest);
     }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void addListenerOnSpinnerItemSelection(){
		    verbspinner = (Spinner) findViewById(R.id.verbspinner);
		    verbspinner.setOnItemSelectedListener(this);
		    activityspinner = (Spinner)findViewById(R.id.activityspinner);
		    activityspinner.setOnItemSelectedListener(new activityshow(a,c));
		    listview=(ListView) findViewById(R.id.listview);
		  }
		@Override
	    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			queue = Volley.newRequestQueue(this);
			String url = "http://10.0.2.2:9292/activities?verb=" + selected;
			JSONObject obj = new JSONObject();
			try{
				selected = parent.getItemAtPosition(pos).toString();
				Variables.verb=selected;
				change = (TextView) findViewById(R.id.txt1);
				obj.put("verb",selected);
			}
			catch(JSONException e){
			}
			JsonArrayRequest jsObjRequest = new JsonArrayRequest(url ,response.activityShowlistener,response.activityShowErrorlistener);
			queue.add(jsObjRequest);	
		}
		@Override
	    public void onNothingSelected(AdapterView<?> parent) {
	    }
		public void createEvent(View view)
		{
			SendIntent s1 = new SendIntent(this);
			s1.eventClick(view);
		}
		
}