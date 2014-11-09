package ru.compscicenter.docide.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

import java.util.Map;

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

        Map<String, DocumentMetaInfo> meta = scanner.scanDirectory(dirName);
        String message;
        if (meta == null) {
            message = "no such folder";
        } else {
            message = meta.get("test.md") == null ?
                    "test.md not found" :
                    meta.get("test.md").getMetaInf();
        }

        Messages.showMessageDialog(
                project,
                message,
                "test.md meta",
                Messages.getInformationIcon()
        );
    }
}
