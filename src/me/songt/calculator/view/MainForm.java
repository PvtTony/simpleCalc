package me.songt.calculator.view;

import me.songt.calculator.controller.CalculatorController;
import me.songt.calculator.model.Calculation;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Created by yst on 2016/4/9.
 * MainForm View & Controller
 */
public class MainForm
{
    private Calculation calculation = new Calculation();
    private CalculatorController controller = new CalculatorController();
    private JFrame mainFrame;
    private JPanel mainPanel;

    private JTextField textOut;

    private JButton btnBack;
    private JButton btnCE;
    private JButton btnC;

    private JButton btnAdd;
    private JButton btnMin;
    private JButton btnMul;
    private JButton btnDiv;
    private JButton btnSqrt;
    private JButton btnInv;
    private JButton btnPercentage;

    private JButton btnSwitch;
    private JButton btnPoint;

    private JButton btnMemClr;
    private JButton btnMemRec;
    private JButton btnMemSav;
    private JButton btnMemPls;
    private JLabel memStatus;

    private JButton[] btnDigits = new JButton[10];
    private final String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private JButton btnEql = new JButton("=");

    private boolean isOperationExecutedRecently = true;

    public MainForm()
    {
        EventQueue.invokeLater(() -> {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
            {
                ex.printStackTrace();
            }

        });

        setDefaultSize(48);
        this.draw();
        this.setEventListener();
    }

    private String getOutputText()
    {
        return this.textOut.getText();
    }

    private void setOutputText(String output)
    {
        this.textOut.setText(output);
    }

    private void addOutputText(String addText)
    {
        String text = this.textOut.getText();
        text += addText;
        this.setOutputText(text);
    }

    private void setEventListener()
    {

        for (int index = 0; index < 10; index++)
        {
            btnDigits[index].addActionListener(e -> {
                JButton eventButton = (JButton) e.getSource();
                if (textOut.getText().equals("0.0") || this.isOperationExecutedRecently)
                {
                    setOutputText(eventButton.getText());
                    isOperationExecutedRecently = false;
                }
                else
                {
                    this.addOutputText(eventButton.getText());
                }
            });
        }

        btnPoint.addActionListener(e -> {
            if (!this.getOutputText().equals("0.0") && !isPointExist(this.getOutputText()))
            {
                this.addOutputText(".");
            }
        });

        btnSwitch.addActionListener(e -> {
            if(!this.getOutputText().equals("0.0"))
            {
                String minus = "-";
                minus += this.getOutputText();
                this.setOutputText(minus);
            }
        });

        btnAdd.addActionListener(e -> {
            JButton eventButton = (JButton)e.getSource();
            this.doOperations(eventButton.getText());
        });

        btnMin.addActionListener(e -> {
            JButton eventButton = (JButton)e.getSource();
            this.doOperations(eventButton.getText());
        });

        btnMul.addActionListener(e -> {
            JButton eventButton = (JButton)e.getSource();
            this.doOperations(eventButton.getText());
        });

        btnDiv.addActionListener(e -> {
            JButton eventButton = (JButton)e.getSource();
            this.doOperations(eventButton.getText());
        });

        btnSqrt.addActionListener(e -> {
            double number = controller.parseExpr(this.getOutputText());
            number = Math.sqrt(number);
            this.setOutputText(Double.toString(number));
        });

        btnInv.addActionListener(e -> {
            double number = controller.parseExpr(this.getOutputText());
            number = 1 / number;
            this.setOutputText(Double.toString(number));
        });

        btnPercentage.addActionListener(e -> {
            double number = controller.parseExpr(this.getOutputText());
            number = number / 100;
            this.setOutputText(Double.toString(number));
        });

        btnMemSav.addActionListener(e -> {
            double number = controller.parseExpr(this.getOutputText());
            controller.memSave(number);
            memStatus.setText(String.valueOf(controller.memRecall()));
        });

        btnMemClr.addActionListener(e -> {
            controller.memClean();
            memStatus.setText("");
        });

        btnMemPls.addActionListener(e -> {
            double number = controller.parseExpr(this.getOutputText());
            controller.memPlus(number);
            memStatus.setText(String.valueOf(controller.memRecall()));
        });

        btnMemRec.addActionListener(e -> {
            this.setOutputText(String.valueOf(controller.memRecall()));
        });

        btnCE.addActionListener(e -> {
            calculation.cleanAll();
            this.setOutputText("0.0");
        });

        btnC.addActionListener(e -> {
            String currentData = this.getOutputText();
            if(currentData.equals("0.0"))
            {
                return;
            }
            this.setOutputText(currentData.substring(0, currentData.length() - 1));
            if(this.getOutputText().equals(""))
            {
                this.setOutputText("0.0");
            }


        });

        btnEql.addActionListener(e -> this.doEqual());
    }

    private void draw()
    {
        mainFrame = new JFrame("Calculator");

        mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel utilityPanel = new JPanel(new FlowLayout());
        JPanel calcPanel = new JPanel(new GridLayout(4, 6));

        textOut = new JTextField();
        textOut.setHorizontalAlignment(JTextField.RIGHT);
        textOut.setEditable(false);
        textOut.setText("0.0");
        btnBack = new JButton("← BackSpace");
        btnCE = new JButton("CE");
        btnC = new JButton("C");

        btnAdd = new JButton("+");
        btnMin = new JButton("-");
        btnMul = new JButton("*");
        btnDiv = new JButton("/");
        btnSqrt = new JButton("√");
        btnInv = new JButton("1/x");
        btnPercentage = new JButton("%");

        btnSwitch = new JButton("+/-");
        btnPoint = new JButton(".");

        btnMemClr = new JButton("MC");
        btnMemRec = new JButton("MR");
        btnMemSav = new JButton("MS");
        btnMemPls = new JButton("M+");
        memStatus = new JLabel();

        btnEql = new JButton("=");

        for (int index = 0; index < 10; index++)
        {
            btnDigits[index] = new JButton(digits[index]);
        }
        //Oh Jesus this is fucking stupid
        calcPanel.add(btnMemClr);
        calcPanel.add(btnDigits[7]);
        calcPanel.add(btnDigits[8]);
        calcPanel.add(btnDigits[9]);
        calcPanel.add(btnDiv);
        calcPanel.add(btnSqrt);
        calcPanel.add(btnMemRec);
        calcPanel.add(btnDigits[4]);
        calcPanel.add(btnDigits[5]);
        calcPanel.add(btnDigits[6]);
        calcPanel.add(btnMul);
        calcPanel.add(btnPercentage);
        calcPanel.add(btnMemSav);
        calcPanel.add(btnDigits[1]);
        calcPanel.add(btnDigits[2]);
        calcPanel.add(btnDigits[3]);
        calcPanel.add(btnMin);
        calcPanel.add(btnInv);
        calcPanel.add(btnMemPls);
        calcPanel.add(btnDigits[0]);
        calcPanel.add(btnSwitch);
        calcPanel.add(btnPoint);
        calcPanel.add(btnAdd);
        calcPanel.add(btnEql);
        //stupid too
        utilityPanel.add(memStatus);
        utilityPanel.add(btnBack);
        utilityPanel.add(btnCE);
        utilityPanel.add(btnC);


        buttonPanel.add(utilityPanel, BorderLayout.NORTH);
        buttonPanel.add(calcPanel, BorderLayout.SOUTH);

        mainPanel.add(textOut, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    public void show()
    {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }


    private static void setDefaultSize(int size)
    {

        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys)
        {

            if (key != null && key.toString().toLowerCase().contains("font"))
            {

//                System.out.println(key);
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null)
                {
                    font = font.deriveFont((float) size);
                    UIManager.put(key, font);
                }

            }

        }

    }

    private static boolean isPointExist(String number)
    {
        return number.contains(".");
    }


    private void doOperations(String operation)
    {
        if (calculation.isSymStackEmpty())
        {
            calculation.pushNumber(controller.parseExpr(this.textOut.getText()));
            calculation.pushSymbol(operation);

        }
        else if (calculation.countOfNumStack() == 1 && calculation.countOfSymStack() == 1 && !this.isOperationExecutedRecently)
        {
            calculation.pushNumber(controller.parseExpr(this.getOutputText()));
            double ans = calculation.calculate();
            this.setOutputText(Double.toString(ans));
            calculation.pushNumber(ans);
            calculation.pushSymbol(operation);
        }
        else if(calculation.countOfNumStack() == 1 && calculation.countOfSymStack() == 1 && this.isOperationExecutedRecently)
        {
            calculation.popSymbol();
            calculation.pushSymbol(operation);
        }

        this.isOperationExecutedRecently = true;

        System.out.println("Num Stack:" + calculation.countOfNumStack());
        System.out.println("Sym Stack:" + calculation.countOfSymStack());
    }

    private void doEqual()
    {
        calculation.pushNumber(controller.parseExpr(this.textOut.getText()));
        if(calculation.countOfNumStack() == 2 && calculation.countOfSymStack() == 1 )
        {
            double ans = calculation.calculate();
            this.textOut.setText(Double.toString(ans));
        }
        this.isOperationExecutedRecently = true;
        System.out.println("Num Stack:" + calculation.countOfNumStack());
        System.out.println("Sym Stack:" + calculation.countOfSymStack());
    }
}
