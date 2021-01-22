package Krivitskiy;

public class Seance {
	private Movie movie;
	private Time startTime;
	private Time endTime;
	private Hall hall;

	public Seance(Movie movie, Time startTime) {
		this.movie = movie;
		this.startTime = startTime;
		this.endTime = endTime(movie, startTime);
		hall = new Hall();
	}

	public Hall getHall() {
		return hall;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	// calculation seance end time and adding 15 minutes for cleaning
	private static Time endTime(Movie movie, Time startTime) {
		boolean nextDay = false;
		int hours = startTime.getHour() + movie.getDuration().getHour();
		int min = startTime.getMin() + movie.getDuration().getMin() + 15; // adding 15 min for cleaning

		if (min >= 60) {
			if (min >= 120) {
				hours = hours + 2;
				min = min - 120;
			} else {
				hours = hours + 1;
				min = min - 60;
			}
		}
		if (hours >= 24) {
			hours = hours - 24;
			nextDay = true;
		}
		Time endTime = new Time(hours, min);
		endTime.setNextDay(nextDay);
		return endTime;
	}

	@Override
	public String toString() {
		return movie + ", startTime= " + startTime + ", endTime= " + endTime;
	}

}
