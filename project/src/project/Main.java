package project;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

/**
*
*Main class of the project - starts the application
*
*@author Vojtech Jurka (xjurka08)
*@author Samuel Krempasky (xkremp01)
*/

public class Main extends Application {

	public static void main(String[] args) {


		Application.launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("layout.fxml"));
		BorderPane root = loader.load();
		Scene scene = new Scene(root);//loads the scene to a window
		stage.setScene(scene);
		stage.show();
		
		MainController controller = loader.getController();
		controller.setTime(6, 0, 0);
		
		List<Drawable> elements = new ArrayList<>();


		//XML add a streets
		XMLInput input= new XMLInput();

		List<Street> StreetList = input.getStreets();
		List<Stop> StopList = input.getStops();
		List<Vehicle> VehicleList = input.getLines();
		elements.addAll(StreetList);
		elements.addAll(StopList);
		elements.addAll(VehicleList);

		for (int i = 0; i < StreetList.size(); i++) {
			for (int j = 0; j < StopList.size(); j++){
				if (StreetList.get(i).liesOnStreet(StopList.get(j))){

					StreetList.get(i).setStop(StopList.get(j));
					StopList.get(j).setStreet(StreetList.get(i));

				}
			}
		}
		
		

		//event handler for line clicking
		EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				
				if(controller.pause == true) {//street closing
					if(controller.closed == false) {
						for(Drawable element : elements) {
							if(element.getGUI().get(0) == (Text) e.getTarget()) {
								controller.close((Street) element);
								controller.closed = true;
							}
						}
					}
					else { //closed == true, choosing the way around
						if(e.getButton() == MouseButton.SECONDARY) {
							for(Drawable element : elements) {
								if(element.getGUI().get(0) == (Text) e.getTarget()) {
									controller.choose((Street) element);
								}
							}
						}
						else {//setting the way around for any vehicle that is supposed to go through the closed street
							Coordinate a = null;
							Coordinate b = null;
							
							for(Drawable element : elements) {
								if(element instanceof Vehicle) {
									for(int i = 0; i < ((Vehicle) element).getPath().getCoords().size()-1; i++) {
										a = ((Vehicle) element).getPath().getCoords().get(i);
										b = ((Vehicle) element).getPath().getCoords().get(i+1);
										
										if(controller.closedStreet.getStart().equals(a) && controller.closedStreet.getStop().equals(b)) {
											for(int j = 0; j < controller.aroundStreets.size()-1; j++) {
												((Vehicle) element).path.path.add(i+j+1, controller.aroundStreets.get(j).getStop());
											}
										}
									}
								}
							}
							
							controller.edit();
						}
						
					}
				}
				else { //line clicking
					for(Drawable element : elements) {
						if(element.getGUI().get(0) == (Circle) e.getTarget()) {
							controller.toggleLine((Vehicle) element);
						}
					}
				}
			}
		};
				
		for(Drawable element : elements) {
			if(element instanceof Vehicle) {
				element.getGUI().get(0).setOnMouseClicked(handler);
				
			}
			if(element instanceof Street) {
				element.getGUI().get(0).setOnMouseClicked(handler);
			}
			
		}

		//set the elements and start the time
		controller.setElements(elements);
		controller.startTime(1);
	}

}
