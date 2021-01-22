package Krivitskiy;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Schedule {
	private SeanceComparator comp = new SeanceComparator();
	private Set<Seance> seances = new TreeSet<>(comp);

	public Schedule() {
	}

	public Set<Seance> getSeances() {
		return seances;
	}
	

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	public void addSeance(Seance seance) {
		seances.add(seance);
	}

	public void removeSeance(Seance seance) {
		seances.remove(seance);
	}

	public boolean checkSeance(Seance seance) {
		boolean check = false;
		Iterator<Seance> iterator = seances.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(seance))
				check = true;
		}
		return check;
	}

	public boolean isEmpty() {
		return seances.isEmpty();
	}
}
