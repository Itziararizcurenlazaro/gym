package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import utils.DBConnection;

public class WorkoutDAO {
    public WorkoutDAO() {
    }

    public ResultSet listWorkout() {
        ResultSet rs = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM workouts");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}
