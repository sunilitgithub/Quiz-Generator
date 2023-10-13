import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

class Question {
    private String questionText;
    private List<String> choices;
    private int correctChoiceIndex;

    public Question(String questionText, List<String> choices, int correctChoiceIndex) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctChoiceIndex = correctChoiceIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectChoiceIndex() {
        return correctChoiceIndex;
    }
}

class Quiz extends JPanel {
    private List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private JLabel questionLabel;
    private List<JRadioButton> answerRadioButtons;
    private ButtonGroup answerButtonGroup;
    private JButton nextButton;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        currentQuestionIndex = 0;

        setLayout(new BorderLayout());

        questionLabel = new JLabel("", SwingConstants.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        JPanel choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(0, 1));
        answerRadioButtons = new ArrayList<>();
        answerButtonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            JRadioButton radioButton = new JRadioButton();
            radioButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        nextButton.setEnabled(true);
                    }
                }
            });
            answerRadioButtons.add(radioButton);
            answerButtonGroup.add(radioButton);
            choicesPanel.add(radioButton);
        }
        add(choicesPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion(currentQuestionIndex);
                } else {
                    showScore();
                }
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        if (questions.isEmpty()) {
            System.out.println("No questions added to the quiz.");
            return;
        }

        loadQuestion(currentQuestionIndex);
    }

    private void loadQuestion(int index) {
        Question question = questions.get(index);
        questionLabel.setText(question.getQuestionText());

        List<String> choices = question.getChoices();
        for (int i = 0; i < choices.size(); i++) {
            JRadioButton radioButton = answerRadioButtons.get(i);
            radioButton.setText(choices.get(i));
            radioButton.setSelected(false);
        }

        nextButton.setEnabled(false);
    }

    private void checkAnswer() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            int selectedAnswer = -1;

            for (int i = 0; i < answerRadioButtons.size(); i++) {
                if (answerRadioButtons.get(i).isSelected()) {
                    selectedAnswer = i;
                    break;
                }
            }

            if (selectedAnswer == question.getCorrectChoiceIndex()) {
                score++;
            }
        }
    }

    private void showScore() {
        questionLabel.setText("Quiz completed. Your score: " + score + " out of " + questions.size());
        nextButton.setEnabled(false);
    }
}

public class QuizGeneratorGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Quiz Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Quiz quiz = new Quiz();
        frame.add(quiz);

        // Add questions to the quiz
        List<String> choices1 = List.of("A. Sydney ", "B. Perth", "C. Melbourne", "D. Canberra");
        Question question1 = new Question("What is the capital city of Australia?", choices1, 4);
        quiz.addQuestion(question1);

        List<String> choices2 = List.of("A. Delhi", "B. Punjab", "C. Chandigarh", "D. Chennai");
        Question question2 = new Question("What is the capital of India?", choices2, 2);
        quiz.addQuestion(question2);

        List<String> choices3 = List.of("A. The Catcher in the Rye ", "B. Moby-Dick", "C. 1984 ", "D. The Great Gatsby");
        Question question3 = new Question("Which novel features the character Holden Caulfield?", choices3, 3);
        quiz.addQuestion(question3);

        List<String> choices4 = List.of("A. Benjamin Franklin ", "B. Thomas Jefferson", "C. George Washington", "D. John Adams");
        Question question4 = new Question("Who was the first President of the United States?", choices2, 3);
        quiz.addQuestion(question4);

        List<String> choices5 = List.of("A. Mercury", "B. Venus", "C. Earth", "D. Mars");
        Question question5 = new Question("Which planet is known as the Red Planet?", choices5, 4);
        quiz.addQuestion(question5);

        List<String> choices6 = List.of("A. 4", "B. 5", "C. 6", "D. 7");
        Question question6 = new Question("How many continents are there in the world?", choices6, 2);
        quiz.addQuestion(question6);

        List<String> choices7 = List.of("A. Python", "B. Java", "C. C++", "D. Ruby");
        Question question7 = new Question("Which programming language is known for its simplicity?", choices7, 1);
        quiz.addQuestion(question7);

        List<String> choices8 = List.of("A. wt", "B. H20", "C. wa", "D. wo");
        Question question8 = new Question("What is the chemical symbol for water?", choices8, 3);
        quiz.addQuestion(question8);

        List<String> choices9 = List.of("A. J.K. Rowling", "B. Harper Lee", "C. George Orwell", "D. Jane Austen");
        Question question9 = new Question("Who wrote the novel To Kill a Mockingbird?", choices9, 2);
        quiz.addQuestion(question9);

        List<String> choices10 = List.of("A. Star Trek ", "B. Guardians of the Galaxy", "C. Star Wars", "D. Avatar");
        Question question10 = new Question(" Which movie is known for the quote, May the Force be with you?", choices10, 3);
        quiz.addQuestion(question10);


        frame.setVisible(true);

        quiz.start();
    }
}
