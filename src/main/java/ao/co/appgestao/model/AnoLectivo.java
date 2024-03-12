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
@Table(name = "tb_ano_lectivo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AnoLectivo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=1)
	private Integer ano;
	
	@Column(length=150)
	private String descricao;
	
	/*@Column(length=30)
	private Date dtInicio;
	
	@Column(length=30)
	private Date dtTermino;
	
	@Column(length=30)
	private Date dtInicioMatricula;
	
	@Column(length=30)
	private Date dtTerminoMatricula;*/
	
	@Column(length=30)
	private Date dtRegistro;
	
	@Column(length=350)
	private String obs;
	
	@Column(length=30)
	private Date dtUltAlt;	
	
	@Column(length=50)
	private String usuRegistro;
	
	@Column(length=50)
	private String usuUltAlt;	
	
	@ManyToOne
	@JoinColumn(name = "status_ano_lectivo_id")
	private StatusAnoLectivo statusAnoLectivo;

}
