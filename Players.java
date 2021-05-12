public class Players {
	private String nome;
	private Deuses[] deuses = new Deuses[3];
	private Deuses deusesAtual;
	private boolean derrotado;

	
	public Players (String nome, Deuses[] listaDeDeuses) {
		this.nome = nome;
		deuses = listaDeDeuses;	
		deusesAtual = deuses[0];
		derrotado = false;
	}
	public String getNome() {
		return nome;
	}	
	public Deuses getDeuses(int i) {
		return deuses[i];
	}
	public Deuses getDeusesAtual() {
		return deusesAtual;
	}
	public String getAtaqueAtual (int num) {
		return deusesAtual.getAtaque(num);
	}
	public int getDanoAtual(int num) {
		return deusesAtual.getDanoAtual(num);
	}
	public void recebeAtaque (int dano) {
		deusesAtual.diminuiHP(dano);
	}
	public void cura() {
		if (deusesAtual.estaVivo())
			deusesAtual.aumentaHP(100);
	}
	public int quantosdeuses() {
		return deuses.length;
	}
	public void mudaDeusAtual(int i) {
		if (deuses[i].estaVivo())
			deusesAtual = deuses[i];
	}
	public boolean temDeusVivo() {
		boolean tem = false;
		int size = deuses.length;
		for (int i = 0; i < size ; i++) 
			if (deuses[i].estaVivo()) tem = true;
		return tem;
	}
	public void imprimeDeuses() {
		int aux = deuses.length;
		for (int i = 0; i < aux; i++) {
			if (deuses[i].estaVivo())
				System.out.println(i + " - " + deuses[i].getNome());
		}
	}
	
		public boolean perdeu() {
			return derrotado;
		}
		public void foiDerrotado() {
			derrotado = true;
		}
}