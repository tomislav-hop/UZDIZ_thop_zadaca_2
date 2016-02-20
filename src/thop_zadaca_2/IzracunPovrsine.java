/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import thop_zadaca_2.tipoviPodataka.Podatak;
import thop_zadaca_2.tipoviPodataka.Boja;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomislav
 */
public class IzracunPovrsine {

    public IzracunPovrsine() {
    }

    public float izracunajPovrsinuMnogokuta(int[] x, int[] y, int brojTocaka) {
        float povrsina = 0;
        int j = brojTocaka - 1;

        for (int i = 0; i < brojTocaka; i++) {
            povrsina = povrsina + (x[j] + x[i]) * (y[j] - y[i]);
            j = i;
        }

        povrsina = Math.abs(povrsina / 2);
        return povrsina;
    }

    public float izracunaPovrsinuKruga(float radius) {
        float povrsina = 0;
        float PI = (float) 3.141592653589793;
        povrsina = PI * (radius * radius);
        return povrsina;
    }

    public List<Boja> vratiListuBoja(List<Podatak> podaci) {
        List<Boja> listaBoja = new ArrayList<>();
        for (Podatak p : podaci) {
            if (!p.isPrestup() && p.isStatus() ) {
                if (!bojaPostoji(listaBoja, p.getBoja())) {
                    Boja novaBoja = new Boja(p.getBoja());

                    float povrsinaZaDodati = 0;

                    povrsinaZaDodati = odabirIzracunaPovrsine(p.getKoordinate());

                    novaBoja.dodajPovrsinu(povrsinaZaDodati);
                    listaBoja.add(novaBoja);

                } else {
                    for (Boja b : listaBoja) {
                        if (b.getBoja().equals(p.getBoja())) {
                            float povrsinaZaDodati = 0;

                            povrsinaZaDodati = odabirIzracunaPovrsine(p.getKoordinate());

                            b.dodajPovrsinu(povrsinaZaDodati);
                        }
                    }
                }
            }
        }
        return listaBoja;
    }

    public boolean bojaPostoji(List<Boja> listaBoja, String boja) {
        for (Boja b : listaBoja) {
            if (b.getBoja().equals(boja)) {
                return true;
            }
        }
        return false;
    }

    public float odabirIzracunaPovrsine(String koordinate) {
        float povrsina = 0;

        String[] rastavljeneKoordinate = koordinate.split(",");
        int[] intRastavljeneKoordinate = new int[rastavljeneKoordinate.length];
        for (int i = 0; i < rastavljeneKoordinate.length; i++) {
            intRastavljeneKoordinate[i] = Integer.parseInt(rastavljeneKoordinate[i]);
        }

        if (intRastavljeneKoordinate.length == 3) {
            povrsina = izracunaPovrsinuKruga(intRastavljeneKoordinate[2]);
        } else if (intRastavljeneKoordinate.length == 4) {
            int x1, x2, x3, x4, y1, y2, y3, y4 = 0;
            x1 = intRastavljeneKoordinate[0];
            y1 = intRastavljeneKoordinate[1];

            x2 = intRastavljeneKoordinate[2];
            y2 = intRastavljeneKoordinate[1];

            x3 = intRastavljeneKoordinate[2];
            y3 = intRastavljeneKoordinate[3];

            x4 = intRastavljeneKoordinate[0];
            y4 = intRastavljeneKoordinate[3];

            int[] iksevi = {x1, x2, x3, x4};
            int[] ipsiloni = {y1, y2, y3, y4};

            povrsina = izracunajPovrsinuMnogokuta(iksevi, ipsiloni, 4);

        } else {
            int[] iksevi = new int[intRastavljeneKoordinate.length / 2];
            int[] ipsiloni = new int[intRastavljeneKoordinate.length / 2];
            List<Integer> listaIkseva = new ArrayList<>();
            List<Integer> listaIpsilona = new ArrayList<>();
            for (int i = 0; i < intRastavljeneKoordinate.length; i++) {

                if (i % 2 == 0) {
                    listaIkseva.add(intRastavljeneKoordinate[i]);
                } else {
                    listaIpsilona.add(intRastavljeneKoordinate[i]);
                }
            }
            int brojac = 0;
            for (int broj : listaIkseva) {
                iksevi[brojac] = broj;
                brojac++;
            }
            brojac = 0;
            for (int broj : listaIpsilona) {
                ipsiloni[brojac] = broj;
                brojac++;
            }
            povrsina = izracunajPovrsinuMnogokuta(iksevi, ipsiloni, intRastavljeneKoordinate.length / 2);
        }

        return povrsina;
    }

}
