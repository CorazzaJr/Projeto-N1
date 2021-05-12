public class Ataque {
	private String nome;
	private int forca;
	
	public Ataque(String nome, int dano){
		this.nome = nome;
		this.forca = dano;
	}
	public int getDano(){
		return forca;
	}
	public String getNome(){
		return nome;
	}
}