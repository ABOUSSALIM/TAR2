package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.dao.IDao;
import ma.projet.beans.Developpeur;

import ma.projet.connexion.Connexion;
public class DeveloppeurService implements IDao<Developpeur>{

    @Override
    public boolean create(Developpeur o) {
       boolean c = false ;
        PreparedStatement st = null;
        try{
           String req = "INSERT INTO `developpeur` (`id`, `nom`, `salaire`) VALUES (NULL, ?, ?);";
           st = Connexion.getCn().prepareStatement(req);
           st.setString(1, o.getNom());
           st.setDouble(2, o.getSalaire());
           if(st.executeUpdate() == 1){
               c = true ;
           }
        }catch(SQLException e){
             Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null, e);
        }
        return c ;
    }

    @Override
    public boolean delete(Developpeur o) {
     PreparedStatement st = null;
     boolean c = false ;
     try{
         String req = "DELETE FROM developpeur WHERE id = ?";
         st = Connexion.getCn().prepareStatement(req);
         st.setInt(1, o.getId());
         if(st.executeUpdate()== 1){
             c = true ;
         }
     }catch(SQLException e){
         Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null,e);
     }
     return c ;
    }

    @Override
    public boolean update(Developpeur o) {
        PreparedStatement st = null ;
        boolean c = false ;
        try {
            String req = "UPDATE TABLE developpeur"
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
            Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null,e);
        }
        return c ;
    }

    @Override
    public Developpeur findById(int id) {
    PreparedStatement st = null ;
    Developpeur d = null ;
    try{
        String req = "SELECT * FROM developpeur WHERE id = ?";
        st = Connexion.getCn().prepareStatement(req);
        st.setInt(1,id);
        ResultSet  rs = st.executeQuery();
    while(rs.next()){
       d = new Developpeur(rs.getInt(1),rs.getString(2),rs.getDouble(3));
    }
        
    }catch(SQLException e){
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE,null,e);
    }
    return d ; 
    }

    @Override
    public List<Developpeur> findAll() {
    PreparedStatement st = null ;
    List<Developpeur> dvs = new ArrayList<>();
    try{
    String req = "SELECT * FROM developpeur";
    st = Connexion.getCn().prepareStatement(req);
    ResultSet rs = st.executeQuery();
     while(rs.next()){
      dvs.add(new Developpeur(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
     }
    }catch(SQLException e){
        Logger.getLogger(DeveloppeurService.class.getName()).log(Level.SEVERE, null,e);
    }
    return dvs ; 
    }
    
}
