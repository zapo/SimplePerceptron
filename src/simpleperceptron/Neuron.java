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
public class Neuron {

    private double error;
    private String name;
    private double value;
    private double stick;
    private Vector<Link> incomingLinks;
    private Vector<Link> outgoingLinks;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Vector<Link> getIncomingLinks() {
        return incomingLinks;
    }

    public Vector<Link> getOutgoingLinks() {
        return outgoingLinks;
    }

    public String getName() {
        return name;
    }

    public Neuron(String name) {
        this.name = name;
        this.incomingLinks = new Vector<Link>();
        this.outgoingLinks = new Vector<Link>();
        this.value = 0;

    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getStick() {
        return stick;
    }

    public void setStick(double stick) {
        this.stick = stick;
    }
}
