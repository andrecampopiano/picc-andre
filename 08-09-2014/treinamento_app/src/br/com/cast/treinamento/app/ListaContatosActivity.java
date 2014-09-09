package br.com.cast.treinamento.app;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.cast.treinamento.app.domain.Contato;
import br.com.cast.treinamento.app.service.ContatoService;
import br.com.cast.treinamento.app.widget.ContatoAdapter;

/**
 * Activity responsável pela listagem e disponibilização das funcionalidades da entidade {@link Contato}.
 * 
 * @author venilton.junior
 */
public class ListaContatosActivity extends LifeCicleActivity {

	private ListView listViewContatos;
	private Contato contatoSelecionado;

	@Override
	public String getActivityName() {
		return this.getClass().getSimpleName();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_lista_contatos);

		super.getSupportActionBar().setSubtitle(R.string.subtitulo_lista_contatos);

		listViewContatos = (ListView) super.findViewById(R.id.listViewContatos);

		super.registerForContextMenu(listViewContatos);

		listViewContatos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int posicao, long id) {
				contatoSelecionado = (Contato) adapter.getItemAtPosition(posicao);

			}

		});

		listViewContatos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int posicao, long id) {
				contatoSelecionado = (Contato) adapter.getItemAtPosition(posicao);
				return false;
			}

		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		carregarListView();
	}

	private void carregarListView() {
		List<Contato> contatos = ContatoService.getInstancia(this).listarTodos();
		ContatoAdapter adapter = new ContatoAdapter(this, contatos);
		listViewContatos.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.lista_contatos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem itemEditar = menu.findItem(R.id.action_editar);
		itemEditar.setVisible(contatoSelecionado != null);		
		MenuItem itemExcluir = menu.findItem(R.id.action_excluir);
		itemExcluir.setVisible(contatoSelecionado != null);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		menuOpcoes(item);

		return super.onOptionsItemSelected(item);
	}

	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo menuInfo) {
		getMenuInflater().inflate(R.menu.lista_contatos_context, menu);
		super.onCreateContextMenu(menu, view, menuInfo);

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		menuOpcoes(item);
		return super.onContextItemSelected(item);
	}

	private void menuOpcoes(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.action_novo:
			Intent intent = new Intent(this, ContatoActivity.class);
			super.startActivity(intent);
			break;		
		case R.id.action_editar:
			Intent intent1 = new Intent(this, ContatoActivity.class);

			intent1.putExtra("CHAVE_CONTATO", contatoSelecionado);
			super.startActivity(intent1);
			break;

		case R.id.action_excluir:
			new AlertDialog.Builder(this).setTitle(R.string.dialog_confirmacao).setMessage(getString(R.string.dialog_mensagem, contatoSelecionado.getNome()))
					.setPositiveButton(R.string.dialog_sim, new OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							ContatoService.getInstancia(ListaContatosActivity.this).excluir(contatoSelecionado);
							carregarListView();

						}
					}).setNeutralButton(R.string.dialog_nao, null).create().show();

			break;
		default:
			break;

		}
	}
}
