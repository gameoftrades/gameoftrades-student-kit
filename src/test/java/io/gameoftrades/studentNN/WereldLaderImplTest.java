package io.gameoftrades.studentNN;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Coordinaat;
import io.gameoftrades.model.kaart.TerreinType;
import io.gameoftrades.model.lader.WereldLader;
import io.gameoftrades.model.markt.HandelType;

/**
 * Handige test om de WereldLader implementatie te testen.
 * Zorg dat alle testen groen worden.
 */
public class WereldLaderImplTest {

    private WereldLader lader;

    @Before
    public void init() {
        lader = new WereldLaderImpl();
    }

    @Test
    public void zouVoorbeeldKaartMoetenLaden() {
        Wereld wereld = lader.laad("/kaarten/voorbeeld-kaart.txt");

        assertNotNull(wereld);
        assertNotNull(wereld.getKaart());
        assertNotNull(wereld.getSteden());
        assertNotNull(wereld.getMarkt());

        assertEquals(10, wereld.getKaart().getBreedte());
        assertEquals(10, wereld.getKaart().getHoogte());
        assertEquals(TerreinType.ZEE, wereld.getKaart().getTerreinOp(Coordinaat.op(0, 0)).getTerreinType());
        assertEquals(TerreinType.STAD, wereld.getKaart().getTerreinOp(Coordinaat.op(4, 1)).getTerreinType());
        assertEquals(TerreinType.GRASLAND, wereld.getKaart().getTerreinOp(Coordinaat.op(1, 2)).getTerreinType());
        assertEquals(TerreinType.BOS, wereld.getKaart().getTerreinOp(Coordinaat.op(7, 4)).getTerreinType());
        assertEquals(TerreinType.BERG, wereld.getKaart().getTerreinOp(Coordinaat.op(4, 5)).getTerreinType());

        assertEquals(4, wereld.getSteden().size());
        assertEquals("Aberdeen", wereld.getSteden().get(0).getNaam());
        assertEquals("Birmingham", wereld.getSteden().get(1).getNaam());
        assertEquals("Cambridge", wereld.getSteden().get(2).getNaam());
        assertEquals("Derry", wereld.getSteden().get(3).getNaam());

        assertEquals(5, wereld.getMarkt().getHandel().size());
        assertEquals(wereld.getSteden().get(0), wereld.getMarkt().getHandel().get(0).getStad());
        assertEquals("schapen", wereld.getMarkt().getHandel().get(0).getHandelswaar().getNaam());
        assertEquals(HandelType.BIEDT, wereld.getMarkt().getHandel().get(0).getHandelType());
        assertEquals(15, wereld.getMarkt().getHandel().get(0).getPrijs());
    }

    @Test
    public void zouLegeKaartMoetenLaden() {
        Wereld w = lader.laad("/kaarten/testcases/lege-kaart.txt");

        assertEquals(0, w.getKaart().getBreedte());
        assertEquals(0, w.getKaart().getHoogte());
        assertTrue(w.getSteden().isEmpty());
        assertTrue(w.getMarkt().getHandel().isEmpty());
    }

    @Test
    public void zouBredeKaartMoetenLaden() {
        Wereld w = lader.laad("/kaarten/testcases/brede-kaart.txt");

        assertEquals(20, w.getKaart().getBreedte());
        assertEquals(1, w.getKaart().getHoogte());
        assertTrue(w.getSteden().isEmpty());
        assertTrue(w.getMarkt().getHandel().isEmpty());
    }

    @Test
    public void zouDiepeKaartMoetenLaden() {
        Wereld w = lader.laad("/kaarten/testcases/diepe-kaart.txt");

        assertEquals(1, w.getKaart().getBreedte());
        assertEquals(20, w.getKaart().getHoogte());
        assertTrue(w.getSteden().isEmpty());
        assertTrue(w.getMarkt().getHandel().isEmpty());
    }

    @Test
    public void zouKaartMetExtraSpatiesMoetenladen() {
        Wereld w = lader.laad("/kaarten/testcases/kaart-met-spaties.txt");
        assertEquals(5, w.getKaart().getBreedte());
        assertEquals(5, w.getKaart().getHoogte());
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouEenTeSmalleKaartNietMoetenLaden() {
        lader.laad("/kaarten/testcases/te-smalle-kaart.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouEenTeKorteKaartNietMoetenLaden() {
        lader.laad("/kaarten/testcases/te-korte-kaart.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouEenKaartMetVerkeerdGebiedNietMoetenLaden() {
        lader.laad("/kaarten/testcases/verkeerd-gebied.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouStadMetVerkeerdeCoordinatenNietMoetenLaden() {
        lader.laad("/kaarten/testcases/stad-verkeerde-coordinaten.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouHandelMetVerkeerdeStadNietMoetenLaden() {
        lader.laad("/kaarten/testcases/handel-verkeerde-stad.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouHandelMetVerkeerdeVraagNietMoetenLaden() {
        lader.laad("/kaarten/testcases/handel-verkeerde-vraag.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void zouHandelMetVerkeerdePrijsNietMoetenLaden() {
        lader.laad("/kaarten/testcases/handel-verkeerde-prijs.txt");
    }

    @Test
    public void zouGameOfThronesKaartMoetenLaden() {
        Wereld wereld = lader.laad("/kaarten/westeros-kaart.txt");
        assertEquals(21, wereld.getSteden().size());
        assertEquals(25, wereld.getKaart().getBreedte());
        assertEquals(50, wereld.getKaart().getHoogte());
    }

}
