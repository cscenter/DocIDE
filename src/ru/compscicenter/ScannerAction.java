package ru.compscicenter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author oik77
 */
public class ScannerAction extends AnAction {


    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        String dirName = Messages.showInputDialog(
                project, "Directory Name?", "Directory", Messages.getQuestionIcon()
        );
        MDScanner scanner = new MDScanner();

        DocumentMetaInfo meta = scanner.scanDirectory(dirName).get("test.md");
        Messages.showMessageDialog(
                project,
                meta.getMetaInf().toString(),
                "test.md meta",
                Messages.getInformationIcon()
        );
    }
}
