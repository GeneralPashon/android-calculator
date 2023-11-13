package com.generalpashon.calculator;

import com.generalpashon.calculator.rpn.ReversePolishNotation;
import com.generalpashon.calculator.token.Lexer;
import com.generalpashon.calculator.token.Token;

import java.util.List;

public class Calculator {

    private final Lexer lexer;
    private String input;

    public Calculator() {
        this.lexer = new Lexer();
        this.input = "";
    }


    public void inputChar(char keycode) {
        input += keycode;
    }

    public void inputString(String keycode) {
        input += keycode;
    }


    public String getResult() {
        lexer.tokenize(input);
        final List<Token> tokens = lexer.getTokens();
        return ReversePolishNotation.calculate(tokens);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input){
        this.input = input;
    }

}
