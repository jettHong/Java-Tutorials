package com.jett.java7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 捕获多种异常
 * @author jett
 */
public class CatchMultiException {
    
    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(""));
            Connection conn = null;
            conn.prepareStatement("");
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
