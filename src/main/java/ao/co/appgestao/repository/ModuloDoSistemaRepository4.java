package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.ModuloDoSistema4;

@Repository
public interface ModuloDoSistemaRepository4 extends JpaRepository<ModuloDoSistema4, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value="SELECT count(*) "
			   + "FROM tb_modulos_do_sistema4", nativeQuery=true)
	public int func100Count_Registros();
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = 3 and " +
			       "      descricao = :descricaoSTR", nativeQuery = true)
	public int func101Count_RepeticoesPorDescricaoLen3(@Param("descricaoSTR") String descricaoSTR);
	
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = :lenInt and " +
			       "      descricao   = :descricaoSTR", nativeQuery = true)
	public int func101Count_RepeticoesPorDescricaoLenGenerico(@Param("lenInt") Integer lenInt, 
			                                                  @Param("descricaoSTR") String descricaoSTR);
	
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo3 " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE LEN(codigo) = 3", nativeQuery = true)
	public String func102MaxCod_Len3();
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = 6 and " +
			       "      descricao = :descricaoSTR", nativeQuery = true)
	public int func103Count_RepeticoesPorDescricaoLen6(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo6 " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE LEN(codigo) = 6 and codigo_pai = :codigoPaiSTR", nativeQuery = true)
	public String func104MaxCod_Len6(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo9 " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE LEN(codigo) = 9 and codigo_pai = :codigoPaiSTR", nativeQuery = true)
	public String func105MaxCod_Len9(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = 9 and " +
			       "      descricao = :descricaoSTR", nativeQuery = true)
	public int func106Count_RepeticoesPorDescricaoLen9(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo12 " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE LEN(codigo) = 12 and codigo_pai = :codigoPaiSTR", nativeQuery = true)
	public String func108MaxCod_Len12(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT MAX(codigo) as max_codigo15 " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE LEN(codigo) = 15 and codigo_pai = :codigoPaiSTR", nativeQuery = true)
	public String func109MaxCod_Len15(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = 12 and " +
			       "      descricao = :descricaoSTR", nativeQuery = true)
	public int func110Count_RepeticoesPorDescricaoLen12(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE LEN(codigo) = 15 and " +
			       "      descricao = :descricaoSTR", nativeQuery = true)
	public int func111Count_RepeticoesPorDescricaoLen15(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT count(*) as repeticoes " + 
				   "FROM tb_modulos_do_sistema4 " + 
				   "WHERE id                     = :id and " +
				   "      LEN(codigo)            = 3   and " +
			       "      caminho_ficheiro_xhtml like %:dashboardSTR%", nativeQuery = true)
	public int func112Count_RepeticoesDashBoardXhtmlPorCaminhoLen3(@Param("id") Integer id, 
			                                                       @Param("dashboardSTR") String dashboardSTR);
	
	
	
	@Query(value =  "SELECT count(*) "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 6 and "
		           +"      codigo_pai  = :codigoPaiSTR and "
		           +"      caminho_ficheiro_xhtml like %:caminhoPrefixoSTR%", nativeQuery = true)
	public int func113Count_RepeticoesModulosComDashBoardAdm(@Param("codigoPaiSTR")      String codigoPaiSTR, 
			                                                 @Param("caminhoPrefixoSTR") String caminhoPrefixoSTR);
	
	
	
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE id = :moduloDoSistemaID", nativeQuery = true)
	public ModuloDoSistema4 func201FindOne_PorId(@Param("moduloDoSistemaID") Integer moduloDoSistemaID);	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3 and descricao = :descricaoSTR", nativeQuery = true)
	public ModuloDoSistema4 func202FindOne_PorDescricao(@Param("descricaoSTR") String descricaoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3 and codigo = :codigoSTR", nativeQuery = true)
	public ModuloDoSistema4 func203FindOne_PorCodigo(@Param("codigoSTR") String codigoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = :codigoSTR", nativeQuery = true)
	public ModuloDoSistema4 func204FindOne_PorCodigoStr(@Param("codigoSTR") String codigoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = (select codigo_pai from  tb_modulos_do_sistema4 where codigo = :codigoSTR6)", nativeQuery = true)
	public ModuloDoSistema4 func205FindOne_CodigoLen3PorCodigoLen6(@Param("codigoSTR6") String codigoSTR6);
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE caminho_ficheiro_xhtml LIKE %:caminhoXhtmlSTR%", nativeQuery = true)
	public ModuloDoSistema4 func206FindOne_PorCaminhoXHTMLStr(@Param("caminhoXhtmlSTR") String caminhoXhtmlSTR);
	
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE id = :id", nativeQuery = true)
	public List<ModuloDoSistema4> func300FindAll_PorId(@Param("id") Integer id);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai is null and LEN(codigo) = 3", nativeQuery = true)
	public List<ModuloDoSistema4> func301FindAll_PorCodigoLen3();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 6 and codigo_pai = :codigoPaiLen3STR", nativeQuery = true)
	public List<ModuloDoSistema4> func302FindAll_PorCodigoPaiLen3(@Param("codigoPaiLen3STR") String codigoPaiLen3STR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 9 and codigo_pai = :codigoPaiLen6STR", nativeQuery = true)
	public List<ModuloDoSistema4> func303FindAll_PorCodigoPaiLen6(@Param("codigoPaiLen6STR") String codigoPaiLen6STR);
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 12 and codigo_pai = :codigoPaiLen9STR", nativeQuery = true)
	public List<ModuloDoSistema4> func303FindAll_PorCodigoPaiLen9(@Param("codigoPaiLen9STR") String codigoPaiLen9STR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 15 and codigo_pai = :codigoPaiLen12STR", nativeQuery = true)
	public List<ModuloDoSistema4> func303FindAll_PorCodigoPaiLen12(@Param("codigoPaiLen12STR") String codigoPaiLen12STR);
	
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func304FindAll_PorLen6OrdemPorDescricao();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func305FindAll_PorCodigoLen6OrdemPorDescricao(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai = :codigoPaiSTR and LEN(codigo) = 6 " +
		           "      AND id NOT IN (select modulo_do_sistema_id from tb_grupos_de_privilegios2 where grupo_id = :grupoID) " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func306FindAll_PorCodigoPaiEGrupo(@Param("codigoPaiSTR") String codigoPaiSTR, 
			                                                        @Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo_pai LIKE :codigoPaiSTR% " +
		           "      AND caminho_ficheiro_xhtml is not null " +
		           "      AND id NOT IN (select modulo_do_sistema_id from tb_grupos_de_privilegios4 where grupo_id = :grupoID) " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func307FindAll_PorCodigoPaiEGrupo(@Param("codigoPaiSTR") String codigoPaiSTR, 
			                                                        @Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE caminho_ficheiro_xhtml is not null " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func308FindAll_SubModulos();
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = :codigoSTR and LEN(codigo) = 3 " +
		           "      AND codigo_pai is null " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func309FindAll_PorCodigoLen3(@Param("codigoSTR") String codigoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = :codigoSTR and LEN(codigo) = 6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func310FindAll_PorCodigoLen6(@Param("codigoSTR") String codigoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = :codigoSTR and LEN(codigo) = 9 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func311FindAll_PorCodigoLen9(@Param("codigoSTR") String codigoSTR);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE codigo = :codigoSTR3 or (LEN(codigo) = 6 and codigo_pai = :codigoSTR3) or (LEN(codigo) = 9 and codigo like :codigoSTR3%) " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func311FindAll_PorCodigoLen3E6E9(@Param("codigoSTR3") String codigoSTR3);
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE codigo = :codigoSTR6 or (LEN(codigo) = 9 and codigo_pai = :codigoSTR6) " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func312FindAll_PorCodigoLen6E9(@Param("codigoSTR6") String codigoSTR6);
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
			       "WHERE codigo_pai = :codigoSTR6 " +
		           "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func313FindAll_CodigoLen9PorCodigoLen6(@Param("codigoSTR6") String codigoSTR6);
	
	
	
	
	
	
	
	@Query(value = "SELECT * " 
			       + "FROM tb_modulos_do_sistema4 "  
		           + "WHERE codigo_pai LIKE :codigoPaiSTR% "
		           + "      AND LEN(codigo) = :lenID " 
		           + "      AND id NOT IN (select modulo_do_sistema_id from tb_grupos_de_privilegios4 where grupo_id = :grupoID) " 
		           + "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func320FindAll_PorCodigoPaiEGrupo(@Param("codigoPaiSTR") String codigoPaiSTR, 
			                                                        @Param("grupoID") Integer grupoID, 
			                                                        @Param("lenID") Integer lenID);
	
	
	
	@Query(value = "SELECT * " 
			       + "FROM tb_modulos_do_sistema4 "  
		           + "WHERE codigo_pai LIKE :codigoPaiSTR% "
		           + "      AND LEN(codigo) = :lenID " 
		           + "      AND id NOT IN (select gp.modulo_do_sistema_id "
		           + "                     from tb_grupos_de_privilegios4 gp, tb_modulos_do_sistema4 ms "
		           + "                     where gp.modulo_do_sistema_id = ms.id and "
		           + "                           gp.grupo_id = :grupoID and "
		           + "                           LEN(gp.codigo) = :lenID and "
		           + "                           ms.caminho_ficheiro_xhtml is not null) " 
		           + "ORDER BY descricao", nativeQuery = true)
	public List<ModuloDoSistema4> func320FindAll_PorCodigoPaiEGrupo2(@Param("codigoPaiSTR") String codigoPaiSTR, 
			                                                         @Param("grupoID") Integer grupoID, 
			                                                         @Param("lenID") Integer lenID);
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE id = :id", nativeQuery = true)
	public List<ModuloDoSistema4> func321FindAll_PorId(@Param("id") Integer id);
	
	
	
	
	@Query(value = "SELECT * " + 
			       "FROM tb_modulos_do_sistema4 " + 
		           "WHERE LEN(codigo) = 3 and codigo_pai is null " +
		           "ORDER BY id", nativeQuery = true)
	public List<ModuloDoSistema4> func350FindAll_PorCodigoLen3MenuAdm();
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 6 and codigo_pai = :codigoPaiSTR "
		           +"ORDER BY id", nativeQuery = true)
	public List<ModuloDoSistema4> func351FindAll_PorCodigoLen6MenuAdm(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 9 and codigo_pai = :codigoPaiSTR "
		           +"ORDER BY id", nativeQuery = true)
	public List<ModuloDoSistema4> func352FindAll_PorCodigoLen9MenuAdm(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 12 and codigo_pai = :codigoPaiSTR "
		           +"ORDER BY id", nativeQuery = true)
	public List<ModuloDoSistema4> func353FindAll_PorCodigoLen12MenuAdm(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 15 and codigo_pai = :codigoPaiSTR "
		           +"ORDER BY id", nativeQuery = true)
	public List<ModuloDoSistema4> func354FindAll_PorCodigoLen15MenuAdm(@Param("codigoPaiSTR") String codigoPaiSTR);
	
	
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE caminho_ficheiro_xhtml like:caminhoPrefixoSTR", nativeQuery = true)
	public List<ModuloDoSistema4> func360FindAll_DashBoardPorCaminhoAdm(@Param("caminhoPrefixoSTR") String caminhoPrefixoSTR);
	
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE LEN(codigo) = 6 and "
		           +"      codigo_pai  = :codigoPaiSTR and "
		           +"      caminho_ficheiro_xhtml like %:caminhoPrefixoSTR%", nativeQuery = true)
	public List<ModuloDoSistema4> func361FindAll_DashBoardPorCaminhoAdm(@Param("codigoPaiSTR")      String codigoPaiSTR, 
			                                                            @Param("caminhoPrefixoSTR") String caminhoPrefixoSTR);
	
	
	@Query(value =  "SELECT * "  
			       +"FROM tb_modulos_do_sistema4 " 
		           +"WHERE codigo_pai = :codigoPaiSTR and "
		           +"      id not in (select id from tb_modulos_do_sistema4 "
		           +"                 where caminho_ficheiro_xhtml like %:caminhoPrefixoSTR%)", nativeQuery = true)
	public List<ModuloDoSistema4> func362FindAll_OutrosItensPorCaminhoAdm(@Param("codigoPaiSTR")      String codigoPaiSTR, 
			                                                              @Param("caminhoPrefixoSTR") String caminhoPrefixoSTR);
	
	
}
