package PT2018.demo.DemoProject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * @author Crisan
 *
 */
public class GUI extends Application {
	
	public void start(final Stage stage) throws Exception {
	
		Text minArrivalTime = new Text("Min Arrival Time");
		Text maxArrivalTime = new Text("Max Arrival Time");
		Text minServingTime = new Text("Min Serving Time");
		Text maxServingTime = new Text("Max Serving Time");
		Text noQueues = new Text("Queues number");
		Text runningTime = new Text("Running Time");
		
		final TextField minArrivalTimeTextField = new TextField();
		final TextField maxArrivalTimeTextField = new TextField();
		final TextField minServingTimeTextField = new TextField();
		final TextField maxServingTimeTextField = new TextField();
		final TextField noQueuesTextField = new TextField();
		final TextField runningTimeTextField = new TextField();
		
		Button run = new Button("Run");
		Button clear = new Button("Clear");
		
		GridPane grid = new GridPane();
		grid.setMinSize(500, 500);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.TOP_LEFT);
		grid.add(minArrivalTime, 0, 0);
		grid.add(minServingTime, 0, 1);
		grid.add(noQueues, 0, 2);
		grid.add(run, 0, 3);
		grid.add(minArrivalTimeTextField, 1, 0);
		grid.add(minServingTimeTextField, 1, 1);
		grid.add(noQueuesTextField, 1, 2);
		grid.add(clear, 1, 3);
		grid.add(maxArrivalTime, 2, 0);
		grid.add(maxServingTime, 2, 1);
		grid.add(runningTime, 2, 2);
		grid.add(maxArrivalTimeTextField, 3, 0);
		grid.add(maxServingTimeTextField, 3, 1);
		grid.add(runningTimeTextField, 3, 2);
		
		grid.setStyle("-fx-background-color: BEIGE;");
		final Scene scene = new Scene(grid);
		
		run.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				String minArrivalTime = minArrivalTimeTextField.getText().toString();
				String maxArrivalTime = maxArrivalTimeTextField.getText().toString();
				String minServingTime = minServingTimeTextField.getText().toString();
				String maxServingTime = maxServingTimeTextField.getText().toString();
				String noQueues = noQueuesTextField.getText().toString();
				String runningTime = runningTimeTextField.getText().toString();
				
				long minArrivalTimeL = Long.parseLong(minArrivalTime);
				long maxArrivalTimeL = Long.parseLong(maxArrivalTime);
				long minServingTimeL = Long.parseLong(minServingTime);
				long maxServingTimeL = Long.parseLong(maxServingTime);
				long noQueuesL = Long.parseLong(noQueues);
				long runningTimeL = Long.parseLong(runningTime);
				
				Operation o = new Operation(minArrivalTimeL, maxArrivalTimeL, minServingTimeL, maxServingTimeL, runningTimeL, noQueuesL);
				
				for (int i = 0; i < noQueuesL; i++) {
					o.queues[i].start();
				}
				o.start();
				
				Text [] queues = new Text[(int)noQueuesL];
				TextField [] queuesTextField = new TextField[(int)noQueuesL];
				GridPane runGrid = new GridPane();
				
				stage.setScene(null);
				stage.close();
				
				for (int i = 0; i < (int)noQueuesL; i++) {
					Text queue = new Text("Queue " + i);
					TextField queueTextField = new TextField();
					
					queues[i] = queue;
	
					queueTextField = o.queues[i].textField;
					queuesTextField[i] = queueTextField;
					
					runGrid.setMinSize(500, 500);
					runGrid.setVgap(5);
					runGrid.setHgap(5);
					runGrid.setAlignment(Pos.TOP_CENTER);
					runGrid.add(queues[i], 0, i);
					runGrid.add(queuesTextField[i], 1, i);	
				}
				
				Scene scene2 = new Scene(runGrid);
				stage.setTitle("Running");
				stage.setScene(scene2);
				stage.show();
						
			}
		});
		
		clear.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent e) {
				  minArrivalTimeTextField.setText("");
				  maxArrivalTimeTextField.setText("");
				  minServingTimeTextField.setText("");
				  maxServingTimeTextField.setText("");
				  noQueuesTextField.setText("");
				  runningTimeTextField.setText("");
			}
		});
		
		stage.setTitle("Queues");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String args[]) {
		launch(args);
	}
}
