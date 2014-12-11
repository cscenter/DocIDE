// This is a generated file. Not intended for manual editing.
package ru.compscicenter.docide.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ru.compscicenter.docide.language.psi.RDTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RDParser implements PsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == COLUMN) {
      r = column(b, 0);
    }
    else if (t == PROPERTY) {
      r = property(b, 0);
    }
    else if (t == REPORT) {
      r = report(b, 0);
    }
    else if (t == RESTRICTION) {
      r = restriction(b, 0);
    }
    else if (t == TAG) {
      r = tag(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return RDFile(b, l + 1);
  }

  /* ********************************************************** */
  // item_*
  static boolean RDFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RDFile")) return false;
    int c = current_position_(b);
    while (true) {
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "RDFile", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // KEY AS VALUE
  public static boolean column(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "column")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY, AS, VALUE);
    exit_section_(b, m, COLUMN, r);
    return r;
  }

  /* ********************************************************** */
  // property|tag|COMMENT|CRLF|report
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property(b, l + 1);
    if (!r) r = tag(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    if (!r) r = report(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // AT KEY LBR VALUE RBR
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, KEY, LBR, VALUE, RBR);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  /* ********************************************************** */
  // EXEC column (SEP column)* (WHERE restriction (AND restriction)*)? RBR
  public static boolean report(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report")) return false;
    if (!nextTokenIs(b, EXEC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXEC);
    r = r && column(b, l + 1);
    r = r && report_2(b, l + 1);
    r = r && report_3(b, l + 1);
    r = r && consumeToken(b, RBR);
    exit_section_(b, m, REPORT, r);
    return r;
  }

  // (SEP column)*
  private static boolean report_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!report_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "report_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // SEP column
  private static boolean report_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEP);
    r = r && column(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (WHERE restriction (AND restriction)*)?
  private static boolean report_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_3")) return false;
    report_3_0(b, l + 1);
    return true;
  }

  // WHERE restriction (AND restriction)*
  private static boolean report_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, WHERE);
    r = r && restriction(b, l + 1);
    r = r && report_3_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (AND restriction)*
  private static boolean report_3_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_3_0_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!report_3_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "report_3_0_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // AND restriction
  private static boolean report_3_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "report_3_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AND);
    r = r && restriction(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // KEY EQ VALUE
  public static boolean restriction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "restriction")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, KEY, EQ, VALUE);
    exit_section_(b, m, RESTRICTION, r);
    return r;
  }

  /* ********************************************************** */
  // AT KEY
  public static boolean tag(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "tag")) return false;
    if (!nextTokenIs(b, AT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, AT, KEY);
    exit_section_(b, m, TAG, r);
    return r;
  }

}
