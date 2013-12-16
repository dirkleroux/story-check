package za.co.storycheck;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class StoryActivity extends FragmentActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity);
        String headline = getIntent().getExtras().getString("headline");
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(headline);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.story_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            finish();
            return true;
        case R.id.mi_edit:
            Intent intent = new Intent(this, EditStoryActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            Bundle extras = getIntent().getExtras();
            intent.putExtra("storyId", extras.getLong("storyId"));
            intent.putExtra("headline", extras.getString("headline"));
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
