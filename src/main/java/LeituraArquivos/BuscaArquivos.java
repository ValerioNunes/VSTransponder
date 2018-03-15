package LeituraArquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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

import Dao.DaoTransponder;
import Model.Logs;
import Model.Momento;
import Model.Transponder;
import Model.TransponderBD;


public class BuscaArquivos {

	private ArrayList<Transponder> Transponders;	
	private Momento  Datas;
	private Logs Log =  new Logs();
	DaoTransponder DaoTrans;
	
	
 public Logs BuscarArquivo(){
	 
	    Datas =  new Momento();
	    Transponders = new ArrayList();
	    Log =  new Logs();
	    
	    
		JFileChooser chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    int returnVal = chooser.showOpenDialog(null);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	
	    	LerTXT(chooser.getSelectedFile().getAbsolutePath());
	    	gerarLog();
	    	
	    }else{
	    		return null;
	    }
	    
	   return this.Log;
	}

 
private void gerarLog() {
	
	Set<Integer> kmTransBD = new HashSet <Integer>();
	ArrayList<TransponderBD> TransBD = new ArrayList<TransponderBD>();
	Map<String,Set<Integer>> TranspondersPorLogs = new HashMap<String,Set<Integer>>();
	
	
	
	Set <String>  trans = new  HashSet <String>();
	Set <String>  tipotrans = new  HashSet <String>();
	Map<String,Integer> QuantidadeLeituras = new HashMap<String,Integer>();
	Map<String,Integer> QuantidadeErros = new HashMap<String,Integer>();
	
	Map<String,Integer> QuantidadeLeiturasPorLogs = new HashMap<String,Integer>();
	
	
	Map<String,ArrayList<Transponder>> KeyTransponders = new HashMap<String,ArrayList<Transponder>>();
	
  
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
			Integer err = QuantidadeErros.get(KMTipo) + t.getErro();
			QuantidadeErros.put(KMTipo, err);
		}else{
			QuantidadeLeituras.put(KMTipo,1);
			ArrayList<Transponder> keyTrans = new ArrayList<Transponder>();
			 keyTrans.add(t);
			KeyTransponders.put(KMTipo,keyTrans);
			
			TransponderBD Tdb = new TransponderBD();
			 Tdb.setKm(KM);
			 Tdb.setTipo(t.getTipo());
			 Tdb.setKey(KMTipo);
			TransBD.add(Tdb);
			
			QuantidadeErros.put(KMTipo,t.getErro());
		}
		
		if(QuantidadeLeiturasPorLogs.get(log) != null) {
			Integer qtd = QuantidadeLeiturasPorLogs.get(log) +1;
			QuantidadeLeiturasPorLogs.put(log, qtd);
			
	
			
		}else{
			QuantidadeLeiturasPorLogs.put(log, 1);
			
		}
		
		if(TranspondersPorLogs.get(log) != null) {
			Set<Integer> km = TranspondersPorLogs.get(log);
			km.add(KM);
			TranspondersPorLogs.put(log, km);
		}else{
			Set<Integer> km = new HashSet<Integer>();
			km.add(KM);
			TranspondersPorLogs.put(log, km);
		}
		
	}	   List TranspodersLidos = new ArrayList(trans); 
		   Collections.sort(TranspodersLidos);
		   DaoTrans =  new DaoTransponder();
		   this.Log.setNewTranspoderBDs(DaoTrans.salvar(TransBD));
		   this.Log.setTranspoderTodosBDs(DaoTrans.getTodosTransposnders());
		   this.Log.setQuantidadeLeiturasPorLogs(QuantidadeLeiturasPorLogs);
		   this.Log.setTipoTransponders(tipotrans);
		   this.Log.setQuantidadeLeituras(QuantidadeLeituras);
		   this.Log.setTranspoders(Transponders);
		   this.Log.setTranspodersLidos(TranspodersLidos);
		   this.Log.setKeyTransponders(KeyTransponders);
		   this.Log.setTranspondersPorLogs(TranspondersPorLogs);
		   this.Log.setQuantidadeErros(QuantidadeErros);
		   DaoTrans.Close();
		   
		   for(TransponderBD t : this.Log.getTranspoderTodosBDs())
			   	kmTransBD.add(t.getKm());
		   
		    ArrayList TranspodersLidos2 = new ArrayList(kmTransBD); 
			Collections.sort(TranspodersLidos2);
			this.Log.setTranspondersBD(TranspodersLidos2);
			
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
