package PT2018.demo.DemoProject;

import java.util.*;

/**
 * @author Crisan
 *
 */

public class Client {

	private int id;
	private long servingTime;
	private Date arrivalTime;
	
	/** Client constructor
	 * @param id - client id
	 * @param servingTime - serving time for client 
	 * @param arrivalTime - arrival time for client
	 */
	public Client(int id, long servingTime, Date arrivalTime) {
		
		this.id = id;
		this.servingTime = servingTime;
		this.arrivalTime = arrivalTime;
	}
	
	/**
	 * Empty Client constructor
	 */
	public Client() {};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getServingTime() {
		return servingTime;
	}

	public void setServingTime(long servingTime) {
		this.servingTime = servingTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
}
