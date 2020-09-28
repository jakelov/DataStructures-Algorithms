
public class Requests {
	private String command; // Command, either r (request) or t (advance time).
	private int time;	// The time at which the airline requests use of runway.
	private Airline airline; // Airline object storing the meta data of the airline.

	/**
		Constructor: creates a Request object that represents the t command. No need for airline information.
	**/
	public Requests(String c, int t) {
		command = c;
		time = t;
		airline = null;
	}

	/**
		Constructor: creates a Request object that represents the r command. Stores the airline information for this request.
	**/
	public Requests(String c, int t, String name, String num, String src, String des) {
		command = c;
		time = t;
		airline = new Airline(name, num, src, des);
	}

	/**
		Airline class to hold the airline information.
	**/
	public class Airline {
		private String name; // Name of the airline.
		private String number; // Flight number.
		private String source; // Flight source.
		private String destination; // Flight destination.

		/**
			Constructor: creates an Airline object with appropriate information.
		**/
		public Airline(String n, String num, String src, String des) {
			name = n;
			number = num;
			source = src;
			destination = des;
		}

		/**
			toString method to print the string representation of an airline.
		**/
		public String toString() {
			return name + " " + number + " " + source + " " + destination;
		}
	}

	/**
		Accessor to get the command.
	**/
	public String getCommand() {
		return command;
	}

	/**
		Accessor to get the request time.
	**/
	public int getTime() {
		return time;
	}

	/**
		Accessor to get the airline.
	**/
	public Airline getAirline() {
		return airline;
	}

	/**
		toString method to print the string representation of a request.
	**/
	public String toString() {
		return command + " " + time + " " + airline;
	}	
}
