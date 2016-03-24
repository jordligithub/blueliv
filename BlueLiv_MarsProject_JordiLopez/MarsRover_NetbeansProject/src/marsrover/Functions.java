/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package marsrover;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.PropertyResourceBundle;

/**
 *
 * @author JordiL
 */
public class Functions {

    public static boolean showClassInfo = true;
    public static boolean showMessages = true;
    public static boolean clearOutputFile = false;
    protected static HashMap errors = new HashMap();
    public static String outLogFile = null;
    public static String inputFile = null;
    public static String outFile = null;
    protected static String MARS_LOG_PATH = "logFile";
    protected static String MARS_Show_Class_Info = "showClassInfo";
    protected static String MARS_Show_Logs = "showLogs";
    protected static String MARS_OUTFILE_PATH = "outFile";
    protected static String MARS_INPUTFILE_PATH = "inputFile";
    protected static String MARS_ClearOutputFile = "clearOutputFile";
    protected static String MARS_TestResultExpected = "";

    public static void init(String propFile) {
        errors.put(1, "error cargando el fichero de propiedades");
        errors.put(2, "PropsFile not Found");
        String str;

        //Functions.getBundleProperties(propFile);
        PropertyResourceBundle bundle = getBundleProperties(propFile);

        try {

            Functions.setLogFile(bundle.getString(Functions.MARS_LOG_PATH));
        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_LOG_PATH + " not defined in proFile " + propFile);

        }

        try {

            str = bundle.getString(Functions.MARS_ClearOutputFile).toLowerCase();
            
            if (str.contains("true")) {
                clearOutputFile = true;
            }else{
                clearOutputFile = false;
            }
            
        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_ClearOutputFile + " not defined in proFile " + propFile);

        }

        try {
            Functions.setOutFile(bundle.getString(Functions.MARS_OUTFILE_PATH));
        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_OUTFILE_PATH + " not defined in proFile " + propFile);

        }

        try {

            Functions.setInputFile(bundle.getString(Functions.MARS_INPUTFILE_PATH).toLowerCase());
        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_INPUTFILE_PATH + " not defined in proFile " + propFile);
        }

        try {
            str = bundle.getString(Functions.MARS_Show_Class_Info).toLowerCase();

            if (str.contains("true")) {
                showClassInfo = true;
            }else
                showClassInfo = false;
            
        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_Show_Class_Info + " not defined in proFile " + propFile);

        }

        try {
            str = bundle.getString(Functions.MARS_Show_Logs).toLowerCase();
            if (str.contains("true")) {
                showMessages = true;
            }else
                showMessages = false;

        } catch (Exception e) {
            Functions.mostrarMensajePorConsola(Functions.showMessages, Functions.showClassInfo, "", "property " + Functions.MARS_Show_Logs + " not defined in proFile " + propFile);

        }
    }

    public static void setLogFile(String logFile) {
        Functions.outLogFile = logFile;
    }
    public static void setTestResultExpected(String expectedTest) {
        Functions.MARS_TestResultExpected = expectedTest;
    }
    public static String getTestResultExpected() {
        return Functions.MARS_TestResultExpected;
    }
    public static void setOutFile(String outFile) {
        Functions.outFile = outFile;
    }

    public static void setInputFile(String inputFile) {
        Functions.inputFile = inputFile;
    }

    protected static PropertyResourceBundle getBundleProperties(String propertyFile) {

        FileInputStream fis = null;
        PropertyResourceBundle bundle = null;
        try {
            if (propertyFile.equals("") || propertyFile == null) {
                mostrarMensajePorConsola(true, Functions.showClassInfo, "INFO", (String) errors.get(1));
            } else {
                fis = new FileInputStream(propertyFile);
                mostrarMensajePorConsola(true, Functions.showClassInfo, "INFO", "fichero de propiedades:" + propertyFile);
                bundle = new PropertyResourceBundle(fis);
            }


        } catch (FileNotFoundException ex) {
            mostrarMensajePorConsola(true, Functions.showClassInfo, "INFO", (String) errors.get(2));

        } catch (IOException ex) {

            mostrarMensajePorConsola(true, Functions.showClassInfo, "ERROR", (String) errors.get(2));


        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error with props file");

            }
        }

        return bundle;
    }

    public static void mostrarMensajePorConsola(boolean showMessage, boolean showClassInfo, String logLevel, String message) {
        /* !! Pendiente actualizar documentacion !!
         *  metodo que muestra por la consola estandar o la que se definiese en este metodo los mensajes pasados por parametro cuando se permita tal acción
         *  Input : 
         *          showMessage (boolean) : 1 -> se muestran los mensajes
         *                                  0 -> no se muestran los mensajes
         *          message (String) : mensaje que será mostrado por la consola de salida
         */
        String nameClass = Thread.currentThread().getStackTrace()[2].getClassName();
        String nameFunction = Thread.currentThread().getStackTrace()[2].getMethodName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String time = dateFormat.format(date);

        if (showMessage) {
            String retornoLinea = "\r\n";
            String messageOut;

            if (showClassInfo) {
                if(logLevel.isEmpty())
                    messageOut = "[" + time + "] " + "[" + nameClass + "/" + nameFunction + ":" + lineNumber + "] " + message + retornoLinea;
                   else
                messageOut = "[" + time + "] [" + logLevel + "] [" + nameClass + "/" + nameFunction + ":" + lineNumber + "] " + message + retornoLinea;
            } else {
                if(logLevel.isEmpty())
                    messageOut = "[" + time + "] "  + message + retornoLinea;
                    else
                messageOut = "[" + time + "] [" + logLevel + "] " + message + retornoLinea;

            }

            if (outLogFile != null) {
                PrintStream p;

                try {
                    
                    File yourFile = new File(outLogFile);
                    if (!yourFile.exists()) {
                        yourFile.createNewFile();
                    }
                    FileOutputStream oFile = new FileOutputStream(yourFile, true);

                    p = new PrintStream(new BufferedOutputStream(oFile));

                    // p = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(outLogFile), true)));
                    p.append(messageOut);
                    p.flush();
                } catch (FileNotFoundException ex) {
                    
                    System.out.println("Fichero:" + outLogFile + " no encontrado");
                    
                } catch (IOException ex) {
                    System.out.println("Fichero LOG-error:" + outLogFile);
                    
                }

            } else {
                System.out.print(messageOut);
            }
        }
    }

    public static void writeOutput(boolean clearFile, String message) {
        /* 
         * method that writes outputFile
         * clearFile: True  : write from begin of the file
         *            False : adds text to the end of the file
         */



        String retornoLinea = "\r\n";
        String messageOut;
        messageOut = message + retornoLinea;


        if (outFile != null) {
            PrintStream p = null;
            File yourFile = null;
            try {
                
                yourFile = new File(outFile);
                if (!yourFile.exists()) {
                    yourFile.createNewFile();
                }

                if (clearFile) {

                    FileOutputStream oFile = new FileOutputStream(yourFile, false);
                    p = new PrintStream(new BufferedOutputStream(oFile));
                    p.println(message);
                    p.flush();

                } else {
                    FileOutputStream oFile = new FileOutputStream(yourFile, true);

                    p = new PrintStream(new BufferedOutputStream(oFile));

                    p.println(message);
                    p.flush();
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Fichero:" + outLogFile + " no encontrado");
                
            } catch (IOException ex) {
                System.out.println("Fichero LOG-error:" + outLogFile);
                
            } finally {
                p.close();

            }

        } else {
            System.out.print(message);
        }
    }
}
