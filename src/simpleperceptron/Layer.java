/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleperceptron;

import java.util.Vector;

/**
 *
 * @author zapo
 */
public class Layer {

    private Vector<Neuron> neurons;
    private int size;
    private String label;
    

    public Layer(int size, String label) {
        neurons = new Vector<Neuron>();
        this.size = size;
        this.label = label;


        for (int i = 0; i < size; i++) {

            neurons.add(new Neuron("Neurone " + i + " of Layer \"" + label + "\" "));
        }

    }

    

    public void connectTo(Layer layer) {
        for (Neuron myNeuron : neurons) {
            for (Neuron targetNeuron : layer.getNeurons()) {
                Link l = new Link(myNeuron, targetNeuron);
                myNeuron.getOutgoingLinks().add(l);
                targetNeuron.getIncomingLinks().add(l);


            }
        }
        
    }

    public Vector<Neuron> getNeurons() {
        return neurons;
    }

    public void print() {
        for (Neuron n : getNeurons()) {
            for (Link l : n.getIncomingLinks()) {
                System.out.println(n.getName() + "valeur " + n.getValue() + " connecté à " + l.getFrom().getName() + " poid : " + l.getWeight() + " erreur :" + n.getError());

            }


            for (Link l : n.getOutgoingLinks()) {
                System.out.println(n.getName() + "valeur " + n.getValue() + " connecté à " + l.getTo().getName() + " poid : " + l.getWeight() + " erreur :" + n.getError());

            }

        }
        System.out.println();
    }

    

    public Layer computeLayerActivation() {

        for (Neuron n : getNeurons()) {
            double temp = 0;
            for (Link l : n.getIncomingLinks()) {
                temp += l.getFrom().getValue() * l.getWeight();
            }
            n.setValue(temp);
        }
        return this;
    }

    public Layer computeLayerOutput() {
        for (Neuron n : getNeurons()) {

            n.setValue(1. / (1. + Math.exp(-(n.getValue()))));



        }
        return this;
    }



    

    public void computeLayerError() {
        for (Neuron n : getNeurons()) {
            double err = 0;
            for (Link l : n.getOutgoingLinks()) {
                Neuron n1 = l.getTo();
                err += n1.getError() * l.getWeight();
            }
            err *= (n.getValue() * (1 - n.getValue()));
            n.setError(err);
        }
    }

    public void computeLayerWeightMatrix() {
        for (Neuron n : getNeurons()) {
            for (Link l : n.getIncomingLinks()) {
                l.setWeight(l.getWeight() + (n.getError() * Main.EPSILON * l.getFrom().getValue()));
            }
        }
    }
}
    

        


    