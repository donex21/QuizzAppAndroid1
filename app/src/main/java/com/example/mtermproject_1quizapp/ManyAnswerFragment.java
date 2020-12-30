package com.example.mtermproject_1quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManyAnswerFragment extends Fragment {
    private String question;
    private String[] optAns;

    TextView textViewQuestionManyChoice, textViewAnswerManyAnswer;
    CheckBox checkboxManyA, checkboxManyB, checkboxManyC, checkboxManyD;
    Button submitBtnManyChoice;
    LinearLayout linearManyChoice;

    int checkcount = 0;

    public static ManyAnswerFragment newInstance(String question, String[] optAns) {
        ManyAnswerFragment fragment4 = new ManyAnswerFragment();
        Bundle b1 = new Bundle();
        b1.putString("question", question );
        b1.putStringArray("optAns", optAns);
        fragment4.setArguments(b1);
        return fragment4;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_manyanswer, container, false);
        textViewQuestionManyChoice = v.findViewById(R.id.textViewQuestionManyChoice);
        textViewAnswerManyAnswer = v.findViewById(R.id.textViewAnswerManyAnswer);
        checkboxManyA = v.findViewById(R.id.checkboxManyA);
        checkboxManyB = v.findViewById(R.id.checkboxManyB);
        checkboxManyC = v.findViewById(R.id.checkboxManyC);
        checkboxManyD = v.findViewById(R.id.checkboxManyD);
        submitBtnManyChoice = v.findViewById(R.id.submitBtnManyChoice);
        linearManyChoice =v.findViewById(R.id.linearManyChoice);
        if (getArguments() != null) {
            question = getArguments().getString("question");
            optAns = getArguments().getStringArray("optAns");
        }
        List<String> answerList = new ArrayList<>();
        Collections.addAll(answerList,optAns);
        Collections.shuffle(answerList);

        textViewQuestionManyChoice.setText(question);
        checkboxManyA.setText(answerList.get(0));
        checkboxManyB.setText(answerList.get(1));
        checkboxManyC.setText(answerList.get(2));
        checkboxManyD.setText(answerList.get(3));

        submitBtnManyChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int limitcheck =0;
                int maxcheck = linearManyChoice.getChildCount();
                for(int i=0; i<maxcheck;i++){
                    v = linearManyChoice.getChildAt(i);
                    if(v instanceof CheckBox ){
                        if(((CheckBox) v).isChecked())
                            limitcheck++;
                    }
                }

                if(!(checkboxManyA.isChecked() || checkboxManyB.isChecked()
                        || checkboxManyC.isChecked() || checkboxManyD.isChecked())){
                    //checkboxes not checked
                    Toast.makeText(getActivity(),"Pls. Select Two Answer!!!",Toast.LENGTH_SHORT).show();
                }else if(limitcheck > 2){
                    Toast.makeText(getActivity(),"Pls. Select Two Answer only !!!",Toast.LENGTH_SHORT).show();
                } else{
                    if(checkboxManyA.isChecked())
                        checkboxManyA.setTextColor(Color.rgb(255,51,51));
                    if(checkboxManyB.isChecked())
                        checkboxManyB.setTextColor(Color.rgb(255,51,51));
                    if(checkboxManyC.isChecked())
                        checkboxManyC.setTextColor(Color.rgb(255,51,51));
                    if(checkboxManyD.isChecked())
                        checkboxManyD.setTextColor(Color.rgb(255,51,51));

                    //Correct Answer
                    if(checkboxManyA.isChecked() && (optAns[0].equals(checkboxManyA.getText().toString())
                            || optAns[1].equals(checkboxManyA.getText().toString()))) {
                        checkboxManyA.setTextColor(Color.rgb(0, 204, 0));
                        checkcount ++;
                    }
                    if(checkboxManyB.isChecked() && (optAns[0].equals(checkboxManyB.getText().toString())
                            || optAns[1].equals(checkboxManyB.getText().toString()))) {
                        checkboxManyB.setTextColor(Color.rgb(0, 204, 0));
                        checkcount ++;
                    }
                    if(checkboxManyC.isChecked() && (optAns[0].equals(checkboxManyC.getText().toString())
                            || optAns[1].equals(checkboxManyC.getText().toString()))) {
                        checkboxManyC.setTextColor(Color.rgb(0, 204, 0));
                        checkcount ++;
                    }
                    if(checkboxManyD.isChecked() && (optAns[0].equals(checkboxManyD.getText().toString())
                            || optAns[1].equals(checkboxManyD.getText().toString()))) {
                        checkboxManyD.setTextColor(Color.rgb(0, 204, 0));
                        checkcount ++;
                    }
                    QuizActivity quizActivity =(QuizActivity)getActivity();
                    quizActivity.Points(checkcount);
                    String displayans ="Answer: "+ optAns[0] +", "+ optAns[1]+"     Point(s): "+checkcount;
                    textViewAnswerManyAnswer.setText(displayans);
                    submitBtnManyChoice.setVisibility(v.GONE);
                }
            }
        });
        return v;
    }
}