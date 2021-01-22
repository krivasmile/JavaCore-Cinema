package Krivitskiy;

import java.util.Comparator;

public class SeanceComparator implements Comparator<Seance> {

	@Override
	public int compare(Seance s1, Seance s2) {
		return s1.getStartTime().compareTo(s2.getStartTime());
	}

}
