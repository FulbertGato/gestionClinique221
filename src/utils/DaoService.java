/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author junio
 */
public class DaoService {
    
      public static String lts(LocalDate lctdate){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");
      return lctdate.format(formatter);}
    
    public static LocalDate stl(String date){
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-LLLL-yyyy");

     LocalDate conv = LocalDate.parse(date, formatter);
       return conv;
    }

}
