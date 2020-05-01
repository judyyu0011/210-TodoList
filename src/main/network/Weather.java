package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

//import java.text.ParseException;
import java.util.ArrayList;

import model.ToDoList;
import observer.WeatherObserver;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

public class Weather implements WeatherObserver {

    private static String apikey = "240788122919edf7542fcf22138ca522";
    private static String vancouverweatherquery =
            "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
    private double temp;

    private double importWeatherInfo() throws IOException {

        BufferedReader br = null;

        try {
            String theURL = vancouverweatherquery + apikey;
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }
//            System.out.println(sb);

            return parsejson(sb);

//            Object obj = new JSONParser().parse(String.valueOf(sb));
//            JSONObject jo = (JSONObject) obj;
//
//            JSONObject jo1 = (JSONObject) jo.get("main");
//
//            double t = (double) jo1.get("temp");
//            System.out.println(t);
//            return t;

//            this.temp = t;

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                br.close();
            }
        }
        return 0;
    }

    private URL makeURL() throws MalformedURLException {
        String apikey = "240788122919edf7542fcf22138ca522";
        String vancouverweatherquery =
                "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
        String theURL = vancouverweatherquery + apikey;
        return new URL(theURL);
    }



    private double parsejson(StringBuilder sb) throws ParseException {
        Object obj = new JSONParser().parse(String.valueOf(sb));
        JSONObject jo = (JSONObject) obj;

        JSONObject jo1 = (JSONObject) jo.get("main");

//        ArrayList<String> listdata = new ArrayList<String>();
//        String t = (String) ja.get(1);
//        JSONArray jArray = (JSONArray) jsonObject;
//        if (ja != null) {
//            for (int i = 0; i < ja.size();i++) {
//                listdata.add((String) ja.get(i));
//            }
//        }
//
//        String t = listdata.get(3);




        double t = (double) jo1.get("temp");
//        String t = (String) jo1.get(1);
//        System.out.println(t);
        return t;
    }

    private void closebr(BufferedReader br) throws IOException {
        if (br != null) {
            br.close();
        }
    }

    // EFFECTS: constructs a weather object, with temperature imported from internet
    public Weather() throws IOException {
        this.temp = importWeatherInfo();
    }

    // EFFECTS: prints out the current temperature
    @Override
    public void update(ToDoList list) {
        System.out.println("The current temperature is " + temp);
    }
}

