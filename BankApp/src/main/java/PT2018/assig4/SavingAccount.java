package PT2018.assig4;

/**
 * @author Crisan
 *
 */
public class SavingAccount extends Account implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static String type = "Saving";
	
	/**
	 * @param accountId
	 * @param accountBalance
	 */
	public SavingAccount(int accountId, double accountBalance) {
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
	 * @param sum - sum to be withdrawed
	 */
	public void withdrawMoney(double sum) {
		if (sum < this.getAccountBalance()) {
			this.setAccountBalance(this.getAccountBalance() - sum);
			setChanged();
			notifyObservers();
		}
		else {
			System.out.println("Not enough moeny to withdraw.");
		}
	}
}
