package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_usuarios "
			   + "WHERE nome_usuario = :nomeUsuario", nativeQuery=true)
	public int func101Count_RegistrosRepetidosPorUsuarioNome(@Param("nomeUsuario") String nomeUsuario);
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value="SELECT * "
			   + "FROM tb_usuarios "
			   + "WHERE nome_usuario = :nomeUsuario", nativeQuery=true)
	public Usuario func201FindOne_PorUsuarioNome(@Param("nomeUsuario") String nomeUsuario);
	
	
	
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value="SELECT * "
			   + "FROM tb_usuarios "
			   + "WHERE visibilidade = :visibilidade", nativeQuery=true)
	public List<Usuario> func301FindOne_PorVisibilidade(@Param("visibilidade") String visibilidade);
	
	@Query(value="SELECT * "
			   + "FROM tb_usuarios "
			   + "ORDER BY id", nativeQuery=true)
	public List<Usuario> func302FindOne_OrderById();
	
	@Query(value="SELECT * "
			   + "FROM tb_usuarios "
			   + "WHERE visibilidade = :visibilidade "
			   + "ORDER BY id", nativeQuery=true)
	public List<Usuario> func303FindOne_PorVisibilidade_OrderById(@Param("visibilidade") String visibilidade);
	
	
	@Query(value="SELECT * "
			   + "FROM tb_usuarios "
			   + "WHERE id = :id", nativeQuery=true)
	public List<Usuario> func304FindOne_PorId(@Param("id") Integer id);
	
}
