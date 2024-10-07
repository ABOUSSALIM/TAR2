
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.beans.Personne;
import ma.projet.connexion.Connexion;
import ma.projet.dao.IDao;


public class EmployeService implements IDao<Personne>{
       @Override
    public boolean create(Personne o) {
       boolean c = false ;
        PreparedStatement st = null;
        try{
           String req = "INSERT INTO `personne` (`id`, `nom`, `salaire`) VALUES (NULL, ?, ?);";
           st = Connexion.getCn().prepareStatement(req);
           st.setString(1, o.getNom());
           st.setDouble(2, o.getSalaire());
           if(st.executeUpdate() == 1){
               c = true ;
           }
        }catch(SQLException e){
             Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null, e);
        }
        return c ;
    }

    @Override
    public boolean delete(Personne o) {
     PreparedStatement st = null;
     boolean c = false ;
     try{
         String req = "DELETE FROM personne WHERE id = ?";
         st = Connexion.getCn().prepareStatement(req);
         st.setInt(1, o.getId());
         if(st.executeUpdate()== 1){
             c = true ;
         }
     }catch(SQLException e){
         Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE,null,e);
     }
     return c ;
    }

    @Override
    public boolean update(Personne o) {
        PreparedStatement st = null ;
        boolean c = false ;
        try {
            String req = "UPDATE TABLE personne"
                    + "SET nom = ? , salaire = ?"
                    + "WHERE id = ? ";
            st = Connexion.getCn().prepareStatement(req);
            st.setString(1, o.getNom());
            st.setDouble(2, o.getSalaire());
            st.setInt(3, o.getId());
            if(st.executeUpdate() == 1){
                c = true ;
            }
        }catch(SQLException e){
            Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE,null,e);
        }
        return c ;
    }

    @Override
    public Personne findById(int id) {
    PreparedStatement st = null ;
    Personne e = null ;
    try{
        String req = "SELECT * FROM Personne WHERE id = ?";
        st = Connexion.getCn().prepareStatement(req);
        st.setInt(1,id);
        ResultSet  rs = st.executeQuery();
    while(rs.next()){
       e = new Personne(rs.getInt(1),rs.getString(2),rs.getDouble(3));
    }
        
    }catch(SQLException ex){
        Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE,null,ex);
    }
    return e ; 
    }

    @Override
    public List<Personne> findAll() {
    PreparedStatement st = null ;
    List<Personne> es = new ArrayList<>();
    try{
    String req = "SELECT * FROM personne";
    st = Connexion.getCn().prepareStatement(req);
    ResultSet rs = st.executeQuery();
     while(rs.next()){
      es.add(new Personne(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
     }
    }catch(SQLException e){
        Logger.getLogger(EmployeService.class.getName()).log(Level.SEVERE, null,e);
    }
    return es ; 
    }
}
