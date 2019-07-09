package PT2018.assig4;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;


public class GUI extends Application {
	
	private TableView<Person> table = new TableView<Person>();
	
	private TableView<Account> table2 = new TableView<Account>();
	
	Bank bank;
	
	private void showWithdrawErrorAlert() {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Withdraw Error");
		error.setHeaderText(null);
		error.setContentText("Cannot withdraw from saving account a sum that small!");
		error.showAndWait();
	}
	
	@SuppressWarnings("unchecked")
	public void start(final Stage stage) throws Exception {
		
		bank = new Bank();
		Person person1 = new Person(1, "Crisan", "Baii");
		Person person2 = new Person(2, "Dumea", "Parcului");
		Person person3 = new Person(3, "Ciubuc", "Mehedinti");
		
		bank.addPerson(person1);
		bank.addPerson(person2);
		bank.addPerson(person3);
		
		Account account1 = new Account(1, 2000, "Saving");
		Account account2 = new Account(2, 7000, "Spending");
		bank.addAccount(account1, person1);
		bank.addAccount(account2, person1);
		
		bank.showHashMap();
		
		bank.depositMoney(2, 10000, person1);
		bank.withdrawMoney(2, 1000, person1);
		bank.showHashMap();
		
		Serialization s = new Serialization();
		s.serialize(bank);
		
		Bank newBank = s.deserialize();
		
		table.setEditable(true);

		TableColumn<Person, Integer> personId = new TableColumn<Person, Integer>("ID");
		personId.setMinWidth(20);
		personId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("personId"));
		
		TableColumn<Person, String> personName = new TableColumn<Person, String>("Name");
		personName.setMinWidth(100);
		personName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		
		TableColumn<Person, String> personAddress = new TableColumn<Person, String>("Address");
		personAddress.setMinWidth(100);
		personAddress.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));

		table.getColumns().addAll(personId, personName, personAddress);
		
		Button backPersons = new Button("Back");
		backPersons.setLayoutX(200);
		backPersons.setLayoutY(400);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(table, backPersons);
		
		final Scene personsScene = new Scene(vbox);
		
		
		TableColumn<Account, Integer> accountId = new TableColumn<Account, Integer>("ID");
		accountId.setMinWidth(20);
		accountId.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountId"));
		
		TableColumn<Account, String> accountBalance = new TableColumn<Account, String>("Balance");
		accountBalance.setMinWidth(100);
		accountBalance.setCellValueFactory(new PropertyValueFactory<Account, String>("accountBalance"));
		
		TableColumn<Account, String> accountType = new TableColumn<Account, String>("Type");
		accountType.setMinWidth(100);
		accountType.setCellValueFactory(new PropertyValueFactory<Account, String>("accountType"));

		table2.getColumns().addAll(accountId, accountBalance, accountType);
		
		Button backAccount = new Button("Back");
		backAccount.setLayoutX(200);
		backAccount.setLayoutY(400);
		
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(table2, backAccount);
		
		final Scene accountScene = new Scene(vbox2);
		
		
		Text personIdText = new Text("Person ID");
		Text personNameText = new Text("Person Name");
		Text personAddressText = new Text("Person Address");
		
		final TextField personIdTextField = new TextField();
		final TextField personNameTextField = new TextField();
		final TextField personAddressTextField = new TextField();

		Button addP = new Button("Add");
		Button backAddP = new Button("Back");
		GridPane addPersonGrid = new GridPane();
		addPersonGrid.setMinSize(500, 500);
		addPersonGrid.setPadding(new Insets(10,10,10,10));
		addPersonGrid.setVgap(5);
		addPersonGrid.setHgap(5);
		addPersonGrid.setAlignment(Pos.TOP_LEFT);
		addPersonGrid.add(personIdText, 0, 0);
		addPersonGrid.add(personIdTextField, 1, 0);
		addPersonGrid.add(personNameText, 0, 1);
		addPersonGrid.add(personNameTextField, 1, 1);
		addPersonGrid.add(personAddressText, 0, 2);
		addPersonGrid.add(personAddressTextField, 1, 2);
		addPersonGrid.add(addP, 0, 4);
		addPersonGrid.add(backAddP, 0, 5);
		addPersonGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene addPersonScene = new Scene(addPersonGrid);
		
		
		Text accountIdText = new Text("Account ID");
		Text accountBalanceText = new Text("Balance");
		Text accountTypeText = new Text("Type");
		Text personToAddAccount = new Text("Person");
		
		final TextField accountIdTextField = new TextField();
		final TextField accountBalanceTextField = new TextField();
		final TextField accountTypeTextField = new TextField();
		final TextField personToAddAccountTextField = new TextField();
		
		Button addA = new Button("Add");
		Button backAddA = new Button("Back");
		GridPane addAccountGrid = new GridPane();
		addAccountGrid.setMinSize(500, 500);
		addAccountGrid.setPadding(new Insets(10,10,10,10));
		addAccountGrid.setVgap(5);
		addAccountGrid.setHgap(5);
		addAccountGrid.setAlignment(Pos.TOP_LEFT);
		addAccountGrid.add(accountIdText, 0, 0);
		addAccountGrid.add(accountIdTextField, 1, 0);
		addAccountGrid.add(accountBalanceText, 0, 1);
		addAccountGrid.add(accountBalanceTextField, 1, 1);
		addAccountGrid.add(accountTypeText, 0, 2);
		addAccountGrid.add(accountTypeTextField, 1, 2);
		addAccountGrid.add(personToAddAccount, 0, 3);
		addAccountGrid.add(personToAddAccountTextField, 1, 3);
		addAccountGrid.add(addA, 0, 4);
		addAccountGrid.add(backAddA, 0, 5);
		addAccountGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene addAccountScene = new Scene(addAccountGrid);
		
		Text personNameDeleteText = new Text("Person Name");

		final TextField personNameDeleteTextField = new TextField();

		Button deleteP = new Button("Delete");
		Button deletePBack = new Button("Back");
		GridPane deletePersonGrid = new GridPane();
		deletePersonGrid.setMinSize(500, 500);
		deletePersonGrid.setPadding(new Insets(10,10,10,10));
		deletePersonGrid.setVgap(5);
		deletePersonGrid.setHgap(5);
		deletePersonGrid.setAlignment(Pos.TOP_LEFT);
		deletePersonGrid.add(personNameDeleteText, 0, 1);
		deletePersonGrid.add(personNameDeleteTextField, 1, 1);
		deletePersonGrid.add(deleteP, 0, 4);
		deletePersonGrid.add(deletePBack, 0, 5);
		deletePersonGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene deletePersonScene = new Scene(deletePersonGrid);
		
		Text accountIdDeleteText = new Text("Account Id");
		Text personToDeleteAccountText = new Text("Person");
		
		final TextField accountIdDeleteTextField = new TextField();
		final TextField personToDeleteAccountTextField = new TextField();

		Button deleteA = new Button("Delete");
		Button deleteABack = new Button("Back");
		GridPane deleteAccountGrid = new GridPane();
		deleteAccountGrid.setMinSize(500, 500);
		deleteAccountGrid.setPadding(new Insets(10,10,10,10));
		deleteAccountGrid.setVgap(5);
		deleteAccountGrid.setHgap(5);
		deleteAccountGrid.setAlignment(Pos.TOP_LEFT);
		deleteAccountGrid.add(accountIdDeleteText, 0, 1);
		deleteAccountGrid.add(accountIdDeleteTextField, 1, 1);
		deleteAccountGrid.add(personToDeleteAccountText, 0, 2);
		deleteAccountGrid.add(personToDeleteAccountTextField, 1, 2);
		deleteAccountGrid.add(deleteA, 0, 4);
		deleteAccountGrid.add(deleteABack, 0, 5);
		deleteAccountGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene deleteAccountScene = new Scene(deleteAccountGrid);
		
		
		ArrayList<String> personNames = new ArrayList<String>();
		
		personNames = bank.getAllPersonNames();
	
		Text withdrawMoney = new Text("Withdraw - Account Id");
		Text withdrawAmountText = new Text("Amount");
			
		final TextField withdrawAmountTextField = new TextField("");
		final TextField accountIdTextFieldW = new TextField();
		
		Button withdrawMoneyB = new Button("Withdraw");
		Button backWithdraw = new Button("Back");
		final ComboBox<String> selectPerson = new ComboBox<String>();
		
				
		selectPerson.getItems().addAll(personNames);

		GridPane withdrawGrid = new GridPane();
		withdrawGrid.setMinSize(500, 500);
		withdrawGrid.setPadding(new Insets(10,10,10,10));
		withdrawGrid.setVgap(5);
		withdrawGrid.setHgap(5);
		withdrawGrid.setAlignment(Pos.TOP_LEFT);
		withdrawGrid.add(withdrawMoney, 0, 0);
		withdrawGrid.add(selectPerson, 1, 1);
		withdrawGrid.add(accountIdTextFieldW, 0, 1);
		withdrawGrid.add(withdrawAmountText, 2, 0);
		withdrawGrid.add(withdrawAmountTextField, 2, 1);
		withdrawGrid.add(withdrawMoneyB, 0, 2);
		withdrawGrid.add(backWithdraw, 0, 3);
		withdrawGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene withdrawScene = new Scene(withdrawGrid);
		
	
		Text depositMoney = new Text("Deposit");
		Text depositAmountText = new Text("Amount");
			
		final TextField depositAmountTextField = new TextField("");
		final TextField accountIdTextFieldD = new TextField();
		
		Button depositMoneyB = new Button("Deposit - Account Id");
		Button backDeposit = new Button("Back");
		final ComboBox<String> selectPersonD = new ComboBox<String>();
		
				
		selectPersonD.getItems().addAll(personNames);

		GridPane depositGrid = new GridPane();
		depositGrid.setMinSize(500, 500);
		depositGrid.setPadding(new Insets(10,10,10,10));
		depositGrid.setVgap(5);
		depositGrid.setHgap(5);
		depositGrid.setAlignment(Pos.TOP_LEFT);
		depositGrid.add(depositMoney, 0, 0);
		depositGrid.add(selectPersonD, 1, 1);
		depositGrid.add(accountIdTextFieldD, 0, 1);
		depositGrid.add(depositAmountText, 2, 0);
		depositGrid.add(depositAmountTextField, 2, 1);
		depositGrid.add(depositMoneyB, 0, 2);
		depositGrid.add(backDeposit, 0, 3);
		depositGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene depositScene = new Scene(depositGrid);
		
		Button showPersons = new Button("Show Persons");
		showPersons.setLayoutX(10);
		showPersons.setLayoutY(10);
		
		Button addPerson = new Button("Add New Person");
		addPerson.setLayoutX(10);
		addPerson.setLayoutY(40);
		
		Button deletePerson = new Button("Delete Person");
		deletePerson.setLayoutX(10);
		deletePerson.setLayoutY(70);
		
		Button withdraw = new Button("Withdraw");
		withdraw.setLayoutX(10);
		withdraw.setLayoutY(100);
		
		Button showAccounts = new Button("Show Accounts");
		showAccounts.setLayoutX(10);
		showAccounts.setLayoutY(130);
		
		Button addAccount = new Button("Add New Account");
		addAccount.setLayoutX(10);
		addAccount.setLayoutY(160);
		
		Button deleteAccount = new Button("Delete Account");
		deleteAccount.setLayoutX(10);
		deleteAccount.setLayoutY(190);
		
		Button deposit = new Button("Deposit");
		deposit.setLayoutX(10);
		deposit.setLayoutY(220);
		
		Button generateReport = new Button("Generate Report");
		generateReport.setLayoutX(10);
		generateReport.setLayoutY(250);
		
		Group group = new Group(showPersons, addPerson, deletePerson, withdraw, showAccounts, addAccount, deleteAccount, deposit, generateReport);
		
		final Scene scene = new Scene(group, 500, 500);
	
		showPersons.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {

				table.getItems().clear();
				table.setItems(bank.getAllPersons());
				stage.setScene(personsScene);
				stage.show();
			}
		});
		
		backPersons.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});

		showAccounts.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {

				table2.getItems().clear();
				table2.setItems(bank.getAllAccounts());
				stage.setScene(accountScene);
				stage.show();
			}
		});
		
		backAccount.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		withdraw.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(withdrawScene);
				stage.show();
			}
		});

		withdrawMoneyB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String amount = withdrawAmountTextField.getText().toString();
				double amountInt = Double.parseDouble(amount);
				String person = selectPerson.getValue().toString();
				String accountIdW = accountIdTextFieldW.getText().toString();
				int accountIdWInt = Integer.parseInt(accountIdW);
				Person toWithdraw = bank.findPerson(person);
				ArrayList<Account> acc = bank.findAccounts(person);
				for (Account a : acc) {
					if (a.getAccountId() == accountIdWInt) {
						if (a.getAccountType().equals("Saving")) {
							if (amountInt < a.getAccountBalance() * 0.7) {
								showWithdrawErrorAlert();
							}
						}
					}
				}
				
				bank.withdrawMoney(accountIdWInt, amountInt, toWithdraw);
			}
		});
		
		backWithdraw.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		deposit.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(depositScene);
				stage.show();
			}
		});

		depositMoneyB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String amount = depositAmountTextField.getText().toString();
				double amountInt = Double.parseDouble(amount);
				String person = selectPersonD.getValue().toString();
				String accountIdD = accountIdTextFieldD.getText().toString();
				int accountIdWInt = Integer.parseInt(accountIdD);
				Person toDeposit = bank.findPerson(person);
				
				bank.depositMoney(accountIdWInt, amountInt, toDeposit);
			}
		});
		
		backDeposit.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		addPerson.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(addPersonScene);
				stage.show();
			}
		});

		addP.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String personToAddId = personIdTextField.getText().toString();
				String personToAddName = personNameTextField.getText().toString();
				String personToAddAddress = personAddressTextField.getText().toString();
				int personToAddIdInt = Integer.parseInt(personToAddId);
				
				Person toAdd = new Person(personToAddIdInt, personToAddName, personToAddAddress);
				bank.addPerson(toAdd);
			}
		});
		
		backAddP.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		addAccount.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(addAccountScene);
				stage.show();
			}
		});

		addA.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String accountToAddId = accountIdTextField.getText().toString();
				String accountToAddBalance = accountBalanceTextField.getText().toString();
				String accountToAddType = accountTypeTextField.getText().toString();
				int accountToAddIdInt = Integer.parseInt(accountToAddId);
				double accountToAddBalanceDouble = Double.parseDouble(accountToAddBalance);
				String name = personToAddAccountTextField.getText().toString();
				
				Person p = bank.findPerson(name);
				
				Account toAdd = new Account(accountToAddIdInt, accountToAddBalanceDouble, accountToAddType);
				bank.addAccount(toAdd, p);
			}
		});
		
		backAddA.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		deletePerson.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(deletePersonScene);
				stage.show();
			}
		});

		deleteP.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String personToDeleteName = personNameDeleteTextField.getText();
				Person toDelete =null;
				toDelete = bank.findPerson(personToDeleteName);
				System.out.println(toDelete.getPersonId());
				
				bank.removePerson(toDelete);
				bank.toString();
			}
		});
		
		deletePBack.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		deleteAccount.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(deleteAccountScene);
				stage.show();
			}
		});

		deleteA.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String accountToDeleteId = accountIdDeleteTextField.getText();
				int accountToDeleteIdInt = Integer.parseInt(accountToDeleteId);
				String name = personToDeleteAccountTextField.getText().toString();
				
				Person p = bank.findPerson(name);
				
				bank.removeAccount(accountToDeleteIdInt, p);
			}
		});
		
		deleteABack.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		generateReport.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				bank.generateReport();
			}
		});

		stage.setTitle("Bank");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
