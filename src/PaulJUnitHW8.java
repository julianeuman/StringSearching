/* Last Updated Saturday 11-6-15 @ 12:53 pm */
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PaulJUnitHW8 {
    private SearchableString pattern;
    private SearchableString text;
    private SearchableString current;
    public static final int TIMEOUT = 200;

    // kmp

    // kmp exceptions

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException1() { // pattern is null
        text = new SearchableString("abc");
        pattern = null;
        StringSearching.kmp(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException2() { // pattern length 0
        text = new SearchableString("abc");
        pattern = new SearchableString("");
        StringSearching.kmp(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void kmpException3() { // text is null
        text = null;
        pattern = new SearchableString("abc");
        StringSearching.kmp(pattern, text);
    }

    @Test(timeout = TIMEOUT)
    public void kmpException4() { // text is of length 0 (does not throw exception)
        text = new SearchableString("");
        pattern = new SearchableString("abc");
        StringSearching.kmp(pattern, text);

        List<Integer> expected = new ArrayList<Integer>(); // list remains empty

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpException5() { // pattern is longer than text (does not throw exception)
        text = new SearchableString("a");
        pattern = new SearchableString("abc");
        StringSearching.kmp(pattern, text);

        List<Integer> expected = new ArrayList<Integer>(); // list remains empty

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch1() {
        text = new SearchableString("a");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch2() {
        text = new SearchableString("aa");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch3() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            expected.add(i);
        }

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch4() {
        text = new SearchableString("aa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch5() {
        text = new SearchableString("aaa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch6() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) { // matches found for index 0 - 8
            expected.add(i);
        }

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch7() {
        text = new SearchableString("abcabeaacabcababcabd"); //abcabeaacabcab[abcabd]
        pattern = new SearchableString("abcabd");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(14);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch8() {
        text = new SearchableString("abacbbabaababcaac");  // abacbbaba[ababca]ac
        pattern = new SearchableString("ababca");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch9() {
        text = new SearchableString("=^=_^^=^==^=^_==_");  // =^=_^^=^=[=^=^_=]=_
        pattern = new SearchableString("=^=^_=");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch10() {
        text = new SearchableString("abababab");
        pattern = new SearchableString("ab");

        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(4);
        expected.add(6);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch11() {
        text = new SearchableString("abababab");
        pattern = new SearchableString("abab");

        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(2);
        expected.add(4);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void kmpMatch12() {
        text = new SearchableString("ababbababbaba");
        pattern = new SearchableString("ababbaba");

        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(5);

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }

    // kmp no matches
    @Test(timeout = TIMEOUT)
    public void kmpNoMatch1() {
        text = new SearchableString("ABCDEFGH");
        pattern = new SearchableString("123");
        List<Integer> expected = new ArrayList<Integer>();

        assertEquals(expected, StringSearching.kmp(pattern, text));
    }


    // kmp counts

    @Test(timeout = TIMEOUT)
    public void kmpCount1() {
        text = new SearchableString("a");
        pattern = new SearchableString("a");
        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() == 1);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount2() {
        text = new SearchableString("aa");
        pattern = new SearchableString("aa");

        StringSearching.kmp(pattern, text);
        assertTrue(text.getCount() == 2);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount3() {
        text = new SearchableString("abcabeaacabcababcabd"); //abcabeaacabcab[abcabd]
        pattern = new SearchableString("abcabd");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 26);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount4() {
        text = new SearchableString("abacbbabaababcaac");  // abacbbaba[ababca]ac
        pattern = new SearchableString("ababca");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 19);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount5() {
        text = new SearchableString("abababab");
        pattern = new SearchableString("ab");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 11);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount6() {
        text = new SearchableString("abababab");
        pattern = new SearchableString("abab");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 10);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount7() {
        text = new SearchableString("ABCDEFGH");
        pattern = new SearchableString("123");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 6);
    }

    @Test(timeout = TIMEOUT)
    public void kmpCount8() {
        text = new SearchableString("ababbababbaba");
        pattern = new SearchableString("ababbaba");

        StringSearching.kmp(pattern, text);

        assertTrue(text.getCount() <= 14);
    }

    // buildFailureTable


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void failureTableException() { // pattern is null
        pattern = null;
        int[] failureTable = StringSearching.buildFailureTable(pattern);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable1() {
        pattern = new SearchableString("a");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() == 0);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable2() {
        pattern = new SearchableString("ab");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() == 2);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable3() {
        pattern = new SearchableString("aa");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() == 2);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable4() {
        pattern = new SearchableString("abacab");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 1, 0, 1, 2};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 12);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable5() {
        pattern = new SearchableString("ababac");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 1, 2, 3, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 12);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable6() {
        pattern = new SearchableString("ababaa");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 1, 2, 3, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 12);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable7() {
        pattern = new SearchableString("aaaaaa");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 1, 2, 3, 4, 5};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 10);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable8() {
        pattern = new SearchableString("aacacc");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 1, 0, 1, 0, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 14);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable9() {
        pattern = new SearchableString("abcdef");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 10);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable10() {
        pattern = new SearchableString("abababc");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 1, 2, 3, 4, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 14);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable11() {
        pattern = new SearchableString("abcdabca");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 0, 0, 1, 2, 3, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 16);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable12() {
        pattern = new SearchableString("aabaababb");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 1, 0, 1, 2, 3, 4, 0, 0};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 20);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable13() {
        pattern = new SearchableString("aabaabaaa");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 1, 0, 1, 2, 3, 4, 5, 1};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 20);
    }

    @Test(timeout = TIMEOUT)
    public void failureTable14() {
        pattern = new SearchableString("ababcababc");
        int[] failureTable = StringSearching.buildFailureTable(pattern);
        int[] expected = {0, 0, 1, 2, 0, 1, 2, 3, 4, 5};
        assertArrayEquals(expected, failureTable);
        assertTrue(pattern.getCount() <= 20);
    }

    // boyerMoore

    // boyerMoore exceptions

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException1() { // pattern is null
        text = new SearchableString("abc");
        pattern = null;
        StringSearching.boyerMoore(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException2() { // pattern length 0
        text = new SearchableString("abc");
        pattern = new SearchableString("");
        StringSearching.boyerMoore(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void boyerMooreException3() { // text is null
        text = null;
        pattern = new SearchableString("abc");
        StringSearching.boyerMoore(pattern, text);
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreException4() { // text is of length 0 (does not throw exception)
        text = new SearchableString("");
        pattern = new SearchableString("abc");
        StringSearching.boyerMoore(pattern, text);

        List<Integer> expected = new ArrayList<Integer>(); // list remains empty

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreException5() { // pattern is longer than text (does not throw exception)
        text = new SearchableString("a");
        pattern = new SearchableString("abc");
        StringSearching.boyerMoore(pattern, text);

        List<Integer> expected = new ArrayList<Integer>(); // list remains empty

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch1() {
        text = new SearchableString("a");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch2() {
        text = new SearchableString("aa");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch3() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            expected.add(i);
        }

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch4() {
        text = new SearchableString("aa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch5() {
        text = new SearchableString("aaa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch6() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) { // matches found for index 0 - 8
            expected.add(i);
        }

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch7() {
        text = new SearchableString("abcabeaacabcababcabd"); //abcabeaacabcab[abcabd]
        pattern = new SearchableString("abcabd");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(14);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch8() {
        text = new SearchableString("abacbbabaababcaac");  // abacbbaba[ababca]ac
        pattern = new SearchableString("ababca");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void boyerMooreMatch9() {
        text = new SearchableString("=^=_^^=^==^=^_==_");  // =^=_^^=^=[=^=^_=]=_
        pattern = new SearchableString("=^=^_=");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.boyerMoore(pattern, text));
    }

    // boyer moore count
    @Test(timeout = TIMEOUT)
    public void boyerMooreCount1() {
        text = new SearchableString("my name is jonathan");
        pattern = new SearchableString("jonathan");
        StringSearching.boyerMoore(pattern, text);

        assertTrue(text.getCount() <= 10);
    }


    // buildLastTable
    @Test(timeout = TIMEOUT)
    public void buildLastTable1() {
        pattern = new SearchableString("ilikegreeneggsandham");
        int[] lastTable = StringSearching.buildLastTable(pattern);
        assertEquals(Character.MAX_VALUE + 1, lastTable.length);
        for (int i = 0; i < lastTable.length; i++) {
            switch (i) {
                case 'a':
                    assertEquals(18, lastTable[i]);
                    break;
                case 'd':
                    assertEquals(16, lastTable[i]);
                    break;
                case 'e':
                    assertEquals(10, lastTable[i]);
                    break;
                case 'g':
                    assertEquals(12, lastTable[i]);
                    break;
                case 'h':
                    assertEquals(17, lastTable[i]);
                    break;
                case 'i':
                    assertEquals(2, lastTable[i]);
                    break;
                case 'k':
                    assertEquals(3, lastTable[i]);
                    break;
                case 'l':
                    assertEquals(1, lastTable[i]);
                    break;
                case 'm':
                    assertEquals(19, lastTable[i]);
                    break;
                case 'n':
                    assertEquals(15, lastTable[i]);
                    break;
                case 'r':
                    assertEquals(6, lastTable[i]);
                    break;
                case 's':
                    assertEquals(13, lastTable[i]);
                    break;
                default:
                    assertEquals(-1, lastTable[i]);
                    break;
            }
        }
        assertEquals(20, pattern.getCount());
    }

    // rabinKarp

    // rabinKarp exceptions

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException1() { // pattern is null
        text = new SearchableString("abc");
        pattern = null;
        StringSearching.rabinKarp(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException2() { // pattern length 0
        text = new SearchableString("abc");
        pattern = new SearchableString("");
        StringSearching.rabinKarp(pattern, text);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void rabinKarpException3() { // text is null
        text = null;
        pattern = new SearchableString("abc");
        StringSearching.rabinKarp(pattern, text);
    }

    // rabin karp matches

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch1() {
        text = new SearchableString("a");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch2() {
        text = new SearchableString("aa");
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch3() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("a");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            expected.add(i);
        }

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch4() {
        text = new SearchableString("aa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch5() {
        text = new SearchableString("aaa");
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(0);
        expected.add(1);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch6() {
        text = new SearchableString("aaaaaaaaaa"); // 10 a's
        pattern = new SearchableString("aa");
        List<Integer> expected = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) { // matches found for index 0 - 8
            expected.add(i);
        }

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch7() {
        text = new SearchableString("abcabeaacabcababcabd"); //abcabeaacabcab[abcabd]
        pattern = new SearchableString("abcabd");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(14);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch8() {
        text = new SearchableString("abacbbabaababcaac");  // abacbbaba[ababca]ac
        pattern = new SearchableString("ababca");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    @Test(timeout = TIMEOUT)
    public void rabinKarpMatch9() {
        text = new SearchableString("=^=_^^=^==^=^_==_");  // =^=_^^=^=[=^=^_=]=_
        pattern = new SearchableString("=^=^_=");
        List<Integer> expected = new ArrayList<Integer>();
        expected.add(9);

        assertEquals(expected, StringSearching.rabinKarp(pattern, text));
    }

    // generateHash

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void generateHashException1() { // current is null
        current = null;
        StringSearching.generateHash(current, 5);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void generateHashException2() { // length is negative
        current = new SearchableString("hello");
        StringSearching.generateHash(current, -5);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void generateHashException3() { // length is greater than length of current
        current = new SearchableString("hello");
        StringSearching.generateHash(current, 6);
    }

    @Test(timeout = TIMEOUT)
    public void generateHash1() {
        current = new SearchableString("hello");
        assertEquals(0, StringSearching.generateHash(current, 0));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash2() {
        current = new SearchableString("hello");
        assertEquals(104, StringSearching.generateHash(current, 1));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash3() {
        current = new SearchableString("hello");
        assertEquals(45133, StringSearching.generateHash(current, 2));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash4() {
        current = new SearchableString("hello");
        assertEquals(19542697, StringSearching.generateHash(current, 3));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash5() {
        current = new SearchableString("bunny");
        assertEquals(98, StringSearching.generateHash(current, 1));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash6() {
        current = new SearchableString("bunny");
        assertEquals(42551, StringSearching.generateHash(current, 2));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash7() {
        current = new SearchableString("bunny");
        assertEquals(18424693, StringSearching.generateHash(current, 3));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash8() {
        current = new SearchableString("! 6");  // testing weird characters
        assertEquals(6201047, StringSearching.generateHash(current, 3));
    }

    @Test(timeout = TIMEOUT)
    public void generateHash9() {
        current = new SearchableString("~P>"); // testing weird characters
        assertEquals(23658316, StringSearching.generateHash(current, 3));
    }

    // updateHash
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void updateHashException1() {
        pattern = new SearchableString("hello");
        StringSearching.updateHash(100, -5, 'a', 'b');
    }

    @Test(timeout = TIMEOUT)
    public void updateHash1() {
        pattern = new SearchableString("hello"); // [h]ello -> h[e]llo
        assertEquals(101, StringSearching.updateHash(104, 1, 'h', 'e'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash2() {
        pattern = new SearchableString("hello"); // [h[e]llo -> he[l]lo
        assertEquals(108, StringSearching.updateHash(101, 1, 'e', 'l'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash3() {
        pattern = new SearchableString("hello"); // he[l]lo -> hel[l]o
        assertEquals(108, StringSearching.updateHash(108, 1, 'l', 'l'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash4() {
        pattern = new SearchableString("hello"); // hel[l]o -> hell[o]
        assertEquals(111, StringSearching.updateHash(108, 1, 'l', 'o'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash5() {
        pattern = new SearchableString("hello"); // [he]llo -> h[el]lo
        assertEquals(43841, StringSearching.updateHash(45133, 2, 'h', 'l'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash6() {
        pattern = new SearchableString("hello"); // h[el]lo -> he[ll]o
        assertEquals(46872, StringSearching.updateHash(43841, 2, 'e', 'l'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash7() {
        pattern = new SearchableString("hello"); // he[ll]o -> hel[lo]
        assertEquals(46875, StringSearching.updateHash(46872, 2, 'l', 'o'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash8() {
        pattern = new SearchableString("hello"); // [hel]lo -> h[ell]o
        assertEquals(18983261, StringSearching.updateHash(19542697, 3, 'h', 'l'));
    }

    @Test(timeout = TIMEOUT)
    public void updateHash9() {
        pattern = new SearchableString("hello"); // h[ell]o -> he[llo]
        assertEquals(20295687, StringSearching.updateHash(18983261, 3, 'e', 'o'));
    }

    // BONUS: hard tests below

    /**
     * test will take about 10 seconds to run for kmp, 5 minutes for boyer moore, 4 minutes for boyer moore
     *
     *
     * text is a bunch of repeating a's
     *
     * example: if text = "aaaaa", then it tests kmp(text, "a"), kmp(text, "aa"),
     *          kmp(text, "aaa"), kmp(text, "aaaa"), and kmp (text, "aaaaa")
     *
     * text length goes up to 1000 (1000 a's)
     *
     */
/*
    @Test()
    public void hardTestMatch1() {
        int maximumTextLength = 1000;
        List<Integer> expected;
        String textString = "";
        for (int i = 0; i < maximumTextLength; i++) {
            textString += "a";
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j++) {
                patternString += "a";
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
                for (int k = 0; k <= textString.length() - patternString.length(); k++) {
                    expected.add(k);
                }
                //assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }
*/
    /**
     * test will take about 15 seconds to run for kmp, 11 minutes for boyer moore, 11 minutes for rabin karp
     *
     *
     * text is a bunch of repeating ab's
     *
     * example: if text = "ababab", then it tests kmp(text, "ab"), kmp(text, "abab"),
     *          kmp(text, "ababab")
     *
     * text length goes up to 2000 (1000 ab's)
     *
     */
/*
    @Test()
    public void hardTestMatch2() {
        int repeat = 1000;
        List<Integer> expected;
        String textString = "";
        
        for (int i = 0; i < repeat; i++) {
            textString += "ab";
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j += 2) {
                patternString += "ab";
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
                for (int k = 0; k <= textString.length() - patternString.length(); k+=2) {
                    expected.add(k);
                }
                assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                //assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }    
*/
    /**
     * test will take about 5 seconds to run for kmp, 70 seconds for boyer moore, 2 mintues for rabin karp
     *
     * text is a char sequence from "a" to "abcdefghijklmnopqrstuvwxyzabc...." (1000 letters long)
     *
     */
/*
    @Test()
    public void hardTestMatch3() {
        String[] alphabet = {
                            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                            "u", "v", "w", "x", "y", "z"
                            };
        int maximumTextLength = 1000;
        List<Integer> expected;
        String textString = "";
        for (int i = 0; i < maximumTextLength; i++) {
            textString += alphabet[i % alphabet.length];
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j++) {
                patternString += alphabet[j % alphabet.length];
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
                for (int k = 0; k <= textString.length() - patternString.length(); k+= alphabet.length) {
                    expected.add(k);
                }
                //assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }
*/
    /**
     * test that will take about 10 seconds to run for kmp, 1 minute for boyer moore, 2 minutes for rabin karp
     *
     * text is a bunch of a's
     *
     * pattern is a bunch of b's
     *
     * there should be no matches
     *
     */
/*
    @Test()
    public void hardTestNoMatches1() {
        int maximumTextLength = 1000;
        List<Integer> expected;
        String textString = "";
        for (int i = 0; i < maximumTextLength; i++) {
            textString += "a";
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j++) {
                patternString += "b";
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
 
                //assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }
*/
    /**
     * test that will take about 10 seconds to run for kmp, 1 minute for boyer moore, 2 minutes for rabin karp
     *
     * text is a bunch of a's
     *
     * pattern is a bunch of b's
     *
     * there should be no matches
     *
     */
/*
    @Test()
    public void hardTestNoMatches1() {
        int maximumTextLength = 1000;
        List<Integer> expected;
        String textString = "";
        for (int i = 0; i < maximumTextLength; i++) {
            textString += "a";
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j++) {
                patternString += "b";
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
 
                //assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }
*/
    /**
     * test that will take about 2 seconds to run for kmp, 1 minute for boyer moore, 2 minutes for rabin karp
     *
     * text is a bunch of letters
     *
     * pattern is a bunch number, symbols, and capital letters
     *
     * there should be no matches
     *
     */
/*
    @Test()
    public void hardTestNoMatch2() {
        String[] alphabet = {
                            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                            "u", "v", "w", "x", "y", "z"
                            };
        String[] other = {
                            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                            ")", "!", "@", "#", "$", "%", "^", "&", "*", "(",
                            "-", "_", "+", "=", "[", "{", "]", "}", "|", ";",
                            ":", "'", ",", "<", ".", ">", "/", "?", "A", "B",
                            "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                            "W", "X", "Y", "Z"
 
                          };                    
        int maximumTextLength = 1000;
        List<Integer> expected;
        String textString = "";
        for (int i = 0; i < maximumTextLength; i++) {
            textString += alphabet[i % alphabet.length];
            text = new SearchableString(textString);
            String patternString = "";
            for (int j = 0; j < textString.length(); j++) {
                patternString += other[j % other.length];
                pattern = new SearchableString(patternString);
                expected = new ArrayList<Integer>();
                
                //assertEquals(expected, StringSearching.kmp(pattern, text));
                //assertEquals(expected, StringSearching.boyerMoore(pattern, text));
                assertEquals(expected, StringSearching.rabinKarp(pattern, text));
            }
        }
    }
*/
}