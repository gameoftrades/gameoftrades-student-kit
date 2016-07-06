package io.gameoftrades.studentNN;

import java.util.List;

import io.gameoftrades.model.Handelaar;
import io.gameoftrades.model.Wereld;
import io.gameoftrades.model.kaart.Kaart;
import io.gameoftrades.model.kaart.Pad;
import io.gameoftrades.model.kaart.Stad;
import io.gameoftrades.model.lader.WereldLader;
import io.gameoftrades.model.markt.Handelsplan;

/**
 * Welkom bij Game of Trades! 
 * 
 * Voordat er begonnen kan worden moet eerst de 'studentNN' package omgenoemd worden
 * zodat iedere groep zijn eigen namespace heeft. Vervang de NN met je groep nummer.
 * Dus als je in groep 3 zit dan wordt de packagenaam 'student03' en ben je in groep
 * 42 dan wordt de package naam 'student42'.
 * 
 * Om te controleren of je het goed hebt gedaan is er de ProjectSanityTest die je kan draaien.
 * 
 */
public class HandelaarImpl implements Handelaar {

    /**
     * Opdracht 1, zie ook de handige test-set in WereldLaderImplTest.
     */
    @Override
    public WereldLader nieuweWereldLader() {
        return new WereldLaderImpl();
    }

    /**
     * Opdracht 2
     */
    @Override
    public Pad bepaalSnelstePad(Kaart kaart, Stad van, Stad naar) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Opdracht 3
     */
    @Override
    public List<Stad> stedenTour(Kaart kaart, List<Stad> steden) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Opdracht 4
     */
    @Override
    public Handelsplan optimaliseerWinst(Wereld wereld, Stad startStad, int kapitaal, int capaciteit, int maxTijd) {
        // TODO Auto-generated method stub
        return null;
    }

}
