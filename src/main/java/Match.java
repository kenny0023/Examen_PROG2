import java.time.LocalDateTime;
import java.util.UUID;

class Match {
    private String id;
    private LocalDateTime date;
    private String endroit;
    private MatchType typeMatch;
    private Fighter combattant1;
    private Fighter combattant2;
    private Integer points1;
    private Integer points2;
    private Result resultat;
    private String titre;

    public Match(Fighter combattant1, Fighter combattant2, LocalDateTime date, String endroit, 
                 MatchType typeMatch, String titre) {
        if (typeMatch == null) {
            throw new IllegalArgumentException("Le type de match doit être spécifié.");
        }
        if (typeMatch == MatchType.TITRE && (titre == null || titre.isEmpty())) {
            throw new IllegalArgumentException("Un match de titre doit avoir un titre spécifié.");
        }
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.endroit = endroit;
        this.typeMatch = typeMatch;
        this.combattant1 = combattant1;
        this.combattant2 = combattant2;
        this.titre = typeMatch == MatchType.TITRE ? titre : null;
    }

    public void finish(int points1, int points2) {
        this.points1 = points1;
        this.points2 = points2;

        if (points1 > points2) {
            this.resultat = Result.VICTOIRE;
        } else if (points2 > points1) {
            this.resultat = Result.DEFAITE;
        } else {
            this.resultat = Result.EGALITE;
        }

        if (typeMatch == MatchType.OFFICIEL || typeMatch == MatchType.TITRE) {
            if (this.resultat == Result.VICTOIRE) {
                combattant1.incrementVictoires();
                combattant2.incrementDefaites();
            } else if (this.resultat == Result.DEFAITE) {
                combattant1.incrementDefaites();
                combattant2.incrementVictoires();
            } else {
                combattant1.incrementEgalites();
                combattant2.incrementEgalites();
            }

            if (typeMatch == MatchType.TITRE && this.resultat == Result.VICTOIRE) {
                combattant1.addTitre(titre);
            }
        }
    }

    public String getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public String getEndroit() { return endroit; }
    public MatchType getTypeMatch() { return typeMatch; }
    public Fighter getCombattant1() { return combattant1; }
    public Fighter getCombattant2() { return combattant2; }
    public Integer getPoints1() { return points1; }
    public Integer getPoints2() { return points2; }
    public Result getResultat() { return resultat; }
    public String getTitre() { return titre; }
}
