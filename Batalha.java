import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
public class Batalha extends Controller {
	
	public void batalhaComum (Players jogador1, Players jogador2) throws FileNotFoundException {	
		System.out.println("");
		Scanner leitura = new Scanner(System.in);
		System.out.println(jogador1.getNome() + " x " + jogador2.getNome());
		while (jogador1.temDeusVivo() && jogador2.temDeusVivo()){
			Batalha simulacao = new Batalha();
			int opcao;
			System.out.println("O que o " + jogador1.getNome() + " deseja fazer?");
			System.out.println("1 - Atacar				\n" +
							   "2 - Trocar de deus	");
			opcao = leitura.nextInt();			
			if (opcao == 1) {
				System.out.println("Escolha o ataque: ");
				Deuses aux = jogador1.getDeusesAtual();
				aux.imprimeAtaques();
				opcao = leitura.nextInt();
				long tm = System.currentTimeMillis();
				simulacao.addEvent (new Atacar (tm, jogador1, jogador2, opcao));
			}
			else if (opcao == 2) {
				long tm = System.currentTimeMillis();
				System.out.println("Escolha o deus substituto: ");
				jogador1.imprimeDeuses();
				int novoDeus = leitura.nextInt();
				simulacao.addEvent (new Trocar (tm, jogador1, novoDeus));
			}
			
			System.out.println("O que o " + jogador2.getNome() + " deseja fazer?");
			System.out.println("1 - Atacar				\n" +
							   "2 - Trocar de deus			");
			opcao = leitura.nextInt();
			if (opcao == 1) {
				System.out.println("Escolha o ataque: ");
				Deuses aux = jogador2.getDeusesAtual();
				aux.imprimeAtaques();
				opcao = leitura.nextInt();
				long tm = System.currentTimeMillis();
				simulacao.addEvent (new Atacar (tm, jogador2, jogador1, opcao));
			}
			else if (opcao == 2) {
				long tm = System.currentTimeMillis();
				System.out.println("Escolha o deus substituto: ");
				jogador2.imprimeDeuses();
				int novoDeus = leitura.nextInt();
				simulacao.addEvent (new Trocar (tm, jogador2, novoDeus));
			}
			simulacao.run();
		}		
	}	
	
	
	public class Atacar extends Event {
		private Players quemAtaca;
		private Players alvo;
		private int ataque;
		private boolean trocou = false;
		public 	Atacar(long eventTime, Players quemAtaca, Players alvo, int ataque) {
			super(eventTime);
			this.quemAtaca = quemAtaca;
			this.alvo = alvo;
			this.ataque = ataque;
		}
		public void action() {
			Deuses aux = alvo.getDeusesAtual();
			double k = quemAtaca.getDeusesAtual().vantagem(aux);
			if (aux.estaVivo()) {
				aux.diminuiHP(k * quemAtaca.getDanoAtual(ataque));
				System.out.println(quemAtaca.getNome() + " decidiu atacar:");
				System.out.println(quemAtaca.getDeusesAtual().getNome() + 
						" usou " + quemAtaca.getAtaqueAtual(ataque) + "!");	
				if (k == 0)
					System.out.println("O ataque não surtiu efeito.");
				else if (k == 0.5)
					System.out.println("O ataque não foi muito efetivo.");
				else if (k == 2)
					System.out.println("O ataque foi muito efetivo!");
			}	
			if (aux.estaVivo()){				
				System.out.println(aux.getNome() + " agora tem " + alvo.getDeusesAtual().getHp() + "HP.\n");
			}
			else {
				
				System.out.println(aux.getNome() + " foi derrotado.");
				if (alvo.temDeusVivo()) {
					Scanner leitura = new Scanner (System.in);		
					System.out.println("Escolha o deus substituto: ");
					alvo.imprimeDeuses();
					int novoDeus = leitura.nextInt();
					long tm = System.currentTimeMillis();
					Trocar troca = new Trocar (tm, alvo, novoDeus);
					troca.action();
					trocou = true;
				}
				else {
					System.out.println (alvo.getNome() + " foi derrotado!");
					System.out.println("Vencedor: " + quemAtaca.getNome());
					alvo.foiDerrotado();
				}
			}
				
		}
		public int prioridade() {
			return 4;
		}
		public boolean playersDerrotado() {
			return alvo.perdeu();
		}
		public boolean playersFugiu() {
			return false;
		}
		public boolean playersTrocou(){
			if (trocou)
				return true;
			else
				return false;
		}
	}
	
	public class Trocar extends Event {
		private Players jogador;
		private int novoDeus;
		public Trocar (long eventTime, Players jogador, int novoDeus) {
			super(eventTime);
			this.jogador = jogador;
			this.novoDeus = novoDeus;
		}
		public void action() {
			Deuses aux = jogador.getDeuses(novoDeus);
			if (aux.estaVivo()) {
				jogador.mudaDeusAtual(novoDeus);
				System.out.println(jogador.getNome() + " trocou de deus. deus atual: " + aux.getNome() + ".\n");
			}
		}
		public int prioridade() {
			return 2;
		}
		public boolean playersDerrotado() {
			return false;
		}
		public boolean playersFugiu() {
			return false;
		}
		public boolean playersTrocou() {
			return false;
		}
	}
	

}