package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DataBase {
    static Connection c = null;
    static Statement stmt = null;
    
	 public static Connection iniciar() {
		 

	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:bdtransponders.db");
	         criarTable(c);
	      } catch ( Exception e ) {
	    	  JOptionPane.showMessageDialog(null,  e.getClass().getName() + ": " + e.getMessage());
	          System.exit(0);
	      }
	      
	      return c;
	   }
	 
	 private static void criarTable(Connection c) {
         try {
        	 
			stmt = c.createStatement();      
			String sql = "CREATE TABLE IF NOT EXISTS  tbtransponders " +
                        "(Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " Tipo           TEXT    NOT NULL, " + 
                        " Km             INT     NOT NULL ) ";
	         stmt.executeUpdate(sql);
	         stmt.close();

         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	 }
}
