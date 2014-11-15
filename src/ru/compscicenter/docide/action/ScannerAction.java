package ru.compscicenter.docide.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import ru.compscicenter.docide.language.RDUtil;
import ru.compscicenter.docide.language.psi.RDProperty;

import java.util.List;
import java.util.Map;

/**
 * @author oik77
 */
public class ScannerAction extends AnAction {


    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);
        String property = Messages.showInputDialog(
                project, "Property Name?", "Property", Messages.getQuestionIcon()
        );

        List<RDProperty> properties = RDUtil.findProperties(project, property);

        properties
                .stream()
                .forEach(prop ->
                        System.out.println(prop.getKey() + '-' + prop.getValue())
                );
    }
}
