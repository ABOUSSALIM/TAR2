
package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.dao.IDao;
import ma.projet.connexion.Connexion;
import ma.projet.beans.Directeur;
import ma.projet.beans.Personne;
public class DirecteurService implements IDao<Directeur>{
    
           @Override
    public boolean create(Directeur o) {
       boolean c = false ;
        PreparedStatement st = null;
        try{
           String req = "INSERT INTO `directeur` (`id`, `nom`, `salaire`) VALUES (NULL, ?, ?);";
           st = Connexion.getCn().prepareStatement(req);
           st.setString(1, o.getNom());
           st.setDouble(2, o.getSalaire());
           if(st.executeUpdate() == 1){
               c = true ;
           }
        }catch(SQLException e){
             Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE, null, e);
        }
        return c ;
    }

    @Override
    public boolean delete(Directeur o) {
     PreparedStatement st = null;
     boolean c = false ;
     try{
         String req = "DELETE FROM directeur WHERE id = ?";
         st = Connexion.getCn().prepareStatement(req);
         st.setInt(1, o.getId());
         if(st.executeUpdate()== 1){
             c = true ;
         }
     }catch(SQLException e){
         Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE,null,e);
     }
     return c ;
    }

    @Override
    public boolean update(Directeur o) {
        PreparedStatement st = null ;
        boolean c = false ;
        try {
            String req = "UPDATE TABLE directeur"
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
            Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE,null,e);
        }
        return c ;
    }

    @Override
    public Directeur findById(int id) {
    PreparedStatement st = null ;
    Directeur e = null ;
    try{
        String req = "SELECT * FROM directeur WHERE id = ?";
        st = Connexion.getCn().prepareStatement(req);
        st.setInt(1,id);
        ResultSet  rs = st.executeQuery();
    while(rs.next()){
       e = new Directeur(rs.getInt(1),rs.getString(2),rs.getDouble(3));
    }
        
    }catch(SQLException ex){
        Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE,null,ex);
    }
    return e ; 
    }

    @Override
    public List<Directeur> findAll() {
    PreparedStatement st = null ;
    List<Directeur> es = new ArrayList<>();
    try{
    String req = "SELECT * FROM directeur";
    st = Connexion.getCn().prepareStatement(req);
    ResultSet rs = st.executeQuery();
     while(rs.next()){
      es.add(new  Directeur(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
     }
    }catch(SQLException e){
        Logger.getLogger(DirecteurService.class.getName()).log(Level.SEVERE, null,e);
    }
    return es ; 
    }
}
