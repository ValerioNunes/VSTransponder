package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import DB.DataBase;
import Model.TransponderBD;

public class DaoTransponder {
	Connection c;
	ArrayList<TransponderBD> transponderBD;
	DataBase db =  new DataBase();
	
	public DaoTransponder() {
		super();
		this.c = db.iniciar();
	}

	public ArrayList<TransponderBD> salvar(ArrayList<TransponderBD> transponderBD){
		
		
		if(transponderBD == null)
				return null;
		
		ArrayList<TransponderBD> transBD = getTodosTransposnders();
		ArrayList<TransponderBD> newTransBD = new ArrayList<TransponderBD>();
		
		
		Set <String>  transSet = new  HashSet <String>();
		
		for(TransponderBD t :  transBD) {
			transSet.add(t.getKey());
		}
		
		
		
		for(TransponderBD t :  transponderBD) {
			
			if(!transSet.contains(t.getKey())){
				
				newTransBD.add(t);
				System.out.println(t.getKey());
			}
		}
		
		        
		try {
			c.setAutoCommit(false);
			Statement stmt = c.createStatement();
			
			
			for(  TransponderBD trans : newTransBD) {
				         String sql = "INSERT INTO tbtransponders (Tipo,Km) " +
			                          "VALUES ('"+trans.getTipo()+"', "+ trans.getKm()+");"; 
				         stmt.executeUpdate(sql);
			}
			
	         stmt.close();
	         c.commit();
	         return newTransBD;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<TransponderBD> getTodosTransposnders(){
		ArrayList<TransponderBD> Trans = new ArrayList<TransponderBD>();
		
		try {

		      c.setAutoCommit(false);
		      System.out.println("Opened database successfully");

		      Statement stmt = c.createStatement();
		      ResultSet rs = stmt.executeQuery( "SELECT * FROM  tbtransponders ORDER BY Km;" );
		      
		      while ( rs.next() ) {
		    	  
		         int id = rs.getInt("Id");
		         String  tipo = rs.getString("Tipo");
		         int km  = rs.getInt("Km");
		         
		         TransponderBD trans =  new TransponderBD();
		         		trans.setKm(km);
		         		trans.setTipo(tipo);
		         		trans.setId(id);
		         		trans.setKey(km+" - "+tipo);
		         		
		         		Trans.add(trans);
		      }
		      rs.close();
		      stmt.close();
		   } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		   }
		
		return Trans;
	}
	
	public void Close() {

	      try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
