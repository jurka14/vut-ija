

package project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.Color;

/**
 *Class of the GUI controller - controls the gui and responds to user manipulation
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class MainController {

	@FXML
	private Pane content;
	@FXML
	private TextField timeScale;
	@FXML
	private TextField hours;
	@FXML
	private TextField minutes;
	@FXML
	private TextField seconds;
	@FXML
	private Text clock;
	@FXML
	private TextFlow info;
	
	
	private List<Drawable> elements = new ArrayList<>();
	private Timer timer;
	private List<TimeUpdate> updates = new ArrayList<>();
	private Calendar time = Calendar.getInstance();
	public Street closedStreet;
	public List<Street> aroundStreets = new ArrayList<>();
	public boolean pause = false;
	public boolean closed = false;
	
	
	public void setTime( int h, int m, int s) {
		this.time.set(2020, 5, 8, h, m, s);
	}

	public Calendar getTime() {
		return time;
	}
	
	public void displayTime() {
		clock.setText(Integer.toString(time.get(Calendar.HOUR))+" : "+
				      Integer.toString(time.get(Calendar.MINUTE))+" : "+
				      Integer.toString(time.get(Calendar.SECOND)));
	}
	
	@FXML
	public void edit() {
		
		if(this.pause == false) {
			this.pause = true;
			
		}
		else {
			for(int i = 0; i < this.aroundStreets.size(); i++) {
				content.getChildren().remove(content.getChildren().size()-1);
			}
			this.pause = false;
		}
	}
	
	
	
	@FXML
	private void timeSetter() {
		try {
			float h = Float.parseFloat(hours.getText());
			float m = Float.parseFloat(minutes.getText());
			float s = Float.parseFloat(seconds.getText());
			
			if (h < 6 || h > 23) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time");
				alert.showAndWait();
				return;
			}
			if (m < 0 || m > 59) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time");
				alert.showAndWait();
				return;
			}
			if (s < 0 || s > 59) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time");
				alert.showAndWait();
				return;
			}
			
			setTime((int)h, (int)m, (int)s);

			for (Drawable element : elements) {
				if(element instanceof Vehicle) {
					((Vehicle) element).waiting = 0; //reset waiting
					
					int index = 0;
					for(Calendar t : ((Vehicle) element).TimeList) {
						if(this.time.before(t)) {
							break;
						}
						else {
							index++;
						}
					}
					((Vehicle) element).index = index; //set index to the correct position
				}
			}
			
			
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time");
			alert.showAndWait();
		}
	}
	
	
	@FXML
	private void zoom(ScrollEvent event) {
		event.consume();
		double zoom;
		if(event.getDeltaY() > 0) {//gets the direction of zoom
			zoom = 1.1;
		}
		else {
			zoom = 0.9;
		}
		content.setScaleX(zoom * content.getScaleX());
		content.setScaleY(zoom * content.getScaleY());
		content.layout();
		
	}

	public void setElements(List<Drawable> elements) {
		this.elements = elements;
		
		for (Drawable element : elements) {
			content.getChildren().addAll(element.getGUI());
			if(element instanceof TimeUpdate) {
				updates.add((TimeUpdate) element);
			}
		}
	}
	// method for toggling line
	public void toggleLine(Vehicle vehicle) {
		Coordinate a = null;
		Coordinate b = null;
		
		if(vehicle.toggled == false) {
			//display red line
			for (int i = 0; i < vehicle.getPath().getCoords().size()-1; i++) {
				a = vehicle.getPath().getCoords().get(i);
				b = vehicle.getPath().getCoords().get(i+1);
				Line line = new Line(a.getX(), a.getY(), b.getX(), b.getY());
				line.setStroke(Color.RED);
				line.setStrokeWidth(3);
				content.getChildren().addAll(line);
			}
			//print line info
		    ObservableList<Node> textList = info.getChildren();
		    textList.add(new Text("LINE INFO:\n"));
		    
		    for(int i = 0; i < vehicle.StopList.size(); i++) {//add all stops with timetable
		    	textList.add(new Text(vehicle.StopList.get(i).getId()+
		    						  " - "+
		    						  Integer.toString(vehicle.TimeList.get(i).get(Calendar.HOUR))+
		    						  " : "+
		    						  Integer.toString(vehicle.TimeList.get(i).get(Calendar.MINUTE))+
		    						  " : "+
		    						  Integer.toString(vehicle.TimeList.get(i).get(Calendar.SECOND))+
		    						  "\n"));
		    }
		    vehicle.toggled = true;
		}
		else {//delete lines and text
			for (int i = 0; i < vehicle.getPath().getCoords().size()-1; i++) {
				content.getChildren().remove(content.getChildren().size()-1);
			}
			ObservableList<Node> textList = info.getChildren();
		    textList.clear();
			vehicle.toggled = false;
		}
		
	}
	
	public void close(Street street) {
		this.closedStreet = street;
		
		double x = (street.getStart().getX() + street.getStop().getX())/2;
		double y = (street.getStart().getY() + street.getStop().getY())/2;
		
		Line line1 = new Line(x-5, y-5, x+5, y+5);
		line1.setStroke(Color.RED);
		line1.setStrokeWidth(5);
		
		Line line2 = new Line(x-5, y+5, x+5, y-5);
		line2.setStroke(Color.RED);
		line2.setStrokeWidth(5);
		
		content.getChildren().addAll(line1, line2);
	}
	
	public void choose(Street street) {
		Line line = new Line(street.getStart().getX(), street.getStart().getY(), street.getStop().getX(), street.getStop().getY());
		line.setStroke(Color.RED);
		line.setStrokeWidth(3);
		content.getChildren().addAll(line);
		
		this.aroundStreets.add(street);
		
	}
	
	
	@FXML
	private void onTimeScaleChange() {
		try {
			float scale = Float.parseFloat(timeScale.getText());
			if (scale <= 0) {
				Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time Scale");
				alert.showAndWait();
				return;
			}
			timer.cancel();
			startTime(scale);
		} catch (NumberFormatException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Time Scale");
			alert.showAndWait();
		}
	}
	
	public void startTime(float scale) {
		
		timer = new Timer(false);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				
				if(pause == false) {
					Platform.runLater(() -> {
						displayTime();
						for (TimeUpdate update : updates) {
							update.update(time);
						}
						time.add(Calendar.SECOND, 1);
					});
				}
				
				
			}
		}, 0, 1000 / (long)scale);
	}
	
}
