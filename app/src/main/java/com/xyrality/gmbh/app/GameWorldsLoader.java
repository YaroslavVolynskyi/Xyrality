package com.xyrality.gmbh.app;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarik on 26.09.15.
 */
public class GameWorldsLoader extends AsyncTaskLoader<List<GameWorld>> {

    private Context context;

    private String url;

    private List<NameValuePair> parameters;

    private List<GameWorld> gameWorldList;

    public GameWorldsLoader(Context context, String url) {
        super(context);
        this.context = context;
        this.url = url;
    }

    @Override
    public List<GameWorld> loadInBackground() {
        parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("login", UserAccount.getInstance().getLogin()));
        parameters.add(new BasicNameValuePair("password", UserAccount.getInstance().getPassword()));
        parameters.add(new BasicNameValuePair("deviceType", UserAccount.getInstance().
                getDevice().getDeviceType()));
        parameters.add(new BasicNameValuePair("deviceId", UserAccount.getInstance().
                getDevice().getDeviceId()));

        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;
        String responseString = null;
        try {
            HttpPost httpPost = new HttpPost(this.url);
            httpPost.addHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.addHeader(new BasicHeader("Accept", "application/json"));
            httpPost.setEntity(new UrlEncodedFormEntity(parameters));
            response = client.execute(httpPost);
            responseString = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            Log.e("protocol exc", e.getMessage());
        } catch (IOException e) {
            Log.e("io exc", e.getMessage());
        }

        if (gameWorldList == null) {
            gameWorldList = new ArrayList<>();
        }

        if (responseString != null) {
            JSONObject worldsJSONObject = (JSONObject) JSONValue.parse(responseString);
            JSONArray worldsJSONArray = (JSONArray) worldsJSONObject.get(GameWorld.getWorldsKey());
            if (worldsJSONArray != null) {
                for (int i = 0; i < worldsJSONArray.size(); i++) {
                    JSONObject worldJSON = (JSONObject) worldsJSONArray.get(i);
                    gameWorldList.add(new GameWorld(worldJSON));
                }
            }
        }

        return gameWorldList;
    }

    @Override
    protected void onStartLoading() {
        if (this.gameWorldList != null) {
            deliverResult(this.gameWorldList);
        }

        if (takeContentChanged() || (this.gameWorldList == null)) {
            forceLoad();
        }
    }
}
