package org.lpld.datacompanies.backend.model;

import java.util.Iterator;

import org.bson.Document;

public class AnnualAccount extends AnnualAccountStringHelper {

    public AnnualAccount() {
    }

    public AnnualAccount(Document doc){
        super();
        for (String key : doc.keySet()) {
            this.set(key,doc.get(key));
        }
    }
}
