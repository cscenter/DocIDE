package ru.compscicenter.docide.language.psi.impl;

import com.intellij.lang.ASTNode;
import ru.compscicenter.docide.language.psi.RDProperty;
import ru.compscicenter.docide.language.psi.RDTypes;

/**
 * @author oik77.
 */
public class RDPsiImplUtil {
    public static String getKey(RDProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(RDTypes.KEY);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(RDProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(RDTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }
}