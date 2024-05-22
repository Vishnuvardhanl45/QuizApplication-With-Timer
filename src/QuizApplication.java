import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizApplication {
    public static void main(String[] args) {
        List<Question> quizQuestions = new ArrayList<>();

        quizQuestions.add(new Question("What does the acronym JDK stand for in Java development?", List
                .of("A. Java Development Kit", "B. Java Developer Kit", "C. Java Design Kit", "D. Java Deployment Kit"),
                0));
        quizQuestions.add(new Question("Which keyword is used to define a constant in Java?",
                List.of("A. final", "B. const", "C. static", "D. constant"), 0));
        quizQuestions.add(new Question("What is the purpose of the break statement in Java?",
                List.of("A. To exit a loop or switch statement", "B. To skip the current iteration of a loop",
                        "C. To terminate the program", "D. To resume the next iteration of a loop"),
                0));
        quizQuestions.add(new Question("what is the purpose of the super keyword?",
                List.of("A. To access the superclass's methods and fields", "B. To declare a superclass",
                        "C. To create an instance of the superclass",
                        "D. To refer to the current instance of the class"),
                0));
        quizQuestions
                .add(new Question("Which of the following is a correct way to declare and initialize a String in Java?",
                        List.of("A. String str = 'Hello'1", "B. String str = \\\"Hello\\\";",
                                "C. String str = new String('Hello');", "D. String str; str = 'Hello';"),
                        1));

        Quiz quiz = new Quiz(quizQuestions);
        Scanner scanner = new Scanner(System.in);
        TimerHelper timerHelper = new TimerHelper();
        int questionTimeLimit = 10;
        int score = 0;

        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            Question currentQuestion = quiz.getQuestion(i);

            if (currentQuestion != null) {
                timerHelper.startTimer(questionTimeLimit, () -> {
                    System.out.println("\nTime's up!");
                    System.out.println("Correct answer: "
                            + currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex()));
                });

                System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());
                for (int j = 0; j < currentQuestion.getOptions().size(); j++) {
                    System.out.println((j + 1) + ". " + currentQuestion.getOptions().get(j));
                }

                int userAnswer = -1;
                boolean validInput = false;
                int timeRemaining = questionTimeLimit; // Initialize timeRemaining with the initial time limit
                while (!validInput && timeRemaining > 0) {
                    System.out.print("Your answer (enter the option number): ");
                    try {
                        userAnswer = Integer.parseInt(scanner.nextLine());
                        if (userAnswer >= 1 && userAnswer <= currentQuestion.getOptions().size()) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a valid option number.\n");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.\n");
                    }
                    timeRemaining--;
                }

                timerHelper.cancelTimer();

                if (timeRemaining <= 0) {
                    System.out.println("\nTime's up! Moving to the next question.\n");
                    continue;
                }

                if (userAnswer == currentQuestion.getCorrectAnswerIndex() + 1) {
                    System.out.println("Correct!\n");
                    score++;
                } else {
                    System.out.println("Incorrect. The correct answer is: " +
                            currentQuestion.getOptions().get(currentQuestion.getCorrectAnswerIndex()) + "\n");
                }
            }
        }

        System.out.println("Quiz completed! Your score: " + score + "/" + quiz.getTotalQuestions());
        scanner.close();
    }
}
