/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package simpleperceptron;

/**
 *
 * @author zapo
 */
public class ILayer extends Layer {

    public ILayer(int size, String label) {
        super(size, label);
    }

    public void getPattern(int no) {
        int i = 0;
        for (Neuron n : getNeurons()) {
            n.setValue(Main.formes.get(no).elementAt(i++));
        }
    }

}
