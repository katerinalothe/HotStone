package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.UIGame;
import hotstone.standard.VariantFactories.AlphaFactory;
import hotstone.standard.VariantFactories.GammaFactory;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestTranscriptor {

    @Test
    public void shouldOutputForSuccessfulActions() {
        // Given a transcriptor game,
        UIGame game = new TranscriptorHotStoneGame(new AlphaFactory());
        // When game actions are successfully invoked,
        System.out.println("\n-- TRANSCRIPT OF EXAMPLE GAME --");
        game.playCard(2,0);
        game.endTurn();
        game.playCard(0,0);
        game.endTurn();
        game.attackCard(0,0);
        game.endTurn();
        game.usePower();
        game.attackHero(0);
        // Then those actions should be transcribed.
        System.out.println("-- END OF TRANSCRIPT --\n");
    }

}
