import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator {
    // Method to add two numbers
    public double add(double num1, double num2) {
        return num1 + num2;
    }

    // Method to subtract two numbers
    public double subtract(double num1, double num2) {
        return num1 - num2;
    }

    // Method to multiply two numbers
    public double multiply(double num1, double num2) {
        return num1 * num2;
    }

    // Method to divide two numbers
    public double divide(double num1, double num2) {
        if (num2 == 0) {
            System.out.println ("Cannot divide by zero!");
            return Double.NaN; // NaN stands for "Not a Number"
        }
        return num1 / num2;
    }

    // Method to calculate power of a number
    public double power(double base, double exponent) {
        return Math.pow (base, exponent);
    }

    // Method to calculate square root of a number
    public double squareRoot(double num) {
        return Math.sqrt (num);

    }

    public int factorial(int num) {
        if (num < 0) {
            return -1; // Error for negative numbers
        }
        if (num == 0 || num == 1) {
            return 1;
        }
        int factorial = 1;
        for (int i = 2; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public double logarithm(double num) {
        return Math.log (num);
    }


    public class ScientificCalculatorGUI extends JFrame implements ActionListener {
        private final JTextField displayField;
        private double num1 = 0, num2 = 0, result = 0;
        private String operator = "";
        private Calculator calculator = new Calculator ( );

        public ScientificCalculatorGUI() {
            setTitle ("Scientific Calculator");
            setSize (400, 500);
            setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            setLayout (new BorderLayout ( ));

            displayField = new JTextField ( );
            displayField.setEditable (false);
            displayField.setFont (new Font ("Arial", Font.PLAIN, 44));
            add (displayField, BorderLayout.NORTH);

            JPanel buttonsPanel = new JPanel ( );
            buttonsPanel.setLayout (new GridLayout (6, 5, 5, 5));

            String[] buttons = {
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", ".", "=", "+",
                    "C", "sqrt", "pow", "back",
                    "log", "fact" // Added buttons for log and fact
            };

            for (String button : buttons) {
                JButton btn = new JButton (button);
                btn.setFont (new Font ("Arial", Font.PLAIN, 18));
                btn.addActionListener (this);
                buttonsPanel.add (btn);
            }

            add (buttonsPanel, BorderLayout.CENTER);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand ( );

            if (Character.isDigit (command.charAt (0)) || command.equals (".")) {
                displayField.setText (displayField.getText ( ) + command);
            } else if (command.equals ("C")) {
                displayField.setText ("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.equals ("back")) {
                String currentText = displayField.getText ( );
                if (!currentText.isEmpty ( )) {
                    displayField.setText (currentText.substring (0, currentText.length ( ) - 1));
                }
            } else if (command.equals ("=")) {
                num2 = Double.parseDouble (displayField.getText ( ));
                switch (operator) {
                    case "+":
                        result = calculator.add (num1, num2);
                        break;
                    case "-":
                        result = calculator.subtract (num1, num2);
                        break;
                    case "*":
                        result = calculator.multiply (num1, num2);
                        break;
                    case "/":
                        result = calculator.divide (num1, num2);
                        break;
                    case "pow":
                        result = calculator.power (num1, num2);
                        break;
                    case "sqrt":
                        result = calculator.squareRoot (num1);
                        break;
                    case "log":
                        result = calculator.logarithm (num1);
                        break;
                    case "fact":
                        result = calculator.factorial ((int) num1);
                        break;
                }
                displayField.setText (String.valueOf (result));
                num1 = result;
                operator = "";
            } else {
                if (!operator.isEmpty ( )) {
                    return;
                }
                num1 = Double.parseDouble (displayField.getText ( ));
                operator = command;
                displayField.setText ("");
            }
        }

        public void main(String[] args) {
            EventQueue.invokeLater (() -> {
                ScientificCalculatorGUI calculator = new ScientificCalculatorGUI ( );
                calculator.setVisible (true);
                calculator.setResizable (false);
                calculator.setLocationRelativeTo (null);
            });
        }
    }
}
