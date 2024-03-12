package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Menu4;


@Repository
public interface MenuRepository4 extends JpaRepository<Menu4, Integer>{

	
	
	//--- funcões func100: Seleciona contagem ----------------
	@Query(value = "SELECT count(*) " + 
			       "FROM tb_menu4 " + 
		           "WHERE codigo = :codigoID and grupo_id = :grupoID", nativeQuery = true)
	public int func105Count_OutrasGravacoesPorCodigoEGrupo(@Param("codigoID") String codigoID, @Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT count(*) " + 
			       "FROM tb_menu4 " + 
		           "WHERE LEN(codigo) = :lenINT   and "
		          +"      codigo_pai  = :codigoID and "
		          +"      grupo_id    = :grupoID  and "
		          +"      caminho_ficheiro_xhtml is not null", nativeQuery = true)
	public int func106Count_OutrasGravacoesNoItemPaiPorCodigoEGrupo(@Param("lenINT")   Integer lenINT,
			                                                        @Param("codigoID") String codigoID, 
			                                                        @Param("grupoID")  Integer grupoID);
	
	
	
	//--- funcões func200: Seleciona unico -------------------
	@Query(value = "SELECT * " + 
			       "FROM tb_menu4 " + 
		           "WHERE codigo = :codigoID and grupo_id = :grupoID", nativeQuery = true)
	public Menu4 func205FindOne_PorCodigoEGrupo(@Param("codigoID") String codigoID, @Param("grupoID") Integer grupoID);
	
	
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
			       "FROM tb_menu4 " + 
		           "WHERE LEN(codigo) = 3 and codigo_pai is null and grupo_id = :grupoID " +
		           "ORDER BY id", nativeQuery = true)
	public List<Menu4> func350FindAll_PorCodigoLen3MenuUsuario(@Param("grupoID") Integer grupoID);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_menu4 " 
		           +"WHERE LEN(codigo) = 6 and codigo_pai = :codigoPaiSTR and grupo_id = :grupoID "
		           +"ORDER BY id", nativeQuery = true)
	public List<Menu4> func351FindAll_PorCodigoLen6MenuUsuario(@Param("codigoPaiSTR") String codigoPaiSTR, @Param("grupoID") Integer grupoID);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_menu4 " 
		           +"WHERE LEN(codigo) = 9 and codigo_pai = :codigoPaiSTR and grupo_id = :grupoID "
		           +"ORDER BY id", nativeQuery = true)
	public List<Menu4> func352FindAll_PorCodigoLen9MenuUsuario(@Param("codigoPaiSTR") String codigoPaiSTR, @Param("grupoID") Integer grupoID);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_menu4 " 
		           +"WHERE LEN(codigo) = 12 and codigo_pai = :codigoPaiSTR and grupo_id = :grupoID "
		           +"ORDER BY id", nativeQuery = true)
	public List<Menu4> func353FindAll_PorCodigoLen12MenuUsuario(@Param("codigoPaiSTR") String codigoPaiSTR, @Param("grupoID") Integer grupoID);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_menu4 " 
		           +"WHERE LEN(codigo) = 15 and codigo_pai = :codigoPaiSTR and grupo_id = :grupoID "
		           +"ORDER BY id", nativeQuery = true)
	public List<Menu4> func354FindAll_PorCodigoLen15MenuUsuario(@Param("codigoPaiSTR") String codigoPaiSTR, @Param("grupoID") Integer grupoID);
	
}
