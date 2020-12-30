package com.example.mtermproject_1quizapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IdentificationFragment extends Fragment {
    private String question;
    private String[] optAns;
    int correct = 0;

    TextView textViewQuestionIdentification, textViewRevealAns;
    EditText editTextAns;
    Button submitBtnIdentification;

    public static IdentificationFragment newInstance(String question, String[] optAns) {
        IdentificationFragment fragment2 = new IdentificationFragment();
        Bundle b1 = new Bundle();
        b1.putString("question", question);
        b1.putStringArray("optAns", optAns);
        fragment2.setArguments(b1);
        return fragment2;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_identification, container, false);
        textViewQuestionIdentification = v.findViewById(R.id.textViewQuestionIdentification);
        textViewRevealAns = v.findViewById(R.id.textViewRevealAns);
        editTextAns = v.findViewById(R.id.editTextAns);
        submitBtnIdentification = v.findViewById(R.id.submitBtnIdentification);
        if (getArguments() != null) {
            question = getArguments().getString("question");
            optAns = getArguments().getStringArray("optAns");
        }
        textViewQuestionIdentification.setText(question);
        //textViewRevealAns.setText(optAns[0].toString());
        submitBtnIdentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAns.getText().toString().equals(""))
                    Toast.makeText(getActivity(),"Pls. Provide Answer!!!",Toast.LENGTH_SHORT).show();
                else{
                    //Correct Answer
                    if(editTextAns.getText().toString().equals(optAns[0].toString())) {
                        editTextAns.setTextColor(Color.rgb(0,204,0));
                        correct++;
                    }
                    else{
                        editTextAns.setTextColor(Color.rgb(255,51,51));
                    }

                    QuizActivity quizActivity =(QuizActivity)getActivity();
                    quizActivity.Points(correct);
                    String displayans = "Answer: "+optAns[0]+"     Point(s): "+correct;
                    textViewRevealAns.setText(displayans);
                    submitBtnIdentification.setVisibility(v.GONE);
                }
            }
        });
        return v;
    }

}