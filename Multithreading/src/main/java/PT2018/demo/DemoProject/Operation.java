package PT2018.demo.DemoProject;

import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;


/**
 * @author Crisan
 *
 */
public class Operation extends Thread{

	public long minArrivalTime;
	public long maxArrivalTime;
	public long minServingTime;
	public long maxServingTime;
	public int clients = 0;
	public long openQueues;
	Queue [] queues;
	static long runningTime;
	public long arrivalTime;
	private String pathLog,pathQ;
	private BufferedWriter serverLog;
	private String log = " ", log2 = " "; 
	
	/**
	 * @param minArrivalTime - minimum arrival time between clients
	 * @param maxArrivalTime - maximum arrival time between clients
	 * @param minServingTime - minimum serving time for clients
	 * @param maxServingTime - maximum serving time for clients
	 * @param runningTime - total running time for the app
	 * @param openQueues - number of opened queues
	 */
	public Operation(long minArrivalTime, long maxArrivalTime, long minServingTime, long maxServingTime, long runningTime,  long openQueues) {
	
		this.openQueues = openQueues;
		queues = new Queue[(int)openQueues];
		for (int i = 0; i < openQueues; i++) {
			queues[i] = new Queue();
			queues[i].name = Integer.toString(i);
		}
		Operation.runningTime = runningTime;
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minServingTime = minServingTime;
		this.maxServingTime = maxServingTime;
		this.setLog(" ");
		try {
			File file = new File("LOG.txt");
			pathLog = file.getAbsolutePath();
			serverLog = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			System.err.println("FILE ERROR!!!");
			System.exit(1);
		}
	}
	
	/**
	 * Empty constructor for Operation class
	 */
	public Operation() {};
	
	/**
	 * @return log - return the log String
	 */
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	public String getLog2() {
		return log2;
	}

	public void setLog2(String log2) {
		this.log2 = log2;
	}
	
	/**
	 * @param queue
	 * @return currentSum - the index of the minimum serving time queue
	 */
	public long queueTime(Queue queue) {
		
		long currentSum = 0;
		for (int i = 0; i < queue.clients.size(); i++) {
			currentSum = currentSum + queue.clients.elementAt(i).getServingTime();
		}
		
		return currentSum;
	}
	/**
	 * Generates a random client within the given parameters
	 */
	public Client randomClient() {
		long servingTime = (long)(Math.random()*(maxServingTime - minServingTime + 1)) + minServingTime;
		Client toReturn = new Client(clients, servingTime, new Date());
		return toReturn;
	}
	/**
	 * Computes the arrival time for a new client
	 */
	public long newArrivalTime() {
		return (long)(Math.random()*(maxArrivalTime - minArrivalTime + 1)) + minArrivalTime;
	}
	
	public void run() {
		
		int sleepPeriod = 500;
		while(runningTime > 0) {
			clients += 1;	//increment clients number
			arrivalTime = newArrivalTime();	//create new arrival time
			Client newClient = randomClient();	//create new client
			Queue newQueue = new Queue();
			long queueTime = queueTime(queues[0]); //get queue with minimum serving time
			int queueId = 0;
			for (int i = 0; i < openQueues; i++) {
				if (queueTime(queues[i]) <= queueTime) {
					queueTime = queueTime(queues[i]);
					queueId = i;
				}
			}
			
			newQueue = queues[queueId];	
			log2 = newQueue.returnLog();
			
			if (newQueue.clients.isEmpty() == true) {
				runningTime = runningTime - arrivalTime;
			}
			newQueue.addClient(newClient);	//add client to the queue with the minimum serving time
			
			log = "Client "+newClient.getId()+ " was added to queue " +queueId+" and arrived at " + newClient.getArrivalTime() + " having serving time " + newClient.getServingTime() + "\r\n";

			System.out.println(log2);
			
			System.out.println("Client " + clients + " added to queue " + queueId + " at " + newClient.getArrivalTime() + " having st " + newClient.getServingTime());
			try {
				serverLog.write(log);
				serverLog.newLine();
				serverLog.write(log2);
				serverLog.newLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				sleep(arrivalTime * sleepPeriod);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		try {
			serverLog.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
