package com.example.rssreader.Common;

import android.webkit.HttpAuthHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPdatahandler {
  public    HTTPdatahandler(){}
     String stream;
    public String getHTTPdatahandler(String urlstring){
         try {
             URL url=new URL(urlstring);
             HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
             if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                 InputStream inputStream=new BufferedInputStream(httpURLConnection.getInputStream());

                 BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                 StringBuilder builder=new StringBuilder();
                 String line="";
                 while ((line=reader.readLine())!=null){
                     builder.append(line);
                     stream=builder.toString();
                     httpURLConnection.disconnect();
                 }


             }
         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }

         return stream;
     }
}
