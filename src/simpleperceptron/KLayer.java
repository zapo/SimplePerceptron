/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simpleperceptron;

/**
 *
 * @author zapo
 */
public class KLayer extends Layer{

    public KLayer(int size, String label) {
        super(size, label);
    }


    public double getGlobalError(double expected) {
        double error = 0;

        for (Neuron n : getNeurons()) {
            if (expected == n.getStick()) {
                error += Math.pow(1 - n.getValue(),2);
            } else {
                error += Math.pow(0 - n.getValue(),2);
            }
        }
        error = 0.5 * error;

        return error;
    }

    public void computeLayerError(double expectedValue) {

        for (Neuron n : getNeurons()) {
            if (expectedValue == n.getStick()) {
                n.setError((1 - n.getValue()) * n.getValue() * (1 - n.getValue()));

            } else {

                n.setError((0 - n.getValue()) * n.getValue() * (1 - n.getValue()));
            }
        }


    }
    public double getResult(int i){
        double res=0;
        for(Neuron n : getNeurons()){
                    if(Math.round(n.getValue())==1){
                        if(n.getStick()==1.){
                            //System.out.print("Le patient a le cancer");
                            res=1;
                        }else{
                            //System.out.print("Le patient n'a pas le cancer");
                            res=0;
                        }

                        //System.out.println(" pour la ligne "+(i));
                    }
        }
        return res;
    }


    


}
