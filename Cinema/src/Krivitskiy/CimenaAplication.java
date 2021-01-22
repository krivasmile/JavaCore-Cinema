package Krivitskiy;

import java.util.Scanner;

public class CimenaAplication {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int option = 0;

		Scanner select = new Scanner(System.in);
		Scanner choice = new Scanner(System.in);

		Cinema kino = null;
		Cinema cinema = kino.getCinema(new Time(8, 0), new Time(22, 0));

		// ForTest
		/*
		 * Movie movie = new Movie("love", new Time(2, 45)); Movie movie2 = new
		 * Movie("Inception", new Time(2, 40)); Seance seance1 = new Seance(movie, new
		 * Time(19, 0)); Seance seance2 = new Seance(movie2, new Time(13, 59));
		 * cinema.addMovie(movie); cinema.addMovie(movie2);
		 * cinema.addSeanceToSchedules(seance1, "Monday");
		 * cinema.addSeanceToSchedules(seance2, "Monday");
		 */

		do {
			System.out.println("------------------------------------");
			System.out.println(":Cinema System by Dengerius:");
			System.out.println("------------------------------------\n");
			System.out.println("Please Enter 1 to Add Movie to Library");
			System.out.println("Please Enter 2 to Add Seance to Schedule of the Day");
			System.out.println("Please Enter 3 to Show All Movies From Library");
			System.out.println("Please Enter 4 to Show All Seance on the week");
			System.out.println("Please Enter 5 to Remove Movie from Library and Schedules");
			System.out.println("Please Enter 6 to Remove Seance from Selected Day");
			System.out.println("Please Enter 7 to Reserve Seat");
			System.out.println("Please Enter 8 to Exit\n");

			System.out.print("Enter Option: ");
			option = select.nextInt();

			if (option == 1) {
				System.out.println("-------------------------");
				System.out.println("ADD MOVIE Selected");
				System.out.println("-------------------------");
				System.out.println("Enter the title of the movie:");
				String title = choice.nextLine();
				System.out.println("------------------------------------");
				System.out.println("Enter the duration of the movie [HH:MM]:");
				String[] duration = choice.nextLine().split(":");
				cinema.addMovie(
						new Movie(title, new Time(Integer.parseInt(duration[0]), Integer.parseInt(duration[1]))));
				System.out.println("Movie successfully added");
			}
			if (option == 2) {
				System.out.println("-------------------------");
				System.out.println("ADD SEANCE Selected");
				System.out.println("-------------------------");
				System.out.println("Enter the title of the movie:");
				String title = choice.nextLine();
				if (cinema.checkMovie(title)) {
					System.out.println("-------------------------");
					System.out.println("Please enter the number of seance day:\n");
					chooseDay();
					int dayChoice = Integer.parseInt(choice.nextLine()) - 1;
					String day = Days.values()[dayChoice].toString();
					if (cinema.stringToDays(day) != null) {
						System.out.println("-------------------------");
						System.out.println("Enter seance start time [HH:MM]: (8:00 - 22:00)");
						String[] startTime = choice.nextLine().split(":");
						Seance seance = new Seance(cinema.findMovie(title),
								new Time(Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1])));
						cinema.addSeanceToSchedules(seance, day);
					} else
						System.err.println("\nWrong day input");
				} else
					System.err.println("No such movie in library");
			}
			if (option == 3) {
				System.out.println("-------------------------");
				System.out.println("SHOW ALL MOVIES Selected");
				System.out.println("-------------------------");
				if (!cinema.getMoviesLibrary().isEmpty()) {
					for (int i = 0; i < cinema.getMoviesLibrary().size(); i++) {
						System.out.println(i + 1 + ". " + cinema.getMoviesLibrary().get(i));
					}
				} else
					System.out.println("Movies Library Is Empty");
			}
			if (option == 4) {
				System.out.println("-------------------------");
				System.out.println("SHOW ALL SEANCES Selected");
				System.out.println("-------------------------");
				cinema.showAllSeances();
			}
			if (option == 5) {
				System.out.println("-------------------------");
				System.out.println("REMOVE MOVIE Selected");
				System.out.println("-------------------------");
				System.out.println("Enter the title of the movie:");
				String name = choice.nextLine();
				if (cinema.checkMovie(name)) {
					cinema.removeMovie(cinema.findMovie(name));
				} else
					System.err.println("No such movie in library");
			}
			if (option == 6) {
				System.out.println("-------------------------");
				System.out.println("REMOVE SEANCE Selected");
				System.out.println("-------------------------");
				System.out.println("Please enter the title of the movie:");
				String title = choice.nextLine();
				if (cinema.checkMovie(title)) {
					if (cinema.findSeance(title) != null) {
						System.out.println("-------------------------");
						System.out.println("Please enter the number of seance day:\n");
						chooseDay();
						System.out.println("-------------------------");
						int dayChoice = Integer.parseInt(choice.nextLine()) - 1;
						String day = Days.values()[dayChoice].toString();
						Seance seance = cinema.findSeance(title);
						cinema.removeSeanceFromSchedule(seance, day);
					} else {
						System.err.println("Not found seances for this movie");
					}
				} else
					System.err.println("No such movie in library");
			}
			if (option == 7) {
				System.out.println("-------------------------");
				System.out.println("RESERVE SEAT Selected");
				System.out.println("-------------------------");
				System.out.println("Please enter the number of seance day:");
				chooseDay();
				int dayChoice = Integer.parseInt(choice.nextLine()) - 1;
				Days day = Days.values()[dayChoice];
				System.out.println("-------------------------");
				cinema.showSeancesByDay(day);
				System.out.println("-------------------------");
				if (!cinema.getSchedules().get(day).isEmpty()) {
					System.out.println("Please enter the number of the movie:");
					int movieChoice = Integer.parseInt(choice.nextLine());
					Seance seance = cinema.getSeanceByNumber(movieChoice, day);
					System.out.println("-------------------------");
					seance.getHall().printSeatPlan();

					System.out.print("Enter the row: ");
					int selectedRow = Integer.parseInt(choice.nextLine());
					System.out.print("Enter the seat: ");
					int selectedSeat = Integer.parseInt(choice.nextLine());
					Reserve reserve = new Reserve(seance);
					if (reserve.reserveSeat(selectedRow - 1, selectedSeat - 1)) {
						System.out.println("The seat for movie " + seance.getMovie().getTitle().toUpperCase()
								+ " has been reserved for you");
					} else
						System.out.println("Sorry the seat is already reserved");
				}
			}
			if (option == 8)
				System.exit(0);
		} while (true);
	}

	private static void chooseDay() {
		System.out.println("1. Monday");
		System.out.println("2. Thuesday");
		System.out.println("3. Wednesday");
		System.out.println("4. Thursday");
		System.out.println("5. Friday");
		System.out.println("6. Saturday");
		System.out.println("7. Sunday");
	}
}
