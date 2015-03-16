package com.codepath.apps.mysimpletweets.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codepath.apps.mysimpletweets.ComposeActivity;
import com.codepath.apps.mysimpletweets.ProfileActivity;
import com.codepath.apps.mysimpletweets.R;
import com.codepath.apps.mysimpletweets.TweetsArrayAdapter;
import com.codepath.apps.mysimpletweets.TwitterApplication;
import com.codepath.apps.mysimpletweets.TwitterClient;
import com.codepath.apps.mysimpletweets.models.EndlessScrollListener;
import com.codepath.apps.mysimpletweets.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pleasure on 3/7/2015.
 */
public abstract class TweetsListFragment extends Fragment {
    protected SwipeRefreshLayout swipeLayout;
    private TweetsArrayAdapter aTweets;
    private ArrayList<Tweet> tweets;
    private ListView lvTweets;
    protected TwitterClient client;






    // inflation logic

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        /*swipeLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_container);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchNewTweets();
            }
        });

        swipeLayout.setColorScheme(android.R.color.holo_blue_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_red_dark);*/

        client = TwitterApplication.getRestClient();

        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(aTweets);

        lvTweets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ProfileActivity.class);
                i.putExtra("screen_name", tweets.get(position).getUser().getScreenName());
                Log.d ("DEBUG","OnItemClick: " + tweets.get(position).getUser().getScreenName() );
                startActivity(i);
            }
        });



        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                //customLoadMoreDataFromApi(page);
                if (tweets.size() < 1) {
                    populateTimeline(-1, -1);
                } else {
                    populateTimeline(-1, tweets.get(tweets.size() - 1).getUid() - 1);
                }
            }
        });

        return v;
    }


    private void populateTimeline(long since, long max) {
//        swipeLayout.setRefreshing(true);
        client.getHomeTimeline(since, max, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONArray jsonArray) {

                // ...the data has come back, add new items to your adapter...

                Log.d("DEBUG", "Array length" + jsonArray.length());
                Log.d("DEBUG", jsonArray.toString());

                addAll(Tweet.fromJSONArray(jsonArray));
            }

            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject e) {
                Log.d("DEBUG", "Fetch timeline error: " + e.toString());
            }

            @Override
            public void onFinish() {
//                swipeLayout.setRefreshing(false);
            }
        });
    }


    // creation lifecycle event
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        tweets = new ArrayList<>();

        aTweets = new TweetsArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        aTweets.addAll(tweets);
    }

    public void fetchNewTweets() {
//        swipeLayout.setRefreshing(true);

        tweets.clear();
        aTweets.notifyDataSetChanged();
        populateTimeline(-1,-1);
    }
}
