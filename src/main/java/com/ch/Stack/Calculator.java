package com.ch.Stack;

public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String expression = "100+100/100*100";
        int result = calculator.toCalculator(expression);
        System.out.println(expression + "=" + result);
    }



    /**
     * 1. 通过一个 index  值（索引），来遍历我们的表达式
     * 2. 如果我们发现是一个数字, 就直接入数栈
     * 3. 如果发现扫描到是一个符号,  就分如下情况
     * 3. 如果发现当前的符号栈为 空，就直接入栈
     * 3. 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,
     *    在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
     * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
     * 5. 最后在数栈只有一个数字，就是表达式的结果
     */
    public int  toCalculator(String expression) {
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> operatorStack = new Stack<>();
        int index = 0; //指针
        String keepNum = ""; //拼接数字
        char ch;//将每次扫描得到char保存到ch

        while (index < expression.length()) {
            ch = expression.charAt(index);

            if (isOperator(ch)) {
                //遇到括号将括号内的内容提取出来计算，计算完成后压入数栈
                if (ch =='(') {
                    StringBuffer newExpression = new StringBuffer();
                    index++;//跳过'('
                    while (true) {
                        ch = expression.charAt(index);
                        //当碰到')'时退出
                        if (ch == ')') {
                            break;
                        }
                        newExpression.append(ch);
                        index++;
                    }
                    int result = toCalculator(newExpression.toString());
                    numStack.push(result);
                }else if (operatorStack.isEmpty()) {//如果发现当前的符号栈为 空，就直接入栈
                    operatorStack.push(ch);
                } else {
                    if (priority(ch) > priority(operatorStack.peek())) {//果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈
                        operatorStack.push(ch);
                    } else {
                        //如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,
                        // 在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        char operator = operatorStack.pop();
                        int result = cal(num1, num2, operator);
                        numStack.push(result);
                        operatorStack.push(ch);
                    }
                }
            }else {
                //拼接多位数
                keepNum += ch;
                //如果已经指向最后一个字符直接将数加入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else if (isOperator(expression.charAt(index + 1))) {
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = "";
                }

            }
            index++;
        }

        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        while (!operatorStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            char operator = operatorStack.pop();
            int result = cal(num1, num2, operator);
            numStack.push(result);
        }

        return numStack.pop();
    }

    public int cal(int num1, int num2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
        }
        return result;
    }


    public boolean isOperator(char operator) {
        if (operator == '+' || operator == '-' || operator == '*' || operator == '/' || operator == '(' || operator == ')') {
            return true;
        }
        return false;
    }

    //判断优先级
    public int priority(char operator) {
        if (operator == '+' || operator == '-') {
            return 0;
        }
        if (operator == '*' || operator == '/') {
            return 1;
        }
        return -1;
    }
}
