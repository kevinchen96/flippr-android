package me.flippr.utilities;

import java.io.IOException;

import me.flippr.R;

import org.apache.http.impl.client.DefaultHttpClient;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class AppInfo extends Activity {
    DefaultHttpClient http_client = new DefaultHttpClient();
    Activity activity;
    String TAG = "TGtracker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_info);
        activity = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Log.v(TAG, "resuming activity");
        AccountManager accountManager = AccountManager.get(getApplicationContext());
        //if result is null, you might not have a valid internet connection
        Log.i(TAG, "got token, yipee: "+updateToken(accountManager, true));
    }


    @SuppressWarnings("deprecation")
	private String updateToken(AccountManager am, boolean invalidateToken) {
        String authToken = "null";
        try {
            Account[] accounts = am.getAccountsByType("com.google");
            AccountManagerFuture<Bundle> accountManagerFuture;
            if(activity == null){//this is used when calling from an interval thread
                accountManagerFuture = am.getAuthToken(accounts[0], "android", false, null, null);
            } else {
                accountManagerFuture = am.getAuthToken(accounts[0], "android", null, activity, null, null);
            }
            Bundle authTokenBundle = null;
			try {
				authTokenBundle = accountManagerFuture.getResult();
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (AuthenticatorException e) {

				e.printStackTrace();
			} 
            authToken = authTokenBundle.getString(AccountManager.KEY_AUTHTOKEN).toString();
            Log.v(TAG, "newToken preinvalidate: "+authToken);

            if(invalidateToken) {
                am.invalidateAuthToken("com.google", authToken);
                authToken = updateToken(am, false);

            }
        } catch (IOException e) {
            Log.e(TAG, "the exception was: "+e.toString());
            e.printStackTrace();
        }
        return authToken;
    }
}