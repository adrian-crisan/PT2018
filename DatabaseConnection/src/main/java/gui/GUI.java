package gui;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Client;
import model.Order;
import model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;

import business.Utils;
import dao.ClientDAO;
import dao.OrderDAO;
import dao.ProductDAO;
/**
 * 
 * @author Crisan
 *
 */
public class GUI extends Application {

	private TableView<Object> table = new TableView<Object>();
	private TableView<Object> table2 = new TableView<Object>();
	
	private ObservableList<String> clients = FXCollections.observableArrayList();
	private ObservableList<String> products = FXCollections.observableArrayList();
	
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	private void showInsertAlert() {
		Alert error = new Alert(AlertType.CONFIRMATION);
		error.setTitle("Insert Success");
		error.setHeaderText(null);
		error.setContentText("Insertion successful!");
		error.showAndWait();
	}
	
	private void showUpdateAlert() {
		Alert error = new Alert(AlertType.CONFIRMATION);
		error.setTitle("Update Success");
		error.setHeaderText(null);
		error.setContentText("Update successful!");
		error.showAndWait();
	}
	
	private void showDeleteAlert() {
		Alert error = new Alert(AlertType.CONFIRMATION);
		error.setTitle("Delete Success");
		error.setHeaderText(null);
		error.setContentText("Delete successful.");
		error.showAndWait();
	}
	
	private void showDeleteErrorAlert() {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Delete Error");
		error.setHeaderText(null);
		error.setContentText("Delete failed because insertion does not exist!");
		error.showAndWait();
	}
	
	private void showUnderflowAlert() {
		Alert error = new Alert(AlertType.ERROR);
		error.setTitle("Underflow Error");
		error.setHeaderText(null);
		error.setContentText("Order failed to create because not enough amount in stock!");
		error.showAndWait();
	}
	
	private void showOrderAlert() {
		Alert error = new Alert(AlertType.CONFIRMATION);
		error.setTitle("Order Success");
		error.setHeaderText(null);
		error.setContentText("Successfully ordered the product.");
		error.showAndWait();
	}
	
	private static <T,S> TableColumn<T,S> createTableColumn(Class<S> type, Field field, String text) {
	    TableColumn<T, S> col =  new TableColumn<T,S>(text);
	    col.setCellValueFactory(cellData -> {
	        try {
	            boolean wasAccessible = field.isAccessible() ;
	            field.setAccessible(true);
	            @SuppressWarnings("unchecked")
	            SimpleObjectProperty<S> property = new SimpleObjectProperty<S>((S)(field.get(cellData.getValue())));
	            field.setAccessible(wasAccessible);
	            return property;
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }

	    });
	    return col ;
	}
	
	@SuppressWarnings("unchecked")
	public void start(final Stage stage) throws Exception {
		
		Label label = new Label("Clients");
		label.setFont(new Font("Arial", 20));
	
		Class<Client> client = Client.class;
		Field fieldId = client.getDeclaredField("id");
		Field fieldName = client.getDeclaredField("name");
		Field fieldAddress = client.getDeclaredField("address");
		Field fieldEmail = client.getDeclaredField("email");
		
		table.setEditable(true);
		
		TableColumn<Object, Client> clientId = createTableColumn(client, fieldId, "ID"); 
		clientId.setMinWidth(20);
		
		TableColumn<Object, Client> clientName = createTableColumn(client, fieldName, "Name");
		clientName.setMinWidth(100);
	
		TableColumn<Object, Client> clientAddress = createTableColumn(client, fieldAddress, "Address");
		clientAddress.setMinWidth(100);

		TableColumn<Object, Client> clientEmail = createTableColumn(client, fieldEmail, "Email");
		clientEmail.setMinWidth(100);
		
		ClientDAO c = new ClientDAO();
		table.setItems(c.getClients());
		table.getColumns().addAll(clientId, clientName, clientAddress, clientEmail);
		
		
		Button backClients = new Button("Back");
		backClients.setLayoutX(200);
		backClients.setLayoutY(400);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(table, backClients);
		
		final Scene clientScene = new Scene(vbox);
	
		
		Label label2 = new Label("Products");
		label2.setFont(new Font("Arial", 20));
		
		Class<Product> product = Product.class;
		Field fieldProdId = product.getDeclaredField("id");
		Field fieldProdName = product.getDeclaredField("name");
		Field fieldProdAmount = product.getDeclaredField("amount");
		Field fieldProdPrice = product.getDeclaredField("price");
		
		table.setEditable(true);
		
		TableColumn<Object, Product> productId = createTableColumn(product, fieldProdId, "ID"); 
		clientId.setMinWidth(20);
		
		TableColumn<Object, Product> productName = createTableColumn(product, fieldProdName, "Name");
		clientName.setMinWidth(100);
	
		TableColumn<Object, Product> productAmount = createTableColumn(product, fieldProdAmount, "Amount");
		clientAddress.setMinWidth(100);

		TableColumn<Object, Product> productPrice = createTableColumn(product, fieldProdPrice, "Price");
		clientEmail.setMinWidth(100);

		ProductDAO p = new ProductDAO();
		table2.setItems(p.getProducts());
		table2.getColumns().addAll(productId, productName, productAmount, productPrice);
		
		
		Button backProducts = new Button("Back");
		backProducts.setLayoutX(200);
		backProducts.setLayoutY(400);
		
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(table2, backProducts);
		
		final Scene productScene = new Scene(vbox2);
		
		Button showClients = new Button("Show Clients");
		showClients.setLayoutX(10);
		showClients.setLayoutY(10);
		
		Button addClient = new Button("Add Client");
		addClient.setLayoutX(10);
		addClient.setLayoutY(40);
		
		Button updateClientB = new Button("Update Client");
		updateClientB.setLayoutX(10);
		updateClientB.setLayoutY(70);
		
		Button deleteClientB = new Button("Delete Client");
		deleteClientB.setLayoutX(10);
		deleteClientB.setLayoutY(100);
		
		Button showProducts = new Button("Show Products");
		showProducts.setLayoutX(150);
		showProducts.setLayoutY(10);
		
		Button addProduct = new Button("Add Product");
		addProduct.setLayoutX(150);
		addProduct.setLayoutY(40);
		
		Button updateProductB = new Button("Update Product");
		updateProductB.setLayoutX(150);
		updateProductB.setLayoutY(70);
		
		Button deleteProductB = new Button("Delete Product");
		deleteProductB.setLayoutX(150);
		deleteProductB.setLayoutY(100);
		
		Button createOrderB = new Button("Create Order");
		createOrderB.setLayoutX(10);
		createOrderB.setLayoutY(130);
		
		final TextField billName = new TextField("");
		billName.setLayoutX(80);
		billName.setLayoutY(160);
		Button createBill = new Button("Create Bill");
		createBill.setLayoutX(10);
		createBill.setLayoutY(160);
		
		Text deleteClientNameText = new Text("Client Name");
		
		final TextField deleteClientNameTextField = new TextField();
		
		Button deleteClient = new Button("Delete");
		Button backDeleteClient = new Button("Back");
		GridPane deleteClientGrid = new GridPane();
		deleteClientGrid.setMinSize(500, 500);
		deleteClientGrid.setPadding(new Insets(10,10,10,10));
		deleteClientGrid.setVgap(5);
		deleteClientGrid.setHgap(5);
		deleteClientGrid.setAlignment(Pos.TOP_CENTER);
		deleteClientGrid.add(deleteClientNameText, 0, 0);
		deleteClientGrid.add(deleteClientNameTextField, 0, 1);
		deleteClientGrid.add(deleteClient, 0, 2);
		deleteClientGrid.add(backDeleteClient, 0, 3);
		deleteClientGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene deleteClientScene = new Scene(deleteClientGrid);
		
		
		Text clientIdText = new Text("Client ID");
		Text clientNameText = new Text("Client Name");
		Text clientAddressText = new Text("Client Address");
		Text clientEmailText = new Text("Client E-mail");
		
		final TextField clientIdTextField = new TextField();
		final TextField clientNameTextField = new TextField();
		final TextField clientAddressTextField = new TextField();
		final TextField clientEmailTextField = new TextField();
		
		Button insertClient = new Button("Add");
		Button backAdd = new Button("Back");
		GridPane addClientGrid = new GridPane();
		addClientGrid.setMinSize(500, 500);
		addClientGrid.setPadding(new Insets(10,10,10,10));
		addClientGrid.setVgap(5);
		addClientGrid.setHgap(5);
		addClientGrid.setAlignment(Pos.TOP_LEFT);
		addClientGrid.add(clientIdText, 0, 0);
		addClientGrid.add(clientIdTextField, 1, 0);
		addClientGrid.add(clientNameText, 0, 1);
		addClientGrid.add(clientNameTextField, 1, 1);
		addClientGrid.add(clientAddressText, 0, 2);
		addClientGrid.add(clientAddressTextField, 1, 2);
		addClientGrid.add(clientEmailText, 0, 3);
		addClientGrid.add(clientEmailTextField, 1, 3);
		addClientGrid.add(insertClient, 0, 4);
		addClientGrid.add(backAdd, 0, 5);
		addClientGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene addClientScene = new Scene(addClientGrid);
		

		Text updateClientNameText = new Text("Client Name");
		Text updateClientAddressText = new Text("Client Address");
		
		final TextField updateClientNameTextField = new TextField();
		final TextField updateClientAddressTextField = new TextField();

		Button updateClient = new Button("Update");
		Button backUpdateClient = new Button("Back");
		GridPane updateClientGrid = new GridPane();
		updateClientGrid.setMinSize(500, 500);
		updateClientGrid.setPadding(new Insets(10,10,10,10));
		updateClientGrid.setVgap(5);
		updateClientGrid.setHgap(5);
		updateClientGrid.setAlignment(Pos.TOP_LEFT);
		updateClientGrid.add(updateClientNameText, 0, 0);
		updateClientGrid.add(updateClientNameTextField, 1, 0);
		updateClientGrid.add(updateClientAddressText, 0, 1);
		updateClientGrid.add(updateClientAddressTextField, 1, 1);
		updateClientGrid.add(updateClient, 0, 2);
		updateClientGrid.add(backUpdateClient, 0, 3);
		updateClientGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene updateClientScene = new Scene(updateClientGrid);
		
		
		Text deleteProductNameText = new Text("Product Name");
		
		final TextField deleteProductNameTextField = new TextField();
		
		Button deleteProduct = new Button("Delete");
		Button backDeleteProduct = new Button("Back");
		GridPane deleteProductGrid = new GridPane();
		deleteProductGrid.setMinSize(500, 500);
		deleteProductGrid.setPadding(new Insets(10,10,10,10));
		deleteProductGrid.setVgap(5);
		deleteProductGrid.setHgap(5);
		deleteProductGrid.setAlignment(Pos.TOP_CENTER);
		deleteProductGrid.add(deleteProductNameText, 0, 0);
		deleteProductGrid.add(deleteProductNameTextField, 0, 1);
		deleteProductGrid.add(deleteProduct, 0, 2);
		deleteProductGrid.add(backDeleteProduct, 0, 3);
		deleteProductGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene deleteProductScene = new Scene(deleteProductGrid);
		
		
		Text productIdText = new Text("Product ID");
		Text productNameText = new Text("Product Name");
		Text productAmountText = new Text("Product Amount");
		Text productPriceText = new Text("Product Price");
		
		final TextField productIdTextField = new TextField();
		final TextField productNameTextField = new TextField();
		final TextField productAmountTextField = new TextField();
		final TextField productPriceTextField = new TextField();
		
		Button insertProduct = new Button("Add");
		Button backAddProduct = new Button("Back");
		GridPane addProductGrid = new GridPane();
		addProductGrid.setMinSize(500, 500);
		addProductGrid.setPadding(new Insets(10,10,10,10));
		addProductGrid.setVgap(5);
		addProductGrid.setHgap(5);
		addProductGrid.setAlignment(Pos.TOP_LEFT);
		addProductGrid.add(productIdText, 0, 0);
		addProductGrid.add(productIdTextField, 1, 0);
		addProductGrid.add(productNameText, 0, 1);
		addProductGrid.add(productNameTextField, 1, 1);
		addProductGrid.add(productAmountText, 0, 2);
		addProductGrid.add(productAmountTextField, 1, 2);
		addProductGrid.add(productPriceText, 0, 3);
		addProductGrid.add(productPriceTextField, 1, 3);
		addProductGrid.add(insertProduct, 0, 4);
		addProductGrid.add(backAddProduct, 0, 5);
		addProductGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene addProductScene = new Scene(addProductGrid);
		

		Text updateProductNameText = new Text("Product Name");
		Text updateProductAmountText = new Text("Product Amount");
		
		final TextField updateProductNameTextField = new TextField();
		final TextField updateProductAmountTextField = new TextField();

		Button updateProduct = new Button("Update");
		Button backUpdateProduct = new Button("Back");
		GridPane updateProductGrid = new GridPane();
		updateProductGrid.setMinSize(500, 500);
		updateProductGrid.setPadding(new Insets(10,10,10,10));
		updateProductGrid.setVgap(5);
		updateProductGrid.setHgap(5);
		updateProductGrid.setAlignment(Pos.TOP_LEFT);
		updateProductGrid.add(updateProductNameText, 0, 0);
		updateProductGrid.add(updateProductNameTextField, 1, 0);
		updateProductGrid.add(updateProductAmountText, 0, 1);
		updateProductGrid.add(updateProductAmountTextField, 1, 1);
		updateProductGrid.add(updateProduct, 0, 2);
		updateProductGrid.add(backUpdateProduct, 0, 3);
		updateProductGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene updateProductScene = new Scene(updateProductGrid);
		
		clients = c.getClientsName();
		products = p.getProductsName();
		
		Text createOrderText = new Text("Create Order");
		Text orderAmountText = new Text("Amount");
			
		final TextField orderAmount = new TextField();
		
		Button createOrder = new Button("Create");
		Button backCreateOrder = new Button("Back");
		final ComboBox<String> selectClient = new ComboBox<String>();
		final ComboBox<String> selectProduct = new ComboBox<String>();
		
		selectClient.getItems().addAll(clients);
		selectProduct.getItems().addAll(products);
		
		GridPane createOrderGrid = new GridPane();
		createOrderGrid.setMinSize(500, 500);
		createOrderGrid.setPadding(new Insets(10,10,10,10));
		createOrderGrid.setVgap(5);
		createOrderGrid.setHgap(5);
		createOrderGrid.setAlignment(Pos.TOP_LEFT);
		createOrderGrid.add(createOrderText, 0, 0);
		createOrderGrid.add(selectClient, 0, 1);
		createOrderGrid.add(selectProduct, 1, 1);
		createOrderGrid.add(orderAmountText, 2, 0);
		createOrderGrid.add(orderAmount, 2, 1);
		createOrderGrid.add(createOrder, 0, 2);
		createOrderGrid.add(backCreateOrder, 0, 3);
		createOrderGrid.setStyle("-fx-background-color: BEIGE;");
		final Scene createOrderScene = new Scene(createOrderGrid);
		
		
		Group group = new Group(showClients, showProducts, addClient, updateClientB, deleteClientB, createOrderB, addProduct, updateProductB, deleteProductB, createBill, billName);
		
		final Scene scene = new Scene(group, 500, 500);
		
		showClients.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(clientScene);
				stage.show();
			}
		});
		
		backClients.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		showProducts.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(productScene);
				stage.show();
			}
		});
		
		backProducts.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		addClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(addClientScene);
				stage.show();
			}
		});

		insertClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String clientToAddId = clientIdTextField.getText().toString();
				String clientToAddName = clientNameTextField.getText().toString();
				String clientToAddAddress = clientAddressTextField.getText().toString();
				String clientToAddEmail = clientEmailTextField.getText().toString();
				int clientToAddIdInt = Integer.parseInt(clientToAddId);
				
				Utils add = new Utils();
				add.insertClient(clientToAddIdInt, clientToAddName, clientToAddAddress, clientToAddEmail);
				
				showInsertAlert();
			}
		});
		
		backAdd.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		updateClientB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(updateClientScene);
				stage.show();
			}
		});
	
		updateClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				String clientToUpdateName = updateClientNameTextField.getText().toString();
				String clientToUpdateAddress = updateClientAddressTextField.getText().toString();
				
				Utils update = new Utils();
				update.updateClient(clientToUpdateName, clientToUpdateAddress);
				
				showUpdateAlert();
			}
		});
		
		backUpdateClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		deleteClientB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(deleteClientScene);
				stage.show();
			}
		});
	
		deleteClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				String clientToDeleteName = deleteClientNameTextField.getText().toString();
				
				Utils u = new Utils();
				
				String client = u.findClient(clientToDeleteName);
				
				if (client != null) {
					Utils delete = new Utils();
					delete.deleteClient(clientToDeleteName);
					showDeleteAlert();
				}
				else {
					showDeleteErrorAlert();
				}
			}
		});
		
		backDeleteClient.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		createOrderB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(createOrderScene);
				stage.show();
			}
		});
	
		createOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				String client = selectClient.getValue().toString();
				String product = selectProduct.getValue().toString();
				String amount = orderAmount.getText().toString();
				int amountInt = Integer.parseInt(amount);
				
				Utils u = new Utils();
				String alert = u.createOrder(client, product, amountInt);
				
				if (alert.equals("under")) {
					showUnderflowAlert();
				}
				else {
					showOrderAlert();
				}
				
			}
		});
		
		backCreateOrder.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		addProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(addProductScene);
				stage.show();
			}
		});

		insertProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String productToAddId = productIdTextField.getText().toString();
				String productToAddName = productNameTextField.getText().toString();
				String productToAddAmount = productAmountTextField.getText().toString();
				String productToAddPrice = productPriceTextField.getText().toString();
				int productToAddIdInt = Integer.parseInt(productToAddId);
				int productToAddAmountInt = Integer.parseInt(productToAddAmount);
				float productToAddPriceFloat = Float.parseFloat(productToAddPrice);
				
				Utils add = new Utils();
				add.insertProduct(productToAddIdInt, productToAddName, productToAddAmountInt, productToAddPriceFloat);
				
				showInsertAlert();
			}
		});
		
		backAddProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		updateProductB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(updateProductScene);
				stage.show();
			}
		});
	
		updateProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				String productToUpdateName = updateProductNameTextField.getText().toString();
				String productToUpdateAmount = updateProductAmountTextField.getText().toString();
				int productToUpdateAmountInt = Integer.parseInt(productToUpdateAmount);
				
				Utils update = new Utils();
				update.updateProduct(productToUpdateName, productToUpdateAmountInt);
				
				showUpdateAlert();
			}
		});
		
		backUpdateProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		deleteProductB.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(deleteProductScene);
				stage.show();
			}
		});
	
		deleteProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
			
				String productToDeleteName = deleteProductNameTextField.getText().toString();
				
				Utils u = new Utils();
				
				String product = u.findProduct(productToDeleteName);
				
				if (product != null) {
					Utils delete = new Utils();
					delete.deleteProduct(productToDeleteName);
					showDeleteAlert();
				}
				else {
					showDeleteErrorAlert();
				}
			}
		});
		
		backDeleteProduct.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				stage.setScene(scene);
				stage.show();
			}
		});
		
		createBill.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				
				String name = billName.getText().toString();
				OrderDAO o = new OrderDAO();
				
				Utils u = new Utils();
				orders = o.getOrders(name);

				try {
					u.createBill(orders);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		stage.setTitle("Shop");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
