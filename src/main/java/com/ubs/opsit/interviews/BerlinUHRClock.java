/**
 * 
 */
package com.ubs.opsit.interviews;

import java.lang.Exception.*;

/**
 * @author Ritesh.Keswani
 *
 */
public class BerlinUHRClock implements TimeConverter{
public String convertTime(String time){
        
        int[] splittedTimeArray=new int[3];
        
        StringBuffer berlineTimeRepresentation = new StringBuffer();
        try {
			splittedTimeArray=validateAndSplit(time);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
                
        berlineTimeRepresentation.append(getSecondsLampColor(splittedTimeArray[2]));
        berlineTimeRepresentation.append(getHoursTopLampColor(splittedTimeArray[0]));
        berlineTimeRepresentation.append(getHoursBottomLampColor(splittedTimeArray[0]));
        berlineTimeRepresentation.append(getMinutesTopLampColor(splittedTimeArray[1]));
        berlineTimeRepresentation.append(getMinutesBottomLampColor(splittedTimeArray[1]).replace("\r\n", ""));
        //System.out.print(berlineTimeRepresentation);
        
        return berlineTimeRepresentation.toString();
    }
private int[] validateAndSplit(String time) throws NullPointerException, NumberFormatException{
	
	int counter=0;
	int[] splittedTime=new int[3];
	if (time == null || time.isEmpty()) {
    	throw new NullPointerException("Time should not be null");
    }

    /*loop to split String time into int array*/
    for(String temp:time.split(":")) {    	
    	splittedTime[counter]= Integer.parseInt(temp);
        counter++;
    }
    
    if (splittedTime.length != 3) {
    	throw new NumberFormatException("Invalid Time Format");
    }
    
    if(splittedTime[0] < 0 || splittedTime[0] > 24 || splittedTime[1] < 0 || splittedTime[1] > 60 || 
    		splittedTime[2] <0 || splittedTime[2] > 60 || (splittedTime[0]==24 && (splittedTime[1] >0 || splittedTime[2]>0)) ) {
    	throw new NumberFormatException("Invalid Time Format");
    }
    
    return splittedTime;

}
    private String getHoursTopLampColor(int hourValue){
        return colorCombinationValue(4,hourValue/5,"R");
    }
    private String getHoursBottomLampColor(int hourValue){
        return colorCombinationValue(4,hourValue%5,"R");
    }
    private String getMinutesTopLampColor(int minuteValue){
        return colorCombinationValue(11,minuteValue/5,"Y").replace("YYY","YYR");
    }
    private String getMinutesBottomLampColor(int minuteValue){
        return colorCombinationValue(4,minuteValue%5,"Y");
    }
    /*This functions returns the color Combination value based on the number of lamps, 
    count of the ON lamps and the color passed to it.    */
    private String colorCombinationValue(int numberOfLamps,int onCount,String colorSign) {
        StringBuffer colorValue=new StringBuffer("");
        for(int i=0;i<onCount;i++) {
            colorValue.append(colorSign);
        }
        for(int i=0;i<numberOfLamps-onCount;i++) {
            colorValue.append("O");
        }
        colorValue.append("\r\n");
        return colorValue.toString();
        
    }
    String getSecondsLampColor(int secValue) {
        return (secValue%2 ==0 ? "Y\r\n" : "O\r\n");
    }
     

}
