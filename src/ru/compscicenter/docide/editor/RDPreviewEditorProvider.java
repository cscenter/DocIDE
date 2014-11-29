package ru.compscicenter.docide.editor;

import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.PossiblyDumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.docide.language.RDFileType;
import ru.compscicenter.docide.language.RDLanguage;

/**
 * @author oik77.
 */
public class RDPreviewEditorProvider implements FileEditorProvider, PossiblyDumbAware {

    /** The id of the editors provided by this {@link FileEditorProvider}. */
    public static final String EDITOR_TYPE_ID = "RD" + "PreviewEditor";

    /**
     * Check wether this {@link FileEditorProvider} can create a valid FileEditor for the file.
     *
     * @param project the project context.
     * @param file    the file to be tested for acceptance. This parameter must never be <code>null</code>.
     * @return whether the provider can create a valid editor for the specified <code>file</code>.
     */
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return file.getFileType() instanceof RDFileType;
    }

    /**
     * Create a valid editor for the specified file.
     * <p/>
     * Should be called only if the provider has accepted this file.
     *
     * @param project the project context.
     * @param file    the file for which an editor must be created.
     * @return an editor for the specified file.
     * @see #accept(com.intellij.openapi.project.Project, com.intellij.openapi.vfs.VirtualFile)
     */
    @NotNull
    public FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return new RDPreviewEditor(
                project,
                FileDocumentManager.getInstance().getDocument(file)
        );
    }

    /**
     * Dispose the specified <code>editor</code>.
     *
     * @param editor editor to be disposed. This parameter must not be <code>null</code>.
     */
    public void disposeEditor(@NotNull FileEditor editor) {
        editor.dispose();
    }

    /**
     * Deserialize state from the specified <code>sourceElemet</code>.
     * <p/>
     * Does not do anything as {@link RDPreviewEditor} is stateless.
     *
     * @param sourceElement the source element.
     * @param project       the project.
     * @param file          the file.
     * @return {@link FileEditorState#INSTANCE}
     * @see #writeState(com.intellij.openapi.fileEditor.FileEditorState, com.intellij.openapi.project.Project, org.jdom.Element)
     */
    @NotNull
    public FileEditorState readState(@NotNull Element sourceElement, @NotNull Project project, @NotNull VirtualFile file) {
        return FileEditorState.INSTANCE;
    }

    /**
     * Serialize state into the specified <code>targetElement</code>
     * <p/>
     * Does not do anything as {@link RDPreviewEditor} is stateless.
     *
     * @param state         the state to serialize.
     * @param project       the project.
     * @param targetElement the target element to serialize to.
     * @see #readState(org.jdom.Element, com.intellij.openapi.project.Project, com.intellij.openapi.vfs.VirtualFile)
     */
    public void writeState(@NotNull FileEditorState state, @NotNull Project project, @NotNull Element targetElement) {
    }

    /**
     * Get the id of the editors provided by this {@link FileEditorProvider}.
     *
     * @return {@link #EDITOR_TYPE_ID}
     */
    @NotNull
    public String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    /**
     * Get the {@link FileEditorPolicy} defining how to show editors created via the {@link FileEditorProvider}.
     *
     * @return {@link FileEditorPolicy#PLACE_AFTER_DEFAULT_EDITOR}
     */
    @NotNull
    public FileEditorPolicy getPolicy() {
        return FileEditorPolicy.PLACE_AFTER_DEFAULT_EDITOR;
    }

    /**
     * Indicates the editor can be created while background indexing is running.
     *
     * @return {@code true}
     */
    @Override
    public boolean isDumbAware() {
        return true;
    }
}
