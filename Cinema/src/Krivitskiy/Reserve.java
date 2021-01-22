package Krivitskiy;

public class Reserve {
	private Seance seance;
	private int rowNumber;
	private int seatNumber;

	public Reserve(Seance seance) {
		this.seance = seance;
	}

	public Seance getSeance() {
		return seance;
	}

	public void setSeance(Seance seance) {
		this.seance = seance;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public boolean reserveSeat(int selectedRow, int selectedSeat) {
		if (seance.getHall().getRows().get(selectedRow).getSeats().get(selectedSeat).getReservationStatus()) {
			return false;
		} else {
			seance.getHall().getRows().get(selectedRow).getSeats().get(selectedSeat).reserve();
			setRowNumber(selectedRow);
			setSeatNumber(selectedSeat);
			return true;
		}
	}
}
