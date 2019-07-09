package PT2018.assig4;

/**
 * @author Crisan
 *
 */
public interface BankProc {

	public void addPerson(Person person);
	public void removePerson(Person person);
	public void addAccount(Account account, Person person);
	public void removeAccount(int accountId, Person person);
	public void withdrawMoney(int accountId, double sum, Person person);
	public void depositMoney(int accountId, double sum, Person person);
}
