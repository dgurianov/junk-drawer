package gud.fun.junkdrawer.util.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class  JsonFileReader<T> implements FileReader<T>{

    ObjectMapper objectMapper ;

    @Value("${junkdrawer.load.json}")
    private String filePathString;

    @Override
    public List<T> read() throws IOException {
        List<T> list = objectMapper.readValue(new File(filePathString), new TypeReference<List<T>>() {});
        return list;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
