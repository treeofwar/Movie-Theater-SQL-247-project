
import java.io.*;
import java.util.*;

public class Guest {
		
		protected Ticket ticket;

		public Guest() {
			
			this.ticket = null;
		}

		/**
		 * creates a ticket based on the show
		 * @param show
		 */
		public void createTicket(Show show) {
			Scanner keyboard = new Scanner(System.in);
			System.out.println("what time would you like see?\n" + show.showTimes());
			boolean q = false;
			String time = "";
			while(!q) {
				q =true;
				time = keyboard.nextLine();
				if(time.length()!=13) {
					System.out.println("Please enter a time that is in mm/dd 00:00am format");
					q = false;
				}
				else {
				//	if(show.showTimes().contains(time.toUpperCase())){
						
				//	}
					//else {
				//		System.out.println("Please enter an avaliable show time");
				//		System.out.println(show.showTimes());
					//}
				}
			}
			System.out.println("what seats would you like to reserve?");
			show.printSeats(time);

			System.out.println("Enter seats in AA AB AC format");
			q = false;
			String[] seats = null;
			while(!q) {
				String seatString = keyboard.nextLine().toUpperCase();
				seats = seatString.split(" ");
				q = true;
				//checks if the string is formatted correctly else returns user to enter the seats correctly
				boolean formatted = false;
				for(int i=0; i < seats.length; i++) {
					if(seats[i].length()!=2) {
					System.out.println("Make sure its in AA BB CC format with no space after the last seat");
					q = false;
					break;
					}
					if( i == seats.length - 1)
						formatted = true;
				}
				//if formatted correctly and the seats arent reseverd, reserves the seats, else returns user to reenter unreserved seats
				if(formatted&&!show.reserveSeats(time, seats)) {
					q = false;
					System.out.println("Some of those seats are already reserved, please enter a new batch of seats that are unreserved.");
				}
			}
			double price = seats.length*show.getPrice();//price of ticket logic
			
			/*
			System.out.println("Would you like to pre buy popcorn or other food from this venue");
			String response = keyboard.nextLine();
			Food[] food = null;
			//TODO response logic
			if(response.contains("y")) {
				System.out.println("Foods available at " +show.getVenue().getName()+ " are:");
				show.getVenue().printFood();
				//TODO finish ability to add food to ticket
				Ticket t = new Ticket(show,time,seats,food,price);
				this.ticket = t;			}
			else { */  //food to ticket logic commented out till food implemented
				Ticket t = new Ticket(show,time,seats,price);
				this.ticket = t;
			//}
		}

		/**
		 * Purchases the ticket currently in the user ticket space and prints it
		 */
		public void purchaseTicket() {
			//null check
			if(this.ticket == null) {
				System.out.println("There is nothing in your ticket, please find a show you would like to watch to begin purchasing tickets.");
			}
			
			/*
			 * TODO grab the driver function that creates a regular user and upgrade guest user to regular user
			 * 
			 * 
			 * SUPER IMPORTANT TODO
			 * 
			 */
			
			this.printTicket();
		}

		/**
		 * calls the toString of the ticket saved to the user
		 * generates the ticket and writes it to a text file
		 */
		public void printTicket() {
			try {
				FileWriter writer = new FileWriter("Ticket.txt");
				if(ticket.getFood() == null) {
					writer.write("*************************************\n");
					writer.write(ticket.toStringWithFood());
					writer.write("*************************************");
					writer.close();
				}
				else {
					writer.write("*************************************\n");
					writer.write(ticket.toString());
					writer.write("*************************************");
					writer.close();
				}
				System.out.println("Your ticket has been generated!");
			}catch(IOException e) {
				System.out.println("an error occurred");
				e.printStackTrace();
			}
		}


		public void generateETicket(Ticket ticket) {
			System.out.println(this.ticket.toString());
		}

		/**
		 * Xavier
		 * removes a generated ticket from the user and frees up the reserved seats
		 */
		public void CancelTicket() {
			//null check
			if(this.ticket == null) {
				System.out.println("There is nothing in your ticket.");
				return;
			}
			this.ticket.show.cancelSeatReservation(ticket.time, ticket.seats);
			this.ticket = null;
		}
}
