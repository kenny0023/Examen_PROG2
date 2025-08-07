import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;

class FightLeagueTest {
    private FightLeague ligue;
    private Fighter f1;
    private Fighter f2;
    private LocalDateTime date;

    @BeforeEach
    void setUp() {
        ligue = new FightLeague("Test League");
        f1 = ligue.ajouterCombattant("Kamzat", "John", "Le Lion", 80.0);
        f2 = ligue.ajouterCombattant("Mac", "Gregor", "Le Prédateur", 75.0);
        date = LocalDateTime.of(2025, 8, 7, 10, 0);
    }

    @Test
    void testCreerMatchAmical() {
        Match match = ligue.creerMatch(f1, f2, date, "Paris", MatchType.AMICAL, null);
        assertEquals(MatchType.AMICAL, match.getTypeMatch());
        assertNull(match.getTitre());
    }

    @Test
    void testCreerMatchTitreSansTitre() {
        assertThrows(IllegalArgumentException.class, () ->
                ligue.creerMatch(f1, f2, date, "Paris", MatchType.TITRE, null));
    }

    @Test
    void testMatchAmicalNoPalmaresUpdate() {
        Match match = ligue.creerMatch(f1, f2, date, "Paris", MatchType.AMICAL, null);
        match.finish(10, 5);
        assertEquals(0, f1.getVictoires());
        assertEquals(0, f2.getDefaites());
    }

    @Test
    void testMatchOfficielPalmaresUpdate() {
        Match match = ligue.creerMatch(f1, f2, date, "Paris", MatchType.OFFICIEL, null);
        match.finish(10, 5);
        assertEquals(1, f1.getVictoires());
        assertEquals(1, f2.getDefaites());
    }

    @Test
    void testMatchTitrePalmaresAndTitreUpdate() {
        Match match = ligue.creerMatch(f1, f2, date, "Paris", MatchType.TITRE, "Champion Poids Moyen");
        match.finish(10, 5);
        assertEquals(1, f1.getVictoires());
        assertEquals(1, f2.getDefaites());
        assertTrue(f1.getTitres().contains("Champion Poids Moyen"));
        assertFalse(f2.getTitres().contains("Champion Poids Moyen"));
    }

    @Test
    void testMatchEgalite() {
        Match match = ligue.creerMatch(f1, f2, date, "Paris", MatchType.OFFICIEL, null);
        match.finish(5, 5);
        assertEquals(1, f1.getEgalites());
        assertEquals(1, f2.getEgalites());
        assertEquals(0, f1.getVictoires());
        assertEquals(0, f2.getVictoires());
    }

    @Test
    void testGetMatchsCombattant() {
        Match match1 = ligue.creerMatch(f1, f2, date, "Paris", MatchType.AMICAL, null);
        Match match2 = ligue.creerMatch(f1, f2, date, "London", MatchType.OFFICIEL, null);
        List<Match> matchs = ligue.getMatchsCombattant(f1.getId());
        assertEquals(2, matchs.size());
        assertEquals("Paris", matchs.get(0).getEndroit());
        assertEquals("London", matchs.get(1).getEndroit());
    }
}