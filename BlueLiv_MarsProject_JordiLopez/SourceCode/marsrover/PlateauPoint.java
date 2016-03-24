/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public abstract class PlateauPoint {
    protected boolean positionOccupied;
    public PlateauPoint(boolean positionOccupied){
        this.positionOccupied = positionOccupied;
               
    }
    
    public void setPositionOccupied(boolean occupied){
        this.positionOccupied = occupied;
    }
}
