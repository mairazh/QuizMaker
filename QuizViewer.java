import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import java.util.Arrays;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.CheckBox;


public class QuizViewer extends Application{

    @Override
    public void start(Stage primaryStage){

        VBox vbox = new VBox(15);
        StackPane pane = new StackPane();
        Button btFile = new Button("Load File");
        Button btNext = new Button(">>");
        Button btLast = new Button("<<");

        FlowPane fpane = new FlowPane();
        TextArea textArea = new TextArea();
        textArea.setPrefSize(470, 150);
        TextField txtField = new TextField();
        final int[] index = {0};


        int selectQuestion;
        ArrayList<CheckBox> list = new ArrayList<>();

        btFile.setOnAction(e-> {
            FileChooser fileChooser;
            fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Home\\Desktop\\Project"));
                File file = fileChooser.showOpenDialog(primaryStage);
                file.toString().valueOf(fileChooser);
            Quiz quiz = null;
            ArrayList<Question> questFile;
                try {
                    quiz = Quiz.loadFromFile(file.toString());
                    textArea.setText(quiz.getQuestions().get(index[0]).getDescription().replace("{blank}", "____"));
                } catch (InvalidQuizFormatException exm) {
                        VBox vb = new VBox();
                        Label lbException = new Label("InvalidQuizFormatException");
                        Text text = new Text("The file selected does not fit the requirements for a standard Quiz text file format...");
                        Button btOK = new Button("OK");
                        lbException.setAlignment(Pos.TOP_LEFT);
                        btOK.setAlignment(Pos.BOTTOM_RIGHT);
                        vb.getChildren().addAll(lbException, text, btOK);
                        Scene scene = new Scene(vb, 350, 200);
                        Stage stage1 = new Stage();
                        stage1.setTitle("Quiz Viewer: Error");
                        stage1.setScene(scene);
                        btOK.setOnAction(event1 -> {
                            stage1.hide();
                        });
                }
                questFile = quiz.getQuestions();

                Label label1 = new Label("Status: " + (index[0]+1) + "/5 questions");
                label1.setAlignment(Pos.BOTTOM_LEFT);
            	Quiz finalQuiz = quiz;


            	btNext.setOnAction(event2 ->{
                index[0]++;
                textArea.setText(finalQuiz.getQuestions().get(index[0]).getDescription().replace("{blank}", "____"));
                label1.setText("Status:  " +(index[0]+1)+ " /5 questions");

                
                try{
                if(index[0]+1==questFile.size()){
            	System.out.println("End of quiz");
        		label1.setText("End of Quiz");
        		label1.setAlignment(Pos.BOTTOM_RIGHT);

            }
        } catch(IndexOutOfBoundsException ex){
        	ex.printStackTrace();
        }
                });



            btLast.setOnAction(event2 ->{
                index[0]--;
                textArea.setText(finalQuiz.getQuestions().get(index[0]).getDescription().replace("{blank}", "____"));
                label1.setText("Status:  " +(index[0]+1)+ " /5 questions");
            });


                textArea.setEditable(false);
                TextField textField = new TextField();
                fpane.setPadding(new Insets(0, 0, 0, 0));
                fpane.setHgap(404);
                fpane.getChildren().addAll(textArea, btLast, btNext, label1);
                primaryStage.setScene(new Scene(fpane, 470, 400));
                primaryStage.show();

        });

        pane.getChildren().addAll(btFile);
        Scene scene = new Scene(pane, 470, 400);
        primaryStage.setTitle("Quiz Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}