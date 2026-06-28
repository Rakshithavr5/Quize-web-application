import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginForm extends JFrame implements ActionListener {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister, btnClear;

    public LoginForm() {
        setTitle("Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🎨 Dark background
        getContentPane().setBackground(new Color(25, 25, 25));

        JLabel lblTitle = new JLabel("User Login");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(135, 20, 200, 30);
        add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(60, 80, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(160, 80, 170, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(60, 120, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 120, 170, 25);
        add(txtPassword);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(60, 180, 90, 35);
        btnLogin.setBackground(new Color(0, 120, 215)); // Blue
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.addActionListener(this);
        add(btnLogin);

        btnClear = new JButton("Clear");
        btnClear.setBounds(160, 180, 90, 35);
        btnClear.setBackground(new Color(128, 0, 128)); // Purple
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(this);
        add(btnClear);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(260, 180, 90, 35);
        btnRegister.setBackground(new Color(0, 200, 83)); // Green
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(this);
        add(btnRegister);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Please fill all fields!");
                return;
            }

            try {
                // ✅ Connect to database
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/quizdb", "root", "root"); // change password if needed

                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "✅ Login Successful!\nWelcome, " + username + "!");
                    this.dispose();
                    // 👇 After successful login, open your dashboard or quiz page here
                    new Dashboard(username).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Invalid username or password!");
                }

                conn.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "❌ Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else if (e.getSource() == btnClear) {
            txtUsername.setText("");
            txtPassword.setText("");
        } else if (e.getSource() == btnRegister) {
            this.dispose();
            new RegisterForm().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
