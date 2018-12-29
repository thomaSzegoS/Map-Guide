package com.example.mapguide.mapguide.Services;

import android.os.AsyncTask;

import com.example.mapguide.mapguide.Model.Image;
import com.example.mapguide.mapguide.Model.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.net.ssl.HttpsURLConnection;

public class FlickrService {
    public ArrayList<Image> PhotosData;


    public void GetSearch20(String SearchText) {
        try {
            PhotosData = new ArrayList<>();
            // Get Flickr data in String
            String ObjectInString = new SearchFlickr().execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=522d3b1175d09b060103fa1152d9cec4&text="+SearchText+"&sort=interestingness-desc&has_geo=1&extras=geo%2Cdescription&format=json&nojsoncallback=1&per_page=20&page=1").get();
            JSONObject Object = new JSONObject(ObjectInString);
            JSONObject ResultObjectPhotos = Object.getJSONObject("photos");
            JSONArray ResultPhoto = ResultObjectPhotos.getJSONArray("photo");
            for (int i = 0; i < ResultPhoto.length(); i++) {
                JSONObject ResultObjectPhoto = ResultPhoto.getJSONObject(i);
                String link = "http://farm" + ResultObjectPhoto.get("farm").toString() + ".static.flickr.com/" + ResultObjectPhoto.get("server").toString() + "/" + ResultObjectPhoto.get("id").toString() + "_" + ResultObjectPhoto.get("secret").toString() + ".jpg";
                String id = ResultObjectPhoto.get("id").toString();
                String title = ResultObjectPhoto.get("title").toString();
                JSONObject PhotoDescription = ResultObjectPhoto.getJSONObject("description");
                String desc = PhotoDescription.get("_content").toString();
                String lat = ResultObjectPhoto.get("latitude").toString();
                String lon = ResultObjectPhoto.get("longitude").toString();
                Place PhotoPlace = new Place(lat,lon,"");
                Image Photo = new Image(link, id, desc, title);
                Photo.setPlace(PhotoPlace);
                PhotosData.add(Photo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

        }
    }

    public void GetTop20() {
        try {
            PhotosData = new ArrayList<>();
            // Get Flickr data in String
            String ObjectInString = new SearchFlickr().execute("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=522d3b1175d09b060103fa1152d9cec4&text=places&sort=interestingness-desc&has_geo=1&extras=geo%2Cdescription&format=json&nojsoncallback=1&per_page=20&page=1").get();
            JSONObject Object = new JSONObject(ObjectInString);
            JSONObject ResultObjectPhotos = Object.getJSONObject("photos");
            JSONArray ResultPhoto = ResultObjectPhotos.getJSONArray("photo");
            for (int i = 0; i < ResultPhoto.length(); i++) {
                JSONObject ResultObjectPhoto = ResultPhoto.getJSONObject(i);
                String link = "http://farm" + ResultObjectPhoto.get("farm").toString() + ".static.flickr.com/" + ResultObjectPhoto.get("server").toString() + "/" + ResultObjectPhoto.get("id").toString() + "_" + ResultObjectPhoto.get("secret").toString() + ".jpg";
                String id = ResultObjectPhoto.get("id").toString();
                String title = ResultObjectPhoto.get("title").toString();
                JSONObject PhotoDescription = ResultObjectPhoto.getJSONObject("description");
                String desc = PhotoDescription.get("_content").toString();
                String lat = ResultObjectPhoto.get("latitude").toString();
                String lon = ResultObjectPhoto.get("longitude").toString();
                Place PhotoPlace = new Place(lat,lon,"");
                Image Photo = new Image(link, id, desc, title);
                Photo.setPlace(PhotoPlace);
                PhotosData.add(Photo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();

        }
    }

    private class SearchFlickr extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String server_response = null;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.connect();
                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    return server_response;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            // Log.v("result", s);
        }

    }





}
