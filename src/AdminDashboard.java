import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminDashboard extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JButton btnAddQuestion, btnDeleteQuestion, btnManageUsers, btnViewResults, btnLogout;
    private String adminName = "Admin";

    public AdminDashboard() {
        setTitle("Admin Dashboard - Quiz Management System");
        setSize(800, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(25, 25, 25));
        setLayout(null);

        JLabel lblTitle = new JLabel("🛠️ Admin Dashboard");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(260, 30, 400, 40);
        add(lblTitle);

        JLabel lblWelcome = new JLabel("Welcome, " + adminName + " 👋");
        lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblWelcome.setForeground(Color.CYAN);
        lblWelcome.setBounds(310, 80, 300, 25);
        add(lblWelcome);

        // Main panel (content area)
        mainPanel = new JPanel();
        mainPanel.setBounds(100, 120, 600, 320);
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(30, 30, 30));
        add(mainPanel);

        showDashboardButtons();
    }

    private void showDashboardButtons() {
        mainPanel.removeAll();
        mainPanel.setLayout(new GridLayout(2, 2, 30, 30));

        btnAddQuestion = createButton("➕ Add Question", 160, 60);
        btnDeleteQuestion = createButton("🗑️ Delete Question", 160, 50);
        btnManageUsers = createButton("👥 Manage Users", 160, 50);
        btnViewResults = createButton("📊 View Results", 160, 50);

        mainPanel.add(btnAddQuestion);
        mainPanel.add(btnDeleteQuestion);
        mainPanel.add(btnManageUsers);
        mainPanel.add(btnViewResults);

        btnLogout = createButton("🚪 Logout", 120, 35);
        btnLogout.setBounds(330, 460, 120, 35);
        add(btnLogout);

        btnAddQuestion.addActionListener(this);
        btnDeleteQuestion.addActionListener(this);
        btnManageUsers.addActionListener(this);
        btnViewResults.addActionListener(this);
        btnLogout.addActionListener(this);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showAddQuestionForm() {
        mainPanel.removeAll();
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("➕ Add Question");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(220, 10, 200, 30);
        mainPanel.add(lblTitle);

        String[] labels = {"Question:", "Option A:", "Option B:", "Option C:", "Option D:", "Correct Answer:"};
        JTextField[] fields = new JTextField[6];
        int y = 60;

        for (int i = 0; i < labels.length; i++) {
            JLabel lbl = new JLabel(labels[i]);
            lbl.setForeground(Color.WHITE);
            lbl.setBounds(80, y, 150, 25);
            mainPanel.add(lbl);

            fields[i] = new JTextField();
            fields[i].setBounds(230, y, 300, 25);
            mainPanel.add(fields[i]);
            y += 40;
        }

        JButton btnSave = createButton("💾 Save", 100, 30);
        btnSave.setBounds(180, 285, 100, 30);
        JButton btnBack = createButton("⬅ Back", 100, 30);
        btnBack.setBounds(320, 285, 100, 30);

        mainPanel.add(btnSave);
        mainPanel.add(btnBack);

        btnSave.addActionListener(e -> {
            for (JTextField field : fields) {
                if (field.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all fields!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "✅ Question added successfully!");
            for (JTextField field : fields) field.setText("");
        });

        btnBack.addActionListener(e -> showDashboardButtons());

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showDeleteQuestionView() {
        mainPanel.removeAll();
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("🗑️ Delete Question");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(220, 20, 250, 30);
        mainPanel.add(lblTitle);

        JLabel lblInfo = new JLabel("Enter Question ID to Delete:");
        lblInfo.setForeground(Color.WHITE);
        lblInfo.setBounds(100, 100, 200, 25);
        mainPanel.add(lblInfo);

        JTextField txtId = new JTextField();
        txtId.setBounds(310, 100, 150, 25);
        mainPanel.add(txtId);

        JButton btnDelete = createButton("🗑 Delete", 100, 30);
        btnDelete.setBounds(200, 160, 100, 30);
        JButton btnBack = createButton("⬅ Back", 100, 30);
        btnBack.setBounds(330, 160, 100, 30);
        mainPanel.add(btnDelete);
        mainPanel.add(btnBack);

        btnDelete.addActionListener(e -> {
            if (txtId.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter Question ID!");
            } else {
                JOptionPane.showMessageDialog(this, "Question ID " + txtId.getText() + " deleted successfully!");
                txtId.setText("");
            }
        });

        btnBack.addActionListener(e -> showDashboardButtons());

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showManageUsersView() {
        mainPanel.removeAll();
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("👥 Manage Users");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(220, 20, 250, 30);
        mainPanel.add(lblTitle);

        String[] cols = {"User ID", "Username", "Email", "Score"};
        Object[][] data = {
                {"1", "Rakshitha", "rak@gmail.com", "92"},
                {"2", "Rahul", "rahul@gmail.com", "85"},
                {"3", "Diya", "diya@gmail.com", "78"}
        };

        JTable table = new JTable(data, cols);
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(80, 80, 440, 150);
        mainPanel.add(scroll);

        JButton btnBack = createButton("⬅ Back", 100, 30);
        btnBack.setBounds(250, 250, 100, 30);
        mainPanel.add(btnBack);
        btnBack.addActionListener(e -> showDashboardButtons());

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showResultsView() {
        mainPanel.removeAll();
        mainPanel.setLayout(null);

        JLabel lblTitle = new JLabel("📊 View Results");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(240, 20, 200, 30);
        mainPanel.add(lblTitle);

        String[] cols = {"Rank", "Name", "Category", "Score"};
        Object[][] data = {
                {"1", "Rakshitha", "Computer", "5"},
                {"2", "Rose", "Java", "4"},
                {"3", "Savitha", "GK", "3"}
        };

        JTable table = new JTable(data, cols);
        table.setBackground(new Color(40, 40, 40));
        table.setForeground(Color.WHITE);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(80, 80, 440, 150);
        mainPanel.add(scroll);

        JButton btnBack = createButton("⬅ Back", 100, 30);
        btnBack.setBounds(250, 250, 100, 30);
        mainPanel.add(btnBack);
        btnBack.addActionListener(e -> showDashboardButtons());

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JButton createButton(String text, int width, int height) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(width, height));
        btn.setBackground(new Color(0, 90, 180)); // 🔹 Darker & smoother blue
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddQuestion) showAddQuestionForm();
        else if (e.getSource() == btnDeleteQuestion) showDeleteQuestionView();
        else if (e.getSource() == btnManageUsers) showManageUsersView();
        else if (e.getSource() == btnViewResults) showResultsView();
        else if (e.getSource() == btnLogout) {
            JOptionPane.showMessageDialog(this, "Logged out successfully!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
