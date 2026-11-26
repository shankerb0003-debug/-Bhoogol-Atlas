package com.bhawanishankar.bhoogolatlas;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.atlasWebView);
        adView = findViewById(R.id.adView);

        // WebView Configuration
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(false);

        webView.addJavascriptInterface(new WebAppInterface(), "AndroidAdMob");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.startsWith("mailto:") || url.startsWith("tel:")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                    return true;
                }
                return false;
            }
        });

        webView.loadUrl("file:///android_asset/index.html");

        // Initialize Google AdMob SDK & Load Banner + Interstitial
        MobileAds.initialize(this, initializationStatus -> {});
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        loadInterstitial();
    }

    private void loadInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(
            this,
            getString(R.string.admob_interstitial_id),
            adRequest,
            new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    mInterstitialAd = null;
                }
            }
        );
    }

    public class WebAppInterface {
        @JavascriptInterface
        public void showInterstitial(String unitId) {
            runOnUiThread(() -> {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                    loadInterstitial();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
