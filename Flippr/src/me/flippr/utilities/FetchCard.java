package me.flippr.utilities;

import android.os.AsyncTask;
import android.util.Log;

public class FetchCard extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... url) {
        try {
        	Log.v("test","http://flippr.me/card/" + url[0]);
        	return CustomHttpClient.executeHttpGet("http://flippr.me/card/" + url[0]);
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String string) {
        // TODO: check this.exception 
        // TODO: do something with the feed
    }
}
