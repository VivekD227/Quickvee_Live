package utilities;

import java.util.UUID;

public class DataGenerator {

    /**
     * Generates a random alphanumeric string for Category Names using UUID.
     * 
     * @param prefix An optional prefix. e.g. "Cat_"
     * @return Generated category name.
     */
    public static String generateRandomCategoryName(String prefix) {
        String randomSuffix = UUID.randomUUID().toString().substring(0, 8);
        if (prefix != null && !prefix.isEmpty()) {
            return prefix + randomSuffix;
        }
        return randomSuffix;
    }

}
