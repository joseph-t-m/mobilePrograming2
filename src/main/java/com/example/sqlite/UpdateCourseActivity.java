package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.R;

public class UpdateCourseActivity extends AppCompatActivity {

    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private Button updateCourseBtn, deleteCourseBtn;
    private DBHandler dbHandler;
    String courseName, courseDesc, courseDuration, courseTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseTracksEdt = findViewById(R.id.idEdtCourseTracks);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        // Initializing our dbhandler class.
        dbHandler = new DBHandler(UpdateCourseActivity.this);

        // Getting data
        courseName = getIntent().getStringExtra("name");
        courseDesc = getIntent().getStringExtra("description");
        courseDuration = getIntent().getStringExtra("duration");
        courseTracks = getIntent().getStringExtra("tracks");

        // setting data
        courseNameEdt.setText(courseName);
        courseDescriptionEdt.setText(courseDesc);
        courseTracksEdt.setText(courseTracks);
        courseDurationEdt.setText(courseDuration);

        // adding on click listener to our update course button.
        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Calling an update course method and passing all the edit text values.
                dbHandler.updateCourse(courseName, courseNameEdt.getText().toString(), courseDescriptionEdt.getText().toString(),
                        courseTracksEdt.getText().toString(), courseDurationEdt.getText().toString());

                Toast.makeText(UpdateCourseActivity.this, "Course Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateCourseActivity.this, MainActivity.class);
                startActivity(i);
            }//onClick
        });//setOnClickListener

        // adding on click listener for delete button to delete our course.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete the course.
                dbHandler.deleteCourse(courseName);
                Toast.makeText(UpdateCourseActivity.this, "Deleted the course", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateCourseActivity.this, MainActivity.class);
                startActivity(i);
            }//onClick
        });//setOnClickListener
    }//onCreate
}//UpdateCourseActivity
