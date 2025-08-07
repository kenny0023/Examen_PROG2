import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Fighter {
    private String id;
    private String nom;
    private String prenom;
    private String nomCombattant;
    private double poids;
    private List<String> titres;
    private int victoires;
    private int defaites;
    private int egalites;

    public Fighter(String nom, String prenom, String nomCombattant, double poids) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.prenom = prenom;
        this.nomCombattant = nomCombattant;
        this.poids = poids;
        this.titres = new ArrayList<>();
        this.victoires = 0;
        this.defaites = 0;
        this.egalites = 0;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNomCombattant() { return nomCombattant; }
    public double getPoids() { return poids; }
    public List<String> getTitres() { return titres; }
    public int getVictoires() { return victoires; }
    public int getDefaites() { return defaites; }
    public int getEgalites() { return egalites; }

    public void addTitre(String titre) { titres.add(titre); }
    public void incrementVictoires() { victoires++; }
    public void incrementDefaites() { defaites++; }
    public void incrementEgalites() { egalites++; }
}