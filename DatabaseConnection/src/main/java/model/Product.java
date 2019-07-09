package model;

/**
 * @author Crisan
 *
 */
public class Product {
	
	private int id;
	private String name;
	private int amount;
	private float price;
	
	/**
	 * @param id - product id
	 * @param name - product name
	 * @param amount - product amount
	 * @param price - product price
	 */
	public Product(int id, String name, int amount, float price) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.price = price;
	}
	
	public Product() {}
	/**
	 * 
	 * getters and setters
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}	
}
