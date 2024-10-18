package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        Stack<Character> stack = new Stack<>();
        for(char character : str.toCharArray()){
            if(character=='(' || character=='[' || character=='{'){
                stack.push(character);
            }
            if ((character==')' || character=='}' || character==']')) {
                if(!stack.empty() && 
                    ((stack.peek()=='(' && character==')') || 
                    (stack.peek()=='{' && character=='}') || 
                    (stack.peek()=='[' && character==']'))){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.empty();
    }

}
