package regressiondb;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DbConnectorUtility;

import java.util.ArrayList;
import java.util.HashMap;

public class SoftwareCompanyColDataTest {
    DbConnectorUtility dbConnectorUtility;
    String dbName = "softwarecompany";
    String query = "select * from software_engineer";


    @BeforeMethod
    public void setup() throws Exception {
        dbConnectorUtility = new DbConnectorUtility();

    }


    @Test
    public void gettableRecords() throws Exception {

        ArrayList<Object> colobjbjects = dbConnectorUtility.
                getColumnValuesAsList("address", dbName, query);
        System.out.println("records size is >>>>>>>" + colobjbjects.size());
        System.out.println(colobjbjects.toString());
        Assert.assertEquals(colobjbjects.size(), 5, "records count is not matched");


    }


}
