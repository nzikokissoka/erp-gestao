package ao.co.appgestao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.GrupoPrivilegio2;

@Repository
public interface GrupoPrivilegioRepository2 extends JpaRepository<GrupoPrivilegio2, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id = :grupoID", nativeQuery = true)
	public int func101Count_RegistrosRepetidosPorGrupoId(@Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE codigo = :codigoSTR6 or (LEN(codigo) = 9 and codigo_pai = :codigoSTR6)", nativeQuery = true)
	public int func102Count_RegistrosRepetidosPorCodigoLen6(@Param("codigoSTR6") String codigoSTR6);
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id = :grupoID AND " +
				   "      codigo   = :codigoSTR", nativeQuery = true)
	public int func103FindAll_PorGrupoIdECodigo(@Param("grupoID") Integer grupoID, @Param("codigoSTR") String codigoSTR);
		
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID AND " +
				   "      LEN(codigo) = 3 AND " +
				   "      codigo   LIKE :codigoSTR3% AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func104Count_OutrasInsercoesLen3PorGrupoIDECodigoLen3(@Param("grupoID") Integer grupoID, 
			                                                         @Param("codigoSTR3") String codigoSTR3);
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID AND " +
				   "      LEN(codigo) = 6 AND " +
				   "      codigo_pai  = :codigoSTR3 AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func105Count_OutrasInsercoesLen6PorGrupoIDECodigoLen3(@Param("grupoID") Integer grupoID, 
			                                                         @Param("codigoSTR3") String codigoSTR3);
	
	
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID AND " +
				   "      LEN(codigo) = 9 AND " +
				   "      codigo_pai  = :codigoSTR6 AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func106Count_OutrasInsercoesLen9PorGrupoIDECodigoLen6(@Param("grupoID") Integer grupoID, 
			                                                         @Param("codigoSTR6") String codigoSTR6);
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID AND " +
				   "      LEN(codigo) = 9 AND " +
				   "      codigo   LIKE :codigoSTR6% AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func107Count_OutrasInsercoesLen6PorGrupoIDECodigoLen6(@Param("grupoID") Integer grupoID, 
			                                                         @Param("codigoSTR6") String codigoSTR6);
	
	
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID AND " +				   
				   "      codigo_pai  is null    AND " +
				   "      LEN(codigo) = 3        AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func110Count_OutrasInsercoesLen3PorCodigoPai(@Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID    AND " +				   
				   "      codigo_pai  = :codigoSTR3 AND " +
				   "      LEN(codigo) = 6           AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func111Count_OutrasInsercoesLen6PorCodigoPaiLen3(@Param("grupoID") Integer grupoID, 
			                                                    @Param("codigoSTR3") String codigoSTR3);
	
	
	
	
	@Query(value = "SELECT count(*) " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id    = :grupoID    AND " +				   
				   "      codigo_pai  = :codigoSTR6 AND " +
				   "      LEN(codigo) = 9           AND " +
				   "      (deletar       is not null or " +
				   "       editar        is not null or " +
				   "       novo          is not null or " +
				   "       pesquisar     is not null or " +
				   "       tempo_ativado is not null or " +
				   "       visibilidade  is not null)", nativeQuery = true)
	public int func112Count_OutrasInsercoesLen9PorCodigoPaiLen6(@Param("grupoID") Integer grupoID, 
			                                                    @Param("codigoSTR6") String codigoSTR6);
	
	
	
	//--- funcões func200: Seleciona objecto -----------------
	@Query(value = "SELECT * " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id = :grupoID AND " +
				   "      codigo   = :codigoSTR", nativeQuery = true)
	public GrupoPrivilegio2 func201FindAll_PorGrupoIdECodigo(@Param("grupoID") Integer grupoID, @Param("codigoSTR") String codigoSTR);
		
		
	
	
	
	//--- funcões func300: Seleciona lista -------------------
	@Query(value = "SELECT * " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE grupo_id = :grupoID AND " +
				   "      codigo IN (select codigo from tb_modulos_do_sistema2 where caminho_ficheiro_xhtml is not null)", nativeQuery = true)
	public List<GrupoPrivilegio2> func301FindAll_PorGrupoId(@Param("grupoID") Integer grupoID);
	
	
	@Query(value = "SELECT * " + 
		           "FROM tb_grupos_de_privilegios2 " + 
				   "WHERE LEN(codigo) = 9 and  codigo LIKE :codigoSTR6%", nativeQuery = true)
	public List<GrupoPrivilegio2> func302FindAll_CodigoLen9PorCodigoLen6(@Param("codigoSTR6") String codigoSTR6);
	
}
