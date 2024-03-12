package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_funcoes", nativeQuery=true)
	public int func101Count_Registros();
	
	
	//--- funcões func200: Seleciona objecto -----------------
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_funcoes "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Funcao> func301FindAll_OrdenarPorNome();
	
}
