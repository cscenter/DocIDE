// This is a generated file. Not intended for manual editing.
package ru.compscicenter.docide.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.compscicenter.docide.language.psi.impl.*;

public interface RDTypes {

  IElementType PROPERTY = new RDElementType("PROPERTY");
  IElementType TAG = new RDElementType("TAG");

  IElementType AT = new RDTokenType("AT");
  IElementType COMMENT = new RDTokenType("COMMENT");
  IElementType CRLF = new RDTokenType("CRLF");
  IElementType KEY = new RDTokenType("KEY");
  IElementType LBR = new RDTokenType("LBR");
  IElementType RBR = new RDTokenType("RBR");
  IElementType VALUE = new RDTokenType("VALUE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == PROPERTY) {
        return new RDPropertyImpl(node);
      }
      else if (type == TAG) {
        return new RDTagImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
