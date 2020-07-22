package com.unifaj.semestre.Loja.net.Controller;

import com.unifaj.semestre.Loja.net.DAO.UserDao;
import com.unifaj.semestre.Loja.net.Entity.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserDao userDao;

    //----------------------------------------------------------------------//
    //------------------------- LOGIN VALIDATION ---------------------------//
    //----------------------------------------------------------------------//

    @PostMapping("/userLogin")
    public String userLogin(@RequestBody String loginData) {
        User u;
        JSONObject object = (JSONObject) (new JSONTokener(loginData)).nextValue();
        String loginUser = object.getString("login");
        String passwordUser = object.getString("password");
        System.out.println("Database login: " + loginUser);
        System.out.println("Database password: " + passwordUser);
        u = userDao.login(loginUser, passwordUser);
        if(u == null){
            return "{\"Error\": true}";
        }
        return new JSONObject(u).toString();
    }

    //----------------------------------------------------------------------//
    //-------------------------- USER SIGN UP ------------------------------//
    //----------------------------------------------------------------------//

    @PostMapping("userSignUp")
    public User userSignUp(@RequestBody User u){
        return userDao.userSignUp(u);
    }
}
