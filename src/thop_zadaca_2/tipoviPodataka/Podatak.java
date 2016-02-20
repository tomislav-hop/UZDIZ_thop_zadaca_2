/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2.tipoviPodataka;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomislav
 */
public class Podatak{

    private int tipZapisa;
    private int sifra;
    private int roditelj;
    private String koordinate;
    private String boja;
    private List<Podatak> podpodaci;
    private boolean status;
    private boolean prestup;

    public Podatak(int tipZapisa, int sifra, int roditelj, String koordinate, String boja, boolean status, boolean prestup) {
        this.tipZapisa = tipZapisa;
        this.sifra = sifra;
        this.roditelj = roditelj;
        this.koordinate = koordinate;
        this.boja = boja;
        this.status = status;
        this.podpodaci = new ArrayList<>();
        this.prestup = prestup;
    }

    public void add(Podatak p) {
        podpodaci.add(p);
    }

    public void remove(Podatak p) {
        podpodaci.remove(p);
    }

    public List<Podatak> getPodpodaci() {
        return podpodaci;
    }

    public String toString() {
        //return ("Podatak: [ Tip zapisa: " + tipZapisa + ", Sifra: " + sifra + ", Roditelj: " + roditelj +", Koordinate: " + koordinate + ", Boja: " + boja + " ]");
        return ("Tip zapisa: " + tipZapisa + "\tSifra: " + sifra + "\tRoditelj: " + roditelj + "\tBoja: " + boja + "\tStatus: " + status + "\tPrestup granice: " + prestup + "\tKoordinate: " + koordinate);
    }

    public int getTipZapisa() {
        return tipZapisa;
    }

    public int getSifra() {
        return sifra;
    }

    public String getKoordinate() {
        return koordinate;
    }

    public String getBoja() {
        return boja;
    }

    public int getRoditelj() {
        return roditelj;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isPrestup() {
        return prestup;
    }

    public void setPrestup(boolean prestup) {
        this.prestup = prestup;
    }

}
