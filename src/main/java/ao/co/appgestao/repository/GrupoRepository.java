package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer>{

	// --- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) " + 
	               "FROM tb_grupos " + 
			       "WHERE nome = :nome", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorNome(@Param("nome") String nome);

	// --- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * " + 
	               "FROM tb_grupos " + 
			       "WHERE id = :id", nativeQuery = true)
	public Grupo func201FindOne_PorId(@Param("id") Integer id);
	
	@Query(value = "SELECT * " + 
                   "FROM tb_grupos " + 
		           "WHERE nome = :nome", nativeQuery = true)
	public Grupo func202FindOne_PorNome(@Param("nome") String nome);

	// --- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
                   "FROM tb_grupos " + 
	               "ORDER By id DESC", nativeQuery = true)
	public List<Grupo> func301FindAll_OrdenarPorIdDesc();
	
	
	@Query(value = "SELECT * " + 
		            "FROM tb_grupos " + 
		            "ORDER By nome", nativeQuery = true)
	public List<Grupo> func302FindAll_OrdenarPorNome();
	
	
	@Query(value = "SELECT * " + 
		            "FROM tb_grupos " +
		            "WHERE nome <> 'ADMINISTRADOR DE SISTEMA' " + 
		            "ORDER By id DESC, nome", nativeQuery = true)
	public List<Grupo> func304FindAll_ExceptoAdm();
	
	
	@Query(value = "SELECT * " + 
		           "FROM tb_grupos " + 
		           "ORDER By nome", nativeQuery = true)
	public List<Grupo> func303FindAll_OrdenarPorIdDescENome();
	
	
}
