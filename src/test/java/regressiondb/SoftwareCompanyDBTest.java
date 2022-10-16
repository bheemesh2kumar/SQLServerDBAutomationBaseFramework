package regressiondb;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DbConnectorUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareCompanyDBTest {
    DbConnectorUtility dbConnectorUtility;
    String dbName = "softwarecompany";
    String query = "select * from software_engineer";


    @BeforeMethod
    public void setup() throws Exception {
        dbConnectorUtility = new DbConnectorUtility();

    }


    @Test
    public void gettableRecords() throws Exception {

        ArrayList<HashMap<String, Object>> dbobjects = dbConnectorUtility.getDbRecordsAsMap(dbName, query);
        System.out.println("records size is >>>>>>>" + dbobjects.size());
        System.out.println(dbobjects.toString());
        Assert.assertEquals(dbobjects.size(), 5, "records count is not matched");


    }


}
