package me.songt.calculator.controller;

/**
 * Created by yst on 2016/4/9.
 */
public class CalculatorController
{
    private double memNumber;
    public CalculatorController()
    {
        memNumber = 0.0;
    }

    public double parseExpr(String number)
    {
        try
        {
            return Double.parseDouble(number);
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return 0.0;
        }

    }

    public void memPlus(double num)
    {

    }

    public void memClean()
    {

    }
}
