import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FightLeague {
    private String nom;
    private List<Fighter> combattants;
    private List<Match> matchs;

    public FightLeague(String nom) {
        this.nom = nom;
        this.combattants = new ArrayList<>();
        this.matchs = new ArrayList<>();
    }

    public Fighter ajouterCombattant(String nom, String prenom, String nomCombattant, double poids) {
        Fighter combattant = new Fighter(nom, prenom, nomCombattant, poids);
        combattants.add(combattant);
        return combattant;
    }

    public Match creerMatch(Fighter combattant1, Fighter combattant2, LocalDateTime date, 
                          String endroit, MatchType typeMatch, String titre) {
        Match match = new Match(combattant1, combattant2, date, endroit, typeMatch, titre);
        matchs.add(match);
        return match;
    }

    public List<Match> getMatchsCombattant(String combattantId) {
        List<Match> result = new ArrayList<>();
        for (Match match : matchs) {
            if (Objects.equals(match.getCombattant1().getId(), combattantId) || 
                Objects.equals(match.getCombattant2().getId(), combattantId)) {
                result.add(match);
            }
        }
        return result;
    }
}
