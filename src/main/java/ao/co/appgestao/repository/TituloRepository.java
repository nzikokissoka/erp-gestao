package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_titulos", nativeQuery=true)
	public int func101Count_Registros();
	
	
	//--- funcões func200: Seleciona objecto -----------------
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_titulos "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Titulo> func301FindAll_OrdenarPorNome();
	
	
	@Query(value="SELECT * "
			   + "FROM tb_titulos "
			   + "WHERE sexo = 'M' "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Titulo> func302FindAll_PorSexoM();
	
	@Query(value="SELECT * "
			   + "FROM tb_titulos "
			   + "WHERE sexo = :sexoSTR "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Titulo> func303FindAll_PorSexoOrdenarPorNome(@Param("sexoSTR") String sexoSTR);
	
	
	@Query(value="SELECT * "
			   + "FROM tb_titulos "
			   + "WHERE sexo = 'F' "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Titulo> func304FindAll_PorSexoF();
	
	
	@Query(value="SELECT * "
			   + "FROM tb_titulos "
			   + "ORDER BY nome", nativeQuery=true)
	public List<Titulo> func305FindAll();
	
}
