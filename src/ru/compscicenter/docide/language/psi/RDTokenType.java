package ru.compscicenter.docide.language.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.docide.language.RDLanguage;

/**
 * @author oik77.
 */
public class RDTokenType extends IElementType {

    public RDTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RDLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "RDTokenType." + super.toString();
    }
}