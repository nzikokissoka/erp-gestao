package ao.co.appgestao.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.genericsFuntions.FuncoesGenericas;
import ao.co.appgestao.model.Area;
import ao.co.appgestao.model.AreaEspecifica;
import ao.co.appgestao.model.Categoria;
import ao.co.appgestao.model.Especialidade;
import ao.co.appgestao.model.Funcao;
import ao.co.appgestao.model.Grupo;
import ao.co.appgestao.model.GrupoPrivilegio4;
import ao.co.appgestao.model.HabilitacaoLiteraria;
import ao.co.appgestao.model.Integrante;
import ao.co.appgestao.model.Menu4;
import ao.co.appgestao.model.ModuloDoSistema4;
import ao.co.appgestao.model.Titulo;
import ao.co.appgestao.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named(value = "uController")
@SessionScoped
public class UsuarioController implements Serializable, FuncoesGenericas<Usuario> {

	private static final long serialVersionUID = 5904645700840159862L;
	
	// injecção de dependencias
	@Autowired
	private APersistenciaController persistencia;
	
	@Inject 
	private HttpSession httpSession; 
	
	/*@Autowired 
	BCrypt bCrypt;*/
	
	@Autowired
	private AMensagens mensagem;
	
	//variaveis	
	//para autenticação
	private Map<String, Integer> loginAttempts    = new HashMap<>();
	private static final int MAX_LOGIN_ATTEMPTS   = 3;
    //private static final long LOCK_TIME_IN_MILLIS = TimeUnit.MINUTES.toMillis(5);
    
    private @Getter @Setter Usuario           usuario, usuario2;
	private @Getter @Setter Usuario           usuarioSelecionado, usuarioSelecionado2;
	private @Getter @Setter Integrante        integrante;
	
	private @Getter @Setter Funcao               funcao;
	private @Getter @Setter Area                 area;
	private @Getter @Setter AreaEspecifica       areaEspecifica;
	private @Getter @Setter Especialidade        especialidade;
	private @Getter @Setter HabilitacaoLiteraria habilitacaoLiteraria;
	private @Getter @Setter Titulo               titulo;
	private @Getter @Setter Categoria            categoria;
	
	private @Getter @Setter List<Usuario>              usuariosLista;
	private @Setter @Getter List<Area>                 areasLista;
	private @Setter @Getter List<Integrante>           integrantesLista, integranteUnicoLista;	
	private @Setter @Getter List<AreaEspecifica>       areaEspecificasLista;
	private @Setter @Getter List<Categoria>            categoriasLista;
	private @Setter @Getter List<Especialidade>        especialidadesLista;
	private @Setter @Getter List<Funcao>               funcoesLista;
	private @Setter @Getter List<HabilitacaoLiteraria> habilitacoesLiteriariasLista;
	private @Setter @Getter List<Titulo>               titulosLista;
	private @Setter @Getter List<Grupo>                gruposLista;
	
	private @Setter @Getter List<ModuloDoSistema4>     moduloDoSistema3Lista;
	
	
	private @Getter @Setter boolean           modoEditar;	
	private @Getter @Setter boolean           modoNovo;	
	private @Getter @Setter String            mensagemLogin;
	
	private @Getter @Setter String            username;
	private @Getter @Setter boolean           logado;
	
	
	private @Getter @Setter ModuloDoSistema4  moduloDoSistemaNoMenuUserAdm;
	private @Getter @Setter Menu4             moduloDoSistemaNoMenuUserOutros;
	
	
	
	//variaveis de conexão à bd
	private String databaseURL;
	private String user;
	private String password;
    
    
	//inicialização de variaveis ************************************************
  	@PostConstruct
  	public void init() {
  		
  		this.usuario             = new Usuario();
		this.usuarioSelecionado  = new Usuario();
		this.usuario2            = new Usuario();
		this.usuarioSelecionado2 = new Usuario();
		this.integrante          = new Integrante();
		
		this.funcao               = new Funcao();
		this.area                 = new Area();
		this.areaEspecifica       = new AreaEspecifica();
		this.especialidade        = new Especialidade();
		this.habilitacaoLiteraria = new HabilitacaoLiteraria();
		this.categoria            = new Categoria();
		this.titulo               = new Titulo(); 
		
		//this.integranteUnicoLista = null; 
		
		//this.usuariosLista      = persistencia.uRepository.findAll(); 
		//this.usuariosLista      = func020PegaListaUsuarioPorNOme(persistencia.func001PegarUsuarioNaSessao());
		//this.usuariosLista      = func020PegaListaUsuarioPorNOme((Usuario) httpSession.getAttribute("usuarioLogado"));
		this.areasLista                   = persistencia.aRepository.func301FindAll_OrdenarPorNome();		
		this.areaEspecificasLista         = persistencia.aeRepository.func301FindAll_OrdenarPorNome();
  		this.categoriasLista              = persistencia.cRepository.func301FindAll_OrdenarPorId();
  		this.especialidadesLista          = persistencia.eRepository.func301FindAll_OrdenarPorNome();
  		this.funcoesLista                 = persistencia.fRepository.func301FindAll_OrdenarPorNome();
  		this.habilitacoesLiteriariasLista = persistencia.hlRepository.func301FindAll_OrdenarPorNome();
  		this.titulosLista                 = persistencia.tRepository.func305FindAll();
  		this.gruposLista                  = persistencia.gRepository.func302FindAll_OrdenarPorNome();
		
		this.modoEditar         = false;
		this.modoNovo           = false;
		this.mensagemLogin      = "";
		
		this.username           = null;
		this.logado             = false;
		
		//this.externalContext    = FacesContext.getCurrentInstance().getExternalContext();
		//this.sessionMap         = externalContext.getSessionMap();
		
		this.moduloDoSistema3Lista = new ArrayList<>();
		
		this.moduloDoSistemaNoMenuUserAdm    = null;
		this.moduloDoSistemaNoMenuUserOutros = null;
		
		//variaveis de conexão à bd
		this.databaseURL = "jdbc:sqlserver://localhost;instance=MSSQLSERVER;databaseName=dbsistemas;encrypt=false";
		this.user        = "sa";
		this.password    = "admin2k4";
  		
  	}

  	//funçoes auxiliares ************************************************
  	public void cancelarGeral() {
  		
  		setModoNovo(false);
  		setModoEditar(false);
  		
  		setUsuario(new Usuario());
  		setUsuarioSelecionado(new Usuario());
  		
  		setUsuario2(new Usuario());
  		setUsuarioSelecionado2(new Usuario());
  		setFuncao(new Funcao());
  		setTitulo(new Titulo());
  		setArea(new Area());
  		setAreaEspecifica(new AreaEspecifica());
  		setHabilitacaoLiteraria(new HabilitacaoLiteraria());
  		setEspecialidade(new Especialidade());
  		setCategoria(new Categoria());
  		setIntegranteUnicoLista(null);
  		
  		Usuario usu = (Usuario) httpSession.getAttribute("usuarioLogado");
  		setUsuariosLista(func021PegaListaUsuarioPorNomeUsuario(usu.getNomeUsuario().trim()));
  		
  	}
  	
  	
  	public void novoRegistro() {
  		
  		setModoNovo(true);
  		setModoEditar(false);
  		
  		setUsuario2(new Usuario(null, null, null, null, null, null, "S", 1));
  		
  	}
  	
  	
  	
  	@Override
	public void novaEntidade() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void cancelar() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void selecionaRegistro() {
		
		setModoNovo(false);
  		setModoEditar(true);
  		
  		setUsuario2(usuarioSelecionado2);
  		setIntegranteUnicoLista(persistencia.iRepository.func304FindAll_PorId(usuarioSelecionado2.getIntegrante().getId()));
		
	}
	
	
	@Override
	public void ativaModoEditar() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void desAtivaModoEditar() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	//funçoes auxiliares2 ************************************************
	public boolean isAccountLocked(String nomeUsuario) {
        
		Integer attempts = loginAttempts.get(nomeUsuario);
		
        return attempts != null && attempts >= MAX_LOGIN_ATTEMPTS;
        
    }
	
	public boolean authenticate(String nomeUsuario, String senha) {
		
		boolean resultado = false;
		
		setLogado(false);
		
		//verifica nulidade de nomeUsuario
		if(nomeUsuario != null && nomeUsuario != "") {
			
			//verificação da conta: nomeUsuario
				
				//pega o usuario pelo nomeUsuario
				Usuario usuarioPesq = persistencia.uRepository.func201FindOne_PorUsuarioNome(nomeUsuario);
				
				//verifica a existencia de usuarioPesq
				if(usuarioPesq == null) {
					
					this.mensagemLogin = "Usuário "+nomeUsuario+" não foi encontrado no sistema.";
					
				} else {
					
					//verifica se a conta nomeUsuario esta bloqueada
					if (isAccountLocked(nomeUsuario)) {
			            
						//this.mensagemLogin = "A conta está bloqueada #{nomeUsuario}. Tente novamente mais tarde.";
						this.mensagemLogin = "A conta "+nomeUsuario+", está bloqueada. Tente novamente mais tarde.";
			            
			        } else {
			        	
			        	//verificação da senha fornecida do usuario nomeUsuario
			        		
			        		//verifica se a senha fornecida corresponde à senha encriptada
			        		boolean senhaConfirmada = BCrypt.checkpw(senha, usuarioPesq.getSenha());
			        		
			        		if (senhaConfirmada == true) {
			        					        			
			        			resultado          = true;
			        			this.mensagemLogin = "";
			        			loginAttempts.remove(nomeUsuario);
			        			
			        			setUsername(nomeUsuario);
			        			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", username);
			        			
			        			setLogado(true);		        			
			        			
			        			
			        		} else {
			        			
			        			setUsername(null);
			        			
			        			//incrementa o contador de tentativas de login
			        			int attempts = loginAttempts.getOrDefault(nomeUsuario, 0);
			                    loginAttempts.put(nomeUsuario, attempts + 1);
			                    
			                    if (isAccountLocked(nomeUsuario)) {
			                    	
			                    	this.mensagemLogin = "A conta "+nomeUsuario+", foi bloqueada após várias tentativas malsucedidas. Tente novamente mais tarde.";
			                    	
			                    } else {
			                    	
			                    	this.mensagemLogin = "Usuário ou senha inválidos.";
			                    			                    	
			                    }
			        			
			        		}
			        	
			        	//fim verificação da senha fornecida do usuario nomeUsuario
			        	
			        	
			        }
					
					
				}
				
				
			//fim verificação da conta: nomeUsuario
			
		} 
		//fim verifica nulidade de nomeUsuario
		
		
		return resultado;		
		
	}
	
	public void reencaminhaPaginaHome() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
				
		navigationHandler.handleNavigation(context, null, "/home.xhtml?faces-redirect=true");
				
	}
	
	
	public void reencaminhaPaginaHome2(String usuarioNome) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
				
		navigationHandler.handleNavigation(context, null, "/home.xhtml?faces-redirect=true&usuario=#{usuarioNome}");
		
	}
	
	
	public void reencaminhaPaginaIndex() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
				
		navigationHandler.handleNavigation(context, null, "/index.xhtml?faces-redirect=true");
		
	}
	
	
	public String descricaoUsuario(Usuario usuario) {
		
		String resultado = "";
		
		//verifica existencia de usuario
		if(usuario.equals(null)) {
			
			Integrante integrante = persistencia.uRepository.func201FindOne_PorUsuarioNome(usuario.getNomeUsuario()).getIntegrante();
			
			//verifica existencia de integrante
			if(integrante != null) {
				
				String titulo = (integrante.getTitulo() == null ? "" : integrante.getTitulo()+" ");
				String nome   = integrante.getPessoa().getNome().trim();
				String funcao = (integrante.getFuncao() == null ? "" : " | "+integrante.getFuncao().getNome());
				
				resultado     = "Olá "+titulo+nome+funcao;
				System.out.println(" ---- integrante != null");
				
			} else {
				
				resultado     = "Olá ";
				System.out.println(" ---- integrante == null");
				
			}
			
			
		}
		
		return resultado;
		
	}
	
	
	public boolean verificaSessaoUsuario() {
		
		boolean resultado = false;
		
		//retirar os dados do usuário da sessão 1
		ExternalContext eContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sMap = eContext.getSessionMap();
		Usuario usuarioNaSessao  = (Usuario) sMap.get("usuarioLogado");
				
		if(usuarioNaSessao != null && usuarioNaSessao.getId() != null) {
			System.out.println("------ usuarioNaSessao = "+usuarioNaSessao.toString());
			resultado = true;
		}
		
		return resultado;
		
	}
	
	
	public boolean verificaUsuarioNaSessao() {
		
		boolean resultado = false;
		
		//retirar os dados do usuário da sessão 
		Usuario usuarioNaSessao = persistencia.func001PegarUsuarioNaSessao();
		
		if(usuarioNaSessao != null && usuarioNaSessao.getId() != null) {
			//System.out.println("------ usuarioNaSessao 2 = "+usuarioNaSessao.toString());
			resultado = true;
		}
		
		
		return resultado;
		
	}
	
	
	
	public boolean verificaPerfilUsuarioAdm() {
		
		boolean resultado = false;
		
		//retirar os dados do usuário da sessão 
		Usuario usuarioNaSessao = persistencia.func001PegarUsuarioNaSessao();
		
		if(usuarioNaSessao != null && usuarioNaSessao.getNomeUsuario().equals("admin")) {
			resultado = true;
		}
		
		return resultado;
		
	}
	
	
	
	public Usuario pegaUsuarioLogado() {
		
		Usuario resultado = null;
		
		//retirar os dados do usuário da sessão 
		Usuario usuarioNaSessao = persistencia.func001PegarUsuarioNaSessao();
		
		if(usuarioNaSessao != null && usuarioNaSessao.getId() != null) {
			resultado = usuarioNaSessao;
		}
		
		return resultado;
		
	}
	
	
	
	public String pegarNomeclaturaCompletaUsuario() {
		
		String resultado      = "";		
		Integrante integrante = persistencia.func001PegarUsuarioNaSessao().getIntegrante();
		
		Date date                   = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar tempoAgora         = Calendar.getInstance();
		String hora                 = String.valueOf(tempoAgora.get(Calendar.HOUR_OF_DAY));
		String minutos              = String.valueOf(tempoAgora.get(Calendar.MINUTE));
		//String log                  = " | Log: "+formatador.format(date)+" "+func011PegaDiaDaSemana();
		String log                  = " | Log: "+formatador.format(date)+" "+hora+":"+minutos+" "+
		                              func011PegaDiaDaSemana();
		
		//verifica existencia de integrante
		if(integrante != null) {
			
			String titulo = (integrante.getTitulo() == null ? "" : integrante.getTitulo().getAbreviada()+" ");
			String nome   = integrante.getPessoa().getNome().trim();
			String funcao = (integrante.getFuncao() == null ? "" : " | "+integrante.getFuncao().getNome());
			
			resultado     = "Olá "+titulo+nome+funcao+log;			
			
		} else {
			
			resultado     = "Olá "+log;
						
		}
		
		
		return resultado;
	}
	
	
	public String func011PegaDiaDaSemana() {
		 
		String str = "";
		int diaSemana = Calendar.getInstance().get(Calendar.DAY_OF_WEEK); 
		
		if(diaSemana == 1) {
			str = "Domingo";
		} else if(diaSemana == 2) {
			str = "Segunda-feira";
		} else if(diaSemana == 3) {
			str = "Terça-feira";
		} else if(diaSemana == 4) {
			str = "Quarta-feira";
		} else if(diaSemana == 5) {
			str = "Quinta-feira";
		} else if(diaSemana == 6) {
			str = "Sexta-feira";
		} else if(diaSemana == 7) {
			str = "Sabado";
		} 
				
		return str;
	}
	
	
	
	
	
	
	
	
	//funçoes executoras *************************************************
	public void login() {
		
		//pega a autenticação
		boolean autenticacao = authenticate(usuario.getNomeUsuario().trim(), 
				                            usuario.getSenha().trim());
		
		//verifica login com a função authenticate
		if(autenticacao == false) {
			
			//limpa o objecto usuario
			setUsuario(new Usuario());	
						
		} else if(autenticacao == true && logado == true){
			
			//pesquisar usuário
			Usuario usuarioNaSessao = persistencia.uRepository.func201FindOne_PorUsuarioNome(usuario.getNomeUsuario().trim());
			
			if(usuarioNaSessao != null) {
				
				//incluir os dados do usuário na sessão
				/*ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		        Map<String, Object> sessionMap  = externalContext.getSessionMap();
				sessionMap.put("usuarioLogado", usuarioNaSessao);*/
				
				httpSession.setAttribute("usuarioLogado", usuarioNaSessao);
				
				//monta a lista de usuários
				//setUsuariosLista(func020PegaListaUsuarioPorNOme(usuarioNaSessao));
				setUsuariosLista(func021PegaListaUsuarioPorNomeUsuario(usuario.getNomeUsuario().trim()));
				
				setModuloDoSistema3Lista(func36PegaItensMenuAdm(3, null));
				
			}		
			
			
			//limpa a variavel mensagemLogin
			setMensagemLogin("");
			
			//vai para pagina home.xhmtl
			reencaminhaPaginaHome();		
			
		}
		
	}
	
	
	
	public void logout() {
		
		setLogado(false);
		
		if(logado == false) {
			
			//limpa o objecto usuario
			setUsuario(new Usuario());	
			
			//vai para pagina index.xhmtl
			reencaminhaPaginaIndex();
			
		}		
				
	}
	
	
	
	public void logout2() throws IOException {
		
		setLogado(false);
		
		if(logado == false) {
			
			System.out.println("------ entrou no logout2()");
			
			setUsuario(new Usuario());	
			
			//limpa a lista de usuários
			setUsuariosLista(null);
			
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
			
		}
		
	}
	
	
	public void logout3() {
		
		setLogado(true);
		setUsername(null);
		
		//eliminar os dados do usuário na sessão
		persistencia.func002RemoverUsuarioNaSessao();
		
		//voltar para a pagina index
		reencaminhaPaginaIndex();
		
	}
	
	
	public List<Usuario> func020PegaListaUsuarioPorNOme(Usuario usuario){
		
		List<Usuario> resultado = persistencia.uRepository.func303FindOne_PorVisibilidade_OrderById("S");
		
		//verifica nulidade de usuario
		if(usuario != null && usuario.getNomeUsuario().trim() == "admin") {
			resultado = persistencia.uRepository.func302FindOne_OrderById();
			System.out.println("------ usuario = "+usuario.toString());
		}	
		System.out.println("------ resultado size = "+resultado.size());
		return resultado;
		
	}
	
	
	public List<Usuario> func021PegaListaUsuarioPorNomeUsuario(String nomeUsuario){
		
		List<Usuario> resultado = persistencia.uRepository.func303FindOne_PorVisibilidade_OrderById("S");
		
		//verifica nulidade de usuario
		if(nomeUsuario != null && nomeUsuario != "") {
			
			if(nomeUsuario.equals("admin")) {
				resultado = persistencia.uRepository.func302FindOne_OrderById();				
			}
			
		}		
		
		return resultado;
		
	}
	
	
	public List<Grupo> func022PegaGruposLista(){
		
		List<Grupo> resultado = persistencia.gRepository.func304FindAll_ExceptoAdm();
		
		//verifica se perfil Adm esta on
		if(verificaPerfilUsuarioAdm() == true) {
			resultado = persistencia.gRepository.func302FindAll_OrdenarPorNome();
		}
		
		return resultado;
		
	}
	
	
	
	public List<Integrante> func023PegaListaIntegrantes(Funcao funcao, 
											            Titulo titulo, 
											            Area area, 
											            AreaEspecifica areaEspecifica, 
											            HabilitacaoLiteraria habilitacaoLiteraria,
											            Especialidade especialidade, 
											            Categoria categoria) {
		List<Integrante> resultado = null;
		List<Integrante> lista     = new ArrayList<>();
		
		String funcaoPesq               = "";
		String tituloPesq               = "";
		String areaPesq                 = "";
		String areaEspecificaPesq       = ""; 
		String habilitacaoLiterariaPesq = "";
		String especialidadePesq        = ""; 
		String categoriaPesq            = "";
		String SELECT                   = "";
		
		if(funcao != null && funcao.getId() != null) {
			funcaoPesq = " AND i.funcao_id = "+funcao.getId();			
		}
		
		if(titulo != null && titulo.getId() != null) {
			tituloPesq = " AND i.titulo_id = "+titulo.getId();
		}
		
		if(area != null && area.getId() != null) {
			areaPesq = " AND i.area_id = "+area.getId();
		}
		
		if(areaEspecifica != null && areaEspecifica.getId() != null) {
			areaEspecificaPesq = " AND i.area_especifica_id = "+areaEspecifica.getId();
		}
		
		if(habilitacaoLiteraria != null && habilitacaoLiteraria.getId() != null) {
			habilitacaoLiterariaPesq = " AND i.habilitacao_literaria_id = "+habilitacaoLiteraria.getId();
		}
		
		if(especialidade != null && especialidade.getId() != null) {
			especialidadePesq = " AND i.especialidade_id = "+especialidade.getId();
		}
		
		if(categoria != null && categoria.getId() != null) {
			categoriaPesq = " AND i.categoria_id = "+categoria.getId();
		}		
		
		try {
			
			Connection connection = DriverManager.getConnection(databaseURL, user, password);
			
			SELECT = "SELECT i.* "  
				   + "FROM tb_integrantes i, tb_pessoas p  "
				   + "WHERE i.pessoa_id = p.id AND i.id not in (select id from tb_usuarios) "
				   + funcaoPesq+tituloPesq+areaPesq+areaEspecificaPesq
				   + habilitacaoLiterariaPesq+especialidadePesq+categoriaPesq
				   + " ORDER BY p.nome ";
			System.out.println(SELECT);
			
			Statement statement = connection.createStatement();
			ResultSet result    = statement.executeQuery(SELECT);
			
			while (result.next()) {
				
				Integer id = result.getInt("id");				
				Integrante integranteWhile = new Integrante(id, null, null, null, null, null, 
						                                    null, null, null, null, null, null, null, null, null, 
						                                    null, null, null);
				lista.add(integranteWhile);
				
			}
			
			resultado = lista;			
			connection.close();
			
			
		} catch (Exception e) {
			System.out.println("--- ERRO NA DAO SELECT func023PegaListaIntegrantes = "+e.getMessage()); 
		}		
		
		return resultado;
	
	}
	
	
	
	public List<Integrante> func024PegaIntegrantesLista(Funcao funcao, 
			                                            Titulo titulo, 
			                                            Area area, 
			                                            AreaEspecifica areaEspecifica, 
			                                            HabilitacaoLiteraria habilitacaoLiteraria,
			                                            Especialidade especialidade, 
			                                            Categoria categoria){
		 
		//variaveis
		List<Integrante> resultado = null;
		List<Integrante> lista     = new ArrayList<>();
		
		List<Integrante> integranteListaPesq = func023PegaListaIntegrantes(funcao, titulo, area, areaEspecifica, 
													                       habilitacaoLiteraria, especialidade, 
													                       categoria);
		
		//varrer a lista
		for(int i = 0; i < integranteListaPesq.size(); i++) {
			
			Integrante integrante = persistencia.iRepository.func201FindOne_PorId(integranteListaPesq.get(i).getId());
			lista.add(integrante);
			
		}
		
		resultado = lista;
		
		return resultado;
		
	}
	
	
	
	public boolean func025VerificaNulidadeCamposUsuario(Usuario usuario2){
		
		boolean resultado     = false;
		Integrante integrante = usuario2.getIntegrante();
		String nomeUsuario    = usuario2.getNomeUsuario();
		String senha          = usuario2.getSenha();
		String perfil         = usuario2.getPerfil();
		Grupo grupo           = usuario2.getGrupo();
		
		/*System.out.println("---- Integrante  = "+integrante);
		System.out.println("---- nomeUsuario = "+nomeUsuario);
		System.out.println("---- senha       = "+senha);
		System.out.println("---- perfil      = "+perfil);
		System.out.println("---- grupo       = "+grupo);*/
		
		//verifica a nulidade das variaveis
		if(integrante   == null || 
		   (nomeUsuario == null || nomeUsuario == "") || 
		   (senha       == null || senha       == "") || 
		   (perfil      == null || perfil      == "") || 
		   grupo        == null) {
			resultado = true;
		}		
		
		return resultado;
		
	}
	
	
	public boolean func026VerificaIgualdadeSenha(Usuario usuario2, String senhaFormulario){
		
		boolean resultado = false;
		
		//verifica se senhaFormulario e senha gravada na base de dados, são iguais
		if(senhaFormulario.equals(usuario2.getSenha().trim())) {
			resultado = true;
		}
		
		return resultado;
	}
	
	
	public String func027ActualizaSenha(Usuario usuario2, String senhaFormulario){
		
		String resultado = null;
		
		//verifica se senhaFormulario e senha gravada na base de dados, são iguais
		if(func026VerificaIgualdadeSenha(usuario2, senhaFormulario) == true) {
			resultado = usuario2.getSenha().trim();
		} else {	
			resultado = new Usuario().senhaEncriptada(senhaFormulario);
		}
		
		return resultado;
	}
	
	
	
	public GrupoPrivilegio4 func028PegaObjectoGrupoPrivilegioTry(Usuario usuario, String descricao) {
		
		//variaveis
		GrupoPrivilegio4 resultado = null;
		GrupoPrivilegio4 gp        = null;
		
		//System.out.println("--- usuario logado = "+pegaUsuarioLogado());
		
		//verifica a nulidade dos parametros
		//if(usuario.getNomeUsuario().length() != 0 && descricao.length() != 0) {
		if(pegaUsuarioLogado().getNomeUsuario().length() != 0 && descricao.length() != 0) {
			
			//String nomeUsuario       = usuario.getNomeUsuario().trim();
			String nomeUsuario       = pegaUsuarioLogado().getNomeUsuario().trim();
			String descricaoItemMenu = descricao.trim();
			
			String SELECT = "SELECT gp.* "
					      + "FROM tb_grupos_de_privilegios4 gp, tb_modulos_do_sistema4 ms "
					      + "WHERE gp.modulo_do_sistema_id = ms.id and "
					      + "      gp.grupo_id = (select grupo_id from tb_usuarios where nome_usuario = '"+nomeUsuario+"') and "
					      + "      ms.descricao = '"+descricaoItemMenu+"'";
			
			/*String SELECT = "SELECT gp.* "
					      + "FROM tb_grupos_de_privilegios2 gp, tb_modulos_do_sistema2 ms "
					      + "WHERE gp.modulo_do_sistema_id = ms.id and "
					      + "      gp.grupo_id = (select grupo_id from tb_usuarios where nome_usuario = '"+nomeUsuario+"') and "
					      + "      ms.descricao = '"+descricaoItemMenu+"'";*/
			
			
			try {
				
				Connection connection = DriverManager.getConnection(databaseURL, user, password);				
				Statement statement = connection.createStatement();
				ResultSet result    = statement.executeQuery(SELECT);
				
				while (result.next()) {					
					Integer id = result.getInt("id");	
					gp         = new GrupoPrivilegio4(id, null, null, null, null, null, null, null, null, null, null, null);					
				}
				
				resultado = gp;			
				connection.close();
				
				
			} catch (Exception e) {
				System.out.println("--- ERRO NA DAO SELECT func028PegaObjectoGrupoPrivilegio = "+e.getMessage()); 
			}
			
			
		}
		
		
		return resultado;		
		
	}
	
	
	public GrupoPrivilegio4 func029PegaObjectoGrupoPrivilegio(Usuario usuario, String descricao) {
		
		//variaveis
		GrupoPrivilegio4 resultado = null;
		GrupoPrivilegio4 gp        = func028PegaObjectoGrupoPrivilegioTry(usuario, descricao);
		
		//verifica a nulidade de gp
		if(gp != null && gp.getId() != null) {			
			resultado = persistencia.gpRepository4.findOne(gp.getId());			
		}			
		
		return resultado;
		
	}
	
	
	/*public GrupoPrivilegio2 func029PegaObjectoGrupoPrivilegio(Usuario usuario, String descricao) {
		
		//variaveis
		GrupoPrivilegio2 resultado = null;
		GrupoPrivilegio2 gp        = func028PegaObjectoGrupoPrivilegioTry(usuario, descricao);
		
		//verifica a nulidade de gp
		if(gp != null && gp.getId() != null) {			
			resultado = persistencia.gpRepository2.findOne(gp.getId());			
		}			
		
		return resultado;
		
	}*/
	
	
	public boolean func030verificaExistenciaItem(Usuario usuario, String descricao) {
		
		//variaveis
		boolean resultado   = false;
		GrupoPrivilegio4 gp = func029PegaObjectoGrupoPrivilegio(usuario, descricao);
		
		//verifica a nulidade dos parametros
		if(gp != null && gp.getId() != null) {
			resultado = true;
		}
		
		return resultado;
		
	}
	
	
	
	
	public boolean func031verificaPermissaoItem(Usuario usuario, String descricao, String operacao) {
		
		boolean resultado   = false;
		GrupoPrivilegio4 gp = func029PegaObjectoGrupoPrivilegio(usuario, descricao);
		
		if(operacao.equals("novo") && gp.getNovo() == 1) {
			resultado = true;
		}
		
		if(operacao.equals("salvar") && gp.getSalvar() == 1) {
			resultado = true;
		}
		
		if(operacao.equals("editar") && gp.getEditar() == 1) {
			resultado = true;
		}
		
		if(operacao.equals("deletar") && gp.getDeletar() == 1) {
			resultado = true;
		}
		
		if(operacao.equals("pesquisar") && gp.getPesquisar() == 1) {
			resultado = true;
		}		
		
		return resultado;
		
	}
	
	
	
	public boolean func032VerExistenciaItem(String descricao) {
		
		//variaveis
		boolean resultado       = false;
		Usuario usuarioNaSessao = persistencia.func001PegarUsuarioNaSessao();
				
		//verifica nulidade de usuarioNaSessao
		if(usuarioNaSessao != null && usuarioNaSessao.getId() != null) {
			
			GrupoPrivilegio4 gp = func029PegaObjectoGrupoPrivilegio(usuarioNaSessao, descricao);
			
			//verifica a nulidade dos parametros
			if(gp != null && gp.getId() != null) {
				resultado = true;
			}
			
		}
		System.out.println("---- resultado = "+resultado);
		return resultado;
		
	}
	
	
	
	public boolean func033VerPermissaoItem(String descricao, String operacao) {
		
		boolean resultado       = false;
		Usuario usuarioNaSessao = persistencia.func001PegarUsuarioNaSessao();
		
		//verifica nulidade de usuarioNaSessao
		if(usuarioNaSessao != null && usuarioNaSessao.getId() != null) {
			
			GrupoPrivilegio4 gp     = func029PegaObjectoGrupoPrivilegio(usuarioNaSessao, descricao);
			
			if(operacao.equals("novo") && gp.getNovo() == 1) {
				resultado = true;
			}
			
			if(operacao.equals("salvar") && gp.getSalvar() == 1) {
				resultado = true;
			}
			
			if(operacao.equals("editar") && gp.getEditar() == 1) {
				resultado = true;
			}
			
			if(operacao.equals("deletar") && gp.getDeletar() == 1) {
				resultado = true;
			}
			
			if(operacao.equals("pesquisar") && gp.getPesquisar() == 1) {
				resultado = true;
			}
			
		}				
		
		return resultado;
		
	}
	
	
	
	
	public List<ModuloDoSistema4> func34PegaIdMenuAdm(int codigoLen, String codigoPai) {
    	
		List<ModuloDoSistema4> resultado = null;
		List<ModuloDoSistema4> lista     = new ArrayList<>();
		String RESULTADO_SELECT = "";
    	String SELECT = "SELECT id  "
    			      + "FROM tb_modulos_do_sistema4 "; 
    	String WHERE = "";
    	    	
    	//verifica codigoLen
    	if(codigoLen == 3) {    		
    		WHERE = "WHERE LEN(codigo) = 3 and codigo_pai is null ";    		
    	} else if(codigoLen == 6) {    		
    		WHERE = "WHERE LEN(codigo) = 6 and codigo_pai = '"+codigoPai+"' ";    		
    	} else if(codigoLen == 9) {    		
    		WHERE = "WHERE LEN(codigo) = 9 and codigo_pai = '"+codigoPai+"' ";    		
    	} else if(codigoLen == 12) {    		
    		WHERE = "WHERE LEN(codigo) = 12 and codigo_pai = '"+codigoPai+"' ";    		
    	} else if(codigoLen == 15) {    		
    		WHERE = "WHERE LEN(codigo) = 15 and codigo_pai = '"+codigoPai+"' ";    		
    	}
    			      
    	RESULTADO_SELECT = SELECT +	WHERE;       
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(RESULTADO_SELECT);
    		
    		while (result.next()) {
    			
    			ModuloDoSistema4 moduloDoSistema = new ModuloDoSistema4(result.getInt("id"), null, null, null, null, null);
    			lista.add(moduloDoSistema);
    			
    		}
    		
    		resultado = lista;
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NO SELECT func34PegaIdMenuAdm = "+e.getMessage());
		}    	
    	
    	return resultado;
    	
    }
	
	
	public List<Menu4> func35PegaIdMenuUsuario(int codigoLen, String codigoPai, Grupo grupo) {
		
		List<Menu4> resultado   = null;
		List<Menu4> lista       = new ArrayList<>();
		String RESULTADO_SELECT = "";
    	String SELECT = "SELECT id  "
    			      + "FROM tb_modulos_do_sistema4 "; 
    	String WHERE = "";
    	    	
    	//verifica codigoLen
    	if(codigoLen == 3) {    		
    		WHERE = "WHERE LEN(codigo) = 3 and codigo_pai is null and grupo_id = "+grupo.getId()+" order by grupo_id, modulo_do_sistema_id ";    		
    	} else if(codigoLen == 6) {    		
    		WHERE = "WHERE LEN(codigo) = 6 and codigo_pai = '"+codigoPai+"' and grupo_id = "+grupo.getId()+" order by grupo_id, modulo_do_sistema_id ";    		
    	} else if(codigoLen == 9) {    		
    		WHERE = "WHERE LEN(codigo) = 9 and codigo_pai = '"+codigoPai+"' and grupo_id = "+grupo.getId()+" order by grupo_id, modulo_do_sistema_id ";    		
    	} else if(codigoLen == 12) {    		
    		WHERE = "WHERE LEN(codigo) = 12 and codigo_pai = '"+codigoPai+"' and grupo_id = "+grupo.getId()+" order by grupo_id, modulo_do_sistema_id ";    		
    	} else if(codigoLen == 15) {    		
    		WHERE = "WHERE LEN(codigo) = 15 and codigo_pai = '"+codigoPai+"' and grupo_id = "+grupo.getId()+" order by grupo_id, modulo_do_sistema_id ";    		
    	}
    			      
    	RESULTADO_SELECT = SELECT +	WHERE;       
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(RESULTADO_SELECT);
    		
    		while (result.next()) {
    			
    			Menu4 menu = new Menu4(result.getInt("id"), null, null, null, null, null, null, null, null);
    			lista.add(menu);
    			
    		}
    		
    		resultado = lista;
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NO SELECT func35PegaIdMenuUsuario = "+e.getMessage());
		} 
		
		
		return resultado;
		
	}
	
	
	
	public List<ModuloDoSistema4> func36PegaItensMenuAdm(int codigoLen, String codigoPai) {
		
		List<ModuloDoSistema4> resultado = null;
				
		//verifica nulidade de codigoPai
		if(codigoLen == 3 && (codigoPai == null || codigoPai == "" && codigoPai.length() == 0)) {
			resultado = persistencia.mdsRepository4.func350FindAll_PorCodigoLen3MenuAdm();
						
		} else if(codigoLen == 6 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mdsRepository4.func351FindAll_PorCodigoLen6MenuAdm(codigoPai);
			
		} else if(codigoLen == 9 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mdsRepository4.func352FindAll_PorCodigoLen9MenuAdm(codigoPai);
			
		} else if(codigoLen == 12 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mdsRepository4.func353FindAll_PorCodigoLen12MenuAdm(codigoPai);
			
		} else if(codigoLen == 6 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mdsRepository4.func354FindAll_PorCodigoLen15MenuAdm(codigoPai);
		}			
		
		return resultado;
		
	}
	
	
	public List<ModuloDoSistema4> func36PegaItensMenuAdm2(ModuloDoSistema4 moduloDoSistemaLen3) {
		
		String dashboard                        = "dashboard";
		List<ModuloDoSistema4> resultado        = null;		
		List<ModuloDoSistema4> mdsPesquisaLista = persistencia.mdsRepository4.func361FindAll_DashBoardPorCaminhoAdm(moduloDoSistemaLen3.getCodigo().trim(), dashboard);
		
		//verifica nulidade de resultado
		if(mdsPesquisaLista != null && mdsPesquisaLista.size() != 0) {
			
			if(mdsPesquisaLista.size() == 1) {
				
				resultado = mdsPesquisaLista;
				
			} else if(mdsPesquisaLista.size() > 1) {
				
				resultado = persistencia.mdsRepository4.func300FindAll_PorId(mdsPesquisaLista.get(0).getId());
				
			}
			
		}		
		
		return resultado;
		
	}
	
	
	
	public List<Menu4> func37PegaItensMenuUsuario(int codigoLen, String codigoPai, Grupo grupo) {
		
		List<Menu4> resultado = null;
		
		//verifica nulidade de codigoPai
		if(codigoLen == 3 && (codigoPai == null || codigoPai == "" && codigoPai.length() == 0)) {
			resultado = persistencia.mRepository4.func350FindAll_PorCodigoLen3MenuUsuario(grupo.getId());
						
		} else if(codigoLen == 6 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mRepository4.func351FindAll_PorCodigoLen6MenuUsuario(codigoPai, grupo.getId());
			
		} else if(codigoLen == 9 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mRepository4.func352FindAll_PorCodigoLen9MenuUsuario(codigoPai, grupo.getId());
			
		} else if(codigoLen == 12 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mRepository4.func353FindAll_PorCodigoLen12MenuUsuario(codigoPai, grupo.getId());
			
		} else if(codigoLen == 6 && codigoPai != null && codigoPai.length() != 0) {
			resultado = persistencia.mRepository4.func354FindAll_PorCodigoLen15MenuUsuario(codigoPai, grupo.getId());
		}	
		
		return resultado;
		
	}
	
	
	
	public boolean func030ExisteDashboardXhtml(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
				
		if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
			
			int dashboardStr  = persistencia.mdsRepository4.func112Count_RepeticoesDashBoardXhtmlPorCaminhoLen3(moduloDoSistema.getId(), "dashboard");
			
			if(moduloDoSistema.getCaminhoFicheiroXhtml() != null && dashboardStr != 0) {
				resultado = true;
				System.out.println("---- resultado = "+resultado);
			} else {
				resultado = false;
				System.out.println("---- resultado = "+resultado);
			}
			
		}
		
		return resultado;
		
	}
	
	
	
	public String func040EnviaCaminhoXhtml(ModuloDoSistema4 moduloDoSistema) {
		setModuloDoSistemaNoMenuUserAdm(null);
		setModuloDoSistemaNoMenuUserAdm(moduloDoSistema);
		return moduloDoSistema.getCaminhoFicheiroXhtml().trim();
	}
	
	
	
	public boolean func041VerificaModuloComDashboardAdm(ModuloDoSistema4 moduloDoSistema) {
		
		boolean resultado = false;
		String str        = "dashboard";
		int pesquisa      = persistencia.mdsRepository4.func113Count_RepeticoesModulosComDashBoardAdm(moduloDoSistema.getCodigo().trim(), str);
		
		if(pesquisa != 0) {
			resultado = true;
		}
		
		return resultado;
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public void salvar() {
		
		//verifica a nulidade dos campos
		if(func025VerificaNulidadeCamposUsuario(usuario2) == true){
			mensagem.addError("ATENÇÃO! Nenhum campo do formulário deve ser nulo.");
		} else {
			
			//cria objecto usuarioInsert
			Usuario usuarioInsert = new Usuario(null, 
					usuario2.getNomeUsuario().trim(), 
					usuario2.senhaEncriptada(usuario2.getSenha().trim()), 
					usuario2.getIntegrante(), 
					usuario2.getPerfil(), 
					usuario2.getGrupo(), 
					usuario2.getVisibilidade().trim(), 
					usuario2.getAcessos()); 
			
			//gravar na base de dados
			persistencia.uRepository.save(usuarioInsert);
			
			//mensagem de confirmação
			mensagem.addInfo("Usuário gravado com Sucesso.");
			
			//redefinir lista de usuario
			setUsuariosLista(func021PegaListaUsuarioPorNomeUsuario(usuario.getNomeUsuario().trim()));
			
			//limpar formulario
			cancelarGeral();
			
			
		}
		
	}
	
	
	@Override
	public void editar() {
		
		//verifica a nulidade dos campos
		if(func025VerificaNulidadeCamposUsuario(usuario2) == true){
			mensagem.addError("ATENÇÃO! Nenhum campo do formulário deve ser nulo.");
		} else {
			
			Usuario usuarioPesq = persistencia.uRepository.findOne(usuario2.getId());
			String senhaAlt     = null;
			
			if(usuario2.getSenha().length() == 0) {
				senhaAlt = usuarioPesq.getSenha();				
			} else if(usuario2.getSenha().length() != 0) {
				senhaAlt = func027ActualizaSenha(usuarioPesq, usuario2.getSenha());				
			}
			
			//cria objecto usuarioUpdate
			Usuario usuarioUpdate = new Usuario(
					usuario2.getId(), 
					usuario2.getNomeUsuario().trim(), 
					senhaAlt, 
					usuario2.getIntegrante(), 
					usuario2.getPerfil(), 
					usuario2.getGrupo(), 
					usuario2.getVisibilidade().trim(), 
					usuario2.getAcessos());
			System.out.println("----- usuarioUpdate     = "+usuarioUpdate);
			System.out.println("----- Senha BD          = "+usuarioPesq.getSenha());
			System.out.println("----- Senha form        = "+usuario2.getSenha());
			System.out.println("----- Senha actualizada = "+func027ActualizaSenha(usuarioPesq, usuario2.getSenha()));
			//gravar na base de dados
			persistencia.uRepository.save(usuarioUpdate);
			
			//mensagem de confirmação
			mensagem.addInfo("O Usuário foi alterado com Sucesso.");
			
			//redefinir lista de usuario
			setUsuariosLista(func021PegaListaUsuarioPorNomeUsuario(usuario2.getNomeUsuario().trim()));
			
			//limpar formulario
			cancelarGeral();
			
		}
		
	}
	
	
	@Override
	public void eliminar() {
		// TODO Auto-generated method stub
		
	}
	
	

}
