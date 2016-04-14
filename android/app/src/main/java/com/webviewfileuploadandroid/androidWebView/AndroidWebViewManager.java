package com.webviewfileuploadandroid.androidWebView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.webview.ReactWebViewManager;
import com.webviewfileuploadandroid.MainActivity;

/**
 * Created by ydq on 16/4/12.
 */
public class AndroidWebViewManager extends ReactWebViewManager {


    private Activity mActivity = null;
    public String getName() {
        return "AndroidWebView";
    }

    @ReactProp(name = "uploadEnabledAndroid")
    public void uploadEnabledAndroid(WebView view, boolean enabled) {
        if(enabled) {
            view.setWebChromeClient(new WebChromeClient(){

                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                    ((MainActivity)mActivity).setUploadMessage(uploadMsg);
                    openFileChooserView();

                }

                public boolean onJsConfirm (WebView view, String url, String message, JsResult result){
                    return true;
                }

                public boolean onJsPrompt (WebView view, String url, String message, String defaultValue, JsPromptResult result){
                    return true;
                }

                // For Android < 3.0
                public void openFileChooser(ValueCallback<Uri> uploadMsg) {
                    ((MainActivity)mActivity).setUploadMessage(uploadMsg);
                    openFileChooserView();
                }

                // For Android  > 4.1.1
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                    ((MainActivity)mActivity).setUploadMessage(uploadMsg);
                    openFileChooserView();
                }

                // For Android > 5.0
                public boolean onShowFileChooser (WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                    ((MainActivity)mActivity).setmUploadCallbackAboveL(filePathCallback);
                    openFileChooserView();
                    return true;
                }

                private void openFileChooserView(){
                    try {
                        final Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                        galleryIntent.setType("image/*");
                        final Intent chooserIntent = Intent.createChooser(galleryIntent, "choose file");

                        mActivity.startActivityForResult(chooserIntent, 1);
                    } catch (Exception e) {
                        Log.d("error", e.toString());
                    }
                }
            });
        }
    }


    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

}


