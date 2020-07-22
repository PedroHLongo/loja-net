package com.unifaj.semestre.Loja.net.DAO;

import com.unifaj.semestre.Loja.net.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //----------------------------------------------------------------------//
    //------------------------- LOGIN VALIDATION ---------------------------//
    //----------------------------------------------------------------------//

    public User login(String login, String password){
        String query = "select * from user where(cpf = ? OR email = ?) AND password = ?";
        User u = new User();

        try(Connection con = jdbcTemplate.getDataSource().getConnection()){
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, login);
            ps.setString(2, login);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                u.setId(rs.getLong("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setEmail(rs.getString("email"));
                u.setCpf(rs.getString("cpf"));
                u.setPassword(rs.getString("password"));
            }else{
                return null;
            }
            rs.close();
            ps.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return u;
    }

}
