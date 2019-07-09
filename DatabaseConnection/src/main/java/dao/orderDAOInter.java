package dao;

import model.Order;
/**
 * interface implemented by OrderDAO class
 * @author Crisan
 *
 */
public interface orderDAOInter {
	
	public Order findOrder(String clientName);
	public void insertOrder(Order order);
	public void deleteOrder(String clientName);
}
