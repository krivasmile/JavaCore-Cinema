package Krivitskiy;

public class Time implements Comparable<Time> {
	private int min;
	private int hour;
	private boolean endNextDay;

	public Time(int hour, int min) {
		if (min >= 0 && min < 60 && hour >= 0 && hour < 24) {
			this.min = min;
			this.hour = hour;
			this.endNextDay = false;
		} else
			throw new AssertionError("Hours and minutes must be correct");
	}

	public int getMin() {
		return min;
	}

	public boolean getNextDay() {
		return endNextDay;
	}

	public void setNextDay(boolean endNextDay) {
		this.endNextDay = endNextDay;
	}

	public void setMin(int min) {
		if (min >= 0 && min < 60) {
			this.min = min;
		} else
			throw new AssertionError("Hours and minutes must be correct");
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		if (hour >= 0 && hour < 24) {
			this.hour = hour;
		} else
			throw new AssertionError("Hours and minutes must be correct");
		this.hour = hour;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d", hour, min);
	}

	@Override
	public int compareTo(Time anotherTime) {
		if (this.getNextDay())
			return 1;
		if (this.getHour() > anotherTime.getHour())
			return 1;
		if (this.getHour() < anotherTime.getHour())
			return -1;
		else {
			if (this.getMin() >= anotherTime.getMin())
				return 1;
			else
				return -1;
		}
	}
}
