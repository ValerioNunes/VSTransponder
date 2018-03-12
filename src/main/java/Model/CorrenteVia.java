package Model;

import java.time.LocalDateTime;

public class CorrenteVia {
	
	String texto, texto_anterio;
	
	String Freq;
	double Power;
	double Period;
	LocalDateTime Data;
	String  data;
	
	public CorrenteVia(String texto, String texto_anterio) {
		super();
		this.texto = texto;
		this.texto_anterio = texto_anterio;
		
	}

	public String getTexto() {
		return texto;
	}

	public String getTexto_anterio() {
		return texto_anterio;
	}

	public String getFreq() {
		return Freq;
	}

	public double getPower() {
		return Power;
	}

	public double getPeriod() {
		return Period;
	}

	public LocalDateTime getData() {
		return Data;
	}

	public String getDataTexto() {
		return data;
	}
	
	
	
	
	

}
