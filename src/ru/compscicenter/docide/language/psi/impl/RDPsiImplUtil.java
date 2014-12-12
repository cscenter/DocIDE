package ru.compscicenter.docide.language.psi.impl;

import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import ru.compscicenter.docide.language.psi.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static String getKey(RDColumn element) {
        ASTNode valueNode = element.getNode().findChildByType(RDTypes.KEY);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(RDColumn element) {
        ASTNode valueNode = element.getNode().findChildByType(RDTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return getKey(element);
        }
    }

    public static String getKey(RDRestriction element) {
        ASTNode valueNode = element.getNode().findChildByType(RDTypes.KEY);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(RDRestriction element) {
        ASTNode valueNode = element.getNode().findChildByType(RDTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

}