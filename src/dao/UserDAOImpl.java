package dao;

import beans.User;
import dbutility.DBUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Data Access Object
public class UserDAOImpl extends DBUtility implements UserDAOInter {
//desktop application, multithread
    @Override
    public int insert(User user) {//Ecemi
        Connection connection = null;
        try {
            connection = connect();

            String sql = "insert into user(name,surname) values(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());

            int result = stmt.executeUpdate();
            if(result > 0) {
                int insertedId =  getInsertId(stmt);
                user.setId(insertedId);
                return insertedId;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            close(connection);
        }
        return 0;
    }

    @Override
    public User find(int id) {//jdbc specification-nin shertlerini odeyen kitabxana
        Connection connection = null;
        User user = null;
        try {
            connection = connect();//return new MySQLConnectionClass(); JPA

            String sql = "select * from user where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);//return new MySQLPreparedStatementClass()
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");

                user = new User();
                user.setId(userId);
                user.setName(userName);
                user.setSurname(userSurname);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(connection);
        }
        return user;
    }

    @Override
    public List<User> findAll(String name, String surname) {
        Connection connection = null;
        List<User> list = new ArrayList<>();
        try {
            connection = connect();

            String sql = "select * from user where name = ? and surname = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, surname);


            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String userSurname = rs.getString("surname");

                User user = new User();
                user.setId(userId);
                user.setName(userName);
                user.setSurname(userSurname);

                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(connection);
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = null;
        try {
            connection = connect();

            String sql = "delete from user where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            close(connection);
        }
    }

    @Override
    public boolean update(int id, User user) {
        Connection connection = null;
        try {
            connection = connect();

            String sql = "update user set name = ?, surname = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setInt(3, id);

            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            close(connection);
        }
    }


    public static int getInsertId(PreparedStatement stmt) throws Exception{
        ResultSet generatedKeys = stmt.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }else {
            return -1;
        }
    }

}
