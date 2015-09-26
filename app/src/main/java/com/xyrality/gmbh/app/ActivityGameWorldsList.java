package com.xyrality.gmbh.app;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button changeCredentialsButton;

    private Button refreshButton;

    private static final String CHANGE_CREDENTIALS_DIALOG_TAG = "CHANGE_CREDENTIALS_DIALOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_worlds);

        gameWorldsistView = (ExpandableListView) findViewById(R.id.gameWorldsListView);

        changeCredentialsButton = (Button) findViewById(R.id.changeCredentialsButton);
        refreshButton = (Button) findViewById(R.id.refreshButton);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameWorldsLoader(false);
            }
        });

        changeCredentialsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangindDialog();
            }
        });

        startGameWorldsLoader(true);
    }

    private void startChangindDialog() {
        ChangeCredentialsDialogFragment dialog = new ChangeCredentialsDialogFragment();
        dialog.show(getFragmentManager(), CHANGE_CREDENTIALS_DIALOG_TAG);
    }

    public void startGameWorldsLoader(boolean init) {
        Bundle loaderArgs = new Bundle();
        loaderArgs.putString(URL_KEY, GAME_WORLDS_URL);
        if (init) {
            getLoaderManager().initLoader(LOADER_GAME_WORLDS_ID, loaderArgs, this);
        } else {
            getLoaderManager().restartLoader(LOADER_GAME_WORLDS_ID, loaderArgs, this);
        }
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
        if (loader.getId() == LOADER_GAME_WORLDS_ID && worldsList != null && worldsList.size() > 0) {
            GameWorldsAdapter adapter = new GameWorldsAdapter(ActivityGameWorldsList.this, worldsList);
            gameWorldsistView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            if (gameWorldsistView != null && gameWorldsistView.getAdapter().getCount() > 0) {
                gameWorldsistView.expandGroup(0);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<GameWorld>> loader) {

    }
}
