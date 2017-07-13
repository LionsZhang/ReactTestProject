
1.真机运行，adb reverse tcp:8081 tcp:8081
    npm start,开启服务
    gradlew installDebug  安装包
    gradlew assembleDebug  生成debug包
2.npm安装react,react-native库
    $ npm init
    $ npm install --save react react-native
    $ curl -o .flowconfig https://raw.githubusercontent.com/facebook/react-native/master/.flowconfig
    npm install react-native@0.41.2 //指定安装版本
    npm install react //指定安装版本
    "react-native": "^0.41.2",
    "react": "^15.4.2",
 3.git 命令
 git remote -v # 查看远程服务器地址和仓库名称

   git remote show origin # 查看远程服务器仓库状态

   git remote add origin https://github.com/LionsZhang/ReactTestProject.git # 添加远程仓库地址

   git remote set-url origin https://github.com/LionsZhang/ReactTestProject.git # 设置远程仓库地址(用于修改远程仓库地址) git remote rm <repository> # 删除远程仓库
有如下几种解决方法：

1.使用强制push的方法：

$ git push -u origin master -f

这样会使远程修改丢失，一般是不可取的，尤其是多人协作开发的时候。

2.push前先将远程repository修改pull下来

$ git pull origin master

$ git push -u origin master

3.若不想merge远程和本地修改，可以先创建新的分支：

$ git branch [name]

然后push

$ git push -u origin [name]