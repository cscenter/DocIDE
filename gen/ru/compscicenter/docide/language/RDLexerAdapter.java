package ru.compscicenter.docide.language;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * @author oik77.
 */
public class RDLexerAdapter extends FlexAdapter {
    public RDLexerAdapter() {
        super(new RDLexer((Reader) null));
    }
}
