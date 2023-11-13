package com.generalpashon.calculator.rpn;

import com.generalpashon.calculator.token.Token;

public enum Operator {

    UNDEFINED     ("" , -1, null),

    ADD           ("+",  0, Double::sum),
    SUB           ("-",  0, (a, b) -> a - b),
    MUL           ("*",  1, (a, b) -> a * b),
    DIV           ("/",  1, (a, b) -> a / b),

    BEGIN_BRACKET ("(",  2, (a, b) -> a / b),
    END_BRACKET   (")", -1, (a, b) -> a / b);


    private final String text;
    public final int priority;
    private final OperatorFunc func;

    Operator(String text, int priority, OperatorFunc func) {
        this.text = text;
        this.priority = priority;
        this.func = func;
    }

    public double operateOn(double a, double b) {
        return func.operateOn(a, b);
    }

    public boolean matching(String input){
        return input.equals(text);
    }


    public static Operator fromToken(Token token) {
        for(Operator operator : values())
            if(operator.matching(token.text))
                return operator;

        return UNDEFINED;
    }

}
