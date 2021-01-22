package Krivitskiy;

import java.util.ArrayList;

public class Row {
	private ArrayList<Seat> seats;
	private int rowNumber;
	private int seatCount;

	public Row(int seatCount, int rowNumber) {
		this.rowNumber = rowNumber;
		this.seatCount = seatCount;
		seats = new ArrayList<Seat>();
		createSeats(this.seatCount);
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public void createSeats(int seatCount) {
		for (int i = 1; i <= seatCount; i++) {
			seats.add(new Seat(false, i));
		}
	}

	public ArrayList<Seat> getSeats() {
		return seats;
	}
}
