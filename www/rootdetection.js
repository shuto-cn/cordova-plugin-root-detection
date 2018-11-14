var exec = require('cordova/exec');

exports.isDeviceRooted = function(success, error) {
    exec(success, error, "RootDetection", "isDeviceRooted", []);
};

exports.isEmulate = function (success, error) {
    exec(success, error, "RootDetection", "isEmulate", []);
}
