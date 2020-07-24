package com.example.lojanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lojanet.Entities.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText loginMain;
    TextInputEditText passwordMain;
    Button signIn;
    Button signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginMain = findViewById(R.id.TextInputEditTextLogin);
        passwordMain = findViewById(R.id.TextInputEditTextPassword);
        signIn = findViewById(R.id.buttonSignIn);
        signUp = findViewById(R.id.buttonSignUp);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFieldEmpty();
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            }
        });
    }

    public void isFieldEmpty(){
        if(loginMain.getText().toString().equals("") /*|| passwordMain.getText().toString().equals("")*/){
           /* Snackbar loginSnackBar = Snackbar.make(findViewById(R.id.mainActivity),
                    R.string.fill_in_the_empty_fields, Snackbar.LENGTH_SHORT);
            loginSnackBar.show();*/
           loginMain.setError("Enter Name");

        }if(passwordMain.getText().toString().equals("")){

            passwordMain.setError("Enter Password");

        }

        if(!loginMain.getText().toString().equals("") && !passwordMain.getText().toString().equals("")){
            requestLoginData();
        }
    }

    public void requestLoginData() {
            final String login = loginMain.getText().toString();
            String password = passwordMain.getText().toString();
            try{
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("login", login);
                jsonObj.put("password", password);

                final String json = jsonObj.toString();
                final String jsonEnviar = json;
                String url = ipPC() + "userLogin";

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONTokener jsonTokener = new JSONTokener(response);
                        try {
                            JSONObject jobj = (JSONObject) jsonTokener.nextValue();
                            if(!jobj.has("Error")){
                                User u = new User();
                                u.setId(jobj.getLong("id"));
                                u.setFirstName(jobj.getString("firstName"));
                                u.setLastName(jobj.getString("lastName"));
                                u.setEmail(jobj.getString("email"));
                                u.setCpf(jobj.getString("cpf"));
                                u.setPassword(jobj.getString("password"));
                                saveUserData();
                            }else{
                                loginMain.setText("");
                                passwordMain.setText("");
                                loginMain.setBackgroundResource(R.drawable.edittext_bg);
                                loginMain.setBackgroundColor(1);
                                Snackbar mySnackbar = Snackbar.make(findViewById(R.id.mainActivity),
                                        R.string.incorrect_user, Snackbar.LENGTH_LONG);
                                mySnackbar.show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        try {
                            return jsonEnviar == null ? null : jsonEnviar.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            uee.printStackTrace();
                            return null;
                        }
                    }
                };
                requestQueue.add(stringRequest);
            }catch (Exception ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    public String ipPC(){
        String ip = "http://192.168.0.114:8082/";
        return ip;
    }

    public void saveUserData(){

        startActivity(new Intent(this, SecondActivity.class));
        finish();
    }
}