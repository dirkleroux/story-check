package za.co.storycheck;

import com.google.analytics.tracking.android.EasyTracker;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import za.co.storycheck.data.DbHelper;

public class EditStoryActivity extends SherlockFragmentActivity {

    private EditText et_headline;
    private long id;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_edit_activity);
        et_headline = (EditText) findViewById(R.id.et_headline);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.edit_story));
        Bundle extras = getIntent().getExtras();
        id = extras.getLong("storyId");
        final String headline = extras.getString("headline");
        et_headline.setText(headline);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EasyTracker.getInstance(this).activityStart(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EasyTracker.getInstance(this).activityStart(this);
    }

    private void save() {
        DbHelper dbHelper = DbHelper.getHelper(this);
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("headline", et_headline.getText().toString());
        values.put("last_edit_date", System.currentTimeMillis());
        writableDatabase.beginTransaction();
        try {
            writableDatabase.update("Story", values, "_id = ?", new String[]{String.valueOf(id)});
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
//            writableDatabase.close();
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.ok_cancel_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
            case R.id.mi_cancel:
                finish();
                return true;
            case R.id.mi_ok:
                save();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
