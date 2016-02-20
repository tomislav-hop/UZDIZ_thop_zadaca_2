/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2.memento;

/**
 *
 * @author Tomislav
 */
import java.util.ArrayList;
import java.util.List;

public class CareTaker {

    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }

    public int vratiVelicinu() {
        return mementoList.size();
    }
}
