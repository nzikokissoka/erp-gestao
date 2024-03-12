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
@Table(name = "tb_pessoas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=150)
	private String nome;
	
	@Column(length=200)
	private String filiacao;

	@Column(length=30)
	private Date dtNascimento;
	
	@Column(length=30)
	private String numeroBilheteIdentidade;
	
	@Column(length=1)
	private String sexo;
	
	@Column(length=15)
	private String estadoCivil;
	
	@Column(length=100)
	private String correioElectronico;
	
	@Column(length=100)
	private String correioElectronicoAlternativo;
	
	@Column(length=50)
	private String telefone;
	
	@Column(length=50)
	private String telefoneAlternativo;

	@Column(length=250)
	private String outrosContactos;
	
	@ManyToOne
	@JoinColumn(name = "naturalidade_id")
	private Municipio naturalidade;
	
	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;
	
	@Column(length=350)
	private String residencia;
	
	@Column(length=350)
	private String obs;
		
	@Column(length=30)
	private Date dtRegistro;
	
	@Column(length=30)
	private Date dtUltAlt;
		
	@Column(length=50)
	private String usuRegistro;
	
	@Column(length=50)
	private String usuUltAlt;

	@Column(length=1)
	private Integer ativo;
	
	@Column(length=3)
	private String visibilidade;


}
