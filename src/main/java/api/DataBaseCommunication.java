package api;

import game.shared.logger.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBaseCommunication {

    private static Connection conn = null;


    /* Disclaimer:
     * The SQL statements currently are prone to SQL injections
     */


    private static void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            Logger.getInstance().log(e);
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            conn = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local/dbi370126?useSSL=false", "dbi370126", "SubmarineBattleGame");


        } catch (SQLException e) {
            Logger.getInstance().log(e);
        }
    }

    public boolean register(String username, String password) {
        String query = "INSERT INTO submarine_users (username, password) VALUES ('" + username + "', '" + password + "')";
        return executeStatement(query, 0, false).get(0).equals("true");
    }

    public boolean validateLogin(String time, int userId) {
        //get loginTime from database for userId
        //compare loginTime with time
        //if equals, then change loginTime to null and return true
        time+=" ";
        String query = "SELECT login FROM submarine_users WHERE id = " + userId;
        List<String> output = executeStatement(query, 1, true);
        if (output.size() == 1 && output.get(0).equals(time)) {
            query = "UPDATE submarine_users SET login = '' WHERE id = " + userId;
            executeStatement(query, 0, false);
            return true;
        }
        return false;
    }

    public int login (String username, String password) {
        //try to get ID from database for this username and password
        //if there is one, then update the 'lastLogin' to the current time
        //return the current time
        String query = "SELECT id FROM submarine_users WHERE username = '" + username + "' AND password = '" + password + "'";
        List<String> output = executeStatement(query, 1, true);
        if (output.size() == 1) {
            //login successful
            return Integer.parseInt(output.get(1));
        } else {
            //login failed
            return -1;
        }
    }

    public void setValidationKey(int id, String validationKey){
        String query = "UPDATE submarine_users SET login = '" + validationKey + "' WHERE id = "+id;
        executeStatement(query, 0, false);
    }

    public void updateScore(int id){
        //score+1
        String query = "UPDATE submarine_users SET score = score + 1 WHERE id = " + id;
        executeStatement(query, 0, false);
    }

    public String getScore(int id){
        //return score of user with id
        String query = "SELECT username, score FROM submarine_users WHERE id = " + id;
        List<String> score = executeStatement(query, 1, true);
        return score.get(0);
    }

    public List<String> getTopX(int amount){
        //return username and score of top 'amount' users
        String query = "Select username, score FROM submarine_users ORDER BY score DESC";
        List<String> output =  executeStatement(query, amount, true);
        for(int i = 1; i<= output.size(); i++){
            output.set(i-1, i + ". " + output.get(i-1));
        }
        return output;
    }

    private List<String> executeStatement(String query, int amount, boolean select) {//amount stands for how many output lines it expects
        makeJDBCConnection();
        ArrayList<String> output = new ArrayList<>();
        int count = 0;
        try {
            Statement stmt = conn.createStatement();
            try {
                if (select) {
                    ResultSet rs = stmt.executeQuery(query);
                    try {
                        while (rs.next() && amount != count) {
                            count++;
                            int numColumns = rs.getMetaData().getColumnCount();
                            String outputLine = "";
                            for (int i = 1; i <= numColumns; i++) {
                                outputLine += rs.getString(i) + " ";
                            }
                            output.add(outputLine);
                        }
                    } finally {
                        try {
                            rs.close();
                        } catch (Throwable ignore) {
                        }
                    }
                }
                else {
                    output.add(stmt.execute(query)+"");
                }
            } finally {
                try {
                    stmt.close();
                } catch (Throwable ignore) {
                }
            }
        } catch(SQLException e){
            Logger.getInstance().log(e);
            output.add("Failure");
        } finally {
            try { conn.close(); } catch (Throwable ignore) {}
        }
        return output;
    }

}
