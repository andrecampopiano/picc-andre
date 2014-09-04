package br.com.cast.treinamento.app.widget;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.cast.treinamento.app.R;
import br.com.cast.treinamento.app.domain.Contato;

public class ContatoAdapter extends BaseAdapter{
	
	private Activity contexto;
	private List<Contato> itens;
	
	
	public ContatoAdapter(Activity contexto, List<Contato> itens ) {
		super();
		this.itens = itens;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itens.size();
	}

	@Override
	public Contato getItem(int posicao) {
		// TODO Auto-generated method stub
		return itens.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		Contato item = getItem(posicao);
		return item.getId();
	}

	@Override
	public View getView(int posicao, View arg1, ViewGroup viewPai) {
		
		LayoutInflater layoutInflater = contexto.getLayoutInflater();
		View layoutItem = layoutInflater.inflate(R.layout.item_list_view_contato, null);		
		
		TextView lblNome = (TextView) layoutItem.findViewById(R.id.lblNome);
		Contato item = getItem(posicao);
		lblNome.setText(item.getNome());
		
		TextView lblTelefone = (TextView) layoutItem.findViewById(R.id.lblTelefone);
		lblTelefone.setText(item.getTelefone());
		
	if(posicao %2 == 0){
		layoutItem.setBackgroundColor(contexto.getResources().getColor(R.color.minha_cor));
		
	}
		
		return layoutItem;
	}

}
