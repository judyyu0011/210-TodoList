package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import model.ToDoList;
import observer.WeatherObserver;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.json.simple.parser.ParseException;

public class Weather implements WeatherObserver {

    private static String apikey = "240788122919edf7542fcf22138ca522";
    private static String vancouverweatherquery =
            "http://api.openweathermap.org/data/2.5/weather?q=Vancouver,ca&APPID=";
    public double temp;

    public double importWeatherInfo() throws MalformedURLException, IOException {

        BufferedReader br = null;

        try {
            URL url = makeURL();
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

//            System.out.println(sb);
            return parsejson(sb);

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {

            closebr(br);
        }
        return 0.0;
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

        double t = (double) jo1.get("temp");
        System.out.println(t);
        return t;
    }

    private void closebr(BufferedReader br) throws IOException {
        if (br != null) {
            br.close();
        }
    }


    public Weather() throws IOException {
        this.temp = importWeatherInfo();
    }

//    private double parseJSON(StringBuilder sb) throws ParseException {
//        Object obj = new JSONParser().parse(String.valueOf(sb));
//        JSONObject jo = (JSONObject) obj;
//
//        JSONObject jo1 = (JSONObject) jo.get("main");
//
//        Double t = (Double) jo1.get("temp");
//        System.out.println(t);
//        this.temp = t;
//        return temp;
//    }

    @Override
    public void update(ToDoList list) {
//        list.addGeneralTask("Put on jacket", "everyday", false, "general");
        System.out.println("The current temperature is " + temp);
    }
}

