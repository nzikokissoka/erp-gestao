package ao.co.appgestao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ao.co.appgestao.model.TipoDeCurso;

@Repository
public interface TipoDeCursoRepository extends JpaRepository<TipoDeCurso, Integer>{

	//--- funcões func100: contagens -------------------------
	@Query(value = "SELECT count(*) "
			     + "FROM tb_tipos_cursos", nativeQuery = true)
	public int func100Count_Registros();

	// --- funcões func200: Seleciona objecto -----------------

	// --- funcões func300: Seleciona lista -------------------
	
}
