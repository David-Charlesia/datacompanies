package org.lpld.datacompanies.backend;

import java.util.ArrayList;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Requests {
    public Requests() {}

    public ArrayList<Integer> doRequest(String jsonFile){
        JSONObject request = parseJson(jsonFile);
        ArrayList<Integer> results=new ArrayList<Integer>();



        return results;
    }

    private JSONObject parseJson(String jsonFile){
        try{
            return (JSONObject) new JSONParser().parse(new FileReader(jsonFile));
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
