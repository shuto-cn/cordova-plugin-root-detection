# root与模拟器环境检测插件

用于检测app是否运行在root/模拟器环境


## 安装

```
cordova plugin add https://github.com/shuto-cn/cordova-plugin-root-detection
```

## 方法

### isDeviceRooted

```js
rootdetection.isDeviceRooted(successCallback, errorCallback);
```

- 返回值1代表root环境，0代表非root环境

### Example
```js
var successCallback = function (result) {
    var isDevicesRooted = result == 1;
};
var errorCallback = function (error) {
    console.error(error);
};
rootdetection.isDeviceRooted(successCallback, errorCallback);
```



### isEmulate

```js
rootdetection.isEmulate(successCallback, errorCallback);
```

- 返回值1代表模拟器环境，0代表非模拟器环境

### Example
```js
var successCallback = function (result) {
    var isEmu = result == 1;
};
var errorCallback = function (error) {
    console.error(error);
};
rootdetection.isEmulate(successCallback, errorCallback);
```

## 平台支持

Android
