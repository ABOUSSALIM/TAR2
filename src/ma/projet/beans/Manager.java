
package ma.projet.beans;
import ma.projet.beans.Personne;
import ma.projet.connexion.Connexion;


public class Manager extends Personne{
  private Directeur d ;
    public Manager(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }

    public Manager(Directeur d, String nom, double salaire) {
        super(nom, salaire);
        this.d = d;
    }
    
    public Manager() {
    }

    public Manager(String nom, double salaire) {
        super(nom, salaire);
    }

    public Directeur getD() {
        return d;
    }

    public void setD(Directeur d) {
        this.d = d;
    }
    
    
}
