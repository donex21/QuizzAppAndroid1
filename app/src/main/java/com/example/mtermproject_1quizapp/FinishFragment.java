package com.example.mtermproject_1quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FinishFragment extends Fragment {
    private ArrayList<Integer> correctAnswer;

    public static FinishFragment newInstance(ArrayList<Integer> correctAnswer) {
        FinishFragment finishFragment = new FinishFragment();
        Bundle b1 = new Bundle();
        b1.putIntegerArrayList("correctAnswer", correctAnswer);
        finishFragment.setArguments(b1);
        return finishFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_finish, container, false);
        TextView results = v.findViewById(R.id.results);
        if (getArguments() != null) {
            correctAnswer = getArguments().getIntegerArrayList("correctAnswer");
        }
        int totalAnswer = 0;
        String compiledresult ="";
        int i=1;
        for (Integer num : correctAnswer) {
            totalAnswer += num;
            compiledresult += "Question " +i+ ": " +num+ " point(s)\n";
            i++;
        }
        String display = compiledresult + "Total Score:>>>>> "+ totalAnswer;
        results.setText(display);
        return v;
    }
}