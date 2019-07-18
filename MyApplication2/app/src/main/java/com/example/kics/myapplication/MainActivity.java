import com.example.kics.myapplication.

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.VolumeShaper;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;



import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String WEB_URL;
    private String currentUrl;

    private WebView webView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView_main);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_main);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient( new SubClassWebViewClient());

        WEB_URL = this.getResources().getString(R.string.load_url);

        if( savedInstanceState == null )
            webView.loadUrl( WEB_URL );

        currentUrl = null;

        // badge count
        int badgeCount = readBadgeCount( getApplicationContext() );
        setBadge(getApplicationContext(), badgeCount);


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState ){
        super.onSaveInstanceState(savedInstanceState);

        webView.saveState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState(savedInstanceState);

        webView.restoreState(savedInstanceState);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(webView != null && keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack() ){
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private class SubClassWebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Log.d(TAG, "ps_url: " + Uri.parse(url).getHost() );

            if (Uri.parse(url).getHost().equals(getResources().getString(R.string.pc_host))) {
                //if( url.equals(getResources().getString(R.string.pc_host))){
                return false;
            }

            if( url.equals(currentUrl) ){
                webView.goBack();

            } else  {

                if( url.equals(getResources().getString(R.string.home_url)) && (currentUrl != null && currentUrl.equals(getResources().getString(R.string.login_url))) ){
                    view.loadUrl(getResources().getString(R.string.login_url));
                    currentUrl = getResources().getString(R.string.login_url);
                    finish();

                } else {
                    view.loadUrl(url);
                    currentUrl = url;
                }
            }

            return true;
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (mProgressBar != null) mProgressBar.setVisibility(View.VISIBLE);
        }

    }


    public static void setBadge(final Context context, final int count) {
        final String launcherClassName = getLauncherClassName(context);

        if (launcherClassName == null) {
            return;
        }

        final Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count > 0? count : null);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1 ){
            intent.setFlags(0x00000020);
        }


        context.sendBroadcast(intent);
    }

    private static String getLauncherClassName(Context context) {
        final PackageManager pm = context.getPackageManager();
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);

        for (final ResolveInfo resolveInfo : resolveInfos) {
            final String pkgName = resolveInfo.activityInfo.applicationInfo.packageName;
            if (pkgName.equalsIgnoreCase(context.getPackageName())) {
                String className = resolveInfo.activityInfo.name;
                return className;
            }
        }

        return null;
    }

    private static int readBadgeCount(Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = sharedPref.edit();

        int badgeCount = sharedPref.getInt("badgeCount", 0 );

        if( badgeCount > 0 )
            badgeCount = 0;
        //badgeCount = badgeCount - 1;


        editor.putInt("badgeCount", badgeCount );
        editor.commit();

        Log.d(TAG, "badgeCount = " + badgeCount );

        return badgeCount;
    }



}
