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
@Table(name = "tb_menu4")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Menu4 implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=200)
	private String caminhoFicheiroXhtml;
	
	@Column(length=18)
	private String codigo;
	
	@Column(length=18)
	private String codigoPai;
	
	@Column(length=200)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;	
	
	@Column(length=50)
	private String icone;
	
	@ManyToOne
	@JoinColumn(name = "grupo_privilegio_id")
	private GrupoPrivilegio4 grupoPrivilegio;
	
	@ManyToOne
	@JoinColumn(name = "modulo_do_sistema_id")
	private ModuloDoSistema4 moduloSistema;
	
	
}
