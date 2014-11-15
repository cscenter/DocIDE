package ru.compscicenter.docide.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import ru.compscicenter.docide.language.psi.RDTypes;
import com.intellij.psi.TokenType;

%%

%class RDLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF= \n|\r|\r\n
WHITE_SPACE=[\ \t\f]
END_OF_LINE_COMMENT=("#")[^\r\n]*
AT=[@]
LBR=[\{]
RBR=[\}]
KEY=[:jletter:] [:jletterdigit:]*
VALUE=[:jletterdigit:]+

%state WAITING_KEY
%state WAITING_VALUE

%%

<YYINITIAL> {END_OF_LINE_COMMENT} { yybegin(YYINITIAL); return RDTypes.COMMENT; }

<WAITING_KEY> {KEY}     { yybegin(YYINITIAL); return RDTypes.KEY; }

<YYINITIAL> {AT}               { yybegin(WAITING_KEY); return RDTypes.AT; }

<YYINITIAL> {LBR}              { yybegin(WAITING_VALUE); return RDTypes.LBR;}
<YYINITIAL> {RBR}              { yybegin(YYINITIAL); return RDTypes.RBR;}

<WAITING_VALUE> {CRLF}         { yybegin(YYINITIAL); return RDTypes.CRLF; }

<WAITING_VALUE> {WHITE_SPACE}+ { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }

<WAITING_VALUE> {VALUE}   { yybegin(YYINITIAL); return RDTypes.VALUE; }

{CRLF}                         { yybegin(YYINITIAL); return RDTypes.CRLF; }

{WHITE_SPACE}+                 { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

.                              { return TokenType.BAD_CHARACTER; }