package ao.co.appgestao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Provincia;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_provincias", nativeQuery=true)
	public int func101Count_Registros();
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value="SELECT * "
			   + "FROM tb_provincias "
			   + "WHERE id = :provinciaID", nativeQuery=true)
	public Provincia func201FindOne_PorId(@Param("provinciaID") Integer provinciaID);
	
	
	@Query(value="SELECT * "
			   + "FROM tb_provincias "
			   + "WHERE nome = :nomeSTR", nativeQuery=true)
	public Provincia func202FindOne_PorNome(@Param("nomeSTR") String nomeSTR);
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_provincias "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Provincia> func301FindAll_OrdenarPorNome();
	
}
