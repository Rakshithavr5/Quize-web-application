import javax.swing.*;
import java.awt.*;

public class LeaderboardPage extends JFrame {

    public LeaderboardPage(String username) {
        setTitle("🏆 Leaderboard");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(25, 25, 25));

        JLabel lblTitle = new JLabel("🏆 Global Leaderboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(180, 20, 300, 30);
        add(lblTitle);

        String[] columns = {"Rank", "Name", "Score"};
        Object[][] data = {
                {"1", "Rakshitha vr", "6"},
                {"2", "Preethi", "5"},
                {"3", "Monisha", "6"},
                {"4", "Bhumi", "4"},
                {"5", "Savitha", "3"}         
                
        };

        JTable table = new JTable(data, columns);
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(70, 80, 450, 200);
        add(scrollPane);

        JButton btnBack = new JButton("⬅ Back to Dashboard");
        btnBack.setBounds(200, 300, 180, 40);
        btnBack.setBackground(new Color(100, 149, 237));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard(username).setVisible(true);
        });
        add(btnBack);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LeaderboardPage("Rakshitha").setVisible(true);
    }

    LeaderboardPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
