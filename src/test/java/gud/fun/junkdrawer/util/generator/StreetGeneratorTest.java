// src/test/java/gud/fun/junkdrawer/util/generator/StreetGeneratorTest.java
package gud.fun.junkdrawer.util.generator;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

public class StreetGeneratorTest {

    @Test
    public void testGenerateRandom() {
        // Mock the NameGenerator
        NameGenerator mockNameGenerator = Mockito.mock(NameGenerator.class);
        Mockito.when(mockNameGenerator.generateRandom()).thenReturn("MockName");

        // Create an instance of StreetGenerator and inject the mock
        StreetGenerator streetGenerator = new StreetGenerator();
        streetGenerator.setNameGenerator(mockNameGenerator);

        // Generate the street name
        String streetName = streetGenerator.generateRandom();

        // Verify the result
        assertEquals("MockName str.", streetName, "Generated street name should be 'MockName str.'");
    }
}