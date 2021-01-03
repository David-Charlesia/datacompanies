package org.lpld.datacompanies.backend;

import java.util.ArrayList;

import com.mongodb.DBCursor;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Requests {
    public Requests() {}

    public DBCursor doRequest(String jsonFile){
        JSONObject request = parseJson(jsonFile);
        
        DBCursor cursor=null;
        
        return cursor;
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
