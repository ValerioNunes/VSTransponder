package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Momento {
	String texto;
	Map<String, Object> Log = new HashMap<String,Object>();
	
	public void  busca(String log,int linha,String texto) {
	
		this.texto  = texto;
		String data =   texto.split("to ")[1];
		
		if(Log.get(log) != null) {
			
			 //Map<Integer,String> D = (Map<Integer, String>) Log.get(log);
			 ArrayList<String> D = (ArrayList<String>) Log.get(log);
			 D.add(linha+"-"+data);
			 Log.put(log, D);
			 
		}else {
			
			 ArrayList<String> D = new ArrayList<String>();
			 D.add(linha+"-"+data);
			Log.put(log, D);
		}
	
		//System.out.println(data);
	}
	
	public LocalDateTime getData(Transponder t) {
		LocalDateTime dateTime = null;
		 ArrayList<String> D =  (ArrayList<String>) Log.get(t.log);
	   
	      String Anterior = null;
	      for (String linhatime : D) {
	    	    //00:05:13.35 02/17/18;	    		  
	    	  int linha =  Integer.valueOf(linhatime.split("-")[0]);
	    	  
	    	  String data = linhatime.split("-")[1];
	    	  if(  linha > t.linha) {
	    		 
	    		 if(!data.equals("00/00/00")){
	    			 String str = t.Data+" "+data;
	    			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS MM/dd/yy");
	    			 dateTime = LocalDateTime.parse(str, formatter);
	    			
	    			  
	    		 }else {
	    			 String str = t.Data+" "+Anterior;
	    			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS MM/dd/yy");
	    			 dateTime = LocalDateTime.parse(str, formatter).minusDays(1);	
	    		 }
	    		 
	    		 return  dateTime;
	    	   }
	    	  Anterior = data;
	    	 
	    	}
	      
	         String str = t.Data+" "+Anterior;
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SS MM/dd/yy");
			 dateTime = LocalDateTime.parse(str, formatter).minusDays(1);	
			 
		return  dateTime;
	}
	
	

	public Map<String, Object> getLog() {
		return Log;
	}

	public void setLog(Map<String, Object> log) {
		Log = log;
	}
	
	
	

}
