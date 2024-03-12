package ao.co.appgestao.model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name = "tb_integrantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Integrante implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	private Pessoa pessoa;
	
	@Column(length=20)
	private String matricula;
	
	@Column(length=30)
	private Integer numeroAgente;
	
	@Column(length=30)
	private Date dtAdmissao;
	
	@ManyToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	@ManyToOne
	@JoinColumn(name = "area_especifica_id")
	private AreaEspecifica areaEspecifica;
	
	@ManyToOne
	@JoinColumn(name = "funcao_id")
	private Funcao funcao;
	
	@JoinColumn(name="carreira_id")
	@ManyToOne
	private Carreira carreira;
	
	@JoinColumn(name="categoria_id")
	@ManyToOne
	private Categoria categoria;
	
	@JoinColumn(name="especialidade_id")
	@ManyToOne
	private Especialidade especialidade;
	
	@JoinColumn(name="habilitacao_literaria_id")
	@ManyToOne
	private HabilitacaoLiteraria habilitacaoLiteraria;
	
	@JoinColumn(name="titulo_id")
	@ManyToOne
	private Titulo titulo;
	
	@Column(length=30)
	private Date dtRegistro;
	
	@Column(length=30)
	private Date dtUltAlt;
	
	@Column(length=50)
	private String usuRegistro;
	
	@Column(length=50)
	private String usuUltAlt;	
	
	@Column(length=200)
	private String fotografia;		
	
}
