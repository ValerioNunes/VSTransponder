package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Logs {
	ArrayList<Transponder> Transpoders;
	List<String> TranspodersLidos;
	ArrayList<String> Logs;
	Set<String> TipoTransponders;
	Map<String,Integer> QuantidadeLeituras = new HashMap<String,Integer>(); 
	Map<String,ArrayList<Transponder>> KeyTransponders = new HashMap<String,ArrayList<Transponder>>();
	Map<String,Integer> QuantidadeLeiturasPorLogs = new HashMap<String,Integer>();

	
	
	
	public Map<String, Integer> getQuantidadeLeiturasPorLogs() {
		return QuantidadeLeiturasPorLogs;
	}

	public void setQuantidadeLeiturasPorLogs(Map<String, Integer> quantidadeLeiturasPorLogs) {
		QuantidadeLeiturasPorLogs = quantidadeLeiturasPorLogs;
	}

	public Set<String> getTipoTransponders() {
		return TipoTransponders;
	}

	public void setTipoTransponders(Set<String> tipoTransponders) {
		TipoTransponders = tipoTransponders;
	}

	public Integer getTotalLeituras() {
		return Transpoders.size();
	}
	
	public Map<String, ArrayList<Transponder>> getKeyTransponders() {
		return KeyTransponders;
	}
	public void setKeyTransponders(Map<String, ArrayList<Transponder>> keyTransponders) {
		KeyTransponders = keyTransponders;
	}
	public Map<String,Integer> getQuantidadeLeituras() {
		return QuantidadeLeituras;
	}
	public void setQuantidadeLeituras(Map<String,Integer> quantidadeLeituras) {
		QuantidadeLeituras = quantidadeLeituras;
	}
	public ArrayList<Transponder> getTranspoders() {
		return Transpoders;
	}
	public void setTranspoders(ArrayList<Transponder> transpoders) {
		Transpoders = transpoders;
	}
	public List<String> getTranspodersLidos() {
		return TranspodersLidos;
	}
	public void setTranspodersLidos(List<String> transpodersLidos) {
		TranspodersLidos = transpodersLidos;
	}
	public ArrayList<String> getLogs() {
		return Logs;
	}
	public void setLogs(ArrayList<String> logs) {
		Logs = logs;
	}
	
	
	
}
