package ao.co.appgestao.controller;

import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;


import ao.co.appgestao.genericsFuntions.FuncoesGenericas2;
import ao.co.appgestao.model.Area;
import ao.co.appgestao.model.AreaEspecifica;
import ao.co.appgestao.model.Carreira;
import ao.co.appgestao.model.Categoria;
import ao.co.appgestao.model.Especialidade;
import ao.co.appgestao.model.Funcao;
import ao.co.appgestao.model.HabilitacaoLiteraria;
import ao.co.appgestao.model.Integrante;
import ao.co.appgestao.model.Municipio;
import ao.co.appgestao.model.Pessoa;
import ao.co.appgestao.model.Provincia;
import ao.co.appgestao.model.Titulo;
import ao.co.appgestao.utilities.DAO;
import ao.co.appgestao.utilities.UtilDatas;
import lombok.Getter;
import lombok.Setter;

@Named(value = "iController")
@ViewScoped
public class IntegranteController  implements FuncoesGenericas2<Integrante>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
				
	@Autowired
	private AMensagens              mensagem;
	
	@Inject
    private UsuarioController       uController;
	
	//variaveis	
	private @Setter @Getter Integrante                 integrante, integranteSelecionado;
	private @Setter @Getter Pessoa                     pessoa;
	
	private @Setter @Getter List<Municipio>            naturalidadesLista;
	private @Setter @Getter List<Provincia>            provinciasLista;
	private @Setter @Getter List<Area>                 areasLista;
	private @Setter @Getter List<AreaEspecifica>       areaEspecificasLista;
	private @Setter @Getter List<Carreira>             carreirasLista;
	private @Setter @Getter List<Categoria>            categoriasLista;
	private @Setter @Getter List<Especialidade>        especialidadesLista;
	private @Setter @Getter List<Funcao>               funcoesLista;
	private @Setter @Getter List<HabilitacaoLiteraria> habilitacoesLiteriariasLista;
	private @Setter @Getter List<Titulo>               titulosLista;
	
	private @Setter @Getter boolean                    modoNovo, modoEditar;
	
	private                 DAO                        dao;
	
	private                 Date                       dataRegisto;
	private @Setter @Getter java.util.Date             dataNascimento, dataAdmissao;
	private @Setter @Getter String                     idadeSTR, nomePessoaSTR;
	private                 String                     usuarioRegisto;
	
	private @Setter @Getter UploadedFile               fotoCarregada;
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.integrante                   = new Integrante();
  		this.integranteSelecionado        = new Integrante();
  		this.pessoa                       = new Pessoa();
  		
  		this.naturalidadesLista           = persistencia.munRepotory.func301FindAll_OrdenarPorNome();
  		this.provinciasLista              = persistencia.provRepository.func301FindAll_OrdenarPorNome();
  		this.areasLista                   = persistencia.aRepository.func301FindAll_OrdenarPorNome();
  		this.areaEspecificasLista         = persistencia.aeRepository.func301FindAll_OrdenarPorNome();
  		this.carreirasLista               = persistencia.carrRepository.func301FindAll_OrdenarPorNome();
  		this.categoriasLista              = persistencia.cRepository.func301FindAll_OrdenarPorId();
  		this.especialidadesLista          = persistencia.eRepository.func301FindAll_OrdenarPorNome();
  		this.funcoesLista                 = persistencia.fRepository.func301FindAll_OrdenarPorNome();
  		this.habilitacoesLiteriariasLista = persistencia.hlRepository.func301FindAll_OrdenarPorNome();
  		this.titulosLista                 = persistencia.tRepository.func302FindAll_PorSexoM();
  		
  		this.modoNovo                     = false;
  		this.modoEditar                   = false;
  		
  		this.dao                          = new DAO();
  		
  		this.dataRegisto                  = dao.func02DataActualStringEng2();
  		this.usuarioRegisto               = uController.getUsername();
  		this.idadeSTR                     = "-";
  		this.nomePessoaSTR                = "-";
  		
  	}
	
  	
  	
  	//funções auxiliares 2 *******************************************************
  	public void func001PegaProvincia(Municipio municipio) {
  		
  		//verifica municipio
  		if(!municipio.equals(null)) {
  			pessoa.setProvincia(municipio.getProvincia());
  		} 
  		
  	}
  	
  	
  	public java.sql.Date func002ConverterDateUtilparaDateSQL(java.util.Date dataUtil) {
  		
  		java.sql.Date resultado = null;
  		
  		if(dataUtil != null) {
  			System.out.println("----- dataUtil = "+dataUtil.toString());
  			resultado = UtilDatas.func04ConverteDateUtilparaDateSQL(dataUtil);
  		}
  		
  		return resultado;
  	}
  	
  	
  	public void func003ConverterDateUtilparaDateSQL(java.util.Date dataUtil) {
  		
  		if(dataUtil != null) {
  			
  			//variaveis
  			java.sql.Date dataSQL   = UtilDatas.func04ConverteDateUtilparaDateSQL(dataUtil);
  			
  			pessoa.setDtNascimento(dataSQL); 
  			  			
  			System.out.println("----- dataUtil = "+dataUtil.toString());
  			System.out.println("----- dataSQL  = "+dataSQL.toString());
  			System.out.println("----- Pessoa   = "+pessoa.toString());
  			
  		}
  		
  		/*if(dataUtil != null) {
  			
  			//variaveis
  			java.sql.Date dataSQL   = UtilDatas.func04ConverteDateUtilparaDateSQL(dataUtil);
  			Integer        idade    = dao.func10PegaDiferencaDatas(dataSQL);
  			String         idadeTXT = "";
  			
  			//verifica idade
  			if(idade > 0) {
  				
  				if(idade == 1) {
  					idadeTXT = idade+" ano";
  				} else if(idade > 1) {
  					idadeTXT = idade+" anos";
  				}
  				
  				setIdadeSTR(idadeTXT);
  				
  			} else {
  				setIdadeSTR("-");
  			}
  			
  			pessoa.setDtNascimento(dataSQL);   			  			
  			
  			System.out.println("----- dataUtil = "+dataUtil.toString());
  			System.out.println("----- dataSQL  = "+dataSQL.toString());
  			
  		} else if(dataUtil == null){
  			System.out.println("----- dataUtil nadaaaaaa ");
  			setIdadeSTR("-");
  		}*/
  		
  		
  	}
  	
  	
  	public String func004PegaIdadecomDateSQL(java.sql.Date dataSQL) {
  		
  		String resultado = "-";
  		
  		if(dataSQL != null) {
  			
  			//variaveis
  			java.sql.Date dtSQL     = UtilDatas.func04ConverteDateUtilparaDateSQL(dataSQL);
  			Integer        idade    = dao.func10PegaDiferencaDatas(dtSQL);
  			String         idadeTXT = "";
  			
  			//verifica idade
  			if(idade > 0) {
  				
  				if(idade == 1) {
  					idadeTXT = idade+" ano";
  				} else if(idade > 1) {
  					idadeTXT = idade+" anos";
  				}
  				
  				resultado = idadeTXT;
  				
  			} else {
  				resultado = "-";
  			}
  			
  		} 
  		
  		return resultado;
  		
  	}
  	
  	
  	
  	public String func005PegaIdadecomDateSQL(Pessoa pessoa) {
  		
  		String resultado = "-";
  		
  		if(pessoa != null) {
  			
  			//variaveis
  			//java.sql.Date dtSQL     = UtilDatas.func04ConverteDateUtilparaDateSQL(dataSQL);
  			java.sql.Date dtSQL     = pessoa.getDtNascimento();  			
  			String         idadeTXT = "";
  			
  			if(dtSQL != null) {
  				
  				Integer idade = dao.func10PegaDiferencaDatas(dtSQL);
  				
  				//verifica idade
  	  			if(idade > 0) {
  	  				
  	  				if(idade == 1) {
  	  					idadeTXT = idade+" ano";
  	  				} else if(idade > 1) {
  	  					idadeTXT = idade+" anos";
  	  				}
  	  				
  	  				resultado = idadeTXT;
  	  				
  	  			} else {
  	  				resultado = "-";
  	  			}
  				
  			} else {
  				resultado = "-";
  			}
  			
  			
  			
  		} 
  		
  		return resultado;
  		
  	}
  	
  	
  	
  	public void func006PegaNomePessoa(Pessoa pessoa) {
  		
  		if(pessoa != null) {
  			
  			if(pessoa.getNome() != null && pessoa.getNome() != "") {
  				setNomePessoaSTR(pessoa.getNome().trim());
  			} else {
  				setNomePessoaSTR("-");
  			}  			
  			
  		} else {
  			setNomePessoaSTR("-");
  		} 		
  		
  	}
  	
  	
  	public String func007PegaIdadecomDateUTIL(java.util.Date dataUTIL) {
  		
  		String resultado = "-";
  		
  		if(dataUTIL != null) {
  			
  			//variaveis
  			java.sql.Date dtSQL     = UtilDatas.func04ConverteDateUtilparaDateSQL(dataUTIL);
  			Integer        idade    = dao.func10PegaDiferencaDatas(dtSQL);
  			String         idadeTXT = "";
  			
  			//verifica idade
  			if(idade > 0) {
  				
  				if(idade == 1) {
  					idadeTXT = idade+" ano";
  				} else if(idade > 1) {
  					idadeTXT = idade+" anos";
  				}
  				
  				resultado = idadeTXT;
  				
  			} else {
  				resultado = "-";
  			}
  			
  		} 
  		
  		return resultado;
  		
  	}
  	
  	
  	public void func008OrganizaTitulos(String sexo) {
  		
  		setTitulosLista(persistencia.tRepository.func302FindAll_PorSexoM());
  		
  		//verificar sexo
  		if(sexo != null) {
  			
  			if(sexo.equals("F")) {
  				
  				System.out.println("----- sexo = "+sexo);
  	  			setTitulosLista(persistencia.tRepository.func304FindAll_PorSexoF());
  				
  			}  			
  			
  		}
  		
  	}
  	
  	
  	
  	public void func009OrganizaCategoria(Carreira carreira) {
  		
  		setCategoriasLista(persistencia.cRepository.func301FindAll_OrdenarPorId());
  		
  		if(carreira != null) {   			
  			setCategoriasLista(persistencia.cRepository.func303FindAll_PorIdOrdenarPorId(carreira.getId()));  			
  		}
  		
  		
  	}
  	
  	
  	
	
  	//funções auxiliares ********************************************************
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
		
		setIntegrante(new Integrante());
		setIntegranteSelecionado(new Integrante());
		setPessoa(new Pessoa());
		
		setNaturalidadesLista(persistencia.munRepotory.func301FindAll_OrdenarPorNome());
		setProvinciasLista(persistencia.provRepository.func301FindAll_OrdenarPorNome());
		setAreasLista(persistencia.aRepository.func301FindAll_OrdenarPorNome());
		setAreaEspecificasLista(persistencia.aeRepository.func301FindAll_OrdenarPorNome());
		setCarreirasLista(persistencia.carrRepository.func301FindAll_OrdenarPorNome());
		setCategoriasLista(persistencia.cRepository.func301FindAll_OrdenarPorId());
		setEspecialidadesLista(persistencia.eRepository.func301FindAll_OrdenarPorNome());
		setFuncoesLista(persistencia.fRepository.func301FindAll_OrdenarPorNome());
		setHabilitacoesLiteriariasLista(persistencia.hlRepository.func301FindAll_OrdenarPorNome());
		setTitulosLista(persistencia.tRepository.func302FindAll_PorSexoM());
		
		setDataNascimento(null);
		setDataAdmissao(null);
		setIdadeSTR("-");
		setNomePessoaSTR("-");
		setFotoCarregada(null);
				
		setModoNovo(false);
		setModoEditar(false);	
		
	}

	@Override
	public void selecionaRegistro() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);
		setModoEditar(false);
		
		setPessoa(
				
			new Pessoa(null, null, null, null, null, 
					   "M", null, null, null, null, 
					   null, null, null, null, null, 
					   null, dataRegisto, null, usuarioRegisto, null, 
					   null, null)
				
		);
						
		setIntegrante(
			
			/*new Integrante(null, null, null, 
					       null, null, null, 
					       null, null, null, 
					       null, null, null, 
					       null, null, dataRegisto, 
					       null, usuarioRegisto, null, 
					       null)*/
			
			new Integrante(null, pessoa, null, 
				           null, null, null, 
				           null, null, null, 
				           null, null, null, 
				           null, dataRegisto, null, 
				           usuarioRegisto, null, null)
			
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

	
	
	
	
	//funções executoras ********************************************************
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

}
