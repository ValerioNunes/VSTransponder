package DB;

import java.io.File;

public class Pastas {
	
	String  backup    = "C:/VALERIANO SYSTEM - HELPER DIN�MICO";
	
	
	public String pasta(){
		
		File diretorio = new File(""); // ajfilho � uma pasta!
		
		return diretorio.getAbsolutePath(); 
		
	}
		
	public void pasta(int loco){
		File diretorio = new File(getBackup() + "\\" + loco); // ajfilho � uma pasta!
		if (!diretorio.exists()) {
		   diretorio.mkdirs(); //mkdir() cria somente um diret�rio, mkdirs() cria diret�rios e subdiret�rios.
		} else {
		   //System.out.println("Diret�rio j� existente");
		}
	}
	
	
	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}
	

}
