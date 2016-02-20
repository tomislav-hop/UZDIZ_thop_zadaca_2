/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import thop_zadaca_2.tipoviPodataka.PocetniPodatak;
import thop_zadaca_2.tipoviPodataka.Podatak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomislav
 */
public class UpravljanjePodacima {

    private List<PocetniPodatak> pocetni_podaci = new ArrayList<>();
    private List<Podatak> filtriraniPodaci = new ArrayList<>();
    private List<Podatak> listaRoditelja = new ArrayList<>();
    private List<Podatak> djeca = new ArrayList<>();

    public UpravljanjePodacima(List<PocetniPodatak> pocetniPodaci) {
        this.pocetni_podaci = pocetniPodaci;
    }

    public Podatak SortiranjeElemenata(Podatak podatak) {
        Podatak korijen = podatak;
        for (PocetniPodatak poc : pocetni_podaci) {
            Podatak ubaci = new Podatak(poc.getTipZapisa(), poc.getSifra(), poc.getRoditelj(), poc.getKoordinate(), poc.getBoja(), true, poc.isPrestupGranice());
            if (poc.getRoditelj() == korijen.getSifra() && poc.getRoditelj() != poc.getSifra()) {
                SortiranjeElemenata(ubaci);
                korijen.add(ubaci);
            }
        }
        return korijen;
    }

    public void IspisElemenataComposita(Podatak korijen) {
        System.out.println("\t" + korijen);
        for (Podatak p : korijen.getPodpodaci()) {
            if (p.isStatus()) {
                IspisElemenataComposita(p);
            }
        }
    }

    public void IspisSvihElemenataComposita(Podatak korijen) {
        System.out.println("\t" + korijen);
        for (Podatak p : korijen.getPodpodaci()) {
            IspisElemenataComposita(p);
        }
    }

    public void promjenaStatusa(Podatak korijen, int sifra, boolean status) {

        if (korijen.getSifra() == sifra) {
            korijen.setStatus(status);
            return;
        }

        for (Podatak p : korijen.getPodpodaci()) {
            promjenaStatusa(p, sifra, status);
            if (p.getSifra() == sifra) {
                System.out.println("Promjena statusa od: " + p);
                p.setStatus(status);
            }
        }
    }

    public void promjenaStatusaRoditeljuIDjeci(Podatak korijen, int sifra, boolean status) {
        //TODO
        if (korijen.getSifra() == sifra) {
            korijen.setStatus(status);
            promjenaStatusaDjece(korijen, status);
            return;
        }

        for (Podatak p : korijen.getPodpodaci()) {
            promjenaStatusa(p, sifra, status);
            if (p.getSifra() == sifra) {
                //System.out.println("Promjena statusa od: " + p);
                promjenaStatusaDjece(p, status);
                p.setStatus(status);
                return;
            }
        }
    }

    public void promjenaStatusaDjece(Podatak korijen, boolean status) {
        for (Podatak p : korijen.getPodpodaci()) {
            promjenaStatusaDjece(p, status);
            p.setStatus(status);
        }
    }
    
    public List<Podatak> vratiListuRoditelja(Podatak korijen)
    {
        if (korijen.getPodpodaci().size() > 0) {
            listaRoditelja.add(korijen);
        }
        for (Podatak p : korijen.getPodpodaci()) {
            vratiListuRoditelja(p);
        }
        return listaRoditelja;
    }
    
    public List<Podatak> vratiDjecu(Podatak korijen, int sifraRoditelja)
    {
        //System.out.println("\t" + korijen);
        if(korijen.getSifra() == sifraRoditelja)
        {
            djeca = korijen.getPodpodaci();
        }
        for (Podatak p : korijen.getPodpodaci()) {
            vratiDjecu(p, sifraRoditelja);
        }
        return djeca;
    }

    public List<Podatak> vratiElemente(Podatak korijen, boolean status, boolean prestup) {
        //System.out.println("\t" + korijen);
        if (korijen.isStatus() == status && korijen.isPrestup() == prestup) {
            filtriraniPodaci.add(korijen);
        }
        for (Podatak p : korijen.getPodpodaci()) {
            vratiElemente(p, status, prestup);
        }
        return filtriraniPodaci;
    }

    public List<Podatak> getFiltriraniPodaci() {
        return filtriraniPodaci;
    }

    public void setFiltriraniPodaci(List<Podatak> filtriraniPodaci) {
        this.filtriraniPodaci = filtriraniPodaci;
    }

    public List<Podatak> getListaRoditelja() {
        return listaRoditelja;
    }

    public void setListaRoditelja(List<Podatak> listaRoditelja) {
        this.listaRoditelja = listaRoditelja;
    }

}
