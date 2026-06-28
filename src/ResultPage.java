import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;

public class ResultPage extends JFrame implements ActionListener {

    private JLabel lblTitle, lblCategory, lblScore, lblPercentage, lblMsg;
    private JButton btnDashboard, btnExit;
    private String username, category;
    private int score, totalQuestions;

    // ✅ Main constructor (this is the one that will be called from CategoryQuizPage)
    public ResultPage(String category, int score, int totalQuestions) {
        // Since CategoryQuizPage doesn’t have username, let’s assume a default one
        this.username = "Guest";
        this.category = category;
        this.score = score;
        this.totalQuestions = totalQuestions;

        // 🖥️ Frame setup
        setTitle("Quiz Result - " + category);
        setSize(480, 420);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        // 🎉 Title
        lblTitle = new JLabel("🎯 Quiz Completed!");
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 24));
        lblTitle.setForeground(Color.CYAN);
        lblTitle.setBounds(130, 30, 300, 40);
        add(lblTitle);

        // 📘 Category
        lblCategory = new JLabel("Category: " + category);
        lblCategory.setFont(new Font("Poppins", Font.PLAIN, 18));
        lblCategory.setForeground(Color.WHITE);
        lblCategory.setBounds(140, 90, 250, 25);
        add(lblCategory);

        // 🧮 Score
        lblScore = new JLabel("Score: " + score + " / " + totalQuestions);
        lblScore.setFont(new Font("Poppins", Font.PLAIN, 18));
        lblScore.setForeground(Color.GREEN);
        lblScore.setBounds(150, 130, 250, 25);
        add(lblScore);

        // 📊 Percentage
        double percentage = ((double) score / totalQuestions) * 100;
        lblPercentage = new JLabel(String.format("Percentage: %.2f%%", percentage));
        lblPercentage.setFont(new Font("Poppins", Font.PLAIN, 18));
        lblPercentage.setForeground(Color.ORANGE);
        lblPercentage.setBounds(130, 170, 250, 25);
        add(lblPercentage);

        // 🎓 Custom message based on performance
        lblMsg = new JLabel();
        lblMsg.setFont(new Font("Poppins", Font.BOLD, 18));
        lblMsg.setBounds(120, 210, 300, 30);
        if (percentage == 100) {
            lblMsg.setText("🏆 Perfect Score! Excellent!");
            lblMsg.setForeground(Color.YELLOW);
        } else if (percentage >= 75) {
            lblMsg.setText("👏 Great Job!");
            lblMsg.setForeground(Color.GREEN);
        } else if (percentage >= 50) {
            lblMsg.setText("🙂 Good Effort!");
            lblMsg.setForeground(Color.CYAN);
        } else {
            lblMsg.setText("💪 Keep Practicing!");
            lblMsg.setForeground(Color.RED);
        }
        add(lblMsg);

        // 🏠 Dashboard button
        btnDashboard = new JButton("🏠 Back to Categories");
        btnDashboard.setBounds(120, 270, 230, 40);
        btnDashboard.setBackground(new Color(0, 120, 215));
        btnDashboard.setForeground(Color.WHITE);
        btnDashboard.setFont(new Font("Poppins", Font.BOLD, 14));
        btnDashboard.setFocusPainted(false);
        btnDashboard.addActionListener(this);
        add(btnDashboard);

        // ❌ Exit button
        btnExit = new JButton("⛔ Exit");
        btnExit.setBounds(180, 320, 110, 35);
        btnExit.setBackground(Color.RED);
        btnExit.setForeground(Color.WHITE);
        btnExit.setFont(new Font("Poppins", Font.BOLD, 14));
        btnExit.setFocusPainted(false);
        btnExit.addActionListener(this);
        add(btnExit);

        // 💾 Save to Database (optional)
        saveResult(username, category, score, totalQuestions, percentage);
    }

    // ✅ Optional Overloaded Constructor (if username is available)
    public ResultPage(String username, String category, int score, int totalQuestions) {
        this(category, score, totalQuestions);
        this.username = username;
    }

    // ✅ Save result to DB
    private void saveResult(String username, String category, int score, int total, double percentage) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO results (username, category, score, total, percentage, date_time) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, category);
            pstmt.setInt(3, score);
            pstmt.setInt(4, total);
            pstmt.setDouble(5, percentage);
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
            System.out.println("✅ Result saved successfully!");
        } catch (SQLException e) {
            System.out.println("⚠️ Error saving result: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ No DB connection found, skipping save.");
        }
    }

    // ✅ Handle buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDashboard) {
            dispose();
            new QuizSelectionPage().setVisible(true);
        } else if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    // ✅ For standalone testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> 
            new ResultPage("Science", 4, 5).setVisible(true)
        );
    }
}
