package com.xyrality.gmbh.app;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import java.util.List;

/**
 * Created by yarik on 26.09.15.
 */
public class ActivityGameWorldsList extends Activity implements LoaderManager.LoaderCallbacks<List<GameWorld>>{

    private ExpandableListView gameWorldsistView;

    private static final String GAME_WORLDS_URL = "http://backend1.lordsandknights.com/XYRALITY/WebObjects/BKLoginServer.woa/wa/worlds";

    private static final String URL_KEY = "url";

    private static final int LOADER_GAME_WORLDS_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_worlds);

        gameWorldsistView = (ExpandableListView) findViewById(R.id.gameWorldsListView);

        startGameWorldsLoader();
    }

    private void startGameWorldsLoader() {
        Bundle loaderArgs = new Bundle();
        loaderArgs.putString(URL_KEY, GAME_WORLDS_URL);
        getLoaderManager().initLoader(LOADER_GAME_WORLDS_ID, loaderArgs, this);
    }

    @Override
    public Loader<List<GameWorld>> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_GAME_WORLDS_ID && args != null) {
            String url = args.getString(URL_KEY);
            if (url != null) {
                return new GameWorldsLoader(ActivityGameWorldsList.this, url);
            }
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<GameWorld>> loader, List<GameWorld> worldsList) {
        if (worldsList != null && worldsList.size() > 0) {
            Log.d("size", String.valueOf(worldsList.size()));
        }
    }

    @Override
    public void onLoaderReset(Loader<List<GameWorld>> loader) {

    }
}
