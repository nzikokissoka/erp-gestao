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
import ao.co.appgestao.model.GrupoPrivilegio4;
import ao.co.appgestao.model.Menu4;
import ao.co.appgestao.model.ModuloDoSistema4;

import lombok.Getter;
import lombok.Setter;

@Named(value = "gpController4")
@ViewScoped
public class GrupoPrivilegioController4 implements FuncoesGenericas2<GrupoPrivilegio4>{

	// injecção de dependencias
	@Autowired private APersistenciaController persistencia;
	@Autowired private AMensagens              mensagem;
	@Inject    private UsuarioController       uController;
	
	//variaveis	
	private @Setter @Getter Grupo                  grupo, grupoSelecionado;
	private @Setter @Getter GrupoPrivilegio4       grupoPrivilegio, grupoPrivilegioSelecionado;
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema3, moduloDoSistema6, 
	                                               moduloDoSistema9, moduloDoSistema12, 
	                                               moduloDoSistema15;
		
	private @Setter @Getter List<Grupo>            grupoLista;
	private @Setter @Getter List<GrupoPrivilegio4> grupoPrivilegioLista;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistema3Lista, moduloDoSistema6Lista, 
	                                               moduloDoSistema9Lista, moduloDoSistema12Lista,
	                                               moduloDoSistema15Lista; 
			
	private @Setter @Getter boolean                modoNovo, modoEditar, modoPrivilegios;
	
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.grupo                      = new Grupo();
  		this.grupoSelecionado           = new Grupo();
  		this.grupoPrivilegio            = new GrupoPrivilegio4();
  		this.grupoPrivilegioSelecionado = new GrupoPrivilegio4();
  		
  		this.moduloDoSistema3           = new ModuloDoSistema4();
  		 		
  		this.grupoLista                 = persistencia.gRepository.func302FindAll_OrdenarPorNome();
  		this.grupoPrivilegioLista       = null;
  		
  		this.moduloDoSistema3Lista      = persistencia.mdsRepository4.func301FindAll_PorCodigoLen3();
  		  		
  		this.modoNovo                   = false;
  		this.modoEditar                 = false;
  		this.modoPrivilegios            = false;
  		
  	}
		
		
	
	
	
  	//funcções auxiliares ************************************************
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
		setModoPrivilegios(true);
		
		setGrupo(grupo);
		setGrupoPrivilegio(new GrupoPrivilegio4());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio4());
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupo));
		
		setModuloDoSistema3(new ModuloDoSistema4());
		setModuloDoSistema6(new ModuloDoSistema4());
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistema3Lista(persistencia.mdsRepository4.func301FindAll_PorCodigoLen3());
		setModuloDoSistema6Lista(null);
		setModuloDoSistema9Lista(null);
		setModuloDoSistema12Lista(null);
		setModuloDoSistema15Lista(null);
		
	}
	
	
	public void cancelarGeral() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(false);
		
		setGrupo(new Grupo());
		
		setGrupoSelecionado(new Grupo());
		setGrupoPrivilegio(new GrupoPrivilegio4());
		setGrupoPrivilegioSelecionado(new GrupoPrivilegio4());
		
		setModuloDoSistema3(new ModuloDoSistema4());
		setModuloDoSistema6(new ModuloDoSistema4());
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setGrupoPrivilegioLista(null);
		
		setModuloDoSistema3Lista(persistencia.mdsRepository4.func301FindAll_PorCodigoLen3());
		setModuloDoSistema6Lista(null);
		setModuloDoSistema9Lista(null);
		setModuloDoSistema12Lista(null);
		setModuloDoSistema15Lista(null);		
		
		
	}
	
	
	public void cancelarPrivilegios(Grupo grupo) {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(true);
		
		setModuloDoSistema3(new ModuloDoSistema4());
		setModuloDoSistema6(new ModuloDoSistema4());
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistema3Lista(persistencia.mdsRepository4.func301FindAll_PorCodigoLen3());
		setModuloDoSistema6Lista(null);
		setModuloDoSistema9Lista(null);
		setModuloDoSistema12Lista(null);
		setModuloDoSistema15Lista(null);
		
		setGrupo(grupo);
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupo));		
				
	}
	
	

	@Override
	public void selecionaRegistro() {
		
		setModoNovo(false);
		setModoEditar(true);
		setGrupoPrivilegio(grupoPrivilegioSelecionado);
		
		//pega o grupoPrivilegioSelecionado
		int lenCodigo = grupoPrivilegio.getCodigo().trim().length();
		
		//verifica lenCodigo
		if(lenCodigo == 15) {
			
		} else if(lenCodigo == 12) {
			
			//pegar os códigos anteriores
			String codigo3  = grupoPrivilegio.getCodigoPai().trim().substring(0, 3);
			String codigo6  = grupoPrivilegio.getCodigoPai().trim().substring(0, 6);
			String codigo9  = grupoPrivilegio.getCodigoPai().trim();
			String codigo12 = grupoPrivilegio.getCodigo().trim();
			
			//pesquisa os modulosDoSistema func204FindOne_PorCodigoStr
			ModuloDoSistema4 moduloDoSistema3  = persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo3);
			ModuloDoSistema4 moduloDoSistema6  = persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo6);
			ModuloDoSistema4 moduloDoSistema9  = persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo9);
			ModuloDoSistema4 moduloDoSistema12 = persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo12);
			
			List<ModuloDoSistema4> moduloDoSistema3Lista  = persistencia.mdsRepository4.func321FindAll_PorId(moduloDoSistema3.getId());
			List<ModuloDoSistema4> moduloDoSistema6Lista  = persistencia.mdsRepository4.func321FindAll_PorId(moduloDoSistema6.getId());
			List<ModuloDoSistema4> moduloDoSistema9Lista  = persistencia.mdsRepository4.func321FindAll_PorId(moduloDoSistema9.getId());
			List<ModuloDoSistema4> moduloDoSistema12Lista = persistencia.mdsRepository4.func321FindAll_PorId(moduloDoSistema12.getId());
			
			setModuloDoSistema3Lista(moduloDoSistema3Lista);
			setModuloDoSistema6Lista(moduloDoSistema6Lista);
			setModuloDoSistema9Lista(moduloDoSistema9Lista);
			setModuloDoSistema12Lista(moduloDoSistema12Lista);
			
			
		} else if(lenCodigo == 9) {
			
		} else if(lenCodigo == 6) {
			
		} else if(lenCodigo == 3) {
			
		}
		
		
	}
	
	
	public void selecionaRegistroGrupo() {
		
		setModoNovo(false);
		setModoEditar(false);
		setModoPrivilegios(true);
		
		setGrupo(grupoSelecionado);
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoSelecionado));		
				
	}
	

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModoEditar(false);
				
		setGrupoPrivilegio(
				
			new GrupoPrivilegio4(null, grupo, null, null, null, null, null, null, null, null, null, null)
				
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

	
	
	
	//funcções auxialiares 2 *******************************************
	/*public void func001PegaSubModulosLista(ModuloDoSistema4 moduloDoSistema) {
		
		setSubModuloDoSistemaLista(persistencia.mdsRepository4.func304FindAll_PorLen6OrdemPorDescricao());
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema != null) {
			
			setSubModuloDoSistemaLista(persistencia.mdsRepository4.func305FindAll_PorCodigoLen6OrdemPorDescricao(moduloDoSistema.getCodigo().trim()));
			
		}
		
	}*/
	
	
	public void func001PegaModuloDoSistema6Lista(ModuloDoSistema4 moduloDoSistema3) {
		
				
		
	}
	
		
		
	public boolean func002PegaValorPrivilegio(Integer privilegio) {
			
			boolean resultado = false;
			
			//verifica nulidade de privilegio
			if(privilegio != null && privilegio == 1) {
				resultado = true;
			}
			
			return resultado;
			
	}
		
		
	public void func003PegaSubModulosLista(ModuloDoSistema4 moduloDoSistema, Grupo grupo) {
		/*	
		//verifica o estado do modos
		if(modoNovo == true && modoEditar == false) {
			
			//setSubModuloDoSistemaLista(func007TodosSubmodulos());
			
			//verifica nulidade de moduloDoSistema
			if(grupo != null && moduloDoSistema != null) {
					
				String codigo_pai = moduloDoSistema.getCodigo().trim();
				Integer grupo_id  = grupo.getId();
				setSubModuloDoSistemaLista(persistencia.mdsRepository4.func307FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id));
				System.out.println();
					
			} else if(grupo != null && moduloDoSistema == null){
				
				setModuloDoSistema(new ModuloDoSistema4());
				setSubModuloDoSistema(new ModuloDoSistema4());
				setSubModuloDoSistemaLista(null);
				
			}
			
		}
		
		
		if(modoNovo == false && modoEditar == true) {
			
			//pega o codigo de grupoPrivilegio
			String codigo = grupoPrivilegio.getCodigo().trim();
			
			//verifica o tamanho do codigo
			if(!codigo.equals(null)) {
				
				if(codigo.length() == 3) {
					
					setModuloDoSistemaLista(persistencia.mdsRepository4.func309FindAll_PorCodigoLen3(codigo));
					setSubModuloDoSistemaLista(null);
					
				} else if(codigo.length() == 6) {
					
					String codigo_pai = persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo).getCodigoPai();
					
					//verifica o tamanho do codigo_pai
					if(!codigo_pai.equals(null)) {
						
						setModuloDoSistemaLista(persistencia.mdsRepository4.func309FindAll_PorCodigoLen3(codigo_pai));
						setSubModuloDoSistemaLista(persistencia.mdsRepository4.func310FindAll_PorCodigoLen6(codigo));
						
					}					
					
					
				} else if(codigo.length() == 9) {
					
					String codigo_pai = persistencia.
							            mdsRepository4.
							            func204FindOne_PorCodigoStr(
							               persistencia.mdsRepository4.func204FindOne_PorCodigoStr(codigo).getCodigoPai()// sai len = 6							
							            ).getCodigoPai(); // sai len = 3
					
					//verifica o tamanho do codigo_pai
					if(!codigo_pai.equals(null)) {
						
						setModuloDoSistemaLista(persistencia.mdsRepository4.func309FindAll_PorCodigoLen3(codigo_pai));
						setSubModuloDoSistemaLista(persistencia.mdsRepository4.func311FindAll_PorCodigoLen9(codigo));
						
					}							
					
				}
				
			}
			
		}*/
		
			
	}
	
	
	public void func003PegaModuloDoSistema6Lista(ModuloDoSistema4 moduloDoSistema3, Grupo grupo) {
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema3 != null && moduloDoSistema3.getId() != null) {
			
			String codigo_pai = moduloDoSistema3.getCodigo().trim();
			Integer grupo_id  = grupo.getId();
			Integer len       = 6;
			//setModuloDoSistema6Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id, len));
			setModuloDoSistema6Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo2(codigo_pai, grupo_id, len));
						
			
		} else if(moduloDoSistema3 == null) {
			
			setModuloDoSistema6(new ModuloDoSistema4());
			setModuloDoSistema9(new ModuloDoSistema4());
			setModuloDoSistema12(new ModuloDoSistema4());
			setModuloDoSistema15(new ModuloDoSistema4());
			
			setModuloDoSistema6Lista(null);
			setModuloDoSistema9Lista(null);
			setModuloDoSistema12Lista(null);
			setModuloDoSistema15Lista(null);
			
			setGrupoPrivilegio(new GrupoPrivilegio4());
			
		}
		
	}
	
	public void func003PegaModuloDoSistema9Lista(ModuloDoSistema4 moduloDoSistema6, Grupo grupo) {
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema6 != null && moduloDoSistema6.getId() != null) {
					
			String codigo_pai = moduloDoSistema6.getCodigo().trim();
			Integer grupo_id  = grupo.getId();
			Integer len       = 9;
			//setModuloDoSistema9Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id, len));
			setModuloDoSistema9Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo2(codigo_pai, grupo_id, len));
			
		} else if(moduloDoSistema6 == null) {
					
			setModuloDoSistema9(new ModuloDoSistema4());
			setModuloDoSistema12(new ModuloDoSistema4());
			setModuloDoSistema15(new ModuloDoSistema4());
					
			setModuloDoSistema9Lista(null);
			setModuloDoSistema12Lista(null);
			setModuloDoSistema15Lista(null);
			
			setGrupoPrivilegio(new GrupoPrivilegio4());
					
		}
		
	}
	
	public void func003PegaModuloDoSistema12Lista(ModuloDoSistema4 moduloDoSistema9, Grupo grupo) {
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema9 != null && moduloDoSistema9.getId() != null) {
							
			String codigo_pai = moduloDoSistema9.getCodigo().trim();
			Integer grupo_id  = grupo.getId();
			Integer len       = 12;
			//setModuloDoSistema12Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id, len));
			setModuloDoSistema12Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo2(codigo_pai, grupo_id, len));
							
		} else if(moduloDoSistema9 == null) {
							
			setModuloDoSistema12(new ModuloDoSistema4());
			setModuloDoSistema15(new ModuloDoSistema4());
							
			setModuloDoSistema12Lista(null);
			setModuloDoSistema15Lista(null);
			
			setGrupoPrivilegio(new GrupoPrivilegio4());
							
		}
		
	}
	
	public void func003PegaModuloDoSistema15Lista(ModuloDoSistema4 moduloDoSistema12, Grupo grupo) {
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema12 != null && moduloDoSistema12.getId() != null) {
									
			String codigo_pai = moduloDoSistema12.getCodigo().trim();
			Integer grupo_id  = grupo.getId();
			Integer len       = 15;
			//setModuloDoSistema15Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo(codigo_pai, grupo_id, len));
			setModuloDoSistema15Lista(persistencia.mdsRepository4.func320FindAll_PorCodigoPaiEGrupo2(codigo_pai, grupo_id, len));
									
		} else if(moduloDoSistema12 == null) {
									
			setModuloDoSistema15(new ModuloDoSistema4());
									
			setModuloDoSistema15Lista(null);
			
			setGrupoPrivilegio(new GrupoPrivilegio4());
									
		}
		
	}
	
	
	
	
	
	
	public List<GrupoPrivilegio4> func004PegaGrupoPrivilegiosLista(Grupo grupo) {
		return persistencia.gpRepository4.func301FindAll_PorGrupoId(grupo.getId());
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
	
	
	public List<ModuloDoSistema4> func007TodosSubmodulos(){
		return persistencia.mdsRepository4.func308FindAll_SubModulos();
	}
	
	
	public boolean func008VerXhtml(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
		
		if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
			
			if(moduloDoSistema.getCaminhoFicheiroXhtml() != null) {
				resultado = true;
			}
			
		}
		
		return resultado;
		
	}
	
	
	
	public boolean func008VerXhtmlNaLista(List<ModuloDoSistema4> moduloDoSistemaLista) {
		
		boolean resultado = false;
		
		if(moduloDoSistemaLista != null && moduloDoSistemaLista.size() != 0) {
			
			if(moduloDoSistemaLista.get(0).getCaminhoFicheiroXhtml() != null) {
				resultado = true;
			}
			
		}
		
		return resultado;
		
	}
	
	
	
	public boolean func009VerObjectoNaoNulo(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
		
		if(moduloDoSistema != null && moduloDoSistema.getId() != null) {			
			resultado = true;			
		}
		
		return resultado;
		
	}
	
	
	public boolean func010VerPrivilegioNaoNulo(Integer privilegio) {
		
		boolean resultado = false;
		
		if(privilegio != null ) {			
			resultado = true;			
		}
		
		return resultado;
		
	}
	
	
	public boolean func011VerListaNaoNulo(List<ModuloDoSistema4> moduloDoSistemaLista) {
		
		boolean resultado = false;
		
		if(moduloDoSistemaLista != null && moduloDoSistemaLista.size() != 0) {			
			resultado = true;			
		}
		
		return resultado;
		
	}
	
	
	
	public void func012EliminarModulosNoMenu(GrupoPrivilegio4 grupoPrivilegio) {
		
		String codigo    = grupoPrivilegio.getCodigo().trim();
		String codigoPai = grupoPrivilegio.getCodigoPai();
		Integer grupoID  = grupoPrivilegio.getGrupo().getId();
				
		//verifica codigoPaiLen
		if(codigoPai != null && codigoPai != "" && codigoPai.length() != 0) {
			
			int codigoLen    = codigo.length();
			int codigoPaiLen = codigoPai.length();
			
			
			
			if(codigoLen == 15 && codigoPaiLen == 12) {
				
				//pega MenuLen15 para eliminar e elimina MenuLen15
				Menu4 MenuLen15 = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(codigoPai, grupoID);
				persistencia.mRepository4.delete(MenuLen15);
				
				//pequisa a existencia de outras gravações de codigoPaiLen12
				int outrasGravacoesCodigoPai12 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(15, MenuLen15.getCodigoPai().trim(), grupoID);
				Menu4 MenuLen12                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen15.getCodigoPai().trim(), grupoID);
				
				//verifica outrasGravacoesCodigoPai12
				if(outrasGravacoesCodigoPai12 == 0) {
					
					//elimina MenuLen12
					persistencia.mRepository4.delete(MenuLen12);
					
					//pequisa a existencia de outras gravações de codigoPaiLen9 e pega menuLen9
					int outrasGravacoesCodigoPai9 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(12, MenuLen12.getCodigoPai().trim(), grupoID);
					Menu4 MenuLen9                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen12.getCodigoPai().trim(), grupoID);
					
					//verifica outrasGravacoesCodigoPai9
					if(outrasGravacoesCodigoPai9 == 0) {
						
						//elimina MenuLen9
						persistencia.mRepository4.delete(MenuLen9);
						
						//pequisa a existencia de outras gravações de codigoPaiLen6 e pega menuLen6
						int outrasGravacoesCodigoPai6 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(9, MenuLen9.getCodigoPai().trim(), grupoID);
						Menu4 MenuLen6                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen9.getCodigoPai().trim(), grupoID);
						
						//verifica outrasGravacoesCodigoPai6
						if(outrasGravacoesCodigoPai6 == 0) {
							
							//elimina MenuLen6
							persistencia.mRepository4.delete(MenuLen6);
							
							//pequisa a existencia de outras gravações de codigoPaiLen3 e pega menuLen3
							int outrasGravacoesCodigoPai3 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(6, MenuLen6.getCodigoPai().trim(), grupoID);
							Menu4 MenuLen3                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen6.getCodigoPai().trim(), grupoID);
							
							//verifica outrasGravacoesCodigoPai3
							if(outrasGravacoesCodigoPai3 == 0) {
								
								//elimina MenuLen3
								persistencia.mRepository4.delete(MenuLen3);
								
							}							
							
						}
						
					}
					
				}
				
				
			
			
			} else if(codigoLen == 12 && codigoPaiLen == 9) {
				
				//pega menuLen12 e elimina
				Menu4 MenuLen12 = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(codigo, grupoID);
				persistencia.mRepository4.delete(MenuLen12);
				
				//pequisa a existencia de outras gravações de codigoPaiLen9 e pega menuLen9
				int outrasGravacoesCodigoPai9 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(12, MenuLen12.getCodigoPai().trim(), grupoID);
				Menu4 MenuLen9                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen12.getCodigoPai().trim(), grupoID);
				
				//verifica outrasGravacoesCodigoPai9
				if(outrasGravacoesCodigoPai9 == 0) {
					
					//elimina MenuLen9
					persistencia.mRepository4.delete(MenuLen9);
					
					//pequisa a existencia de outras gravações de codigoPaiLen6 e pega menuLen6
					int outrasGravacoesCodigoPai6 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(9, MenuLen9.getCodigoPai().trim(), grupoID);
					Menu4 MenuLen6                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen9.getCodigoPai().trim(), grupoID);
					
					//verifica outrasGravacoesCodigoPai6
					if(outrasGravacoesCodigoPai6 == 0) {
						
						//elimina MenuLen6
						persistencia.mRepository4.delete(MenuLen6);
						
						//pequisa a existencia de outras gravações de codigoPaiLen3 e pega menuLen3
						int outrasGravacoesCodigoPai3 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(6, MenuLen6.getCodigoPai().trim(), grupoID);
						Menu4 MenuLen3                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen6.getCodigoPai().trim(), grupoID);
						
						//verifica outrasGravacoesCodigoPai3
						if(outrasGravacoesCodigoPai3 == 0) {
							
							//elimina MenuLen3
							persistencia.mRepository4.delete(MenuLen3);
							
						}
						
						
						
					}
					
				}
				
				
				
			
			
			
			} else if(codigoLen == 9 && codigoPaiLen == 6) {
				
				//pega menuLen9 e elimina
				Menu4 MenuLen9 = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(codigo, grupoID);
				persistencia.mRepository4.delete(MenuLen9);
				
				//pequisa a existencia de outras gravações de codigoPaiLen6 e pega menuLen6
				int outrasGravacoesCodigoPai6 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(9, MenuLen9.getCodigoPai().trim(), grupoID);
				Menu4 MenuLen6                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen9.getCodigoPai().trim(), grupoID);
				
				//verifica outrasGravacoesCodigoPai6
				if(outrasGravacoesCodigoPai6 == 0) {
					
					//elimina MenuLen6
					persistencia.mRepository4.delete(MenuLen6);
					
					//pequisa a existencia de outras gravações de codigoPaiLen3 e pega menuLen3
					int outrasGravacoesCodigoPai3 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(6, MenuLen6.getCodigoPai().trim(), grupoID);
					Menu4 MenuLen3                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen6.getCodigoPai().trim(), grupoID);
					
					//verifica outrasGravacoesCodigoPai3
					if(outrasGravacoesCodigoPai3 == 0) {
						
						//elimina MenuLen3
						persistencia.mRepository4.delete(MenuLen3);
						
					}
					
					
					
				}
				
				
				
			
			
			
			} else if(codigoLen == 6 && codigoPaiLen == 3) {
				
				//pega menuLen6 e elimina
				Menu4 MenuLen6 = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(codigo, grupoID);
				persistencia.mRepository4.delete(MenuLen6);
				
				//pequisa a existencia de outras gravações de codigoPaiLen3 e pega menuLen3
				int outrasGravacoesCodigoPai3 = persistencia.mRepository4.func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(6, MenuLen6.getCodigoPai().trim(), grupoID);
				Menu4 MenuLen3                = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(MenuLen6.getCodigoPai().trim(), grupoID);
				
				//verifica outrasGravacoesCodigoPai3
				if(outrasGravacoesCodigoPai3 == 0) {
					
					//elimina MenuLen3
					persistencia.mRepository4.delete(MenuLen3);
					
				}
				
			
			
			
			} else if(codigoLen == 3 && codigoPaiLen == 0) {
				
				//pega menuLen3 e elimina
				Menu4 MenuLen3 = persistencia.mRepository4.func205FindOne_PorCodigoEGrupo(codigo, grupoID);
				persistencia.mRepository4.delete(MenuLen3);
				
			}
			
		}
		
	}
	
	
	
	
	public void func013EliminarModulosNoGrupoPrivilegio(GrupoPrivilegio4 grupoPrivilegio) {
		
		//variaveis
		String codigo    = grupoPrivilegio.getCodigo().trim();
		String codigoPai = grupoPrivilegio.getCodigoPai();
		Integer grupoID  = grupoPrivilegio.getGrupo().getId();
		
		//verifica nulidade do codigoPai
		if(codigoPai != null && codigoPai != "" && codigoPai.length() != 0) {
			
			//pega o comprimento de codigo e codigoPai
			int codigoLen    = codigo.length();
			int codigoPaiLen = codigoPai.length();
			
			//verifica codigoLen
			if(codigoLen == 15 && codigoPaiLen == 12) {
				
				//pega grupoPrivilegioLen15 e elimina
				GrupoPrivilegio4 grupoPrivilegioLen15 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
				persistencia.gpRepository4.delete(grupoPrivilegioLen15);
				
				//pequisa a existencia de outras gravações de codigoPaiLen12
				int outrasGravacoesCodigoPai12        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(15, grupoID, grupoPrivilegioLen15.getCodigoPai().trim());
				GrupoPrivilegio4 grupoPrivilegioLen12 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen15.getCodigoPai().trim());
				
				//verifica outrasGravacoesCodigoPai12
				if(outrasGravacoesCodigoPai12 == 0) {
					
					//elimina grupoPrivilegioLen12
					persistencia.gpRepository4.delete(grupoPrivilegioLen12);
					
					//pequisa a existencia de outras gravações de codigoPaiLen9
					int outrasGravacoesCodigoPai9        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(12, grupoID, codigoPai);
					GrupoPrivilegio4 grupoPrivilegioLen9 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen12.getCodigoPai().trim());
					
					//verifica outrasGravacoesCodigoPai9
					if(outrasGravacoesCodigoPai9 == 0) {
						
						//elimina outrasGravacoesCodigoPai9
						persistencia.gpRepository4.delete(grupoPrivilegioLen9);
						
						//pequisa a existencia de outras gravações de codigoPaiLen6
						int outrasGravacoesCodigoPai6        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(9, grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
						GrupoPrivilegio4 grupoPrivilegioLen6 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
						
						//verifica outrasGravacoesCodigoPai6
						if(outrasGravacoesCodigoPai6 == 0) {
							
							//elimina outrasGravacoesCodigoPai6
							persistencia.gpRepository4.delete(grupoPrivilegioLen6);
														
							//pequisa a existencia de outras gravações de outrasGravacoesCodigoPai3
							int outrasGravacoesCodigoPai3        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(6, grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
							GrupoPrivilegio4 grupoPrivilegioLen3 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
							
							//verifica outrasGravacoesCodigoPai6
							if(outrasGravacoesCodigoPai3 == 0) {
								
								//elimina outrasGravacoesCodigoPai3
								persistencia.gpRepository4.delete(grupoPrivilegioLen3);
								
							}
							
						}
						
					}
					
				}
				
				
			} else if(codigoLen == 12 && codigoPaiLen ==9) {
				
				//pega grupoPrivilegioLen12 e elimina
				GrupoPrivilegio4 grupoPrivilegioLen12 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
				persistencia.gpRepository4.delete(grupoPrivilegioLen12);
								
				//pequisa a existencia de outras gravações de codigoPaiLen9
				int outrasGravacoesCodigoPai9        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(12, grupoID, codigoPai);
				GrupoPrivilegio4 grupoPrivilegioLen9 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen12.getCodigoPai().trim());
				
				//verifica outrasGravacoesCodigoPai9
				if(outrasGravacoesCodigoPai9 == 0) {
					
					//elimina outrasGravacoesCodigoPai9
					persistencia.gpRepository4.delete(grupoPrivilegioLen9);
					
					//pequisa a existencia de outras gravações de codigoPaiLen6
					int outrasGravacoesCodigoPai6        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(9, grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
					GrupoPrivilegio4 grupoPrivilegioLen6 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
					
					//verifica outrasGravacoesCodigoPai6
					if(outrasGravacoesCodigoPai6 == 0) {
						
						//elimina outrasGravacoesCodigoPai6
						persistencia.gpRepository4.delete(grupoPrivilegioLen6);
						
						
						//pequisa a existencia de outras gravações de outrasGravacoesCodigoPai3
						int outrasGravacoesCodigoPai3        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(6, grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
						GrupoPrivilegio4 grupoPrivilegioLen3 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
						
						//verifica outrasGravacoesCodigoPai6
						if(outrasGravacoesCodigoPai3 == 0) {
							
							//elimina outrasGravacoesCodigoPai3
							persistencia.gpRepository4.delete(grupoPrivilegioLen3);
							
						}
						
					}
					
				}
				
				
				
			} else if(codigoLen == 9 && codigoPaiLen == 6) {
				
				//pega grupoPrivilegioLen9 e elimina
				GrupoPrivilegio4 grupoPrivilegioLen9 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
				persistencia.gpRepository4.delete(grupoPrivilegioLen9);
				
				//pequisa a existencia de outras gravações de codigoPaiLen6
				int outrasGravacoesCodigoPai6        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(9, grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
				GrupoPrivilegio4 grupoPrivilegioLen6 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen9.getCodigoPai().trim());
				
				//verifica outrasGravacoesCodigoPai6
				if(outrasGravacoesCodigoPai6 == 0) {
					
					//elimina outrasGravacoesCodigoPai6
					persistencia.gpRepository4.delete(grupoPrivilegioLen6);					
					
					//pequisa a existencia de outras gravações de outrasGravacoesCodigoPai3
					int outrasGravacoesCodigoPai3        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(6, grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
					GrupoPrivilegio4 grupoPrivilegioLen3 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
					
					//verifica outrasGravacoesCodigoPai6
					if(outrasGravacoesCodigoPai3 == 0) {
						
						//elimina outrasGravacoesCodigoPai3
						persistencia.gpRepository4.delete(grupoPrivilegioLen3);
						
					}
					
				}
				
				
				
			} else if(codigoLen == 6 && codigoPaiLen == 3) {
				
				//pega grupoPrivilegioLen6 e elimina
				GrupoPrivilegio4 grupoPrivilegioLen6 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
				persistencia.gpRepository4.delete(grupoPrivilegioLen6);
				
				//pequisa a existencia de outras gravações de outrasGravacoesCodigoPai3
				int outrasGravacoesCodigoPai3        = persistencia.gpRepository4.func114Count_OutrasInsercoesPorLenGrupoECodigoPai(6, grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
				GrupoPrivilegio4 grupoPrivilegioLen3 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, grupoPrivilegioLen6.getCodigoPai().trim());
				
				//verifica outrasGravacoesCodigoPai6
				if(outrasGravacoesCodigoPai3 == 0) {
					
					//elimina outrasGravacoesCodigoPai3
					persistencia.gpRepository4.delete(grupoPrivilegioLen3);
					
				}
				
				
				
				
			} else if(codigoLen == 3 && codigoPaiLen == 0) {
				
				//pega grupoPrivilegioLen3 e elimina
				GrupoPrivilegio4 grupoPrivilegioLen3 = persistencia.gpRepository4.func201FindAll_PorGrupoIdECodigo(grupoID, codigo);
				persistencia.gpRepository4.delete(grupoPrivilegioLen3);
				
			}
			
			
			
			
			
			
		} else if(codigoPai == null || codigoPai != "" || codigoPai.length() == 0) {
			
		}
		
	}
	
	
	
	
	
	//funções excutoras ************************************************
	@Override
	public void salvar() {
		
		//pega tamanho do moduloDoSistema
		Integer moduloDoSistemaLen = null;
		
		if(func008VerXhtml(moduloDoSistema3) == true) {
			moduloDoSistemaLen = 3;
		} else if(func008VerXhtml(moduloDoSistema6) == true) {
			moduloDoSistemaLen = 6;
		} else if(func008VerXhtml(moduloDoSistema9) == true) {
			moduloDoSistemaLen = 9;
		} else if(func008VerXhtml(moduloDoSistema12) == true) {
			moduloDoSistemaLen = 12;
		} else if(func008VerXhtml(moduloDoSistema15) == true) {
			moduloDoSistemaLen = 15;
		}
		
		//tratamento tendo em conta moduloDoSistemaLen
		if(moduloDoSistemaLen == 3) {
			
			String codigoPai = null;
			String codigo    = moduloDoSistema3.getCodigo().trim();
			
			//pesquisa a existencia do codigoPai e codigo na base de dados
			int repeticoes3 = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupoPrivilegio.getGrupo().getId(), codigo);
			
			//verifica repeticoes3
			if(repeticoes3 != 0) {
				
				//envia mensagem de aviso
				mensagem.addWarn("INSERÇÃO IMPOSSIBILITADA. Esse privilégio ja existe no sistema.");				
				cancelar();
				
			} else {
				
				//criar objecto grupoPrivilegioInsert3
				GrupoPrivilegio4 grupoPrivilegioInsert3 = new GrupoPrivilegio4(
						null, 
						grupoPrivilegio.getGrupo(), 
						moduloDoSistema3, 
						codigo, 
						codigoPai, 
						grupoPrivilegio.getNovo(), 
						grupoPrivilegio.getSalvar(), 
						grupoPrivilegio.getEditar(), 
						grupoPrivilegio.getDeletar(), 
						grupoPrivilegio.getPesquisar(), 
						null, 
						null);			
				
			}
			
			
		} else if(moduloDoSistemaLen == 6) {
			
		} else if(moduloDoSistemaLen == 9) {
			
		} else if(moduloDoSistemaLen == 12) {
			
			String codigoPai = moduloDoSistema12.getCodigoPai().trim();
			String codigo    = moduloDoSistema12.getCodigo().trim();
			
			//pesquisa a existencia do codigoPai e codigo na base de dados
			int repeticoes12 = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupoPrivilegio.getGrupo().getId(), codigo);
			
			//verifica repeticoes3
			if(repeticoes12 != 0) {
				
				//envia mensagem de aviso
				mensagem.addWarn("INSERÇÃO IMPOSSIBILITADA. Esse privilégio ja existe no sistema.");				
				cancelar();
				
			} else {
				
				//criar objecto grupoPrivilegioInsert12
				GrupoPrivilegio4 grupoPrivilegioInsert12 = new GrupoPrivilegio4(
						null, 
						grupoPrivilegio.getGrupo(), 
						moduloDoSistema12, 
						codigo, 
						codigoPai, 
						grupoPrivilegio.getNovo(), 
						grupoPrivilegio.getSalvar(), 
						grupoPrivilegio.getEditar(), 
						grupoPrivilegio.getDeletar(), 
						grupoPrivilegio.getPesquisar(), 
						null, 
						null);
				
				System.out.println("--- grupoPrivilegioInsert12 = "+grupoPrivilegioInsert12);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioInsert12);
				
				//envia mensagem de confirmação
				mensagem.addInfo("O privilégio foi gravado com sucesso.");
				
				//limpar a tela
				cancelarPrivilegios(grupoPrivilegioInsert12.getGrupo());
				
			}
			
		} else if(moduloDoSistemaLen == 15) {
			
		}
		
	}
	
	
	
	public void salvar2() {
		
		//pega tamanho do moduloDoSistema
		Integer moduloDoSistemaLen = null;
				
		if(func008VerXhtml(moduloDoSistema3) == true) {
			moduloDoSistemaLen = 3;
		} else if(func008VerXhtml(moduloDoSistema6) == true) {
			moduloDoSistemaLen = 6;
		} else if(func008VerXhtml(moduloDoSistema9) == true) {
			moduloDoSistemaLen = 9;
		} else if(func008VerXhtml(moduloDoSistema12) == true) {
			moduloDoSistemaLen = 12;
		} else if(func008VerXhtml(moduloDoSistema15) == true) {
			moduloDoSistemaLen = 15;
		}
		
		
		//tratamento tendo em conta moduloDoSistemaLen
		if(moduloDoSistemaLen == 3) {
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema3.getCodigo().trim());
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) { 
				
				//criar objecto grupoPrivilegioLen3
				GrupoPrivilegio4 grupoPrivilegioLen3 = new GrupoPrivilegio4(null, 
						grupo, 
						moduloDoSistema3, 
						moduloDoSistema3.getCodigo().trim(), 
						moduloDoSistema3.getCodigoPai().trim(), 
						grupoPrivilegio.getNovo(), 
                        grupoPrivilegio.getSalvar(), 
                        grupoPrivilegio.getEditar(), 
                        grupoPrivilegio.getDeletar(), 
                        grupoPrivilegio.getPesquisar(), 
                       (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
                        null);
				
				Menu4 menuLen3 = new Menu4(null, 
						                   moduloDoSistema3.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim(), 
						                   moduloDoSistema3.getCodigo() == null ? null : moduloDoSistema3.getCodigo().trim(), 
						                   moduloDoSistema3.getCodigoPai() == null ? null : moduloDoSistema3.getCodigoPai().trim(), 
						                   moduloDoSistema3.getDescricao().trim(), 
						                   grupoPrivilegioLen3.getGrupo(), 
						                   moduloDoSistema3.getIcone() == null ? null : moduloDoSistema3.getIcone().trim(), 
						                   grupoPrivilegioLen3, 
						                   moduloDoSistema3);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen3);
				persistencia.mRepository4.save(menuLen3);
				
			}
			
			
			
		} else if(moduloDoSistemaLen == 6) {
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema3.getCodigo().trim());
			int repeticoesCodigoLen6  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema6.getCodigo().trim());
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) { 
				
				//criar objecto grupoPrivilegioLen3
				GrupoPrivilegio4 grupoPrivilegioLen3 = new GrupoPrivilegio4(null, grupo, moduloDoSistema3, moduloDoSistema3.getCodigo().trim(), null, null, null, null, null, null, null, null);
				Menu4 menuLen3 = new Menu4(null, 
						                   moduloDoSistema3.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim(), 
						                   moduloDoSistema3.getCodigo() == null ? null : moduloDoSistema3.getCodigo().trim(), 
						                   moduloDoSistema3.getCodigoPai() == null ? null : moduloDoSistema3.getCodigoPai().trim(), 
						                   moduloDoSistema3.getDescricao().trim(), 
						                   grupoPrivilegioLen3.getGrupo(), 
						                   moduloDoSistema3.getIcone() == null ? null : moduloDoSistema3.getIcone().trim(), 
						                   grupoPrivilegioLen3, 
						                   moduloDoSistema3);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen3);
				persistencia.mRepository4.save(menuLen3);
				
			}
			
			//verifica grupoPrivilegioLen6
			if(repeticoesCodigoLen6 == 0) { 
				
				//criar objecto grupoPrivilegioLen6
				GrupoPrivilegio4 grupoPrivilegioLen6 = new GrupoPrivilegio4(null, 
						grupo, 
						moduloDoSistema6, 
						moduloDoSistema6.getCodigo().trim(), 
						moduloDoSistema6.getCodigoPai().trim(), 
						grupoPrivilegio.getNovo(), 
                        grupoPrivilegio.getSalvar(), 
                        grupoPrivilegio.getEditar(), 
                        grupoPrivilegio.getDeletar(), 
                        grupoPrivilegio.getPesquisar(), 
                       (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
                        null);			
				
				Menu4 menuLen6 = new Menu4(null, 
										   moduloDoSistema6.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim(), 
										   moduloDoSistema6.getCodigo() == null ? null : moduloDoSistema6.getCodigo().trim(), 
										   moduloDoSistema6.getCodigoPai() == null ? null : moduloDoSistema6.getCodigoPai().trim(), 
										   moduloDoSistema6.getDescricao().trim(), 
										   grupoPrivilegioLen6.getGrupo(), 
										   moduloDoSistema6.getIcone() == null ? null : moduloDoSistema6.getIcone().trim(), 
										   grupoPrivilegioLen6, 
										   moduloDoSistema6);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen6);
				persistencia.mRepository4.save(menuLen6);
				
			}
			
			
			
			
		}  else if(moduloDoSistemaLen == 9) {
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema3.getCodigo().trim());
			int repeticoesCodigoLen6  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema6.getCodigo().trim());
			int repeticoesCodigoLen9  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema9.getCodigo().trim());
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) { 
				
				//criar objecto grupoPrivilegioLen3
				GrupoPrivilegio4 grupoPrivilegioLen3 = new GrupoPrivilegio4(null, grupo, moduloDoSistema3, moduloDoSistema3.getCodigo().trim(), null, null, null, null, null, null, null, null);
				Menu4 menuLen3                       = new Menu4(null, 
						                                         moduloDoSistema3.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim(), 
						                                         moduloDoSistema3.getCodigo() == null ? null : moduloDoSistema3.getCodigo().trim(), 
						                                         moduloDoSistema3.getCodigoPai() == null ? null : moduloDoSistema3.getCodigoPai().trim(), 
						                                         moduloDoSistema3.getDescricao().trim(), 
						                                         grupoPrivilegioLen3.getGrupo(), 
						                                         moduloDoSistema3.getIcone() == null ? null : moduloDoSistema3.getIcone().trim(), 
						                                         grupoPrivilegioLen3, 
						                                         moduloDoSistema3);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen3);
				persistencia.mRepository4.save(menuLen3);
				
			}
			
			//verifica grupoPrivilegioLen6
			if(repeticoesCodigoLen6 == 0) { 
				
				//criar objecto grupoPrivilegioLen6
				GrupoPrivilegio4 grupoPrivilegioLen6 = new GrupoPrivilegio4(null, grupo, moduloDoSistema6, moduloDoSistema6.getCodigo().trim(), moduloDoSistema6.getCodigoPai().trim(), null, null, null, null, null, null, null);
				Menu4 menuLen6                       = new Menu4(null, 
										                         moduloDoSistema6.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim(), 
										                         moduloDoSistema6.getCodigo() == null ? null : moduloDoSistema6.getCodigo().trim(), 
										                         moduloDoSistema6.getCodigoPai() == null ? null : moduloDoSistema6.getCodigoPai().trim(), 
										                         moduloDoSistema6.getDescricao().trim(), 
										                         grupoPrivilegioLen6.getGrupo(), 
										                         moduloDoSistema6.getIcone() == null ? null : moduloDoSistema6.getIcone().trim(), 
										                         grupoPrivilegioLen6, 
										                         moduloDoSistema6);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen6);
				persistencia.mRepository4.save(menuLen6);
				
			}
			
			
			//verifica repeticoesCodigoLen9
			if(repeticoesCodigoLen9 == 0) { 
				
				//criar objecto repeticoesCodigoLen9
				GrupoPrivilegio4 grupoPrivilegioLen9 = new GrupoPrivilegio4(null, 
						grupo, 
						moduloDoSistema9, 
						moduloDoSistema9.getCodigo().trim(), 
						moduloDoSistema9.getCodigoPai().trim(), 
						grupoPrivilegio.getNovo(), 
                        grupoPrivilegio.getSalvar(), 
                        grupoPrivilegio.getEditar(), 
                        grupoPrivilegio.getDeletar(), 
                        grupoPrivilegio.getPesquisar(), 
                       (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
                        null);				
				
				Menu4 menuLen9 = new Menu4(null, 
										   moduloDoSistema9.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim(), 
										   moduloDoSistema9.getCodigo() == null ? null : moduloDoSistema9.getCodigo().trim(), 
										   moduloDoSistema9.getCodigoPai() == null ? null : moduloDoSistema9.getCodigoPai().trim(), 
										   moduloDoSistema9.getDescricao().trim(), 
										   grupoPrivilegioLen9.getGrupo(), 
										   moduloDoSistema9.getIcone() == null ? null : moduloDoSistema9.getIcone().trim(), 
										   grupoPrivilegioLen9, 
										   moduloDoSistema9);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen9);
				persistencia.mRepository4.save(menuLen9);
				
			}
			
			
			
		
		}  else if(moduloDoSistemaLen == 12) {
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema3.getCodigo().trim());
			int repeticoesCodigoLen6  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema6.getCodigo().trim());
			int repeticoesCodigoLen9  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema9.getCodigo().trim());
			int repeticoesCodigoLen12 = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema12.getCodigo().trim());
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) { System.out.println("--- moduloDoSistemaLen == 12, Entrou no 3");
				
				//criar objecto grupoPrivilegioLen3
				GrupoPrivilegio4 grupoPrivilegioLen3 = new GrupoPrivilegio4(null, grupo, moduloDoSistema3, moduloDoSistema3.getCodigo().trim(), null, null, null, null, null, null, null, null);
				Menu4 menuLen3                       = new Menu4(null, 
						                                         moduloDoSistema3.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim(), 
						                                         moduloDoSistema3.getCodigo() == null ? null : moduloDoSistema3.getCodigo().trim(), 
						                                         moduloDoSistema3.getCodigoPai() == null ? null : moduloDoSistema3.getCodigoPai().trim(), 
						                                         moduloDoSistema3.getDescricao().trim(), 
						                                         grupoPrivilegioLen3.getGrupo(), 
						                                         moduloDoSistema3.getIcone() == null ? null : moduloDoSistema3.getIcone().trim(), 
						                                         grupoPrivilegioLen3, 
						                                         moduloDoSistema3);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen3);
				persistencia.mRepository4.save(menuLen3);
				
			}
			
			//verifica grupoPrivilegioLen6
			if(repeticoesCodigoLen6 == 0) { System.out.println("--- moduloDoSistemaLen == 12, Entrou no 6");
				
				//criar objecto grupoPrivilegioLen6
				GrupoPrivilegio4 grupoPrivilegioLen6 = new GrupoPrivilegio4(null, grupo, moduloDoSistema6, moduloDoSistema6.getCodigo().trim(), moduloDoSistema6.getCodigoPai().trim(), null, null, null, null, null, null, null);
				Menu4 menuLen6                       = new Menu4(null, 
										                         moduloDoSistema6.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim(), 
										                         moduloDoSistema6.getCodigo() == null ? null : moduloDoSistema6.getCodigo().trim(), 
										                         moduloDoSistema6.getCodigoPai() == null ? null : moduloDoSistema6.getCodigoPai().trim(), 
										                         moduloDoSistema6.getDescricao().trim(), 
										                         grupoPrivilegioLen6.getGrupo(), 
										                         moduloDoSistema6.getIcone() == null ? null : moduloDoSistema6.getIcone().trim(), 
										                         grupoPrivilegioLen6, 
										                         moduloDoSistema6);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen6);
				persistencia.mRepository4.save(menuLen6);
				
			}
			
			
			//verifica repeticoesCodigoLen9
			if(repeticoesCodigoLen9 == 0) { System.out.println("--- moduloDoSistemaLen == 12, Entrou no 9");
				
				//criar objecto repeticoesCodigoLen9
				GrupoPrivilegio4 grupoPrivilegioLen9 = new GrupoPrivilegio4(null, grupo, moduloDoSistema9, moduloDoSistema9.getCodigo().trim(), moduloDoSistema9.getCodigoPai().trim(), null, null, null, null, null, null, null);
				Menu4 menuLen9                       = new Menu4(null, 
										                         moduloDoSistema9.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim(), 
										                         moduloDoSistema9.getCodigo() == null ? null : moduloDoSistema9.getCodigo().trim(), 
										                         moduloDoSistema9.getCodigoPai() == null ? null : moduloDoSistema9.getCodigoPai().trim(), 
										                         moduloDoSistema9.getDescricao().trim(), 
										                         grupoPrivilegioLen9.getGrupo(), 
										                         moduloDoSistema9.getIcone() == null ? null : moduloDoSistema9.getIcone().trim(), 
										                         grupoPrivilegioLen9, 
										                         moduloDoSistema9);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen9);
				persistencia.mRepository4.save(menuLen9);
				
			}
			
			
			//verifica repeticoesCodigoLen12
			if(repeticoesCodigoLen12 == 0) { System.out.println("--- moduloDoSistemaLen == 12, Entrou no 12");
				
				//criar objecto repeticoesCodigoLen12
				GrupoPrivilegio4 grupoPrivilegioLen12 = new GrupoPrivilegio4(null, 
						grupo, 
						moduloDoSistema12, 
						moduloDoSistema12.getCodigo().trim(), 
						moduloDoSistema12.getCodigoPai().trim(), 
						grupoPrivilegio.getNovo(), 
                        grupoPrivilegio.getSalvar(), 
                        grupoPrivilegio.getEditar(), 
                        grupoPrivilegio.getDeletar(), 
                        grupoPrivilegio.getPesquisar(), 
                       (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
                        null);
				
				Menu4 menuLen12 = new Menu4(null, 
										    moduloDoSistema12.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema12.getCaminhoFicheiroXhtml().trim(), 
										    moduloDoSistema12.getCodigo() == null ? null : moduloDoSistema12.getCodigo().trim(), 
										    moduloDoSistema12.getCodigoPai() == null ? null : moduloDoSistema12.getCodigoPai().trim(), 
										    moduloDoSistema12.getDescricao().trim(), 
										    grupoPrivilegioLen12.getGrupo(), 
										    moduloDoSistema12.getIcone() == null ? null : moduloDoSistema12.getIcone().trim(), 
										    grupoPrivilegioLen12, 
										    moduloDoSistema12);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen12);
				persistencia.mRepository4.save(menuLen12);
				
			}
			
			
			
			
			
		}  else if(moduloDoSistemaLen == 15) {
			
			//pesquisa a existencia do codigoLen3 e codigoLen6 na base de dados
			int repeticoesCodigoLen3  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema3.getCodigo().trim());
			int repeticoesCodigoLen6  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema6.getCodigo().trim());
			int repeticoesCodigoLen9  = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema9.getCodigo().trim());
			int repeticoesCodigoLen12 = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema12.getCodigo().trim());
			int repeticoesCodigoLen15 = persistencia.gpRepository4.func103FindAll_PorGrupoIdECodigo(grupo.getId(), moduloDoSistema15.getCodigo().trim());
			
			//verifica repeticoesCodigoLen3
			if(repeticoesCodigoLen3 == 0) { 
				
				//criar objecto grupoPrivilegioLen3
				GrupoPrivilegio4 grupoPrivilegioLen3 = new GrupoPrivilegio4(null, grupo, moduloDoSistema3, moduloDoSistema3.getCodigo().trim(), null, null, null, null, null, null, null, null);
				Menu4 menuLen3                       = new Menu4(null, 
						                                         moduloDoSistema3.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim(), 
						                                         moduloDoSistema3.getCodigo() == null ? null : moduloDoSistema3.getCodigo().trim(), 
						                                         moduloDoSistema3.getCodigoPai() == null ? null : moduloDoSistema3.getCodigoPai().trim(), 
						                                         moduloDoSistema3.getDescricao().trim(), 
						                                         grupoPrivilegioLen3.getGrupo(), 
						                                         moduloDoSistema3.getIcone() == null ? null : moduloDoSistema3.getIcone().trim(), 
						                                         grupoPrivilegioLen3, 
						                                         moduloDoSistema3);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen3);
				persistencia.mRepository4.save(menuLen3);
				
			}
			
			//verifica grupoPrivilegioLen6
			if(repeticoesCodigoLen6 == 0) { 
				
				//criar objecto grupoPrivilegioLen6
				GrupoPrivilegio4 grupoPrivilegioLen6 = new GrupoPrivilegio4(null, grupo, moduloDoSistema6, moduloDoSistema6.getCodigo().trim(), moduloDoSistema6.getCodigoPai().trim(), null, null, null, null, null, null, null);
				Menu4 menuLen6                       = new Menu4(null, 
										                         moduloDoSistema6.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim(), 
										                         moduloDoSistema6.getCodigo() == null ? null : moduloDoSistema6.getCodigo().trim(), 
										                         moduloDoSistema6.getCodigoPai() == null ? null : moduloDoSistema6.getCodigoPai().trim(), 
										                         moduloDoSistema6.getDescricao().trim(), 
										                         grupoPrivilegioLen6.getGrupo(), 
										                         moduloDoSistema6.getIcone() == null ? null : moduloDoSistema6.getIcone().trim(), 
										                         grupoPrivilegioLen6, 
										                         moduloDoSistema6);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen6);
				persistencia.mRepository4.save(menuLen6);
				
			}
			
			
			//verifica repeticoesCodigoLen9
			if(repeticoesCodigoLen9 == 0) { 
				
				//criar objecto repeticoesCodigoLen9
				GrupoPrivilegio4 grupoPrivilegioLen9 = new GrupoPrivilegio4(null, grupo, moduloDoSistema9, moduloDoSistema9.getCodigo().trim(), moduloDoSistema9.getCodigoPai().trim(), null, null, null, null, null, null, null);
				Menu4 menuLen9                       = new Menu4(null, 
										                         moduloDoSistema9.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim(), 
										                         moduloDoSistema9.getCodigo() == null ? null : moduloDoSistema9.getCodigo().trim(), 
										                         moduloDoSistema9.getCodigoPai() == null ? null : moduloDoSistema9.getCodigoPai().trim(), 
										                         moduloDoSistema9.getDescricao().trim(), 
										                         grupoPrivilegioLen9.getGrupo(), 
										                         moduloDoSistema9.getIcone() == null ? null : moduloDoSistema9.getIcone().trim(), 
										                         grupoPrivilegioLen9, 
										                         moduloDoSistema9);
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen9);
				persistencia.mRepository4.save(menuLen9);
				
			}
			
			
			//verifica repeticoesCodigoLen12
			if(repeticoesCodigoLen12 == 0) { 
				
				//criar objecto repeticoesCodigoLen12
				GrupoPrivilegio4 grupoPrivilegioLen12 = new GrupoPrivilegio4(null, grupo, moduloDoSistema12, moduloDoSistema12.getCodigo().trim(), null, null, null, null, null, null, null, null);				
				Menu4 menuLen12                       = new Menu4(null, 
										                          moduloDoSistema12.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema12.getCaminhoFicheiroXhtml().trim(), 
										                          moduloDoSistema12.getCodigo() == null ? null : moduloDoSistema12.getCodigo().trim(), 
										                          moduloDoSistema12.getCodigoPai() == null ? null : moduloDoSistema12.getCodigoPai().trim(), 
										                          moduloDoSistema12.getDescricao().trim(), 
										                          grupoPrivilegioLen12.getGrupo(), 
										                          moduloDoSistema12.getIcone() == null ? null : moduloDoSistema12.getIcone().trim(), 
										                          grupoPrivilegioLen12, 
										                          moduloDoSistema12);
				 
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen12);
				persistencia.mRepository4.save(menuLen12);
				
			}			
			
			
			//verifica repeticoesCodigoLen15
			if(repeticoesCodigoLen15 == 0) { 
				
				//criar objecto repeticoesCodigoLen15
				GrupoPrivilegio4 grupoPrivilegioLen15 = new GrupoPrivilegio4(null, 
						grupo, 
						moduloDoSistema15, 
						moduloDoSistema15.getCodigo().trim(), 
						moduloDoSistema15.getCodigoPai().trim(), 
						grupoPrivilegio.getNovo(), 
                        grupoPrivilegio.getSalvar(), 
                        grupoPrivilegio.getEditar(), 
                        grupoPrivilegio.getDeletar(), 
                        grupoPrivilegio.getPesquisar(), 
                       (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
                        null);
				
				Menu4 menuLen15 = new Menu4(null, 
										    moduloDoSistema15.getCaminhoFicheiroXhtml() == null ? null : moduloDoSistema15.getCaminhoFicheiroXhtml().trim(), 
										    moduloDoSistema15.getCodigo() == null ? null : moduloDoSistema15.getCodigo().trim(), 
										    moduloDoSistema15.getCodigoPai() == null ? null : moduloDoSistema15.getCodigoPai().trim(), 
								            moduloDoSistema15.getDescricao().trim(), 
										    grupoPrivilegioLen15.getGrupo(), 
										    moduloDoSistema15.getIcone() == null ? null : moduloDoSistema15.getIcone().trim(), 
										    grupoPrivilegioLen15, 
										    moduloDoSistema15);
				
				
				//gravar na base de dados
				persistencia.gpRepository4.save(grupoPrivilegioLen15);
				persistencia.mRepository4.save(menuLen15);
				
			}
			
			
			
		} 
		
		
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi gravado com sucesso.");
		
		//limpar a tela
		cancelarPrivilegios(grupo);
		
		
	}
	
	

	@Override
	public void editar() {
		
		//criar objecto grupoPrivilegioUpdate
		GrupoPrivilegio4 grupoPrivilegioUpdate = new GrupoPrivilegio4(grupoPrivilegio.getId(), 
						                                              grupoPrivilegio.getGrupo(), 
						                                              grupoPrivilegio.getModuloSistema(), 
						                                              grupoPrivilegio.getCodigo().trim(), 
						                                              grupoPrivilegio.getCodigoPai(), 
						                                              grupoPrivilegio.getNovo(), 
						                                              grupoPrivilegio.getSalvar(), 
						                                              grupoPrivilegio.getEditar(), 
						                                              grupoPrivilegio.getDeletar(), 
						                                              grupoPrivilegio.getPesquisar(), 
						                                             (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
						                                                      null);
		
		//alterar na base de dados
		persistencia.gpRepository4.save(grupoPrivilegioUpdate);
				
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi alterado com sucesso.");
				
		//redefine a lista de modulos
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoPrivilegio.getGrupo()));
				
		//limpa o formulário
		cancelar();
		
		
	}
	
	
	
	
	
	
	

	@Override
	public void eliminar() {
		
		//criar objecto grupoPrivilegioDelete
		GrupoPrivilegio4 grupoPrivilegioDelete = new GrupoPrivilegio4(grupoPrivilegio.getId(), 
								                                      grupoPrivilegio.getGrupo(), 
								                                      grupoPrivilegio.getModuloSistema(), 
								                                      grupoPrivilegio.getCodigo().trim(), 
								                                      grupoPrivilegio.getCodigoPai(), 
								                                      grupoPrivilegio.getNovo(), 
								                                      grupoPrivilegio.getSalvar(), 
								                                      grupoPrivilegio.getEditar(), 
								                                      grupoPrivilegio.getDeletar(), 
								                                      grupoPrivilegio.getPesquisar(), 
								                                     (grupoPrivilegio.getVisibilidade() == null || grupoPrivilegio.getVisibilidade() == "" ? null : grupoPrivilegio.getVisibilidade().trim()), 
								                                      null);
		
		//eliminar no menu
		func012EliminarModulosNoMenu(grupoPrivilegioDelete);
		
		//eliminar na base de dados
		//persistencia.gpRepository4.delete(grupoPrivilegioDelete);
		func013EliminarModulosNoGrupoPrivilegio(grupoPrivilegioDelete);
				
		//envia mensagem de confirmação
		mensagem.addInfo("O privilégio foi eliminado com sucesso.");
				
		//redefine a lista de modulos
		setGrupoPrivilegioLista(func004PegaGrupoPrivilegiosLista(grupoPrivilegio.getGrupo()));
								
		//limpa o formulário
		cancelar();		
		
	}

}
