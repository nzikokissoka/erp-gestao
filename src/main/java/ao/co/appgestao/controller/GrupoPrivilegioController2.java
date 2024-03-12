package ao.co.appgestao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.Grupo;
import ao.co.appgestao.model.GrupoPrivilegio2;
import ao.co.appgestao.model.ModuloDoSistema2;
import lombok.Getter;
import lombok.Setter;

@Named(value = "gpController2")
@ViewScoped
public class GrupoPrivilegioController2 implements FuncoesGenericas2<GrupoPrivilegio2>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
				
	@Autowired
	private AMensagens              mensagem;
	
	@Inject
    private UsuarioController uController;
	
	
	//variaveis	
	private @Setter @Getter Grupo                  grupo, grupoSelecionado;
	private @Setter @Getter GrupoPrivilegio2       grupoPrivilegio, grupoPrivilegioSelecionado;
	private @Setter @Getter ModuloDoSistema2       moduloDoSistema, subModuloDoSistema, itemSubModuloDoSistema;
	private @Setter @Getter List<Grupo>            grupoLista;
	private @Setter @Getter List<GrupoPrivilegio2> grupoPrivilegioLista;
	private @Setter @Getter List<ModuloDoSistema2> moduloDoSistemaLista; 
	private @Setter @Getter List<ModuloDoSistema2> subModuloDoSistemaLista; 
	private @Setter @Getter List<ModuloDoSistema2> itemSubModuloDoSistemaLista; 
	private @Setter @Getter boolean                modoNovo, modoEditar, modoPrivilegios;
	
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.grupo                      = new Grupo();
  		this.grupoSelecionado           = new Grupo();
  		this.grupoPrivilegio            = new GrupoPrivilegio2();
  		this.grupoPrivilegioSelecionado = new GrupoPrivilegio2();
  		 		
  		this.grupoLista                 = persistencia.gRepository.func302FindAll_OrdenarPorNome();
  		this.grupoPrivilegioLista       = null;
  		
  		this.moduloDoSistemaLista       = persistencia.mdsRepository2.func301FindAll_PorCodigoLen3();
  		//this.subModuloDoSistemaLista    = persistencia.mdsRepository.func304FindAll_PorLen6OrdemPorDescricao();
  		
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
		
		setGrupoPrivilegio(new GrupoPrivilegio2());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio2());
		setModuloDoSistema(new ModuloDoSistema2());
		setSubModuloDoSistema(new ModuloDoSistema2());
		
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupo));
		setModuloDoSistemaLista(persistencia.mdsRepository2.func301FindAll_PorCodigoLen3());
				
	}
	
	public void cancelarGeral() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(false);
		
		setGrupo(new Grupo());
		setGrupoSelecionado(new Grupo());
		setGrupoPrivilegio(new GrupoPrivilegio2());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio2());
		setModuloDoSistema(new ModuloDoSistema2());
		setSubModuloDoSistema(new ModuloDoSistema2());
		
		setGrupoPrivilegioLista(null);
		setModuloDoSistemaLista(persistencia.mdsRepository2.func301FindAll_PorCodigoLen3());
		
		
	}

	@Override
	public void selecionaRegistro() {
		
		//pesquisa o tamanho de codigo
		String codigo     = grupoPrivilegioSelecionado.getCodigo().trim();
		String codigoLen3 = grupoPrivilegioSelecionado.getCodigo().trim().substring(0, 3);
		
		setModoNovo(false);
		setModoEditar(true);
		setGrupoPrivilegio(grupoPrivilegioSelecionado);
		
		setModuloDoSistema(persistencia.mdsRepository2.func203FindOne_PorCodigo(grupoPrivilegioSelecionado.getModuloSistema().getCodigo().trim()));
		setSubModuloDoSistema(persistencia.mdsRepository2.func204FindOne_PorCodigoStr(grupoPrivilegioSelecionado.getCodigo().trim()));
				
		setModuloDoSistemaLista(persistencia.mdsRepository2.func309FindAll_PorCodigoLen3(codigoLen3));
		
				
		//verifica codigo
		if(codigo.length() == 6) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository2.func310FindAll_PorCodigoLen6(codigo));
			
		} else if(codigo.length() == 9) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository2.func311FindAll_PorCodigoLen9(codigo));
			
		}
		
		
	}
	
	public void selecionaRegistroGrupo() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(true);
		
		setGrupo(grupoSelecionado);
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoSelecionado));
		
		System.out.println("---- entra na função selecionaRegistroGrupo() ");
		System.out.println("---- grupo = "+grupo.toString());
		System.out.println("---- GrupoPrivilegios =  "+grupoPrivilegioLista.size());
		
		System.out.println("####### obterUsernameDaSessao() = "+func006NomeUsuario());
		System.out.println("####### partesAdm = "+func005visualizaParteAdm());
				
	}

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModoEditar(false);
		
		this.moduloDoSistema    = new ModuloDoSistema2();
  		this.subModuloDoSistema = new ModuloDoSistema2();
		
		setGrupoPrivilegio(
				
			new GrupoPrivilegio2(null, grupo, null, null, null, null, null, null, null, null, null, null)
				
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
	public void func001PegaSubModulosLista(ModuloDoSistema2 moduloDoSistema) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository2.func304FindAll_PorLen6OrdemPorDescricao());
			
			//verifica nulidade de moduloDoSistema
			if(moduloDoSistema != null) {
				
				setSubModuloDoSistemaLista(persistencia.mdsRepository2.func305FindAll_PorCodigoLen6OrdemPorDescricao(moduloDoSistema.getCodigo().trim()));
				
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
		
		
	public void func003PegaSubModulosLista(ModuloDoSistema2 moduloDoSistema, Grupo grupo) {
			
		//verifica o estado do modos
		if(modoNovo == true && modoEditar == false) {
			
			//setSubModuloDoSistemaLista(func007TodosSubmodulos());
			
			//verifica nulidade de moduloDoSistema
			if(grupo != null && moduloDoSistema != null) {
					
				String codigo_pai = moduloDoSistema.getCodigo().trim();
				Integer grupo_id  = grupo.getId();
				setSubModuloDoSistemaLista(persistencia.mdsRepository2.func307FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id));
				System.out.println();
					
			} else if(grupo != null && moduloDoSistema == null){
				
				setModuloDoSistema(new ModuloDoSistema2());
				setSubModuloDoSistema(new ModuloDoSistema2());
				setSubModuloDoSistemaLista(null);
				
			}
			
		}
		
		
		if(modoNovo == false && modoEditar == true) {
			
			//pega o codigo de grupoPrivilegio
			String codigo = grupoPrivilegio.getCodigo().trim();
			
			//verifica o tamanho do codigo
			if(!codigo.equals(null)) {
				
				if(codigo.length() == 3) {
					
					setModuloDoSistemaLista(persistencia.mdsRepository2.func309FindAll_PorCodigoLen3(codigo));
					setSubModuloDoSistemaLista(null);
					
				} else if(codigo.length() == 6) {
					
					String codigo_pai = persistencia.mdsRepository2.func204FindOne_PorCodigoStr(codigo).getCodigoPai();
					
					//verifica o tamanho do codigo_pai
					if(!codigo_pai.equals(null)) {
						
						setModuloDoSistemaLista(persistencia.mdsRepository2.func309FindAll_PorCodigoLen3(codigo_pai));
						setSubModuloDoSistemaLista(persistencia.mdsRepository2.func310FindAll_PorCodigoLen6(codigo));
						
					}					
					
					
				} else if(codigo.length() == 9) {
					
					String codigo_pai = persistencia.
							            mdsRepository2.
							            func204FindOne_PorCodigoStr(
							               persistencia.mdsRepository2.func204FindOne_PorCodigoStr(codigo).getCodigoPai()// sai len = 6							
							            ).getCodigoPai(); // sai len = 3
					
					//verifica o tamanho do codigo_pai
					if(!codigo_pai.equals(null)) {
						
						setModuloDoSistemaLista(persistencia.mdsRepository2.func309FindAll_PorCodigoLen3(codigo_pai));
						setSubModuloDoSistemaLista(persistencia.mdsRepository2.func311FindAll_PorCodigoLen9(codigo));
						
					}							
					
				}
				
			}
			
		}
		
		
		
		
		
			
	}
	
	
	public List<GrupoPrivilegio2> func004PegaGrupoPrivilegiosLista(Grupo grupo) {
		return persistencia.gpRepository2.func301FindAll_PorGrupoId(grupo.getId());
	}
	
	
	public boolean func005visualizaParteAdm() {		
		
		boolean resultado = false;
		
		if(func006NomeUsuario().equals("admin")) {
			resultado = true;
		}
		
		return resultado;		
	}
	
	
	public String func006NomeUsuario() {
        return uController.getUsername();
    }
	
	
	public List<ModuloDoSistema2> func007TodosSubmodulos(){
		return persistencia.mdsRepository2.func308FindAll_SubModulos();
	}
	
	
		
		
		
		
	// FUNÇÕES EXECUTORAS ********************************
	@Override
	public void salvar() {
		
		//pega codigoSubModulo
		String codigoSubModulo = subModuloDoSistema.getCodigo().trim();
		
		//verifica o tamanho de codigoSubModulo
		if(codigoSubModulo.length() == 3) {
			
			// nada
			
		} else if(codigoSubModulo.length() == 6) {
			
			String codigoLen3 = moduloDoSistema.getCodigo().trim();
			String codigoLen6 = codigoSubModulo;
			
			//criar objecto grupoPrivilegioLen3
			GrupoPrivilegio2 grupoPrivilegioLen3 = new GrupoPrivilegio2(null, grupo, moduloDoSistema, codigoLen3, null, null, null, null, null, null, null, null);
			
			
			//criar objecto grupoPrivilegioLen6
			GrupoPrivilegio2 grupoPrivilegioLen6 = new GrupoPrivilegio2(null, 
					                                                    grupo, 
					                                                    subModuloDoSistema, 
					                                                    codigoLen6, 
					                                                    codigoLen3, 
					                                                    grupoPrivilegio.getNovo(), 
					                                                    grupoPrivilegio.getSalvar(), 
					                                                    grupoPrivilegio.getEditar(), 
					                                                    grupoPrivilegio.getDeletar(), 
					                                                    grupoPrivilegio.getPesquisar(), 
					                                                    (grupoPrivilegio.getVisibilidade().equals(null) || grupoPrivilegio.getVisibilidade().equals("") ? null : grupoPrivilegio.getVisibilidade().trim()), 
					                                                    null);
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3 = persistencia.gpRepository2.func103FindAll_PorGrupoIdECodigo(grupo.getId(), codigoLen3);
			int repeticoesCodigoLen6 = persistencia.gpRepository2.func103FindAll_PorGrupoIdECodigo(grupo.getId(), codigoLen6);
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) {
				persistencia.gpRepository2.save(grupoPrivilegioLen3);
			}
			
			
			//verifica repeticoesCodigoLen6
			if(repeticoesCodigoLen6 == 0) {
				
				//gravar na base de dados
				persistencia.gpRepository2.save(grupoPrivilegioLen6);
				
				//envia mensagem de confirmação
				mensagem.addInfo("O privilégio foi gravado com sucesso.");
				
			} else if(repeticoesCodigoLen6 != 0) {
				
				//envia mensagem de aviso
				mensagem.addWarn("INSERÇÃO IMPOSSIBILITADA. Esse privilégio ja existe no sistema.");
				
				
			} 
			
			//redefine a lista de modulos
			setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupo));
			
			//limpa o formulário
			cancelar();
			
			
			
			
		} else if(codigoSubModulo.length() == 9) {
									
			ModuloDoSistema2 moduloDoSistemaLen6 = persistencia.mdsRepository2.func204FindOne_PorCodigoStr(codigoSubModulo.substring(0, 6));
			
			String codigoLen3 = moduloDoSistema.getCodigo().trim();
			String codigoLen6 = moduloDoSistemaLen6.getCodigo().trim();
			String codigoLen9 = codigoSubModulo.trim();
			
			//criar objecto grupoPrivilegioLen3
			GrupoPrivilegio2 grupoPrivilegioLen3 = new GrupoPrivilegio2(null, grupo, moduloDoSistema, codigoLen3, null, null, null, null, null, null, null, null);
			
			//criar objecto grupoPrivilegioLen6
			GrupoPrivilegio2 grupoPrivilegioLen6 = new GrupoPrivilegio2(null, grupo, moduloDoSistemaLen6, 
					                                                    codigoLen6, codigoLen3, null, null, null, null, null, null, null);
			
			//criar objecto grupoPrivilegioLen9
			GrupoPrivilegio2 grupoPrivilegioLen9 = new GrupoPrivilegio2(null, grupo, subModuloDoSistema, codigoLen9, 
					                                                    codigoLen6, 
					                                                    grupoPrivilegio.getNovo(), 
					                                                    grupoPrivilegio.getSalvar(), 
					                                                    grupoPrivilegio.getEditar(), 
					                                                    grupoPrivilegio.getDeletar(), 
					                                                    grupoPrivilegio.getPesquisar(), 
					                                                    (grupoPrivilegio.getVisibilidade().equals(null) || grupoPrivilegio.getVisibilidade().equals("") ? null : grupoPrivilegio.getVisibilidade().trim()), 
					                                                    null);
			
			
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3 = persistencia.gpRepository2.func103FindAll_PorGrupoIdECodigo(grupo.getId(), codigoLen3);
			int repeticoesCodigoLen6 = persistencia.gpRepository2.func103FindAll_PorGrupoIdECodigo(grupo.getId(), codigoLen6);
			int repeticoesCodigoLen9 = persistencia.gpRepository2.func103FindAll_PorGrupoIdECodigo(grupo.getId(), codigoLen9);
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) {
				persistencia.gpRepository2.save(grupoPrivilegioLen3);
			}
			
			//verifica repeticoesCodigoLen6
			if(repeticoesCodigoLen6 == 0) {
				persistencia.gpRepository2.save(grupoPrivilegioLen6);
			}
			
			//verifica repeticoesCodigoLen9
			if(repeticoesCodigoLen9 == 0) {
				
				// gravar na base de dados
				persistencia.gpRepository2.save(grupoPrivilegioLen9);
				
				//envia mensagem de confirmação
				mensagem.addInfo("O privilégio foi gravado com sucesso.");
				
			} else if(repeticoesCodigoLen9 != 0) {
				
				//envia mensagem de aviso
				mensagem.addWarn("INSERÇÃO IMPOSSIBILITADA. Esse privilégio ja existe no sistema.");
				
				
			} 
			
			//redefine a lista de modulos
			setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupo));
			
			//limpa o formulário
			cancelar();
			
		}
		
	}

	@Override
	public void editar() {
		
		//criar objecto grupoPrivilegioLen6
		GrupoPrivilegio2 grupoPrivilegioUpdate = new GrupoPrivilegio2(grupoPrivilegio.getId(), 
				                                                      grupoPrivilegio.getGrupo(), 
				                                                      grupoPrivilegio.getModuloSistema(), 
				                                                      grupoPrivilegio.getCodigo().trim(), 
				                                                      grupoPrivilegio.getCodigoPai(), 
				                                                      grupoPrivilegio.getNovo(), 
				                                                      grupoPrivilegio.getSalvar(), 
				                                                      grupoPrivilegio.getEditar(), 
				                                                      grupoPrivilegio.getDeletar(), 
				                                                      grupoPrivilegio.getPesquisar(), 
				                                                     (grupoPrivilegio.getVisibilidade().equals(null) || grupoPrivilegio.getVisibilidade().equals("") ? null : grupoPrivilegio.getVisibilidade().trim()), 
				                                                      null);
		
		//alterar na base de dados
		persistencia.gpRepository2.save(grupoPrivilegioUpdate);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoPrivilegio.getGrupo()));
		
		//limpa o formulário
		cancelar();
		
		
	}

	@Override
	public void eliminar() {
		
		//pega o código
		String codigo     = grupoPrivilegio.getCodigo().trim();
		Integer grupoID   = grupoPrivilegio.getGrupo().getId();
		int tamanhoCodigo = codigo.length();
		
		//verifica o tamanhoCodigo
		if(tamanhoCodigo == 3) {
			
			//pega grupoPrivilegioLen3
			GrupoPrivilegio2 grupoPrivilegioLen3 = persistencia.gpRepository2.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
			
			//elimina grupoPrivilegioLen3 da base de dados
			persistencia.gpRepository2.delete(grupoPrivilegioLen3);
			
		} else if(tamanhoCodigo == 6) {
			
			String codigoLen3 = grupoPrivilegio.getCodigo().trim().substring(0, 3);
			
			//elimina grupoPrivilegioLen6 da base de dados
			persistencia.gpRepository2.delete(grupoPrivilegio);
			
			//pega a existencia de outras inserções Len6
			int outrasIncercoesLen6PorCodigoPaiLen3 = persistencia.gpRepository2.func111Count_OutrasInsercoesLen6PorCodigoPaiLen3(grupoID, codigoLen3);
			
			//verifica outrasIncercoesLen6PorCodigoPaiLen3
			if(outrasIncercoesLen6PorCodigoPaiLen3 == 0) {
				
				//pega a existencia de outras inserções Len3
				int outrasIncercoesLen3PorCodigoPaiNull = persistencia.gpRepository2.func110Count_OutrasInsercoesLen3PorCodigoPai(grupoID);
				
				//verifica outrasIncercoesLen3PorCodigoPaiNull
				if(outrasIncercoesLen3PorCodigoPaiNull == 0) {
					
					//pega grupoPrivilegioLen3
					GrupoPrivilegio2 grupoPrivilegioLen3 = persistencia.gpRepository2.func201FindAll_PorGrupoIdECodigo(grupoID, codigoLen3);
					
					//elimina grupoPrivilegioLen3 da base de dados
					persistencia.gpRepository2.delete(grupoPrivilegioLen3);
					
				}
				
			}
			
			
			
		} else if(tamanhoCodigo == 9) {
			
			String codigoLen3  = codigo.substring(0, 3);
			String codigoLen6  = codigo.substring(0, 6);
			String codigoLen9  = codigo;
			
			//elimina grupoPrivilegioLen9 da base de dados
			persistencia.gpRepository2.delete(grupoPrivilegio);
			
			//pega numero de outras inserções Len9
			int outrasIncercoesLen9PorCodigoPaiLen6 = persistencia.gpRepository2.func112Count_OutrasInsercoesLen9PorCodigoPaiLen6(grupoID, codigoLen9);
			
			// verifica outrasIncercoesLen9PorCodigoPaiLen6
			if(outrasIncercoesLen9PorCodigoPaiLen6 == 0) {
				
				//pega outrasInserções Len6 por Len3
				int outrasIncercoesLen6PorCodigoPaiLen3 = persistencia.gpRepository2.func111Count_OutrasInsercoesLen6PorCodigoPaiLen3(grupoID, codigoLen3);
				
				// verifica outrasIncercoesLen6PorCodigoPaiLen3
				if(outrasIncercoesLen6PorCodigoPaiLen3 == 0) {
					
					//pega grupoPrivilegioLen6
					GrupoPrivilegio2 grupoPrivilegioLen6 = persistencia.gpRepository2.func201FindAll_PorGrupoIdECodigo(grupoID, codigoLen6);
					
					//elimina grupoPrivilegioLen3 da base de dados
					persistencia.gpRepository2.delete(grupoPrivilegioLen6);
					
					//pega outrasInserções Len3
					int outrasIncercoesLen3PorCodigoPaiNull = persistencia.gpRepository2.func110Count_OutrasInsercoesLen3PorCodigoPai(grupoID);
					
					//verifica outrasIncercoesLen3PorCodigoPaiNull
					if(outrasIncercoesLen3PorCodigoPaiNull == 0) {
						
						//pega grupoPrivilegioLen3
						GrupoPrivilegio2 grupoPrivilegioLen3 = persistencia.gpRepository2.func201FindAll_PorGrupoIdECodigo(grupoID, codigoLen3);
						
						//elimina grupoPrivilegioLen3 da base de dados
						persistencia.gpRepository2.delete(grupoPrivilegioLen3);
						
					}
					
				}
				
			}
			
			
		}
		
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi eliminado com sucesso.");
				
		//redefine a lista de modulos
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoPrivilegio.getGrupo()));
				
		//limpa o formulário
		cancelar();
		
		
		
		
	}

}
