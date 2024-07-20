package com.example.quizz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView question , sc;

    Button yes , no;

    private final String [] questions = {"1.Constructor overloading is not possible in Java ?", "2.Assignment operator is evaluated Left to Right ?" , "3.Garbage Collection is manual process ?" , "4.Java was originally developed by James Gosling ?" , "5.Java was Formed in 1948 ?"};

    private final boolean [] result = {false, false , false ,true ,true};

    private int score = 0;
    private int index = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.tvQuestions);
        yes = findViewById(R.id.btnYes);
        no = findViewById(R.id.btnNo);
        sc = findViewById(R.id.tvscore);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index <= questions.length -1){
                    if(result[index] == true){
                        score++;
                    }
                    index++;
                    sc.setText("Your Score : " + score + "/" + questions.length);
                    if (index <= questions.length -1){
                        question.setText(questions[index]);
                    }else {
                        Toast.makeText(MainActivity.this, "Your score is " + score, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Restart the app to play again", Toast.LENGTH_SHORT).show();
                    openalert();
                }

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index <= questions.length -1){
                    if(result[index] == false){
                        score++;
                    }
                    index++;
                    sc.setText("Your Score : " + score + "/" + questions.length);
                    if (index <= questions.length -1){
                        question.setText(questions[index]);
                    }else {
                        Toast.makeText(MainActivity.this, "Your score is " + score, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Restart the app to play again", Toast.LENGTH_SHORT).show();
                    openalert();
                }
            }
        });
    }

    private void openalert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.drawable.quiz);
        builder.setTitle("Do you want to restart quizz");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              restartApp();
                Toast.makeText(MainActivity.this, "Restarting Quizz", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                exitApp();
                Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void restartApp() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void exitApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }
}