package gud.fun.junkdrawer.util.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class StreetGeneratorTest {


    @Mock
    private NameGenerator mockNameGenerator;

    @InjectMocks
    private StreetGenerator streetGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateRandom() {
        Mockito.when(mockNameGenerator.generateRandom()).thenReturn("MockName");

        String streetName = streetGenerator.generateRandom();

        assertEquals("MockName str.", streetName, "Generated street name should be 'MockName str.'");
    }

    @Test
    void testGenerateRandomByCriteria() {
        Mockito.when(mockNameGenerator.generateRandom()).thenReturn("MockName");

        String streetName = streetGenerator.generateRandomByCriteria("Main");

        assertEquals("MockName Main str.", streetName, "Generated street name should be 'MockName Main str.'");
    }

    @Test
    void testGenerateRandomAsString() {
        Mockito.when(mockNameGenerator.generateRandom()).thenReturn("MockName");

        String streetName = streetGenerator.generateRandomAsString();

        assertEquals("MockName str.", streetName, "Generated street name should be 'MockName str.'");
    }

    @Test
    void testGenerateRandomAsStringByCriteria() {
        Mockito.when(mockNameGenerator.generateRandom()).thenReturn("MockName");

        String streetName = streetGenerator.generateRandomAsStringByCriteria("Main");

        assertEquals("MockName Main str.", streetName, "Generated street name should be 'MockName Main str.'");
    }
}