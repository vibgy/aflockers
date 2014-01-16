package example.flockers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class Responses {

	Activity activity;
	Context context;
	public Responses(Activity activity,Context context)
	{
		this.activity = activity;
		this.context = context;
	}
	
	Response.Listener<JSONArray> myEventsOrganisedListlistener = new Response.Listener<JSONArray>(){
		@Override
		   public void onResponse(JSONArray response)
		   {
		          try{
			      int l=response.length();
			      ListView listview = (ListView) activity.findViewById(R.id.listview);
				  List<String> list = new ArrayList<String>();
				  for(int i=0;i<l;i++){
				  JSONObject actor = response.getJSONObject(i);
				  String name = actor.getString("ename");
				  list.add(name);
				  }
				  ArrayAdapter<String> dataAdapter = new ArrayAdapter <String> (context,android.R.layout.simple_list_item_1, list);
				  listview.setAdapter(dataAdapter);
				  listview.setOnItemClickListener(new EventListener(activity,context));
		          }
				  catch(Exception e){
					  e.printStackTrace();
				  }
		   }	
	};
	
	Response.Listener<JSONArray> activityShowlistener = new Response.Listener<JSONArray>() {
		@Override
		public void onResponse(JSONArray response) {
			// TODO Auto-generated method stub
			try{				  
				  Spinner spinner2 = (Spinner) activity.findViewById(R.id.activityspinner);	
				  spinner2.setVisibility(View.VISIBLE);
				  
				  List<String> list = new ArrayList<String>();
				  for (int i =0; i<response.length(); i++){
				      JSONObject obj2 = response.getJSONObject(i);
				      list.add(""+obj2.get("activity"));
				  }
				  
				  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, list);
				  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				  spinner2.setAdapter(dataAdapter);            
				}
			catch(JSONException e){
			    e.printStackTrace();
			}
		}
	};
	
	Response.Listener<JSONArray> verbShowlistener = new Response.Listener<JSONArray>() {
	    @Override
	    public void onResponse(JSONArray response)
	    {
	          try{
			      int l=response.length();
				  Spinner spinner1 = (Spinner) activity.findViewById(R.id.verbspinner);
				  List<String> list = new ArrayList<String>();
				  for(int i=0;i<l;i++){
					  JSONObject actor = response.getJSONObject(i);
					  String name = actor.getString("verb");
					  list.add(name);
				  }
				  ArrayAdapter<String> dataAdapter = new ArrayAdapter <String> (context,android.R.layout.simple_spinner_item, list);
				  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				  spinner1.setAdapter(dataAdapter);
	          }
			  catch(Exception e){
				  e.printStackTrace();
			  }
	    }
	};
	
	Response.ErrorListener verbShowErrorlistener = new Response.ErrorListener() {
        @Override
		public void onErrorResponse(VolleyError error) {
            Log.e("ERROR", "error getting " + ": " + error);
		}
	};
	
	Response.ErrorListener activityShowErrorlistener = new Response.ErrorListener() {
        @Override
		public void onErrorResponse(VolleyError error) {
            Log.e("ERROR", "error getting " + ": " + error);
		}
	};
	
	Response.ErrorListener myEventsOrganisedListErrorlistener = new Response.ErrorListener() {
        @Override
		public void onErrorResponse(VolleyError error) {
            Log.e("ERROR", "error getting " + ": " + error);
		}
	};
}
