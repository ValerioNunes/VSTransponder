package LeituraArquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Logs;
import Model.Momento;
import Model.Transponder;


public class BuscaArquivos {

	private ArrayList<Transponder> Transponders;	
	private Momento  Datas;
	private Logs Log =  new Logs();
	
 public Logs BuscarArquivo(){
	 
	    Datas =  new Momento();
	    Transponders = new ArrayList();
	    Log =  new Logs();
	    
	    
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	
	    	LerTXT(chooser.getSelectedFile().getAbsolutePath());
	    	
	    }else{
	  
	    }
	    
	   
	    gerarLog();
	    
	   return this.Log;
	}

 
private void gerarLog() {
	
	Set <String>  trans = new  HashSet <String>();
	Set <String>  tipotrans = new  HashSet <String>();
	Map<String,Integer> QuantidadeLeituras = new HashMap<String,Integer>();
	Map<String,Integer> QuantidadeLeiturasPorLogs = new HashMap<String,Integer>();
	
	Map<String,ArrayList<Transponder>> KeyTransponders = new HashMap<String,ArrayList<Transponder>>();
	
    Integer TotalLeituras = 0;	
	for (Transponder t : Transponders) {
		
		int KM = t.getKM();
		String KMTipo = (new Integer(KM)+" - "+t.getTipo());
		String log =  t.getLog();
		trans.add(KMTipo);
		tipotrans.add(t.getTipo());
		t.setData(Datas.getData(t));
		
		
		if(QuantidadeLeituras.get(KMTipo) != null) {
			
			Integer qtd = 	QuantidadeLeituras.get(KMTipo)+1;
			QuantidadeLeituras.put(KMTipo,qtd);
			
			ArrayList<Transponder> keyTrans = KeyTransponders.get(KMTipo);
			 keyTrans.add(t);
			KeyTransponders.put(KMTipo,keyTrans);
			
		}else{
			QuantidadeLeituras.put(KMTipo,1);
			ArrayList<Transponder> keyTrans = new ArrayList<Transponder>();
			 keyTrans.add(t);
			KeyTransponders.put(KMTipo,keyTrans);
			
		}
		
		if(QuantidadeLeiturasPorLogs.get(log) != null) {
			Integer qtd = QuantidadeLeiturasPorLogs.get(log) +1;
			QuantidadeLeiturasPorLogs.put(log, qtd);
		}else{
			QuantidadeLeiturasPorLogs.put(log, 1);
		}
		
	}
		   List TranspodersLidos = new ArrayList(trans); 
		   Collections.sort(TranspodersLidos);
		   this.Log.setQuantidadeLeiturasPorLogs(QuantidadeLeiturasPorLogs);
		   this.Log.setTipoTransponders(tipotrans);
		   this.Log.setQuantidadeLeituras(QuantidadeLeituras);
		   this.Log.setTranspoders(Transponders);
		   this.Log.setTranspodersLidos(TranspodersLidos);
		   this.Log.setKeyTransponders(KeyTransponders);
}
 
 	void LerTXT(String local){
      ArrayList<String> Log = new ArrayList<String>();
      
	  File folder = new File(local);
	    for (File arquivo : folder.listFiles()) {
	        if (arquivo.isFile()) {
	        	    
					if(getFileExtension(arquivo).toLowerCase().equals("log")) {
						
							Ler(arquivo);
							Log.add(arquivo.getName());

					}
	        	
	        	}
	        }
	    this.Log.setLogs(Log);
 	}
 
 	
 	
	private static String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	void Ler(File nome) {
		    try {
		    	
		      BufferedReader lerArq = new BufferedReader(new FileReader(nome));
		      	
		  	  ArrayList<String> linhas = new ArrayList<String>();	
		      linhas.add(lerArq.readLine());
		      while (linhas.get(linhas.size()-1) != null) {
		    	String texto = lerArq.readLine();
		        linhas.add(texto);
		        
		        int linha = linhas.size();
		        String log =  nome.getName();
	    		if(texto != null ){
	        	    if( texto.contains("location")){
	        			Transponder ev = new Transponder(log ,linha,texto);
	        			Transponders.add(ev);
	        	    }
	        	    if( texto.contains("Date")){
	        			Datas.busca(log , linha, texto);
	        	    }	        	    
	        	   
		      }
	    	}
		      
		     
		    } catch (IOException e) {
		    	JOptionPane.showMessageDialog(null, "Erro na abertura do arquivo: %s.\n" + e.getMessage());
		       
		    }

		  }
 
}
