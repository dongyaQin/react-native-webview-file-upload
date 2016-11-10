/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 */

import React, {Component} from 'react';
import {
    Dimensions
} from 'react-native';

import AndroidWebView from './AndroidWebView'

export default class App extends Component {
  render() {
    return (
      <AndroidWebView
          style={{height: Dimensions.get('window').height}}
          source={require('./fileUpload.html')}
          javaScriptEnabled={true}
          domStorageEnabled={true}
          startInLoadingState={true}
          scalesPageToFit={true}/>
    );
  }
}
