package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Product;
/**
 * 
 * @author Crisan
 *
 */
public class ProductDAO implements productDAOInter {

	static final Logger LOGGER = Logger.getLogger(Product.class.getName());
	private final static String findByNameStatementString = "SELECT * FROM product where name = ?";
	private final static String insertStatementString = "INSERT INTO product (id, name, amount, price) VALUES (?, ?, ?, ?)";
	private final static String updateStatementString = "UPDATE product SET amount = ? WHERE name = ?";
	private final static String deleteStatementString = "DELETE FROM product where name = ?";
	
	private ObservableList<Object> productData = FXCollections.observableArrayList();
	private ObservableList<String> productsName = FXCollections.observableArrayList();
	
	/**
	 * finds a new product in the database
	 * @param productName - product name to be searched
	 * @return searchedProduct - found product
	 */
	public Product findProduct(String productName) {
		
		Product searchedProduct = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement(findByNameStatementString);
			findByNameStatement.setString(1, productName);
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedProductId = resultSet.getInt("id");
				String searchedProductName = resultSet.getString("name");
				int searchedProductAmount = resultSet.getInt("amount");
				float searchedProductPrice = resultSet.getFloat("price");
				
				searchedProduct = new Product(searchedProductId, searchedProductName, searchedProductAmount, searchedProductPrice);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return searchedProduct;
	}
	/**
	 * inserts a new product in the database
	 * @param product - product to be inserted
	 */
	public void insertProduct(Product product) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setInt(1, product.getId());
			insertStatement.setString(2, product.getName());
			insertStatement.setInt(3, product.getAmount());
			insertStatement.setFloat(4, product.getPrice());
			
			insertStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * updates the amount of the given product
	 * @param amount - the new amount
	 * @param name - the product name to be updated
	 */
	public void updateProduct(int amount, String name) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, amount);
			updateStatement.setString(2, name);
			
			updateStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * deletes a product from the database
	 * @param productName - name of the product to be deleted
	 */
	public void deleteProduct(String productName) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, productName);
			
			deleteStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	

	/**
	 * @return productData - returns an ObservableList of products
	 */
	public ObservableList<Object> getProducts() {
		
		Product searchedProduct = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement("SELECT * FROM product");
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedProductId = resultSet.getInt("id");
				String searchedProductName = resultSet.getString("name");
				int searchedProductAmount = resultSet.getInt("amount");
				float searchedProductPrice = resultSet.getFloat("price");
				
				searchedProduct = new Product(searchedProductId, searchedProductName, searchedProductAmount, searchedProductPrice);
				productData.add(searchedProduct);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return productData;
	}
	
	/**
	 * 
	 * @return productsName - returns an ObservableList with the names of all the products
	 */
	public ObservableList<String> getProductsName() {
		
		String searchedProduct = new String();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement("SELECT * FROM product");
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				String searchedProductName = resultSet.getString("name");
				
				searchedProduct = searchedProductName;
				productsName.add(searchedProduct);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProductDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return productsName;
	}
}
