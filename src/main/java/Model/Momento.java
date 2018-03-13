package Model;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.DateFormatter;

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
	
	public Date getData(Transponder t) {
		Date dateTime = null;
		 ArrayList<String> D =  (ArrayList<String>) Log.get(t.log);
	   
	      String Anterior = null;
	      for (String linhatime : D) {
	    	    //00:05:13.35 02/17/18;	    		  
	    	  int linha =  Integer.valueOf(linhatime.split("-")[0]);
	    	  
	    	  String data = linhatime.split("-")[1];
	    	  if(  linha > t.linha) {
	    		 
	    		 if(!data.equals("00/00/00")){
	    			 String str = t.Data+" "+data;

	    			 
	    			 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS MM/dd/yy");
	    			 try {
						dateTime =  dataMenosDias(new java.sql.Date(format.parse(str).getTime()),0);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			  
	    		 }else {
	    			 String str = t.Data+" "+Anterior;
	       			 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS MM/dd/yy");
	    			 try {
						dateTime =  dataMenosDias(new java.sql.Date(format.parse(str).getTime()),1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	    		 }
	    		 
	    		 return  dateTime;
	    	   }
	    	  Anterior = data;
	    	 
	    	}
	      
	         String str = t.Data+" "+Anterior;
   			 SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SS MM/dd/yy");
			 try {
				dateTime =  dataMenosDias(new java.sql.Date(format.parse(str).getTime()),1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 

			 
		return  dateTime;
	}
	
	
	
	private Date dataMenosDias(Date data, int dias) {
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(data);
		 cal.add(Calendar.DATE, -dias);
		 return cal.getTime();
		
	}

	public Map<String, Object> getLog() {
		return Log;
	}

	public void setLog(Map<String, Object> log) {
		Log = log;
	}
	
	
	

}
