package model;

/**
 * @author Crisan
 *
 */
public class Client {

	private int id;
	private String name;
	private String address;
	private String email;

	/**
	 * @param id - the client id
	 * @param name - the client name
	 * @param address - the client address
	 * @param email - the client name
	 */
	public Client(int id, String name, String address, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
	}
	
	public Client() {};
	/**
	 * getters and setters
	 * 
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
