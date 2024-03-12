package ao.co.appgestao.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.Menu4;
import ao.co.appgestao.model.ModuloDoSistema4;
import lombok.Getter;
import lombok.Setter;

@Named(value = "mdsController4")
@ViewScoped
public class ModuloDoSistemaController4 implements FuncoesGenericas2<ModuloDoSistema4>{

	// injecção de dependencias
	@Autowired
	private @Setter @Getter APersistenciaController persistencia;
			
	@Autowired
	private AMensagens              mensagem;
	
	
	//variaveis	
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema3, moduloDoSistemaSelecionado3;
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema6, moduloDoSistemaSelecionado6;
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema9, moduloDoSistemaSelecionado9;
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema12, moduloDoSistemaSelecionado12;
	private @Setter @Getter ModuloDoSistema4       moduloDoSistema15, moduloDoSistemaSelecionado15;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistemaLista3;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistemaLista6;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistemaLista9;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistemaLista12;
	private @Setter @Getter List<ModuloDoSistema4> moduloDoSistemaLista15;
	private @Setter @Getter boolean                modoNovo3, modoNovo6, modoNovo9, modoNovo12, modoNovo15;
	private @Setter @Getter boolean                modoEditar3, modoEditar6, modoEditar9, modoEditar12, modoEditar15;
	private @Setter @Getter boolean                painelModulos6, painelModulos9, painelModulos12, painelModulos15;
	//private                 VariaveisConecaoDB     vcdb; 
	
	//variaveis de conexão à bd
	private String databaseURL;
	private String user;
	private String password;
	
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.moduloDoSistema3             = new ModuloDoSistema4();
  		this.moduloDoSistema6             = new ModuloDoSistema4();
  		this.moduloDoSistema9             = new ModuloDoSistema4();
  		this.moduloDoSistema12            = new ModuloDoSistema4();
  		this.moduloDoSistema15            = new ModuloDoSistema4();
  		this.moduloDoSistemaSelecionado3  = new ModuloDoSistema4();  		
  		this.moduloDoSistemaSelecionado6  = new ModuloDoSistema4();  		
  		this.moduloDoSistemaSelecionado9  = new ModuloDoSistema4();
  		this.moduloDoSistemaSelecionado12 = new ModuloDoSistema4();
  		this.moduloDoSistemaSelecionado15 = new ModuloDoSistema4();
  		
  		this.moduloDoSistemaLista3        = func006ListaLen3();
  		this.moduloDoSistemaLista6        = null;
  		this.moduloDoSistemaLista9        = null;
  		this.moduloDoSistemaLista12       = null;
  		this.moduloDoSistemaLista15       = null;
  		
  		this.modoNovo3                    = false;
  		this.modoNovo6                    = false;
  		this.modoNovo9                    = false;
  		this.modoNovo12                   = false;
  		this.modoNovo15                   = false;
  		
  		this.modoEditar3                  = false;
  		this.modoEditar6                  = false;
  		this.modoEditar9                  = false;
  		this.modoEditar12                 = false;
  		this.modoEditar15                 = false;
  		
  		this.painelModulos6               = false;
  		this.painelModulos9               = false;
  		this.painelModulos12              = false;
  		this.painelModulos15              = false;
  		 		
  	
  		this.databaseURL = "jdbc:sqlserver://localhost;instance=MSSQLSERVER;databaseName=dbsistemas;encrypt=false";
  		this.user        = "sa";
  		this.password    = "admin2k4";
  		
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
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar3(false);
		setModoEditar6(false);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setPainelModulos6(false);
		setPainelModulos9(false);
		setPainelModulos12(false);
		setPainelModulos15(false);
		
		setModuloDoSistemaLista3(func006ListaLen3());
		setModuloDoSistemaLista6(null);
		setModuloDoSistemaLista9(null);
		setModuloDoSistemaLista12(null);
		setModuloDoSistemaLista15(null);
		
		setModuloDoSistema3(new ModuloDoSistema4());
		setModuloDoSistema6(new ModuloDoSistema4());
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistemaSelecionado3(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado6(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado9(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado12(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado15(new ModuloDoSistema4());
				
	}
	
	
	public void cancelarLen6() {
		
		setModoNovo6(false);
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		
		setModoEditar6(false);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setPainelModulos9(false);
		setPainelModulos12(false);
		setPainelModulos15(false);
		
		setModuloDoSistemaLista9(null);
		setModuloDoSistemaLista12(null);
		setModuloDoSistemaLista15(null);
		
		
		setModuloDoSistema6(new ModuloDoSistema4());
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistemaSelecionado6(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado9(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado12(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado15(new ModuloDoSistema4());
		
	}
	
	
	public void cancelarLen9() {
		
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setPainelModulos12(false);
		setPainelModulos15(false);
		
		setModuloDoSistemaLista12(null);
		setModuloDoSistemaLista15(null);
		
		setModuloDoSistema9(new ModuloDoSistema4());
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistemaSelecionado9(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado12(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado15(new ModuloDoSistema4());
		
	}
	
	
	
	public void cancelarLen12() {
		
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar12(false);
		setModoEditar15(false);
		
		setPainelModulos15(false);
		
		setModuloDoSistemaLista15(null);
		
		setModuloDoSistema12(new ModuloDoSistema4());
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistemaSelecionado12(new ModuloDoSistema4());
		setModuloDoSistemaSelecionado15(new ModuloDoSistema4());
		
	}
	
	
	public void cancelarLen15() {
		
		setModoNovo15(false);
		
		setModoEditar15(false);
		
		setModuloDoSistema15(new ModuloDoSistema4());
		
		setModuloDoSistemaSelecionado15(new ModuloDoSistema4());
		
	}
	
	
	public void selecionaRegistroLen3() {
		
		//verifica existencia de ficheiro xhtml
		if(func012VerXhtml(moduloDoSistemaSelecionado3) == false) {
			
			setModoNovo3(false);
			setModoNovo6(false);
			setModoNovo9(false);
			setModoNovo12(false);
			setModoNovo15(false);
			
			setModoEditar3(false);
			setModoEditar6(false);
			setModoEditar9(false);
			setModoEditar12(false);
			setModoEditar15(false);
			
			setPainelModulos6(true);
			setPainelModulos9(false);
			setPainelModulos12(false);
			setPainelModulos15(false);
			
			setModuloDoSistema3(moduloDoSistemaSelecionado3);
			
			setModuloDoSistemaLista6(func007ListaLen6(moduloDoSistemaSelecionado3));
			
		}		
		
		
	}
	
	
	public void selecionaRegistroLen6() {
			
		//verifica existencia de ficheiro xhtml
		if(func012VerXhtml(moduloDoSistemaSelecionado6) == false) {
			
			setModoNovo6(false);
			setModoNovo9(false);
			setModoNovo12(false);
			setModoNovo15(false);
			
			setModoEditar6(false);
			setModoEditar9(false);
			setModoEditar12(false);
			setModoEditar15(false);
			
			setPainelModulos9(true);
			setPainelModulos12(false);
			setPainelModulos15(false);			
			
			setModuloDoSistema6(moduloDoSistemaSelecionado6);
			
			setModuloDoSistemaLista9(func008ListaLen9(moduloDoSistemaSelecionado6));
			
		}
		
		
	}
	
	
	
	public void selecionaRegistroLen9() {

		//verifica existencia de ficheiro xhtml
		if(func012VerXhtml(moduloDoSistemaSelecionado9) == false) {
			
			setModoNovo9(false);
			setModoNovo12(false);
			setModoNovo15(false);
			
			setModoEditar9(false);
			setModoEditar12(false);
			setModoEditar15(false);
			
			setPainelModulos12(true);
			setPainelModulos15(false);
			
			setModuloDoSistema9(moduloDoSistemaSelecionado9);
			
			setModuloDoSistemaLista12(func009ListaLen12(moduloDoSistemaSelecionado9));
			
		}
				
	}
	
	
	
	public void selecionaRegistroLen12() {

		//verifica existencia de ficheiro xhtml
		if(func012VerXhtml(moduloDoSistemaSelecionado12) == false) {
			
			setModoNovo12(false);
			setModoNovo15(false);
			
			setModoEditar12(false);
			setModoEditar15(false);
			
			setPainelModulos15(true);
			
			setModuloDoSistema12(moduloDoSistemaSelecionado12);
			
			setModuloDoSistemaLista15(func010ListaLen15(moduloDoSistemaSelecionado12));
			
		}
		
	}
	
	
	public void selecionaRegistroLen15() {

		
		
	}
	
	
	
	
	
	
	public void selecionaRegistroAlterarLen3(ModuloDoSistema4 moduloDoSistema4) {
		
		setModoNovo3(false);
		setModoNovo6(false);
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar3(true);
		setModoEditar6(false);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema3(moduloDoSistema4);
		
	}
	
	
	public void selecionaRegistroAlterarLen6(ModuloDoSistema4 moduloDoSistema6) {
		
		setModoNovo6(false);
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar6(true);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema6(moduloDoSistema6);		
		
	}	
	
	
	public void selecionaRegistroAlterarLen9(ModuloDoSistema4 moduloDoSistema9) {
		
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar9(true);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema9(moduloDoSistema9);
		
	}
	
	
	public void selecionaRegistroAlterarLen12(ModuloDoSistema4 moduloDoSistema12) {
		
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar12(true);
		setModoEditar15(false);
		
		setModuloDoSistema12(moduloDoSistema12);
		
	}


	public void selecionaRegistroAlterarLen15(ModuloDoSistema4 moduloDoSistema15) {
	
		setModoNovo15(false);
		
		setModoEditar15(true);
		
		setModuloDoSistema15(moduloDoSistema15);
	
	}
	
	
	

	@Override
	public void selecionaRegistro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void novoRegistro() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void novoRegistroLen3() {
		
		setModoNovo3(true);
		setModoNovo6(false);
		setModoNovo9(false);
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar3(false);
		setModoEditar6(false);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema3(
				
			new ModuloDoSistema4(null, 
					             func001NovoCodigo3(), 
					             null, 
					             null, null, null) 
				
		);
		
	}
	
	
	public void novoRegistroLen6() {
		
		setModoNovo6(true);
		setModoNovo9(false);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar6(false);
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema6(
			
			new ModuloDoSistema4(null, 
					             func002NovoCodigo6(moduloDoSistema3), 
					             moduloDoSistema3.getCodigo().trim(), 
					             null, null, null)
				
		);
		
		
	}
	
	
	public void novoRegistroLen9() {
		
		setModoNovo9(true);
		setModoNovo12(false);
		setModoNovo15(false);
		
		setModoEditar9(false);
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema9(
			
			new ModuloDoSistema4(null, 
					             func003NovoCodigo9(moduloDoSistema6), 
					             moduloDoSistema6.getCodigo().trim(), 
					             null, null, null)
				
		);
		
	} // 
	
	
	
	public void novoRegistroLen12() {
		
		setModoNovo12(true);
		setModoNovo15(false);
		
		setModoEditar12(false);
		setModoEditar15(false);
		
		setModuloDoSistema12(
			
			new ModuloDoSistema4(null, 
								 func004NovoCodigo12(moduloDoSistema9), 
					             moduloDoSistema9.getCodigo().trim(), 
					             null, null, null)
				
		);
		
	} 
	
	
	public void novoRegistroLen15() {
		
		setModoNovo15(true);
		
		setModoEditar15(false);
		
		setModuloDoSistema15(
			
			new ModuloDoSistema4(null, 
					             func005NovoCodigo15(moduloDoSistema12), 
					             moduloDoSistema12.getCodigo().trim(), 
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
		String maxCodigo = persistencia.mdsRepository4.func102MaxCod_Len3();
		
		//verifica nulidade de maxCodigo
		if(maxCodigo == null) {
			
			resultado = "001";
			
		} else {
			
			Integer novoCodigo = (Integer.parseInt(maxCodigo) + 1);
			resultado          = StringUtils.leftPad(novoCodigo.toString(), 3, "0");
			
		}
		
		return resultado;
		
	}
	
	
	public String func002NovoCodigo6(ModuloDoSistema4 moduloDoSistema3) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema3
		if(moduloDoSistema3 != null) {
			
			String codigoPai3 = moduloDoSistema3.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo6 = persistencia.mdsRepository4.func104MaxCod_Len6(codigoPai3);
			
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
	
	
	
	public String func003NovoCodigo9(ModuloDoSistema4 moduloDoSistema6) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema3
		if(moduloDoSistema6 != null) {
			
			String codigoPai6 = moduloDoSistema6.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo9 = persistencia.mdsRepository4.func105MaxCod_Len9(codigoPai6);
			
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
	
	
	public String func004NovoCodigo12(ModuloDoSistema4 moduloDoSistema9) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema9
		if(moduloDoSistema9 != null) {
			
			String codigoPai9 = moduloDoSistema9.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo12 = persistencia.mdsRepository4.func108MaxCod_Len12(codigoPai9);
			
			//verifica nulidade de maxCodigo
			if(maxCodigo12 == null) {
				
				resultado = codigoPai9 + "001";
				
			} else {
				
				Integer novoCodigo = (Integer.parseInt(maxCodigo12.substring(10, 12)) + 1);
				resultado          = codigoPai9 + StringUtils.leftPad(novoCodigo.toString(), 3, "0");
				
			}
			
			
		}
		
		return resultado;
	}
	
	
	public String func005NovoCodigo15(ModuloDoSistema4 moduloDoSistema12) {
		
		String resultado = null;
		
		//verifica nulidade de moduloDoSistema9
		if(moduloDoSistema12 != null) {

			String codigoPai12 = moduloDoSistema12.getCodigo().trim();
			
			//pesquisa o codigo máximo
			String maxCodigo15 = persistencia.mdsRepository4.func109MaxCod_Len15(codigoPai12);
			
			//verifica nulidade de maxCodigo
			if(maxCodigo15 == null) {
				
				resultado = codigoPai12 + "001";
				
			} else {
				
				Integer novoCodigo = (Integer.parseInt(maxCodigo15.substring(13, 15)) + 1);
				resultado          = codigoPai12 + StringUtils.leftPad(novoCodigo.toString(), 3, "0");
				
			}
			
		}
		
		return resultado;
	}
	
	
	
	
	public List<ModuloDoSistema4> func006ListaLen3() {
		return persistencia.mdsRepository4.func301FindAll_PorCodigoLen3();
	}
	
	public List<ModuloDoSistema4> func007ListaLen6(ModuloDoSistema4 moduloDoSistema) {
		return persistencia.mdsRepository4.func302FindAll_PorCodigoPaiLen3(moduloDoSistema.getCodigo());
	}
	
	public List<ModuloDoSistema4> func008ListaLen9(ModuloDoSistema4 moduloDoSistema6) {
		return persistencia.mdsRepository4.func303FindAll_PorCodigoPaiLen6(moduloDoSistema6.getCodigo());
	}
	
	
	public List<ModuloDoSistema4> func009ListaLen12(ModuloDoSistema4 moduloDoSistema9) {
		return persistencia.mdsRepository4.func303FindAll_PorCodigoPaiLen9(moduloDoSistema9.getCodigo());
	}
	
	
	public List<ModuloDoSistema4> func010ListaLen15(ModuloDoSistema4 moduloDoSistema12) {
		return persistencia.mdsRepository4.func303FindAll_PorCodigoPaiLen12(moduloDoSistema12.getCodigo());
	}
	
	
	
	public int func011EliminaModuloDoSistema(ModuloDoSistema4 moduloDoSistema) {
		
    	Integer resultado = null;
    	
    	//verifica nulidade de enquete
    	if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
    		
    		String DELETE = "DELETE FROM tb_modulos_do_sistema4 "
		                  + "WHERE codigo LIKE '"+moduloDoSistema.getCodigo().trim()+"%'";
    		    		
    		try {
    			
    			Connection connection               = DriverManager.getConnection(databaseURL, user, password);
        		PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        		
        		int resultSet = preparedStatement.executeUpdate();
        		
                resultado = resultSet;
        		
                preparedStatement.close();
        		connection.close();
        		
    		} catch (SQLException e) {
    			System.out.println("--- ERRO NA func011EliminaModuloDoSistema = = "+e.getMessage());
    		} 
    		
    		
    		
    		/*String UPDATE = "DELETE FROM tb_modulos_do_sistema4 "
        			      + "WHERE codigo like ? ";
        	
        	try {
				
        		Connection connection               = DriverManager.getConnection(databaseURL, user, password);
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        		
        		preparedStatement.setString(1, "'"+moduloDoSistema.getCodigo().trim()+"'%");              
                
                int resultSet = preparedStatement.executeUpdate();
        		
                resultado = resultSet;
        		
			} catch (SQLException e) {
				System.out.println("--- ERRO NA DAO func12EliminaModuloDoSistema = = "+e.getMessage());
			}*/
    		
    	}    	
    	
    	return resultado;
    	
    }
	
	
	public boolean func012VerXhtml(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
				
		if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
			
			int dashboardStr  = persistencia.mdsRepository4.func112Count_RepeticoesDashBoardXhtmlPorCaminhoLen3(moduloDoSistema.getId(), "dashboard");
			
			if(moduloDoSistema.getCaminhoFicheiroXhtml() != null && dashboardStr == 0) {				
				resultado = true;
				
			} else if(moduloDoSistema.getCaminhoFicheiroXhtml() != null && dashboardStr != 0) {
				resultado = false;
			}
			
		}
		
		return resultado;
		
	}
	
	
	
	/*public boolean func012VerXhtml(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
				
		if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
			
			if(moduloDoSistema.getCaminhoFicheiroXhtml() != null) {
				resultado = true;
			}
			
		}
		
		return resultado;
		
	}*/
	
	
	/*public boolean func012VerDashbordXhtml(String dashBoardStr) {
		
		boolean resultado = false;
		
		if(dashBoardStr != null && dashBoardStr != "" && dashBoardStr.length() != 0) {
			
			if(persistencia.mdsRepository4.func112Count_RepeticoesDashBoardXhtmlPorCaminhoLen3(dashBoardStr) != 0) {
				resultado = true;
			}
			
		}
		
		return resultado;
	}*/
	
	
	public List<Menu4> func13PegaIdMenu(ModuloDoSistema4 moduloDoSistema) {
    	
		List<Menu4> resultado = null;
		List<Menu4> lista     = new ArrayList<>();
    	String SELECT = "SELECT id  "
    			      + "FROM tb_menu4 " 
    			      + "WHERE modulo_do_sistema_id = "+moduloDoSistema.getId();
    			       
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			
    			Menu4 menu = new Menu4(result.getInt("id"), null, null, null, null, null, null, null, null);
    			lista.add(menu);
    			
    		}
    		
    		resultado = lista;
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NO SELECT func13PegaIdMenu = "+e.getMessage());
		}    	
    	
    	return resultado;
    	
    }
	
	
	
	
	
	
	public boolean verificaCaminho(ModuloDoSistema4 moduloDoSistema) {
		
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
	
	public void salvarLen3() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLen3(moduloDoSistema3.getDescricao());
		
		//verifica nulidade de repeticoes
		if(repeticoes != 0) {
					
			mensagem.addError("O módulo "+moduloDoSistema3.getDescricao()+", já existe no sistema.");
					
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema4 moduloSistemaInsert = new ModuloDoSistema4(null, 
					                                                    moduloDoSistema3.getCodigo(), 
					                                                    null, 
					                                                   (moduloDoSistema3.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema3.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim()), 
					                                                    moduloDoSistema3.getDescricao().trim().toUpperCase(), 
					                                                   (moduloDoSistema3.getIcone().equals(null) || moduloDoSistema3.getIcone().equals("") ? null : moduloDoSistema3.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository4.save(moduloSistemaInsert);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O módulo "+moduloSistemaInsert.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista3(func006ListaLen3());
			
			//limpa o formulário
			cancelarGeral();
			
		}
		
	}
	
	
	
	public void salvarLen6() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes6 = persistencia.mdsRepository4.func103Count_RepeticoesPorDescricaoLen6(moduloDoSistema6.getDescricao().trim());
		
		//verifica nulidade de repeticoes
		if(repeticoes6 != 0) {
							
			mensagem.addError("O sub-módulo "+moduloDoSistema6.getDescricao().trim()+", já existe no sistema.");
							
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema4 moduloSistemaInsert6 = new ModuloDoSistema4(null, 
					                                                     moduloDoSistema6.getCodigo().trim(), 
					                                                     moduloDoSistema6.getCodigoPai().trim(), 
					                                                    (moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim()), 
					                                                     moduloDoSistema6.getDescricao().trim(), 
					                                                    (moduloDoSistema6.getIcone().equals(null) || moduloDoSistema6.getIcone().equals("") ? null : moduloDoSistema6.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository4.save(moduloSistemaInsert6);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O ítem "+moduloSistemaInsert6.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista6(func007ListaLen6(moduloDoSistema3));
			
			//limpa o formulário
			cancelarLen6();
			
		}
		
	}
	
	
	
	public void salvarLen9() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes9 = persistencia.mdsRepository4.func106Count_RepeticoesPorDescricaoLen9(moduloDoSistema9.getDescricao().trim());
				
		//verifica nulidade de repeticoes
		if(repeticoes9 != 0) {
									
			mensagem.addError("O ítem "+moduloDoSistema9.getDescricao().trim()+", já existe no sistema.");
									
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema4 moduloSistemaInsert9 = new ModuloDoSistema4(null, 
					                                                     moduloDoSistema9.getCodigo().trim(), 
					                                                     moduloDoSistema9.getCodigoPai().trim(), 
					                                                    (moduloDoSistema9.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema9.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim()), 
					                                                     moduloDoSistema9.getDescricao().trim(), 
					                                                    (moduloDoSistema9.getIcone().equals(null) || moduloDoSistema9.getIcone().equals("") ? null : moduloDoSistema9.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository4.save(moduloSistemaInsert9);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O ítem "+moduloSistemaInsert9.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista9(func008ListaLen9(moduloDoSistema6));
			
			//limpa o formulário
			cancelarLen9();
			
		}
		
	}
	
	
	
	public void salvarLen12() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes12 = persistencia.mdsRepository4.func110Count_RepeticoesPorDescricaoLen12(moduloDoSistema12.getDescricao().trim());
		
		//verifica nulidade de repeticoes
		if(repeticoes12 != 0) {
											
			mensagem.addError("O ítem "+moduloDoSistema12.getDescricao().trim()+", já existe no sistema.");
											
		} else {
			
			//criar objecto de inserção
			ModuloDoSistema4 moduloSistemaInsert12 = new ModuloDoSistema4(null, 
					                                                     moduloDoSistema12.getCodigo().trim(), 
					                                                     moduloDoSistema12.getCodigoPai().trim(), 
					                                                    (moduloDoSistema12.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema12.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema12.getCaminhoFicheiroXhtml().trim()), 
					                                                     moduloDoSistema12.getDescricao().trim(), 
					                                                    (moduloDoSistema12.getIcone().equals(null) || moduloDoSistema12.getIcone().equals("") ? null : moduloDoSistema12.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository4.save(moduloSistemaInsert12);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O ítem "+moduloSistemaInsert12.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista12(func009ListaLen12(moduloDoSistema9));
			
			//limpa o formulário
			cancelarLen12();
			
			
		}
		
	}
	
	
	public void salvarLen15() {
		
		//pega o numero de repetições da descriçao a gravar
		int repeticoes15 = persistencia.mdsRepository4.func111Count_RepeticoesPorDescricaoLen15(moduloDoSistema12.getDescricao().trim());
		
		//verifica nulidade de repeticoes
		if(repeticoes15 != 0) {
													
			mensagem.addError("O ítem "+moduloDoSistema15.getDescricao().trim()+", já existe no sistema.");
											
		} else {
					
			//criar objecto de inserção
			ModuloDoSistema4 moduloSistemaInsert15 = new ModuloDoSistema4(null, 
																		 moduloDoSistema15.getCodigo().trim(), 
					                                                     moduloDoSistema15.getCodigoPai().trim(), 
					                                                    (moduloDoSistema15.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema15.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema15.getCaminhoFicheiroXhtml().trim()), 
					                                                     moduloDoSistema15.getDescricao().trim(), 
					                                                    (moduloDoSistema15.getIcone().equals(null) || moduloDoSistema15.getIcone().equals("") ? null : moduloDoSistema15.getIcone().trim()));
			
			//salvar na base de dados
			persistencia.mdsRepository4.save(moduloSistemaInsert15);
			
			//envia mensagem de confirmação
			mensagem.addInfo("O ítem "+moduloSistemaInsert15.getDescricao()+", foi inserido com sucesso.");
			
			//redefine a lista de modulos
			setModuloDoSistemaLista15(func010ListaLen15(moduloDoSistema12));
			
			//limpa o formulário
			cancelarLen15();		
					
		}
		
	}
	
	
	
	

	@Override
	public void editar() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void editarLen3() {				
		
		//verifica nulidade de repeticoes
		if(moduloDoSistema3.getDescricao().length() == 0) {
					
			mensagem.addError("O campo Descrição não pode ser nulo.");
		
		} else if(moduloDoSistema3.getDescricao().length() != 0 && moduloDoSistema3.getDescricao().length() > 1) {
			
			//pega o numero de repetições da descriçao a gravar
			int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLen3(moduloDoSistema3.getDescricao());
			
			//verifica nulidade de repeticoes
			if(repeticoes > 1) {
						
				mensagem.addError("O módulo "+moduloDoSistema3.getDescricao()+", já existe no sistema.");
						
			} else {
				
				//criar objecto de inserção
				ModuloDoSistema4 moduloSistemaUpdate3 = new ModuloDoSistema4(moduloDoSistema3.getId(), 
						                                                     moduloDoSistema3.getCodigo(), 
						                                                     moduloDoSistema3.getCodigoPai() == null || moduloDoSistema3.getCodigoPai() == "" ? null : moduloDoSistema3.getCodigoPai(), 
						                                                    (moduloDoSistema3.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema3.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema3.getCaminhoFicheiroXhtml().trim()), 
						                                                     moduloDoSistema3.getDescricao().trim(), 
						                                                    (moduloDoSistema3.getIcone().equals(null) || moduloDoSistema3.getIcone().equals("") ? null : moduloDoSistema3.getIcone().trim()));
				
				//salvar na base de dados
				persistencia.mdsRepository4.save(moduloSistemaUpdate3);
				
				
				List<Menu4> menuIdLista = func13PegaIdMenu(moduloSistemaUpdate3);
				if(menuIdLista != null && menuIdLista.size() != 0) {
					
					for(int i = 0; i < menuIdLista.size(); i++) {
						
						Menu4 menuUpdatePesq = persistencia.mRepository4.findOne(menuIdLista.get(i).getId());
						persistencia.mRepository4.save(new Menu4(menuUpdatePesq.getId(), 
								                                 moduloSistemaUpdate3.getCaminhoFicheiroXhtml(), 
								                                 moduloSistemaUpdate3.getCodigo(), 
								                                 moduloSistemaUpdate3.getCodigoPai(), 
								                                 moduloSistemaUpdate3.getDescricao(), 
								                                 menuUpdatePesq.getGrupo(), 
								                                 moduloSistemaUpdate3.getIcone(), 
								                                 menuUpdatePesq.getGrupoPrivilegio(), 
								                                 moduloSistemaUpdate3));
						
					}
					
				}
				
				
				
				//envia mensagem de confirmação
				mensagem.addInfo("O módulo "+moduloSistemaUpdate3.getDescricao()+", foi alterado com sucesso.");
				
				//redefine a lista de modulos
				setModuloDoSistemaLista3(func006ListaLen3());
				
				//limpa o formulário
				cancelarGeral();
				
			}
			
		} 
		
	}
	
	
	
	public void editarLen6() {				
		
		//verifica nulidade de repeticoes
		if(moduloDoSistema6.getDescricao().length() == 0) {
					
			mensagem.addError("O campo Descrição não pode ser nulo.");
		
		} else if(moduloDoSistema6.getDescricao().length() != 0 && moduloDoSistema6.getDescricao().length() > 1) {
			
			//pega o numero de repetições da descriçao a gravar
			int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLenGenerico(6, moduloDoSistema6.getDescricao());
			
			//verifica nulidade de repeticoes
			if(repeticoes != 0) {
						
				mensagem.addError("O módulo "+moduloDoSistema6.getDescricao()+", já existe no sistema.");
						
			} else {
				
				//criar objecto de inserção
				ModuloDoSistema4 moduloSistemaUpdate6 = new ModuloDoSistema4(moduloDoSistema6.getId(), 
						                                                     moduloDoSistema6.getCodigo(), 
						                                                     moduloDoSistema6.getCodigoPai() == null || moduloDoSistema6.getCodigoPai() == "" ? null : moduloDoSistema6.getCodigoPai(), 
						                                                    (moduloDoSistema6.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema6.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema6.getCaminhoFicheiroXhtml().trim()), 
						                                                     moduloDoSistema6.getDescricao().trim(), 
						                                                    (moduloDoSistema6.getIcone().equals(null) || moduloDoSistema6.getIcone().equals("") ? null : moduloDoSistema6.getIcone().trim()));
				
				//salvar na base de dados
				persistencia.mdsRepository4.save(moduloSistemaUpdate6);
				
				
				
				List<Menu4> menuIdLista = func13PegaIdMenu(moduloSistemaUpdate6);
				if(menuIdLista != null && menuIdLista.size() != 0) {
					
					for(int i = 0; i < menuIdLista.size(); i++) {
						
						Menu4 menuUpdatePesq = persistencia.mRepository4.findOne(menuIdLista.get(i).getId());
						persistencia.mRepository4.save(new Menu4(menuUpdatePesq.getId(), 
								                                 moduloSistemaUpdate6.getCaminhoFicheiroXhtml(), 
								                                 moduloSistemaUpdate6.getCodigo(), 
								                                 moduloSistemaUpdate6.getCodigoPai(), 
								                                 moduloSistemaUpdate6.getDescricao(), 
								                                 menuUpdatePesq.getGrupo(), 
								                                 moduloSistemaUpdate6.getIcone(), 
								                                 menuUpdatePesq.getGrupoPrivilegio(), 
								                                 moduloSistemaUpdate6));
						
					}
					
				}
				
				
				//envia mensagem de confirmação
				mensagem.addInfo("O ítem "+moduloSistemaUpdate6.getDescricao()+", foi alterado com sucesso.");
				
				//redefine a lista de modulos
				setModuloDoSistemaLista6(func007ListaLen6(moduloDoSistema3));
				
				//limpa o formulário
				cancelarLen6();
				
			}
			
		} 
		
	}
	
	
	
	public void editarLen9() {				
		
		//verifica nulidade de repeticoes
		if(moduloDoSistema9.getDescricao().length() == 0) {
					
			mensagem.addError("O campo Descrição não pode ser nulo.");
		
		} else if(moduloDoSistema9.getDescricao().length() != 0 && moduloDoSistema9.getDescricao().length() > 1) {
			
			//pega o numero de repetições da descriçao a gravar
			int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLenGenerico(9, moduloDoSistema9.getDescricao());
			
			//verifica nulidade de repeticoes
			if(repeticoes != 0) {
						
				mensagem.addError("O módulo "+moduloDoSistema9.getDescricao()+", já existe no sistema.");
						
			} else {
				
				//criar objecto de inserção
				ModuloDoSistema4 moduloSistemaUpdate9 = new ModuloDoSistema4(moduloDoSistema9.getId(), 
						                                                     moduloDoSistema9.getCodigo(), 
						                                                     moduloDoSistema9.getCodigoPai() == null || moduloDoSistema9.getCodigoPai() == "" ? null : moduloDoSistema9.getCodigoPai(), 
						                                                    (moduloDoSistema9.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema9.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema9.getCaminhoFicheiroXhtml().trim()), 
						                                                     moduloDoSistema9.getDescricao().trim(), 
						                                                    (moduloDoSistema9.getIcone().equals(null) || moduloDoSistema9.getIcone().equals("") ? null : moduloDoSistema9.getIcone().trim()));
				 
				//salvar na base de dados
				persistencia.mdsRepository4.save(moduloSistemaUpdate9);
				
				
				List<Menu4> menuIdLista = func13PegaIdMenu(moduloSistemaUpdate9);
				if(menuIdLista != null && menuIdLista.size() != 0) {
					
					for(int i = 0; i < menuIdLista.size(); i++) {
						
						Menu4 menuUpdatePesq = persistencia.mRepository4.findOne(menuIdLista.get(i).getId());
						persistencia.mRepository4.save(new Menu4(menuUpdatePesq.getId(), 
								                                 moduloSistemaUpdate9.getCaminhoFicheiroXhtml(), 
								                                 moduloSistemaUpdate9.getCodigo(), 
								                                 moduloSistemaUpdate9.getCodigoPai(), 
								                                 moduloSistemaUpdate9.getDescricao(), 
								                                 menuUpdatePesq.getGrupo(), 
								                                 moduloSistemaUpdate9.getIcone(), 
								                                 menuUpdatePesq.getGrupoPrivilegio(), 
								                                 moduloSistemaUpdate9));
						
					}
					
				}
				
				
				//envia mensagem de confirmação
				mensagem.addInfo("O ítem "+moduloSistemaUpdate9.getDescricao()+", foi alterado com sucesso.");
				
				//redefine a lista de modulos
				setModuloDoSistemaLista9(func008ListaLen9(moduloDoSistema6));
				
				//limpa o formulário
				cancelarLen9();
				
			}
			
		} 
		
	}
	
	
	
	public void editarLen12() {				
		
		//verifica nulidade de repeticoes
		if(moduloDoSistema12.getDescricao().length() == 0) {
					
			mensagem.addError("O campo Descrição não pode ser nulo.");
		
		} else if(moduloDoSistema12.getDescricao().length() != 0 && moduloDoSistema12.getDescricao().length() > 1) {
			
			//pega o numero de repetições da descriçao a gravar
			int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLenGenerico(12, moduloDoSistema12.getDescricao());
			
			//verifica nulidade de repeticoes
			if(repeticoes != 0) {
						
				mensagem.addError("O módulo "+moduloDoSistema12.getDescricao()+", já existe no sistema.");
						
			} else {
				
				//criar objecto de inserção
				ModuloDoSistema4 moduloSistemaUpdate12 = new ModuloDoSistema4(moduloDoSistema12.getId(), 
						                                                      moduloDoSistema12.getCodigo(), 
						                                                      moduloDoSistema12.getCodigoPai() == null || moduloDoSistema12.getCodigoPai() == "" ? null : moduloDoSistema12.getCodigoPai(), 
						                                                     (moduloDoSistema12.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema12.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema12.getCaminhoFicheiroXhtml().trim()), 
						                                                      moduloDoSistema12.getDescricao().trim(), 
						                                                     (moduloDoSistema12.getIcone().equals(null) || moduloDoSistema12.getIcone().equals("") ? null : moduloDoSistema12.getIcone().trim()));
				
				//salvar na base de dados
				persistencia.mdsRepository4.save(moduloSistemaUpdate12);
				
				
				List<Menu4> menuIdLista = func13PegaIdMenu(moduloSistemaUpdate12);
				if(menuIdLista != null && menuIdLista.size() != 0) {
					
					for(int i = 0; i < menuIdLista.size(); i++) {
						
						Menu4 menuUpdatePesq = persistencia.mRepository4.findOne(menuIdLista.get(i).getId());
						persistencia.mRepository4.save(new Menu4(menuUpdatePesq.getId(), 
								                                 moduloSistemaUpdate12.getCaminhoFicheiroXhtml(), 
								                                 moduloSistemaUpdate12.getCodigo(), 
								                                 moduloSistemaUpdate12.getCodigoPai(), 
								                                 moduloSistemaUpdate12.getDescricao(), 
								                                 menuUpdatePesq.getGrupo(), 
								                                 moduloSistemaUpdate12.getIcone(), 
								                                 menuUpdatePesq.getGrupoPrivilegio(), 
								                                 moduloSistemaUpdate12));
						
					}
					
				}
				
				
				
				
				//envia mensagem de confirmação
				mensagem.addInfo("O ítem "+moduloSistemaUpdate12.getDescricao()+", foi alterado com sucesso.");
				
				//redefine a lista de modulos
				setModuloDoSistemaLista12(func009ListaLen12(moduloDoSistema9));
				
				//limpa o formulário
				cancelarLen12();
				
			}
			
		} 
		
	}
		
	
	public void editarLen15() {				
		
		//verifica nulidade de repeticoes
		if(moduloDoSistema15.getDescricao().length() == 0) {
					
			mensagem.addError("O campo Descrição não pode ser nulo.");
		
		} else if(moduloDoSistema15.getDescricao().length() != 0 && moduloDoSistema15.getDescricao().length() > 1) {
			
			//pega o numero de repetições da descriçao a gravar
			int repeticoes = persistencia.mdsRepository4.func101Count_RepeticoesPorDescricaoLenGenerico(15, moduloDoSistema15.getDescricao());
			
			//verifica nulidade de repeticoes
			if(repeticoes != 0) {
						
				mensagem.addError("O módulo "+moduloDoSistema15.getDescricao()+", já existe no sistema.");
						
			} else {
				
				//criar objecto de inserção
				ModuloDoSistema4 moduloSistemaUpdate15 = new ModuloDoSistema4(moduloDoSistema15.getId(), 
						                                                      moduloDoSistema15.getCodigo(), 
						                                                      moduloDoSistema15.getCodigoPai() == null || moduloDoSistema15.getCodigoPai() == "" ? null : moduloDoSistema15.getCodigoPai(), 
						                                                     (moduloDoSistema15.getCaminhoFicheiroXhtml().equals(null) || moduloDoSistema15.getCaminhoFicheiroXhtml().equals("") ? null : moduloDoSistema15.getCaminhoFicheiroXhtml().trim()), 
						                                                      moduloDoSistema15.getDescricao().trim(), 
						                                                     (moduloDoSistema15.getIcone().equals(null) || moduloDoSistema15.getIcone().equals("") ? null : moduloDoSistema15.getIcone().trim()));
				
				//salvar na base de dados
				persistencia.mdsRepository4.save(moduloSistemaUpdate15);
				
				
				
				List<Menu4> menuIdLista = func13PegaIdMenu(moduloSistemaUpdate15);
				if(menuIdLista != null && menuIdLista.size() != 0) {
					
					for(int i = 0; i < menuIdLista.size(); i++) {
						
						Menu4 menuUpdatePesq = persistencia.mRepository4.findOne(menuIdLista.get(i).getId());
						persistencia.mRepository4.save(new Menu4(menuUpdatePesq.getId(), 
								                                 moduloSistemaUpdate15.getCaminhoFicheiroXhtml(), 
								                                 moduloSistemaUpdate15.getCodigo(), 
								                                 moduloSistemaUpdate15.getCodigoPai(), 
								                                 moduloSistemaUpdate15.getDescricao(), 
								                                 menuUpdatePesq.getGrupo(), 
								                                 moduloSistemaUpdate15.getIcone(), 
								                                 menuUpdatePesq.getGrupoPrivilegio(), 
								                                 moduloSistemaUpdate15));
						
					}
					
				}
				
				
				
				//envia mensagem de confirmação
				mensagem.addInfo("O ítem "+moduloSistemaUpdate15.getDescricao()+", foi alterado com sucesso.");
				
				//redefine a lista de modulos
				setModuloDoSistemaLista15(func010ListaLen15(moduloDoSistema12));
				
				//limpa o formulário
				cancelarLen15();
				
			}
			
		} 
		
	}
	
	

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void eliminarLen3() {
		
		//pesquisar existencia de moduloDoSistema3 na tabela grupo privilégios
		int existenciaModuloDoSistema3 = persistencia.gpRepository4.func113Count_RegistrosRepetidosPorModuloSistemaId(moduloDoSistema3.getId());
		
		//verifica existencia de existenciaModuloDoSistema3
		if(existenciaModuloDoSistema3 != 0) {
			
			mensagem.addWarn("O módulo "+moduloDoSistema3.getDescricao().trim()+", está em uso na tabela Grupos Privilégios. Deve eliminar primeiro essa correspondência nessa tabela.");
		
		} else {
			
			//elimina moduloDoSistema3 da base de dados
			int resultado = func011EliminaModuloDoSistema(moduloDoSistema3);
			
			//verifica resultado
			if(resultado == 0 ) {
				
				mensagem.addError("O módulo "+moduloDoSistema3.getDescricao().trim()+", não foi eliminado.");
			
			} else {
				
				if(resultado == 1) {
					mensagem.addInfo("O módulo "+moduloDoSistema3.getDescricao().trim()+", foi eliminado com sucesso.");
				} else if(resultado > 1) {
					mensagem.addInfo("O módulo "+moduloDoSistema3.getDescricao().trim()+" e seus dependêntes, foram eliminados com sucesso.");
				}							
				
				//redefine a lista de modulos
				setModuloDoSistemaLista3(func006ListaLen3());
				
				//limpa o formulário
				cancelarGeral();
			}
			
		}
		
		
	}
	
	
	public void eliminarLen6() {
		
		// pesquisar existencia de moduloDoSistema6 na tabela grupo privilégios
		int existenciaModuloDoSistema6 = persistencia.gpRepository4.func113Count_RegistrosRepetidosPorModuloSistemaId(moduloDoSistema6.getId());

		// verifica existencia de existenciaModuloDoSistema6
		if (existenciaModuloDoSistema6 != 0) {

			mensagem.addWarn("O módulo " + moduloDoSistema6.getDescricao().trim()
					+ ", está em uso na tabela Grupos Privilégios. Deve eliminar primeiro essa correspondência nessa tabela.");

		} else {

			// elimina moduloDoSistema3 da base de dados
			int resultado = func011EliminaModuloDoSistema(moduloDoSistema6);

			// verifica resultado
			if (resultado == 0) {

				mensagem.addError("O ítem " + moduloDoSistema6.getDescricao().trim() + ", não foi eliminado.");

			} else {

				if (resultado == 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema6.getDescricao().trim() + ", foi eliminado com sucesso.");
				} else if (resultado > 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema6.getDescricao().trim()+ " e seus dependêntes, foram eliminados com sucesso.");
				}

				//redefine a lista de modulos
				setModuloDoSistemaLista6(func007ListaLen6(moduloDoSistema3));
				
				//limpa o formulário
				cancelarLen6();
			}

		}
		
	}
	
	
	public void eliminarLen9() {
		
		// pesquisar existencia de moduloDoSistema9 na tabela grupo privilégios
		int existenciaModuloDoSistema9 = persistencia.gpRepository4.func113Count_RegistrosRepetidosPorModuloSistemaId(moduloDoSistema9.getId());

		// verifica existencia de existenciaModuloDoSistema9
		if (existenciaModuloDoSistema9 != 0) {

			mensagem.addWarn("O módulo " + moduloDoSistema9.getDescricao().trim()+ ", está em uso na tabela Grupos Privilégios. Deve eliminar primeiro essa correspondência nessa tabela.");

		} else {

			// elimina moduloDoSistema3 da base de dados
			int resultado = func011EliminaModuloDoSistema(moduloDoSistema9);

			// verifica resultado
			if (resultado == 0) {

				mensagem.addError("O ítem " + moduloDoSistema9.getDescricao().trim() + ", não foi eliminado.");

			} else {

				if (resultado == 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema9.getDescricao().trim() + ", foi eliminado com sucesso.");
				} else if (resultado > 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema9.getDescricao().trim()+ " e seus dependêntes, foram eliminados com sucesso.");
				}

				//redefine a lista de modulos
				setModuloDoSistemaLista9(func008ListaLen9(moduloDoSistema6));
				
				//limpa o formulário
				cancelarLen9();
			}

		}
		
	}
	
	
	public void eliminarLen12() {
		
		// pesquisar existencia de moduloDoSistema12 na tabela grupo privilégios
		int existenciaModuloDoSistema12 = persistencia.gpRepository4.func113Count_RegistrosRepetidosPorModuloSistemaId(moduloDoSistema12.getId());

		// verifica existencia de moduloDoSistema12
		if (existenciaModuloDoSistema12 != 0) {

			mensagem.addWarn("O ítem " + moduloDoSistema12.getDescricao().trim()+ ", está em uso na tabela Grupos Privilégios. Deve eliminar primeiro essa correspondência nessa tabela.");

		} else {

			// elimina moduloDoSistema3 da base de dados
			int resultado = func011EliminaModuloDoSistema(moduloDoSistema12);

			// verifica resultado
			if (resultado == 0) {

				mensagem.addError("O ítem " + moduloDoSistema12.getDescricao().trim() + ", não foi eliminado.");

			} else {

				if (resultado == 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema12.getDescricao().trim() + ", foi eliminado com sucesso.");
				} else if (resultado > 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema12.getDescricao().trim()+ " e seus dependêntes, foram eliminados com sucesso.");
				}

				//redefine a lista de modulos
				setModuloDoSistemaLista12(func009ListaLen12(moduloDoSistema9));
				
				//limpa o formulário
				cancelarLen12();
			}

		}
		
	}
	
	
	public void eliminarLen15() {
		
		// pesquisar existencia de moduloDoSistema15 na tabela grupo privilégios
		int existenciaModuloDoSistema15 = persistencia.gpRepository4.func113Count_RegistrosRepetidosPorModuloSistemaId(moduloDoSistema15.getId());

		// verifica existencia de existenciaModuloDoSistema15
		if (existenciaModuloDoSistema15 != 0) {

			mensagem.addWarn("O ítem " + moduloDoSistema15.getDescricao().trim()+ ", está em uso na tabela Grupos Privilégios. Deve eliminar primeiro essa correspondência nessa tabela.");

		} else {
			
			// elimina moduloDoSistema3 da base de dados
			int resultado = func011EliminaModuloDoSistema(moduloDoSistema15); 

			// verifica resultado
			if (resultado == 0) {

				mensagem.addError("O ítem " + moduloDoSistema15.getDescricao().trim() + ", não foi eliminado.");

			} else {

				if (resultado == 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema15.getDescricao().trim() + ", foi eliminado com sucesso.");
				} else if (resultado > 1) {
					mensagem.addInfo("O ítem " + moduloDoSistema15.getDescricao().trim()+ " e seus dependêntes, foram eliminados com sucesso.");
				}

				//redefine a lista de modulos
				setModuloDoSistemaLista15(func010ListaLen15(moduloDoSistema12));
				
				//limpa o formulário
				cancelarLen15();
			}

		}
		
	}
	
	
	
}
