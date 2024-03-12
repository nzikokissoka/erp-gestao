package ao.co.appgestao.utilities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VariaveisConecaoDB {

	//variaveis de conexão à bd
	private String databaseURL = "jdbc:sqlserver://localhost;instance=MSSQLSERVER;databaseName=dbsistemas;encrypt=false";
	private String user        = "sa";
	private String password    = "admin2k4";
	
}
