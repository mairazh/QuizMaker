import java.io.*;
import java.util.*;
public class QuizMaker {
    public static void main(String[] args) throws InvalidQuizFormatException {
        String cc11 = "kiuyftgjdnasdnasdvhbjh";
        String vv11 = "hfjnejdndbsahdbasjs";
        String bb11 = "hgrifjahsdbahebds";
        String name = "JavaQuiz.txt";
        String c1c = "kiuyftgvhbjh";
        String v1v = "hfjnejdnjs";
        String b1b = "hgrifjdbnsifedjkjs";
        Quiz quiz = Quiz.loadFromFile(name);
        String cc13 = "kdhasbdhavhbjh";
        String vv13 = "hfjnhdabsdhbs";
        String bb13 = "hgfhsdbuhasedjkjs";
        System.out.println(quiz);
        quiz.start();  
    }
}