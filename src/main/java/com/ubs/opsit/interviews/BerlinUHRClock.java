/**
 * 
 */
package com.ubs.opsit.interviews;

/**
 * @author Ritesh.Keswani
 *
 */
public class BerlinUHRClock implements TimeConverter {
public String convertTime(String time) {
        
        int[] splittedTimeArray=new int[3];
        int counter=0;
        StringBuffer berlineTimeRepresentation = new StringBuffer();
        
        /*loop to split String time into int array*/
        for(String temp:time.split(":")) {
            splittedTimeArray[counter]=Integer.parseInt(temp);
            counter++;
        }
        
        berlineTimeRepresentation.append(getSecondsLampColor(splittedTimeArray[2]));
        berlineTimeRepresentation.append(getHoursTopLampColor(splittedTimeArray[0]));
        berlineTimeRepresentation.append(getHoursBottomLampColor(splittedTimeArray[0]));
        berlineTimeRepresentation.append(getMinutesTopLampColor(splittedTimeArray[1]));
        berlineTimeRepresentation.append(getMinutesBottomLampColor(splittedTimeArray[1]));
        System.out.println(berlineTimeRepresentation);
        /*for(String timeVar : berlineTimeRepresentation) {
            System.out.println(timeVar);
        }*/
        return berlineTimeRepresentation.toString();
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
        colorValue.append("\n");
        return colorValue.toString();
        
    }
    String getSecondsLampColor(int secValue) {
        return (secValue%2 ==0 ? "Y\n" : "O\n");
    }
     /*public static void main(String []args){
         BerlinUHRClock b=new BerlinUHRClock();
        b.convertTime("11:56:05");
     }*/

}
