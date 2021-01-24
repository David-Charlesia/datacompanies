package org.lpld.datacompanies.backend.model;

import org.bson.Document;

public class AnnualAccount extends AnnualAccountStringHelper {

    public AnnualAccount(){}

    public AnnualAccount(Document doc){
        super();
        //exemple this.ACTIF = 1;
    }
}
