package com.example.firebasememorybox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddMemoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    // How to implement a Spinner
    // https://www.tutorialspoint.com/how-to-get-spinner-value-in-android
    //thanks kyle, not praneet
    // How to style the spinner
    // https://www.youtube.com/watch?v=7tnlh1nVkuE


    Spinner spinner;
    EditText memoryName, memoryDesc;
    String spinnerSelectedText = "none";
    int value;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        memoryName = findViewById(R.id.memoryName);
        memoryDesc = findViewById(R.id.memoryDesc);

        // this attaches my spinner design (spinner_list.xml) and my array of spinner choices(R.array.memoryRating)
        spinner = findViewById(R.id.memorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                getResources().getStringArray(R.array.memoryArray));

        // this attaches my custom row design (how I want each row to look)
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        value=Integer.parseInt(spinnerSelectedText);
        Toast.makeText(parent.getContext(), spinnerSelectedText, Toast.LENGTH_SHORT).show();
    }
    // This method is required, even if empty, for the OnItemSelectedListener to work
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void addMemoryButtonClicked(View view) {
        String memName = memoryName.getText().toString();
        String memDesc = memoryDesc.getText().toString();
        int memoryRatingNum = 0;
        memoryRatingNum = Integer.parseInt(spinnerSelectedText);
        // This will take the option they clicked on and ensure it is a number.
// My options went from 5 to 1, so that is why I have it adjusted with 6-i
// I also had an instruction statement as my first line in my string array
// ADJUST THIS LOOP TO MATCH YOUR CODE!

// Note the syntax here for how to access an index of a string array within
// the java
        //code for for loop if my array ncluded string
//        for (int i = 0; i < 5 ; i++) {
//            if (spinnerSelectedText.equals(getResources().
//                    getStringArray(R.array.memoryArray)[i])) {
//                memoryRatingNum = i+1;
//                break;
//            }
//        }
//
        Memory m = new Memory(memoryRatingNum, memName, memDesc);
       SignInActivity.firebaseHelper.addData(m);
//
        memoryName.setText("");
        memoryDesc.setText("");
//    }
    }

    }

