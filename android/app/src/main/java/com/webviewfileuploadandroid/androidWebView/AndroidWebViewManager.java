package com.webviewfileuploadandroid.androidWebView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.webview.ReactWebViewManager;
import com.webviewfileuploadandroid.MainActivity;

/**
 * Created by ydq on 16/4/12.
 */
public class AndroidWebViewManager extends ReactWebViewManager {


    private Activity mActivity = null;
    private AndroidWebViewPackage aPackage;
    public String getName() {
        return "AndroidWebView";
    }

    @ReactProp(name = "uploadEnabledAndroid")
    public void uploadEnabledAndroid(WebView view, boolean enabled) {
        if(enabled) {
            final AndroidWebViewModule module = this.aPackage.getModule();
            view.setWebChromeClient(new WebChromeClient(){

                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
                    ((MainActivity)module.getActivity()).setUploadMessage(uploadMsg);
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
                    ((MainActivity)module.getActivity()).setUploadMessage(uploadMsg);
                    openFileChooserView();
                }

                // For Android  > 4.1.1
                public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                    ((MainActivity)module.getActivity()).setUploadMessage(uploadMsg);
                    openFileChooserView();
                }

                // For Android > 5.0
                public boolean onShowFileChooser (WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                    Log.d("customwebview", "onShowFileChooser");

                    ((MainActivity)module.getActivity()).setmUploadCallbackAboveL(filePathCallback);
                    openFileChooserView();
                    return true;
                }

                private void openFileChooserView(){
                    try {
                        final Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                        galleryIntent.setType("image/*");
                        final Intent chooserIntent = Intent.createChooser(galleryIntent, "choose file");
                        module.getActivity().startActivityForResult(chooserIntent, 1);
                    } catch (Exception e) {
                        Log.d("customwebview", e.toString());
                    }
                }
            });
        }
    }

    public void setPackage(AndroidWebViewPackage aPackage){
        this.aPackage = aPackage;
    }

    public AndroidWebViewPackage getPackage(){
        return this.aPackage;
    }
}


