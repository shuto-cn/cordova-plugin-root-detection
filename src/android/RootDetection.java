package ru.trykov.root;

import android.util.Log;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;
import java.io.File;

/**
 * Detect weather device is rooted or not
 *
 * @author trykov
 */
public class RootDetection extends CordovaPlugin {

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("isDeviceRooted")) {
      try {
        callbackContext.success(isDeviceRooted() ? 1 : 0);
        return true;
      } catch (Exception e) {
        callbackContext.error("N/A");
        return false;
      }
    } else if ("isEmulate".equals(action)) {
      try {
        callbackContext.success(CheckQEmuDriverFile() ? 1 : 0);
        return true;
      } catch (Exception e) {
        callbackContext.error("N/A");
        return false;
      }
    }
    return false;
  }

  private boolean isDeviceRooted() {
    return checkBuildTags() || checkSuperUserApk() || checkFilePath();
  }

  private boolean checkBuildTags() {
    String buildTags = android.os.Build.TAGS;
    return buildTags != null && buildTags.contains("test-keys");
  }

  private boolean checkSuperUserApk() {
    return new File("/system/app/Superuser.apk").exists();
  }

  private boolean checkFilePath() {
    String[] paths = {"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su",
      "/system/bin/failsafe/su", "/data/local/su"};
    for (String path : paths) {
      if (new File(path).exists()) return true;
    }
    return false;
  }

  private String[] known_qemu_drivers = {"goldfish"};

  public Boolean CheckQEmuDriverFile() {
    File driver_file = new File("/proc/tty/drivers");
    if (driver_file.exists() && driver_file.canRead()) {
      byte[] data = new byte[(int) driver_file.length()];
      try {
        InputStream inStream = new FileInputStream(driver_file);
        inStream.read(data);
        inStream.close();
      } catch (FileNotFoundException e) {
      //TODO自动生成的catch块
        e.printStackTrace();
      } catch (IOException e) {
      //TODO自动生成的catch块
        e.printStackTrace();
      }
      String driver_data = new String(data);
      for (String known_qemu_driver : known_qemu_drivers) {
        if (driver_data.indexOf(known_qemu_driver) != -1) {
          Log.v("Result:", "Findknown_qemu_drivers!");
          return true;
        }
      }
    }
    Log.v("Result:", "NotFindknown_qemu_drivers!");
    return false;
  }


}
