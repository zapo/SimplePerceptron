/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleperceptron;

/**
 *
 * @author zapo
 */
public class Link {

    private Neuron from;
    private Neuron to;
    private double weight;

    public Link(Neuron from, Neuron to) {
        this.from = from;
        this.to = to;
        weight = Math.random();

    }

    public Neuron getFrom() {
        return from;
    }

    public void setFrom(Neuron from) {
        this.from = from;
    }

    public Neuron getTo() {
        return to;
    }

    public void setTo(Neuron to) {
        this.to = to;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
