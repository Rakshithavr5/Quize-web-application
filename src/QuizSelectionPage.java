import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizSelectionPage extends JFrame implements ActionListener {
    private JButton btnComputer, btnScience, btnGK, btnHistory, btnJava, btnGeography, btnBack;
    private String username;

    public QuizSelectionPage(String username) {
        this.username = username;

        setTitle("Select Quiz Category");
        setSize(600, 450);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        JLabel lblTitle = new JLabel("Select Your Quiz Category");
        lblTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        lblTitle.setForeground(Color.CYAN);
        lblTitle.setBounds(150, 30, 400, 40);
        add(lblTitle);

        btnComputer = createButton("💻 Computer");
        btnScience = createButton("🔬 Science");
        btnGK = createButton("🌍 GK");
        btnHistory = createButton("📜 History");
        btnJava = createButton("☕ Java");
        btnGeography = createButton("🗺️ Geography");

        JPanel panel = new JPanel(new GridLayout(2, 3, 20, 25)); // uniform row gap
        panel.setBackground(Color.BLACK);
        panel.setBounds(60, 100, 470, 200);
        panel.add(btnComputer);
        panel.add(btnScience);
        panel.add(btnGK);
        panel.add(btnHistory);
        panel.add(btnJava);
        panel.add(btnGeography);
        add(panel);

        btnBack = new JButton("⬅ Back to Dashboard");
        btnBack.setBackground(new Color(0, 120, 255));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        btnBack.setBounds(220, 340, 160, 40);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnBack);

        // Button actions
        btnComputer.addActionListener(this);
        btnScience.addActionListener(this);
        btnGK.addActionListener(this);
        btnHistory.addActionListener(this);
        btnJava.addActionListener(this);
        btnGeography.addActionListener(this);
        btnBack.addActionListener(this);
    }

    QuizSelectionPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Remove the old unsupported constructor (very important)
    // ✅ This constructor was causing exceptions earlier.
    //    We don’t need it anymore, so delete it completely.

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnComputer)
            new CategoryQuizPage(username, "Computer").setVisible(true);
        else if (e.getSource() == btnScience)
            new CategoryQuizPage(username, "Science").setVisible(true);
        else if (e.getSource() == btnGK)
            new CategoryQuizPage(username, "GK").setVisible(true);
        else if (e.getSource() == btnHistory)
            new CategoryQuizPage(username, "History").setVisible(true);
        else if (e.getSource() == btnJava)
            new CategoryQuizPage(username, "Java").setVisible(true);
        else if (e.getSource() == btnGeography)
            new CategoryQuizPage(username, "Geography").setVisible(true);
        else if (e.getSource() == btnBack) {
            dispose(); // close this window
            new Dashboard(username).setVisible(true); // open Dashboard again ✅
        }
    }

    public static void main(String[] args) {
        new QuizSelectionPage("Rakshitha").setVisible(true);
    }
}
