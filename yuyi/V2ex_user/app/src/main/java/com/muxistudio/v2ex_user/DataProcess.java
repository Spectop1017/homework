package com.muxistudio.v2ex_user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * used to deal with the information for the internet.
 * getInformation() is used to get the whole string;
 * getInformation() needs a infor and a select
 * infor means the string that need to add to the end of url;
 * select has two values:1/0  1 means use username to get information
 * 0 means use id to get information.
 * searchInformation() is used to find out the single information;
 * As to searchInformation(), it needs infor and select for getInformation() using
 * searchInformation() will return a list include status,id,username,twitter,tagline,github,bio,picture.
 * when the user is found, the status will be "found" and the other value will be put in the list
 * when the user is not found, the status will "notfound" and the other value will be null.
 * Created by root on 15-2-13.
 */

public class DataProcess {
    String res = "";
    Bitmap bitmap = null;

    public String getInformation(String infor, int select) {
        final String urldress;

        if (select == 1) {
            urldress = "https://www.v2ex.com/api/members/show.json?username=" + infor;
        } else {
            urldress = "https://www.v2ex.com/api/members/show.json?id=" + infor;
        }


        Thread netThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String str;

                    URL url = new URL(urldress);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(10000);
                    connection.setRequestProperty("Accept-Language", Locale.getDefault().toString());
                    connection.setRequestProperty("Accept-Charset", "UTF-8");
                    InputStream inputStream = connection.getInputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                    while ((str = bufferedReader.readLine()) != null) {
                        str = str.replace("\\u000D\\u000A\\u000D\\u000A", "\n");
                        res += str + '\n';
                    }
                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        netThread.start();

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (netThread.isAlive());
        return res;
    }

    public List<Map<String, String>> searchInformation(String infor, int select) {
        char ch;
        int status, username, id, picture, bio, tagline, github, twitter, len, i;
        String string, text;

        string = getInformation(infor, select);

        status = string.indexOf("notfound");

        if (status < 0) {
            username = string.indexOf("username");
            id = string.indexOf("id");
            picture = string.indexOf("avatar_large");
            bio = string.indexOf("bio");
            tagline = string.indexOf("tagline");
            github = string.indexOf("github");
            twitter = string.indexOf("twitter");
        } else {
            username = id = picture = bio = tagline = github = twitter = -1;
        }

        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        len = string.length();

        if (status < 0) {

            map.put("status", "found");
            text = "";

            for (i = id + 6; i < username; i++) {
                ch = string.charAt(i);
                if (ch != ',') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("id", text);
            text = "";

            for (i = username + 13; i < twitter; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("username", text);
            text = "";

            for (i = twitter + 12; i < github; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("twitter", text);
            text = "";

            for (i = github + 11; i < tagline; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("github", text);
            text = "";

            for (i = tagline + 12; i < bio; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("tagline", text);
            text = "";

            for (i = bio + 8; i < picture; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("bio", text);
            text = "";

            for (i = picture + 19; i < len; i++) {
                ch = string.charAt(i);
                if (ch != '\"') {
                    text = text + ch;
                } else {
                    break;
                }
            }
            map.put("picture", text);

        } else {
            map.put("status", "notfound");
            map.put("id", null);
            map.put("username", null);
            map.put("twitter", null);
            map.put("github", null);
            map.put("tagline", null);
            map.put("bio", null);
            map.put("picture", null);
        }

        list.add(map);

        return list;
    }

    public String getstatus(List<Map<String, String>> list) {
        String status;
        Map<String, String> map = list.get(0);
        status = map.get("status");
        return status;
    }

    public String getid(List<Map<String, String>> list) {
        String id;
        Map<String, String> map = list.get(0);
        id = map.get("id");
        return id;
    }

    public String getusername(List<Map<String, String>> list) {
        String username;
        Map<String, String> map = list.get(0);
        username = map.get("username");
        return username;
    }

    public String gettwitter(List<Map<String, String>> list) {
        String twitter;
        Map<String, String> map = list.get(0);
        twitter = map.get("twitter");
        return twitter;
    }

    public String getgithub(List<Map<String, String>> list) {
        String github;
        Map<String, String> map = list.get(0);
        github = map.get("github");
        return github;
    }

    public String gettagline(List<Map<String, String>> list) {
        String tagline;
        Map<String, String> map = list.get(0);
        tagline = map.get("tagline");
        return tagline;
    }

    public String getbio(List<Map<String, String>> list) {
        String bio;
        Map<String, String> map = list.get(0);
        bio = map.get("bio");
        return bio;
    }

    public String getpicture(List<Map<String, String>> list) {
        String picture;
        Map<String, String> map = list.get(0);
        picture = map.get("picture");
        return picture;
    }

    public Bitmap regetpic(final String urlstr) {
        Thread picgetThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://" + urlstr);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(10000);
                    InputStream inputStream = url.openStream();

                    bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        picgetThread.start();

        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (picgetThread.isAlive());

        return bitmap;
    }


}
