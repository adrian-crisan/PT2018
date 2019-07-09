package model;

/**
 * @author Crisan
 *
 */
public class Order {
	
	private int id;
	private String clientName;
	private String productName;
	private int amount;
	
	/**
	 * @param id - order id
	 * @param clientName - name of the client that ordered the product
	 * @param productName - name of the ordered product
	 * @param amount - amount of product ordered
	 */
	public Order(int id, String clientName, String productName, int amount) {
		this.id = id;
		this.clientName = clientName;
		this.productName = productName;
		this.amount = amount;
	}
	
	public Order() {}
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
