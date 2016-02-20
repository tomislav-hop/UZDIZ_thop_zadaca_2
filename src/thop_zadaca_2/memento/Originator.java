/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2.memento;

import java.util.List;
import thop_zadaca_2.tipoviPodataka.PocetniPodatak;
import thop_zadaca_2.tipoviPodataka.Podatak;

/**
 *
 * @author Tomislav
 */
public class Originator {
   private List<PocetniPodatak> state;

   public void setState(List<PocetniPodatak> state){
      this.state = state;
   }

   public List<PocetniPodatak> getState(){
      return state;
   }

   public Memento saveStateToMemento(){
      return new Memento(state);
   }

   public void getStateFromMemento(Memento Memento){
      state = Memento.getState();
   }
}
