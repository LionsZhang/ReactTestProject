/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow

 */

import React, { Component } from 'react';
import Home from './js_modules/home/home'
import {
  AppRegistry
} from 'react-native';

export default class HelloWorld extends Component {
  render() {
    return (
      <Home/>
    );
  }
}
//TestActivity 注册的Activity组件名，  HelloWorld指向渲染的H5组件名，展示的页面
AppRegistry.registerComponent('MainActivity', () => HelloWorld);
