package ru.compscicenter.docide.editor;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.ide.structureView.StructureViewBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.event.DocumentAdapter;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.UserDataHolderBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiManager;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.compscicenter.docide.language.RDUtil;
import ru.compscicenter.docide.language.psi.RDProperty;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author oik77.
 */
public class RDPreviewEditor extends UserDataHolderBase implements FileEditor {

    /** The {@link java.awt.Component} used to render the HTML preview. */
    protected final JEditorPane jEditorPane = new JEditorPane();

    /** The {@link JBScrollPane} allowing to browse {@link #jEditorPane}. */
    protected final JBScrollPane scrollPane = new JBScrollPane(jEditorPane);

    /** The {@link Document} previewed in this editor. */
    protected final Document document;
    protected final Project project;

    /** Indicates whether the HTML preview is obsolete and should regenerated from the Markdown {@link #document}. */
    protected boolean previewIsObsolete = true;

    public RDPreviewEditor(@NotNull Project project, @NotNull Document document) {
        this.document = document;
        this.project = project;

        // Listen to the document modifications.
        this.document.addDocumentListener(new DocumentAdapter() {
            @Override
            public void documentChanged(DocumentEvent e) {
                previewIsObsolete = true;
            }
        });
    }

    /**
     * Get the {@link java.awt.Component} to display as this editor's UI.
     *
     * @return a scrollable {@link JEditorPane}.
     */
    @NotNull
    public JComponent getComponent() {
        return scrollPane;
    }

    /**
     * Get the component to be focused when the editor is opened.
     *
     * @return {@link #scrollPane}
     */
    @Nullable
    public JComponent getPreferredFocusedComponent() {
        return scrollPane;
    }

    /**
     * Get the editor displayable name.
     */
    @NotNull
    @NonNls
    public String getName() {
        return "Meta Info";
    }

    /**
     * Get the state of the editor.
     *
     * @param level the level.
     * @return {@link FileEditorState#INSTANCE}
     */
    @NotNull
    public FileEditorState getState(@NotNull FileEditorStateLevel level) {
        return FileEditorState.INSTANCE;
    }

    /**
     * Set the state of the editor.
     * <p/>
     * Does not do anything as {@link RDPreviewEditor} is stateless.
     *
     * @param state the new state.
     * @see #getState(com.intellij.openapi.fileEditor.FileEditorStateLevel)
     */
    public void setState(@NotNull FileEditorState state) {
    }

    /**
     * Indicates whether the document content is modified compared to its file.
     *
     * @return {@code false} as {@link RDPreviewEditor} is read-only.
     */
    public boolean isModified() {
        return false;
    }

    /**
     * Indicates whether the editor is valid.
     *
     * @return {@code true} if {@link #document} content is readable.
     */
    public boolean isValid() {
        return document.getText() != null;
    }

    private String propertiesToString(Collection<RDProperty> properties) {
        StringBuilder stringBuilder = new StringBuilder();

        properties
                .stream()
                .forEach(property ->
                        stringBuilder
                                .append(property.getKey())
                                .append(" = ")
                                .append(property.getValue())
                                .append("\n")
                );

        return stringBuilder.toString();
    }

    private String printReports(VirtualFile virtualFile) {
        return String.join("\n",
                RDUtil.getReports(project, virtualFile)
                        .stream()
                        .map(report -> RDUtil.createTable(project, report).toString())
                        .collect(Collectors.toList())
        );
    }

    /**
     * Invoked when the editor is selected.
     * <p/>
     */
    public void selectNotify() {
        VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(document);
        if (previewIsObsolete) {
            List<RDProperty> properties = RDUtil.findProperties(
                    project, virtualFile
                );

            try {

                jEditorPane.setText(
                        propertiesToString(properties) + '\n' +
                        "======================================\n" +
                        printReports(virtualFile)
                );

                previewIsObsolete = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Invoked when the editor is deselected.
     * <p/>
     * Does nothing.
     */
    public void deselectNotify() {
    }

    /**
     * Add specified listener.
     * <p/>
     * Does nothing.
     *
     * @param listener the listener.
     */
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    /**
     * Remove specified listener.
     * <p/>
     * Does nothing.
     *
     * @param listener the listener.
     */
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {
    }

    /**
     * Get the background editor highlighter.
     *
     * @return {@code null} as {@link RDPreviewEditor} does not require highlighting.
     */
    @Nullable
    public BackgroundEditorHighlighter getBackgroundHighlighter() {
        return null;
    }

    /**
     * Get the current location.
     *
     * @return {@code null} as {@link RDPreviewEditor} is not navigable.
     */
    @Nullable
    public FileEditorLocation getCurrentLocation() {
        return null;
    }

    /**
     * Get the structure view builder.
     *
     * @return parsing/PSI is not implemented.
     */
    @Nullable
    public StructureViewBuilder getStructureViewBuilder() {
        return null;
    }

    /** Dispose the editor. */
    public void dispose() {
        Disposer.dispose(this);
    }
}
