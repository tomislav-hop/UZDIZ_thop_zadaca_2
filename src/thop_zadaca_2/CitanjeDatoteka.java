/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thop_zadaca_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomislav
 */
public class CitanjeDatoteka {

    public CitanjeDatoteka() {
    }

    public String citanjeDatoteke(String nazivDatoteke) {
        System.out.println("Naziv datoteke: " + nazivDatoteke);
        String sve = "";

        try (BufferedReader br = new BufferedReader(new FileReader(nazivDatoteke))) {
            StringBuilder sb = new StringBuilder();
            String red = br.readLine();
            while (red != null) {
                sb.append(red);
                sb.append(System.lineSeparator());
                red = br.readLine();
            }
            sve = sb.toString();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CitanjeDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CitanjeDatoteka.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println(sve);
        return sve;
    }
    
    

}
