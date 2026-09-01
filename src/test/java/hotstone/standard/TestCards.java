
package hotstone.standard;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCards {

    private Game game;
    private Player playerInTurn;

    @BeforeEach
    public void setUp() {
        game = new StandardHotStoneGame();
        playerInTurn = game.getPlayerInTurn();
    }

    @Test
    public void shouldHaveUnoAs111() {
        // Given an Uno card,
        Card uno = new StandardCard(GameConstants.UNO_CARD);
        // When we check the attributes,
        // It should have attributes (1,1,1).
        assertThat(uno.getManaCost(),   is(1));
        assertThat(uno.getAttack(),     is(1));
        assertThat(uno.getHealth(),     is(1));
    }

    @Test
    public void shouldHaveDosAs222() {
        // Given a Dos card,
        Card dos = new StandardCard(GameConstants.DOS_CARD);
        // When we check the attributes,
        // It should have attributes (2,2,2).
        assertThat(dos.getManaCost(),   is(2));
        assertThat(dos.getAttack(),     is(2));
        assertThat(dos.getHealth(),     is(2));
    }

    @Test
    public void shouldHaveTresAs333() {
        // Given a Tres card,
        Card dos = new StandardCard(GameConstants.TRES_CARD);
        // When we check the attributes,
        // It should have attributes (3,3,3).
        assertThat(dos.getManaCost(),   is(3));
        assertThat(dos.getAttack(),     is(3));
        assertThat(dos.getHealth(),     is(3));
    }

    @Test
    public void shouldHaveCuatroAs231() {
        // Given a Cuatro card,
        Card dos = new StandardCard(GameConstants.CUATRO_CARD);
        // When we check the attributes,
        // It should have attributes (2,3,1).
        assertThat(dos.getManaCost(),   is(2));
        assertThat(dos.getAttack(),     is(3));
        assertThat(dos.getHealth(),     is(1));
    }

    @Test
    public void shouldHaveCincoAs351() {
        // Given a Cinco card,
        Card dos = new StandardCard(GameConstants.CINCO_CARD);
        // When we check the attributes,
        // It should have attributes (3,5,1).
        assertThat(dos.getManaCost(),   is(3));
        assertThat(dos.getAttack(),     is(5));
        assertThat(dos.getHealth(),     is(1));
    }

    @Test
    public void shouldHaveSeisAs213() {
        // Given a Seis card,
        Card dos = new StandardCard(GameConstants.SEIS_CARD);
        // When we check the attributes,
        // It should have attributes (2,1,3).
        assertThat(dos.getManaCost(),   is(2));
        assertThat(dos.getAttack(),     is(1));
        assertThat(dos.getHealth(),     is(3));
    }

    @Test
    public void shouldHaveSieteAs324() {
        // Given a Siete card,
        Card dos = new StandardCard(GameConstants.SIETE_CARD);
        // When we check the attributes,
        // It should have attributes (3,2,4).
        assertThat(dos.getManaCost(),   is(3));
        assertThat(dos.getAttack(),     is(2));
        assertThat(dos.getHealth(),     is(4));
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
