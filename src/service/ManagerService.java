
package service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ma.projet.connexion.Connexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.projet.dao.IDao;
import ma.projet.beans.Manager;
public class ManagerService implements IDao<Manager>{

    @Override
    public boolean create(Manager o) {
       boolean c = false ;
        PreparedStatement st = null;
        try{
           String req = "INSERT INTO `manager` (`id`, `nom`, `salaire`) VALUES (NULL, ?, ?);";
           st = Connexion.getCn().prepareStatement(req);
           st.setString(1, o.getNom());
           st.setDouble(2, o.getSalaire());
           if(st.executeUpdate() == 1){
               c = true ;
           }
        }catch(SQLException e){
             Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null, e);
        }
        return c ;
    }

    @Override
    public boolean delete(Manager o) {
     PreparedStatement st = null;
     boolean c = false ;
     try{
         String req = "DELETE FROM manager WHERE id = ?";
         st = Connexion.getCn().prepareStatement(req);
         st.setInt(1, o.getId());
         if(st.executeUpdate()==1){
             c = true ;
         }
     }catch(SQLException e){
         Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE,null,e);
     }
     return c ;
    }

    @Override
    public boolean update(Manager o) {
        PreparedStatement st = null ;
        boolean c = false ;
        try {
            String req = "UPDATE TABLE manager"
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
            Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE,null,e);
        }
        return c ;
    }

    @Override
    public Manager findById(int id) {
    PreparedStatement st = null ;
    Manager m = null ;
    try{
        String req = "SELECT * FROM manager WHERE id = ?";
        st = Connexion.getCn().prepareStatement(req);
        st.setInt(1,id);
        ResultSet  rs = st.executeQuery();
    while(rs.next()){
       m = new Manager(rs.getInt(1),rs.getString(2),rs.getDouble(3));
    }
        
    }catch(SQLException e){
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE,null,e);
    }
    return m ; 
    }

    @Override
    public List<Manager> findAll() {
    PreparedStatement st = null ;
    List<Manager> mgs = new ArrayList<>();
    try{
    String req = "SELECT * FROM manager";
    st = Connexion.getCn().prepareStatement(req);
    ResultSet rs = st.executeQuery();
     while(rs.next()){
      mgs.add(new Manager(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
     }
    }catch(SQLException e){
        Logger.getLogger(ManagerService.class.getName()).log(Level.SEVERE, null,e);
    }
    return mgs ; 
    }
    
    
}
