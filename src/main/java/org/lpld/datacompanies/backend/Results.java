package org.lpld.datacompanies.backend;

import java.util.ArrayList;

import com.mongodb.client.MongoCursor;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.lpld.datacompanies.backend.model.AnnualAccount;

public class Results {
    private Requests request;
    private JSONObject requestFile;
    private MongoCursor<Document> cursor;
    private ArrayList<AnnualAccount> list;
    private int currentPagePosition;
    private int numberOfPagesInList;
    private int numberOfAccount;
    private int numberOfDocumentInCursor;
    private boolean finish;

    public Results(JSONObject requestFile){
        this.request = new Requests("localhost");
        this.requestFile = requestFile;
        //this.cursor = request.doRequest(this.requestFile);
        this.list = new ArrayList<AnnualAccount>();
        this.currentPagePosition = 0;
        this.numberOfAccount = 10;
        this.numberOfPagesInList = 0;
        this.finish = false;
    }

    public AnnualAccount[] nextPage(){
        return this.goToPage(this.currentPagePosition+1);
    }

    public AnnualAccount[] goToPage(int pagePosition){
        int posBegin = pagePosition * this.numberOfAccount - this.numberOfAccount;
        int posEnd = pagePosition * this.numberOfAccount;

        int size = this.numberOfAccount;

        if(posBegin>list.size() && finish){
            return null;
        }

        if(posBegin>list.size()){
            this.cursor = this.request.doRequest(this.requestFile);
            for(int i=0;i<list.size();i++){
                cursor.next();
            }
            for (int i = list.size(); i < posEnd; i++) {
                if (cursor.hasNext()) {
                    list.add(new AnnualAccount(cursor.next()));
                }
            }
            this.cursor.close();
        }
        



        if(list.size()<posEnd){
            size = this.list.size();
            posEnd = this.list.size();
            this.finish=true;
        }

        AnnualAccount[] page = new AnnualAccount[size];
        int y = 0;
        for (int i = posBegin; i < posEnd; i++) {
            page[y] = this.list.get(i);
            y+=1;
        }

        this.currentPagePosition = pagePosition;

        return page;
    }

    public boolean finish(){
        return this.finish;
    }

    public int getCurrentPagePosition(){
        return this.currentPagePosition;
    }


    
}
