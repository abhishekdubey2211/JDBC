package com.jodo;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.jodo.model.UrlSetting;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.jodo.model.UrlsettingApiResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Abhishek
 */
@RestController
@RequestMapping("/url")
public class UrlAction {

    // Method to get the current date and time as a formatted string
    public static String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }

    @PostMapping
    public UrlsettingApiResponse addUrlSetting(@RequestBody UrlSetting urlsetting) {
        UrlsettingApiResponse urlApiResponse = new UrlsettingApiResponse();
        List<UrlSetting> urlsettingList = new ArrayList<>();
        String timestamp = getCurrentDateTime();
        try {
            urlApiResponse.setResdatetime(timestamp);
            urlApiResponse.setRevisionno(1);

            int functionid = urlsetting.getFunctionid();
            int add = urlsetting.getAddPermission();
            int view = urlsetting.getViewpermission();
            int delete = urlsetting.getDeletepermission();
            int edit = urlsetting.getEditpermission();

            urlsetting.setFunctionid(functionid);
            urlsetting.setAddPermission(add);
            urlsetting.setEditpermission(edit);
            urlsetting.setDeletepermission(delete);
            urlsetting.setViewpermission(view);
            urlsettingList.add(urlsetting);

            if (urlsetting != null) {
                urlApiResponse.setStatus(1);
                urlApiResponse.setStatusdesc("Sucess");
                urlApiResponse.setUrlSetting(urlsettingList);
            } else {
                urlApiResponse.setStatus(1);
                urlApiResponse.setStatusdesc("fail");
                urlApiResponse.setUrlSetting(null);
            }

            return urlApiResponse;
        } catch (Exception e) {

        }
        return null;
    }

    @GetMapping
    public String checkpermission(@RequestBody UrlSetting url) {
        UrlsettingApiResponse urlApiResponse = new UrlsettingApiResponse();

        urlApiResponse = addUrlSetting(url);
        if (urlApiResponse == null || urlApiResponse.getUrlSetting() == null || urlApiResponse.getUrlSetting().isEmpty()) {
            return "urlApiResponse found null"; // No URL settings found
        }

        // Serialize the object to JSON using GSON
        Gson gson = new Gson();
        String json = gson.toJson(urlApiResponse);

        System.out.println("urlApiResponse >> POJO TO JSON :: >> " + json);
        // Deserialize the JSON back to an object

        UrlsettingApiResponse jsonRequest = gson.fromJson(json, UrlsettingApiResponse.class);
        System.out.println("urlApiResponse json >> JSON TO POJO :: >> " + json);
        // Retrieve and use values from the JSON object
        
        List<UrlSetting> urlSetting = jsonRequest.getUrlSetting();
        if (urlSetting != null) {
            for (UrlSetting setting : jsonRequest.getUrlSetting()) {
                if (setting.getAddPermission() == 1) {
                    return "AddPermission granted";
                } else {
                    return "AddPermission denied";
                }
            }

            for (int i = 0; i <= urlSetting.size(); i++) {
                int view = urlSetting.get(i).getViewpermission();
                if (view == 1) {
                    return "ViewPermission granted";
                } else {
                    return "ViewPermission denied";
                }
            }

            Iterator<UrlSetting> iterator = urlSetting.iterator();
            while (iterator.hasNext()) {
                UrlSetting data = iterator.next();
                int deletePermission = data.getDeletepermission(); // Assuming getDeletePermission() returns an int
                if (deletePermission == 1) {
                    return "DeletePermission granted"; // If "deletePermission" is 1, return true
                } else {
                    return "DeletePermission denied"; // If "deletePermission" is 1, return true
                }
            }
        }
        return null; // If no matching permission is found
    }

    @PutMapping
    public JSONObject getData(@RequestBody String strRequest) {
        JSONObject strJsonResponse = new JSONObject();

        try {
            JSONObject strJsonRequest;
            JSONArray strJdonRequestList = new JSONArray();

            JSONParser parser = new JSONParser();
            strJsonRequest = (JSONObject) parser.parse(strRequest);

            String functionid = String.valueOf(strJsonRequest.get("functionid"));
            Long infunctionid = (Long) strJsonRequest.get("functionid");
            Long addpermission = (Long) strJsonRequest.get("addpermission");
            Long editpermission = (Long) strJsonRequest.get("editpermission");
            Long viewpermission = (Long) strJsonRequest.get("viewpermission");

            String timestamp = getCurrentDateTime();
            strJdonRequestList.add(strJsonRequest);

            if (strJdonRequestList != null && !strJdonRequestList.isEmpty()) {
                strJsonResponse.put("UrlSetting", strJdonRequestList);
                strJsonResponse.put("statusdesc", "Success");
                strJsonResponse.put("requestdatetime", timestamp);
                strJsonResponse.put("status", 1);
            } else {
                strJsonResponse.put("statusdesc", "Fail");
                strJsonResponse.put("requestdatetime", timestamp);
                strJsonResponse.put("status", 0);
                strJsonResponse.put("UrlSetting", null);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception, e.g., log the error
        }
        return strJsonResponse;
    }

    @GetMapping("/json")
    public JSONObject checkPermissionFromJsonObj(@RequestBody String data) {
        String timestamp = getCurrentDateTime();
        JSONObject strJsonResponse = new JSONObject();

        try {
            JSONObject strJsonRequest = getData(data);

            JSONArray urlarray = (JSONArray) strJsonRequest.get("UrlSetting");
            Iterator iterator = urlarray.iterator();
            Long addPermission = null;

            while (iterator.hasNext()) {
                JSONObject data1 = (JSONObject) iterator.next();
                addPermission = (Long) data1.get("addpermission");
            }

            if (addPermission != null && addPermission == 1L) {
                strJsonResponse.put("addpermission", "Granter");
                strJsonResponse.put("statusdesc", "Success");
            } else {
                strJsonResponse.put("addpermission", "Denied");
                strJsonResponse.put("statusdesc", "Success");
            }

            strJsonResponse.put("requestdatetime", timestamp);
            strJsonResponse.put("status", 1);

            return strJsonResponse;
        } catch (Exception e) {
            return null;
        }
    }

}
