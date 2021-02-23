import java.util.Arrays;

public class Theater {
	private static final int UNICODE_OFFSET= 65;
		private Integer theaterid;
		private Integer showid;
		private String time;
		private boolean [][] seats;
		private int rows;private int cols;
		
		public Theater(Integer theaterid, Integer show, int rows, int cols, String time, String seats) {
			this.cols = cols;
			this.rows = rows;
			this.theaterid = theaterid;
			this.showid = show;
			this.seats = new boolean[rows][cols];
			for(int i = 0; i < rows; i++) {
                for(int j = 0; j < cols; j++) 
                    if(seats.charAt((i+1)*j)==('N')) {
                    this.seats[i][j]= false;
                    }
                    else
                        this.seats[i][j]= true;
            }
			this.setTime(time);
		}
		
		public Theater(Integer theaterid, Integer show, int rows, int cols, String time) {
			this.cols = cols;
			this.rows = rows;
			this.theaterid = theaterid;
			this.showid = show;
			this.seats = new boolean[rows][cols];
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) 
					this.seats[i][j]= true;
			}
			this.setTime(time);
		}
		// getters and setters
		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public boolean[][] getSeats() {
			return seats;
		}

		public void setSeats(boolean[][] seats) {
			this.seats = seats;
		}

		public Integer getShowID() {
			return showid;
		}

		@Override
		public String toString() {
			return "times:" + time;
		}
		public void printSeats() {
            System.out.print("  ");
            //prints the row names
            for(int i = 0; i < cols; i++ ) {
                char c =  (char) (i+UNICODE_OFFSET);
                System.out.print(c+" ");
            }
            //prints the seats as (O)pen or (x)occupied, prints the row label first.
            System.out.println();
            for(int i = 0; i < rows; i++) {
                char c =  (char) (i+UNICODE_OFFSET);
                System.out.print(c+ " ");
                for (int j = 0; j < cols; j++) {
                    if(seats[i][j]==true)
                        System.out.print("O ");
                    else
                        System.out.print("X ");
                }
                System.out.println();
        }
    }
	}