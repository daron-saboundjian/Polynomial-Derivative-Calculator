import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener{

    JButton submit;
    String equation;
    int count;
    JTextField equationInput;
    JTextField countInput;
    Calc calc;

    Frame() {
        this.setTitle("Polynomial Derivative Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520, 500);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JLabel welcome = new JLabel("Welcome to the Polynomial Derivative Calculator");
        welcome.setForeground(Color.GREEN);
        welcome.setFont(new Font(null, Font.BOLD, 20));
        welcome.setBounds(10, -100, 500, 250);
        
        JTextArea instructions = new JTextArea("\nRules:\n• Only input polynomials (however, decimal exponents are permitted)\n• Do not add spaces\n• Do not use parentheses\n• Use '^' to express exponents\n• Express fractions as decimal values\n• Use 'x' for independent variable\n• EXAMPLE VALID INPUT: 8x^3+1.5x^2-x^1.5+3x");
        instructions.setBackground(Color.BLACK);
        instructions.setForeground(Color.GREEN);
        instructions.setFont(new Font(null, Font.PLAIN, 15));
        instructions.setEditable(false);
        instructions.setFocusable(false);
        instructions.setBounds(20, 75, 450, 200);

        JTextArea equationLabel = new JTextArea("Input Function:");
        equationLabel.setBackground(Color.BLACK);
        equationLabel.setForeground(Color.GREEN);
        equationLabel.setEditable(false);
        equationLabel.setFocusable(false);
        equationLabel.setFont(new Font(null, Font.PLAIN, 15));
        equationLabel.setBounds(50, 270, 100, 20);

        JTextArea countLabel = new JTextArea("Enter Derivative Order:");
        countLabel.setBackground(Color.BLACK);
        countLabel.setForeground(Color.GREEN);
        countLabel.setEditable(false);
        countLabel.setFocusable(false);
        countLabel.setFont(new Font(null, Font.PLAIN, 15));
        countLabel.setBounds(200, 270, 200, 20);
        
        equationInput = new JTextField();
        equationInput.setBounds(50, 300, 100, 20);
        equationInput.setFont(new Font(null, Font.BOLD, 12));

        countInput = new JTextField();
        countInput.setBounds(200, 300, 50, 20);
        countInput.setFont(new Font(null, Font.BOLD, 12));

        submit = new JButton("Submit");
        submit.setBounds(50, 350, 100, 20);
        submit.setFocusable(false);
        submit.addActionListener(this);

        this.add(submit);
        this.add(countLabel);
        this.add(equationLabel);
        this.add(countInput);
        this.add(equationInput);
        this.add(welcome);
        this.add(instructions);
        this.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //If expection is thrown, then prompt user with error popup
        if(e.getSource()==submit) {
            try {
                equation = equationInput.getText();
                count = Integer.parseInt(countInput.getText());
                if (count >= 1) { //prevents user from inputing a derivative order less than 1
                    calc = new Calc(equation, count);
                    JOptionPane.showMessageDialog(null, calc.calculate(), "Answer", JOptionPane.PLAIN_MESSAGE);
                } else {
                    Exception IllegalArgumentException = new Exception();
                    throw IllegalArgumentException;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Something went wrong. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
