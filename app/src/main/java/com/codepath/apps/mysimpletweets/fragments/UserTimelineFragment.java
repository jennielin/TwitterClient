package com.codepath.apps.mysimpletweets.fragments;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Pleasure on 3/10/2015.
 */
public class UserTimelineFragment extends TweetsListFragment {
    // debug tip from Anuj
    // private final String TAG = getClass().getSimpleName().toString();
    // Log.d(TAG, "");

    protected String user_id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        client = new TwitterApplication().getRestClient();
        populateTimeline();



    }

    //  data from activity to fragment
    public static UserTimelineFragment newInstance(String screen_name) {
        UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();

        args.putString("screen_name", screen_name);
        userFragment.setArguments(args);
        return userFragment;
    }


    private void populateTimeline() {
        String screenName = getArguments().getString("screen_name");
        Log.d ("DEBUG","User pupulateTimeline: " + screenName);
        client.getUserTimeline(screenName, 1, -1, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                Log.d("DEBUG", json.toString());
                // clearAdapter();
                addAll(Tweet.fromJSONArray(json));
                //Log.d("DEBUG", aTweets.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("DEBUG", errorResponse.toString());
            }

        });
    }
}
