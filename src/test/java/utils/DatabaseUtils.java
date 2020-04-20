package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static utils.GetProperties.*;


public class DatabaseUtils {

    private Connection con = null;
    private Connection con2 = null;
    private Statement statement;

    private void connectToDatabase() {
        try {
            Class.forName(DB_DRIVER).newInstance();
            switch (ENVIRONMENT) {
                case "alpha":
                    con = DriverManager.getConnection(DB_URL_ALPHA, DB_USER, DB_PASSWORD);
                    break;
                case "beta":
                    con = DriverManager.getConnection(DB_URL_BETA, DB_USER, DB_PASSWORD);
                    break;
                case "prod":
                    con = DriverManager.getConnection(DB_URL_PROD, DB_USER, DB_PASSWORD);
                    break;
            }
            System.out.println("Database connection established.");
            statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("Error, failure in connecting to database.");
            e.printStackTrace();
        }
    }


    private void connectToOCCDatabase() {
        try {
            Class.forName(DB_DRIVER).newInstance();
            con2 = DriverManager.getConnection(AUTO_SCHEMA_URL, AUTO_SCHEMA_USER, AUTO_SCHEMA_PASSWORD);

            System.out.println("Database connection to TEST AUTOMATION Database Established.");
            statement = con2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            System.out.println("Error, failure in connecting to the TEST AUTOMATION database.");
            e.printStackTrace();
        }
    }



    private void disconnectFromDatabase() throws SQLException {
        if (con != null) {
            con.close();
            System.out.println("Database connection closed.\n");
        }
    }

    private void disconnectFromAutomationDatabase() throws SQLException {
        if (con2 != null) {
            con2.close();
            System.out.println("Database connection closed.\n");
        }
    }

    public String getQueryResult(String query, String columnName) throws Exception {
        String result = null;
        try {
            connectToDatabase();

            System.out.println("Getting the result of the query: " + query);
            ResultSet rs = statement.executeQuery(query);

            if (rs != null) {
                if (rs.first()) {
                    result = rs.getString(columnName);
                    rs.close();
                }
            }

            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
        return result;
    }

    public void executeQuery(String query) throws Exception {
        try {
            connectToDatabase();
            System.out.println("Executing query: " + query);
            statement.executeQuery(query);
//            con.commit(); //Not needed with ojdbc7, autocommit is set to ON
            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public void runScript(String scriptPath) throws Exception {
        try {
            connectToDatabase();

            BufferedReader reader = new BufferedReader(new FileReader(scriptPath));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                if (line.contains("@") && !line.contains(USER_EMAIL)) {
                    System.out.println("Changing email in line: " + line);
                    line = replaceEmailAddress(line);
                    System.out.println("To email in test.properties file: " + USER_EMAIL + "\n");
                }
                System.out.println(line);
                sb.append(line);
                line = reader.readLine();
            }

            FileUtilities fu = new FileUtilities();
            String script = fu.getFileNameFromPath(scriptPath);
            System.out.println("Running SQL script, '" + script + "'...");
            statement.execute(sb.toString());
            System.out.println("'" + script + "' script executed successfully.");

            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            statement.close();
        }
    }

    public List<String> executeQueryAndGetAllResultStringValues(String query) {
        List<String> results = new ArrayList<>();
        try {
            connectToDatabase();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
            resultSet.close();
            System.out.println("Results retrieved from query.");
            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<String> executeAutoDBQueryAndGetAllResultStringValues(String query) {
        List<String> results = new ArrayList<>();
        try {
            connectToOCCDatabase();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
            resultSet.close();
            System.out.println("Results retrieved from query.");
            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }



    public List<Integer> executeQueryAndGetAllResultIntegerValues(String query) {
        List<Integer> results = new ArrayList<>();
        try {
            connectToDatabase();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                results.add(Integer.valueOf(resultSet.getString(1)));
            }
            resultSet.close();
            System.out.println("Results retrieved from query: " + query);
            disconnectFromDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private List<String> executeQueryAndGetAllResultValues(String query) {
        List<String> results = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                results.add(resultSet.getString(1));
            }
            resultSet.close();
            System.out.println("Results retrieved from query: " + query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    private String replaceEmailAddress(String line) {
        String emailRegex = "(?:[a-z0-9!#$%&*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        line = line.replaceAll(emailRegex, USER_EMAIL);
        return line;
    }
}
