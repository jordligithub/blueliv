/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

/**
 *
 * @author JordiL
 */
public class SquarePlateauPoint extends PlateauPoint {
    protected int positionX;
    protected int positionY;
    public SquarePlateauPoint (int posX, int posY, boolean occupied){
        super(occupied);
        this.positionX = posX;
        this.positionY = posY;
    }
}
