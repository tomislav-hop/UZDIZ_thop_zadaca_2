/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import thop_zadaca_2.tipoviPodataka.Podatak;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomislav
 */
public class ProvjeraPresjekaDjece {

    public ProvjeraPresjekaDjece() {
    }

    public void provjerePresjeka(List<Podatak> djeca) {
        List<Shape> listaOblika = new ArrayList<>();
        for (Podatak d : djeca) {
            listaOblika.add(napraviOblik(d.getKoordinate()));
        }

        for (int i = 0; i < listaOblika.size(); i++) {
            for (int j = i + 1; j < listaOblika.size(); j++) {
                // compare list.get(i) and list.get(j)
                Area a = new Area(listaOblika.get(i));
                a.intersect(new Area(listaOblika.get(j)));
                if(!a.isEmpty()){
                    System.out.println("\tElement sifre "+djeca.get(i).getSifra() + " ima presjek sa elementom sifre " + djeca.get(j).getSifra());
                }

            }
        }

    }

    public Shape napraviOblik(String koordinate) {
        Shape oblik = null;

        String[] rastavljeneKoordinate = koordinate.split(",");
        int[] intRastavljeneKoordinate = new int[rastavljeneKoordinate.length];
        for (int i = 0; i < rastavljeneKoordinate.length; i++) {
            intRastavljeneKoordinate[i] = Integer.parseInt(rastavljeneKoordinate[i]);
        }

        if (intRastavljeneKoordinate.length == 3) {
            oblik = new Ellipse2D.Double(intRastavljeneKoordinate[0], intRastavljeneKoordinate[1], intRastavljeneKoordinate[2], intRastavljeneKoordinate[2]);
            return oblik;

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

            oblik = new Polygon(iksevi, ipsiloni, 4);
            return oblik;

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

            oblik = new Polygon(iksevi, ipsiloni, intRastavljeneKoordinate.length / 2);
            return oblik;
        }
    }
}
