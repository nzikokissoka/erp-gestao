package ao.co.appgestao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_coordenacoes_area_formacao")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CoordenacaoAreaFormacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/*@Column(length=30)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "integrante_coordenador_area_formacao_id")
	private Integrante coordenacaoAreaFormacao;
	
	@ManyToOne
	@JoinColumn(name = "status_coordenacao_id")
	private StatusCoordenacao statusCoordenacao;
		
	@OneToOne
	@JoinColumn(name = "coordenacao_id")
	private Coordenacao coordenacao;
	
	@Column(length=300)
	private String responsabilidadesCoordenadorAreaFormacao;*/	
	
	
}
