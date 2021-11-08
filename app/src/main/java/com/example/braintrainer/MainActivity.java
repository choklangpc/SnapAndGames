package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();//array list to store all the possible answers
    int locationOfCorrectAnswer ;//location of true answer
    TextView resultTextView; //the result display obj
    int score=0;//tracking of the score
    int numberOfQuestion=0;//track number of question
    TextView scoreTextView;
    Button button0;//button tag0 for clicking option
    Button button1;//button tag1 for clicking option
    Button button2;//button tag2 for clicking option
    Button button3;//button tag3 for clicking option
    TextView sumTextView;//total sum result
    TextView timerTextView;//timer text view
    Button playAgainButton;
    ConstraintLayout gameLayout;
    public void playAgain(View view){
        score = 0;
        numberOfQuestion=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));//views the score
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(30100, 1000){//timer count down.
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+ "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("Timer Finished Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    public void chooseAnswer(View view){
       if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
           resultTextView.setText("Correct!");
           score++;
       }else {
           resultTextView.setText("Wrong :(");
       }
        numberOfQuestion++;//regardless of Right/Wrong the number of question should increase
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestion));//views the score
        newQuestion();
    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(playAgainButton);
}

    public void newQuestion(){
        Random rand = new Random();//creating random seed

        int a = rand.nextInt(21);//random for question
        int b = rand.nextInt(21);//random for the question

        sumTextView.setText(Integer.toString(a)+ "+" + Integer.toString(b));

        locationOfCorrectAnswer=rand.nextInt(4);

        answers.clear();

        for(int i=0;i <4; i++)//forloop for answers
        {
            if(i==locationOfCorrectAnswer) {
                answers.add(a+b);
            }else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0); //buttons tags
        button1 = findViewById(R.id.button1); //buttons tags
        button2 = findViewById(R.id.button2); //buttons tags
        button3 = findViewById(R.id.button3); //buttons tags
        resultTextView=findViewById(R.id.resultTextView);//result text view ex: WIN/LOSE
        scoreTextView =findViewById(R.id.scoreTextView);//score text view ex:shows the score.
        timerTextView =findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);

        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}