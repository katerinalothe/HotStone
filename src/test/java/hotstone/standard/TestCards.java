
package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.UIGame;
import hotstone.framework.Player;
import hotstone.standard.VariantFactories.AlphaFactory;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCards {

    private UIGame game;
    private Player playerInTurn;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame(new AlphaFactory());
        playerInTurn = game.getPlayerInTurn();
    }

    @Test
    public void shouldHaveUnoAs111() {
        // Given an uno card,
        Card uno = new StandardCard(GameConstants.UNO_CARD, 1,1,1);
        // When we check the attributes,
        // It should have attributes (1,1,1).
        assertThat(uno.getManaCost(),   is(1));
        assertThat(uno.getAttack(),     is(1));
        assertThat(uno.getHealth(),     is(1));
    }

    @Test
    public void shouldBeInactiveIfInHand() {
        // Given a card in hand,
        Card card = game.getCardInHand(playerInTurn, 0);
        // When we check its status,
        // Then it should be inactive
        assertThat(card.isActive(), is(false));
    }

}
