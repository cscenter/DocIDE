package ru.compscicenter.docide.language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author oik77.
 */
public class RDFileType extends LanguageFileType {
    public static final RDFileType INSTANCE = new RDFileType();

    private RDFileType() {
        super(RDLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "RD file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "RD language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "RD";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return RDIcons.FILE;
    }
}
