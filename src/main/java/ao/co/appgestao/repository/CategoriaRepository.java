package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_categorias", nativeQuery=true)
	public int func101Count_Registros();
	
	
	
	//--- funcões func200: Seleciona objecto -----------------
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_categorias "
			   + "ORDER BY id", nativeQuery=true)
	public List<Categoria> func301FindAll_OrdenarPorId();
	
	
	@Query(value="SELECT * "
			   + "FROM tb_categorias "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Categoria> func302FindAll_OrdenarPorNome();
	
	
	@Query(value="SELECT * "
			   + "FROM tb_categorias "
			   + "WHERE carreira_id = :carreiraID "
			   + "ORDER BY id", nativeQuery=true)
	public List<Categoria> func303FindAll_PorIdOrdenarPorId(@Param("carreiraID") Integer carreiraID);
	
}
