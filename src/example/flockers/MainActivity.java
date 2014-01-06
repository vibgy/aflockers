package example.flockers;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	private Spinner spinner1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Search");

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("My Events");
		spec2.setContent(R.id.tab2);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		
		addItemsOnSpinner1();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void addItemsOnSpinner1() {
		 spinner1 = (Spinner) findViewById(R.id.spinner);
		  List list = new ArrayList();
	      list.add("Play");
		  list.add("Dance");
		  list.add("Study");
		  list.add("Sleep");
		  ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spinner1.setAdapter(dataAdapter);
		
		  }


}
