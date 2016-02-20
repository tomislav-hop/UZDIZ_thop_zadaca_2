/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2.tipoviPodataka;

/**
 *
 * @author Tomislav
 */
public class PocetniPodatak {

    private int tipZapisa;
    private int sifra;
    private int roditelj;
    private String koordinate;
    private String boja;
    private boolean prestupGranice;

    public PocetniPodatak() {
    }

    public PocetniPodatak(int tipZapisa, int sifra, int roditelj, String koordinate, String boja, boolean prestupGranice) {
        this.tipZapisa = tipZapisa;
        this.sifra = sifra;
        this.roditelj = roditelj;
        this.koordinate = koordinate;
        this.boja = boja;
        this.prestupGranice = prestupGranice;
    }
    
    public String toString() {
        //return ("Podatak: [ Tip zapisa: " + tipZapisa + ", Sifra: " + sifra + ", Roditelj: " + roditelj +", Koordinate: " + koordinate + ", Boja: " + boja + " ]");
        return ("Tip zapisa: " + tipZapisa + "\tSifra: " + sifra + "\tRoditelj: " + roditelj + "\tBoja: " + boja +"\tPrestup granice: " + prestupGranice + "\tKoordinate: " + koordinate);
    }

    public int getTipZapisa() {
        return tipZapisa;
    }

    public void setTipZapisa(int tipZapisa) {
        this.tipZapisa = tipZapisa;
    }

    public int getSifra() {
        return sifra;
    }

    public void setSifra(int sifra) {
        this.sifra = sifra;
    }

    public int getRoditelj() {
        return roditelj;
    }

    public void setRoditelj(int roditelj) {
        this.roditelj = roditelj;
    }

    public String getKoordinate() {
        return koordinate;
    }

    public void setKoordinate(String koordinate) {
        this.koordinate = koordinate;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public boolean isPrestupGranice() {
        return prestupGranice;
    }

    public void setPrestupGranice(boolean prestupGranice) {
        this.prestupGranice = prestupGranice;
    }

}
