package br.com.cast.treinamento.app;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MeuOnClickListener implements OnClickListener {

	@Override
	public void onClick(View view) {
		Log.d("MEU DEBUG", "Clicou pra caramba!");
	}

}
