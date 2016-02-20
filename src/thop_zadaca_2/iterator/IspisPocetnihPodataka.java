/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2.iterator;

import thop_zadaca_2.tipoviPodataka.PocetniPodatak;
import java.util.ArrayList;
import java.util.List;
import thop_zadaca_2.tipoviPodataka.Podatak;

/**
 *
 * @author Tomislav
 */
public class IspisPocetnihPodataka implements Container {

    public List<PocetniPodatak> pocetni_podaci = new ArrayList<>();

    public IspisPocetnihPodataka(List<PocetniPodatak> pocetniPodaci) {
        this.pocetni_podaci = pocetniPodaci;
    }

    public void ispisPodataka() {
        for (Iterator iter = new IspisPocetnihPodataka.PodatakIterator(); iter.hasNext();) {
            PocetniPodatak poc = (PocetniPodatak) iter.next();
            System.out.println("\t"+poc);
        }
    }

    public Podatak vratiKorijen() {
        Podatak korijen = null;
        for (Iterator iter = new IspisPocetnihPodataka.PodatakIterator(); iter.hasNext();) {
            PocetniPodatak poc = (PocetniPodatak) iter.next();
            korijen = new Podatak(poc.getTipZapisa(), poc.getSifra(), poc.getRoditelj(), poc.getKoordinate(), poc.getBoja(), true, poc.isPrestupGranice());

            System.out.println(poc);
            return korijen;
        }
        return null;
    }

    @Override
    public Iterator getIterator() {

        return new PodatakIterator();
    }

    private class PodatakIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (index < pocetni_podaci.size()) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return pocetni_podaci.get(index++);
            }
            return null;
        }
    }
}
