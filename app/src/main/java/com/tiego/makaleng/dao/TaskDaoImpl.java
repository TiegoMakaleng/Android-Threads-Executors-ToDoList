package com.tiego.makaleng.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tiego.makaleng.data.TaskContract;
import com.tiego.makaleng.data.TaskDbHelper;
import com.tiego.makaleng.model.TaskEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tiego Makaleng on 10/9/2018.
 */

public class TaskDaoImpl implements TaskDao {
    private SQLiteDatabase mDb;
    private TaskDbHelper mTaskDbHelper;

    // Columns which we are interested in displaying
    private static final String[] TASKS_PROJECTION = {
            TaskContract.TaskEntry.COLUMN_DESCRIPTION,
            TaskContract.TaskEntry.COLUMN_PRIORITY
    };

    private static final String TABLE_NAME = TaskContract.TaskEntry.TABLE_NAME;

    public TaskDaoImpl (Context context) {
        this.mTaskDbHelper = new TaskDbHelper(context);
        this.mDb = mTaskDbHelper.getWritableDatabase();
    }

    @Override
    public List<TaskEntry> loadAllTasks() {
        List<TaskEntry> tasks = new ArrayList<>();

        String table = TABLE_NAME;
        String [] columns = TASKS_PROJECTION;
        String selection = null;
        String [] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = null;

        Cursor cursor = mDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);

        int descriptionCol = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESCRIPTION);
        int priorityCol = cursor.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY);

        while (cursor.moveToNext()) {
            String description = cursor.getString(descriptionCol);
            int priority = cursor.getInt(priorityCol);
            TaskEntry taskEntry = new TaskEntry(description, priority);
            tasks.add(taskEntry);
        }
        return tasks;
    }

    @Override
    public long insertTask(TaskEntry taskEntry) {

        String table = TABLE_NAME;
        String nullColumnHack = TaskContract.TaskEntry.COLUMN_DESCRIPTION;
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, taskEntry.getDescription());
        values.put(TaskContract.TaskEntry.COLUMN_PRIORITY, taskEntry.getPriority());

        // Add data into the database
        return mDb.insert(table, nullColumnHack, values);
    }

    @Override
    public int updateTask(TaskEntry taskEntry) {
      String table = TABLE_NAME;

      ContentValues values = new ContentValues();
      values.put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, taskEntry.getDescription());
      values.put(TaskContract.TaskEntry.COLUMN_PRIORITY, taskEntry.getPriority());

      String whereClause = TaskContract.TaskEntry._ID + "=?";
      String [] whereArgs = {String.valueOf(taskEntry.getId())};
      // Update the selected task
      return  mDb.update(table, values, whereClause, whereArgs);
    }

    @Override
    public int deleteTask(TaskEntry taskEntry) {
      String table = TABLE_NAME;
      String whereClause = TaskContract.TaskEntry._ID + "=?";
      String[] whereArgs = {String.valueOf(taskEntry.getId())};
      // delete a record/task from the database
      return mDb.delete(table, whereClause, whereArgs);
    }
}
