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
@Table(name = "tb_coordenacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coordenacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=30)
	private String nome;
	
	/*@ManyToOne
	@JoinColumn(name = "integrante_coordenador_id")
	private Integrante coordenador;
	
	@ManyToOne
	@JoinColumn(name = "status_coordenacao_id")
	private StatusCoordenacao statusCoordenacao;
		
	@OneToOne
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@Column(length=300)
	private String responsabilidadesCoordenador;
	
	@Column(length=100)
	private String horarioAtendimento;*/
	
	
}
