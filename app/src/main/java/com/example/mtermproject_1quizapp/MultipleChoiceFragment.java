package com.example.mtermproject_1quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MultipleChoiceFragment extends Fragment {
    private String question;
    private String[] optAns;
    int correct = 0;

    TextView textViewQuestionMultipleChoice, textViewAnswerMultipleChoice;
    RadioGroup radioGrpMultipleChoice;
    RadioButton radioBtnChoiceA, radioBtnChoiceB, radioBtnChoiceC, radioBtnChoiceD;
    Button submitBtnMultipleChoice;
    public static MultipleChoiceFragment newInstance(String question, String[] optAns) {
        MultipleChoiceFragment fragment3 = new MultipleChoiceFragment();
        Bundle b1 = new Bundle();
        b1.putString("question", question );
        b1.putStringArray("optAns", optAns);
        fragment3.setArguments(b1);
        return fragment3;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_multiplechoice, container, false);
        textViewQuestionMultipleChoice = v.findViewById(R.id.textViewQuestionMultipleChoice);
        textViewAnswerMultipleChoice = v.findViewById(R.id.textViewAnswerMultipleChoice);
        radioGrpMultipleChoice = v.findViewById(R.id.radioGrpMultipleChoice);
        submitBtnMultipleChoice = v.findViewById(R.id.submitBtnMultipleChoice);
        radioBtnChoiceA = v.findViewById(R.id.radioBtnChoiceA);
        radioBtnChoiceB = v.findViewById(R.id.radioBtnChoiceB);
        radioBtnChoiceC = v.findViewById(R.id.radioBtnChoiceC);
        radioBtnChoiceD = v.findViewById(R.id.radioBtnChoiceD);

        if (getArguments() != null) {
            question = getArguments().getString("question");
            optAns = getArguments().getStringArray("optAns");
        }
        List<String> answerList = new ArrayList<>();
        Collections.addAll(answerList,optAns);
        Collections.shuffle(answerList);

        textViewQuestionMultipleChoice.setText(question);
        radioBtnChoiceA.setText(answerList.get(0));
        radioBtnChoiceB.setText(answerList.get(1));
        radioBtnChoiceC.setText(answerList.get(2));
        radioBtnChoiceD.setText(answerList.get(3));

        submitBtnMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGrpMultipleChoice.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity(),"Pls. Select Answer!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    // one of the radio buttons is checked
                    //Correct Answer
                    if(radioBtnChoiceA.isChecked() && optAns[0].equals(radioBtnChoiceA.getText().toString())) {
                        radioBtnChoiceA.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else if(radioBtnChoiceB.isChecked() && optAns[0].equals(radioBtnChoiceB.getText().toString())) {
                        radioBtnChoiceB.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else if(radioBtnChoiceC.isChecked() && optAns[0].equals(radioBtnChoiceC.getText().toString())) {
                        radioBtnChoiceC.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else if(radioBtnChoiceD.isChecked() && optAns[0].equals(radioBtnChoiceD.getText().toString())) {
                        radioBtnChoiceD.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else{
                        //Wrong Answer
                        if(radioBtnChoiceA.isChecked()){
                            radioBtnChoiceA.setTextColor(Color.rgb(255,51,51));
                        }else if(radioBtnChoiceB.isChecked()){
                            radioBtnChoiceB.setTextColor(Color.rgb(255,51,51));
                        }else if(radioBtnChoiceC.isChecked()){
                            radioBtnChoiceC.setTextColor(Color.rgb(255,51,51));
                        }else{
                            radioBtnChoiceD.setTextColor(Color.rgb(255,51,51));
                        }
                    }
                    QuizActivity quizActivity =(QuizActivity)getActivity();
                    quizActivity.Points(correct);
                    String displayans = "Answer: "+optAns[0]+"     Point(s): "+correct;
                    textViewAnswerMultipleChoice.setText(displayans);
                    submitBtnMultipleChoice.setVisibility(v.GONE);
                }
            }
        });

        return v;
    }
}