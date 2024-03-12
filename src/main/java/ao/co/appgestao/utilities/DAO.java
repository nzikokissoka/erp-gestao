package ao.co.appgestao.utilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ao.co.appgestao.model.Area;
import ao.co.appgestao.model.AreaEspecifica;
import ao.co.appgestao.model.Categoria;
import ao.co.appgestao.model.Especialidade;
import ao.co.appgestao.model.Funcao;
import ao.co.appgestao.model.HabilitacaoLiteraria;
import ao.co.appgestao.model.Integrante;
import ao.co.appgestao.model.ModuloDoSistema4;
import ao.co.appgestao.model.Titulo;

public class DAO {

	//variaveis de conexão à bd
	String databaseURL = "jdbc:sqlserver://localhost;instance=MSSQLSERVER;databaseName=dbsistemas;encrypt=false";
	String user        = "sa";
	String password    = "admin2k4";
	
	
	
	//funções
	public String func01DataActualString() {
    	
    	String resultado = null;    	
    	String SELECT = "SELECT SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 1,4)+'-'+"
    			      + "       SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 6,2)+'-01' as currentDateENG";
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("currentDateENG");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func01DataRegistoActualString = "+e.getMessage());
		}
    	
    	return resultado;
    	
    }
	
	
	public String func02DataActualStringEng() {
    	
    	String resultado = null;    	
    	String SELECT = "SELECT SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 1,4)+'-'+"
    			      + "       SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 6,2)+'-'+"
    			      + "       SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 9,2) as currentDateENG";
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("currentDateENG");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func02DataRegistoActualStringEng = "+e.getMessage());
		}
    	
    	return resultado;
    	
    }
	
	
	public Date func02DataActualStringEng2() {
    	
		Date resultado = null;    	
    	String SELECT = "SELECT SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 1,4)+'-'+"
    			      + "       SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 6,2)+'-'+"
    			      + "       SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 9,2) as currentDateENG";
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getDate("currentDateENG");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func03DataActualStringEng2 = "+e.getMessage());
		}
    	
    	return resultado;
    	
    }
	
	
	public String func03DataActualStringPt() {
    	
    	String resultado = null;    	
    	String SELECT = "SELECT SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 9,2)+'/'+"
			          + "    	SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 6,2)+'/'+"
			          + "    	SUBSTRING(CONVERT(varchar(10), GETDATE(), 120), 1,4) as currentDatePT";
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("currentDatePT");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func03DataRegistoActualStringPt = "+e.getMessage());
		}
    	
    	return resultado;
    	
    }
	
	
	public String func04DataHoraActualString() {
    	
    	String resultado = null;    	
    	String SELECT = "SELECT CONVERT(varchar(10), GETDATE(), 120)+' '+"
    			      + "       CONVERT(varchar(8), GETDATE(), 108)+'.000' as currentDateTimeENG";
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("currentDateTimeENG");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func04DataHoraRegistoActualString = "+e.getMessage());
		}
    	
    	return resultado;
    	
    }
	
	
	public String func05DataHoraDiaSemanaString(Date dt) {
    	
    	String resultado = null;  
    	
    	//verifica a nulidade de dt
    	if(dt != null) {
    		
    		String SELECT = "SELECT (SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 9,2)+'/'+" + 
    				"                SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 6,2)+'/'+" + 
    				"	             SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 1,4)+' - '+" + 
    				"	            (SUBSTRING(CONVERT(varchar(19), '"+dt+"', 120), 11, 9))+' - '+" + 
    				"	             CASE DATENAME(WEEKDAY, '"+dt+"')" + 
    				"			       WHEN 'Monday'    THEN 'Segunda-feira' " + 
    				"			       WHEN 'Tuesday'   THEN 'Terça-feira' " + 
    				"			       WHEN 'Wednesday' THEN 'Quarta-feira' " + 
    				"			       WHEN 'Thursday'  THEN 'Quinta-feira' " + 
    				"			       WHEN 'Friday'    THEN 'Sexta-feira' " + 
    				"			       WHEN 'Saturday'  THEN 'Sábado' " + 
    				"			       WHEN 'Sunday'    THEN 'Domingo' " + 
    				"	             END) as dataString";
        	
        	try {
    			
        		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
        		Statement statement   = connection.createStatement();
        		ResultSet result      = statement.executeQuery(SELECT);
        		
        		while (result.next()) {
        			resultado = result.getString("dataString");
        		}
        		
        		connection.close();
        		
    		} catch (SQLException e) {
    			System.out.println("--- ERRO NA DAO: func05DataHoraDiaSemanaActualString = "+e.getMessage());
    		}
    		
    	}    	
    	
    	return resultado;
    	
    }
	
	
	public String func06DataHoraDiaSemanaString2(String dt) {
    	
    	String resultado = null;  
    	
    	//verifica a nulidade de dt
    	if(dt != null) {
    		
    		String SELECT = "SELECT (SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 9,2)+'/'+" + 
    				"                SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 6,2)+'/'+" + 
    				"	             SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 1,4)+' '+" + 
    				"	            (SUBSTRING(CONVERT(varchar(19), '"+dt+"', 120), 11, 9))+' '+" + 
    				"	             CASE DATENAME(WEEKDAY, '"+dt+"')" + 
    				"			       WHEN 'Monday'    THEN 'Segunda-feira' " + 
    				"			       WHEN 'Tuesday'   THEN 'Terça-feira' " + 
    				"			       WHEN 'Wednesday' THEN 'Quarta-feira' " + 
    				"			       WHEN 'Thursday'  THEN 'Quinta-feira' " + 
    				"			       WHEN 'Friday'    THEN 'Sexta-feira' " + 
    				"			       WHEN 'Saturday'  THEN 'Sábado' " + 
    				"			       WHEN 'Sunday'    THEN 'Domingo' " + 
    				"	             END) as dataString";
        	
        	try {
    			
        		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
        		Statement statement   = connection.createStatement();
        		ResultSet result      = statement.executeQuery(SELECT);
        		
        		while (result.next()) {
        			resultado = result.getString("dataString");
        		}
        		
        		connection.close();
        		
    		} catch (SQLException e) {
    			System.out.println("--- ERRO NA DAO: func06DataHoraDiaSemanaString2 = "+e.getMessage());
    		}
    		
    	}    	
    	
    	return resultado;
    	
    }
	
	
	public String func07DiaDeSemana(String dt) {
    	
    	String resultado = null;  
    	
    	//verifica a nulidade de dt
    	if(dt != null) {
    		
    		String SELECT = "SELECT CASE DATENAME(WEEKDAY, '"+dt+"') " + 
    				"			       WHEN 'Monday'    THEN 'Segunda-feira' " + 
    				"			       WHEN 'Tuesday'   THEN 'Terça-feira' " + 
    				"			       WHEN 'Wednesday' THEN 'Quarta-feira' " + 
    				"			       WHEN 'Thursday'  THEN 'Quinta-feira' " + 
    				"			       WHEN 'Friday'    THEN 'Sexta-feira' " + 
    				"			       WHEN 'Saturday'  THEN 'Sábado' " + 
    				"			       WHEN 'Sunday'    THEN 'Domingo' " + 
    				"	             END as dataString";
        	
        	try {
    			
        		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
        		Statement statement   = connection.createStatement();
        		ResultSet result      = statement.executeQuery(SELECT);
        		
        		while (result.next()) {
        			resultado = result.getString("dataString");
        		}
        		
        		connection.close();
        		
    		} catch (SQLException e) {
    			System.out.println("--- ERRO NA DAO: func07DiaDeSemana = "+e.getMessage());
    		}
    		
    	}    	
    	
    	return resultado;
    	
    }
	
	
	public String func08DataHoraCorrente(Timestamp dt) {
    	
    	String resultado = null;
    	String SELECT = "SELECT (SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 9,2)+'/'+" + 
				"                SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 6,2)+'/'+" + 
				"	             SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 1,4)+''+" + 
				
				"	            (SUBSTRING(CONVERT(varchar(19), '"+dt+"', 120), 11, 9))+' - '+" + 
				
				"	             CASE DATENAME(WEEKDAY, '"+dt+"')" + 
				"			       WHEN 'Monday'    THEN 'Segunda-feira' " + 
				"			       WHEN 'Tuesday'   THEN 'Terça-feira' " + 
				"			       WHEN 'Wednesday' THEN 'Quarta-feira' " + 
				"			       WHEN 'Thursday'  THEN 'Quinta-feira' " + 
				"			       WHEN 'Friday'    THEN 'Sexta-feira' " + 
				"			       WHEN 'Saturday'  THEN 'Sábado' " + 
				"			       WHEN 'Sunday'    THEN 'Domingo' " + 
				"	             END) as dataString";
    			       
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("dataString");
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func08DataHoraCorrente = "+e.getMessage());
		}    	
    	
    	return resultado;
    	
    }
	
	
	public String func09DataNomeDia(Date dt) {
    	
    	String resultado = null;
    	String SELECT = "SELECT (SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 9,2)+'/'+" + 
				"                SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 6,2)+'/'+" + 
				"	             SUBSTRING(CONVERT(varchar(10), '"+dt+"', 120), 1,4)+' '+" + 
				
				"	             CASE DATENAME(WEEKDAY, '"+dt+"')" + 
				"			       WHEN 'Monday'    THEN 'Segunda-feira' " + 
				"			       WHEN 'Tuesday'   THEN 'Terça-feira' " + 
				"			       WHEN 'Wednesday' THEN 'Quarta-feira' " + 
				"			       WHEN 'Thursday'  THEN 'Quinta-feira' " + 
				"			       WHEN 'Friday'    THEN 'Sexta-feira' " + 
				"			       WHEN 'Saturday'  THEN 'Sábado' " + 
				"			       WHEN 'Sunday'    THEN 'Domingo' " + 
				"	             END) as dataString";
    			       
    	
    	try {
			
    		Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
    		Statement statement   = connection.createStatement();
    		ResultSet result      = statement.executeQuery(SELECT);
    		
    		while (result.next()) {
    			resultado = result.getString("dataString");    			
    		}
    		
    		connection.close();
    		
		} catch (SQLException e) {
			System.out.println("--- ERRO NA DAO: func09DataNomeDia = "+e.getMessage());
		}    	
    	
    	return resultado;
    	
    }
	
	
	public Integer func10PegaDiferencaDatas(Date dt1) {
    	
    	Integer resultado = null;
    	String SELECT     = "SELECT DATEDIFF(YYYY, '"+dt1.toString()+"', GETDATE()) as anosDiferenca";
    	
    	try {
			
			Connection connection = DriverManager.getConnection(databaseURL, user, password);    				
			Statement statement   = connection.createStatement();
			ResultSet result      = statement.executeQuery(SELECT);
			
			while (result.next()) {								
				resultado  = result.getInt("anosDiferenca");				
			}
			
			connection.close();
			
		} catch (Exception e) {
			System.out.println("--- ERRO NA DAO SELECT func10PegaDiferencaDatas = "+e.getMessage());
		}
    	
    	return resultado;
    }
	
	public void/*List<Integrante>*/ func11PegaListaIntegrantes(Funcao funcao, 
                                                       Titulo titulo, 
                                                       Area area, 
                                                       AreaEspecifica areaEspecifica, 
                                                       HabilitacaoLiteraria habilitacaoLiteraria,
                                                       Especialidade especialidade, 
                                                       Categoria categoria) {
		List<Integrante> resultado = null;
    	List<Integrante> lista     = new ArrayList<>();
    	
    	String funcaoPesq               = "";
    	String tituloPesq               = "";
    	String areaPesq                 = "";
    	String areaEspecificaPesq       = ""; 
    	String habilitacaoLiterariaPesq = "";
    	String especialidadePesq        = ""; 
    	String categoriaPesq            = "";
    	
    	if(funcao != null && funcao.getId() != null) {
    		funcaoPesq = " AND i.funcao_id = "+funcao.getId();
    	}
    	
    	if(titulo != null) {
    		tituloPesq = " AND i.titulo_id = "+titulo.getId();
    	}
    	
    	if(area != null) {
    		areaPesq = " AND i.area_id = "+area.getId();
    	}
    	
    	if(areaEspecifica != null) {
    		areaEspecificaPesq = " AND i.area_especifica_id = "+areaEspecifica.getId();
    	}
    	
    	if(habilitacaoLiteraria != null) {
    		habilitacaoLiterariaPesq = " AND i.habilitacao_literaria_id = "+habilitacaoLiteraria.getId();
    	}
    	
    	if(especialidade != null) {
    		especialidadePesq = " AND i.especialidade_id = "+especialidade.getId();
    	}
    	
    	if(categoria != null) {
    		categoriaPesq = " AND i.categoria_id = "+categoria.getId();
    	}
    	
    	String SELECT                   =  "SELECT i.* "  
				                         + "FROM tb_integrantes i, tb_pessoas p  "
				                         + "WHERE i.pessoa_id = p.id "
				                         + funcaoPesq+tituloPesq+areaPesq+areaEspecificaPesq
				                         + habilitacaoLiterariaPesq+especialidadePesq+categoriaPesq
				                         + " ORDER BY p.nome ";
    	
    	System.out.println(SELECT);
    	
		
		//return null;
		
	}
	
	
	public Integer func12EliminaModuloDoSistema(ModuloDoSistema4 moduloDoSistema) {
		System.out.println("---- moduloDoSistema dentro de fun12 da dao= "+moduloDoSistema);
		System.out.println("---- moduloDoSistema len = "+moduloDoSistema.getCodigo().length());
    	Integer resultado = null;
    	
    	//verifica nulidade de enquete
    	if(moduloDoSistema != null && moduloDoSistema.getId() != null) {
    		
    		String DELETE = "DELETE FROM tb_modulos_do_sistema4 "
		                  + "WHERE codigo LIKE '"+moduloDoSistema.getCodigo().trim()+"%'";
    		System.out.println("-- DELETE = "+DELETE);
    		
    		try {
    			
    			Connection connection               = DriverManager.getConnection(databaseURL, user, password);
        		PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        		
        		int resultSet = preparedStatement.executeUpdate();
        		
                resultado = resultSet;
        		
        		connection.close();
        		
    		} catch (SQLException e) {
    			System.out.println("--- ERRO NA DAO func12EliminaModuloDoSistema = = "+e.getMessage());
    		} 
    		
    		
    		
    		/*String UPDATE = "DELETE FROM tb_modulos_do_sistema4 "
        			      + "WHERE codigo like ? ";
        	
        	try {
				
        		Connection connection               = DriverManager.getConnection(databaseURL, user, password);
        		PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        		
        		preparedStatement.setString(1, "'"+moduloDoSistema.getCodigo().trim()+"'%");              
                
                int resultSet = preparedStatement.executeUpdate();
        		
                resultado = resultSet;
        		
			} catch (SQLException e) {
				System.out.println("--- ERRO NA DAO func12EliminaModuloDoSistema = = "+e.getMessage());
			}*/
    		
    	}    	
    	
    	return resultado;
    	
    }
	
	
	
	
}
