package ao.co.appgestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) FROM tb_pessoas " + 
		           "WHERE id = :id", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorId(@Param("id") Integer id);
	
	@Query(value = "SELECT count(*) FROM tb_pessoas " + 
			       "WHERE nome like :nome%", nativeQuery = true)
	public int func102Count_RegistrosRepetidosPorNome(@Param("nome") String nome);
	
	@Query(value = "SELECT count(*) FROM tb_pessoas " + 
	               "WHERE numero_bilhete_identidade = :numeroBI", nativeQuery = true)
	public int func103Count_RegistrosRepetidosPorBI(@Param("numeroBI") String numeroBI);
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * FROM tb_pessoas " + 
	               "WHERE id = :id", nativeQuery = true)
	public Pessoa func201FindOne_PessoaPorId(@Param("id") Integer id);

	@Query(value = "SELECT * FROM tb_pessoas " + 
			       "WHERE nome like :nome%", nativeQuery = true)
	public Pessoa func202FindOne_PessoaPorNome(@Param("nome") String nome);

	@Query(value = "SELECT * FROM tb_pessoas " + 
	               "WHERE numero_bilhete_identidade = :numeroBI", nativeQuery = true)
	public Pessoa func203FindOne_PessoaPorBI(@Param("numeroBI") String numeroBI);
	
	
	@Query(value = "SELECT * FROM tb_pessoas "
			     + "WHERE nome like :nome% AND "
			     + "      numero_bilhete_identidade = :numeroBI", nativeQuery = true)
	public Pessoa func204FindOne_PessoaPorNomeEBI(@Param("nome") String nome, @Param("numeroBI") String numeroBI);
	
	
	//--- funcões func300: Seleciona lista -------------------
	
}
