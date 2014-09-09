package br.com.cast.treinamento.app.service;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import br.com.cast.treinamento.app.R;
import br.com.cast.treinamento.app.dao.ContatoDAO;
import br.com.cast.treinamento.app.domain.Contato;
import br.com.cast.treinamento.app.domain.exception.ExcecaoNegocio;

public final class ContatoService {

	private static  ContatoService INSTANCIA;

	private Context contexto;
	private ContatoService(Context contexto) {
		super();
		this.contexto = contexto;
		
	}

	public static ContatoService getInstancia(Context contexto) {
		if(INSTANCIA == null){
			INSTANCIA = new ContatoService(contexto);
		}
		return INSTANCIA;
	}

	public List<Contato> listarTodos() {
		return ContatoDAO.getInstancia(contexto).listarTodos();
	}

	public void salvar(Contato contato) throws ExcecaoNegocio{
		ExcecaoNegocio excecao= new ExcecaoNegocio();
		
		if (TextUtils.isEmpty(contato.getNome())) {
			excecao.getMapaErros().put(R.id.txtNome, R.string.erro_preencimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getEndereco())) {
			excecao.getMapaErros().put(R.id.txtEndereco, R.string.erro_preencimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getSite())) {
			excecao.getMapaErros().put(R.id.txtSite, R.string.erro_preencimento_obrigatorio);
		}
		if (TextUtils.isEmpty(contato.getTelefone())) {
			excecao.getMapaErros().put(R.id.txtTelefone, R.string.erro_preencimento_obrigatorio);
		}
		
		if(!excecao.getMapaErros().isEmpty()){
			throw excecao;			
		}

		ContatoDAO.getInstancia(contexto).salvar(contato);
	}

	public void excluir(Contato contatoSelecionado) {
		
		ContatoDAO.getInstancia(contexto).excluir(contatoSelecionado);		
	}
}
