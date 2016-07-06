package io.gameoftrades.studentNN;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.gameoftrades.model.Handelaar;
import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.lader.WereldLader;
import io.gameoftrades.model.markt.Handelsplan;
import io.gameoftrades.model.markt.actie.Actie;
import io.gameoftrades.model.markt.actie.BeweegActie;

/**
 * Een verzameling eenvoudige tests om te kijken of de handelaar werkt.
 * 
 * Breid deze uit en of maak je eigen oplossing specifieke tests.
 */
public class HandelaarImplTest {

    private Handelaar handelaar;

    @Before
    public void init() {
        handelaar = new HandelaarImpl();
    }

    @Test
    public void zouWereldMoetenLaden() {
        WereldLader lader = handelaar.nieuweWereldLader();
        assertNotNull(lader);

        Wereld wereld = lader.laad("/kaarten/voorbeeld-kaart.txt");
        assertNotNull(wereld);
    }

    @Test
    public void zouEenPadMoetenVinden() {
        Wereld wereld = handelaar.nieuweWereldLader().laad("/kaarten/voorbeeld-kaart.txt");
        
        Kaart kaart = wereld.getKaart();
        Stad van = wereld.getSteden().get(0);
        Stad naar = wereld.getSteden().get(1);

        Pad pad = handelaar.bepaalSnelstePad(kaart, van, naar);

        assertNotNull(pad.getBewegingen());

        // 19 is de tijd voor de meest optimale route, om de bergen heen.

        assertEquals(19, pad.getTotaleTijd());

        // Heen

        Coordinaat bestemming = pad.volg(van.getCoordinaat());
        assertEquals(naar.getCoordinaat(), bestemming);

        // En Terug

        Coordinaat bron = pad.omgekeerd().volg(naar.getCoordinaat());
        assertEquals(van.getCoordinaat(), bron);
    }

    @Test
    public void zouEenRouteMoetenVinden() {
        Wereld wereld = handelaar.nieuweWereldLader().laad("/kaarten/voorbeeld-kaart.txt");

        List<Stad> result = handelaar.stedenTour(wereld.getKaart(), wereld.getSteden());

        assertNotNull(result);
        assertEquals(4, result.size());
        assertEquals(4, new HashSet<>(result).size()); // check duplicaten.
    }

    @Test
    public void zouEenHandelsplanMoetenMaken() {
        Wereld wereld = handelaar.nieuweWereldLader().laad("/kaarten/voorbeeld-kaart.txt");
        
        Stad startStad = wereld.getSteden().get(0);
        
        Handelsplan plan = handelaar.optimaliseerWinst(wereld, startStad, 150, 10, 50);

        assertNotNull(plan);
        assertNotNull(plan.getActies());

        for (Actie a : plan.getActies()) {
            assertNotNull(a);
            assertFalse("BeweegActie gevonden, zet deze om in NavigeerActies", a instanceof BeweegActie);
        }
    }

}
