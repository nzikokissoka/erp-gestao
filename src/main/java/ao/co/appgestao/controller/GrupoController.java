package ao.co.appgestao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.Grupo;
import lombok.Getter;
import lombok.Setter;

@Named(value = "gController")
@ViewScoped
public class GrupoController implements FuncoesGenericas2<Grupo>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
		
	@Autowired
	private AMensagens              mensagem;
	
	
	
	//variaveis	
	private @Setter @Getter Grupo grupo, grupoSelecionado;
	private @Setter @Getter List<Grupo> grupoLista;
	private @Setter @Getter boolean modoNovo, modoEditar;
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.grupo            = new Grupo();
  		this.grupoSelecionado = new Grupo();
  		
  		this.grupoLista       = persistencia.gRepository.func301FindAll_OrdenarPorIdDesc();
  		
  		this.modoNovo         = false;
  		this.modoEditar       = false;
  		
  	}
	
	
  	// FUNÇÕES AUXILIARES **********************************
	@Override
	public void novaEntidade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpaTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelar() {
		
		setModoNovo(false);
		setModoEditar(false);
		
		setGrupo(new Grupo());
		setGrupoSelecionado(new Grupo());
		
		setGrupoLista(persistencia.gRepository.func301FindAll_OrdenarPorIdDesc());
		
	}

	@Override
	public void selecionaRegistro() {
		
		setGrupo(grupoSelecionado);
		setModoNovo(false);
		setModoEditar(true);
		
	}
	
	
	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModoEditar(false);
		
	}

	@Override
	public void ativaModoEditar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ativaModoNovo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desAtivaModoEditar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desAtivaModoNovo() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	// FUNÇÕES AUXILIARES 2 ********************************
	
	
	
	
	
	// FUNÇÕES EXECUTORAS **********************************
	@Override
	public void salvar() {
		
		//pega numero de repetições do grupo à salvar
		int repeticoesGrupo = persistencia.gRepository.func101Count_RegistrosRepetidosPorNome(grupo.getNome().trim());
		
		//verifica repeticoesGrupo
		if(repeticoesGrupo != 0) {
			
			mensagem.addError("O Grupo "+grupo.getNome().trim().toUpperCase()+", ja existe no sistema.");
			
		} else {
			
			//criar objecto grupoInsert
			Grupo grupoInsert = new Grupo(null, grupo.getNome().trim().toUpperCase());
			
			//salvar na base de dados
			persistencia.gRepository.save(grupoInsert);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O Grupo "+grupo.getNome().trim().toUpperCase()+", foi gravado com sucesso.");
			
			//redefine a lista de modulos
			setGrupoLista(persistencia.gRepository.func301FindAll_OrdenarPorIdDesc());
			
			//limpa o formulário
			cancelar();
			
		}
		
	}

	@Override
	public void editar() {
		
		//verifica nulidade de grupo
		if(grupo.getNome().equals(null) || grupo.getNome().equals("")) {
			
			mensagem.addInfo("O Nome grupo não deve ser nulo.");
			
		} else {
			
			//criar objecto grupoInsert
			Grupo grupoUpdate = new Grupo(grupo.getId(), grupo.getNome().trim().toUpperCase());
			
			//salvar na base de dados
			persistencia.gRepository.save(grupoUpdate);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O Grupo "+grupo.getNome().trim().toUpperCase()+", foi alterado com sucesso.");
			
			//redefine a lista de modulos
			setGrupoLista(persistencia.gRepository.func301FindAll_OrdenarPorIdDesc());
			
			//limpa o formulário
			cancelar();
			
		}		
		
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void eliminarLinha(Grupo grupo) {
		
		//verifica nulidade de grupo
		if(grupo != null) {
			
			//conta registos repetidos ou em uso na tabela Grupo privilagios
			int gruposRepetidos = persistencia.gpRepository.func101Count_RegistrosRepetidosPorGrupoId(grupo.getId());
			
			//verifica gruposRepetidos
			if(gruposRepetidos != 0) {
				
				//envia mensagem de confirmação
				mensagem.addWarn("Eliminação impossibilitada. O Grupo "+grupo.getNome().trim().toUpperCase()+", está ser usado na tabela: Grupo privilégios.");
				
			} else {
				
				//criar objecto grupoDelete
				Grupo grupoDelete = new Grupo(grupo.getId(), grupo.getNome());
				
				//salvar na base de dados
				persistencia.gRepository.delete(grupoDelete);
				
				//envia mensagem de confirmação
				mensagem.addInfo("O Grupo "+grupo.getNome().trim().toUpperCase()+", foi eliminado com sucesso.");
				
				//redefine a lista de modulos
				setGrupoLista(persistencia.gRepository.func301FindAll_OrdenarPorIdDesc());
				
				//limpa o formulário
				cancelar();
				
			}
			
		}		
		
		
	}
	

}
