public class Deuses {
	private String nome;
	private String tipo;
	private int HP;
	private Ataque[] ataques;
	private int hpMax;
	public Deuses(String nome, String tipo, int HP, Ataque[] listaAtaques){
		this.nome = nome;
		this.tipo = tipo;
		this.HP = HP;
		ataques = listaAtaques;
		hpMax = HP;
	}
	public String getNome(){
		return nome;
	}
	public String getTipo(){
		return tipo;
	}
	
	
	public int getHp() {
		return HP;
	}
	public int getHpMax() {
		return hpMax;
	}
	public void diminuiHP(double dano){
		int intDano = (int) dano;
		if (this.estaVivo()) {
			HP -= intDano;
		}
	}
	public void aumentaHP(int ganho){
		int auxHp = HP + ganho;
		if (auxHp <= hpMax)
			HP = auxHp;
		else
			HP = hpMax;
	} 
	public boolean estaVivo() {
		if (HP > 0)
			return true;
		return false;
	}
	
	
	public String getAtaque(int num){
		return ataques[num].getNome();
	}
	public Ataque[] getListaAtk() {
		return ataques;
	}
	public int getDanoAtual(int num) {
		return ataques[num].getDano();
	}
	public void imprimeAtaques() {
		System.out.println("0 - " + this.ataques[0].getNome());
		System.out.println("1 - " + this.ataques[1].getNome());

	}
	public int associaTipo(String tipo){
		if(tipo.compareTo("Normal") == 0) return 0;
		else if(tipo.compareTo("Fogo") == 0) return 1;
		else if(tipo.compareTo("Água") == 0) return 2;
		else if(tipo.compareTo("Grama") == 0) return 3;	
		else return -1;
	}
	public double vantagem(Deuses alvo){
		double m = 0.5;
		double[][] vantagem = { {1, 1, 1, 1, 1, m, 1, 0, m, 1, 1, 1, 1, 1, 1, 1, 1, 1},
								{2, 1, m, m, 1, 2, m, 0, 2, 1, 1, 1, 1, m, 2, 1, 2, m},
								{1, 2, 1, 1, 1, m, 2, 1, m, 1, 1, 2, m, 1, 1, 1, 1, 1},
								{1, 1, 1, m, m, m, 1, m, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2},
								{1, 1, 0, 2, 1, 2, m, 1, 2, 2, 1, m, 2, 1, 1, 1, 1, 1},
								{1, m, 2, 1, m, 1, 2, 1, m, 2, 1, 1, 1, 1, 2, 1, 1, 1},
								{1, m, m, m, 1, 1, 1, m, m, m, 1, 2, 1, 2, 1, 1, 2, m},
								{0, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, m, 1},
								{1, 1, 1, 1, 1, 2, 1, 1, m, m, m, 1, m, 1, 2, 1, 1, 2},
								{1, 1, 1, 1, 1, m, 2, 1, 2, m, m, 2, 1, 1, 2, m, 1, 1},
								{1, 1, 1, 1, 2, 2, 1, 1, 1, 2, m, m, 1, 1, 1, m, 1, 1},
								{1, 1, m, m, 2, 2, m, 1, m, m, 2, m, 1, 1, 1, m, 1, 1},
								{1, 1, 2, 1, 0, 1, 1, 1, 1, 1, 2, m, m, 1, 1, m, 1, 1},
								{1, 2, 1, 2, 1, 1, 1, 1, m, 1, 1, 1, 1, m, 1, 1, 0, 1},
								{1, 1, 2, 1, 2, 1, 1, 1, m, m, m, 2, 1, 1, m, 2, 1, 1},
								{1, 1, 1, 1, 1, 1, 1, 1, m, 1, 1, 1, 1, 1, 1, 2, 1, 0},
								{1, m, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, m, m},
								{1, 2, 1, m, 1, 1, 1, 1, m, m, 1, 1, 1, 1, 1, 2, 2, 1}
							};
		int pkm1 = associaTipo(tipo), pkm2 = associaTipo(alvo.getTipo());
		return vantagem[pkm1][pkm2];
	}
}
