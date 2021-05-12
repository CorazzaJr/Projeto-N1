
public class EventSet {
	private Event[] events = new Event[2];
	private int index = 0;
	private int next = 0;
	public void add (Event e) {
		if(index >= events.length)
			return;
		if (index % 2 == 0)			
			events[index++] = e;
		else {
			if (events[index - 1].prioridade() <= e.prioridade()) {
				events[index++] = e;
			}
			else {
				events[index] = events[index - 1];
				events[index - 1] = e;
				index++;
			}
		}
	}
	public Event getNext() {
		while(next < index) {
			if (events[next] != null)
				return events[next];
			next++;
		}
		return null;
	}
	public void removeCurrent() {
		events[next] = null;
	}
	
}