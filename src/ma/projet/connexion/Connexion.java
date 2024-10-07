
package ma.projet.connexion;

import java.io.FileInputStream ;
import java.sql.SQLException ;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    private static Connection cn  ;
    static{
        try{
            cn = null ; 
            FileInputStream f = new FileInputStream("config/base.properties");

            Properties p = new Properties();
            p.load(f);
           
            String url = p.getProperty("jdbc.url");
            String login = p.getProperty("jdbc.username");
            String password = p.getProperty("jdbc.password");
            String driver = p.getProperty("jdbc.driver");
            Class.forName(driver);
            cn = DriverManager.getConnection(url, login, password);
        }catch(Exception e){
            System.out.println(" "+e.getMessage());
            e.printStackTrace();
        }
    }
    public static Connection getCn(){
        return cn;
    }
}
