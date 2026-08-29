
package hotstone.standard;

import hotstone.framework.Card;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestCards {

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

    // Repeat these tests for the remaining cards.

}
