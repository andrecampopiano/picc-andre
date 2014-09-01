package br.com.cast.treinamento.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private Button btnExemplo;
	private EditText txtExemplo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main);

		txtExemplo = (EditText) super.findViewById(R.id.txtExemplo);

		btnExemplo = (Button) super.findViewById(R.id.btnExemplo);
		btnExemplo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Toast.makeText(view.getContext(), String.format("Você digitou: %s", txtExemplo.getText().toString()), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
