
package ma.projet.beans;
import ma.projet.beans.Personne;
import ma.projet.connexion.Connexion;

public class Developpeur extends Personne{

    private Manager m ;
    private Directeur d ;
    public Developpeur(int id, String nom, double salaire) {
        super(id, nom, salaire);
    }

    public Developpeur(Manager m, Directeur d, String nom, double salaire) {
        super(nom, salaire);
        this.m = m;
        this.d = d;
    }

    
    public Developpeur(Manager m, String nom, double salaire) {
        super(nom, salaire);
        this.m = m;
    }

    public Developpeur() {
    }

    public Developpeur(String nom, double salaire) {
        super(nom, salaire);
    }

    public Manager getM() {
        return m;
    }

    public void setM(Manager m) {
        this.m = m;
    }

    public Directeur getD() {
        return d;
    }

    public void setD(Directeur d) {
        this.d = d;
    }
    
    
}
