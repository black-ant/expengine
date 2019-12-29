package com.gang.engine.velocity;

/**
 * @Classname VelocityType
 * @Description TODO
 * @Date 2019/11/19 23:09
 * @Created by zengzg
 */
public enum VelocityType {

    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    LBRACKET("["),
    RBRACKET("]"),
    SEMI(";"),
    COMMA(","),
    QUOTATION_MARKS("`"),
    DOUBLE_QUOTATION_MARKS("\""),
    DOT("."),
    DOTDOT(".."),
    DOTDOTDOT("..,"),
    EQ("="),
    GT(">"),
    LT("<"),
    LT_SUB_GT("<->"),
    BANG("!"),
    BANGBANG("!!"),
    BANG_TILDE("!~"),
    BANG_TILDE_STAR("!~*"),
    TILDE("~"),
    TILDE_STAR("~*"),
    TILDE_EQ("~="),
    QUES("?"),
    QUESQUES("??"),
    QUESBAR("?|"),
    QUESAMP("?&"),
    COLON(":"),
    COLONCOLON("::"),
    COLONEQ(":="),
    EQEQ("=="),
    EQGT("=>"),
    LTEQ("<="),
    LTEQGT("<=>"),
    LTGT("<>"),
    GTEQ(">="),
    BANGEQ("!="),
    BANGGT("!>"),
    BANGLT("!<"),
    AMPAMP("&&"),
    BARBAR("||"),
    BARBARSLASH("||/"),
    BARSLASH("|/"),
    PLUS("+"),
    SUB("-"),
    SUBGT("->"),
    SUBGTGT("->>"),
    STAR("*"),
    SLASH("/"),
    AMP("&"),
    BAR("|"),
    CARET("^"),
    CARETEQ("^="),
    PERCENT("%"),
    LTLT("<<"),
    GTGT(">>"),
    MONKEYS_AT("@"),
    MONKEYS_AT_AT("@@"),
    POUND("#"),
    POUNDGT("#>"),
    POUNDGTGT("#>>"),
    MONKEYS_AT_GT("@>"),
    LT_MONKEYS_AT("<@");


    private String code;

    VelocityType(String code) {
        this.code = code;
    }
}
