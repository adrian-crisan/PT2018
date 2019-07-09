package dao;

import javafx.collections.ObservableList;
import model.Product;
/**
 * interface implemented by ProductDAO class
 * @author Crisan
 *
 */
public interface productDAOInter {

	public Product findProduct(String productName);
	public void insertProduct(Product product);
	public void updateProduct(int amount, String name);
	public void deleteProduct(String productName);
	public ObservableList<Object> getProducts();
	public ObservableList<String> getProductsName();
}
