/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, {Component} from 'react';
import{
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Dimensions,
} from 'react-native';

import WebViewForUploadImages from './WebViewForUploadImages'

class WebviewFileUploadAndroid extends Component {
    render() {
        return (
            <View style={{height: Dimensions.get('window').height}}>
                <WebViewForUploadImages />
            </View>
        );
    }
}

AppRegistry.registerComponent('WebviewFileUploadAndroid', () => WebviewFileUploadAndroid);
