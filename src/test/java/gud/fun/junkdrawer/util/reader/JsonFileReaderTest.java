// src/test/java/gud/fun/junkdrawer/util/reader/JsonFileReaderTest.java
package gud.fun.junkdrawer.util.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JsonFileReaderTest {

    private JsonFileReader<String> jsonFileReader;
    private ObjectMapper mockObjectMapper;

    @BeforeEach
    public void setUp() {
        jsonFileReader = new JsonFileReader<>();
        mockObjectMapper = Mockito.mock(ObjectMapper.class);
        jsonFileReader.setObjectMapper(mockObjectMapper);
        ReflectionTestUtils.setField(jsonFileReader, "filePathString", "test.json");
    }

    @Test
    public void testRead() throws IOException {
        List<String> expectedList = Arrays.asList("item1", "item2", "item3");
        when(mockObjectMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(expectedList);

        List<String> resultList = jsonFileReader.read();

        assertEquals(expectedList, resultList, "The read method should return the expected list");
    }

    @Test
    public void testReadFileNotFound() throws IOException {
        when(mockObjectMapper.readValue(any(File.class), any(TypeReference.class))).thenThrow(new IOException("File not found"));

        assertThrows(IOException.class, () -> jsonFileReader.read(), "Expected read() to throw, but it didn't");
    }
}