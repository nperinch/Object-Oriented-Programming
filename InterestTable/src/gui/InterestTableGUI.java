package gui;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;


public class InterestTableGUI extends Application {



	public void start(Stage primaryStage) { 
		FlowPane pane = new FlowPane(); //begin a flow pan
		pane.setAlignment(Pos.CENTER); //align in the center
		TextArea answerField = new TextArea(); //text area for answer
		new ScrollPane(answerField); //allow answer to have a scroll bar
		answerField.setEditable(false); 
		pane.getChildren().addAll(answerField); //add answer box to pane

		
		int s_width = 550, s_height = 325; //dimensions of pane

		Label rate = new Label("Rate(Percentage): "); //label for user input box
		Label principal = new Label("Principal: "); 
		Label num_years = new Label("Number of Years: "); //label for scroll bar
		num_years.setTranslateX(70); //change X position of label 
		
		TextField rateText = new TextField(); //textfield for user input label
		TextField principalText = new TextField(); //textfield for user input label
		Slider yrs = new Slider(); //slider for num_years
		pane.getChildren().addAll(principal, principalText);
		pane.getChildren().addAll(rate, rateText);


		
		//set values in slider and add to pane(lines 45-53)
		yrs.setMin(1);
		yrs.setMax(25);
		yrs.setValue(25);
		yrs.setMajorTickUnit(1);
		yrs.setShowTickMarks(true);
		yrs.setShowTickLabels(true);
		yrs.setTranslateX(70);
		pane.getChildren().addAll(num_years, yrs);
		
		//create buttons to retrieve answers,adjust positions to make layout neat, and add to pane(lines 56-65)
		Button compound_interest = new Button("Compund Interest"); 
		Button simple_interest = new Button("Simple Interest"); 
		Button both_interests = new Button("Both Interests");
		simple_interest.setTranslateX(-230);
		simple_interest.setTranslateY(50);
		compound_interest.setTranslateX(-180);
		compound_interest.setTranslateY(50);
		both_interests.setTranslateX(170);
		both_interests.setTranslateY(18);
		pane.getChildren().addAll(simple_interest, compound_interest,both_interests);

		//set a scene to view the calculator and show the stage (lines 68-71)
		Scene scene = new Scene(pane, s_width, s_height);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//if compound interest button is pressed
		compound_interest.setOnAction(new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) { 
				answerField.clear(); //clear what was in answerfield before
				double yr = yrs.getValue(); //get year value from scroll bar
				double r = Double.parseDouble(rateText.getText()); //get the text in rate text
				double prin = Double.parseDouble(principalText.getText()); //get principal text
				answerField.appendText("Principal: $"+ prin+", "+ "Rate: "+ r); //add new lines to answer field
				answerField.appendText("\nYear, Compound Interest Amount");
				for (int i=1; i<= yr; i++) {
					answerField.appendText("\n"+i+"-->"+"$" + String.format("%.2f",Calculations.compoundInterest(r,prin, i))); //provide values for each year leading up to selected year including selected year
				}
				
			}
		});
		//same as above but when simple interest in pressed
		class SimpleHandler implements EventHandler<ActionEvent>{ 
			public void handle(ActionEvent e) { 
				answerField.clear();
				double yr = yrs.getValue();
				double r = Double.parseDouble(rateText.getText());
				double prin= Double.parseDouble(principalText.getText());
				answerField.appendText("Principal: $"+ prin+", "+ "Rate: "+ r);
				answerField.appendText("\nYear, Simple Interest Amount");
				for (int i=1; i<= yr; i++) {
					answerField.appendText("\n"+i+"-->"+"$" + String.format("%.2f",Calculations.simpleInterest(r,prin, i)));
				}
			}
		}	
		simple_interest.setOnAction(new SimpleHandler());
		
		//same as above but when both interests is pressed
		both_interests.setOnAction(e ->{ 
			answerField.clear();
			double yr = yrs.getValue();
			double r = Double.parseDouble(rateText.getText());
			double prin = Double.parseDouble(principalText.getText());
			answerField.appendText("Principal: $"+ prin+", "+ "Rate: "+ r);
			answerField.appendText("\nYear, Simple Interest Amound, Compound Interest Amount");
			for (int i=1; i<= yr; i++) {
				answerField.appendText("\n"+i+"-->"+"$" + String.format("%.2f",Calculations.simpleInterest(r,prin, i))+"-->"+"$" + String.format("%.2f",Calculations.compoundInterest(r,prin, i)));
			}
		});
	}
	
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
