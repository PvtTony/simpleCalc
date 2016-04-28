package me.songt.calculator.model;

import javax.swing.*;
import java.util.Stack;

/**
 * Created by yst on 2016/4/9.
 */
public class Calculation
{
    private Stack<Double> numStack = new Stack<>();
    private Stack<String> symStack = new Stack<>();

    public Calculation()
    {
        this.cleanNumStack();
        this.cleanSymStack();
    }

    public void cleanNumStack()
    {
        while (!this.isNumStackEmpty())
        {
            numStack.pop();
        }
    }

    public void cleanSymStack()
    {
        while (!this.isSymStackEmpty())
        {
            symStack.pop();
        }
    }

    public boolean isNumStackEmpty()
    {
        return this.numStack.empty();
    }

    public boolean isSymStackEmpty()
    {
        return this.symStack.empty();
    }

    public int countOfNumStack()
    {
        return numStack.size();
    }

    public int countOfSymStack()
    {
        return symStack.size();
    }

    public void pushNumber(double number)
    {
        this.numStack.push(number);
    }

    public void pushSymbol(String symbol)
    {
        if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/"))
        {
            this.symStack.push(symbol);
        }
    }

    public double popNumber()
    {
        return this.numStack.pop();
    }

    public String popSymbol()
    {
        return this.symStack.pop();
    }

    public double calculate()
    {
        double num2 = numStack.pop();
        double num1 = numStack.pop();
        String symbol = symStack.pop();
        if (symbol.equals("+"))
        {
            return num1 + num2;
        }
        else if (symbol.equals("-"))
        {
            return num1 - num2;
        }
        else if (symbol.equals("*"))
        {
            return num1 * num2;
        }
        else if (symbol.equals("/"))
        {
            if (num2 != 0.0)
            {
                return num1 / num2;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Unable to be divided by zero!", "Calculation Error", JOptionPane.ERROR_MESSAGE);
                return Double.POSITIVE_INFINITY;
            }
        }
        return 0.0;
    }

    public void cleanAll()
    {
        this.cleanNumStack();
        this.cleanSymStack();
    }

}
