package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Integrante;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) FROM tb_integrantes " + 
		           "WHERE id = :id", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorId(@Param("id") Integer id);
	
	@Query(value = "SELECT count(*) FROM tb_integrantes " + 
			       "WHERE pessoa_id = :pessoaId", nativeQuery = true)
	public int func102Count_RegistrosRepetidosPorPessoaId(@Param("pessoaId") Integer pessoaId);
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * FROM tb_integrantes " + 
		           "WHERE id = :id", nativeQuery = true)
	public Integrante func201FindOne_PorId(@Param("id") Integer id);
		
	@Query(value = "SELECT * FROM tb_integrantes " + 
			       "WHERE pessoa_id = :pessoaId", nativeQuery = true)
	public Integrante func202FindOne_PorPessoaId(@Param("pessoaId") Integer pessoaId);
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * FROM tb_integrantes " + 
		           "ORDER BY id DESC", nativeQuery = true)
	public List<Integrante> func301FindAll_OrdernarPorIdDESC();
	
	@Query(value = "SELECT * FROM tb_integrantes "
			     + "WHERE id NOT IN (select id from tb_pessoas where visibilidade = 'N')" 
		         + "ORDER BY id DESC", nativeQuery = true)
	public List<Integrante> func302FindAll_PorVisibilidadeN();
	
	@Query(value = "SELECT i.* "
			     + "FROM tb_integrantes i, tb_pessoas p "
			     + "WHERE i.pessoa_id = p.id " 
		         + "ORDER BY p.nome", nativeQuery = true)
	public List<Integrante> func303FindAll_OrdernarPorNome();
	
	@Query(value = "SELECT * FROM tb_integrantes " + 
		           "WHERE id = :id", nativeQuery = true)
	public List<Integrante> func304FindAll_PorId(@Param("id") Integer id);
	
}
