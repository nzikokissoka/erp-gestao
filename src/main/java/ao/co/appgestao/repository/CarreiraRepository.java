package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Carreira;

@Repository
public interface CarreiraRepository extends JpaRepository<Carreira, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_carreiras", nativeQuery=true)
	public int func101Count_Registros();
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value="SELECT * "
			   + "FROM tb_carreiras "
			   + "WHERE nome = :nomeSTR", nativeQuery=true)
	public Carreira func201FindOne_PorNome(@Param("nomeSTR") String nomeSTR);
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_carreiras "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Carreira> func301FindAll_OrdenarPorNome();
	
}
