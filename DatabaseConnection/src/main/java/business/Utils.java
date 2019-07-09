package business;

import java.util.ArrayList;
import java.util.logging.Logger;

import model.Product;
import dao.ProductDAO;
import model.Client;
import dao.ClientDAO;
import model.Order;
import dao.OrderDAO;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;  
/**
 * 
 * @author Crisan
 *
 */
public class Utils {
	/**
	 * empty constructor
	 */
	public Utils() {};
	
	static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	
	static final Logger LOGGER2 = Logger.getLogger(Product.class.getName());
	
	/**
	 * inserts a client with the given parameters into the database
	 * @param id - client id
	 * @param name - client name
	 * @param address = client address
	 * @param email - client email
	 */
	public void insertClient(int id, String name, String address, String email) {
		
		ClientDAO c = new ClientDAO();
		Client clientToAdd = new Client(id, name, address, email);
		c.insertClient(clientToAdd);
	}
	/**
	 * updates the address of a client
	 * @param name - client name to be updated
	 * @param address - new address
	 */
	public void updateClient(String name, String address) {
		
		ClientDAO client = new ClientDAO();
		client.updateClient(address, name);
	}
	/**
	 * deletes a client from the database
	 * @param name - client name to be deleted
	 */
	public void deleteClient(String name) {
		
		ClientDAO client = new ClientDAO();
		client.deleteClient(name);
	}
	/**
	 * 
	 * @param name - client name to be found
	 * @return name of the client if found or null if not
	 */
	public String findClient(String name) {
		
		Client client = new Client();
		ClientDAO c = new ClientDAO();
		client = c.findClient(name);
		
		if (client != null) {
			return client.getName();
		}
		else {
			return null;
		}
	}
	/**
	 * inserts a new product with the given parameters
	 * @param id - product id
	 * @param name - product name
	 * @param amount - product amount
	 * @param price - product price
	 */
	public void insertProduct(int id, String name, int amount, float price) {
		
		ProductDAO p = new ProductDAO();
		Product productToAdd = new Product(id, name, amount, price);
		p.insertProduct(productToAdd);
	}
	/**
	 * updates the amount of a product
	 * @param name - product name to be updated
	 * @param amount - new amount
	 */
	public void updateProduct(String name, int amount) {
		
		ProductDAO prod = new ProductDAO();
		prod.updateProduct(amount, name);
	}
	/**
	 * deletes a product from the database
	 * @param name - product name to be deleted
	 */
	public void deleteProduct(String name) {
		
		ProductDAO prod = new ProductDAO();
		prod.deleteProduct(name);
	}
	/**
	 * 
	 * @param name - product name to be found
	 * @return name of the product if found or null if not
	 */
	public String findProduct(String name) {
		
		Product prod = new Product();
		ProductDAO p = new ProductDAO();
		prod = p.findProduct(name);
		
		if (prod != null) {
			return prod.getName();
		}
		else {
			return null;
		}
	}
	/**
	 * creates a new order
	 * @param clientName - name of the clients that orders the product
	 * @param productName - product name that is ordered
	 * @param amount - amount that is ordered
	 * @return a string for success or a string if the amount ordered does not exist
	 */
	public String createOrder(String clientName, String productName, int amount) {
		
		OrderDAO o = new OrderDAO();
		Order order = new Order(0, clientName, productName, amount);
		o.insertOrder(order);
		
		ProductDAO p = new ProductDAO();
		int currentAmount = p.findProduct(productName).getAmount();
		if (currentAmount - amount < 0) {
			return "under";
		}
		else {
			p.updateProduct(currentAmount - amount, productName);
			return "success";
		}
	}
	/**
	 * creates the bill for a client into a pdf file
	 * @param orders - an ObservableList of orders
	 * @throws Exception
	 */
	public void createBill(ArrayList<Order> orders) throws Exception {
		
		Order order = new Order();
		
		String dest = "C:/Users/Wtp/Desktop/UTCN/An 3/Semestrul 2/PT2018/DemoProject3/bill.pdf";
		PdfWriter writer = new PdfWriter(dest);
		
		PdfDocument bill = new PdfDocument(writer);
		
		Document document = new Document(bill);
		
		ArrayList<String> info = new ArrayList<String>();
		ArrayList<Paragraph> paragraph = new ArrayList<Paragraph>();
		
		for (int i = 0; i < orders.size(); i++) {
			
			order = orders.get(i);
			String productName = order.getProductName();
			
			ProductDAO p = new ProductDAO();
			float price = p.findProduct(productName).getPrice();
			
			info.add(order.getClientName() + " ordered " + order.getAmount() + " " + order.getProductName() + " having the total price of: " + order.getAmount() * price);
			paragraph.add(new Paragraph(info.get(i)));
			
			document.add(paragraph.get(i));	
		}

		document.close();
	}
}
