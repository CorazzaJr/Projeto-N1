import java.util.Scanner;
import java.lang.Math;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {			
		
		Scanner leitura = new Scanner (System.in);
		System.out.println("=======================================");
		System.out.println("\n     JOGO A BATALHA DOS DEUSES     \n");
		System.out.println("=======================================");
		System.out.println("Bem vindo! Digite para começar: ");
		System.out.println("1 - Batalha entre players");
		System.out.println("2 - Sair");
		
		int tipoJogo = leitura.nextInt(); 
		
		if(tipoJogo == 1){

			Scanner players = new Scanner(new FileReader("criaPlayers.txt")).useDelimiter("\\||\\n");

			String nome1 = players.next();
			int quantidade = players.nextInt();				
			Deuses[] listaDeuses1 = new Deuses[quantidade];
			for (int i = 0; i < quantidade; i++) {
				String nomeDeus = players.next();
				String tipo = players.next();
				int hp = players.nextInt();
				Ataque[] listaAtk = new Ataque[2];
				for (int j = 0; j < 2; j++) {
					String nomeAtk = players.next();
					int dano = players.nextInt();
					listaAtk[j] = new Ataque(nomeAtk, dano);
				}	
				listaDeuses1[i] = new Deuses(nomeDeus, tipo, hp, listaAtk);
			}
			Players jogador1 = new Players (nome1, listaDeuses1);
			
			
			String nome2 = players.next();
			quantidade = players.nextInt();				
			Deuses[] listaDeuses2 = new Deuses[quantidade];
			for (int i = 0; i < quantidade; i++) {
				String nomeDeus = players.next();
				String tipo = players.next();
				int hp = players.nextInt();
				Ataque[] listaAtk = new Ataque[2];
				for (int j = 0; j < 2; j++) {
					String nomeAtk = players.next();
					int dano = players.nextInt();
					listaAtk[j] = new Ataque(nomeAtk, dano);
				}	
				listaDeuses2[i] = new Deuses(nomeDeus, tipo, hp, listaAtk);
			}
			Players jogador2 = new Players (nome2, listaDeuses2);

			Batalha comum = new Batalha();
			comum.batalhaComum(jogador1, jogador2);			
		
		}
		else {
			System.out.println("Obrigado por jogar!");
		}
		leitura.close();
	}

}