/**
 * Created by lionszhang on 2017/7/13.
 */
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,TouchableHighlight
} from 'react-native';
import NativeAndroid from '../common_native/native';
export default class Home extends Component {
  _onPressButton() {
    console.log("You tapped the button!");
   NativeAndroid.jumpActivity(NativeAndroid.JUMP_KEY,"显示JS传给原生的信息")
    // React.NativeModules.jumpActivity(NativeAndroid.JUMP_KEY,"显示JS传给原生的信息")
  }
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
           <TouchableHighlight  onPress={this._onPressButton}>
                <Text style={styles.text_style}>可点击的button</Text>
              </TouchableHighlight>
      </View>
    );
  }
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#629eff',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#FF0000',
    marginBottom: 5,
  },
    buttonBg: {
     justifyContent: 'center',
      alignItems: 'center',
      backgroundColor: '#f99709',
      width:180 ,
      height:50
    }
    ,
      text_style: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
        color: '#ff3f40'
      }
});

