package com.tiego.makaleng.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class TaskContract {

    /* TaskEntry is an inner class that defines the contents of the task table */
    public static final class TaskEntry implements BaseColumns {

        // Task table and column names
        public static final String TABLE_NAME = "tasks";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below        
		public final static String _ID = BaseColumns._ID;
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_PRIORITY = "priority";

    }
}
