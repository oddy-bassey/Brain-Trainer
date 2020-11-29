package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout gameView;
    boolean isPLaying = false;

    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswers;
    int score = 0, numberOfuestions = 0;

    TextView resultTextView;
    TextView sumTextView;
    TextView scoreTextView;
    TextView timerTextView;

    Button goButton;
    Button playAgainButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;


    public void start(View view){
        gameView.setVisibility(View.VISIBLE);
        playAgain(view);
        goButton.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view){
        if(isPLaying) {
            if (Integer.toString(locationOfCorrectAnswers).equals(view.getTag().toString())) {
                resultTextView.setText("Correct!");
                score++;
            } else {
                resultTextView.setText("Wrong :(");
            }
            numberOfuestions++;
            scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfuestions));
            createQuestion();
        }else{
            Toast.makeText(getApplicationContext(), "Cant play anymore, restart game!", Toast.LENGTH_SHORT).show();
        }
    }

    public void createQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswers = random.nextInt(4);
        answers.clear();

        for(int i = 0; i<4 ; i++){
            if(i==locationOfCorrectAnswers) {
                answers.add(a + b);
            }else{
                int wrongAnswer = random.nextInt(41);

                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view){
        isPLaying = true;
        playAgainButton.setVisibility(View.INVISIBLE);
        gameView.setVisibility(View.VISIBLE);
        resultTextView.setText("");


        score = 0;
        numberOfuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfuestions));

        createQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                isPLaying = false;
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);

        button0 = findViewById(R.id.button2);
        button1 = findViewById(R.id.button3);
        button2 = findViewById(R.id.button4);
        button3 = findViewById(R.id.button5);

        goButton = findViewById(R.id.goButton);
        playAgainButton = findViewById(R.id.playAgainButton);

        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);

        gameView.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
    }
}
