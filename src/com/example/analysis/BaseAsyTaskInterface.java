package com.example.analysis;

import org.json.JSONObject;

public interface BaseAsyTaskInterface {
 // void dataSubmit();
  void dataSuccess(JSONObject result);
  void dataError(String msg);
}
