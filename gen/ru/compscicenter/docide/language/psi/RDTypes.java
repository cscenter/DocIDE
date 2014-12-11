// This is a generated file. Not intended for manual editing.
package ru.compscicenter.docide.language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import ru.compscicenter.docide.language.psi.impl.*;

public interface RDTypes {

  IElementType COLUMN = new RDElementType("COLUMN");
  IElementType PROPERTY = new RDElementType("PROPERTY");
  IElementType REPORT = new RDElementType("REPORT");
  IElementType RESTRICTION = new RDElementType("RESTRICTION");
  IElementType TAG = new RDElementType("TAG");

  IElementType AND = new RDTokenType("AND");
  IElementType AS = new RDTokenType("AS");
  IElementType AT = new RDTokenType("AT");
  IElementType COMMENT = new RDTokenType("COMMENT");
  IElementType CRLF = new RDTokenType("CRLF");
  IElementType EQ = new RDTokenType("EQ");
  IElementType EXEC = new RDTokenType("EXEC");
  IElementType KEY = new RDTokenType("KEY");
  IElementType LBR = new RDTokenType("LBR");
  IElementType RBR = new RDTokenType("RBR");
  IElementType SEP = new RDTokenType("SEP");
  IElementType VALUE = new RDTokenType("VALUE");
  IElementType WHERE = new RDTokenType("WHERE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == COLUMN) {
        return new RDColumnImpl(node);
      }
      else if (type == PROPERTY) {
        return new RDPropertyImpl(node);
      }
      else if (type == REPORT) {
        return new RDReportImpl(node);
      }
      else if (type == RESTRICTION) {
        return new RDRestrictionImpl(node);
      }
      else if (type == TAG) {
        return new RDTagImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
