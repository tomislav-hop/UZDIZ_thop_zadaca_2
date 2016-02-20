/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import thop_zadaca_2.tipoviPodataka.PocetniPodatak;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomislav
 */
public class ProvjeraPodataka {

    public ProvjeraPodataka() {
    }

    public List<PocetniPodatak> provjeri_i_spremi_podatke(String sadrzajDatoteke) {
        //List<Podatak> ispravniPodaci = new ArrayList<>();
        List<PocetniPodatak> pocetniPodaci = new ArrayList<>();

        int brojacIshodisnih = 0;
        int pomocniBrojac = 0;

        String stringPoEnterima[] = sadrzajDatoteke.split("\\r?\\n");

        System.out.println("**************************************************");
        System.out.println("Ispis provjere podataka u datoteci po redovima:");
        for (int i = 0; i < stringPoEnterima.length; i++) {
            String stringPoTabovima[] = stringPoEnterima[i].split("\\r?\\t");
            String provjera = provjeraKoordinata(stringPoTabovima[3]);

            if (Integer.parseInt(stringPoTabovima[1]) == Integer.parseInt(stringPoTabovima[2])) {
                brojacIshodisnih++;

            }
            if (brojacIshodisnih == 1) {
                if (provjera.equals("OK")) {
                    if (provjeraSifre(pocetniPodaci, Integer.parseInt(stringPoTabovima[1]))) {
                        if (provjeraRoditelja(pocetniPodaci, Integer.parseInt(stringPoTabovima[1]), Integer.parseInt(stringPoTabovima[2]))) {
                            System.out.println("\tRed broj " + i + " " + provjera);
                            pomocniBrojac++;
                            //if(brojacIshodisnih)
                            if (pomocniBrojac == 1) {
                                PocetniPodatak pocPod = new PocetniPodatak(Integer.parseInt(stringPoTabovima[0]), Integer.parseInt(stringPoTabovima[1]), Integer.parseInt(stringPoTabovima[2]), stringPoTabovima[3], stringPoTabovima[4], false);
                                pocetniPodaci.add(pocPod);
                            } else {
                                PocetniPodatak pocPod = new PocetniPodatak(Integer.parseInt(stringPoTabovima[0]), Integer.parseInt(stringPoTabovima[1]), Integer.parseInt(stringPoTabovima[2]), stringPoTabovima[3], stringPoTabovima[4], provjeraPrestupaGranice(vratiRoditelja(pocetniPodaci, Integer.parseInt(stringPoTabovima[2])), stringPoTabovima[3]));
                                pocetniPodaci.add(pocPod);
                            }
                            //Podatak podatak = new Podatak(Integer.parseInt(stringPoTabovima[0]), Integer.parseInt(stringPoTabovima[1]), Integer.parseInt(stringPoTabovima[2]), stringPoTabovima[3], stringPoTabovima[4], true);

                            //ispravniPodaci.add(podatak);
                        } else {
                            System.out.println("\tRed broj " + i + " Ne postoji roditelj");
                        }
                    } else {
                        System.out.println("\tRed broj " + i + " Šifra već postoji u podacima");
                    }
                } else {
                    System.out.println("\tRed broj " + i + " " + provjera);
                }
            } else {
                System.out.println("\tRed broj " + i + " Već postoji ishodišni element");
                brojacIshodisnih--;
            }
        }

        return pocetniPodaci;
    }

    private String provjeraKoordinata(String koordinateZaProvjeru) {

        String[] koordinate = koordinateZaProvjeru.split(",");
        String provjera = "";
        if (koordinate.length == 3) {
            provjera = "OK";
        } else if (koordinate.length == 4) {
            provjera = "OK";
        } else if (koordinate.length % 2 == 0 && koordinate.length / 2 <= 7 && koordinate.length / 2 > 2) {
            provjera = "OK";
        } else {
            provjera = "Ne odgovara jer je broj x i y sveukupno: " + koordinate.length;
        }
        return provjera;
    }

    /**
     *
     * @param lista
     * @param sifra
     * @return true ako ne postoji šifra u listi ili false ako postoji šifra u
     * listi
     */
    private boolean provjeraSifre(List<PocetniPodatak> lista, int sifra) {
        for (PocetniPodatak p : lista) {
            //System.out.println(p);
            if (p.getSifra() == sifra) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param lista
     * @param roditelj
     * @return true ako postoji roditelj u listi ili ako je to početni vraća
     * false ako ne postoji roditelj u listi
     */
    private boolean provjeraRoditelja(List<PocetniPodatak> lista, int sifra, int roditelj) {
        if (sifra == roditelj) {
            return true;
        } else {
            for (PocetniPodatak p : lista) {
                if (p.getSifra() == roditelj) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean provjeraPrestupaGranice(PocetniPodatak roditelj, String dijete) {

        String koord = roditelj.getKoordinate();
        String[] koordinate = koord.split(",");
        int maxX = Integer.parseInt(koordinate[2])-Integer.parseInt(koordinate[0]);
        int maxY = Integer.parseInt(koordinate[3])-Integer.parseInt(koordinate[1]);

        //String koordD = dijete.getKoordinate();
        String[] koordinateD = dijete.split(",");

        if (koordinateD.length == 3) {

            int maxXkruga = Integer.parseInt(koordinateD[2]) + Integer.parseInt(koordinateD[0]);
            int maxYkruga = Integer.parseInt(koordinateD[2]) + Integer.parseInt(koordinateD[1]);
            
            int minXkruga = Integer.parseInt(koordinateD[0]) - Integer.parseInt(koordinateD[2]);
            int minYkruga = Integer.parseInt(koordinateD[0]) - Integer.parseInt(koordinateD[2]);
            /*System.out.println("PROVJERA GRANICA");
            System.out.println("MAX X: " + maxX + "\tMAX Y: " + maxY);
            System.out.println("X KRUGA: " + maxXkruga + "\tY KRUGA: " + maxYkruga + "\tRADIUS: " + koordinateD[2]);
            System.out.println("XMIN KRUGA: " + minXkruga + "\tYMIN KRUGA: " + minYkruga + "\tRADIUS: " + koordinateD[2]);*/

            if (maxXkruga > maxX || maxYkruga > maxY || minXkruga < 0 || minYkruga < 0) {
                return true;
            }
        } else {
            for (int i = 0; i < koordinateD.length; i++) {
                if (i % 2 == 0) {
                    if (Integer.parseInt(koordinateD[i]) > maxX) {
                        return true;
                    }
                } else {
                    if (Integer.parseInt(koordinateD[i]) > maxY) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    private PocetniPodatak vratiRoditelja(List<PocetniPodatak> listaSvih, int sifra) {
        PocetniPodatak roditelj = new PocetniPodatak();

        //System.out.println("RODITELJ KOJEGA TRAZIM JE: " + sifra);

        for (PocetniPodatak pp : listaSvih) {
            if (pp.getSifra() == sifra) {
                roditelj = pp;
                return roditelj;
            }
        }

        return roditelj;
    }
}
