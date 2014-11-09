package ru.compscicenter.docide.language.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.docide.language.RDLanguage;

/**
 * @author oik77.
 */
public class RDElementType extends IElementType {
    public RDElementType(@NotNull @NonNls String debugName) {
        super(debugName, RDLanguage.INSTANCE);
    }
}