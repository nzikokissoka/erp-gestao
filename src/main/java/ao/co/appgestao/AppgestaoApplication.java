package ao.co.appgestao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ao.co.appgestao.model.Carreira;
import ao.co.appgestao.model.Categoria;
import ao.co.appgestao.model.Especialidade;
import ao.co.appgestao.model.Funcao;
import ao.co.appgestao.model.Grupo;
import ao.co.appgestao.model.HabilitacaoLiteraria;
import ao.co.appgestao.model.Integrante;
import ao.co.appgestao.model.ModuloDoSistema4;
import ao.co.appgestao.model.Municipio;
import ao.co.appgestao.model.Pessoa;
import ao.co.appgestao.model.Provincia;
import ao.co.appgestao.model.StatusAnoLectivo;
import ao.co.appgestao.model.StatusCoordenacao;
import ao.co.appgestao.model.StatusGenerico;
import ao.co.appgestao.model.TipoDeCurso;
import ao.co.appgestao.model.Titulo;
import ao.co.appgestao.model.Usuario;
import ao.co.appgestao.repository.CarreiraRepository;
import ao.co.appgestao.repository.CategoriaRepository;
import ao.co.appgestao.repository.EspecialidadeRepository;
import ao.co.appgestao.repository.FuncaoRepository;
import ao.co.appgestao.repository.GrupoRepository;
import ao.co.appgestao.repository.HabilitacaoLiterariaRepository;
import ao.co.appgestao.repository.IntegranteRepository;
import ao.co.appgestao.repository.ModuloDoSistemaRepository4;
import ao.co.appgestao.repository.MunicipioRepository;
import ao.co.appgestao.repository.PessoaRepository;
import ao.co.appgestao.repository.ProvinciaRepository;
import ao.co.appgestao.repository.StatusAnoLectivoRepository;
import ao.co.appgestao.repository.StatusCoordenacaoRepository;
import ao.co.appgestao.repository.StatusGenericoRepository;
import ao.co.appgestao.repository.TipoDeCursoRepository;
import ao.co.appgestao.repository.TituloRepository;
import ao.co.appgestao.repository.UsuarioRepository;
import ao.co.appgestao.utilities.DAO;

@SpringBootApplication
public class AppgestaoApplication implements CommandLineRunner{

	// -- INJECÇÃO DE DEPENDÊNCIAS -------------------------------------------
	private @Autowired GrupoRepository      gRepository;
	private @Autowired PessoaRepository     pRepository;
	private @Autowired IntegranteRepository iRepository;
	private @Autowired UsuarioRepository    uRepository;
	private @Autowired ProvinciaRepository  provRepository;
	private @Autowired MunicipioRepository  munRepository;
	private @Autowired CategoriaRepository  cRepository;
	private @Autowired CarreiraRepository   carrRepository;
	private @Autowired TituloRepository     tRepository;
	private @Autowired FuncaoRepository     fRepository;
	private @Autowired HabilitacaoLiterariaRepository hlRepository;
	private @Autowired EspecialidadeRepository        eRepository;
	private @Autowired ModuloDoSistemaRepository4     msdRepository4;
	private @Autowired StatusAnoLectivoRepository     salRepository;
	private @Autowired StatusCoordenacaoRepository    scooRepository;
	private @Autowired StatusGenericoRepository       sgenRepository;
	private @Autowired TipoDeCursoRepository          tdcRepository;
	
	private DAO dao        = new DAO();
	private Date dtRegisto = dao.func02DataActualStringEng2();
	
	public static void main(String[] args) {
		SpringApplication.run(AppgestaoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//inserção de Grupo		
			//pega numero de registos com nome:Administrador do sistema.
			int contaRegistosGrupo = gRepository.func101Count_RegistrosRepetidosPorNome("Administradores de Sistema");
			List<Grupo> grupos     = new ArrayList<>();		
			
			//verifica a existencia do grupo
			if(contaRegistosGrupo == 0) {
				
				grupos.add(new Grupo(null, "Administradores de Sistema"));
				grupos.add(new Grupo(null, "Alunos"));			
				grupos.add(new Grupo(null, "Coordenadores de Área de Formação"));
				grupos.add(new Grupo(null, "Coordenadores de Curso"));				
				grupos.add(new Grupo(null, "Coordenadores de Turno"));
				grupos.add(new Grupo(null, "Directores Gerais"));
				grupos.add(new Grupo(null, "Outros Administradores de Sistema"));
				grupos.add(new Grupo(null, "Professores"));
				grupos.add(new Grupo(null, "Secretários Escolar"));
				grupos.add(new Grupo(null, "Subdirectores Pedagógicos"));
				grupos.add(new Grupo(null, "Subdirectores Administratívos"));				
				
				///Grupo grupoInsert = new Grupo(null, "Administrador de Sistema");				
				gRepository.save(grupos);
				
			}
		//fim inserção de Grupo	
		
			
			
			
		//inserção de Pessoa
			//pega numero de registos com nome:Administrador do sistema.
			int contaRegistosPessoa = pRepository.func102Count_RegistrosRepetidosPorNome("Administrador Padrão do Sistema");
			
			if(contaRegistosPessoa == 0) {
				
				Pessoa pessoaInsert = new Pessoa(null, 
						                         "Administrador Padrão do Sistema", 
						                         null, null, null, null, null, null, null, null, null, null, null, null, null, null, 
						                         dtRegisto, 
						                         null, null, null, null, 
						                         "N");
				
				pRepository.save(pessoaInsert);			
				
				
			}
		//fim inserção de Pessoa
		
		
		//inserção de Integrante
			
			//Pegar pessoa			
			Pessoa pessoa = pRepository.func202FindOne_PessoaPorNome("Administrador Padrão do Sistema");
			
			//verifica nulidade do obj pessoa
			if(pessoa != null) {
				
				//pega numero de registos com id de pessoa.
				int contaRegistosIntegrante = iRepository.func102Count_RegistrosRepetidosPorPessoaId(pessoa.getId());
				
				//verifica contaRegistosIntegrante
				if(contaRegistosIntegrante == 0) {
					
					//cria obj integrente
					Integrante integranteInsert = new Integrante(null, pessoa, null, 
													             null, null, null, 
													             null, null, null, 
													             null, null, null, 
													             null, dtRegisto, null, null, 
													             null, null);
					
					
					iRepository.save(integranteInsert);					
					
					
				}
				
				
				
				//inserção de usuario
					
					// variaveis									
					String usuario        = "admin";
					String senha          = new Usuario().senhaEncriptada("admin2k4");
					Integrante integrante = iRepository.func202FindOne_PorPessoaId(pessoa.getId());
					Grupo grupo           = gRepository.func202FindOne_PorNome("Administradores de Sistema");
					String perfil         = "ROLE_ADMIN";
					String visibilidade   = "N";
					Integer acessos       = 2; //1-acesso inicial(pode ser alterado); 2-acesso antigo(sem alteração)
					
					//pega registros repetidos com usuario de nome: admin
					int contaRegistosUsuario = uRepository.func101Count_RegistrosRepetidosPorUsuarioNome(usuario);
					
					//verifica contaRegistosUsuario
					if(contaRegistosUsuario == 0) {
						
						Usuario usuarioInsert = new Usuario(null, 
								                            usuario, 
								                            senha, 
								                            integrante, 
								                            perfil, 
								                            grupo, 
								                            visibilidade, 
								                            acessos);
						
						uRepository.save(usuarioInsert);
						
					}
					
				//fim inserção de usuario
				
				
			}
		
		//fim inserção de Integrante
			
		
		//inserção de Províncias
			
			//contagem da existencia de registros
			int contagemProvincias     = provRepository.func101Count_Registros();
			List<Provincia> provincias = new ArrayList<>();
			
			//verifica contagem
			if(contagemProvincias == 0) {
				
				//variaveis
				provincias.add(new Provincia(null, "Bengo"));
				provincias.add(new Provincia(null, "Benguela"));
				provincias.add(new Provincia(null, "Bié"));
				provincias.add(new Provincia(null, "Cabinda"));
				provincias.add(new Provincia(null, "Cuando Cubango"));
				provincias.add(new Provincia(null, "Cuanza Norte"));
				provincias.add(new Provincia(null, "Cuanza Sul"));
				provincias.add(new Provincia(null, "Cunene"));
				provincias.add(new Provincia(null, "Huambo"));
				provincias.add(new Provincia(null, "Huíla"));
				provincias.add(new Provincia(null, "Luanda"));
				provincias.add(new Provincia(null, "Lunda Norte"));
				provincias.add(new Provincia(null, "Lunda Sul"));
				provincias.add(new Provincia(null, "Malanje"));
				provincias.add(new Provincia(null, "Moxico"));
				provincias.add(new Provincia(null, "Namibe"));
				provincias.add(new Provincia(null, "Uíge"));
				provincias.add(new Provincia(null, "Zaire"));
								
				//inserção na base de dados
				provRepository.save(provincias);
				
				
			}
			
		//fim inserção de Províncias
			
			
		//    inserção de Municípios
			
			//contagem da existencia de registros
			int contagemMunicipios     = munRepository.func101Count_Registros();
			List<Municipio> municipios = new ArrayList<>();
			
			//verifica contagemProvincias
			if(contagemMunicipios == 0) {
				
				Provincia Bengo = provRepository.func202FindOne_PorNome("Bengo");
				municipios.add(new Municipio(null, "Ambriz",        Bengo));
				municipios.add(new Municipio(null, "Dande(sede)",   Bengo));
				municipios.add(new Municipio(null, "Dembos",        Bengo));
				municipios.add(new Municipio(null, "Bula Atumba",   Bengo));
				municipios.add(new Municipio(null, "Nambuangongo",  Bengo));
				municipios.add(new Municipio(null, "Pango Aluquêm", Bengo));
				
				Provincia Benguela = provRepository.func202FindOne_PorNome("Benguela");
				municipios.add(new Municipio(null, "Balombo",        Benguela));
				municipios.add(new Municipio(null, "Baía Farta",     Benguela));
				municipios.add(new Municipio(null, "Benguela(sede)", Benguela));
				municipios.add(new Municipio(null, "Bocoio",         Benguela));
				municipios.add(new Municipio(null, "Caimbambo",      Benguela));
				municipios.add(new Municipio(null, "Catumbela",      Benguela));
				municipios.add(new Municipio(null, "Chongoroi",      Benguela));
				municipios.add(new Municipio(null, "Cubal",          Benguela));
				municipios.add(new Municipio(null, "Ganda",          Benguela));
				municipios.add(new Municipio(null, "Lobito",         Benguela));
				
				Provincia Bie = provRepository.func202FindOne_PorNome("Bié");
				municipios.add(new Municipio(null, "Andulo",         Bie));
				municipios.add(new Municipio(null, "Camacupa",       Bie));
				municipios.add(new Municipio(null, "Catabola",       Bie));
				municipios.add(new Municipio(null, "Chinguar",       Bie));
				municipios.add(new Municipio(null, "Chitembo",       Bie));
				municipios.add(new Municipio(null, "Cuemba",         Bie));
				municipios.add(new Municipio(null, "Cunhinga",       Bie));
				municipios.add(new Municipio(null, "Kuito(sede)",    Bie));
				municipios.add(new Municipio(null, "Nharea",         Bie));
				
				Provincia Cabinda = provRepository.func202FindOne_PorNome("Cabinda");
				municipios.add(new Municipio(null, "Belize",         Cabinda));
				municipios.add(new Municipio(null, "Buco-Zau",       Cabinda));
				municipios.add(new Municipio(null, "Cabinda(sede)",  Cabinda));
				municipios.add(new Municipio(null, "Cacongo",        Cabinda));
				
				Provincia CuandoCubango = provRepository.func202FindOne_PorNome("Cuando Cubango");
				municipios.add(new Municipio(null, "Calai",           CuandoCubango));
				municipios.add(new Municipio(null, "Cuangar",         CuandoCubango));
				municipios.add(new Municipio(null, "Cuchi",           CuandoCubango));
				municipios.add(new Municipio(null, "Cuito Cuanavale", CuandoCubango));
				municipios.add(new Municipio(null, "Dirico",          CuandoCubango));
				municipios.add(new Municipio(null, "Longa",           CuandoCubango));
				municipios.add(new Municipio(null, "Mavinga",         CuandoCubango));
				municipios.add(new Municipio(null, "Menongue(sede)",  CuandoCubango));
				municipios.add(new Municipio(null, "Rivungo",         CuandoCubango));
				
				Provincia CuanzaNorte = provRepository.func202FindOne_PorNome("Cuanza Norte");
				municipios.add(new Municipio(null, "Ambaca",         CuanzaNorte));
				municipios.add(new Municipio(null, "Banga",          CuanzaNorte));
				municipios.add(new Municipio(null, "Bolongongo",     CuanzaNorte));
				municipios.add(new Municipio(null, "Cambambe",       CuanzaNorte));
				municipios.add(new Municipio(null, "Cazengo(sede)",  CuanzaNorte));
				municipios.add(new Municipio(null, "Golungo Alto",   CuanzaNorte));
				municipios.add(new Municipio(null, "Gonguembo",      CuanzaNorte));
				municipios.add(new Municipio(null, "Lucala",         CuanzaNorte));
				municipios.add(new Municipio(null, "Quiculungo",     CuanzaNorte));
				municipios.add(new Municipio(null, "Samba Caju",     CuanzaNorte));
				
				Provincia CuanzaSul = provRepository.func202FindOne_PorNome("Cuanza Sul");
				municipios.add(new Municipio(null, "Amboim",         CuanzaSul));
				municipios.add(new Municipio(null, "Cassongue",      CuanzaSul));
				municipios.add(new Municipio(null, "Conda",          CuanzaSul));
				municipios.add(new Municipio(null, "Ebo",            CuanzaSul));
				municipios.add(new Municipio(null, "Libolo",         CuanzaSul));
				municipios.add(new Municipio(null, "Mussende",       CuanzaSul));
				municipios.add(new Municipio(null, "Porto Amboim",   CuanzaSul));
				municipios.add(new Municipio(null, "Quibala",        CuanzaSul));
				municipios.add(new Municipio(null, "Quilenda",       CuanzaSul));
				municipios.add(new Municipio(null, "Seles",          CuanzaSul));
				municipios.add(new Municipio(null, "Sumbe(sede)",    CuanzaSul));
				municipios.add(new Municipio(null, "Waku Kungo",     CuanzaSul));
				
				Provincia Cunene = provRepository.func202FindOne_PorNome("Cunene");
				municipios.add(new Municipio(null, "Cahama",         Cunene));
				municipios.add(new Municipio(null, "Cuanhama(sede)", Cunene));
				municipios.add(new Municipio(null, "Curoca",         Cunene));
				municipios.add(new Municipio(null, "Cuvelay",        Cunene));
				municipios.add(new Municipio(null, "Namacunde",      Cunene));
				municipios.add(new Municipio(null, "Ombadja",        Cunene));
				
				Provincia Huambo = provRepository.func202FindOne_PorNome("Huambo");
				municipios.add(new Municipio(null, "Bailundo",            Huambo));
				municipios.add(new Municipio(null, "Catchiungo",          Huambo));
				municipios.add(new Municipio(null, "Caála",               Huambo));
				municipios.add(new Municipio(null, "Ekunha",              Huambo));
				municipios.add(new Municipio(null, "Huambo(sede)",        Huambo));
				municipios.add(new Municipio(null, "Londuimbale",         Huambo));
				municipios.add(new Municipio(null, "Longongo",            Huambo));
				municipios.add(new Municipio(null, "Mungo",               Huambo));
				municipios.add(new Municipio(null, "Tchicala-Tcholoanga", Huambo));
				municipios.add(new Municipio(null, "Tchindjenje",         Huambo));
				municipios.add(new Municipio(null, "Ucuma",               Huambo));
				
				Provincia Huila = provRepository.func202FindOne_PorNome("Huíla");
				municipios.add(new Municipio(null, "Caconda",       Huila));
				municipios.add(new Municipio(null, "Cacula",        Huila));
				municipios.add(new Municipio(null, "Caluquembe",    Huila));
				municipios.add(new Municipio(null, "Chiange",       Huila));
				municipios.add(new Municipio(null, "Chibia",        Huila));
				municipios.add(new Municipio(null, "Chicomba",      Huila));
				municipios.add(new Municipio(null, "Chipindo",      Huila));
				municipios.add(new Municipio(null, "Humpata",       Huila));
				municipios.add(new Municipio(null, "Jamba",         Huila));
				municipios.add(new Municipio(null, "Kuvango",       Huila));
				municipios.add(new Municipio(null, "Lubango(sede)", Huila));
				municipios.add(new Municipio(null, "Matala",        Huila));
				municipios.add(new Municipio(null, "Quilengues",    Huila));
				municipios.add(new Municipio(null, "Quipungo",      Huila));
				
				Provincia Luanda = provRepository.func202FindOne_PorNome("Luanda");
				municipios.add(new Municipio(null, "Belas",         Luanda));
				municipios.add(new Municipio(null, "Cacuaco",       Luanda));
				municipios.add(new Municipio(null, "Cazenga",       Luanda));
				municipios.add(new Municipio(null, "Icolo e Bengo", Luanda));
				municipios.add(new Municipio(null, "Luanda(sede)",  Luanda));
				municipios.add(new Municipio(null, "Quiçama",       Luanda));
				municipios.add(new Municipio(null, "Kilamba Kiaxi", Luanda));
				municipios.add(new Municipio(null, "Talatona",      Luanda));
				municipios.add(new Municipio(null, "Viana",         Luanda));
				
				Provincia LundaNorte = provRepository.func202FindOne_PorNome("Lunda Norte");
				municipios.add(new Municipio(null, "Cambulo",                LundaNorte));
				municipios.add(new Municipio(null, "Capenda-Camulemba",      LundaNorte));
				municipios.add(new Municipio(null, "Caungula",               LundaNorte));
				municipios.add(new Municipio(null, "Chitato/Tchitato(sede)", LundaNorte));
				municipios.add(new Municipio(null, "Cuango",                 LundaNorte));
				municipios.add(new Municipio(null, "Cuilo",                  LundaNorte));
				municipios.add(new Municipio(null, "Lóvua",                  LundaNorte));
				municipios.add(new Municipio(null, "Lubalo",                 LundaNorte));
				municipios.add(new Municipio(null, "Lucapa",                 LundaNorte));
				municipios.add(new Municipio(null, "Xá Muteba",              LundaNorte));
				
				Provincia LundaSul = provRepository.func202FindOne_PorNome("Lunda Sul");
				municipios.add(new Municipio(null, "Cacolo",        LundaSul));
				municipios.add(new Municipio(null, "Dala",          LundaSul));
				municipios.add(new Municipio(null, "Muconda",       LundaSul));
				municipios.add(new Municipio(null, "Saurimo(sede)", LundaSul));
				
				Provincia Malanje = provRepository.func202FindOne_PorNome("Malanje");
				municipios.add(new Municipio(null, "Cacuso",           Malanje));
				municipios.add(new Municipio(null, "Calandula",        Malanje));
				municipios.add(new Municipio(null, "Cambundi-Catembo", Malanje));
				municipios.add(new Municipio(null, "Cangandala",       Malanje));
				municipios.add(new Municipio(null, "Caombo",           Malanje));
				municipios.add(new Municipio(null, "Cuaba Nzoji",      Malanje));
				municipios.add(new Municipio(null, "Cunda-Dia-Baze",   Malanje));
				municipios.add(new Municipio(null, "Luquembo",         Malanje));
				municipios.add(new Municipio(null, "Malanje(sede)",    Malanje));
				municipios.add(new Municipio(null, "Marimba",          Malanje));
				municipios.add(new Municipio(null, "Massango",         Malanje));
				municipios.add(new Municipio(null, "Mucari-caculama",  Malanje));
				municipios.add(new Municipio(null, "Quela",            Malanje));
				municipios.add(new Municipio(null, "Quirima",          Malanje));
				
				Provincia Moxico = provRepository.func202FindOne_PorNome("Moxico");
				municipios.add(new Municipio(null, "Alto Zambeze",     Moxico));
				municipios.add(new Municipio(null, "Bundas",           Moxico));
				municipios.add(new Municipio(null, "Camanongue",       Moxico));
				municipios.add(new Municipio(null, "Cameia",           Moxico));
				municipios.add(new Municipio(null, "Luau",             Moxico));
				municipios.add(new Municipio(null, "Lucano",           Moxico));
				municipios.add(new Municipio(null, "Luchazes",         Moxico));
				municipios.add(new Municipio(null, "Léua",             Moxico));
				municipios.add(new Municipio(null, "Moxico(sede)",     Moxico));
				
				Provincia Namibe = provRepository.func202FindOne_PorNome("Namibe");
				municipios.add(new Municipio(null, "Bibala",           Namibe));
				municipios.add(new Municipio(null, "Camulo",           Namibe));
				municipios.add(new Municipio(null, "Namibe(sede)",     Namibe));
				municipios.add(new Municipio(null, "Tômbua",           Namibe));
				municipios.add(new Municipio(null, "Virei",            Namibe));
				
				Provincia Uige = provRepository.func202FindOne_PorNome("Uíge");
				municipios.add(new Municipio(null, "Alto Cauale",      Uige));
				municipios.add(new Municipio(null, "Ambuíla",          Uige));
				municipios.add(new Municipio(null, "Bembe",            Uige));
				municipios.add(new Municipio(null, "Buengas",          Uige));
				municipios.add(new Municipio(null, "Bungo",            Uige));
				municipios.add(new Municipio(null, "Damba",            Uige));
				municipios.add(new Municipio(null, "Macocola",         Uige));
				municipios.add(new Municipio(null, "Mucaba",           Uige));
				municipios.add(new Municipio(null, "Negage",           Uige));
				municipios.add(new Municipio(null, "Puri",             Uige));
				municipios.add(new Municipio(null, "Quimbele",         Uige));
				municipios.add(new Municipio(null, "Quitexe",          Uige));
				municipios.add(new Municipio(null, "Sanza Pombo",      Uige));
				municipios.add(new Municipio(null, "Songo",            Uige));
				municipios.add(new Municipio(null, "Uíge(sede)",       Uige));
				municipios.add(new Municipio(null, "Maquela do Zombo", Uige));
				
				Provincia Zaire = provRepository.func202FindOne_PorNome("Zaire");
				municipios.add(new Municipio(null, "Cuimba",              Zaire));
				municipios.add(new Municipio(null, "M'Banza Kongo(sede)", Zaire));
				municipios.add(new Municipio(null, "Noqui",               Zaire));
				municipios.add(new Municipio(null, "N'Zeto",              Zaire));
				municipios.add(new Municipio(null, "Soyo",                Zaire));
				municipios.add(new Municipio(null, "Tomboco",             Zaire));
				
				//inserção da base de dados
				munRepository.save(municipios);
				
			}
			
		//fim inserção de Municípios
			
		
			
        //----inserção de Títulos ----------------------------------------	
			
			//contagem da existencia de registros
			int contagemTitulos  = tRepository.func101Count_Registros();
			List<Titulo> titulos = new ArrayList<>();
			
			//verifica contagemCarreiras
			if(contagemTitulos == 0) {
				
				//titlos masculinos
				titulos.add(new Titulo(null, "Adm."  , "Administrador"   , "M"));
				titulos.add(new Titulo(null, "Adv.º" , "Advogado"        , "M"));
				titulos.add(new Titulo(null, "Al."   , "Aluno"           , "M"));	
				titulos.add(new Titulo(null, "Arq.º" , "Aquitecto"       , "M"));	
				titulos.add(new Titulo(null, "Bel."  , "Bacharel"        , "M"));	
				titulos.add(new Titulo(null, "DD."   , "Digníssimo"      , "M"));
				titulos.add(new Titulo(null, "Dr."   , "Doutor"          , "M"));
				titulos.add(new Titulo(null, "Eng.º" , "Engenheiro"      , "M"));
				titulos.add(new Titulo(null, "Exmo." , "Excelentíssimo"  , "M"));
				titulos.add(new Titulo(null, "Prof." , "Professor"       , "M"));	
				titulos.add(new Titulo(null, "Méd."  , "Medico"          , "M"));
				titulos.add(new Titulo(null, "Me."   , "Mestre"          , "M"));
				titulos.add(new Titulo(null, "Sr."   , "Senhor"          , "M"));
				titulos.add(new Titulo(null, "V.Ex.ª", "Vossa Excelência", "M"));
								
				//titlos femininos
				titulos.add(new Titulo(null, "Adm.ª" , "Administradora"  , "F"));
				titulos.add(new Titulo(null, "Adv.ª" , "Advogada"        , "F"));
				titulos.add(new Titulo(null, "Al.ª"  , "Aluna"           , "F"));
				titulos.add(new Titulo(null, "Arq.ª" , "Aquitecta"       , "F"));
				titulos.add(new Titulo(null, "Bel.ª" , "Bacharela"       , "F"));	
				titulos.add(new Titulo(null, "DD.ª"  , "Digníssima"      , "F"));
				titulos.add(new Titulo(null, "D.ª"   , "Dona"            , "F"));
				titulos.add(new Titulo(null, "Dra."  , "Doutora"         , "F"));
				titulos.add(new Titulo(null, "Eng.ª" , "Engenheira"      , "F"));
				titulos.add(new Titulo(null, "Exma." , "Excelentíssima"  , "F"));				
				titulos.add(new Titulo(null, "Prof.ª", "Professora"      , "F"));
				titulos.add(new Titulo(null, "Ma."   , "Mestra"          , "F"));
				titulos.add(new Titulo(null, "Méd.ª" , "Medica"          , "F"));
				titulos.add(new Titulo(null, "Sra."  , "Senhora"         , "F"));
				titulos.add(new Titulo(null, "Stra." , "Senhorita"       , "F"));
				titulos.add(new Titulo(null, "V.Ex.ª", "Vossa Excelência", "F"));
								
				//inserção da base de dados
				tRepository.save(titulos);	
				
			}
			
		//fim inserção de Títulos ----------------------------------------	
	
			
			
		//---- inserção de Carreiras -------------------------------------		
			//contagem da existencia de registros
			int contagemCarreiras     = carrRepository.func101Count_Registros();
			List<Carreira> carreiras = new ArrayList<>();
			
			//verifica contagemCarreiras
			if(contagemCarreiras == 0) {
				
				//variaveis
				carreiras.add(new Carreira(null, "AAE" , "Auxiliar de Ação Educatíva"));
				carreiras.add(new Carreira(null, "EI"  , "Educador de Infância"));
				carreiras.add(new Carreira(null, "EAE" , "Especialista da Administração da Educação"));
				carreiras.add(new Carreira(null, "PEPA", "Professor do Ensino Primário Auxiliar"));
				carreiras.add(new Carreira(null, "PEPS", "Professor do Ensino Primário e Secundário"));
				carreiras.add(new Carreira(null, "TP"  , "Técnico Pedagógico"));
				
				//inserção da base de dados
				carrRepository.save(carreiras);	
				
			}			
		//-fim inserção de Carreiras -------------------------------------
		
			
			
		//---- inserção de Categorias ------------------------------------		
			//contagem da existencia de registros
			int contagemCategorias     = cRepository.func101Count_Registros();
			List<Categoria> categorias = new ArrayList<>();
			
			//verifica contagemProvincias
			if(contagemCategorias == 0) {
				
				//variaveis
				
				//Carreira de Auxiliar de Ação Educatíva
				Carreira carreiraAuxiliarAcaoEducativa = carrRepository.func201FindOne_PorNome("Auxiliar de Ação Educatíva");				
				
				//verifica carreiraAuxiliarAcaoEducativa
				if(!carreiraAuxiliarAcaoEducativa.getId().equals(null)) {
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 1º Grau", carreiraAuxiliarAcaoEducativa));
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 2º Grau", carreiraAuxiliarAcaoEducativa));
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 3º Grau", carreiraAuxiliarAcaoEducativa));
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 4º Grau", carreiraAuxiliarAcaoEducativa));
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 5º Grau", carreiraAuxiliarAcaoEducativa));
					categorias.add(new Categoria(null, "Auxiliar de Ação Educatíva do 6º Grau", carreiraAuxiliarAcaoEducativa));
				}
				
				
								
				//Carreira de Educação de Infância
				Carreira carreiraEducadorInfancia = carrRepository.func201FindOne_PorNome("Educador de Infância");
				
				// verifica carreiraEducadorInfancia
				if(!carreiraEducadorInfancia.getId().equals(null)) {
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 1º Grau" , carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 2º Grau" , carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 3º Grau" , carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 4º Grau" , carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 5º Grau" , carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível I do 6º Grau" , carreiraEducadorInfancia));				
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 1º Grau", carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 2º Grau", carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 3º Grau", carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 4º Grau", carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 5º Grau", carreiraEducadorInfancia));
					categorias.add(new Categoria(null, "Educador de Infância de Nível II do 6º Grau", carreiraEducadorInfancia));
				}
				
				
				
				//Carreira de Especialista da Administração da Educação
				Carreira carreiraEspecialistaAdministracaoEducacao = carrRepository.func201FindOne_PorNome("Especialista da Administração da Educação");
				
				//verifica carreiraEspecialistaAdministracaoEducacao
				if(!carreiraEspecialistaAdministracaoEducacao.getId().equals(null)) {
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 1º Grau", carreiraEspecialistaAdministracaoEducacao));
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 2º Grau", carreiraEspecialistaAdministracaoEducacao));
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 3º Grau", carreiraEspecialistaAdministracaoEducacao));
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 4º Grau", carreiraEspecialistaAdministracaoEducacao));
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 5º Grau", carreiraEspecialistaAdministracaoEducacao));
					categorias.add(new Categoria(null, "Especialista da Administração da Educação do 6º Grau", carreiraEspecialistaAdministracaoEducacao));
				}
				
				
				
				//Carreira de Professor do Ensino Primário Auxiliar
				Carreira carreiraProfessorEnsinoPrimarioAuxiliar= carrRepository.func201FindOne_PorNome("Professor do Ensino Primário Auxiliar");
				
				//verifica carreiraProfessorEnsinoPrimarioAuxiliar
				if(!carreiraProfessorEnsinoPrimarioAuxiliar.getId().equals(null)) {
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 1º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 2º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 3º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 4º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 5º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
					categorias.add(new Categoria(null, "Professor do Ensino Primário Auxiliar do 6º Grau", carreiraProfessorEnsinoPrimarioAuxiliar));
				}
				
				
				
				//Carreira de Professor do Ensino Primário e Secundário
				Carreira carreiraProfessorEnsinoPrimarioSecundario = carrRepository.func201FindOne_PorNome("Professor do Ensino Primário e Secundário");
				
				//verifica carreiraProfessorEnsinoPrimarioSecundario
				if(!carreiraProfessorEnsinoPrimarioSecundario.getId().equals(null)) {
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 1º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 2º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 3º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 4º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 5º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 6º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 7º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 8º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 9º Grau" , carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 10º Grau", carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 11º Grau", carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 12º Grau", carreiraProfessorEnsinoPrimarioSecundario));
					categorias.add(new Categoria(null, "Professor do Ensino Primário e Secundário do 13º Grau", carreiraProfessorEnsinoPrimarioSecundario));
				}
				
				
				
				//Carreira de Técnico Pedagógico
				Carreira carreiraTecnicoPedagogico = carrRepository.func201FindOne_PorNome("Técnico Pedagógico");
				
				//verifica carreiraTecnicoPedagogico
				if(!carreiraTecnicoPedagogico.getId().equals(null)) {
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível I do 1º Grau" , carreiraTecnicoPedagogico));
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível I do 2º Grau" , carreiraTecnicoPedagogico));
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível I do 3º Grau" , carreiraTecnicoPedagogico));				
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível II do 1º Grau", carreiraTecnicoPedagogico));
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível II do 2º Grau", carreiraTecnicoPedagogico));
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível II do 3º Grau", carreiraTecnicoPedagogico));
					categorias.add(new Categoria(null, "Técnico Pedagógico de Nível II do 3º Grau", carreiraTecnicoPedagogico));
				}
				
								
				//inserção na base de dados
				cRepository.save(categorias);
				
			}			
		//-fim inserção de Categorias ------------------------------------
			
		
			
		//-----inserção de Especialidade ---------------------------------
		//-fim inserção de Especialidade ---------------------------------
			
			
			
		//-----inserção de Habilitações Literárias -----------------------
			
			//contagem da existencia de registros
			int contagemHabLiterarias                         = hlRepository.func101Count_Registros();
			List<HabilitacaoLiteraria> habilitacoesLiterarias = new ArrayList<>();
			
			//verifica contagemHabLiterarias
			if(contagemHabLiterarias == 0) {
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "1ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "2ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "3ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "4ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "5ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "6ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "7ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "8ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "9ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "10ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "11ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "12ª Classe"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "13ª Classe"));
				
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "1º Ano da Facudalde"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "2º Ano da Facudalde"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "3º Ano da Facudalde"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "4º Ano da Facudalde"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "5º Ano da Facudalde"));
				
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Bacharel(a)"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Doutorado(a)"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Licenciado(a)"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Pós-graduado(a)"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Mestre(a)"));
				habilitacoesLiterarias.add(new HabilitacaoLiteraria(null, "Sem habilitações"));
				
				//inserção na base de dados
				hlRepository.save(habilitacoesLiterarias);
				
			}
			
		//-fim inserção de Habilitações Literárias -----------------------
			
			
		//-----inserção de Especialidades --------------------------------
			
			//contagem da existencia de registros
			int contagemEspecialidades         = eRepository.func101Count_Registros();
			List<Especialidade> especialidades = new ArrayList<>();
			
			//verifica contagemEspecialidades
			if(contagemEspecialidades == 0) {
				especialidades.add(new Especialidade(null, "Administração Pública"));
				especialidades.add(new Especialidade(null, "Administração"));
				especialidades.add(new Especialidade(null, "Administração de Empresas"));
				especialidades.add(new Especialidade(null, "Arquitetura"));
				especialidades.add(new Especialidade(null, "Arquitetura e Urmanismo"));
				
				especialidades.add(new Especialidade(null, "Bióloga"));
				
				especialidades.add(new Especialidade(null, "Ciências Biologicas"));
				especialidades.add(new Especialidade(null, "Ciências Economicas e Júridicas"));
				especialidades.add(new Especialidade(null, "Ciências Exactas"));
				especialidades.add(new Especialidade(null, "Ciências Físicas e Biológicas"));
				especialidades.add(new Especialidade(null, "Ciências Pedagógicas"));				
				especialidades.add(new Especialidade(null, "Ciencias Sociais"));
				especialidades.add(new Especialidade(null, "Comunicação Social"));
				especialidades.add(new Especialidade(null, "Construção Civil"));
				especialidades.add(new Especialidade(null, "Contabilidade"));
				especialidades.add(new Especialidade(null, "Contabilidade e Administração"));
				especialidades.add(new Especialidade(null, "Contabilidade e Auditoria"));
				especialidades.add(new Especialidade(null, "Contabilidade e Gestão"));
				
				especialidades.add(new Especialidade(null, "Design Educacional"));
				especialidades.add(new Especialidade(null, "Direito"));
				especialidades.add(new Especialidade(null, "Docência no Ensino Superior"));
				
				especialidades.add(new Especialidade(null, "Económia"));
				especialidades.add(new Especialidade(null, "Economia e Gestão"));
				especialidades.add(new Especialidade(null, "Economia/Relações Internacionais"));
				especialidades.add(new Especialidade(null, "Economista"));
				especialidades.add(new Especialidade(null, "Educação a Distância"));
				especialidades.add(new Especialidade(null, "Educação Corporativa e Gestão do Conhecimento"));
				especialidades.add(new Especialidade(null, "Educação de Jovens e Adultos"));
				especialidades.add(new Especialidade(null, "Educação em Contextos Não Escolares"));
				especialidades.add(new Especialidade(null, "Educação Física Escolar"));
				especialidades.add(new Especialidade(null, "Educação para Diversidade"));
				especialidades.add(new Especialidade(null, "Educação Para a infância"));
				especialidades.add(new Especialidade(null, "Educação, Ludicidade e Desenvolvimento Infantil"));				
				especialidades.add(new Especialidade(null, "Electricidade"));
				especialidades.add(new Especialidade(null, "Electricidade Industrial"));
				especialidades.add(new Especialidade(null, "Electrotécnia"));
				especialidades.add(new Especialidade(null, "Electrotecnia e Telecomunicações"));
				especialidades.add(new Especialidade(null, "Electrotécnico"));
				especialidades.add(new Especialidade(null, "Engenharia de Alimentos"));
				especialidades.add(new Especialidade(null, "Engenharia de Agrícola"));
				especialidades.add(new Especialidade(null, "Engenharia Biomédica"));
				especialidades.add(new Especialidade(null, "Engenharia Ambiental"));
				especialidades.add(new Especialidade(null, "Engenharia Civil"));
				especialidades.add(new Especialidade(null, "Engenharia da Computação"));
				especialidades.add(new Especialidade(null, "Engenharia de Controle e Automação"));
				especialidades.add(new Especialidade(null, "Engenharia Electrotecnia Energética"));
				especialidades.add(new Especialidade(null, "Engenharia Electrica"));
				especialidades.add(new Especialidade(null, "Engenharia Energética"));
				especialidades.add(new Especialidade(null, "Engenharia Hídrica"));
				especialidades.add(new Especialidade(null, "Engenharia Informática"));
				especialidades.add(new Especialidade(null, "Engenharia Industrial"));				
				especialidades.add(new Especialidade(null, "Engenharia Florestal"));				
				especialidades.add(new Especialidade(null, "Engenharia Mecânica"));
				especialidades.add(new Especialidade(null, "Engenharia Metalúrgica"));
				especialidades.add(new Especialidade(null, "Engenharia de Minas"));
				especialidades.add(new Especialidade(null, "Engenharia de Petróleo"));
				especialidades.add(new Especialidade(null, "Engenharia de Petróleo e Gás"));
				especialidades.add(new Especialidade(null, "Engenharia de Produção"));
				especialidades.add(new Especialidade(null, "Engenharia Química"));
				especialidades.add(new Especialidade(null, "Engenharia de Telecomunicações"));
				especialidades.add(new Especialidade(null, "Engenharia de Software"));
				especialidades.add(new Especialidade(null, "Engenharia de Segurança no Trabalho"));				
				especialidades.add(new Especialidade(null, "Ensino, Aprendizagem e Linguagens Artísticas"));				
				especialidades.add(new Especialidade(null, "Ensino de História e Geografia"));
				especialidades.add(new Especialidade(null, "Ensino de Leitura e Produção Textual"));
				
				especialidades.add(new Especialidade(null, "Geologia"));
				especialidades.add(new Especialidade(null, "Geografia e História"));
				especialidades.add(new Especialidade(null, "Gestão Educacional"));
				especialidades.add(new Especialidade(null, "Gestão Educacional e Processos Escolares"));
				especialidades.add(new Especialidade(null, "Gestão Empresarial"));
				especialidades.add(new Especialidade(null, "Gestão da Aprendizagem e Educação Cognitiva"));
				especialidades.add(new Especialidade(null, "Gestão Comercial e Marketing"));
				especialidades.add(new Especialidade(null, "Gestão de Empresas"));
				especialidades.add(new Especialidade(null, "Gestão de Processos Educacionais"));
				especialidades.add(new Especialidade(null, "Gestão de Recursos Humanos"));				
				especialidades.add(new Especialidade(null, "Gestão e Contabilidade"));
				especialidades.add(new Especialidade(null, "Gestão e Contabilidade no Ramo de Gestão de Empresas"));
				especialidades.add(new Especialidade(null, "Gestão e Liderança no Ensino a Distância"));
				especialidades.add(new Especialidade(null, "Gestão e Marketing"));
				
				especialidades.add(new Especialidade(null, "Inovação e Aprendizagem no Ensino de Matemática e Ciências"));
				especialidades.add(new Especialidade(null, "Informática de gestão"));
				
				especialidades.add(new Especialidade(null, "Jurista"));
				
				especialidades.add(new Especialidade(null, "Linguas e Literatura em Línguas Angolanas"));
				especialidades.add(new Especialidade(null, "Linguas e Literatura em Língua Portuguesa"));
				especialidades.add(new Especialidade(null, "Língua Portuguesa e Línguas Nacionais"));
				especialidades.add(new Especialidade(null, "Literatura"));
				
				especialidades.add(new Especialidade(null, "Matemática"));
				especialidades.add(new Especialidade(null, "Mecânica"));
				especialidades.add(new Especialidade(null, "Mecânica/Textes"));
				especialidades.add(new Especialidade(null, "Metodologias Ativas de Aprendizagem"));
				especialidades.add(new Especialidade(null, "Metodologias Ativas e Práticas Docentes Para Educação Básica"));
				especialidades.add(new Especialidade(null, "Metodologias de Aprendizagem Para a Educação Infantil e Ensino Fundamental"));
				
				especialidades.add(new Especialidade(null, "Orientação Educacional"));
				especialidades.add(new Especialidade(null, "Organização e Gestão de Empresas"));
				
				especialidades.add(new Especialidade(null, "Pedagógia"));
				especialidades.add(new Especialidade(null, "Pedagogia Social"));
				especialidades.add(new Especialidade(null, "Políticas Públicas Educacionais"));
				especialidades.add(new Especialidade(null, "Práticas Inovadoras em Educação"));
				especialidades.add(new Especialidade(null, "Prática Docente na Educação Infantil"));
				especialidades.add(new Especialidade(null, "Produção de Material Didático"));
				especialidades.add(new Especialidade(null, "Processos Avaliativos na Educação"));
				especialidades.add(new Especialidade(null, "Processos de Aprendizagem em Ambientes Virtuais"));
				especialidades.add(new Especialidade(null, "Psicologia"));
				especialidades.add(new Especialidade(null, "Psicopedagogia"));
				
				especialidades.add(new Especialidade(null, "Relações Internacionais"));
				
				especialidades.add(new Especialidade(null, "Sistemas de Telecomunições e Electrónica"));
				especialidades.add(new Especialidade(null, "Supervisão e Gestão Escolar"));
				especialidades.add(new Especialidade(null, "Supervisão Educacional"));				
				
				especialidades.add(new Especialidade(null, "Técnicas e Tecnologias Electroenergeticas"));
				especialidades.add(new Especialidade(null, "Técnico de Frio e Climatização"));
				especialidades.add(new Especialidade(null, "Técnologia de Informação"));
				especialidades.add(new Especialidade(null, "Tecnologias Digitais Aplicadas à Educação"));
				especialidades.add(new Especialidade(null, "Tipografia"));
				

				//inserção na base de dados
				eRepository.save(especialidades);
				
			}
			
		//-fim inserção de Especialidades --------------------------------
			
		
		//----inserção de Funções ----------------------------------------
			
			//contagem da existencia de registros
			int contagemFuncoes  = fRepository.func101Count_Registros();
			List<Funcao> funcoes = new ArrayList<>();
			
			//verifica contagemEspecialidades
			if(contagemFuncoes == 0) {
				
				//Comunidade estudantil---------------
				funcoes.add(new Funcao(null, "Aluno", "Membro da comunidade estudantil matriculado na instituição, participando de aulas, atividades e cumprindo requisitos acadêmicos."));
				
				//Direção/Administração--------------
				funcoes.add(new Funcao(null, "Diretor(a)"                       , "Responsável pela liderança geral da escola, tomada de decisões estratégicas, administração e representação da instituição perante a comunidade."));
				funcoes.add(new Funcao(null, "Vice-diretor(a)"                  , "Apoia o diretor em diversas funções administrativas, assume responsabilidades na ausência do diretor e participa da gestão escolar."));
				funcoes.add(new Funcao(null, "Coordenador(a) Geral"             , "Coordena diferentes áreas da escola, garantindo a coesão e eficiência, muitas vezes colaborando diretamente com o diretor."));
				funcoes.add(new Funcao(null, "Secretário(a) Escolar"            , "Gerencia a administração diária, registros acadêmicos, matrículas e comunicação com pais e alunos."));
				//funcoes.add(new Funcao(null, "", ""));
				
				//Área Pedagógica---------------------
				funcoes.add(new Funcao(null, "Sub-Director(a) Pedagógico(a)"    , "Auxilia o diretor na gestão pedagógica da instituição."));
				funcoes.add(new Funcao(null, "Coordenador(a) Pedagógico(a)"     , "Supervisiona o desenvolvimento acadêmico, orienta professores, promove práticas pedagógicas eficazes e lida com questões disciplinares."));
				funcoes.add(new Funcao(null, "Orientador(a) Educacional"        , "Oferece apoio emocional e acadêmico aos alunos, auxilia na resolução de problemas pessoais e orienta escolhas acadêmicas."));
				funcoes.add(new Funcao(null, "Supervisor(a) Pedagógico(a)"      , "Avalia e aprimora a qualidade do ensino, fornecendo suporte aos professores para o desenvolvimento profissional."));
				funcoes.add(new Funcao(null, "Professor(a)"                     , "Instrui os alunos nas disciplinas específicas, desenvolve planos de aula e avaliações."));
				funcoes.add(new Funcao(null, "Auxiliar de Sala de Aula"         , "Auxilia os professores nas atividades diárias em sala de aula, oferecendo suporte aos alunos."));
				
				
				//Área Administrativa-----------------
				funcoes.add(new Funcao(null, "Sub-Director()a Administratívo(a)", "Auxilia o diretor na gestão administrativa e operacional da escola."));
				funcoes.add(new Funcao(null, "Secretário(a) Administrativo(a)"  , "Gerencia tarefas administrativas, como correspondência, agendas e arquivos."));
				funcoes.add(new Funcao(null, "Assistente Administrativo(a)"     , "Presta suporte administrativo geral, auxiliando em diversas tarefas."));
				funcoes.add(new Funcao(null, "Recepcionista"                    , "Recebe visitantes, atende chamadas telefônicas e realiza tarefas de recepção."));
				
				
				//Área Financeira---------------------
				funcoes.add(new Funcao(null, "Tesoureiro(a)"                    , "Gerencia as finanças da escola, realiza transações e mantém registros financeiros."));
				funcoes.add(new Funcao(null, "Assistente Financeiro(a)"         , "Presta suporte nas operações financeiras, processa pagamentos e auxilia na elaboração de relatórios."));
				
				
				//Área de Apoio à Educação------------
				funcoes.add(new Funcao(null, "Bibliotecário(a)"                 , "Gerencia a biblioteca, organiza o acervo e auxilia na pesquisa"));
				funcoes.add(new Funcao(null, "Auxiliar de Biblioteca"           , "Presta suporte nas atividades da biblioteca, ajuda usuários e mantém o ambiente organizado."));
				funcoes.add(new Funcao(null, "Técnico(a) em Informática"        , "Gerencia sistemas e oferece suporte técnico em questões relacionadas à tecnologia."));
				funcoes.add(new Funcao(null, "Auxiliar de Serviços Gerais"      , "Cuida da limpeza e organização dos espaços comuns da instituição."));
				funcoes.add(new Funcao(null, "Responsável dos Laboratórios"     , "Supervisiona e gerencia as atividades relacionadas aos laboratórios."));
				funcoes.add(new Funcao(null, "Responsável do Património"        , "Gerencia e mantém o patrimônio da instituição, incluindo prédios, instalações, equipamentos e outros ativos. "));
				
				
				//Coordenações Específicas-----------
				funcoes.add(new Funcao(null, "Coordenador(a) de Curso"                          , "Coordena atividades e desenvolvimento curricular específicos para determinados cursos."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Ensino Fundamental I/II e Médio", "Coordena atividades e desenvolvimento curricular para cada nível de ensino."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Educação Infantil"              , "Coordena atividades e desenvolvimento curricular específicos para a educação infantil."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Atividades Extracurriculares"   , "Supervisiona atividades que ocorrem fora do currículo regular, promovendo enriquecimento acadêmico e cultural."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Turno (Manhã)"                  , "Coordena atividades escolares no turno da manhã."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Turno (Tarde)"                  , "Coordena atividades escolares no turno da tarde."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Turno (Noite)"                  , "Coordena atividades escolares no turno da noite"));
				
				
				//Profissionais de Saúde e Bem-Estar--
				funcoes.add(new Funcao(null, "Enfermeiro(a) Escolar"                            , "Oferece assistência de saúde aos alunos, administra medicamentos e coordena programas de saúde."));
				funcoes.add(new Funcao(null, "Psicólogo(a) Escolar"                             , "Oferece suporte emocional e auxilia no desenvolvimento social e emocional dos alunos."));
				funcoes.add(new Funcao(null, "Nutricionista"                                    , "Planeja e supervisiona programas de alimentação escolar, promovendo hábitos alimentares saudáveis."));
				
				
				//Atividades Culturais e Esportivas--
				funcoes.add(new Funcao(null, "Coordenador(a) de Atividades Esportivas"          , "Coordena e promove atividades esportivas, eventos e competições na escola."));
				funcoes.add(new Funcao(null, "Instrutor(a) de Artes"                            , "Ministra aulas de artes visuais, teatro, dança ou música."));
				funcoes.add(new Funcao(null, "Instrutor(a) de Música"                           , "Ministra aulas de música, promove concertos e eventos musicais."));
				
				
				//Tecnologia da Informação-----------
				funcoes.add(new Funcao(null, "Coordenador(a) de TI"                             , "Gerencia a infraestrutura de TI, desenvolve estratégias tecnológicas e supervisiona a equipe de TI."));
				funcoes.add(new Funcao(null, "Analista de Sistemas"                             , "Desenvolve e mantém sistemas de informação."));
				funcoes.add(new Funcao(null, "Suporte Técnico"                                  , "Presta suporte técnico aos usuários para questões relacionadas à tecnologia."));
				
				
				//Comunicação e Marketing------------
				funcoes.add(new Funcao(null, "Assessor(a) de Comunicação"                       , "Gerencia a comunicação interna e externa da escola, incluindo mídias sociais e relações públicas."));
				funcoes.add(new Funcao(null, "Coordenador(a) de Marketing"                      , "Desenvolve estratégias de marketing para promover a instituição."));
				
				
				//Serviço Social---------------------
				funcoes.add(new Funcao(null, "Assistente Social"                                , "Oferece suporte social aos alunos, intervém em questões sociais e promove a inclusão."));
								
				//Segurança-------------------------
				funcoes.add(new Funcao(null, "Porteiro(a)"                                      , "Controla o acesso à instituição, verifica identificações e monitora a entrada."));
				funcoes.add(new Funcao(null, "Vigia"                                            , "Realiza a vigilância e a segurança das instalações escolares."));
				
				
				//Manutenção e Infraestrutura-------
				funcoes.add(new Funcao(null, "Engenheiro(a) Civil"                              , "Gerencia projetos de construção e manutenção das instalações físicas."));
				funcoes.add(new Funcao(null, "Eletricista"                                      , "Cuida da manutenção elétrica das instalações."));
				funcoes.add(new Funcao(null, "Encanador"                                        , "Realiza trabalhos de encanamento e manutenção hidráulica."));
				
				
				//Outras Funções Específicas---------
				funcoes.add(new Funcao(null, "Advogado(a) (para assuntos legais)"               , "Oferece aconselhamento jurídico e lida com questões legais."));
				funcoes.add(new Funcao(null, "Auditor(a)"                                       , "Realiza auditorias internas para garantir conformidade e eficiência."));
				funcoes.add(new Funcao(null, "Tradutor(a)/Intérprete de Libras"                 , "Facilita a comunicação para alunos com deficiência auditiva ou entre diferentes idiomas."));
				
			}
			//fim verifica contagemEspecialidades
			
			//inserção na base de dados
			fRepository.save(funcoes);
			
			
		//-fim inserção de Funções ---------------------------------------
			
			
		
		//inserção de Modulos do Sistema
			
			//contagem da existencia de registros
			int contagemModulosDoSistema                 = msdRepository4.func100Count_Registros();
			List<ModuloDoSistema4> modulosDoSistemaLista = new ArrayList<>();
			
			//verifica contagemEspecialidades
			if(contagemModulosDoSistema == 0) {
				
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001", null, null, "CONFIGURAÇÕES", "pi pi-fw pi-cog"));
				
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001001", "001", "/integrantes2.xhtml"      , "Integrantes"       , "pi pi-id-card"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001002", "001", "/grupos.xhtml"            , "Grupos"            , "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001003", "001", "/gruposPrivilegios4.xhtml", "Grupo privilégios" , "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001004", "001", "/modulosDoSistema4.xhtml" , "Módulos de sistema", "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001005", "001", "/usuarios.xhtml"          , "Usuários"          , "pi pi-users"));
				
				
				/*modulosDoSistemaLista.add(new ModuloDoSistema4(null, "001", null, "/home.xhtml", "HOME", "pi pi-home"));
				
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002", null, null, "CONFIGURAÇÕES", "pi pi-fw pi-cog"));
				
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002001", "002", "/integrantes2.xhtml"      , "Integrantes"       , "pi pi-id-card"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002002", "002", "/grupos.xhtml"            , "Grupos"            , "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002003", "002", "/gruposPrivilegios4.xhtml", "Grupo privilégios" , "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002004", "002", "/modulosDoSistema4.xhtml" , "Módulos de sistema", "pi pi-file-o"));
				modulosDoSistemaLista.add(new ModuloDoSistema4(null, "002005", "002", "/usuarios.xhtml"          , "Usuários"          , "pi pi-users"));*/
				
			}
			//fim verifica contagemEspecialidades
			
			
			//inserção na base de dados
			msdRepository4.save(modulosDoSistemaLista);
			
			
		//fim inserção de Modulos do Sistema
			
			
			
			
		//inserção de Status Ano lectivo			
			//contagem da existencia de registros
			int contagemStatusAnoLectivo                 = salRepository.func100Count_Registros();
			List<StatusAnoLectivo> statusAnoLectivoLista = new ArrayList<>();
			
			//verifica contagemStatusAnoLectivo
			if(contagemStatusAnoLectivo == 0) {
				
				statusAnoLectivoLista.add(new StatusAnoLectivo(null, "ativo"    , "Ativo"));
				statusAnoLectivoLista.add(new StatusAnoLectivo(null, "concluido", "Concluído"));
				statusAnoLectivoLista.add(new StatusAnoLectivo(null, "pendente" , "Pendente"));
				
			}
			//fim verifica contagemStatusAnoLectivo
			
			//inserção na base de dados
			salRepository.save(statusAnoLectivoLista);			
		//fim inserção de Status Ano lectivo
		
			
			
		//inserção de Status coordenacao			
			//contagem da existencia de registros
			int contagemStatusCoordenacao                  = scooRepository.func100Count_Registros();
			List<StatusCoordenacao> StatusCoordenacaoLista = new ArrayList<>();
			
			//verifica contagemStatusCoordenacao
			if(contagemStatusCoordenacao == 0) {
				
				StatusCoordenacaoLista.add(new StatusCoordenacao(null, "ativa"  , "Ativa"));
				StatusCoordenacaoLista.add(new StatusCoordenacao(null, "inativa", "Inativa"));				
				
			}
			//fim verifica contagemStatusCoordenacao
			
			//inserção na base de dados
			scooRepository.save(StatusCoordenacaoLista);			
		//fim inserção de Status coordenacao	
		
			
			
		//inserção de Status generico			
			//contagem da existencia de registros
			int contagemStatusGenerico               = sgenRepository.func100Count_Registros();
			List<StatusGenerico> statusGenericoLista = new ArrayList<>();
			
			//verifica contagemStatusGenerico
			if(contagemStatusGenerico == 0) {
				
				statusGenericoLista.add(new StatusGenerico(null, "aberto"      , "Aberto(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "fechado"     , "Fechado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "aceito"      , "Aceito(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "descartado"  , "Descartado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "rejeitado"   , "Rejeitado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "adiado"      , "Adiado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "programado"  , "Programado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "ativa"       , "Ativo(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "inativo"     , "Inativo(a)"));			
				statusGenericoLista.add(new StatusGenerico(null, "aprovado"    , "Aprovado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "pendente"    , "Pendente"));
				statusGenericoLista.add(new StatusGenerico(null, "resolvido"   , "Resolvido(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "reprovado"   , "Reprovado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "arquivado"   , "Arquivado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "desarquivado", "Desarquivado(a)"));								
				statusGenericoLista.add(new StatusGenerico(null, "bloqueado"   , "Bloqueado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "desbloqueado", "Desbloqueado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "confirmado"  , "Confirmado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "cancelado"   , "Cancelado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "considerado" , "Considerado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "decidido"    , "Decidido(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "designado"   , "Designado(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "exonerado"   , "Exonarado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "incluido"    , "Incluído(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "retirado"    , "Retirado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "no-prazo"    , "No Prazo"));
				statusGenericoLista.add(new StatusGenerico(null, "atrasado"    , "Atrasado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "respondido"  , "Respondido(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "sem-resposta", "Sem resposta"));				
				statusGenericoLista.add(new StatusGenerico(null, "suspenso"    , "Suspenso(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "retomado"    , "Retomado(a)"));				
				statusGenericoLista.add(new StatusGenerico(null, "valido"      , "Válido(a)"));
				statusGenericoLista.add(new StatusGenerico(null, "expirado"    , "Expirado(a)"));
				
				statusGenericoLista.add(new StatusGenerico(null, "em-andamento"        , "Em andamento"));
				statusGenericoLista.add(new StatusGenerico(null, "em-andamento-revisao", "Em andamento, sob revisão"));
				statusGenericoLista.add(new StatusGenerico(null, "em-analise"          , "Em análise"));
				statusGenericoLista.add(new StatusGenerico(null, "em-analise-risco"    , "Em análise de risco"));				
				statusGenericoLista.add(new StatusGenerico(null, "em-desenvolvimento"  , "Em desenvolvimento"));				
				statusGenericoLista.add(new StatusGenerico(null, "em-espera"           , "Em espera"));
				statusGenericoLista.add(new StatusGenerico(null, "em-espera-aprovacao" , "Em espera de aprovação"));				
				statusGenericoLista.add(new StatusGenerico(null, "em-execucao"         , "Em execução"));
				statusGenericoLista.add(new StatusGenerico(null, "em-exercicio"        , "Em exercício de funções"));				
				statusGenericoLista.add(new StatusGenerico(null, "em-manutencao"       , "Em manutenção"));
				statusGenericoLista.add(new StatusGenerico(null, "em-progresso"        , "Em progresso"));									
				statusGenericoLista.add(new StatusGenerico(null, "em-revisao"          , "Em revisão"));
				statusGenericoLista.add(new StatusGenerico(null, "em-resisao-legal"    , "Em revisão legal"));	
				statusGenericoLista.add(new StatusGenerico(null, "em-teste"            , "Em teste"));
				
				
				
								
			}
			//fim verifica contagemStatusGenerico
			
			//inserção na base de dados
			sgenRepository.save(statusGenericoLista);			
		//fim inserção de Status generico
			
			
			
			
		//inserção de tipos cursos
			//contagem da existencia de registros  
			int contagemTiposCursos                 = tdcRepository.func100Count_Registros();
			List<TipoDeCurso> tiposDeCursosoLista = new ArrayList<>();
			//fim contagem da existencia de registros
			
			//verifica contagemTiposCursos
			if(contagemTiposCursos == 0) {
				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Aperfeiçoamento"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Aprendizagem Industrial"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Aprendizagem Comercial"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Aprendizagem Rural"));					
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Bacharelado"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Doutorado"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Educação Continuada"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Especialização Técnica"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Extensão"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Formação Profissional"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Graduação Tecnológica"));			
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Licenciatura"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "MBA (Master of Business Administration)"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Mestrado"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Pós-graduação"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Pós-graduação Lato Sensu"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Pós-graduação Stricto Sensu"));				
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Técnico"));
				tiposDeCursosoLista.add(new TipoDeCurso(null, "Técnico Profissionalizante"));
				
			}
			//fim verifica contagemTiposCursos
			
			//inserção na base de dados
			tdcRepository.save(tiposDeCursosoLista);			
		//fim inserção de tipos cursos
			
			
			
		
		
		
	}

}
