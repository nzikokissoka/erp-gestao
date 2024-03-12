package ao.co.appgestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.StatusGenerico;

@Repository
public interface StatusGenericoRepository extends JpaRepository<StatusGenerico, Integer>{

	
	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_status_generico", nativeQuery=true)
	public int func100Count_Registros();
	
	
	//--- funcões func200: Seleciona objecto -----------------
	
	
	//--- funcões func300: Seleciona lista -------------------
	
	
	
	
}
