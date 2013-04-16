package br.com.rubythree.employees;


import java.io.IOException;


import us.monoid.web.Resty;
import static us.monoid.web.Resty.*;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends Activity {
	final Resty r = new Resty();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add);

		setThreadPolicy();

		try {
			postEmployees();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressLint("NewApi")
	public void setThreadPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	public void postEmployees() throws IOException {

		Button button = (Button) findViewById(R.id.save_button);
		
		button.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				try {
					
					final EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
					final EditText salaryEditText = (EditText) findViewById(R.id.salary_edit_text);
					
					String name = nameEditText.getEditableText().toString();
					String salary = salaryEditText.getEditableText().toString();
					
					r.json("http://192.168.0.104:3000/employees",
							form(data("employee[name]", name),
									data("employee[salary]", salary)));
					
					Toast.makeText(AddActivity.this, "Saved =)", Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
}