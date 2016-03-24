/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.util.HashMap;

/**
 *
 * @author JordiL
 */
public abstract class Plateau {
 
    protected HashMap<Integer,Rover> rovers;
    protected HashMap<Integer,HashMap> plateau;
    
    abstract public boolean moveAllowed(int x,int y,String header);
    abstract public void moveRover(int RoverID);
    
    public String getRoversPositions(){
        String str="";
        for(int i=0; i<rovers.size(); i++){
            if(i>0)
                str+=" ";
            str += rovers.get(i).getPositionSring();
        }
        return str;
    }


}
