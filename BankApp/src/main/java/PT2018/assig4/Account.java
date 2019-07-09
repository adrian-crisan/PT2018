package PT2018.assig4;

import java.util.Observable;

/**
 * @author Crisan
 *
 */
public class Account extends Observable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int accountId;
	private double accountBalance;
	private String accountType;
	
	/**
	 * @param accountId
	 * @param accountBalance
	 * @param accountType
	 */
	public Account(int accountId, double accountBalance, String accountType) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.accountType = accountType;
	}	
	
	/**
	 * empty constructor
	 */
	public Account() {};
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	/**
	 * @param sum - sum to be deposited
	 */
	public void depositMoney(double sum)
	{
		this.accountBalance += sum;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @param sum - sum to be withdraweds
	 */
	public void withdrawMoney(double sum)
	{
		
		this.accountBalance -= sum;
		setChanged();
		notifyObservers();
	}
}
