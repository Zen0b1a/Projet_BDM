/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Annabelle
 */
public class ConnexionUtils {
    final private String LOGIN = "ag092850";
    final private String MDP = "ag092850";
    final private String URL_FAC = "jdbc:oracle:thin:@butor:1521:ensb2016";
    final private String URL_EXTERIEUR = "jdbc:oracle:thin:@ufrsciencestech.u-bourgogne.fr:25561:ensb2016";
    private static Connection connect;
    private static ConnexionUtils single;
    
    private ConnexionUtils()
    {
        try 
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            this.connect = DriverManager.getConnection(URL_FAC, LOGIN, MDP);
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(ConnexionUtils.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (SQLException ex) 
        {
            try 
            {
                this.connect = DriverManager.getConnection(URL_EXTERIEUR, LOGIN, MDP);
            } 
            catch (SQLException ex1) 
            {
                Logger.getLogger(ConnexionUtils.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } 
    }
    
    public static Connection getInstance()
    {
        if(single==null)
            single = new ConnexionUtils();
        return connect;
    }
}
