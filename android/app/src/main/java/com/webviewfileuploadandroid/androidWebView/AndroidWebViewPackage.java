package com.webviewfileuploadandroid.androidWebView;

import android.app.Activity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ydq on 16/3/21.
 */
public class AndroidWebViewPackage implements ReactPackage {
    private Activity mActivity = null;

    public AndroidWebViewPackage(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }


    @Override
    public List<ViewManager> createViewManagers(
            ReactApplicationContext reactContext) {

        AndroidWebViewManager awm = new AndroidWebViewManager();
        awm.setmActivity(mActivity);
        return Arrays.<ViewManager>asList(awm
        );
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
