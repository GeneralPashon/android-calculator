package com.generalpashon.calculator.token;

import com.generalpashon.calculator.rpn.Operator;

public class Token {

    public final TokenType type;
    public final String text;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }


    public boolean isNumber() {
        return type == TokenType.NUMBER;
    }

    public boolean isOperator() {
        return type == TokenType.OPERATOR;
    }

    public boolean isBeginBracket() {
        return Operator.BEGIN_BRACKET.matching(text);
    }

    public boolean isEndBracket() {
        return Operator.END_BRACKET.matching(text);
    }


    public Operator asOperator() {
        return Operator.fromToken(this);
    }


    @Override
    public String toString() {
        return text;
    }

}
