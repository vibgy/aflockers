package example.flockers;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class SendIntent {
	
	private Context context; 
	public SendIntent(Context context){
		this.context = context;
	}
	public void eventClick(View view){
		Intent intent = new Intent(context, CreateEvent.class);
		String message[] = new String[2];
		message[0] = Variables.verb;
		message[1]=Variables.selectedActivity;
		intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
		context.startActivity(intent);
		}
	public void eventDetail(String ename,String date,String time,String place,String fees,String prize,String description){
		Intent intent = new Intent(context, EventDetail.class);
		String message[]=new String[7];
		message[0]=ename;
		message[1]=date;
		message[2]=time;
		message[3]=place;
		message[4]=fees;
		message[5]=prize;
		message[6]=description;
		intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
		context.startActivity(intent);
	}
	public void eventSearch(String ename,String date,String time,String place,String fees,String prize,String description){
		Intent intent = new Intent(context, EventSearch.class);
		String message[]=new String[7];
		message[0]=ename;
		message[1]=date;
		message[2]=time;
		message[3]=place;
		message[4]=fees;
		message[5]=prize;
		message[6]=description;
		intent.putExtra(MainActivity.EXTRA_MESSAGE, message);
		context.startActivity(intent);
	}
}