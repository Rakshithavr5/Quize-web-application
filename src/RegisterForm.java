import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterForm extends JFrame implements ActionListener {

    private JTextField txtUsername, txtEmail, txtPhone, txtDob;
    private JPasswordField txtPassword;
    private JComboBox<String> genderBox;
    private JButton btnRegister, btnBack;

    public RegisterForm() {
        setTitle("Register");
        setSize(400, 420);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 🎨 Dark background
        getContentPane().setBackground(new Color(25, 25, 25));

        JLabel lblTitle = new JLabel("Create Account");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(120, 20, 200, 30);
        add(lblTitle);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(50, 70, 100, 25);
        add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(160, 70, 170, 25);
        add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(50, 110, 100, 25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 110, 170, 25);
        add(txtPassword);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setBounds(50, 150, 100, 25);
        add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(160, 150, 170, 25);
        add(txtEmail);

        JLabel lblPhone = new JLabel("Phone No:");
        lblPhone.setForeground(Color.WHITE);
        lblPhone.setBounds(50, 190, 100, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(160, 190, 170, 25);
        add(txtPhone);

        JLabel lblDob = new JLabel("Date of Birth:");
        lblDob.setForeground(Color.WHITE);
        lblDob.setBounds(50, 230, 100, 25);
        add(lblDob);

        txtDob = new JTextField();
        txtDob.setBounds(160, 230, 170, 25);
        add(txtDob);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setForeground(Color.WHITE);
        lblGender.setBounds(50, 270, 100, 25);
        add(lblGender);

        String[] genders = {"Select", "Male", "Female", "Other"};
        genderBox = new JComboBox<>(genders);
        genderBox.setBounds(160, 270, 170, 25);
        add(genderBox);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(70, 320, 110, 35);
        btnRegister.setBackground(new Color(0, 120, 215)); // Blue
        btnRegister.setForeground(Color.WHITE);
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(this);
        add(btnRegister);

        btnBack = new JButton("Back");
        btnBack.setBounds(210, 320, 110, 35);
        btnBack.setBackground(new Color(128, 0, 128)); // Purple
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(this);
        add(btnBack);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();
            String email = txtEmail.getText().trim();
            String phone = txtPhone.getText().trim();
            String dob = txtDob.getText().trim();
            String gender = genderBox.getSelectedItem().toString();

            if (username.isEmpty() || password.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || dob.isEmpty() || gender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "⚠️ Please fill all fields properly!");
                return;
            }

            // ✅ Save to database
            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/quizdb", "root", "root"); // change if needed

                String query = "INSERT INTO users (username, password, email, phone, dob, gender) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.setString(3, email);
                pst.setString(4, phone);
                pst.setString(5, dob);
                pst.setString(6, gender);

                int rows = pst.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "✅ Registered Successfully!\nWelcome, " + username + "!");
                    this.dispose();
                    new LoginForm().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Registration failed! Try again.");
                }

                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "❌ Database Error: " + ex.getMessage());
                ex.printStackTrace();
            }

        } else if (e.getSource() == btnBack) {
            this.dispose();
            new LoginForm().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterForm().setVisible(true));
    }
}
