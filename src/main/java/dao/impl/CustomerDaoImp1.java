package dao.impl;
import dao.RoleDao;
import dbutil.JDBCUtil;
import entity.Customer;
import entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoImp1 implements RoleDao {
    public Customer login(String name, String password) {
        String sql = "select*from customers where name= ? and password = ?";
        Connection connection = JDBCUtil.getConnect("myshop");
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();

            if (result.next()) {
                Customer customer = new Customer();
                customer.setId(result.getInt("id"));
                customer.setName(result.getString("name"));
                customer.setPassword(result.getString("password"));
                customer.setPhone(result.getString("phone"));
                customer.setMoney(result.getInt("money"));
                return customer;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    public boolean register(Role role) {
        Customer customer = (Customer)role;
        String sql = "insert into customers(name,password,phone,money) values (?,?,?,?)";
        Connection connection = JDBCUtil.getConnect("myshop");
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getPhone());
            pstmt.setInt(4, customer.getMoney());
            ResultSet result = pstmt.executeQuery();
            pstmt.execute();

        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }

        return true;
    }

    public boolean check(Role role) {
        Customer customer = (Customer)role;
        String sql = "select*from customers where phone= ?";
        Connection connection = JDBCUtil.getConnect("myshop");
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,customer.getPhone());
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(Role role) {
        return false;
    }

    public List<Role> findAll() {
        return null;
    }

    public Role findById(int id) {
        return null;
    }


}
