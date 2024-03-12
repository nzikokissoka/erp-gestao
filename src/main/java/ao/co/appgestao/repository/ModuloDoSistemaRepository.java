package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.ModuloDoSistema;

@Repository
public interface ModuloDoSistemaRepository extends JpaRepository<ModuloDoSistema, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) as repeticoes " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3 and descricao = :descricaoSTR", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorDescricaoLen3(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3", nativeQuery = true)
	public String func102MaxCod_Len3();
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is not null and LEN(codigo) = 6", nativeQuery = true)
	public String func103MaxCod_Len6();
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is not null and LEN(codigo) = 6 and descricao = :descricaoSTR", nativeQuery = true)
	public int func104Count_RegistrosRepetidosPorDescricaoLen6(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6", nativeQuery = true)
	public String func105MaxCod_Len6(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE id = :moduloDoSistemaID", nativeQuery = true)
	public ModuloDoSistema func201FindOne_PorId(@Param("moduloDoSistemaID") Integer moduloDoSistemaID);	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3 and descricao = :descricaoSTR", nativeQuery = true)
	public ModuloDoSistema func202FindOne_PorDescricao(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3 and codigo = :codigoSTR", nativeQuery = true)
	public ModuloDoSistema func203FindOne_PorCodigo(@Param("codigoSTR") String codigoSTR);
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3", nativeQuery = true)
	public List<ModuloDoSistema> func301FindAll_PorCodigoLen3();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6", nativeQuery = true)
	public List<ModuloDoSistema> func302FindAll_PorCodigoLen6(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE LEN(codigo) = 6", nativeQuery = true)
	public List<ModuloDoSistema> func303FindAll_PorLen6();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE LEN(codigo) = 6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema> func304FindAll_PorLen6OrdemPorDescricao();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema> func305FindAll_PorCodigoLen6OrdemPorDescricao(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6 " +
		           "      AND id NOT IN (select modulo_do_sistema_id from tb_grupos_de_privilegios where grupo_id = :grupoID) " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema> func306FindAll_PorCodigoPaiEGrupo(@Param("codigoPaiSTR") String codigoPaiSTR, 
			                                                       @Param("grupoID") Integer grupoID);
	
}
