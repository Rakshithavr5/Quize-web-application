import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;


public class CategoryQuizPage extends JFrame implements ActionListener {
    private String username, category;
    private JLabel lblQuestion, lblTimer;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton btnNext;
    private int currentQuestion = 0, score = 0, timeLeft = 120;
    private javax.swing.Timer timer;
    private String[][] questions;

    public CategoryQuizPage(String username, String category) {
        this.username = username;
        this.category = category;

        setTitle(category + " Quiz");
        setSize(650, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        JLabel lblTitle = new JLabel(category + " Quiz");
        lblTitle.setForeground(Color.CYAN);
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 22));
        lblTitle.setBounds(250, 20, 300, 30);
        add(lblTitle);

        lblTimer = new JLabel("Time Left: 02:00");
        lblTimer.setForeground(Color.RED);
        lblTimer.setFont(new Font("Poppins", Font.BOLD, 16));
        lblTimer.setBounds(480, 20, 200, 30);
        add(lblTimer);

        lblQuestion = new JLabel();
        lblQuestion.setForeground(Color.WHITE);
        lblQuestion.setFont(new Font("Poppins", Font.BOLD, 16));
        lblQuestion.setBounds(50, 80, 550, 40);
        add(lblQuestion);

        options = new JRadioButton[4];
        group = new ButtonGroup();
        int y = 140;
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setForeground(Color.WHITE);
            options[i].setBackground(Color.BLACK);
            options[i].setFont(new Font("Poppins", Font.PLAIN, 14));
            options[i].setBounds(70, y, 400, 30);
            group.add(options[i]);
            add(options[i]);
            y += 40;
        }

        btnNext = new JButton("Next");
        btnNext.setBackground(new Color(0, 102, 204));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFont(new Font("Poppins", Font.BOLD, 14));
        btnNext.setBounds(250, 300, 120, 35);
        btnNext.addActionListener(this);
        add(btnNext);

        loadQuestions();
        showQuestion();

        timer = new javax.swing.Timer(1000, e -> {
            timeLeft--;
            int minutes = timeLeft / 60;
            int seconds = timeLeft % 60;
            lblTimer.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
            if (timeLeft <= 0) {
                timer.stop();
                finishQuiz();
            }
        });
        timer.start();
    }

    private void loadQuestions() {
        switch (category) {
            case "Computer":
                questions = new String[][] {
                    {"CPU stands for?", "Central Process Unit", "Central Processing Unit", "Computer Personal Unit", "Control Processing Unit", "Central Processing Unit"},
                    {"Which part of computer is brain?", "CPU", "RAM", "Monitor", "Keyboard", "CPU"},
                    {"Binary code uses?", "0 and 1", "A and B", "1 and 2", "True and False", "0 and 1"},
                    {"Which device shows output?", "Monitor", "Keyboard", "Scanner", "Mouse", "Monitor"},
                    {"RAM is?", "Permanent", "Volatile", "External", "Input", "Volatile"}
                };
                break;

            case "Science":
                questions = new String[][] {
                    {"Water formula?", "H2O", "CO2", "O2", "NaCl", "H2O"},
                    {"Gas for respiration?", "Oxygen", "Hydrogen", "Nitrogen", "Helium", "Oxygen"},
                    {"Earth revolves around?", "Sun", "Moon", "Stars", "Mars", "Sun"},
                    {"Human blood color?", "Blue", "Red", "Green", "Yellow", "Red"},
                    {"Boiling point of water?", "100°C", "50°C", "0°C", "150°C", "100°C"}
                };
                break;

            case "GK":
                questions = new String[][] {
                    {"Capital of India?", "Delhi", "Mumbai", "Kolkata", "Chennai", "Delhi"},
                    {"Largest ocean?", "Pacific", "Atlantic", "Indian", "Arctic", "Pacific"},
                    {"Taj Mahal built by?", "Shah Jahan", "Akbar", "Aurangzeb", "Babar", "Shah Jahan"},
                    {"National animal of India?", "Lion", "Tiger", "Elephant", "Peacock", "Tiger"},
                    {"National flower of India?", "Lotus", "Rose", "Sunflower", "Lily", "Lotus"}
                };
                break;

            case "History":
                questions = new String[][] {
                    {"Who discovered India?", "Columbus", "Vasco da Gama", "Magellan", "Marco Polo", "Vasco da Gama"},
                    {"Father of Nation?", "Nehru", "Gandhi", "Patel", "Bose", "Gandhi"},
                    {"First Mughal Emperor?", "Akbar", "Babur", "Aurangzeb", "Shah Jahan", "Babur"},
                    {"Quit India Movement year?", "1942", "1947", "1930", "1950", "1942"},
                    {"Battle of Plassey year?", "1757", "1857", "1657", "1957", "1757"}
                };
                break;

            case "Java":
                questions = new String[][] {
                    {"Java is?", "Procedural", "Object-Oriented", "Functional", "None", "Object-Oriented"},
                    {"JVM stands for?", "Java Virtual Machine", "Java Very Machine", "Java Visual Model", "Java Vendor Model", "Java Virtual Machine"},
                    {"Extension of Java file?", ".js", ".java", ".jav", ".jar", ".java"},
                    {"Java invented by?", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "Guido van Rossum", "James Gosling"},
                    {"Keyword to inherit class?", "super", "extends", "this", "import", "extends"}
                };
                break;

            case "Geography":
                questions = new String[][] {
                    {"Largest continent?", "Asia", "Africa", "Europe", "Australia", "Asia"},
                    {"River Nile flows in?", "Egypt", "India", "USA", "Brazil", "Egypt"},
                    {"Highest mountain?", "Everest", "K2", "Kangchenjunga", "Makalu", "Everest"},
                    {"Sahara is a?", "Desert", "River", "Forest", "Mountain", "Desert"},
                    {"Earth shape?", "Flat", "Round", "Oval", "Square", "Round"}
                };
                break;
        }
    }

    private void showQuestion() {
        if (currentQuestion < questions.length) {
            lblQuestion.setText((currentQuestion + 1) + ". " + questions[currentQuestion][0]);
            List<String> opts = new ArrayList<>();
            for (int i = 1; i <= 4; i++) opts.add(questions[currentQuestion][i]);
            Collections.shuffle(opts);
            for (int i = 0; i < 4; i++) options[i].setText(opts.get(i));
            group.clearSelection();
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Quiz Completed!");
        new ResultPage(username, category, score, questions.length).setVisible(true);
        dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (group.getSelection() != null) {
            String correctAnswer = questions[currentQuestion][5];
            for (JRadioButton opt : options)
                if (opt.isSelected() && opt.getText().equals(correctAnswer))
                    score++;
        }
        currentQuestion++;
        showQuestion();
    }

    public static void main(String[] args) {
        new CategoryQuizPage("Rakshitha", "Computer").setVisible(true);
    }
}
