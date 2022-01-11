package com.doo.selenium.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileRead {

    public static List<String> getIps() {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader("/Users/doo/project/selenium/data/Free_Proxy_List.json");
            JSONArray jsonArray = (JSONArray) parser.parse(reader);


            List<String> ips = (List<String>) jsonArray.stream().map(y -> {
                        try {
                            JSONObject jsonObject = (JSONObject) parser.parse(y.toString());
                            return (String) jsonObject.get("ip") + ":" + (String) jsonObject.get("port");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return "";
                    }
            ).collect(Collectors.toList());

            return ips;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
