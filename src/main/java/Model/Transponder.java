package Model;

import java.util.Date;

public class Transponder {
	
 String log;
 int linha;
 String texto;
 int erro;
 int kph;
 int A,B,C,D;
 int KM; 
 double g1,g2,g3,g4;
 String Tipo;
 int Linha;
 Date data;
 public String Data;
 
public Transponder(String log, int linha, String texto) {
	super();
	this.log = log;
	this.linha = linha;
	this.texto = texto;
	
	KM(texto); 
	Erro(texto);
	Tipo(texto);
	Kph(texto);
	Data(texto);
}


private void Data(String texto) {
	
	try  
	  {  
			this.Data = texto.split(" ")[0];
			//System.out.println(this.Data);
	  }  
	  catch(Exception nfe)  
	  {  
		  System.out.println(texto);
	  } 
}

private void Linha(String tipo) {
	if(tipo.contains("I")) {
		for(int i=0; i< tipo.length();i++  ) {
			if( 'I' == tipo.charAt(i)) {
				this.Linha++;
			}
		}
		//System.out.println("Linha: "+this.Linha+" tipo "+ tipo);
	}
}
private void Tipo(String texto) {
	 try  
	  {  
		if(texto.contains("tag,")) 
			this.Tipo =  texto.split("tag,")[0].substring(19).replace(" ", "");
		else
			this.Tipo =  texto.split(",")[0].substring(19).replace(" ", "");
	    //System.out.println("Km: "+this.Tipo);
	    Linha(this.Tipo);
	  }  
	  catch(Exception nfe)  
	  {  
		  System.out.println(texto);
	  } 
}
private void KM(String texto) {
	
	  try  
	  {  
		String km =  texto.split("location")[1].split(" ")[1];
		this.KM = Integer.valueOf(km);
	    //System.out.println("Km: "+d);
	  }  
	  catch(NumberFormatException nfe)  
	  {  
		  System.out.println(texto);
	  } 
}

private void  Erro(String texto) {
	if(texto.contains("err =")) {
		  try  
		  {  
			String km =  texto.split("err =")[1];
		    this.erro = Integer.valueOf(km);  
		    //System.out.println("log/: "+log+" linha: "+linha+" Erro: "+d);
		  }  
		  catch(NumberFormatException nfe)  
		  {
			  System.out.println(texto);
		  } 
	}
}


private void  Kph(String texto) {
	if(texto.contains("kph")) {
		  try  
		  {  
			String km =  texto.split("} ")[1].split(" ")[0];
		     this.kph = Integer.valueOf(km);  
		   // System.out.println(" kph "+ this.kph);
		  }  
		  catch(NumberFormatException nfe)  
		  {
			  System.out.println(texto);
		  } 
		 
	} 
}



public void setData(Date data) {
	this.data = data;
}


public String getLog() {
	return log;
}
public int getLinha() {
	return linha;
}
public String getTexto() {
	return texto;
}
public int getErro() {
	return erro;
}
public int getKph() {
	return kph;
}
public int getA() {
	return A;
}
public int getB() {
	return B;
}
public int getC() {
	return C;
}
public int getD() {
	return D;
}
public int getKM() {
	return KM;
}
public double getG1() {
	return g1;
}
public double getG2() {
	return g2;
}
public double getG3() {
	return g3;
}
public double getG4() {
	return g4;
}
public String getTipo() {
	return Tipo;
}
public int getLinha1() {
	return Linha;
}
public Date getData() {
	return data;
}
 
 
 
}
