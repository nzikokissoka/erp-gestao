package ao.co.appgestao.utilities;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class UtilDatas {

	
	public static String func01FormataDateUtilparaDateStringDDMMYYYY(Date dt) {
		
		String resultado = null;
		
		if(dt != null) {
  			resultado = DateFormatUtils.format(dt, "dd-MM-yyyy");  	
  		}
						
		return resultado;
				
	}
	
	public static String func02FormataDateUtilparaDateStringYYYYMMDD(Date dt) {
		
		String resultado = null;
		
		if(dt != null) {
  			resultado = DateFormatUtils.format(dt, "yyyy-MM-dd");  	
  		}
						
		return resultado;
				
	}
	
	public static java.sql.Date func03ConverteDateUtilparaDateSQL(Date dt) {
		
		java.sql.Date resultado = null;
		
		if(dt != null) {
			resultado = java.sql.Date.valueOf(func02FormataDateUtilparaDateStringYYYYMMDD(dt));
		}
		
		return resultado;
		
	}
	
	
	public static java.sql.Date func04ConverteDateUtilparaDateSQL(java.util.Date dtUtil) {		
		return new java.sql.Date(dtUtil.getTime());		
	}
	
	
	public static java.util.Date func05ConverteDateSQLparaDateUtil(java.sql.Date dtSql) {		
		return new java.util.Date(dtSql.getTime());		
	}
	
	
}
