package ao.co.appgestao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_grupos_de_privilegios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GrupoPrivilegio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;	
	
	@ManyToOne
	@JoinColumn(name = "modulo_do_sistema_id")
	private ModuloDoSistema moduloSistema;
	
	@Column(length=1)
	private Integer novo;
	
	@Column(length=1)
	private Integer salvar;
	
	@Column(length=1)
	private Integer editar;
	
	@Column(length=1)
	private Integer deletar;
	
	@Column(length=1)
	private Integer pesquisar;
	
	@Column(length=3)
	private String visibilidade;
	
	@Column(length=15)
	private Integer tempoAtivado;
	
}
