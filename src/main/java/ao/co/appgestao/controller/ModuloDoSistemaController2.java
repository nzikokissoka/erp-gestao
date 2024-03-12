package ao.co.appgestao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.ModuloDoSistema2;
import lombok.Getter;
import lombok.Setter;

@Named(value = "mdsController2")
@ViewScoped
public class ModuloDoSistemaController2 implements FuncoesGenericas2<ModuloDoSistema2>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
		
	@Autowired
	private AMensagens              mensagem;
	
	
	//variaveis	
	private @Setter @Getter ModuloDoSistema2       moduloDoSistema3, moduloDoSistemaSelecionado3;
	private @Setter @Getter ModuloDoSistema2       moduloDoSistema6, moduloDoSistemaSelecionado6;
	private @Setter @Getter ModuloDoSistema2       moduloDoSistema9, moduloDoSistemaSelecionado9;
	private @Setter @Getter List<ModuloDoSistema2> moduloDoSistemaLista3;
	private @Setter @Getter List<ModuloDoSistema2> moduloDoSistemaLista6;
	private @Setter @Getter List<ModuloDoSistema2> moduloDoSistemaLista9;
	private @Setter @Getter boolean                modoNovo3, modoNovo6, modoNovo9;
	private @Setter @Getter boolean                modoEditar3, modoEditar6, modoEditar9;
	private @Setter @Getter boolean                painelModulos6, painelModulos9;
	
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.moduloDoSistema3            = new ModuloDoSistema2();
  		this.moduloDoSistema6            = new ModuloDoSistema2();
  		this.moduloDoSistema9            = new ModuloDoSistema2();
  		this.moduloDoSistemaSelecionado3 = new ModuloDoSistema2();  		
  		this.moduloDoSistemaSelecionado6 = new ModuloDoSistema2();  		
  		this.moduloDoSistemaSelecionado9 = new ModuloDoSistema2();
  		
  		this.moduloDoSistemaLista3       = func004ListaLen3();
  		this.moduloDoSistemaLista6       = null;
  		this.moduloDoSistemaLista9       = null;
  		
  		this.modoNovo3                   = false;
  		this.modoNovo6                   = false;
  		this.modoNovo9                   = false;
  		this.modoEditar3                 = false;
  		this.modoEditar6                 = false;
  		this.modoEditar9                 = false;
  		this.painelModulos6              = false;
  		this.painelModulos9              = false;
  		
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
		// TODO Auto-generated method stub
		
	}
	
	public void cancelarGeral() {
		
		setModoNovo3(false);
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar3(false);
		setModoEditar6(false);
		setModoEditar9(false);
		
		setPainelModulos6(false);
		setPainelModulos9(false);
		
		setModuloDoSistemaLista3(func004ListaLen3());
		setModuloDoSistemaLista6(null);
		setModuloDoSistemaLista9(null);
		
		setModuloDoSistema3(new ModuloDoSistema2());
		setModuloDoSistema6(new ModuloDoSistema2());
		setModuloDoSistema9(new ModuloDoSistema2());
		
		setModuloDoSistemaSelecionado3(new ModuloDoSistema2());
		setModuloDoSistemaSelecionado6(new ModuloDoSistema2());
		setModuloDoSistemaSelecionado9(new ModuloDoSistema2());
				
	}
	
	public void cancelar6() {
		
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar6(false);
		setModoEditar9(false);
		
		setPainelModulos9(false);
		
		setModuloDoSistemaLista9(null);
		
		setModuloDoSistema6(new ModuloDoSistema2());
		setModuloDoSistema9(new ModuloDoSistema2());
		
		setModuloDoSistemaSelecionado6(new ModuloDoSistema2());
		setModuloDoSistemaSelecionado9(new ModuloDoSistema2());
		
	}
	
	public void cancelar9() {
		
		setModoNovo9(false);
		
		setModoEditar9(false);
		
		setModuloDoSistema9(new ModuloDoSistema2());
		
		setModuloDoSistemaSelecionado9(new ModuloDoSistema2());
		
	}

	@Override
	public void selecionaRegistro() {
		// TODO Auto-generated method stub
		
	}
	
	public void selecionaRegistro3() {
		
		setModoNovo3(false);
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar3(false);
		setModoEditar6(false);
		setModoEditar9(false);
		
		setPainelModulos6(true);
		
		setModuloDoSistema3(moduloDoSistemaSelecionado3);
		
		setModuloDoSistemaLista6(func005ListaLen6(moduloDoSistemaSelecionado3));
		
	}
	
	
	public void selecionaRegistro6() {

		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar6(false);
		setModoEditar9(false);
		
		setPainelModulos9(true);
		
		setModuloDoSistema6(moduloDoSistemaSelecionado6);
		
		setModuloDoSistemaLista9(func006ListaLen9(moduloDoSistemaSelecionado6));
		
	}
	
	public void selecionaRegistro9() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void selecionaRegistroAlterar3(ModuloDoSistema2 moduloDoSistema2) {
		
		setModoNovo3(false);
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar3(true);
		setModoEditar6(false);
		setModoEditar9(false);
		
		setModuloDoSistema3(moduloDoSistema2);
		
	}
	
	
	public void selecionaRegistroAlterar6(ModuloDoSistema2 moduloDoSistema6) {
		
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModoEditar6(true);
		setModoEditar9(false);
		
		setModuloDoSistema6(moduloDoSistema6);
		
	}	
	
	
	public void selecionaRegistroAlterar9(ModuloDoSistema2 moduloDoSistema9) {
		
		setModoNovo9(false);
		
		setModoEditar9(true);
		
		setModuloDoSistema9(moduloDoSistema9);
		
	}
	
	

	@Override
	public void novoRegistro() {
		// TODO Auto-generated method stub
		
	}
	
	public void novoRegistro3() {
		
		setModoNovo3(true);
		setModoNovo6(false);
		setModoNovo9(false);
		
		setModuloDoSistema3(
				
			new ModuloDoSistema2(null, 
					             func001NovoCodigo3(), 
					             null, 
					             null, null, null) 
				
		);
		
	}
	
	public void novoRegistro6() {
		
		setModoNovo6(true);
		setModoNovo9(false);
		
		setModuloDoSistema6(
			
			new ModuloDoSistema2(null, 
					             func002NovoCodigo6(moduloDoSistema3), 
					             moduloDoSistema3.getCodigo().trim(), 
					             null, null, null)
				
		);
		
		
	}
	
	public void novoRegistro9() {
		
		setModoNovo9(true);
		
		setModuloDoSistema9(
			
			new ModuloDoSistema2(null, 
					             func003NovoCodigo9(moduloDoSistema6), 
					             moduloDoSistema6.getCodigo().trim(), 
					             null, null, null)
				
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
	
	
	
	
	
	// FUNÇÕES AUXILIARES 2 **********************************	
	public String func001NovoCodigo3() {
		
		String resultado = null;
		
		//pesquisa o codigo máximo
		String maxCodigo = persistencia.mdsRepository2.func102MaxCod_Len3();
		
		//verifica nulidade de maxCodigo
		if(maxCodigo == null) {
			
			resultado = "001";
			
		} else {
			
			Integer novoCodigo = (Integer.parseInt(maxCodigo) + 1);
			resultado          = StringUtils.leftPad(novoCodigo.toString(), 3, "0");
			
		}
		
		return resultado;
		
	}
	
	
	public String func002NovoCodigo6(ModuloDoSistema2 moduloDoSistema3) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema3
		if(moduloDoSistema3 != null) {
			
			String codigoPai3 = moduloDoSistema3.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo6 = persistencia.mdsRepository2.func104MaxCod_Len6(codigoPai3);
			
			//verifica nulidade de maxCodigo
			if(maxCodigo6 == null) {
						
				resultado = codigoPai3 + "001";
						
			} else {
				
				Integer novoCodigo = (Integer.parseInt(maxCodigo6.substring(4, 6)) + 1);
				resultado          = codigoPai3 + StringUtils.leftPad(novoCodigo.toString(), 3, "0");
				
			}
			
		}		
		
		return resultado;
		
	}
	
	
	
	public String func003NovoCodigo9(ModuloDoSistema2 moduloDoSistema6) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema3
		if(moduloDoSistema6 != null) {
			
			String codigoPai6 = moduloDoSistema6.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo9 = persistencia.mdsRepository2.func105MaxCod_Len9(codigoPai6);
			
			//verifica nulidade de maxCodigo
			if(maxCodigo9 == null) {
						
				resultado = codigoPai6 + "001";
						
			} else {
				
				Integer novoCodigo = (Integer.parseInt(maxCodigo9.substring(7, 9)) + 1);
				resultado          = codigoPai6 + StringUtils.leftPad(novoCodigo.toString(), 3, "0");				
			}
			
		}		
		
		return resultado;		
		
	}
	
	
	
	
	public List<ModuloDoSistema2> func004ListaLen3() {
		return persistencia.mdsRepository2.func301FindAll_PorCodigoLen3();
	}
	
	public List<ModuloDoSistema2> func005ListaLen6(ModuloDoSistema2 moduloDoSistema) {
		return persistencia.mdsRepository2.func302FindAll_PorCodigoPaiLen3(moduloDoSistema.getCodigo());
	}
	
	public List<ModuloDoSistema2> func006ListaLen9(ModuloDoSistema2 moduloDoSistema6) {
		return persistencia.mdsRepository2.func303FindAll_PorCodigoPaiLen6(moduloDoSistema6.getCodigo());
	}
	
	public boolean verificaCaminho(ModuloDoSistema2 moduloDoSistema) {
		
		boolean resultado = false;
		
		if(!moduloDoSistema.getCaminhoFicheiroXhtml().equals(null) && !moduloDoSistema.getCaminhoFicheiroXhtml().equals("")) {
			resultado = true;
		}
		
		return resultado;
		
	}
	
	
	
	
	

	
	
	// FUNÇÕES EXECUTORAS **********************************
	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void salvar3() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes = persistencia.mdsRepository2.func101Count_RepeticoesPorDescricaoLen3(moduloDoSistema3.getDescricao());
		
		//verifica nulidade de repeticoes
		if(repeticoes != 0) {
					
			mensagem.addError("O módulo "+moduloDoSistema3.getDescricao()+", já existe no sistema.");
					
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema2 moduloSistemaInsert = new ModuloDoSistema2(null, 
					                                                    moduloDoSistema3.getCodigo(), 
					                                                    null, 
					                                                   (moduloDoSistema3.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema3.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim()), 
					                                                    moduloDoSistema3.getDescricao().trim().toUpperCase(), 
					                                                   (moduloDoSistema3.getIcone().equals(null) || moduloDoSistema3.getIcone().equals("") ? null : moduloDoSistema3.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository2.save(moduloSistemaInsert);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O módulo "+moduloSistemaInsert.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista3(func004ListaLen3());
			
			//limpa o formulário
			cancelarGeral();
			
		}
		
	}
	
	public void salvar6() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes6 = persistencia.mdsRepository2.func103Count_RepeticoesPorDescricaoLen6(moduloDoSistema6.getDescricao().trim());
		
		//verifica nulidade de repeticoes
		if(repeticoes6 != 0) {
							
			mensagem.addError("O sub-módulo "+moduloDoSistema6.getDescricao().trim()+", já existe no sistema.");
							
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema2 moduloSistemaInsert6 = new ModuloDoSistema2(null, 
					                                                    moduloDoSistema6.getCodigo().trim(), 
					                                                    moduloDoSistema6.getCodigoPai().trim(), 
					                                                   (moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim()), 
					                                                    moduloDoSistema6.getDescricao().trim(), 
					                                                   (moduloDoSistema6.getIcone().equals(null) || moduloDoSistema6.getIcone().equals("") ? null : moduloDoSistema6.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository2.save(moduloSistemaInsert6);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O sub-módulo "+moduloSistemaInsert6.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista6(func005ListaLen6(moduloDoSistema3));
			
			//limpa o formulário
			cancelar6();
			
		}
		
	}
	
	public void salvar9() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes9 = persistencia.mdsRepository2.func106Count_RepeticoesPorDescricaoLen9(moduloDoSistema9.getDescricao().trim());
				
		//verifica nulidade de repeticoes
		if(repeticoes9 != 0) {
									
			mensagem.addError("O ítem "+moduloDoSistema9.getDescricao().trim()+", já existe no sistema.");
									
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema2 moduloSistemaInsert9 = new ModuloDoSistema2(null, 
					                                                    moduloDoSistema9.getCodigo().trim(), 
					                                                    moduloDoSistema9.getCodigoPai().trim(), 
					                                                   (moduloDoSistema9.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema9.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim()), 
					                                                    moduloDoSistema9.getDescricao().trim(), 
					                                                   (moduloDoSistema9.getIcone().equals(null) || moduloDoSistema9.getIcone().equals("") ? null : moduloDoSistema9.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository2.save(moduloSistemaInsert9);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O ítem "+moduloSistemaInsert9.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista9(func006ListaLen9(moduloDoSistema6));
			
			//limpa o formulário
			cancelar9();
			
		}
		
	}
	
	public void editar3() {
		
		//criar objecto de alterar
		ModuloDoSistema2 moduloSistemaUpdate = new ModuloDoSistema2(moduloDoSistema3.getId(), 
				                                                    moduloDoSistema3.getCodigo().trim(), 
				                                                    moduloDoSistema3.getCodigoPai(), 
				                                                   (moduloDoSistema3.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema3.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim()), 
				                                                    moduloDoSistema3.getDescricao().trim().toUpperCase(), 
				                                                   (moduloDoSistema3.getIcone().equals(null) || moduloDoSistema3.getIcone().equals("") ? null : moduloDoSistema3.getIcone().trim()));
		
		//salvar na base de dados
		persistencia.mdsRepository2.save(moduloSistemaUpdate);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O módulo "+moduloSistemaUpdate.getDescricao()+", foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setModuloDoSistemaLista3(func004ListaLen3());
		
		//limpa o formulário
		cancelarGeral();
		
	}
	
	public void editar6() {
		
		//criar objecto de alteração
		ModuloDoSistema2 moduloSistemaUpdate6 = new ModuloDoSistema2(moduloDoSistema6.getId(), 
				                                                     moduloDoSistema6.getCodigo().trim(), 
				                                                     moduloDoSistema6.getCodigoPai().trim(), 
				                                                    (moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim()), 
				                                                     moduloDoSistema6.getDescricao().trim(), 
				                                                    (moduloDoSistema6.getIcone().equals(null) || moduloDoSistema6.getIcone().equals("") ? null : moduloDoSistema6.getIcone().trim()));
		
		//salvar na base de dados
		persistencia.mdsRepository2.save(moduloSistemaUpdate6);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O sub-módulo "+moduloSistemaUpdate6.getDescricao()+", foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setModuloDoSistemaLista6(func005ListaLen6(moduloDoSistema3));
		
		//limpa o formulário
		cancelar6();
		
	}
	
	public void editar9() {
		
		//criar objecto de inserção
		ModuloDoSistema2 moduloSistemaUpdate9 = new ModuloDoSistema2(moduloDoSistema9.getId(), 
				                                                     moduloDoSistema9.getCodigo().trim(), 
				                                                     moduloDoSistema9.getCodigoPai().trim(), 
				                                                    (moduloDoSistema9.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema9.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim()), 
				                                                     moduloDoSistema9.getDescricao().trim(), 
				                                                    (moduloDoSistema9.getIcone().equals(null) || moduloDoSistema9.getIcone().equals("") ? null : moduloDoSistema9.getIcone().trim()));
		
		//salvar na base de dados
		persistencia.mdsRepository2.save(moduloSistemaUpdate9);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O ítem "+moduloSistemaUpdate9.getDescricao()+", foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setModuloDoSistemaLista9(func006ListaLen9(moduloDoSistema6));
		
		//limpa o formulário
		cancelar9();
		
	}
	
	public void eliminar3() {
		// TODO Auto-generated method stub
		
	}
	
	public void eliminar6() {
		
		//pega as repetições de usabilidade do codigo na tabela privilegios
		int repeticoes = persistencia.gpRepository2.func102Count_RegistrosRepetidosPorCodigoLen6(moduloDoSistema6.getCodigo().trim());
		
		//verifica as repecições
		if(repeticoes != 0) {
			
			//envia mensagem de confirmação
			mensagem.addInfo("ELIMINAÇÃO IMPOSSIBILITADA. O sub-módulo "+moduloDoSistema6.getDescricao()+", está em uso na tabela Grupo Privilégios.");
			
		} else if(repeticoes == 0){
			
			//listagem de possiveis filhos de moduloDoSistema6 
			List<ModuloDoSistema2> moduloDoSistema9Lista = persistencia.mdsRepository2.func313FindAll_CodigoLen9PorCodigoLen6(moduloDoSistema6.getCodigo());
			
			//verifica os possiveis filhos de moduloDoSistema6 
			if(moduloDoSistema9Lista.size() != 0) {
				
				//elimina possiveis filhos de moduloDoSistema6 
				for(int i = 0; i < moduloDoSistema9Lista.size(); i++) {
					persistencia.mdsRepository2.delete(moduloDoSistema9Lista.get(i).getId());
				}
				
			}
			
			String descricaoSTR6 = moduloDoSistema6.getDescricao().trim();
			
			//elimina moduloDoSistema6 
			persistencia.mdsRepository2.delete(moduloDoSistema6);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O sub-módulo "+descricaoSTR6+", foi eliminado com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista6(func005ListaLen6(
				persistencia.mdsRepository2.func205FindOne_CodigoLen3PorCodigoLen6(moduloDoSistema6.getCodigo().trim())	
			));
			
			//limpa o formulário
			cancelar6();
			
		}
		
	}
	
	public void eliminar9() {
		// TODO Auto-generated method stub
		
	}

}
