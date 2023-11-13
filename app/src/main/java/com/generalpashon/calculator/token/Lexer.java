package com.generalpashon.calculator.token;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class Lexer{

    private final List<Token> tokens;

    public Lexer() {
        this.tokens = new ArrayList<>();
    }

    public void tokenize(String expression) {
        tokens.clear();

        // Iterate symbols
        int i = 0;
        while(i < expression.length())
            i += tryToReadToken(expression.substring(i));
    }

    private int tryToReadToken(String expression) {
        for(TokenType tokenType: TokenType.values()) {
            // If not matching => try next token type
            final Matcher matcher = tokenType.matching(expression);
            if(!matcher.find())
                continue;

            // If matches => add to list
            tokens.add(new Token(tokenType, matcher.group()));
            return matcher.end(); // skip all token symbols
        }

        return 1; // try next symbol
    }

    public List<Token> getTokens() {
        return tokens;
    }

}
