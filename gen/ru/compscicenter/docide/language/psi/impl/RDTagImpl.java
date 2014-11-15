// This is a generated file. Not intended for manual editing.
package ru.compscicenter.docide.language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static ru.compscicenter.docide.language.psi.RDTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import ru.compscicenter.docide.language.psi.*;

public class RDTagImpl extends ASTWrapperPsiElement implements RDTag {

  public RDTagImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RDVisitor) ((RDVisitor)visitor).visitTag(this);
    else super.accept(visitor);
  }

}
