/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import thop_zadaca_2.tipoviPodataka.PocetniPodatak;
import thop_zadaca_2.tipoviPodataka.Podatak;
import thop_zadaca_2.tipoviPodataka.Boja;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thop_zadaca_2.iterator.IspisPocetnihPodataka;
import thop_zadaca_2.memento.CareTaker;
import thop_zadaca_2.memento.Originator;

/**
 *
 * @author Tomislav
 */
public class Thop_zadaca_2 {

    public static Originator izvor = new Originator();
    public static CareTaker save = new CareTaker();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Podatak glavniPodatak = null;

        String datotekaPodataka = args[0];
        CitanjeDatoteka cd = new CitanjeDatoteka();
        String sadrzajDatoteke = cd.citanjeDatoteke(datotekaPodataka);
        ProvjeraPodataka pp = new ProvjeraPodataka();
        List<PocetniPodatak> pocetniPodaci = pp.provjeri_i_spremi_podatke(sadrzajDatoteke);

        System.out.println("**************************************************");
        System.out.println("Ispis svih elemenata: ");
        IspisPocetnihPodataka ipp = new IspisPocetnihPodataka(pocetniPodaci);
        ipp.ispisPodataka();

        UpravljanjePodacima upravljanjePodacima = new UpravljanjePodacima(pocetniPodaci);
        System.out.println("**************************************************");
        System.out.println("Korijen je: ");
        glavniPodatak = upravljanjePodacima.SortiranjeElemenata(ipp.vratiKorijen());

        System.out.println("**************************************************");
        System.out.println("Ispis svih elemenata iz composita: ");
        upravljanjePodacima.IspisElemenataComposita(glavniPodatak);

        while (true) {
            try {
                System.out.println("**************************************************");
                System.out.println("Odaberite sljedeći korak: ");
                System.out.println(""
                        + "1 - pregled stanja, "
                        + "2 - pregled jednostavnih elemenata u presjeku, "
                        + "3 - promjena statusa elementa, "
                        + "4 - ukupne površine boja, "
                        + "5 - učitavanje dodatne datoteke, "
                        + "6 - spremnanje trenutnog stanja podataka ili ispis starih stanja, "
                        + "7 - ispis pocetnih podataka koji su trenutno ucitani, "
                        + "0 - izlaz iz programa");
                System.out.print("Unos(1/2/3/4/5/6/0): ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int odabir = Integer.parseInt(br.readLine());
                if (odabir == 1) {
                    System.out.println("**************************************************");
                    System.out.println("Ispis svih elemenata iz composita: ");
                    upravljanjePodacima.IspisElemenataComposita(glavniPodatak);
                } else if (odabir == 2) {
                    System.out.println("Pregled jednostavnih elemenata u presjeku: ");

                    List<Podatak> listaRoditelja = upravljanjePodacima.vratiListuRoditelja(glavniPodatak);
                    ProvjeraPresjekaDjece ppd = new ProvjeraPresjekaDjece();
                    for (Podatak p : listaRoditelja) {
                        List<Podatak> djeca = upravljanjePodacima.vratiDjecu(glavniPodatak, p.getSifra());
                        System.out.println("Presjeci djece od " + p.getSifra() + " su: ");
                        ppd.provjerePresjeka(djeca);
                    }

                } else if (odabir == 3) {
                    System.out.println("Unesite šifru i novi status elementa: ");
                    System.out.print("Šifra: ");
                    String sifra = br.readLine();
                    int intSifra = Integer.parseInt(sifra);
                    System.out.print("Status(aktivni/sakriveni): ");
                    String status = br.readLine();
                    boolean boolStatus;
                    if (status.equals("aktivni")) {
                        boolStatus = true;
                    } else {
                        boolStatus = false;
                    }
                    upravljanjePodacima.promjenaStatusaRoditeljuIDjeci(glavniPodatak, intSifra, boolStatus);
                    System.out.println("**************************************************");
                    System.out.println("Ispis svih elemenata iz composita: ");
                    upravljanjePodacima.IspisElemenataComposita(glavniPodatak);
                } else if (odabir == 4) {
                    System.out.println("Ukupne površine boja su: ");
                    upravljanjePodacima.setFiltriraniPodaci(new ArrayList<>());
                    List<Podatak> obliciZaPovrsinu = new ArrayList<>();
                    obliciZaPovrsinu = upravljanjePodacima.vratiElemente(glavniPodatak, true, false);
                    System.out.println("Ispis svih oblika za koje je potreban izracun");
                    for (Podatak oblik : obliciZaPovrsinu) {
                        System.out.println("\t" + oblik);
                    }
                    System.out.println("Ispis svih vrsta boja i povrsine");
                    IzracunPovrsine ip = new IzracunPovrsine();
                    List<Boja> listaBoja = ip.vratiListuBoja(obliciZaPovrsinu);
                    for (Boja b : listaBoja) {
                        System.out.println("\tBoja: " + b.getBoja() + "\t Povrsina: " + b.getPovrsina());
                    }
                } else if (odabir == 5) {
                    System.out.print("Unesite naziv datoteke koju dodajete: ");
                    String novaDatoteka = br.readLine();
                    String sadrzajNoveDatoteke = cd.citanjeDatoteke(novaDatoteka);
                    sadrzajDatoteke += sadrzajNoveDatoteke;
                    List<PocetniPodatak> noviPocetniPodaci = pp.provjeri_i_spremi_podatke(sadrzajDatoteke);
                    System.out.println("**************************************************");
                    System.out.println("Ispis svih elemenata: ");
                    IspisPocetnihPodataka ipp2 = new IspisPocetnihPodataka(noviPocetniPodaci);
                    ipp2.ispisPodataka();
                    System.out.println("**************************************************");
                    System.out.println("Korijen je: ");
                    UpravljanjePodacima upravljanjePodacimaPomocno = new UpravljanjePodacima(noviPocetniPodaci);
                    Podatak noviGlavniPodatak = upravljanjePodacimaPomocno.SortiranjeElemenata(ipp2.vratiKorijen());
                    glavniPodatak = noviGlavniPodatak;
                    pocetniPodaci = noviPocetniPodaci;
                    System.out.println("**************************************************");
                    System.out.println("Ispis svih elemenata iz composita: ");
                    upravljanjePodacimaPomocno.IspisElemenataComposita(glavniPodatak);
                } else if (odabir == 6) {
                    System.out.println("**************************************************");
                    System.out.println("Spremnanje trenutnog stanja podataka ili ispis starih stanja: ");
                    System.out.print("Zelite li spremiti trenutno stanje podataka ili ucitati staro koje ste vec spremili (spremiti/ucitati): ");
                    String akcija = br.readLine();
                    if (akcija.equals("spremiti")) {
                        izvor.setState(pocetniPodaci);
                        save.add(izvor.saveStateToMemento());
                    } else if (akcija.equals("ucitati")) {
                        System.out.print("Imate spremljeno: " + save.vratiVelicinu() + " stanja. Odaberite jedno (od 0 do " + (save.vratiVelicinu() - 1) + "): ");
                        String stanje = br.readLine();
                        int odabranoStanje = Integer.parseInt(stanje);
                        izvor.getStateFromMemento(save.get(odabranoStanje));
                        List<PocetniPodatak> ucitaniPodaci = izvor.getState();
                        System.out.println("Učitano stanje je: ");
                        for (PocetniPodatak p : ucitaniPodaci) {
                            System.out.println(p);
                        }

                        System.out.print("Zelite vratiti iznad ispisano stanje? (D/N): ");
                        String dane = br.readLine();
                        if (dane.equals("D")) {
                            pocetniPodaci = ucitaniPodaci;
                        }
                    }

                } else if (odabir == 7) {
                    for (PocetniPodatak p : pocetniPodaci) {
                        System.out.println(p);
                    }
                } else if (odabir == 0) {
                    System.out.println("Odabran broj 0, IZLAZ!");
                    break;
                } else {
                    System.out.println("Ne postoji taj korak!");
                }
            } catch (IOException ex) {
                Logger.getLogger(Thop_zadaca_2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
