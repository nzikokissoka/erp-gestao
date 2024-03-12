package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.GrupoPrivilegio;

@Repository
public interface GrupoPrivilegioRepository extends JpaRepository<GrupoPrivilegio, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) " + 
	               "FROM tb_grupos_de_privilegios " + 
			       "WHERE grupo_id = :grupoID", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorGrupoId(@Param("grupoID") Integer grupoID);
	
	
	//--- funcões func200: Seleciona objecto -----------------
	
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
	               "FROM tb_grupos_de_privilegios " + 
			       "WHERE grupo_id = :grupoID", nativeQuery = true)
	public List<GrupoPrivilegio> func301FindAll_PorGrupoId(@Param("grupoID") Integer grupoID);
	
	
	
	
}
