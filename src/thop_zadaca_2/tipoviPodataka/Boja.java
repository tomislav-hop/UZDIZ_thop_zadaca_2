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
public class Boja {

    private String boja;
    private float povrsina;

    public Boja(String boja) {
        this.boja = boja;
        this.povrsina = 0;
    }

    public void dodajPovrsinu(float povrsinaZaDodati) {
        this.povrsina+=povrsinaZaDodati;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public float getPovrsina() {
        return povrsina;
    }

    public void setPovrsina(float povrsina) {
        this.povrsina = povrsina;
    }


}
