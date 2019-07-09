package dao;

import javafx.collections.ObservableList;
import model.Client;
/**
 * interface implemented by ClientDAO class
 * @author Crisan
 *
 */
public interface clientDAOInter {
	
	public Client findClient(String clientName);
	public void insertClient(Client client);
	public void updateClient(String address, String name);
	public void deleteClient(String clientName);
	public ObservableList<Object> getClients();
	public ObservableList<String> getClientsName();
}
