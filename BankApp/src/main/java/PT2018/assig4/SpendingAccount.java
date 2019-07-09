package PT2018.assig4;

/**
 * @author Crisan
 *
 */
public class SpendingAccount extends Account implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static String type = "Spending";
	
	/**
	 * @param accountId
	 * @param accountBalance
	 */
	public SpendingAccount(int accountId, double accountBalance) {
		super(accountId, accountBalance, type);
		System.out.println(countObservers());
	}
	
	/**
	 * @param sum - sum to be deposited
	 */
	public void depositMoney(double sum) {
		this.setAccountBalance(this.getAccountBalance() + sum);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @param sum - sum to be withdrawn
	 */
	public void withdrawMoney(double sum) {
		if (sum < this.getAccountBalance()) {
			this.setAccountBalance(this.getAccountBalance() - sum);
			setChanged();
			notifyObservers();
		}
		else {
			System.out.println("Not enougb money to withdraw.");
		}
	}
}
