

public class TestDriver {
	public static void main(String[] args) {
        Venue Nickelodeon = new Cineplex("Nickelodeon", "Main Street");
        Admin Xavier = new Admin("Xavier", "09/09/1999", "notanemail@gmail.com", "GOD", "****", 12, Nickelodeon );
        System.out.println(Xavier.toString());
        //Xavier.addShowListing();
        String [] times = {"12/12 12:00AM", "12/01 01:00PM", "12/13 02:00PM"};
        Show show = new Show(Nickelodeon, "Frozen", "Town Freezes to Death", times, null, 12, 13, 12.00);
        Nickelodeon.shows.addShow(show);
        Nickelodeon.shows.printShows();
        //Show seeShow = Nickelodeon.shows.shows.get("frozen");
        Xavier.createTicket(show);
        Xavier.printTicket();
        Xavier.createTicket(show);
        Xavier.printTicket();
        Xavier.createShowReview(show);
        Xavier.createVenueReview(Nickelodeon);
        System.out.print(show.toString());
        System.out.print(Nickelodeon.toString());
    }
}
