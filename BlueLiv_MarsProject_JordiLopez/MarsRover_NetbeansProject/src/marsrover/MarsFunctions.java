/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author JordiL
 */
public class MarsFunctions {

    protected static int plateauMaxX = -1;
    protected static int plateauMaxY = -1;
    protected static ArrayList<Rover> roverList;

    public static void readInputFile(String inputFile) {
        File file = new File(inputFile);
        boolean plateauSizeRead = false;
        boolean roverPosRead = false;
        boolean roverMoveRead = false;
        List lines;
        try {
            roverList = new ArrayList<Rover>();
            lines = FileUtils.readLines(file);
            int id = 0;
            RoverSQ rover2 = null;
            for (int i = 0; i < lines.size(); i++) {
                String line = (String) lines.get(i);

                if (!(line.isEmpty()) && line != "") {
                    
                    if (line.toLowerCase().startsWith("TestResultExected:".toLowerCase())) {
                        Functions.setTestResultExpected(line.replace("TestResultExected:", ""));
                    } else {
                        if (!plateauSizeRead) {
                            line = line.trim();
                            plateauMaxX = Integer.parseInt(line.split(" ")[0]);
                            plateauMaxY = Integer.parseInt(line.split(" ")[1]);
                            plateauSizeRead = true;
                            roverPosRead = false;
                            roverMoveRead = false;
                        } else if (!roverPosRead && !roverMoveRead) {
                            line = line.trim();

                            int posx = Integer.parseInt(line.split(" ")[0]);
                            int posy = Integer.parseInt(line.split(" ")[1]);
                            String header = line.split(" ")[2];

                            rover2 = new RoverSQ(id, header, "", posx, posy);

                            String msg = "created rover " + id + ":" + posx + " " + posy + " " + header;
                            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", msg);

                            id++;
                            roverPosRead = true;
                        } else if (roverPosRead && !roverMoveRead) {
                            line = line.replaceAll(" ", "");
                            rover2.setMovements(line);
                            roverList.add(rover2);

                            String msg = "move rover " + id + ":" + line;
                            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", msg);


                            roverPosRead = false;
                            roverMoveRead = false;
                        }
                    }

                }

            }
        } catch (IOException ex) {
            String msg = "error reading inputFile " + inputFile;
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", msg);

        }
    }

    public static ArrayList<Rover> getRoverList() {
        return roverList;
    }

    public static Rover getRover(int i) {
        return roverList.get(i);
    }

    public static int getRoverSize() {
        return roverList.size();
    }

    public static int getPlateauMaxX() {
        return plateauMaxX;
    }

    public static int getPlateauMaxY() {
        return plateauMaxY;
    }
}
