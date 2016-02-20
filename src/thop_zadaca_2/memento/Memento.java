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
public class Memento {
   private List<PocetniPodatak> state;

   public Memento(List<PocetniPodatak> state){
      this.state = state;
   }

   public List<PocetniPodatak> getState(){
      return state;
   }	
}
