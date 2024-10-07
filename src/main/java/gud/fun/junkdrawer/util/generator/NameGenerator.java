package gud.fun.junkdrawer.util.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NameGenerator implements JunkDataGenerator<String> {

    private static final String VOWELS = "AEIOUaeiou";
    private static final String CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz";

    @Value("${generator.name.max-length:6}")
    private int maxLength; // You can adjust the length as needed

    private NameGenerator() {}

    public NameGenerator(int maxLength) {
        if (maxLength < 3) {
            throw new IllegalArgumentException("Name length must be at least 3 characters");
        }
         this.maxLength = maxLength;
    }

    @Override
    public String generateRandom() {
        Random random = new Random();
        StringBuilder firstName = new StringBuilder(maxLength);

        // Ensure the first character is a capital letter
        firstName.append((char) ('A' + random.nextInt(26)));

        int vowelCount = 0;
        int consonantCount = 0;

        for (int i = 1; i < maxLength; i++) {
            boolean isVowel = random.nextBoolean();
            if (vowelCount >= 2) {
                isVowel = false;
            } else if (consonantCount >= 2) {
                isVowel = true;
            }

            if (isVowel) {
                firstName.append(VOWELS.charAt(random.nextInt(5, 10)));
                vowelCount++;
                consonantCount = 0;
            } else {
                firstName.append(CONSONANTS.charAt(random.nextInt(21 ,CONSONANTS.length())));
                consonantCount++;
                vowelCount = 0;
            }
        }

        return firstName.toString();
    }

    @Override
    public String generateRandomAsString() {
        return generateRandom();
    }

    public static String getVowels() {
        return VOWELS;
    }

    public static String getConsonants() {
        return CONSONANTS;
    }

    public int getNameLength() {
        return this.maxLength;
    }
}