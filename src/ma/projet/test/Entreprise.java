
package ma.projet.test;
import ma.projet.connexion.Connexion;
import service.DeveloppeurService;
import ma.projet.beans.Developpeur;
import ma.projet.beans.Directeur;
import ma.projet.beans.Manager;
import service.DirecteurService;
import service.ManagerService;
public class Entreprise {
    public static void main(String[] args) {
    
    DeveloppeurService dv = new DeveloppeurService();
   
    dv.create(new Developpeur("Mohammed",1300));
    dv.create(new Developpeur("Aissa",1300));
    
    Manager m = new Manager("Baqqali",20000);
    ManagerService mgs = new ManagerService();
    mgs.create(m);
    dv.findById(1).setM(m);
    dv.findById(2).setM(m);
    
     dv.create(new Developpeur("Adil",1300));
    
    Directeur d = new Directeur ("ALAWI",30000);
    DirecteurService drs = new DirecteurService();
    drs.create(d);
    m.setD(d);
    
    dv.findById(3).setD(d);
}
}

