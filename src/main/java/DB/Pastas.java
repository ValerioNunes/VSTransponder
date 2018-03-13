package DB;

import java.io.File;

public class Pastas {
	
	String  backup    = "C:/VALERIANO SYSTEM - HELPER DINÂMICO";
	
	
	public String pasta(){
		
		File diretorio = new File(""); // ajfilho é uma pasta!
		
		return diretorio.getAbsolutePath(); 
		
	}
		
	public void pasta(int loco){
		File diretorio = new File(getBackup() + "\\" + loco); // ajfilho é uma pasta!
		if (!diretorio.exists()) {
		   diretorio.mkdirs(); //mkdir() cria somente um diretório, mkdirs() cria diretórios e subdiretórios.
		} else {
		   //System.out.println("Diretório já existente");
		}
	}
	
	
	public String getBackup() {
		return backup;
	}

	public void setBackup(String backup) {
		this.backup = backup;
	}
	

}
