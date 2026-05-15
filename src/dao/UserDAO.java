package dao;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//USER DAO
public class UserDAO {

    public ResultSet listUsers() {

        ResultSet rs = null;

        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            rs = st.executeQuery("SELECT * FROM users");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}