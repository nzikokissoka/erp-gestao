package ao.co.appgestao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_modulos_do_sistema4")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ModuloDoSistema4 implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=21)
	private String codigo;
	
	@Column(length=21)
	private String codigoPai;
	
	@Column(length=200)
	private String caminhoFicheiroXhtml;
	
	@Column(length=200)
	private String descricao;
	
	@Column(length=50)
	private String icone;
	
}
