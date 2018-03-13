package Model;

public class TransponderBD {
	Integer id;
	Integer Km;
	String Tipo;
	String Key;
	
	
	
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getKm() {
		return Km;
	}
	public void setKm(Integer km) {
		Km = km;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
}
