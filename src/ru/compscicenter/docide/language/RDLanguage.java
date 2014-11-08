package ru.compscicenter.docide.language;

import com.intellij.lang.Language;

/**
 * @author oik77.
 */
public class RDLanguage extends Language {
    public static final RDLanguage INSTANCE = new RDLanguage();

    private RDLanguage() {
        super("RD");
    }
}
