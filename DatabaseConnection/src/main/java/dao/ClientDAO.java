package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
/**
 * 
 * @author Crisan
 *
 */
public class ClientDAO implements clientDAOInter {

	static final Logger LOGGER = Logger.getLogger(Client.class.getName());
	private final static String findByNameStatementString = "SELECT * FROM client where name = ?";
	private final static String insertStatementString = "INSERT INTO client (id, name, address, email) VALUES (?, ?, ?, ?)";
	private final static String updateStatementString = "UPDATE client SET address = ? WHERE name = ?";
	private final static String deleteStatementString = "DELETE FROM client where name = ?";
	
	private ObservableList<Object> clientData = FXCollections.observableArrayList();
	private ObservableList<String> clientsName = FXCollections.observableArrayList();
	
	/**
	 * finds a client in the database using the given name
	 * @param clientName - name of the client to be found
	 * @return searchedClient - the searched client
	 */
	public Client findClient(String clientName) {
		
		Client searchedClient = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement(findByNameStatementString);
			findByNameStatement.setString(1, clientName);
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedClientId = resultSet.getInt("id");
				String searchedClientName = resultSet.getString("name");
				String searchedClientAddress = resultSet.getString("address");
				String searchedClientEmail = resultSet.getString("email");
				
				searchedClient = new Client(searchedClientId, searchedClientName, searchedClientAddress, searchedClientEmail);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return searchedClient;
	}
	/**
	 * inserts a new client in the database
	 * @param client - client to be inserted
	 * 
	 */
	public void insertClient(Client client) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString);
			insertStatement.setInt(1, client.getId());
			insertStatement.setString(2, client.getName());
			insertStatement.setString(3, client.getAddress());
			insertStatement.setString(4, client.getEmail());
			
			insertStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * updates a client's address
	 * @param address - new client address
	 * @param name - client name to be updated
	 */
	public void updateClient(String address, String name) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, address);
			updateStatement.setString(2, name);
			
			updateStatement.executeUpdate();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	/**
	 * deletes a client from the database
	 * @param clientName - name of the client to be deleted
	 */
	public void deleteClient(String clientName) {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setString(1, clientName);
			
			deleteStatement.execute();
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	/**
	 * 
	 * @return clientData - returns an ObservableList of clients
	 */
	public ObservableList<Object> getClients() {
		
		Client searchedClient = null;
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			findByNameStatement = dbConnection.prepareStatement("SELECT * FROM client");
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				int searchedClientId = resultSet.getInt("id");
				String searchedClientName = resultSet.getString("name");
				String searchedClientAddress = resultSet.getString("address");
				String searchedClientEmail = resultSet.getString("email");
				
				searchedClient = new Client(searchedClientId, searchedClientName, searchedClientAddress, searchedClientEmail);
				clientData.add(searchedClient);
		
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return clientData;
	}
	
	/**
	 * 
	 * @return clientsName - returns an ObservableList with the names of all the clients
	 */
	public ObservableList<String> getClientsName() {
		
		String searchedClient = new String();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findByNameStatement = null;
		ResultSet resultSet = null;
		
		try {
			findByNameStatement = dbConnection.prepareStatement("SELECT * FROM client");
			
			resultSet = findByNameStatement.executeQuery();
			while (resultSet.next()) {
				
				String searchedClientName = resultSet.getString("name");
				
				searchedClient = searchedClientName;
				
				clientsName.add(searchedClient);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO : findByName " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(findByNameStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return clientsName;
	}
}
