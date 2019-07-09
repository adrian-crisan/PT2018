package PT2018.assig4;

import java.util.*;
 
import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;  

/**
 * @author Crisan
 *
 */
public class Bank implements BankProc,java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HashMap<Person, ArrayList<Account>> hashMap = new HashMap<Person, ArrayList<Account>>();
	
	transient private ObservableList<Person> persons = FXCollections.observableArrayList();
	transient private ObservableList<Account> accounts = FXCollections.observableArrayList();
	private ArrayList<String> personNames = new ArrayList<String>();
	private ArrayList<Integer> accountIds = new ArrayList<Integer>();
	
	/**
	 * @param person - person to be added in the bank
	 */
	public void addPerson(Person person) {
		assert person != null:"The person cannot be null.";
		hashMap.put(person, new ArrayList<Account>());
	}

	/** 
	 * @param person - person to be removed from the bank
	 */
	public void removePerson(Person person) {
		assert person != null:"The person cannot be null";
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person p = entry.getKey();
			if (person.getName().equals(p.getName())) {
				hashMap.remove(p);
			}
		}
		hashMap.toString();
	}

	/**
	 * @param account - account to be added
	 * @param person - person who owns the account to be added
	 */
	public void addAccount(Account account, Person person) {
		assert person != null:"The person cannot be null.";
		assert account.getAccountType() != null:"Type must not be null.";
		assert this.isWellFormed() == true:"HashMap not null";
		assert account.getAccountBalance() > 0:"Balance must be positive.";
		if (account.getAccountType().equals("Saving")) {
			if (account.getAccountBalance() < 1000.0) {
				System.out.println("Balance too small to be saving");
			}
			else {
				SavingAccount accSave = new SavingAccount(account.getAccountId(), account.getAccountBalance());
				accSave.addObserver(person);
				for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
					Person p = entry.getKey();
					if (person.getName().equals(p.getName())) {
						hashMap.get(p).add(accSave);
					}
				}
			}
		}
		else if (account.getAccountType().equals("Spending")) {
			SpendingAccount accSpend = new SpendingAccount(account.getAccountId(), account.getAccountBalance());
			accSpend.addObserver(person);
			hashMap.get(person).add(accSpend);
		}
	}

	/**
	 * @param accountId - id of account to be removed
	 * @param person - person who owns the account to be removed
	 */
	public void removeAccount(int accountId, Person person) {
		
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person p = entry.getKey();
			if (person.getName().equals(p.getName())) {
				ArrayList<Account> accounts = hashMap.get(p);
				for (Account a : accounts) {
					if (a.getAccountId() == accountId) {
						hashMap.get(p).remove(a);
						break;
					}
				}
			}
		}
	}

	/**
	 * @param name - name of the person to be found
	 * @return toReturn - the found person, null if not found
	 */
	public Person findPerson(String name) {
		Person toReturn = new Person();
		assert name != null:"name must not be null.";
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			if (entry.getKey().getName().equals(name)) {
				toReturn.setPersonId(entry.getKey().getPersonId());
				toReturn.setName(entry.getKey().getName());
				toReturn.setAddress(entry.getKey().getAddress());
				return toReturn;
			}
		}
		return null;
	}
	
	/**
	 * @param name - name of the person whose accounts are searched
	 * @return accounts - the array containing the accounts of a person
	 */
	public ArrayList<Account> findAccounts(String name) {
		ArrayList<Account> accounts = new ArrayList<Account>();
		assert name != null:"name must not be null.";
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			if (entry.getKey().getName() == name) {
				accounts = entry.getValue();
			}
		}
		return accounts;
	}
	
	/**
	 * @return persons - list containing all the persons in the bank
	 */
	public ObservableList<Person> getAllPersons() {
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person toAdd = new Person();
			toAdd.setPersonId(entry.getKey().getPersonId());
			toAdd.setName(entry.getKey().getName());
			toAdd.setAddress(entry.getKey().getAddress());
			persons.add(toAdd);
			System.out.println("*");
		}
		return persons;
	}
	
	/**
	 * @return accounts - list containing all the accounts of all the persons in the bank
	 */
	public ObservableList<Account> getAllAccounts() {
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person p = entry.getKey();
			for (Account a : hashMap.get(p)) {
				Account toAdd = new Account();
				toAdd.setAccountId(a.getAccountId());
				toAdd.setAccountBalance(a.getAccountBalance());
				toAdd.setAccountType(a.getAccountType());
				accounts.add(toAdd);
			}
		}
		return accounts;
	}
	
	/**
	 * @return personNames - list containing the names of all the persons 
	 */
	public ArrayList<String> getAllPersonNames() {
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			personNames.add(entry.getKey().getName());
			System.out.println("*");
		}
		return personNames;
	}
	
	/**
	 * @param name - person name whose accounts are searched
	 * @return accountIds - the ids of all the accounts of the person
	 */
	public ArrayList<Integer> getAccountIds(String name) {
		
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person p = entry.getKey();
			for (Account a : hashMap.get(p)) {
				if (p.getName().equals(name)) {
					accountIds.add(a.getAccountId());
				}
			}
		}
		return accountIds;
	}
	
	/**
	 * @param accountId - id of account from which money are withdrawn
	 * @param sum - sum to be withdrawn
	 * @param person - person who owns the account
	 */
	public void withdrawMoney(int accountId, double sum, Person person) {
		assert sum > 0:"Sum must be positive.";
		ArrayList<Account> acc = this.findAccounts(person.getName());
		for (Account a : acc) {
			if (a.getAccountId() == accountId) {
				if (a.getAccountType().equals("Saving") && sum < a.getAccountBalance() * 0.7) {
					System.out.println("Cannot withdraw from saving account such small amount.");
				}
				else {
					a.withdrawMoney(sum);
				}
			}
		}
	}

	/**
	 * @param accountId - account id to deposit money in
	 * @param sum - sum to be deposited
	 * @param person - person who owns the account
	 */
	public void depositMoney(int accountId, double sum, Person person) {
		assert sum > 0:"Sum must be positive.";
		ArrayList<Account> acc = this.findAccounts(person.getName());
		for (Account a : acc) {
			System.out.println(a.getAccountId());
			if (a.getAccountId() == accountId) {
				System.out.println(a.getAccountType());
				if (a.getAccountType().equals("Saving")) {
					if (a.getAccountBalance()*0.5 > sum) {
						System.out.println("Cannot deposit such a small amount");
					}
					else {
						a.depositMoney(sum);
					}
				}
				else if (a.getAccountType().equals("Spending")) {
					a.depositMoney(sum);
				}
			}
		}
	}
	
	/**
	 * prints the entire content of the bank
	 */
	public void showHashMap() {
		for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
			Person p = entry.getKey();
			if (hashMap.get(p).isEmpty()) {
				System.out.println(p.getName() + " " + p.getAddress());
			}
			else {
				for (Account a : hashMap.get(p)) {
					System.out.println(p.getName() + " " + p.getAddress() + " " + a.getAccountType() + " " + a.getAccountBalance());
				}
			}
		}
	}
	
	/**
	 * generates a pdf report containg the data bank
	 */
	public void generateReport() {
		assert hashMap != null:"Can't be null.";

		try {
			if (this!= null) {
				String dest = "C:/Users/Wtp/Desktop/UTCN/An 3/Semestrul 2/PT2018/assig4/report.pdf";
				
				PdfWriter writer = new PdfWriter(dest);
			
				PdfDocument report = new PdfDocument(writer);
					
				Document document = new Document(report);
				
				for (Map.Entry<Person, ArrayList<Account>> entry : hashMap.entrySet()) {
					Person p = entry.getKey();
					document.add(new Paragraph("\n" + p.getName() + " " + p.getAddress()));
					for (Account a : hashMap.get(p)) {
						document.add(new Paragraph("\n" + "\t" + p.getName() + " " + p.getAddress() + " " + a.getAccountType() + " " + a.getAccountBalance()));
					}
				}
				
				document.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return boolean - true if map not empty, false otherwise
	 */
	public boolean isWellFormed() {
		if (hashMap == null) {
			return false;
		}
		return true;
	}

}
