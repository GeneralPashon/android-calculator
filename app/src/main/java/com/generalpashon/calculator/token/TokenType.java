package com.generalpashon.calculator.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TokenType {

    OPERATOR ("^[*/\\(\\)+\\-]{1}"),
    NUMBER   ("^[0-9]+");

    private final Pattern pattern;

    TokenType(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher matching(String input) {
        return pattern.matcher(input);
    }

}
