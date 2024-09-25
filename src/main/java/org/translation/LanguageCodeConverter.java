package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class provides the service of converting language codes to their names.
 */
public class LanguageCodeConverter {

    private Map codeToLanuage = new HashMap();
    private Map lanuageToCode = new HashMap();
    private int length;

    /**
     * Default constructor which will load the language codes from "language-codes.txt"
     * in the resources folder.
     */
    public LanguageCodeConverter() {
        this("language-codes.txt");
    }

    /**
     * Overloaded constructor which allows us to specify the filename to load the language code data from.
     *
     * @param filename the name of the file in the resources folder to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public LanguageCodeConverter(String filename) {

        try {
            List<String> lines = Files.readAllLines(Paths.get(getClass()
                    .getClassLoader().getResource(filename).toURI()));
            for (int i = 1; i < lines.size(); i++) {
                String[] arr = lines.get(i).split("\t");
                String language = arr[0];
                String code = arr[1];
                lanuageToCode.put(language, code);
                codeToLanuage.put(code, language);
                length++;
            }

        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Returns the name of the language for the given language code.
     *
     * @param code the language code
     * @return the name of the language corresponding to the code
     */
    public String fromLanguageCode(String code) {
        return (String) codeToLanuage.get(code);
    }

    /**
     * Returns the code of the language for the given language name.
     *
     * @param language the name of the language
     * @return the 2-letter code of the language
     */
    public String fromLanguage(String language) {
        return (String) lanuageToCode.get(language);
    }

    /**
     * Returns how many languages are included in this code converter.
     *
     * @return how many languages are included in this code converter.
     */
    public int getNumLanguages() {
        return length;
    }
}
