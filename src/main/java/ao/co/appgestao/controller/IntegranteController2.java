package ao.co.appgestao.controller;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
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
import ao.co.appgestao.model.Usuario;
import ao.co.appgestao.utilities.DAO;
import ao.co.appgestao.utilities.UtilDatas;
import lombok.Getter;
import lombok.Setter;

@Named(value = "iController2")
@ViewScoped
public class IntegranteController2 implements FuncoesGenericas2<Integrante>{

	// injecção de dependencias
	@Autowired
	private APersistenciaController    persistencia;
					
	@Autowired
	private AMensagens                 mensagem;
		
	@Inject
	private UsuarioController          uController;	
	
	@Inject 
	private HttpSession httpSession;
	
	
	//variaveis	
	private @Setter @Getter Integrante                 integrante, integranteSelecionado;
	private @Setter @Getter Pessoa                     pessoa;
	
	private @Setter @Getter List<Integrante>           integrantesLista, integrantesListaNotAdmin;
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
	private @Setter @Getter boolean                    modoNovoIndividual, modoNovoGrupo, modoNovoMultiplicacao;
		
	private                 DAO                        dao;
		
	private                 Date                       dataRegisto;
	private @Setter @Getter java.util.Date             dataNascimento, dataAdmissao;
	private @Setter @Getter String                     idadeSTR, nomePessoaSTR;
	private                 String                     usuarioRegisto;
	
	private @Setter @Getter Integer                    tipoDeNovo; 
	private @Setter @Getter Integer                    opcaoAltDel;
		
	private transient @Getter @Setter UploadedFile     fotoCarregada;
	private transient @Getter @Setter UploadedFile     uploadedFile, file;
	private @Setter @Getter Part                       fotoPorCarregar;
	private String                                     caminhoDirectorio;
	
	private @Setter @Getter Usuario                    usuario;
	
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.integrante                   = new Integrante();
  		this.integranteSelecionado        = new Integrante();
  		this.pessoa                       = new Pessoa();
  		
  		this.integrantesLista             = persistencia.iRepository.func301FindAll_OrdernarPorIdDESC();
  		this.integrantesListaNotAdmin     = persistencia.iRepository.func302FindAll_PorVisibilidadeN();
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
  		this.modoNovoIndividual           = false;
  		this.modoNovoGrupo                = false;  		
  		this.modoNovoMultiplicacao        = false;
  		
  		this.tipoDeNovo                   = null;
  		
  		this.opcaoAltDel                  = 1;  		
  		
  		this.dao                          = new DAO();
  		
  		this.dataRegisto                  = dao.func02DataActualStringEng2();
  		//this.usuarioRegisto               = uController.getUsername();
  		this.usuarioRegisto               = persistencia.func001PegarUsuarioNaSessao().getNomeUsuario().trim();
  		this.idadeSTR                     = "-";
  		this.nomePessoaSTR                = "-";
  		
  		this.caminhoDirectorio            = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/carregamentos");
  		
  		this.usuario                      = (Usuario) httpSession.getAttribute("usuarioLogado");
  		
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
  			resultado = UtilDatas.func04ConverteDateUtilparaDateSQL(dataUtil);
  		}
  		
  		return resultado;
  	}
  	
  	
  	
  	public java.util.Date func002ConverterDateSQLparaDateUtil(java.sql.Date dataSQL) {
  		
  		java.util.Date resultado = null;
  		
  		if(dataSQL != null) {
  			resultado = UtilDatas.func05ConverteDateSQLparaDateUtil(dataSQL);
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
  	
  	
  	
  	public void func010OrganizaTipoDeNovo(Integer tipoDeNovo) {
  		
  		//verifica tipoDeNovo
  		if(tipoDeNovo != null) {
  			
  			setIntegrante(new Integrante());
  			setIntegranteSelecionado(new Integrante());
  			setPessoa(new Pessoa());
  			
  			setDataNascimento(null);
  			setDataAdmissao(null);
  			setIdadeSTR("-");
  			setNomePessoaSTR("-");
  			setFotoCarregada(null);
  			  			
  			if(tipoDeNovo == 1) {
  	  			
  				setTipoDeNovo(1);
  				setModoNovoIndividual(true);
  				setModoNovoGrupo(false);
  				setModoNovoMultiplicacao(false);
  				
  	  		} else if(tipoDeNovo == 2) {
  	  			
  	  			setTipoDeNovo(2);
				setModoNovoIndividual(false);
				setModoNovoGrupo(true);
				setModoNovoMultiplicacao(false);
  	  			
  	  		} else if(tipoDeNovo == 3) {
  	  			
  	  			setTipoDeNovo(3);
				setModoNovoIndividual(false);
				setModoNovoGrupo(false);
				setModoNovoMultiplicacao(true);
  	  			
  	  		}
  			
  		}
  		
  	}
  	
  	
  	public boolean func011VerificaNulidadeEmPessoa(Pessoa pessoa) {
  		
  		boolean resultado = false;
  		
  		if((pessoa.getNome().equals(null) || pessoa.getNome().equals("")) ||
  		   (pessoa.getNumeroBilheteIdentidade().equals(null) || pessoa.getNumeroBilheteIdentidade().equals(""))) {
  			
  			resultado = true;
  			
  		}
  		  		
  		return resultado;
  		
  	}
  	
  	
  	public boolean func012VerificaRepeticaoEmNome(Pessoa pessoa) {
  		
  		boolean resultado = false;
  		int repecicoes    = persistencia.pRepository.func102Count_RegistrosRepetidosPorNome(pessoa.getNome());
  		
  		if(repecicoes != 0) {
  			resultado = true;
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	public boolean func013VerificaRepeticaoEmBI(Pessoa pessoa) {
  		
  		boolean resultado = false;
  		int repecicoes    = persistencia.pRepository.func103Count_RegistrosRepetidosPorBI(pessoa.getNumeroBilheteIdentidade());
  		
  		if(repecicoes != 0) {
  			resultado = true;
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	public void func014PegaListaIntegrantes(UsuarioController ucontroller){
  		
  		if(ucontroller != null) {
  			
  			if(ucontroller.getUsername().equals("admin")) {
  				setIntegrantesLista(persistencia.iRepository.func301FindAll_OrdernarPorIdDESC());
  			} else {
  				setIntegrantesLista(persistencia.iRepository.func302FindAll_PorVisibilidadeN());
  			}
  			
  		}
  		
  	}
  	
  	
  	
  	public List<Integrante> func015PegaListaIntegrantes(UsuarioController ucontroller){
  		
  		List<Integrante> resultado = persistencia.iRepository.func302FindAll_PorVisibilidadeN();
  		
  		if(ucontroller != null) {
  			
  			if(ucontroller.getUsername().equals("admin")) {
  				resultado = persistencia.iRepository.func301FindAll_OrdernarPorIdDESC();
  			} 
  			
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	public boolean func016visualizaParteAdm() {		
		
		boolean resultado = false;
		
		if(uController.getUsername() != null) {
			
			if(uController.getUsername().equals("admin")) {
				resultado = true;
			}
			
		}
		
		return resultado;		
	}
  	
  	
  	public String func017tirarNomeSobrenome(String nomeCompleto) {
  		
  		String resultado = null;
  		
  		//verifica nulidade de nomeCompleto
  		if(nomeCompleto != null) {
  			
  			//divide a string nomeCompleto em partes usando o espaço em branco como delimitador
  	        String[] partesNomeCompleto = nomeCompleto.split(" ");
  	        
  	        //verifica nulidade de partesNomeCompleto
  	        if(partesNomeCompleto.length >= 2) {
  	        	
  	        	String nome = partesNomeCompleto[0];
  	            String sobreNome = partesNomeCompleto[partesNomeCompleto.length - 1];
  	            
  	            resultado = nome+"."+sobreNome;
  	        	
  	        }
  			
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	
  	public String func018geraNovoNomeFicheiro() {
  		
  		String resultado = null;
  		
  		String nome  = func017tirarNomeSobrenome(pessoa.getNome().trim());
  		String numBI = pessoa.getNumeroBilheteIdentidade().trim();
  		
  		resultado    = nome+"_"+numBI.toUpperCase();
  		
  		return resultado;
  		
  	}
  	
  	
  	
  	public boolean func021verificaFotografiaCarregada(UploadedFile uploadedFile) {
  		
  		boolean resultado = false;
  		
  		if(uploadedFile.getSize() != 0) {
  			resultado = true;
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	public String func022caminhoFotografia(Integrante integrante) {  		
  		System.out.println("------------"+caminhoDirectorio+"\\"+integrante.getFotografia());
  		return caminhoDirectorio+"\\"+integrante.getFotografia();  		
  	}
  	
  	
  	public boolean func023verificaExistenciaFotografia(Integrante integrante) {
  		
  		boolean resultado = false;
  		
  		if(integrante != null) {
  			
  			if (integrante.getFotografia() != null && integrante.getFotografia() != "") {
  				resultado = true;
			}			
  			
  		}
  		//System.out.println("---- entrou na func023verificaExistenciaFotografia = "+resultado);
  		return resultado;
  		
  	} 
  	
  	
  	public boolean func024verificaPerfilUsuarioAdministrador() {
  		
  		boolean resultado = false;
  		
  		if(uController.verificaPerfilUsuarioAdm() == true) {
  			resultado = true;
  		}
  		
  		return resultado;
  		
  	}
  	
  	
  	
  	
  	
  	
  	
  	
	
	
	//funções auxiliares  ********************************************************
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
		
		setIntegrantesLista(persistencia.iRepository.func301FindAll_OrdernarPorIdDESC());
		setIntegrantesListaNotAdmin(persistencia.iRepository.func302FindAll_PorVisibilidadeN());
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
		setModoNovoIndividual(false);
		setModoNovoGrupo(false);
		setModoNovoMultiplicacao(false);
		
		setTipoDeNovo(null);
		
		setOpcaoAltDel(1);
		
		
	}
	
	

	@Override
	public void selecionaRegistro() {
		
		setModoNovo(false);		
		setModoEditar(true);
		
		setModoNovoIndividual(false);
		setModoNovoGrupo(false);
		setModoNovoMultiplicacao(false);
		
		setIntegrante(integranteSelecionado);
		setPessoa(integranteSelecionado.getPessoa());
		
		setDataNascimento(null);
		setDataAdmissao(null);
		
		setDataNascimento(func002ConverterDateSQLparaDateUtil(pessoa.getDtNascimento()));
		setDataAdmissao(func002ConverterDateSQLparaDateUtil(integrante.getDtAdmissao()));
		
	}
	
	
	public void selecionaRegistroFotografia(Integrante integrante) {
		
		setModoNovo(false);		
		setModoEditar(true);
		
		setModoNovoIndividual(false);
		setModoNovoGrupo(false);
		setModoNovoMultiplicacao(false);
		
		setIntegrante(integrante);
		setPessoa(integrante.getPessoa());
		
		setDataNascimento(null);
		setDataAdmissao(null);
		
		setDataNascimento(func002ConverterDateSQLparaDateUtil(integrante .getPessoa().getDtNascimento()));
		setDataAdmissao(func002ConverterDateSQLparaDateUtil(integrante.getDtAdmissao()));
		
		System.out.println("--- passou na função selecionaRegistroFotografia ");
		
	}

	@Override
	public void novoRegistro() {
		
		setModoNovo(true);		
		setModoEditar(false);
		
		setModoNovoIndividual(true);
		setModoNovoGrupo(false);
		setModoNovoMultiplicacao(false);
		
		setIntegrante(new Integrante());
		setIntegranteSelecionado(new Integrante());
		setPessoa(new Pessoa());
		
		setTipoDeNovo(1);
		setDataNascimento(null);
		setDataAdmissao(null);
		setIdadeSTR("-");
		setNomePessoaSTR("-");
		setFotoCarregada(null);
		
		setPessoa(
				
			new Pessoa(null, null, null, null, null, 
					   "M", "solteiro", null, null, null, 
					   null, null, null, null, null, 
					   null, dataRegisto, null, usuarioRegisto, null, 
					   null, "S")
				
		);
						
		setIntegrante(
			
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

	
	
	
	
	
	//funções executoras *******************************************************
	@Override
	public void salvar() {
		
		//variaveis
		Date dNascimento = dataNascimento != null ? UtilDatas.func03ConverteDateUtilparaDateSQL(dataNascimento) : null;
		Date dAdmissao   = dataAdmissao   != null ? UtilDatas.func03ConverteDateUtilparaDateSQL(dataAdmissao)   : null;
		
		//verifica nulidade de alguns campos em pessoa
		if(func011VerificaNulidadeEmPessoa(pessoa) == true) {
			
			mensagem.addError("Os campos: Nome e Bilhete de Identidade, não podem ser nulos.");
						
		} else {
			
			
			
			//pesquisar as repetições do Nome e Nº do BI
			boolean repeticoesNome  = func012VerificaRepeticaoEmNome(pessoa);
			boolean repeticoesNumBI = func013VerificaRepeticaoEmBI(pessoa);
			
			//verificar repeticoesNome e repeticoesNumBI
			if(repeticoesNome == true) {
				mensagem.addError("O nome '"+pessoa.getNome()+"' já existe no sistema.");
			} else if(repeticoesNumBI == true) {
				mensagem.addError("O Nº do BI '"+pessoa.getNumeroBilheteIdentidade()+"' já existe no sistema.");
			} else {
				
				//montar objecto pessoaIsert
				Pessoa pessoaInsert = new Pessoa(null, 
						                        pessoa.getNome().trim(), 
						                        pessoa.getFiliacao().equals(null)                      || pessoa.getFiliacao().equals("")                     ? null : pessoa.getFiliacao().trim(), 
						                        dNascimento, 
						                        pessoa.getNumeroBilheteIdentidade().trim(), 
						                        pessoa.getSexo().trim(), 
						                        pessoa.getEstadoCivil().trim(), 
						                        pessoa.getCorreioElectronico().equals(null)            || pessoa.getCorreioElectronico().equals("")            ? null : pessoa.getCorreioElectronico().trim(), 
						                        pessoa.getCorreioElectronicoAlternativo().equals(null) || pessoa.getCorreioElectronicoAlternativo().equals("") ? null : pessoa.getCorreioElectronicoAlternativo().trim(), 
						                        pessoa.getTelefone().equals(null)                      || pessoa.getTelefone().equals("")                      ? null : pessoa.getTelefone().trim(), 
						                        pessoa.getTelefoneAlternativo().equals(null)           || pessoa.getTelefoneAlternativo().equals("")           ? null : pessoa.getTelefoneAlternativo().trim(), 
						                        pessoa.getOutrosContactos().equals(null)               || pessoa.getOutrosContactos().equals("")               ? null : pessoa.getOutrosContactos().trim(), 
						                        pessoa.getNaturalidade(), 
						                        pessoa.getProvincia(), 
						                        pessoa.getResidencia().equals(null)                    || pessoa.getResidencia().equals("")                    ? null : pessoa.getResidencia().trim(), 
						                        pessoa.getObs().equals(null)                           || pessoa.getObs().equals("")                           ? null : pessoa.getObs().trim(), 
						                        dataRegisto, 
						                        null, 
						                        usuarioRegisto, 
						                        null, 
						                        null, 
						                        null); 
				
				//salvar na base se dados
				persistencia.pRepository.save(pessoaInsert);
				
				//pesquisar objecto pessoaPesq por nome e numero de BI
				Pessoa pessoaPesq = persistencia.pRepository.
						            func204FindOne_PessoaPorNomeEBI(pessoaInsert.getNome().trim(), 
						            		                        pessoaInsert.getNumeroBilheteIdentidade().trim());
				
				//verifica nulidade de pessoaPesq
				if(pessoaPesq != null) {
					
					//monta objecto integranteInsert
					Integrante integranteInsert = new Integrante(null, 
																 pessoaPesq, 
																 integrante.getMatricula().equals(null) || integrante.getMatricula().equals("") ? null : integrante.getMatricula().trim(), 
																 integrante.getNumeroAgente(), 
																 dAdmissao, 
																 integrante.getArea(), 
																 integrante.getAreaEspecifica(), 
																 integrante.getFuncao(), 
																 integrante.getCarreira(), 
																 integrante.getCategoria(), 
																 integrante.getEspecialidade(), 
																 integrante.getHabilitacaoLiteraria(), 
																 integrante.getTitulo(), 
																 dataRegisto, 
																 null, 
																 usuarioRegisto, 
																 null, 
																 null);
					
					//salvar na base de dados
					persistencia.iRepository.save(integranteInsert);
					
					//envia mensagem de confirmação
					mensagem.addInfo("Integrante '"+pessoaPesq.getNome()+"', foi inserido com sucesso.");
					
					//redefine a lista de integrantes
					setIntegrantesLista(persistencia.iRepository.func301FindAll_OrdernarPorIdDESC());
					setIntegrantesListaNotAdmin(persistencia.iRepository.func302FindAll_PorVisibilidadeN());
					//func015PegaListaIntegrantes(uController);
					
					//limpa o formulário
					cancelarGeral();				
					
				}
				
			}			
			
			
		}
		
		
		
	}
	
	
	
	public void salvarCarregamentoFotografia() {
  		
		System.out.println("--- entrou na função salvarCarregamentoFotografia");
		
  		if(uploadedFile != null) {
  			
  			if(uploadedFile.getSize() == 0) {	
  	  			
  		  		mensagem.addWarn("Deve selecionar o ficheiro para carregamento.");	
  		  		
  			} else {
  				
  				String nomeFicheiro      = uploadedFile.getFileName();
  				String extensaoFicheiro  = FilenameUtils.getExtension(nomeFicheiro);
  				
  				//String caminhoFicheiro   = caminhoDirectorio + nomeFicheiro;
  				String novoNomeFicheiro  = func018geraNovoNomeFicheiro()+"."+extensaoFicheiro;			
  				
  				
  				//preparar e fazer upload na pasta do servidor
  				try {
  					
  					//First, Generate file to make directories
  			  	  	String savedFileName    = caminhoDirectorio + "/" + nomeFicheiro;
  			  	  	String newSavedFileName = caminhoDirectorio + "/" + novoNomeFicheiro;
  			  	  	File fileToSave         = new File(savedFileName);
  			  	  	File fileToRename       = new File(newSavedFileName);
  			  	  	
  			  	  	//Rename fileToSave to fileToRename
  			  	  	fileToSave.renameTo(fileToRename);		  	  	  	  	
  			  	  	fileToSave.getParentFile().mkdirs();
  			  	  	fileToSave.delete();
  			  	  	
  			  	  	//Generate path file to copy file
  			  	  	Path folder         = Paths.get(caminhoDirectorio + "\\" +fileToRename.getName());
  					Path fileToSavePath = Files.createFile(folder);
  					
  					//Copy file to server
  				    InputStream input = uploadedFile.getInputStream();
  				    Files.copy(input, fileToSavePath, StandardCopyOption.REPLACE_EXISTING);
  				    
  				    
  				  //criar objecto integranteUpdate
  				  Integrante integranteUpdate = new Integrante(integrante.getId(), 
  							integrante.getPessoa(), 
  							integrante.getMatricula().equals(null) || integrante.getMatricula().equals("") ? null : integrante.getMatricula().trim(),
  							integrante.getNumeroAgente(), 
  							integrante.getDtAdmissao(), 
  							integrante.getArea(), 
  							integrante.getAreaEspecifica(), 
  							integrante.getFuncao(), 
  							integrante.getCarreira(), 
  							integrante.getCategoria(), 
  							integrante.getEspecialidade(), 
  							integrante.getHabilitacaoLiteraria(), 
  							integrante.getTitulo(), 
  							integrante.getDtRegistro(), 
  							dataRegisto, 
  							integrante.getUsuRegistro(), 
  							usuarioRegisto, 
  							novoNomeFicheiro);
  					
  					
  					//gravar na base de dados
  					persistencia.iRepository.save(integranteUpdate);
  					
  					//redefine o objecto integrante
  					//setIntegrante(persistencia.iRepository.func201FindOne_PorId(integranteUpdate.getId()));
  			  	  	
  					//limpa tudo
  					cancelarGeral();
  					
  					//repor tudo de novo
  					selecionaRegistroFotografia(integranteUpdate);
  					
  					//limpa a variável uploadedFile
  					setUploadedFile(null);
  					
  					
  				} catch (Exception e) {
  					System.out.println("####*** ERRO NO CARREGAMENTO DE FOTOGRAFIA = "+e.getMessage());
  				}
  				
  			}
  			
  		} else {
  			mensagem.addWarn("O ficheiro é nulo.");				
  			System.out.println("---- uploadedFile é nulo.");
  		}		
  		
  	}
	
	
	

	@Override
	public void editar() {
		System.out.println("---- entrou em Editar......");
		//variaveis
		Date dNascimento = dataNascimento != null ? UtilDatas.func03ConverteDateUtilparaDateSQL(dataNascimento) : null;
		Date dAdmissao   = dataAdmissao   != null ? UtilDatas.func03ConverteDateUtilparaDateSQL(dataAdmissao)   : null;
		
	}

	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	
	public void eliminarFotografia(Integrante integrante) {
		
		String pathForFileToDelete = caminhoDirectorio + "/" + integrante.getFotografia();
		File fileToDelete          = new File(pathForFileToDelete);
		
		//criar objecto integranteUpdate
		Integrante integranteDelete = new Integrante(integrante.getId(), 
					integrante.getPessoa(), 
					integrante.getMatricula(),
					integrante.getNumeroAgente(), 
					integrante.getDtAdmissao(), 
					integrante.getArea(), 
					integrante.getAreaEspecifica(), 
					integrante.getFuncao(), 
					integrante.getCarreira(), 
					integrante.getCategoria(), 
					integrante.getEspecialidade(), 
					integrante.getHabilitacaoLiteraria(), 
					integrante.getTitulo(), 
					integrante.getDtRegistro(), 
					dataRegisto, 
					integrante.getUsuRegistro(), 
					usuarioRegisto, 
					null);
			
		//eliminar fotografia na pasta
		fileToDelete.delete();		
		
		//gravar na base de dados
		persistencia.iRepository.save(integranteDelete);
		
		//limpa tudo
		cancelarGeral();
		
		//repor tudo de novo
		selecionaRegistroFotografia(integranteDelete);		
		
	}
	
	
	public void uploadFile() {
		
		//verifica nulidade de file
		if(uploadedFile != null) {
			
			System.out.println("*********** file name = "+uploadedFile.getFileName());			
			
		} else {
			
			System.out.println("************ file é nulo");
			
		}
		
	}
	
	public void uploadFile2(FileUploadEvent event) {
		
		UploadedFile uploadedFile = event.getFile();
				
		//verifica nulidade de file
		if(uploadedFile != null) {
			
			System.out.println("*********** file name = "+uploadedFile.getFileName());
			System.out.println("*********** file name = "+uploadedFile.getSize());
			
			String fileName            = uploadedFile.getFileName();	
			String extensao            = FilenameUtils.getExtension(fileName);	
			String pathNovo            = caminhoDirectorio;
			String nomeFicheiroDigital = integrante.getPessoa().getNome().trim();	
		  	String novoNomeFicheiro    = nomeFicheiroDigital+"."+extensao;
		  	
		  	System.out.println("*********** novoNomeFicheiro = "+novoNomeFicheiro);
		  	
		  //preparar objecto integranteUpdate
		  Integrante integranteUpdate = new Integrante(
				     integrante.getId(), 
				     integrante.getPessoa(), 
					 integrante.getMatricula(), 
					 integrante.getNumeroAgente(), 
					 integrante.getDtAdmissao(), 
					 integrante.getArea(), 
					 integrante.getAreaEspecifica(), 
					 integrante.getFuncao(), 
					 integrante.getCarreira(), 
					 integrante.getCategoria(), 
					 integrante.getEspecialidade(), 
					 integrante.getHabilitacaoLiteraria(), 
					 integrante.getTitulo(), 
					 integrante.getDtRegistro(), 
					 dataRegisto, 
					 integrante.getUsuRegistro(), 
					 usuarioRegisto, 
					 novoNomeFicheiro);
		  
		  System.out.println("*********** integranteUpdate = "+integranteUpdate.toString());
		  
		  //gravar na base de dados
		  persistencia.iRepository.save(integranteUpdate);
			
		  //limpa tudo
		  cancelarGeral();
			
		  //repor tudo de novo
		  selecionaRegistroFotografia(integranteUpdate);
			
		 
		  	
		  	
		  //preparar e fazer upload na pasta do servidor
		  	try {
				
		  		//First, Generate file to make directories
		  	  	String savedFileName    = pathNovo + "/" + fileName;
		  	  	String newSavedFileName = pathNovo + "/" + novoNomeFicheiro;
		  	  	File fileToSave         = new File(savedFileName);
		  	  	File fileToRename       = new File(newSavedFileName);
		  	  	
		  	  	//Rename fileToSave to fileToRename
		  	  	fileToSave.renameTo(fileToRename);		  	  	  	  	
		  	  	fileToSave.getParentFile().mkdirs();
		  	  	fileToSave.delete();
		  	  	
		  	  	//Generate path file to copy file
		  	  	Path folder         = Paths.get(pathNovo + "\\" +fileToRename.getName());
				Path fileToSavePath = Files.createFile(folder);
				
				//Copy file to server
			    //InputStream input = uploadedFile.getInputstream();
				InputStream input = uploadedFile.getInputStream();
			    Files.copy(input, fileToSavePath, StandardCopyOption.REPLACE_EXISTING);
		  		
			} catch (Exception e) {
				System.out.println("****** ERRO DE UPLOAD DA FOTOGRAFIA= "+e.getMessage());
			}
						
		} 
		
	}
	

}
