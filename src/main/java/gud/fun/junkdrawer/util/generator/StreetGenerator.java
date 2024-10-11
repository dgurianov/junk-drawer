package gud.fun.junkdrawer.util.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreetGenerator implements JunkDataGenerator<String,String>{

    @Autowired
    private NameGenerator nameGenerator;

    @Override
    public String generateRandom() {
        return nameGenerator.generateRandom() + " str.";
    }

    @Override
    public String generateRandomByCriteria(String criteria) {
        return "";
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom();
    }

    @Override
    public String generateRandomAsStringByCriteria(String criteria) {
        return "";
    }

    public void setNameGenerator(NameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }
}
