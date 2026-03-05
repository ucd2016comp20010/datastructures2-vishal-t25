package project20280.stacksqueues;

import project20280.interfaces.Stack;

class BracketChecker {
    private final String input;

    public BracketChecker(String in) {
        input = in;
    }

    public void check() {
        // TODO
        Stack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            if (c == ')' || c == '}' || c == ']'){
                if (stack.isEmpty()){
                    System.out.println("not correct");
                    return;
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')){
                    System.out.println("not correct");
                    return;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("not correct");
            return;
        }
        System.out.println("correct");
    }

    public static void main(String[] args) {
        String[] inputs = {
                "[]]()()", // not correct
                "c[d]", // correct\n" +
                "a{b[c]d}e", // correct\n" +
                "a{b(c]d}e", // not correct; ] doesn't match (\n" +
                "a[b{c}d]e}", // not correct; nothing matches final }\n" +
                "a{b(c) ", // // not correct; Nothing matches opening {
        };

        for (String input : inputs) {
            BracketChecker checker = new BracketChecker(input);
            System.out.println("checking: " + input);
            checker.check();
        }
    }
}