package ru.compscicenter;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author oik77
 */
public class ScanFile extends AnAction {


    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        String fileName = Messages.showInputDialog(
                project, "FileName?", "File name", Messages.getQuestionIcon()
        );
        MDScanner scanner = new MDScanner();
        DocumentMetaInfo meta = scanner.parseFile(fileName);
        System.out.println(meta.getMetaInf());
        Messages.showMessageDialog(
                project,
                meta.getMetaInf().toString(),
                "Information",
                Messages.getInformationIcon()
        );
    }
}
