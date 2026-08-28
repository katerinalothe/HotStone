package hotstone.standard;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestMatcherDemonstrator {
    /** REMOVE ME. Not a test of HotStone, just an example of the
     matchers that the hamcrest library has... */
    @Test
    public void shouldDefinitelyBeRemoved() {
        // Matching null and not null values
        // 'is' require an exact match
        // Hamcrest uses the 'equals()' method
        String s = null;
        assertThat(s, is(nullValue()));
        s = "Ok";
        assertThat(s, is(notNullValue()));
        assertThat(s, is("Ok"));

        // If you only validate substrings, use containsString
        assertThat("This is a dummy test", containsString("dummy"));

        // You can use is on any type
        int answerToLifeUniverseAndEverything = 42;
        assertThat(answerToLifeUniverseAndEverything, is(42));

        // Match contents of Lists
        List<String> l = new ArrayList<String>();
        l.add("Bimse");
        l.add("Bumse");
        // Note - ordering is ignored when matching using hasItems
        assertThat(l, hasItems(new String[] {"Bumse","Bimse"}));

        // Matchers may be combined, like is-not
        assertThat(l.get(0), is(not("Bumse")));
    }
}
