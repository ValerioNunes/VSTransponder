package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Logs {
	
	ArrayList<Transponder> Transpoders;
	ArrayList<TransponderBD>  newTranspoderBDs;
	ArrayList<TransponderBD>  TranspoderTodosBDs;
	List<String> TranspodersLidos;
	ArrayList<String> Logs;
	Set<String>  TipoTransponders;
	ArrayList<Integer> TranspondersBD;
	Map<String,Integer> QuantidadeLeituras = new HashMap<String,Integer>(); 
	Map<String,ArrayList<Transponder>> KeyTransponders = new HashMap<String,ArrayList<Transponder>>();
	Map<String,Integer> QuantidadeLeiturasPorLogs = new HashMap<String,Integer>();
	Map<String,Set<Integer>> TranspondersPorLogs = new HashMap<String,Set<Integer>>();
	Map<String,Integer> QuantidadeErros = new HashMap<String,Integer>();

	
	
	public Map<String, Integer> getQuantidadeErros() {
		return QuantidadeErros;
	}

	public void setQuantidadeErros(Map<String, Integer> quantidadeErros) {
		QuantidadeErros = quantidadeErros;
	}

	public ArrayList<Integer> getTranspondersBD() {
		return TranspondersBD;
	}

	public void setTranspondersBD(ArrayList<Integer> transpondersBD) {
		TranspondersBD = transpondersBD;
	}

	public Map<String, Set<Integer>> getTranspondersPorLogs() {
		return TranspondersPorLogs;
	}

	public void setTranspondersPorLogs(Map<String, Set<Integer>> transpondersPorLogs) {
		TranspondersPorLogs = transpondersPorLogs;
	}

	public ArrayList<TransponderBD> getNewTranspoderBDs() {
		return newTranspoderBDs;
	}

	public void setNewTranspoderBDs(ArrayList<TransponderBD> newTranspoderBDs) {
		this.newTranspoderBDs = newTranspoderBDs;
	}

	public ArrayList<TransponderBD> getTranspoderTodosBDs() {
		return TranspoderTodosBDs;
	}

	public void setTranspoderTodosBDs(ArrayList<TransponderBD> transpoderTodosBDs) {
		TranspoderTodosBDs = transpoderTodosBDs;
	}

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
