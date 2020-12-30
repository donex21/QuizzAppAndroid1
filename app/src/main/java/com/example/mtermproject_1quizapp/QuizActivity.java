package com.example.mtermproject_1quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    Fragment fragment1, fragment2, fragment3, fragment4, fragment5;
    Button nextBtn;
    int counter = 0;
    Question[] archiveQuestions;
    List<Integer> listIndex;
    ArrayList<Integer> correctAnswer = new ArrayList<>(20);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final TextView textviewItems = findViewById(R.id.textViewItems);
        nextBtn = findViewById(R.id.nextBtn);
        fragment1 = new TrueOrFalseFragment();
        fragment2 = new IdentificationFragment();
        fragment3 = new MultipleChoiceFragment();
        fragment4 = new ManyAnswerFragment();
        fragment5 = new FinishFragment();
        archiveQuestions = new Question[]{
                new Question("First",getResources().getString(R.string.q_one),getResources().getStringArray(R.array.opt_ans_one)),
                new Question("First",getResources().getString(R.string.q_two),getResources().getStringArray(R.array.opt_ans_two)),
                new Question("First",getResources().getString(R.string.q_three),getResources().getStringArray(R.array.opt_ans_three)),
                new Question("First",getResources().getString(R.string.q_four),getResources().getStringArray(R.array.opt_ans_four)),
                new Question("First",getResources().getString(R.string.q_five),getResources().getStringArray(R.array.opt_ans_five)),
                new Question("Second",getResources().getString(R.string.q_six),getResources().getStringArray(R.array.opt_ans_six)),
                new Question("Second",getResources().getString(R.string.q_seven),getResources().getStringArray(R.array.opt_ans_seven)),
                new Question("Second",getResources().getString(R.string.q_eight),getResources().getStringArray(R.array.opt_ans_eight)),
                new Question("Second",getResources().getString(R.string.q_nine),getResources().getStringArray(R.array.opt_ans_nine)),
                new Question("Second",getResources().getString(R.string.q_ten),getResources().getStringArray(R.array.opt_ans_ten)),
                new Question("Third",getResources().getString(R.string.q_eleven),getResources().getStringArray(R.array.opt_ans_eleven)),
                new Question("Third",getResources().getString(R.string.q_twelve),getResources().getStringArray(R.array.opt_ans_twelve)),
                new Question("Third",getResources().getString(R.string.q_thirteen),getResources().getStringArray(R.array.opt_ans_thirteen)),
                new Question("Third",getResources().getString(R.string.q_forteen),getResources().getStringArray(R.array.opt_ans_forteen)),
                new Question("Third",getResources().getString(R.string.q_fifteen),getResources().getStringArray(R.array.opt_ans_fifteen)),
                new Question("Forth",getResources().getString(R.string.q_sixteen),getResources().getStringArray(R.array.opt_ans_sixteen)),
                new Question("Forth",getResources().getString(R.string.q_seventeen),getResources().getStringArray(R.array.opt_ans_seventeen)),
                new Question("Forth",getResources().getString(R.string.q_eighteen),getResources().getStringArray(R.array.opt_ans_eighteen)),
                new Question("Forth",getResources().getString(R.string.q_nineteen),getResources().getStringArray(R.array.opt_ans_nineteen)),
                new Question("Forth",getResources().getString(R.string.q_twenty),getResources().getStringArray(R.array.opt_ans_twenty))
        };

        //shuffle the index of the archive question.
        listIndex = new ArrayList<>();
        for(int i = 0; i < archiveQuestions.length; i++)
            listIndex.add(i);
        Collections.shuffle(listIndex);
        Question firstquestion = archiveQuestions[listIndex.get(counter)];
        counter++;
        if (firstquestion.getCategory().equals("First")){
            TrueOrFalseFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
            getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment1).commit();
        }else if (firstquestion.getCategory().equals("Second")){
            fragment2 = IdentificationFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
            getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment2).commit();
        }else if (firstquestion.getCategory().equals("Third")) {
            fragment3 = MultipleChoiceFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
            getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment3).commit();
        }else{
            fragment4 = ManyAnswerFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
            getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment4).commit();
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter < listIndex.size()) {
                    if (counter == (listIndex.size() - 1)) {
                        nextBtn.setText("Finish");
                    }
                    Question firstquestion = archiveQuestions[listIndex.get(counter)];
                    counter++;
                    String items = " " + counter + "/" + listIndex.size();
                    textviewItems.setText(items);
                    if (firstquestion.getCategory().equals("First")) {
                        fragment1 = TrueOrFalseFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
                        getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment1).commit();
                    } else if (firstquestion.getCategory().equals("Second")){
                        fragment2 = IdentificationFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
                        getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment2).commit();
                    }else if (firstquestion.getCategory().equals("Third")) {
                        fragment3 = MultipleChoiceFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
                        getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment3).commit();
                    }else {
                        fragment4 = ManyAnswerFragment.newInstance(firstquestion.getQuestionnaire(),firstquestion.getOptionAns());
                        getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment4).commit();
                    }

                }else if(counter == listIndex.size()){
                    nextBtn.setText("Back>>");
                    textviewItems.setText("");
                    fragment5 = FinishFragment.newInstance(correctAnswer);
                    getSupportFragmentManager().beginTransaction().replace(R.id.FlFragment, fragment5).commit();
                    counter++;
                }else if (counter > listIndex.size()){
                    finish();
                }
            }
        });
    }
    public void Points(int point) {
        correctAnswer.add(point);
    }
}