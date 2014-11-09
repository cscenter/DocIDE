package ru.compscicenter.docide.language.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.docide.language.RDFileType;
import ru.compscicenter.docide.language.RDLanguage;

import javax.swing.*;

/**
 * @author oik77.
 */
public class RDFile extends PsiFileBase {
    public RDFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RDLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RDFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "RD File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}