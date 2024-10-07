package gud.fun.junkdrawer.util.generator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NameGeneratorTest {

    @Test
    public void testGenerateFirstNameStartsWithCapital() {
        NameGenerator ng = new NameGenerator(6);
        String name = ng.generate();
        assertTrue(Character.isUpperCase(name.charAt(0)), "First character should be a capital letter");
    }

    @Test
    public void testGenerateFirstNameNoMoreThanTwoVowelsConsecutively() {
        NameGenerator ng = new NameGenerator(6);
        String name = ng.generate();
        int vowelCount = 0;
        for (char c : name.toCharArray()) {
            if (NameGenerator.getVowels().indexOf(c) != -1) {
                vowelCount++;
                assertTrue(vowelCount <= 2, "No more than two vowels should appear consecutively");
            } else {
                vowelCount = 0;
            }
        }
    }

    @Test
    public void testGenerateFirstNameNoMoreThanTwoConsonantsConsecutively() {
        NameGenerator ng = new NameGenerator(6);
        String name = ng.generate();
        int consonantCount = 0;
        for (char c : name.toCharArray()) {
            if (NameGenerator.getConsonants().indexOf(c) != -1) {
                consonantCount++;
                assertTrue(consonantCount <= 2, "No more than two consonants should appear consecutively");
            } else {
                consonantCount = 0;
            }
        }
    }

    @Test
    public void testGenerateFirstNameLength() {
        NameGenerator ng = new NameGenerator(6);
        String name = ng.generate();
        assertEquals(ng.getNameLength(), name.length(), "Generated name should have the correct length");
    }
}