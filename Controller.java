public class Controller {
	private EventSet es = new EventSet();
	public void addEvent(Event c) {
		es.add(c); 
	}
	public void run() {	
		Event a;
		boolean trocou = false;
		boolean perdeu = false;
		while((a = es.getNext()) != null && !perdeu && !trocou) {
			if(a.ready()) {
				a.action();
				if (a.playersDerrotado())
					perdeu = true;
				if (a.playersTrocou())
					trocou = true;
				es.removeCurrent(); 
			}
		}
	}
}