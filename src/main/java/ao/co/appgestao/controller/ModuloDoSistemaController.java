package ao.co.appgestao.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.ModuloDoSistema;
import lombok.Getter;
import lombok.Setter;

@Named(value = "mdsController")
@ViewScoped
public class ModuloDoSistemaController implements FuncoesGenericas2<ModuloDoSistema>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
	
	@Autowired
	private AMensagens              mensagem;
	
	//variaveis	
	private @Setter @Getter ModuloDoSistema moduloDoSistema, moduloDoSistemaSelecionado;
	private @Setter @Getter ModuloDoSistema moduloDoSistema6, moduloDoSistemaSelecionado6;	
	private @Setter @Getter List<ModuloDoSistema> moduloDoSistemaLista;
	private @Setter @Getter List<ModuloDoSistema> moduloDoSistema6Lista;
	private @Setter @Getter boolean modoNovo, modoNovo6;
	private @Setter @Getter boolean modoEditar, modoEditar6;
	private @Setter @Getter boolean painelModulos6;
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.moduloDoSistema            = new ModuloDoSistema();
  		this.moduloDoSistemaSelecionado = new ModuloDoSistema();
  		
  		this.moduloDoSistemaLista       = persistencia.mdsRepository.func301FindAll_PorCodigoLen3();
  		
  		this.modoNovo                   = false;
  		this.modoNovo6                  = false;
  		this.modoEditar                 = false;
  		this.modoEditar6                = false;
  		this.painelModulos6               = false;
  		
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
		
		setModoNovo(false);
		setModoNovo6(false);
		setModoEditar(false);
		setModoEditar6(false);
		setPainelModulos6(false);
		
		setModuloDoSistema(new ModuloDoSistema());
		setModuloDoSistemaSelecionado(new ModuloDoSistema());
		setModuloDoSistema6(new ModuloDoSistema());
		setModuloDoSistemaSelecionado6(new ModuloDoSistema());
		
		setModuloDoSistema6Lista(null);
		
	}
	
	
	public void cancelar6() {
		
		setModoNovo(false);
		setModoNovo6(false);
		setModoEditar(false);
		setModoEditar6(false);
				
		setModuloDoSistema6(new ModuloDoSistema());
		setModuloDoSistemaSelecionado6(new ModuloDoSistema());
				
	}
	

	@Override
	public void selecionaRegistro() {
		
		setPainelModulos6(true);
		setModuloDoSistema(moduloDoSistemaSelecionado);
		setModuloDoSistema6Lista(persistencia.mdsRepository.func302FindAll_PorCodigoLen6(moduloDoSistemaSelecionado.getCodigo().trim()));
		
	}
	
	public void selecionaRegistroAlterar(ModuloDoSistema moduloDoSistemaLinha) {
		
		setModuloDoSistema(moduloDoSistemaLinha);
		setModoEditar(true);
		
	}
	
	
	public void selecionaRegistroAlterar6(ModuloDoSistema moduloDoSistemaLinha) {
		
		setModuloDoSistema6(moduloDoSistemaLinha);
		setModoEditar6(true);
		
	}
	

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModuloDoSistema(
				new ModuloDoSistema(null, 
						            func001NovoCodigo(), 
						            null, null, null)
		);
		
	}
	
	
	public void novoRegistro6() {
		
		setModoNovo6(true);
		setModuloDoSistema6(
				new ModuloDoSistema(null, 
						            func002NovoCodigoLen6(moduloDoSistema), 
						            moduloDoSistema.getCodigo().trim(), 
						            null, null)
		);
		
		System.out.println("---- moduloDoSistema6 = "+moduloDoSistema6.toString());
		
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
	public String func001NovoCodigo() {
		
		String resultado = null;
		
		//pesquisa o codigo máximo
		String maxCodigo = persistencia.mdsRepository.func102MaxCod_Len3();
		
		//verifica nulidade de maxCodigo
		if(maxCodigo == null) {
			
			resultado = "001";
			
		} else {
			
			Integer novoCodigo = (Integer.parseInt(maxCodigo) + 1);
			resultado          = StringUtils.leftPad(novoCodigo.toString(), 3, "0");
			
		}
		
		return resultado;
		
	}
	
	
	
	public String func002NovoCodigoLen6(ModuloDoSistema moduloDoSistema) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema
		if(moduloDoSistema != null) {
			
			String codigo = moduloDoSistema.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigoLen6 = persistencia.mdsRepository.func105MaxCod_Len6(codigo);
			
			//verifica nulidade de maxCodigo
			if(maxCodigoLen6 == null) {
				
				resultado = codigo+"001";
				
			} else {
				
				Integer novoCodigo = (Integer.parseInt(maxCodigoLen6.substring(4, 6)) + 1);
				resultado          = codigo+StringUtils.leftPad(novoCodigo.toString(), 3, "0");
				
			}
			
		}	
		
		return resultado;
		
	}
	
	
	
	// FUNÇÕES EXECUTORAS **********************************
	@Override
	public void salvar() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes = persistencia.mdsRepository.func101Count_RegistrosRepetidosPorDescricaoLen3(moduloDoSistema.getDescricao());
		
		//verifica nulidade de repeticoes
		if(repeticoes != 0) {
			
			mensagem.addError("O módulo "+moduloDoSistema.getDescricao()+", já existe no sistema.");
			
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema moduloSistemaInsert = new ModuloDoSistema(
					null, 
					moduloDoSistema.getCodigo(), 
					null, 
					moduloDoSistema.getDescricao().toUpperCase().trim(), 
					moduloDoSistema.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema.getCaminhoFicheiroXhtml().trim());
			
			//salvar na base de dados
			persistencia.mdsRepository.save(moduloSistemaInsert);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O módulo "+moduloSistemaInsert.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista(persistencia.mdsRepository.func301FindAll_PorCodigoLen3());
			
			//limpa o formulário
			cancelarGeral();		
			
			
		}
		
	}
	
	
	public void salvar6() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes = persistencia.mdsRepository.func104Count_RegistrosRepetidosPorDescricaoLen6(moduloDoSistema6.getDescricao());
		
		//verifica nulidade de repeticoes
		if(repeticoes != 0) {
			
			mensagem.addError("O sub-módulo "+moduloDoSistema6.getDescricao()+", já existe no sistema.");
			
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema moduloSistemaInsert6 = new ModuloDoSistema(
					null, 
					moduloDoSistema6.getCodigo().trim(), 
					moduloDoSistema6.getCodigoPai().trim(), 
					moduloDoSistema6.getDescricao().trim(), 
					moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim());
			
			//salvar na base de dados
			persistencia.mdsRepository.save(moduloSistemaInsert6);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O sub-módulo "+moduloSistemaInsert6.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistema6Lista(persistencia.mdsRepository.func302FindAll_PorCodigoLen6(moduloSistemaInsert6.getCodigoPai().trim()));
			
			//limpa o formulário
			cancelar6();		
			
			
		}
		
	}
	

	@Override
	public void editar() {
		
		//criar objecto de inserção
		ModuloDoSistema moduloSistemaUpdate = new ModuloDoSistema(
				moduloDoSistema.getId(), 
				moduloDoSistema.getCodigo().equals(null) ? null : moduloDoSistema.getCodigo().trim(), 
				moduloDoSistema.getCodigoPai(), 
				moduloDoSistema.getDescricao().toUpperCase().trim(), 
				moduloDoSistema.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema.getCaminhoFicheiroXhtml().trim());
		
		//alterar na base de dados
		persistencia.mdsRepository.save(moduloSistemaUpdate);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O módulo "+moduloSistemaUpdate.getDescricao()+", foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setModuloDoSistemaLista(persistencia.mdsRepository.func301FindAll_PorCodigoLen3());
		
		//limpa o formulário
		cancelarGeral();	
		
	}
	
	
	public void editar6() {
		
		//criar objecto de inserção
		ModuloDoSistema moduloSistemaUpdate6 = new ModuloDoSistema(
				moduloDoSistema6.getId(), 
				moduloDoSistema6.getCodigo().trim(), 
				moduloDoSistema6.getCodigoPai().trim(), 
				moduloDoSistema6.getDescricao().trim(), 
				moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim());
		
		//salvar na base de dados
		persistencia.mdsRepository.save(moduloSistemaUpdate6);
		
		//envia mensagem de confirmação
		mensagem.addInfo("O sub-módulo "+moduloSistemaUpdate6.getDescricao()+", foi alterado com sucesso.");
		
		//redefine a lista de modulos
		setModuloDoSistema6Lista(persistencia.mdsRepository.func302FindAll_PorCodigoLen6(moduloSistemaUpdate6.getCodigoPai().trim()));
		
		//limpa o formulário
		cancelar6();
		
	}
	

	@Override
	public void eliminar() {
		
		System.out.println("Elimina módulo...");
		
	}
	
	
	public void eliminar6() {
		
		System.out.println("Elimina módulo...");
		
	}
	

}
