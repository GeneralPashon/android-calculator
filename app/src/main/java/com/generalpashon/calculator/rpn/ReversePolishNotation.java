package com.generalpashon.calculator.rpn;

import com.generalpashon.calculator.token.Token;
import com.generalpashon.calculator.token.TokenType;

import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {

    private static final Stack<Token> operators = new Stack<>();
    private static final Stack<Token> numbers = new Stack<>();

    public static String calculate(List<Token> tokens) {
        for(Token token : tokens)
            processToken(token);

        while (!operators.empty())
            calculateStack();

        return numbers.peek().text;
    }

    private static void processToken(Token token) {
        if(token.isBeginBracket()) {
            operators.push(token);

        }else if(token.isEndBracket()) {

            while(!operators.empty() && !operators.peek().isBeginBracket())
                calculateStack();

            if(operators.empty())
                throw new RuntimeException("Unexpected ')'");

            operators.pop();
        }else if(token.isOperator()) {

            if(operators.empty() || operators.peek().isBeginBracket()) {
                operators.push(token);
            }else{
                final Operator peekOperator = operators.peek().asOperator();
                final Operator operator = token.asOperator();

                if(operator.priority < peekOperator.priority)
                    calculateStack();

                operators.push(token);
            }
        }else {
            numbers.push(token);
        }
    }

    private static void calculateStack() {
        debugStep();

        final Operator operator = Operator.fromToken(operators.pop());

        final Token bToken = numbers.pop();
        final Token aToken = numbers.pop();

        final double a = Double.parseDouble(aToken.text);
        final double b = Double.parseDouble(bToken.text);
        final double result = operator.operateOn(a, b);

        final Token resultToken = new Token(TokenType.NUMBER, String.valueOf(result));

        numbers.push(resultToken);
    }


    private static int step = 1;

    private static void debugStep(){
        System.out.println("Step " + step + ":");
        step++;

        if(!operators.empty()){
            System.out.print("    ");
            for(Token token: operators)
                System.out.print(token.text + " ");
            System.out.println();
        }

        System.out.print("    ");
        for(Token token: numbers)
            System.out.print(token.text + " ");
        System.out.println();
    }

}
