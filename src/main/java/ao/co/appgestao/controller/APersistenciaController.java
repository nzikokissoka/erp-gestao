package ao.co.appgestao.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import ao.co.appgestao.model.ModuloDoSistema4;
import ao.co.appgestao.model.Usuario;
import ao.co.appgestao.repository.AreaEspecificaRepository;
import ao.co.appgestao.repository.AreaRepository;
import ao.co.appgestao.repository.CarreiraRepository;
import ao.co.appgestao.repository.CategoriaRepository;
import ao.co.appgestao.repository.EspecialidadeRepository;
import ao.co.appgestao.repository.FuncaoRepository;
import ao.co.appgestao.repository.GrupoPrivilegioRepository;
import ao.co.appgestao.repository.GrupoPrivilegioRepository2;
import ao.co.appgestao.repository.GrupoPrivilegioRepository4;
import ao.co.appgestao.repository.GrupoRepository;
import ao.co.appgestao.repository.HabilitacaoLiterariaRepository;
import ao.co.appgestao.repository.IntegranteRepository;
import ao.co.appgestao.repository.MenuRepository4;
import ao.co.appgestao.repository.ModuloDoSistemaRepository;
import ao.co.appgestao.repository.ModuloDoSistemaRepository2;
import ao.co.appgestao.repository.ModuloDoSistemaRepository4;
import ao.co.appgestao.repository.MunicipioRepository;
import ao.co.appgestao.repository.PessoaRepository;
import ao.co.appgestao.repository.ProvinciaRepository;
import ao.co.appgestao.repository.TituloRepository;
import ao.co.appgestao.repository.UnidadeOrganicaRepository;
import ao.co.appgestao.repository.UsuarioRepository;
import ao.co.appgestao.utilities.DAO;
import ao.co.appgestao.utilities.UtilDatas;
import lombok.Getter;
import lombok.Setter;

@Named
@ApplicationScoped
public class APersistenciaController {

	//injecção de dependencias
	@Inject HttpSession httpSession; 
	
	@Autowired AreaRepository              aRepository;
	@Autowired AreaEspecificaRepository    aeRepository;
	@Autowired CategoriaRepository         cRepository; 
	@Autowired CarreiraRepository          carrRepository;
	@Autowired EspecialidadeRepository     eRepository;
	@Autowired FuncaoRepository            fRepository;
	@Autowired HabilitacaoLiterariaRepository hlRepository;	
	@Autowired IntegranteRepository        iRepository;
	@Autowired GrupoRepository             gRepository;
	@Autowired GrupoPrivilegioRepository   gpRepository;
	@Autowired GrupoPrivilegioRepository2  gpRepository2;
	@Autowired GrupoPrivilegioRepository4  gpRepository4;
	@Autowired ModuloDoSistemaRepository   mdsRepository;
	@Autowired ModuloDoSistemaRepository2  mdsRepository2;
	@Autowired ModuloDoSistemaRepository4  mdsRepository4;
	@Autowired MunicipioRepository         munRepotory;
	@Autowired PessoaRepository            pRepository;
	@Autowired ProvinciaRepository         provRepository;
	@Autowired TituloRepository            tRepository;
	@Autowired UnidadeOrganicaRepository   uoRepository;
	@Autowired UsuarioRepository           uRepository;
	@Autowired MenuRepository4             mRepository4;
	
	//inicialização de variaveis
	private @Getter @Setter DAO            DAO;
	private @Getter @Setter UtilDatas      utilDatas;
	
	
	
	@PostConstruct
	public void init() {}
	
	
	// Funções ...................................
	public Usuario func001PegarUsuarioNaSessao() {
		
		return (Usuario) httpSession.getAttribute("usuarioLogado");		
		
	}
	
	
	public void func002RemoverUsuarioNaSessao() {
		
		httpSession.removeAttribute("usuarioLogado");
		
	}
	
	public String func003PegaPrefixoTituloPagina(String caminhoXhtml) {
		
		String resultado = "";
				
		//verifica nulidade de caminhoXhtml
		if(caminhoXhtml != null && caminhoXhtml != "") {
			
			ModuloDoSistema4 moduloDoSistema = mdsRepository4.func206FindOne_PorCaminhoXHTMLStr(caminhoXhtml);
			
			//verificar nulidade de moduloDoSistema
			if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
				
				Integer codigoLen = moduloDoSistema.getCodigo().trim().length();
				
				//verificar nulidade de codigoLen
				if(codigoLen != null) {
					
					//verificar as quantidades de codigoLen
					if(codigoLen == 3) {
						
						resultado = "";
						
					} else if(codigoLen == 6) {
						
						String codigo3 = moduloDoSistema.getCodigoPai().trim();
						
						String dcr3 = mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == null || 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == "" ? "" : 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao().trim()+" > ";
						
						resultado = dcr3;
						
					} else if(codigoLen == 9) {
						
						String codigo3 = moduloDoSistema.getCodigo().trim().substring(0, 3);
						String codigo6 = moduloDoSistema.getCodigoPai().trim();
						
						String dcr3 = mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == null || 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == "" ? "" : 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao().trim()+" > ";
						
						String dcr6 = mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == null || 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == "" ? "" : 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao().trim()+" > ";
						
						resultado = dcr3+dcr6;
						
					} else if(codigoLen == 12) {
						
						String codigo3 = moduloDoSistema.getCodigo().trim().substring(0, 3);
						String codigo6 = moduloDoSistema.getCodigo().trim().substring(0, 6);
						String codigo9 = moduloDoSistema.getCodigoPai().trim();
												
						String dcr3 = mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == null || 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == "" ? "" : 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao().trim()+" > ";
						
						String dcr6 = mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == null || 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == "" ? "" : 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao().trim()+" > ";
						
						String dcr9 = mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == null || 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == "" ? "" : 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao().trim()+" > ";
						
						resultado = dcr3+dcr6+dcr9;
						
						
					} else if(codigoLen == 15) {
						
						String codigo3  = moduloDoSistema.getCodigo().trim().substring(0, 3);
						String codigo6  = moduloDoSistema.getCodigo().trim().substring(0, 6);
						String codigo9  = moduloDoSistema.getCodigo().trim().substring(0, 9);
						String codigo12 = moduloDoSistema.getCodigoPai().trim();
						
						String dcr3 = mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == null || 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == "" ? "" : 
								      mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao().trim()+" > ";
						
						String dcr6 = mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == null || 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == "" ? "" : 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao().trim()+" > ";
						
						String dcr9 = mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == null || 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == "" ? "" : 
							          mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao().trim()+" > ";
						
						String dcr12 = mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao() == null || 
						               mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao() == "" ? "" : 
						               mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao().trim()+" > ";
						
						resultado = dcr3+dcr6+dcr9+dcr12;
						
					}
					
				}
				
			}
			
		}
		
		return resultado.toUpperCase();
		
	}
	
	
	
	public String func004PegaTituloPagina(String caminhoXhtml) {
		
		String resultado = "";
				
		//verifica nulidade de caminhoXhtml
		if(caminhoXhtml != null && caminhoXhtml != "") {
			
			ModuloDoSistema4 moduloDoSistema = mdsRepository4.func206FindOne_PorCaminhoXHTMLStr(caminhoXhtml);
			
			//verificar nulidade de moduloDoSistema
			if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
				
				Integer codigoLen = moduloDoSistema.getCodigo().trim().length();
				
				//verificar nulidade de codigoLen
				if(codigoLen != null) {
					
					//verificar as quantidades de codigoLen
					if(codigoLen == 3) {
						
						String codigo3 = moduloDoSistema.getCodigo().trim();
						
						resultado = mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == null || 
							        mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao() == "" ? "" : 
							        mdsRepository4.func204FindOne_PorCodigoStr(codigo3).getDescricao().trim();
						
					} else if(codigoLen == 6) {
						
						String codigo6 = moduloDoSistema.getCodigo().trim();
						
						resultado = mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == null || 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao() == "" ? "" : 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo6).getDescricao().trim();						
											
					} else if(codigoLen == 9) {
						
						String codigo9 = moduloDoSistema.getCodigo().trim();						
						
						resultado = mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == null || 
							        mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao() == "" ? "" : 
							        mdsRepository4.func204FindOne_PorCodigoStr(codigo9).getDescricao().trim();						
						
					} else if(codigoLen == 12) {
						
						String codigo12 = moduloDoSistema.getCodigo().trim();
												
						resultado = mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao() == null || 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao() == "" ? "" : 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo12).getDescricao().trim();						
						
					} else if(codigoLen == 15) {
						
						String codigo15 = moduloDoSistema.getCodigo().trim();
						
						resultado = mdsRepository4.func204FindOne_PorCodigoStr(codigo15).getDescricao() == null || 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo15).getDescricao() == "" ? "" : 
								    mdsRepository4.func204FindOne_PorCodigoStr(codigo15).getDescricao().trim();						
						
					}
					
				}
				
			}
			
		}
		
		return resultado.toUpperCase();
		
	}
	
	
	
}
