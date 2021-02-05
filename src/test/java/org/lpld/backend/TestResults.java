package org.lpld.backend;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lpld.datacompanies.backend.Requests;
import org.lpld.datacompanies.backend.Results;
import org.lpld.datacompanies.backend.model.AnnualAccount;

public class TestResults {
    private Results results;

    @Before
    public void setUp(){
        JSONObject req = new JSONObject();
        JSONObject more_than = new JSONObject();
        more_than.put("CO.0", 4500);
        req.put("more_than", more_than);
        this.results = new Results(req);
    }


    @Test
    public void testNextPage(){
        AnnualAccount[] list = results.nextPage();
        Assert.assertNotNull(list);
    }

    @Test
    public void testGoToPage(){
        AnnualAccount[] list = results.goToPage(5);
        Assert.assertNotNull(list);
    }

    @Test
    public void testGetCurrentPagePosition(){
        AnnualAccount[] list = results.goToPage(3);
        int currentPagePosition = results.getCurrentPagePosition();
        Assert.assertNotNull(list);
        Assert.assertEquals(3, currentPagePosition);
    }

    public void testFinish(){
        Assert.assertFalse(results.finish());
    }
}
