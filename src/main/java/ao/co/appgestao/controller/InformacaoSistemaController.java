package ao.co.appgestao.controller;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import java.io.Serializable;

@Named(value = "isController")
@SessionScoped
public class InformacaoSistemaController implements Serializable {

	private static final long serialVersionUID = 9219845041653740770L;
	
	public String tituloSistemaTopo() {
		
		return "EscolaGEST - Sistema de Gestão Escolar";
		
	}
	
	public String tituloSistemaBase() {
		
		return "EscolaGEST © Copyright 2023";
		
	}
	
	public String tituloTemplate() {
		
		return "EscolaGEST";
		
	}
	
	/*public String descricaoUsuario(Usuario usuario) {
		
		String resultado = "";
		
		if(usuario != null) {
			
			String titulo = usuario.getIntegrante().getTitulo();
			String nome   = usuario.getIntegrante().getPessoa().getNome().trim();
			String funcao = usuario.getIntegrante().getFuncao().getNome();
			
			resultado     = "Saudações "+
					        (titulo == null || titulo == "" ? "" : titulo+" ")+
					        nome+
					        (funcao == null || funcao == "" ? "" : " | " + funcao);
			
		}
		System.out.println("Entrou na funçao descricaoUsuario..");
		return resultado;
		
	}*/

}
