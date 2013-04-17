package br.com.rubythree.employees;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SingleMenuItemActivity extends Activity{


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_menu);
        
		Bundle extras = getIntent().getExtras();
		String name = extras.getString("name");
		String salary = extras.getString("salary");
		
		TextView nameTextView = (TextView) findViewById(R.id.name);
		TextView salaryTextView = (TextView) findViewById(R.id.salary);
		
		nameTextView.setText(getString(R.string.user_name, name));
		salaryTextView.setText(getString(R.string.user_salary, salary));
	}
}