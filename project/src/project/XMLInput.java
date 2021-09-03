

package project;

/*
 *
 *gets a stops, streets, coordinates, lines and their timetables
 *
 */


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 *Class for executing the XML file data input
 *@author Vojtech Jurka (xjurka08)
 *@author Samuel Krempasky (xkremp01)
 */

public class XMLInput {
    NodeList StreetList;
    NodeList StopList;
    NodeList LinesList;




    public XMLInput (){
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse("data/bigInput.xml");
            this.StreetList=doc.getElementsByTagName("Street");
            this.StopList=doc.getElementsByTagName("Stop");
            this.LinesList=doc.getElementsByTagName("Line");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Example:
    *       <Street>
    *        <name>Alžbetina</name>
    *        <startX>1000</startX>
    *        <startY>250</startY>
    *        <stopX>1500</stopX>
    *        <stopY>1500</stopY>
    *       </Street>
    *
    * */

    public List<Street> getStreets(){
        String Name = null;
        Double startX= null;
        Double startY= null;
        Double stopX= null;
        Double stopY= null;

        List<Street> returnList = new ArrayList<>();

        for (int i=0; i<this.StreetList.getLength();i++){
            Node street = this.StreetList.item(i);
            if (street.getNodeType()==Node.ELEMENT_NODE){
                Element streetEl= (Element) street;
                NodeList atts=streetEl.getChildNodes();
                for (int j=0; j<atts.getLength();j++){
                    //this iterates through child elements like start and stop

                    Node streetAtt=  atts.item(j);
                    if (street.getNodeType()==Node.ELEMENT_NODE) {

                        switch(streetAtt.getNodeName()){
                            case "name":
                                Name=streetAtt.getTextContent();
                                break;
                            case "startX":
                                startX=Double.parseDouble(streetAtt.getTextContent());
                                break;
                            case "startY":
                                startY=Double.parseDouble(streetAtt.getTextContent());
                                break;
                            case "stopX":
                                stopX=Double.parseDouble(streetAtt.getTextContent());
                                break;
                            case "stopY":
                                stopY=Double.parseDouble(streetAtt.getTextContent());
                                break;

                            }


                        }
                    }

                returnList.add(new Street(Name, new Coordinate(startX, startY), new Coordinate(stopX, stopY)));


                }

            }

        return  returnList;

    }

    public List<Stop>  getStops(){
        String Name = null;
        String streetName=null;
        Double coorX= null;
        Double coorY= null;


        List<Stop> returnList = new ArrayList<>();

        for (int i=0; i<this.StopList.getLength();i++){
            Node street = this.StopList.item(i);
            if (street.getNodeType()==Node.ELEMENT_NODE){
                Element streetEl= (Element) street;
                NodeList atts=streetEl.getChildNodes();
                for (int j=0; j<atts.getLength();j++){
                    //this iterates through child elements like start and stop

                    Node streetAtt=  atts.item(j);
                    if (street.getNodeType()==Node.ELEMENT_NODE) {

                        switch(streetAtt.getNodeName()){
                            case "name":
                                Name=streetAtt.getTextContent();
                                break;
                            case "coorX":
                                coorX=Double.parseDouble(streetAtt.getTextContent());
                                break;
                            case "coorY":
                                coorY=Double.parseDouble(streetAtt.getTextContent());
                                break;
                            case "streetName":
                                streetName=streetAtt.getTextContent();
                                break;

                        }

                    }
                }

                returnList.add(new Stop(Name, new Coordinate(coorX, coorY), streetName));

            }

        }
        return  returnList;

    }

    public List<Vehicle> getLines(){

        String Name=null;
        String StopName=null;
        String StreetName=null;
        int Time=0;






        List<Vehicle> returnList = new ArrayList<>();
        ArrayList <Stop> TmpStopList=new ArrayList<>();
        ArrayList <Coordinate> CoordinateList=new ArrayList<>();
        ArrayList <Calendar> TmpTimeList= new ArrayList<>();

        Coordinate tmpCoor;
        Calendar tmpTime;

        int x=0;
        int y=0;
        Coordinate tmp=null;

        for (int i=0; i<this.LinesList.getLength();i++){
            //iterates through line elements
            //this is basically vehicle
            Node street = this.LinesList.item(i);
            if (street.getNodeType()==Node.ELEMENT_NODE){
                Element streetEl= (Element) street;
                NodeList atts=streetEl.getChildNodes();
                for (int j=0; j<atts.getLength();j++){
                    //this iterates through child elements like stops etc

                    Node streetAtt=  atts.item(j);
                    if (street.getNodeType()==Node.ELEMENT_NODE) {

                        switch(streetAtt.getNodeName()){
                            case "id":
                                Name=streetAtt.getTextContent();

                                break;
                            case "Stop":
                                StopName=streetAtt.getTextContent();

                                //line.addRecord(new Street(streetName), Time);

                                tmpCoor= new Coordinate(
                                        Integer.parseInt(streetAtt.getAttributes().getNamedItem("x").getNodeValue()),
                                        Integer.parseInt(streetAtt.getAttributes().getNamedItem("y").getNodeValue()));
                                tmpTime = Calendar.getInstance();
                                tmpTime.set(2020, 5, 8,
                                        Integer.parseInt(streetAtt.getAttributes().getNamedItem("hour").getNodeValue()),
                                        Integer.parseInt(streetAtt.getAttributes().getNamedItem("minutes").getNodeValue()),
                                        0);
                                TmpTimeList.add(tmpTime);
                                TmpStopList.add(new Stop(StopName, tmpCoor));

                                break;
                            case "Street":


                                break;
                            case "coor":

                                x=Integer.parseInt(streetAtt.getAttributes().getNamedItem("x").getNodeValue());
                                y=Integer.parseInt(streetAtt.getAttributes().getNamedItem("y").getNodeValue());

                                CoordinateList.add(new Coordinate(x,y));
                                break;


                        }

                    }

                }



                returnList.add(new Vehicle(CoordinateList.get(0), 1, new Path(CoordinateList), TmpStopList, TmpTimeList));
                CoordinateList=new ArrayList<>();
                TmpStopList=new ArrayList<>();
                TmpTimeList=new ArrayList<>();
                
                

            }

        }


        return returnList;

    }

}
