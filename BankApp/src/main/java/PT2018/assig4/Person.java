package PT2018.assig4;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Crisan
 *
 */
public class Person implements Observer, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int personId;
	private String name;
	private String address;

	
	/**
	 * @param personId
	 * @param name
	 * @param address
	 */
	public Person(int personId, String name, String address) {
		super();
		this.personId = personId;
		this.name = name;
		this.address = address;
	}

	/**
	 * 
	 * empty constructor
	 */
	public Person() {};
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
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
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Observed.");
	}

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", name=" + name + ", address=" + address + "]";
	}
	
}
