package com.example.mtermproject_1quizapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TrueOrFalseFragment extends Fragment {
    private String question;
    private String[] optAns;
    int correct = 0;

    Button submitBtnTraulse;
    TextView textViewQuestionTrualse, textViewAnswerTrualse;
    RadioGroup radioGrpTrualse;
    RadioButton radioBtnTrue, radioBtnFalse;

    public static TrueOrFalseFragment newInstance(String question, String[] optAns) {
        TrueOrFalseFragment fragment1 = new TrueOrFalseFragment();
        Bundle b1 = new Bundle();
        b1.putString("question", question );
        b1.putStringArray("optAns", optAns);
        fragment1.setArguments(b1);
        return fragment1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_true_or_false, container, false);
        textViewQuestionTrualse = v.findViewById(R.id.textViewQuestionTrualse);
        textViewAnswerTrualse = v.findViewById(R.id.textViewAnswerTrualse);
        submitBtnTraulse =v.findViewById(R.id.submitBtnTraulse);
        radioGrpTrualse = v.findViewById(R.id.radioGrpTrualse);
        radioBtnTrue = v.findViewById(R.id.radioBtnTrue);
        radioBtnFalse = v.findViewById(R.id.radioBtnFalse);
        if (getArguments() != null) {
            question = getArguments().getString("question");
            optAns = getArguments().getStringArray("optAns");
        }
        textViewQuestionTrualse.setText(question);

        submitBtnTraulse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGrpTrualse.getCheckedRadioButtonId() == -1) {
                    // no radio buttons are checked
                    Toast.makeText(getActivity(),"Pls. Select Answer!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    // one of the radio buttons is checked
                    //Correct Answer
                    if(radioBtnTrue.isChecked() && optAns[0].equals(radioBtnTrue.getText().toString())) {
                        radioBtnTrue.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else if(radioBtnFalse.isChecked() && optAns[0].equals(radioBtnFalse.getText().toString())){
                        radioBtnFalse.setTextColor(Color.rgb(0, 204, 0));
                        correct++;
                    }else{
                        //Wrong Answer
                        if(radioBtnTrue.isChecked())
                             radioBtnTrue.setTextColor(Color.rgb(255,51,51));
                        else
                            radioBtnFalse.setTextColor(Color.rgb(255,51,51));
                    }
                    QuizActivity quizActivity =(QuizActivity)getActivity();
                    quizActivity.Points(correct);
                    String displayans = "Answer: "+optAns[0]+"     Point(s): "+correct;
                    textViewAnswerTrualse.setText(displayans);
                    submitBtnTraulse.setVisibility(v.GONE);
                }
            }
        });

        return v;
    }

}