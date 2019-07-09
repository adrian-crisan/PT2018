package PT2018.demo.DemoProject;

import java.util.Date;
import java.util.Vector;
import javafx.scene.control.TextField;

/**
 * @author Crisan
 *
 */
public class Queue extends Thread {
	
	public Vector<Client> clients = new Vector<Client>();
	public String name;
	public TextField textField = new TextField();
	public String log = " ";
	
	/** Empty constructor for Queue class
	 * 
	 */
	public Queue() {};
	
	/* 
	 * 
	 */
	public void run() {
		
		int sleepPeriod = 500;
		Operation o = new Operation();
		
		while (o.runningTime > 0) {
			if (clients.isEmpty() == false) {
				try {
					sleep(clients.elementAt(0).getServingTime()*sleepPeriod);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Client client = clients.elementAt(0);
				log = "Client "+ client.getId() + " left queue " + this.name + " at " + new Date() +  "\r\n";
				this.setLog(log);
				removeClient();	//remove client after it was served
			
			}
		}
	}
	
	public void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * @return log - returns the log message
	 */
	public String returnLog() {
		return log;
	}
	
	/**
	 * @param client - add new Client to queue
	 */
	public synchronized void addClient(Client client) {
		clients.add(client);
		textField.setText(this.updateQueue());
		notifyAll();
	}
	
	/**
	 *  remove client from queue
	 */
	public synchronized void removeClient() {
		try {
			while (clients.isEmpty() == true) {
				wait();	//if queue is empty, wait until a client is added
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Operation o = new Operation();
		o.runningTime = o.runningTime - clients.elementAt(0).getServingTime();
		clients.removeElementAt(0);	//remove the first client
		textField.setText(this.updateQueue());
		
		System.out.println(log);
		notifyAll();
	}

	/**
	 * @return toReturn - returns the String with the updated queue after adding and removing clients
	 */
	public synchronized String updateQueue() {
		String toReturn = "";
		for (int i = 0; i< this.clients.size(); i++) {
			toReturn = toReturn + " C" + this.clients.elementAt(i).getId();
		}
		return toReturn;
	}
}
