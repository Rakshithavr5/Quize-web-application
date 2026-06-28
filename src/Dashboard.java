import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {

    private JButton btnStartQuiz, btnLeaderboard, btnAbout, btnLogout;
    private String username;

    public Dashboard(String username) {
        this.username = username;

        setTitle("Dashboard - " + username);
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(20, 20, 20));

        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 16);

        JLabel lblWelcome = new JLabel("Welcome, " + username + " 👋");
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        lblWelcome.setBounds(30, 20, 400, 40);
        add(lblWelcome);

        JLabel lblSub = new JLabel("Your Learning Dashboard");
        lblSub.setForeground(Color.LIGHT_GRAY);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSub.setBounds(30, 60, 300, 30);
        add(lblSub);

        // Stat panels
        createStatPanel("25", "Quizzes Taken", 30, 120);
        createStatPanel("5", "High Score", 190, 120);
        createStatPanel("100%", "Average Score", 30, 220);
        createStatPanel("1st 🥇", "Rank", 190, 220);

btnStartQuiz = createButton("🎯 Start Quiz", new Color(0, 180, 170), 370, 120, emojiFont);
btnLeaderboard = createButton("🏆 Leaderboard", new Color(80, 120, 255), 540, 120, emojiFont);
btnAbout = createButton("ℹ About", new Color(140, 100, 210), 370, 200, emojiFont);
btnLogout = createButton("🚪 Logout", new Color(200, 60, 60), 540, 200, emojiFont);


        add(btnStartQuiz);
        add(btnLeaderboard);
        add(btnAbout);
        add(btnLogout);

        // Tips section
        JTextArea tips = new JTextArea(
            "💡 Tips:\n" +
            "- Retake quizzes to improve your score.\n" +
            "- View leaderboard to see top performers.\n" +
            "- Choose your favorite category to begin!"
        );
        tips.setBounds(30, 320, 640, 100);
        tips.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
        tips.setEditable(false);
        tips.setBackground(new Color(30, 30, 30));
        tips.setForeground(Color.LIGHT_GRAY);
        tips.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY),
            "Tips", 0, 0,
            new Font("Segoe UI Emoji", Font.BOLD, 14),
            Color.WHITE)
        );
        add(tips);

        // Button actions
        btnStartQuiz.addActionListener(this);
        btnLeaderboard.addActionListener(this);
        btnAbout.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    private void createStatPanel(String value, String title, int x, int y) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, 140, 80);
        panel.setBackground(new Color(45, 45, 45));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        lblValue.setForeground(Color.WHITE);

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblTitle.setForeground(Color.LIGHT_GRAY);

        panel.add(lblValue, BorderLayout.CENTER);
        panel.add(lblTitle, BorderLayout.SOUTH);
        add(panel);
    }

    private JButton createButton(String text, Color color, int x, int y, Font font) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 160, 60);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(font.deriveFont(Font.BOLD, 15f));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnStartQuiz) {
            dispose();
            new QuizSelectionPage(username).setVisible(true);
        } 
        else if (e.getSource() == btnLeaderboard) {
    dispose();
    new LeaderboardPage(username).setVisible(true);
}

        else if (e.getSource() == btnAbout) {
            JOptionPane.showMessageDialog(this,
                "📘 Online Quiz Application\nDeveloped by Rakshitha V R\n© 2025 All Rights Reserved.",
                "About", JOptionPane.INFORMATION_MESSAGE);
        } 
        else if (e.getSource() == btnLogout) {
            JOptionPane.showMessageDialog(this, "You have been logged out.");
            dispose();
        }
    }

    public static void main(String[] args) {
        new Dashboard("Rakshitha").setVisible(true);
    }
}
