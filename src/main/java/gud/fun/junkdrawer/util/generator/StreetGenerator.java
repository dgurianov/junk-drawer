package gud.fun.junkdrawer.util.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreetGenerator implements JunkDataGenerator<String>{

    @Autowired
    private NameGenerator nameGenerator;

    @Override
    public String generateRandom() {
        return nameGenerator.generateRandom() + " str.";
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom();
    }

    public void setNameGenerator(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }
}
