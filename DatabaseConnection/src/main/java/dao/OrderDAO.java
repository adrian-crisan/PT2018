package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Order;
/**
 * 
 * @author Crisan
 *
 */
public class OrderDAO implements orderDAOInter {

	static final Logger LOGGER = Logger.getLogger(Order.class.getName());
	private final static String findByNameStatementString = "SELECT * FROM shop.order where clientName = ?";
	private final static String insertStatementString = "INSERT INTO shop.order (id, clientName, productName, amount) VALUES (?, ?, ?, ?)";
	private final static String deleteStatementString = "DELETE FROM shop.order where clientName = ?";
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	/**
	 * finds an order by the given client name
	 * @param clientName - client name 
	 * @return searchedOrder - returns the searched order
	 */
	public Order findOrder(String clientName) {
		
		Order searchedOrder = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement(findByNameStatementString);
			findByNameStatement.setString(1, clientName);
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedOrderId = resultSet.getInt("id");
				String searchedOrderClientName = resultSet.getString("clientName");
				String searchedOrderProductName = resultSet.getString("productName");
				int searchedOrderAmount = resultSet.getInt("amount");
				
				searchedOrder = new Order(searchedOrderId, searchedOrderClientName, searchedOrderProductName, searchedOrderAmount);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return searchedOrder;
	}
	/**
	 * inserts a new order in the database
	 * @param order - order to be inserted
	 */
	public void insertOrder(Order order) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setInt(1, order.getId());
			insertStatement.setString(2, order.getClientName());
			insertStatement.setString(3, order.getProductName());
			insertStatement.setInt(4, order.getAmount());
			
			insertStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO : insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * deletes an order from the database
	 * @param clientName - client name
	 */
	public void deleteOrder(String clientName) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, clientName);
			
			deleteStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO : delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * returns all the orders of a given client
	 * @param clientName - client name to be searched
	 * @return orders - all the orders of the client in an ObservableList
	 */
	public ArrayList<Order> getOrders(String clientName) {
		
		Order searchedOrder = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement("SELECT * FROM shop.order where clientName = ?");
			findByNameStatement.setString(1, clientName);
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedOrderId = resultSet.getInt("id");
				String searchedOrderClientName = resultSet.getString("clientName");
				String searchedOrderProductName = resultSet.getString("productName");
				int searchedOrderAmount = resultSet.getInt("amount");
				
				searchedOrder = new Order(searchedOrderId, searchedOrderClientName, searchedOrderProductName, searchedOrderAmount);
				orders.add(searchedOrder);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "OrderDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return orders;
	}
}
