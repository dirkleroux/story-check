package za.co.storycheck.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import za.co.storycheck.R;
import za.co.storycheck.data.RawQueryLoader;

/**
 * Created with IntelliJ IDEA.
 * User: dirk
 * Date: 2013/09/06
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class StoryListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;
    private ListView listView;
    private String[] select = new String[]{"headline", "type"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.story_type_fragment, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.lv_story_type);
        adapter = new SimpleCursorAdapter(getActivity(), R.layout.story_row, null, select, new int[] {R.id.tv_label, R.id.tv_type}, 0);
        listView.setAdapter(adapter);
        getLoaderManager().initLoader(0, savedInstanceState, this);
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new RawQueryLoader(getActivity(), R.string.sql_query_all_Story, null);
    }

    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        adapter.swapCursor(null);
    }
}