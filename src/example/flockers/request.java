package example.flockers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

public class request extends JsonObjectRequest {
	public request(int method,String url,JSONObject jsonrequest,Response.Listener<JSONObject> listener, Response.ErrorListener errorlistener){
		super(method,url,jsonrequest,listener,errorlistener);
	}
	
	@Override
	 public Map<String,String> getHeaders() throws AuthFailureError {
		Map<String,String> headers = new HashMap <String,String>();
		if(!cookie.cookies.isEmpty())
			headers.put("Cookie",cookie.cookies);
		return headers;
	}

}
