package com.example.task1;

import org.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.Connection;

import java.io.IOException;
import java.util.ArrayList;


public class WebCrowler {


    int page_size = 20;
    String jsonBody;
    final String API_URL = "https://api.londonstockexchange.com/api/v1/components/refresh";
    final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.5112.81 Safari/537.36 Edg/104.0.1293.47";
    String Referer = "https://www.londonstockexchange.com/";

    public void apifetch() throws Exception {
        ArrayList<CrowlerModel> list = new ArrayList<CrowlerModel>();
        for (int i = 1; i < 7; i++) {
           // jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":\"page=" + i + "&size=20&sort=datetime,desc\"}]}";

            String jsonBody = "{" +
                    "\"path\"" +
                    ":\"news\"," +
                    "\"parameters\"" +
                    ":\"tab%3Dnews-explorer%26" +
                    "page%" +
                    "3" +
                    "D" +i+
                    "%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\"" +
                    "," +
                    "\"components\"" +
                    ":[{\"componentId\"" +
                    ":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\"" +
                    ",\"parameters\"" +
                    ":null}]}";

            Connection.Response response = Jsoup.connect(API_URL).
                    ignoreContentType(true)
                    .followRedirects(true)
                    .ignoreHttpErrors(true)
                    .userAgent(USER_AGENT)
                    .header("content-type", "application/json")
                    .header("accept", "application/json")
                    .header("referer", Referer)
                    .requestBody(jsonBody)
                    .method(Connection.Method.POST)
                    .timeout(0)
                    .execute();

            int statusCode = response.statusCode();
            System.out.println("code status is" + statusCode);
            System.out.println(response.body());


//            JSONArray jsonArray = new JSONArray(response.body());
//            for (int x = i; x < 6; x++) {
//            String companyName = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("companyname");
//            String dateTime = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("datetime");
//            double lastPrice = (double) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("lastprice");


//            list.add(new CrowlerModel(companyName, lastPrice, dateTime));
//            System.out.println(list);

        }
    }

    public void HistoricData() throws IOException {

        for (int i = 0; i < page_size; i++) {
            ArrayList<CrowlerModel> list = new ArrayList<CrowlerModel>();
            if (i == 0) {
                jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26period%3Dcustom%26beforedate%3D20220811%26afterdate%3D20220801%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\"" +
                        ",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":null}]}";//first page payload
            } else {
                jsonBody = "{\"path\":\"news\",\"parameters\":\"" +
                        "tab%3Dnews-explorer%26period%3Dcustom%26beforedate%3D20220811%26afterdate%3D20220801%26" +
                        "page%" +
                        "3" +
                        "D" +
                        i +
                        "%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f" +
                        "\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":null}]}";//very next page after first page
            }

            Connection.Response response = Jsoup.connect(API_URL).ignoreContentType(true).followRedirects(true).ignoreHttpErrors(true)
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.POST)
                    .header("content-type", "application/json")
                    .header("referer", Referer)
                    .requestBody(jsonBody)
                    .method(Connection.Method.POST)
                    .timeout(0)
                    .execute();
            int statusCode = response.statusCode();
            System.out.println("code status is" + statusCode);
            JSONArray jsonArray = new JSONArray(response.body());
            for (int x = i; x < 20; x++) {
              /*  System.out.println(jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("companyname"));
                System.out.println(jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("datetime"));
                System.out.println(jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("lastprice"));
                System.out.println(" Number: " + x);*/
                String companyName = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("companyname");
                String dateTime = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("datetime");
                String newsSource = (String) jsonArray.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x).get("newssource");
              // System.out.println(companyName + "------"+newsSource +"------"+ dateTime);
               // System.out.println(list.add(new CrowlerModel(companyName, newsSource, dateTime)));
               // System.out.println(list);
                list.add(new CrowlerModel(companyName,  newsSource, dateTime));

            }
            System.out.println(list);
        }
    }
}
