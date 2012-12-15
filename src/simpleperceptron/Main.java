/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleperceptron;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zapo
 */

public class Main {

    public final static double EPSILON=0.01;
    public final static double TETA = 0.01;
    

    
    public static Vector<Vector<Double>> formes = getData("benchmark.txt");
    public static Vector<Double> expectedOutputVector = getExpectedOutputVector("benchmark.txt");

    public static void main(String[] args) throws IOException {


        

        ILayer layerI = new ILayer(9, "Couche d\'entrée I");
        Layer layerJ = new Layer(10, "Couche cachée J");
        KLayer layerK = new KLayer(2, "Couche de sortie K");

        layerI.connectTo(layerJ);
        layerJ.connectTo(layerK);


        double[] stick = {0, 1};
        initKLayerOutput(layerK, stick);


        double d = 0;
        int period = 0;
        double periodError = 0;

//        FileOutputStream fous = new FileOutputStream("output.txt");
//        PrintWriter pw = new PrintWriter(fous);



        System.out.println("Entree en phase d'apprentissage");

        do {
            periodError = 0;

            period++;

            for (int k = 0; k < formes.size()-50; k++) {

                d = expectedOutputVector.elementAt(k);
                
                layerI.getPattern(k);


                layerJ.computeLayerActivation();
                layerJ.computeLayerOutput();
                layerK.computeLayerActivation();
                layerK.computeLayerOutput();

                layerK.computeLayerError(d);
                layerJ.computeLayerError();



                //retour d'erreur

                layerK.computeLayerWeightMatrix();
                layerJ.computeLayerWeightMatrix();


                periodError += layerK.getGlobalError(expectedOutputVector.elementAt(k));


            }




            

        } while (layerK.getGlobalError(d) > Main.TETA);


        //Phase de test :
        

            int justes=0;
            for(int i=600;i<683;i++){
            
            
            layerI.getPattern(i-1);

            layerJ.computeLayerActivation();
            layerJ.computeLayerOutput();
            layerK.computeLayerActivation();
            layerK.computeLayerOutput();

                if(layerK.getResult(i)==expectedOutputVector.elementAt(i)){
                        justes++;
                }

            }

            System.out.print(justes/83);


    }

    

    public static Vector<Vector<Double>> getData(String path) {

        Vector<Vector<Double>> vect = new Vector<Vector<Double>>();

        try {

            FileInputStream is = new FileInputStream(path);
            Scanner scan = new Scanner(is);

            int i = 0;

            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] gni = ligne.split("\t");

                Vector<Double> vectemp = new Vector<Double>();

                for (i = 0; i < gni.length - 1; i++) {

                    vectemp.add(Double.parseDouble(gni[i]));
                }

                vect.add(vectemp);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }


        return vect;
    }

    public static Vector<Double> getExpectedOutputVector(String path) {


        Vector<Double> vec = new Vector<Double>();
        try {

            FileInputStream is = new FileInputStream(path);
            Scanner scan = new Scanner(is);

            int i = 0;

            while (scan.hasNextLine()) {
                String ligne = scan.nextLine();
                String[] gni = ligne.split("\t");

                vec.add(Double.parseDouble(gni[gni.length - 1]));




            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }


        return vec;
    }

    public static void initKLayerOutput(Layer layerK, double[] vecteur) {
        for (int i = 0; i < layerK.getNeurons().size(); i++) {
            Neuron current = layerK.getNeurons().elementAt(i);
            current.setStick(vecteur[i]);
        }
    }
}