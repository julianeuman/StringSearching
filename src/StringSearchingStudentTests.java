import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * String searching student tests.
 * @version 1.1
 */
public class StringSearchingStudentTests {
    private List<Integer> sellAnswer;
    private List<Integer> emptyList;
    private SearchableString sell;
    private SearchableString sellNotThere;
    private SearchableString sellText;

    private List<Integer> kmpAnswer;
    private SearchableString kmpPattern;
    private SearchableString kmpText;
    private SearchableString kmpNotThere;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        sell = new SearchableString("sell");
        sellNotThere = new SearchableString("sea lions trains cardinal "
                + "boardwalk");
        sellText = new SearchableString("She sells seashells");// by the seashore.");

        sellAnswer = new ArrayList<>();
        sellAnswer.add(4);

        emptyList = new ArrayList<>();

        kmpPattern = new SearchableString("ababa");
        kmpText = new SearchableString("ababaaababa");
        kmpNotThere = new SearchableString("ababbaba");

        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(0);
        kmpAnswer.add(6);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        int[] failureTable = StringSearching.buildFailureTable(kmpPattern);
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, failureTable);
        assertTrue("kmpPattern count was " + kmpPattern.getCount()
                + ". Should be <= 10", kmpPattern.getCount() <= 10);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPThere() {
        assertEquals(kmpAnswer, StringSearching.kmp(kmpPattern, kmpText));
        assertTrue("kmpText count was " + kmpText.getCount()
                + ". Should be <= 17", kmpText.getCount() <= 17);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNotThere() {
        assertEquals(emptyList, StringSearching.kmp(kmpPattern, kmpNotThere));
        assertTrue("kmpNotThere count was " + kmpNotThere.getCount()
                + ". Should be <= 6", kmpNotThere.getCount() <= 6);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        int[] lastTable = StringSearching.buildLastTable(sell);
        assertEquals(Character.MAX_VALUE + 1, lastTable.length);
        for (int i = 0; i < lastTable.length; i++) {
            switch (i) {
            case 's':
                assertEquals(0, lastTable[i]);
                break;
            case 'e':
                assertEquals(1, lastTable[i]);
                break;
            case 'l':
                assertEquals(3, lastTable[i]);
                break;
            default:
                assertEquals(-1, lastTable[i]);
                break;
            }
        }
        assertEquals(4, sell.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreThere() {
        assertEquals(sellAnswer, StringSearching.boyerMoore(sell, sellText));
        assertTrue("sellText count was " + sellText.getCount()
                + ". Should be <= 20.", sellText.getCount() <= 2);
    }

    @Test(timeout = TIMEOUT)
    public void testBoyerMooreNotThere() {
        assertEquals(emptyList, StringSearching.boyerMoore(sell, sellNotThere));
        assertTrue("sellNotThere count was " + sellNotThere.getCount()
                + ". Should be <= 9.", sellNotThere.getCount() <= 9);
    }

    @Test(timeout = TIMEOUT)
    public void testGenerateHash() {
        assertEquals(277220518, StringSearching.generateHash(
                    "matt is my friend", 4));
    }

    @Test(timeout = TIMEOUT)
    public void testUpdateHash() {
        assertEquals(731294060, StringSearching.updateHash(99342732, 5, 'a',
                    'q'));
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpThere() {
        assertEquals(sellAnswer, StringSearching.rabinKarp(sell, sellText));
        assertTrue("sellText count was " + sellText.getCount()
                + ". Should be <= 72.", sellText.getCount() <= 7);
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNotThere() {
        assertEquals(emptyList, StringSearching.rabinKarp(sell, sellNotThere));
        assertTrue("sellNotThere count was " + sellNotThere.getCount()
                + ". Should be <= 68.", sellNotThere.getCount() <= 68);
    }
}
