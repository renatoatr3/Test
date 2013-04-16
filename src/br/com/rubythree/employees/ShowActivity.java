package br.com.rubythree.employees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ShowActivity extends ListActivity {
    private static final String Name = "name";
    private static final String Salary = "salary";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		
		ListView listview = (ListView) findViewById(R.id.list_view);
		
		final ArrayList<String> list = new ArrayList<String>();
		ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
		
		ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		String response = makeRequest("http://192.168.0.104:3000/employees.json");
		
		
		
		try {
		JSONArray json = new JSONArray(response);
			for (int i = 0; i < json.length(); i++) {
				String name = json.getJSONObject(i).getString("name");
				
				list.add(name);
			}
		} catch (JSONException e) {
			// TODO: handle exception
			e.printStackTrace();
		
		}
		ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(mArrayAdapter);
	
	ListAdapter adapter = new SimpleAdapter(this, contactList,
            R.layout.show,
            new String[] {Name, Salary }, new int[] {
                    R.id.name, R.id.salary });

    setListAdapter(adapter);

    // selecting single ListView item
    ListView lv = getListView();

    lv.setOnItemClickListener(new OnItemClickListener() {
    	public void onItem(AdapterView<?> pare) {
			
		}
	});
}
	
	private String makeRequest(String urlAdress) {
		HttpURLConnection con = null;
		URL url = null;
		String response = null;
		try {
			url = new URL(urlAdress);
			con = (HttpURLConnection) url.openConnection();
			response = readStream(con.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			con.disconnect();
		}
		return response;
	}
	private String readStream(InputStream in){
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try{
			reader = new BufferedReader(new InputStreamReader(in));
		String line = null;
		while ((line = reader.readLine()) !=null){
			builder.append(line + "\n");
		  }
		} catch (IOException e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			if (reader != null){
				try{
					reader.close();
				
				}catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}
			}
		}
		return builder.toString();
	} 

}
