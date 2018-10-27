package com.tiego.makaleng;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.tiego.makaleng.dao.TaskDaoImpl;
import com.tiego.makaleng.data.TaskContract;
import com.tiego.makaleng.executors.AppExecutors;
import com.tiego.makaleng.model.TaskEntry;

public class AddTaskActivity extends AppCompatActivity {

   // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    private static final String TAG = AddTaskActivity.class.getSimpleName();
    private TaskDaoImpl mTaskDb;
    private TaskEntry mTaskEntry;
    // Declare a member variable to keep track of a task's selected mPriority
    private int mPriority;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        mTaskDb = new TaskDaoImpl(this);
        // Initialize to highest mPriority by default (mPriority = 1)
        ((RadioButton) findViewById(R.id.radButton1)).setChecked(true);
        mPriority = 1;
    }

    /**
     * onClickAddTask is called when the "ADD" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onClickAddTask(View view) {
        // Not yet implemented
        // Check if EditText is empty, if not retrieve input and store it in a ContentValues object
        // If the EditText input is empty -> don't create an entry
        final String input = ((EditText) findViewById(R.id.editTextTaskDescription)).getText().toString();
        if (input.length() == 0) {
            return;
        }

        Thread backgroundDataInsertTask = new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v(TAG, "Thread called.");

                mTaskEntry = new TaskEntry(input, mPriority);
                if (mTaskEntry != null) {
                    long id =  mTaskDb.insertTask(mTaskEntry);
                    if (id > 0) {
                        finish();
                    } else {
                        Log.e(TAG, "Error adding a task.");
                    }
                }
            }
        });
        backgroundDataInsertTask.start();

        /**
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       mTaskEntry = new TaskEntry(input, mPriority);
                       if (mTaskEntry != null) {
                           long id =  mTaskDb.insertTask(mTaskEntry);
                           if (id > 0) {
                              finish();
                           } else {
                               Context context = AddTaskActivity.this;
                               String message = "Error adding a task!";
                               int duration = Toast.LENGTH_SHORT;
                               Toast.makeText(context, message, duration).show();
                           }
                       }
                    }
                });
            }
        }); */
    }


    /**
     * onPrioritySelected is called whenever a priority button is clicked.
     * It changes the value of mPriority based on the selected button.
     */
    public void onPrioritySelected(View view) {
        if (((RadioButton) findViewById(R.id.radButton1)).isChecked()) {
            mPriority = 1;
        } else if (((RadioButton) findViewById(R.id.radButton2)).isChecked()) {
            mPriority = 2;
        } else if (((RadioButton) findViewById(R.id.radButton3)).isChecked()) {
            mPriority = 3;
        }
    }
}
