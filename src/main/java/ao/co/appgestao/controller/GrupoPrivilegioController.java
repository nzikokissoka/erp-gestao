package ao.co.appgestao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.Grupo;
import ao.co.appgestao.model.GrupoPrivilegio;
import ao.co.appgestao.model.ModuloDoSistema;
import lombok.Getter;
import lombok.Setter;

@Named(value = "gpController")
@ViewScoped
public class GrupoPrivilegioController implements FuncoesGenericas2<GrupoPrivilegio>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
			
	@Autowired
	private AMensagens              mensagem;
	
	//variaveis	
	private @Setter @Getter Grupo                 grupo, grupoSelecionado;
	private @Setter @Getter GrupoPrivilegio       grupoPrivilegio, grupoPrivilegioSelecionado;
	private @Setter @Getter ModuloDoSistema       moduloDoSistema, subModuloDoSistema;
	private @Setter @Getter List<Grupo>           grupoLista;
	private @Setter @Getter List<GrupoPrivilegio> grupoPrivilegioLista;
	private @Setter @Getter List<ModuloDoSistema> moduloDoSistemaLista; 
	private @Setter @Getter List<ModuloDoSistema> subModuloDoSistemaLista; 
	private @Setter @Getter boolean               modoNovo, modoEditar, modoPrivilegios;
	
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.grupo                      = new Grupo();
  		this.grupoSelecionado           = new Grupo();
  		this.grupoPrivilegio            = new GrupoPrivilegio();
  		this.grupoPrivilegioSelecionado = new GrupoPrivilegio();
  		 		
  		this.grupoLista                 = persistencia.gRepository.func302FindAll_OrdenarPorNome();
  		this.grupoPrivilegioLista       = null;
  		
  		this.moduloDoSistemaLista       = persistencia.mdsRepository.func301FindAll_PorCodigoLen3();
  		this.subModuloDoSistemaLista    = persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao();
  		
  		this.modoNovo                   = false;
  		this.modoEditar                 = false;
  		this.modoPrivilegios            = false;
  		
  	}
	
	
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
		
		setGrupoPrivilegio(new GrupoPrivilegio());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio());
		setModuloDoSistema(new ModuloDoSistema());
		setSubModuloDoSistema(new ModuloDoSistema());
		
		setGrupoPrivilegioLista(persistencia.gpRepository.func301FindAll_PorGrupoId(grupo.getId()));
		setModuloDoSistemaLista(persistencia.mdsRepository.func301FindAll_PorCodigoLen3());
		setSubModuloDoSistemaLista(persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao());
		
	}
	
	public void cancelarGeral() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(false);
		
		setGrupo(new Grupo());
		setGrupoSelecionado(new Grupo());
		setGrupoPrivilegio(new GrupoPrivilegio());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio());
		setModuloDoSistema(new ModuloDoSistema());
		setSubModuloDoSistema(new ModuloDoSistema());
		
		setGrupoPrivilegioLista(null);
		setModuloDoSistemaLista(persistencia.mdsRepository.func301FindAll_PorCodigoLen3());
		setSubModuloDoSistemaLista(persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao());
		
	}

	@Override
	public void selecionaRegistro() {
		
		setModoNovo(false);
		setModoEditar(true);
		setGrupoPrivilegio(grupoPrivilegioSelecionado);
		
		setModuloDoSistema(persistencia.mdsRepository.func203FindOne_PorCodigo(grupoPrivilegioSelecionado.getModuloSistema().getCodigoPai()));
		setSubModuloDoSistema(persistencia.mdsRepository.func201FindOne_PorId(grupoPrivilegioSelecionado.getId()));
		
		System.out.println("-- grupoPrivilegioSelecionado = "+grupoPrivilegioSelecionado.toString());
		System.out.println("-- subModuloDoSistema = "+subModuloDoSistema.toString());
		
	}
	
	public void selecionaRegistroGrupo() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(true);
		
		setGrupo(grupoSelecionado);
		setGrupoPrivilegioLista(persistencia.gpRepository.func301FindAll_PorGrupoId(grupo.getId()));
		
	}

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModoEditar(false);
		
		this.moduloDoSistema    = new ModuloDoSistema();
  		this.subModuloDoSistema = new ModuloDoSistema();
		
		setGrupoPrivilegio(
			
			new GrupoPrivilegio(null, grupo, null, null, null, null, null, null, null, null)
				
		);
						
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
	public void func001PegaSubModulosLista(ModuloDoSistema moduloDoSistema) {
		
		setSubModuloDoSistemaLista(persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao());
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema != null) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository.func305FindAll_PorCodigoLen6OrdemPorDescricao(moduloDoSistema.getCodigo().trim()));
			
		}
		
	}
	
	
	public boolean func002PegaValorPrivilegio(Integer privilegio) {
		
		boolean resultado = false;
		
		//verifica nulidade de privilegio
		if(privilegio != null && privilegio == 1) {
			resultado = true;
		}
		
		return resultado;
		
	}
	
	
	public void func003PegaSubModulosLista(ModuloDoSistema moduloDoSistema, Grupo grupo) {
		
		setSubModuloDoSistemaLista(persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao());
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema != null && grupo != null) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository.func306FindAll_PorCodigoPaiEGrupo(moduloDoSistema.getCodigo().trim(), grupo.getId()));
			
		}
		
	}
	
	
	
	
	// FUNÇÕES EXECUTORAS ********************************
	@Override
	public void salvar() {
		
		//cria o objecto grupoPrivilegioInsert
		GrupoPrivilegio grupoPrivilegioInsert = new GrupoPrivilegio(null, 
				                                                    grupo, 
				                                                    subModuloDoSistema, 
				                                                    grupoPrivilegio.getNovo(), 
				                                                    grupoPrivilegio.getSalvar(), 
				                                                    grupoPrivilegio.getEditar(), 
				                                                    grupoPrivilegio.getDeletar(), 
				                                                    grupoPrivilegio.getPesquisar(), 
				                                                    (grupoPrivilegio.getVisibilidade().equals(null) || grupoPrivilegio.getVisibilidade().equals("") ? null : grupoPrivilegio.getVisibilidade().trim()), 
				                                                    null);
		
		//salvar na base de dados
		persistencia.gpRepository.save(grupoPrivilegioInsert);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi gravado com sucesso.");
		
		//redefine a lista de modulos
		setGrupoPrivilegioLista(persistencia.gpRepository.func301FindAll_PorGrupoId(grupo.getId()));
		
		//limpa o formulário
		cancelar();
		
	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}

}
