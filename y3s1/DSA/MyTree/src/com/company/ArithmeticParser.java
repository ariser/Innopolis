package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArithmeticParser {
    private MyArrayTree<String> expressionTree;
    private List<MyArrayTree<String>> placeholderValues = new ArrayList<>();

    private String[] operators = {"+", "-", "*", "/"};
    private String numberPattern = "^\\s*-?\\d+(\\.\\d+)?\\s*$";
    private String placeholderPattern = "^\\{(\\d+)}$";
    private String expressionPattern = "^\\s*(\\d+(\\.\\d+)?|\\{(\\d+)})\\s*([+\\-*/]\\s*(\\d+(\\.\\d+)?|\\{(\\d+)})\\s*)*$";
    private String deepestParenthesesPatten = "\\(([^()]+)\\)";

    ArithmeticParser(String input) {
        if (input.contains("(")) {
            expressionTree = parseParentheses(input);
            placeholderValues = null; // no need for them anymore, GC
        } else {
            expressionTree = parse(input);
        }
    }

    private MyArrayTree<String> parse(String input) throws InternalError, IllegalArgumentException {
        String[] parts = null;
        String operator = null;

        for (String o : operators) {
            parts = input.split(Pattern.quote(o), 2);
            if (parts.length == 2) {
                operator = o;
                break;
            }
        }

        if (parts == null) {
            throw new InternalError("Operators not found");
        }

        // handle negative numbers at the left side of the expression
        if (parts.length == 2 && parts[0].isEmpty() && operator.equals("-")) {
            input = parts[1];
            for (String o : operators) {
                parts = input.split(Pattern.quote(o), 2);
                if (parts.length == 2) {
                    operator = o;
                    break;
                }
            }
            parts[0] = "-" + parts[0];
        }

        // recursion base case
        if (parts.length == 1) {
            // if input is a number, return a tree of height 1 with this number as root
            if (parts[0].matches(numberPattern)) {
                return new MyArrayTree<>(2, parts[0]);
            }
            // if input is a placeholder, retrieve it from cache and return
            if (parts[0].matches(placeholderPattern)) {
                Matcher m = Pattern.compile(placeholderPattern).matcher(parts[0]);
                if (m.find()) {
                    return placeholderValues.get(Integer.parseInt(m.group(1)));
                } else {
                    // if we are here then the Universe is most likely screwed
                    throw new InternalError("If you have a problem and you solve it with regular expression, you have two problems.");
                }
            }
            // in other cases the input string was malformed
            throw new IllegalArgumentException("Malformed expression.");
        }

        if (operator == null) {
            throw new IllegalArgumentException("Malformed expression.");
        }

        // handle negative numbers at the right side of the expression
        if (parts.length == 2 && operator.equals("-") && !parts[0].matches(numberPattern) && !parts[0].matches(expressionPattern)) {
            input = parts[0] + parts[1];
            for (String o : operators) {
                parts = input.split(Pattern.quote(o), 2);
                if (parts.length == 2) {
                    operator = o;
                    break;
                }
            }
            parts[1] = "-" + parts[1].trim();
        }

        String left = parts[0].trim();
        String right = parts[1].trim();

        MyArrayTree<String> subtree = new MyArrayTree<>(2, operator);
        subtree.getRoot().addSubtree(0, parse(left));
        subtree.getRoot().addSubtree(1, parse(right));

        return subtree;
    }

    private MyArrayTree<String> parseParentheses(String input) {
        Matcher m = Pattern.compile(deepestParenthesesPatten).matcher(input);
        int placeholderIndex = 0;
        while (m.find()) {
            placeholderValues.add(parse(m.group(1)));
            input = input.replaceFirst(deepestParenthesesPatten, "{" + placeholderIndex + "}");
            placeholderIndex++;
            m = Pattern.compile(deepestParenthesesPatten).matcher(input);
        }
        return parse(input);
    }

    public MyArrayTree<String> getExpressionTree() {
        return expressionTree;
    }

    public double calculate() {
        final Stack<Double> stack = new Stack<>();
        expressionTree.traversalPostorder(new Function<MyArrayTree<String>.Node, Object>() {
            @Override
            public Object apply(MyArrayTree.Node node) {
                double a, b;
                switch (node.getValue().toString()) {
                    case "*":
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b * a);
                        break;
                    case "/":
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b / a);
                        break;
                    case "+":
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b + a);
                        break;
                    case "-":
                        a = stack.pop();
                        b = stack.pop();
                        stack.push(b - a);
                        break;
                    default:
                        a = Double.parseDouble(node.getValue().toString());
                        stack.push(a);
                        break;
                }
                return null;
            }
        });
        return stack.pop();
    }
}
