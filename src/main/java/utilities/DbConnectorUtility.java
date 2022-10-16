package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DbConnectorUtility {
    Connection con;
    Statement stmt;
    ResultSet resultSet;

    public Connection getDBConnection(String databasename) throws Exception {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;DatabaseName=" + databasename +
                ";encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url, "sa", "tiger");
    }


    public ArrayList<HashMap<String, Object>> getDbRecordsAsMap(String databaseName, String query)
            throws Exception {
        try {
            con = getDBConnection(databaseName);
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);
            ArrayList<HashMap<String, Object>> listmap = new ArrayList<HashMap<String, Object>>();

            while (resultSet.next()) {
                HashMap<String, Object> mapvals = new HashMap<String, Object>();
                int totalCols = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= totalCols; i++) {

                    mapvals.put(resultSet.getMetaData().getColumnLabel(i),
                            resultSet.getObject(i));

                }

                listmap.add(mapvals);
            }

            return listmap;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            stmt.close();
            con.close();
        }
    }


    public ArrayList<Object> getColumnValuesAsList(String colHeaderName, String databaseName, String query)
            throws Exception {

        ArrayList<HashMap<String, Object>> dbobjects = getDbRecordsAsMap(databaseName, query);
        ArrayList<Object> colvalues = new ArrayList<Object>();
        for (int i = 0; i < dbobjects.size(); i++) {
            Object ob = dbobjects.get(i).get(colHeaderName);
            colvalues.add(ob);
        }
        return colvalues;
    }


}
