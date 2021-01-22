package Krivitskiy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Cinema {
	private static Cinema cinema;
	private TreeMap<Days, Schedule> schedules = new TreeMap<>();
	private ArrayList<Movie> moviesLibrary = new ArrayList<>();
	private Time open;
	private Time close;

	private Cinema(Time open, Time close) {
		this.open = open;
		this.close = close;
		schedulesFill();
	}

	public static Cinema getCinema(Time open, Time close) {
		if (cinema == null) {
			cinema = new Cinema(open, close);
		}
		return cinema;
	}

	public TreeMap<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(TreeMap<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public ArrayList<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpen() {
		return open;
	}

	public void setOpen(Time open) {
		this.open = open;
	}

	public Time getClose() {
		return close;
	}

	public void setClose(Time close) {
		this.close = close;
	}

	public void addMovie(Movie movie) {
		moviesLibrary.add(movie);
	}

	public boolean checkMovie(String title) {
		return moviesLibrary.stream().anyMatch(m -> m.getTitle().equalsIgnoreCase(title));
	}

	public Movie findMovie(String title) {
		return moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).findFirst().get();
	}

	public Seance findSeance(String title) {
		Seance seance = null;
		for (Map.Entry<Days, Schedule> entry : schedules.entrySet()) {
			for (int i = 0; i < entry.getValue().getSeances().size(); i++) {
				seance = entry.getValue().getSeances().stream()
						.filter(m -> m.getMovie().getTitle().equalsIgnoreCase(title)).findFirst().orElse(null);
			}
		}
		return seance;
	}

	public void addSeanceToSchedules(Seance seance, String day) {
		boolean checkOpenHours = seance.getStartTime().compareTo(open) > 0 && seance.getEndTime().compareTo(close) < 0;
		if (checkOpenHours) {
			schedules.get(stringToDays(day)).addSeance(seance);
			System.out.println("Seance of movie " + seance.getMovie().getTitle().toUpperCase()
					+ " successfully added to " + stringToDays(day));
		} else
			System.err.println("The seance time must be during the cinema opening hours");
	}

	public void removeMovie(Movie movie) {
		if (moviesLibrary.contains(movie)) {
			moviesLibrary.remove(movie);
			System.out.println("Movie successfully deleted from library");
		}
		for (Map.Entry<Days, Schedule> entry : schedules.entrySet()) {
			for (int i = 0; i < entry.getValue().getSeances().size(); i++) {
				List<Seance> seancesforDelete = entry.getValue().getSeances().stream()
						.filter(m -> m.getMovie().equals(movie)).collect(Collectors.toList());
				entry.getValue().getSeances().removeAll(seancesforDelete);
				if (!seancesforDelete.isEmpty()) {
					System.out.println(
							"Movie " + movie.getTitle() + " successfully deleted from schedules for " + entry.getKey());
				}
			}
		}
	}

	public void removeSeanceFromSchedule(Seance seance, String day) {
		if (schedules.get(stringToDays(day)).checkSeance(seance)) {
			schedules.get(stringToDays(day)).removeSeance(seance);
			System.out.println("Seance successfully deleted from " + day.toUpperCase());
		} else
			System.err.println(
					"Not found seance " + seance.getMovie().getTitle().toUpperCase() + " on " + day.toUpperCase());
	}

	public void showAllSeances() {
		for (Map.Entry<Days, Schedule> entry : schedules.entrySet()) {
			if (entry.getValue().getSeances().isEmpty()) {
				System.out.println(entry.getKey() + ": no seances");
			} else {
				System.out.println(entry.getKey() + ": ");
				for (Seance seance : entry.getValue().getSeances()) {
					System.out.println(seance);
				}
			}
		}
	}

	public void showSeancesByDay(Days day) {
		if (!schedules.get(day).getSeances().isEmpty()) {
			System.out.println(day.toString() + " seances:");
			Iterator<Seance> iterator = schedules.get(day).getSeances().iterator();
			for (int i = 0; i < schedules.get(day).getSeances().size(); i++) {
				System.out.println(String.valueOf(i + 1) + ". " + iterator.next());
			}
		} else
			System.out.println("No seances in " + day);
	}

	public Seance getSeanceByNumber(int number, Days day) {
		Seance seance = null;
		if (!schedules.get(day).getSeances().isEmpty()) {
			Iterator<Seance> iterator = schedules.get(day).getSeances().iterator();
			for (int i = 0; i < schedules.get(day).getSeances().size(); i++) {
				Seance forCount = iterator.next();
				if (i + 1 == number) {
					seance = forCount;
				}
			}
		}
		return seance;
	}

	public Days stringToDays(String day) {
		return Arrays.stream(Days.values()).filter(d -> day.equalsIgnoreCase(d.toString())).findFirst().orElse(null);
	}

	private void schedulesFill() {
		for (int i = 0; i < Days.values().length; i++) {
			this.schedules.put(Days.values()[i], new Schedule());
		}
	}
}
