package ao.co.appgestao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Curso implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=30)
	private String nome;
	
	/*@ManyToMany
	@JoinColumn(name = "coordenacao_id")
	private Coordenacao coordenacao;*/
	
}
