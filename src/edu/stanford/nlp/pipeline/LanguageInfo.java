package edu.stanford.nlp.pipeline;

import java.lang.reflect.Field;
import java.util.*;

/**
 * This contains mappings from strings to language properties files
 */

public class LanguageInfo {

    /** languages supported **/
    public enum HumanLanguage {ARABIC, CHINESE, ENGLISH, FRENCH, GERMAN, SPANISH}

    /** list of properties files for each language **/
    public static final String ARABIC_PROPERTIES = "StanfordCoreNLP-arabic.properties";
    public static final String CHINESE_PROPERTIES = "StanfordCoreNLP-chinese.properties";
    public static final String ENGLISH_PROPERTIES = "StanfordCoreNLP.properties";
    public static final String FRENCH_PROPERTIES = "StanfordCoreNLP-french.properties";
    public static final String GERMAN_PROPERTIES = "StanfordCoreNLP-german.properties";
    public static final String SPANISH_PROPERTIES = "StanfordCoreNLP-spanish.properties";

    /** map enum to properties file **/
    public static final HashMap<HumanLanguage,String> languageToPropertiesFile;
    static {
        languageToPropertiesFile = new HashMap<HumanLanguage,String>();
        languageToPropertiesFile.put(HumanLanguage.ARABIC, ARABIC_PROPERTIES);
        languageToPropertiesFile.put(HumanLanguage.CHINESE, CHINESE_PROPERTIES);
        languageToPropertiesFile.put(HumanLanguage.ENGLISH, ENGLISH_PROPERTIES);
        languageToPropertiesFile.put(HumanLanguage.FRENCH, FRENCH_PROPERTIES);
        languageToPropertiesFile.put(HumanLanguage.GERMAN, GERMAN_PROPERTIES);
        languageToPropertiesFile.put(HumanLanguage.SPANISH, SPANISH_PROPERTIES);
    }

    private LanguageInfo() {
    }

    /**
     * Go through all of the paths via reflection, and print them out in a TSV format.
     * This is useful for command line scripts.
     *
     * @param args Ignored.
     */
    public static void main(String[] args) throws IllegalAccessException {
        for (Field field : LanguageInfo.class.getFields()) {
            System.out.println(field.getName() + "\t" + field.get(null));
        }
    }

    /** return the properties file name for a specific language **/
    public static String getLanguagePropertiesFile(String inputString) {
        return languageToPropertiesFile.get(getLanguageFromString(inputString));
    }

    /** convert various input strings to language enum **/
    public static HumanLanguage getLanguageFromString(String inputString) {
        if (inputString.toLowerCase().equals("arabic") || inputString.toLowerCase().equals("ar"))
            return HumanLanguage.ARABIC;
        if (inputString.toLowerCase().equals("english") || inputString.toLowerCase().equals("en"))
            return HumanLanguage.ENGLISH;
        if (inputString.toLowerCase().equals("chinese") || inputString.toLowerCase().equals("zh"))
            return HumanLanguage.CHINESE;
        if (inputString.toLowerCase().equals("french") || inputString.toLowerCase().equals("fr"))
            return HumanLanguage.FRENCH;
        if (inputString.toLowerCase().equals("german") || inputString.toLowerCase().equals("de"))
            return HumanLanguage.GERMAN;
        if (inputString.toLowerCase().equals("spanish") || inputString.toLowerCase().equals("es"))
            return HumanLanguage.SPANISH;
        else
            return null;
    }

    /** check if language is a segmenter language, return enum **/
    public static boolean isSegmenterLanguage(HumanLanguage language) {
        if (language == HumanLanguage.ARABIC || language == HumanLanguage.CHINESE)
            return true;
        else
            return false;
    }

    public static boolean isSegmenterLanguage(String inputString) {
        return isSegmenterLanguage(getLanguageFromString(inputString));
    }
}