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

import org.mindrot.jbcrypt.BCrypt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(length=30)
	private String nomeUsuario;
	
	@Column(length=80)
	private String senha;
	
	@JoinColumn(name="integrante_id")
	@ManyToOne	
	private Integrante integrante;
	
	@Column(length=15)
	private String perfil;

	@ManyToOne
	@JoinColumn(name = "grupo_id")
	private Grupo grupo;
	
	@Column(length=3)
	private String visibilidade;
	
	@Column(length=3)
	private Integer acessos;
	
	// Função que encripta a senha antes de armazená-la na base de dados
	public void encriptaSenha(String senha) {
		
		String hashedSenha = BCrypt.hashpw(senha, BCrypt.gensalt());
		
		this.senha = hashedSenha;
		
	}
	
	public String senhaEncriptada(String senha) {
		
		return BCrypt.hashpw(senha, BCrypt.gensalt());
		
	}
	
	// Função que verifica se a senha fornecida corresponde à senha encriptada
	public boolean verificaSenhaEncriptada(String senha) {
		
		return BCrypt.checkpw(senha, this.senha);
		
	}

}
