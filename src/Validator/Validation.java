/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import java.util.regex.Pattern;

/**
 *
 * @author junio
 */
public class Validation {
    public static boolean isValidMail(String email)
    {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null || email == "")
            return false;
        return pat.matcher(email).matches();
    }
    
      public static boolean isValidTel(String tel)
    {
        String telRegex = "^(\\+|00)(221)(70|76|77|78|33)([0-9]{7})";

        Pattern pat = Pattern.compile(telRegex);
        if (tel == null || tel == "")
            return false;
        return pat.matcher(tel).matches();
    }

}
