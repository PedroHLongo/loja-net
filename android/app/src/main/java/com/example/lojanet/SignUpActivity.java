package com.example.lojanet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lojanet.Entities.CpfValidator;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.UnsupportedEncodingException;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText signUpETFirstName;
    TextInputEditText signUpETLastName;
    TextInputEditText signUpETEmail;
    TextInputEditText signUPETCPF;
    TextInputEditText signUpETPassword;
    TextInputEditText signUpETConfirmPassword;
    Button signUpButton;
    LoginActivity lA = new LoginActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpETFirstName = findViewById(R.id.TextInputEditTextFirstName);
        signUpETLastName = findViewById(R.id.TextInputEditTextLastName);
        signUpETEmail = findViewById(R.id.TextInputEditTextEmail);
        signUPETCPF = findViewById(R.id.TextInputEditTextCpf);
        signUpETPassword = findViewById(R.id.TextInputEditTextPassword);
        signUpETConfirmPassword = findViewById(R.id.TextInputEditTextConfirmPassword);
        signUpButton = findViewById(R.id.signUpbuttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserSignUpData();
            }
        });
    }

    public void getUserSignUpData(){
        String firstName = signUpETFirstName.getText().toString();
        String lastName = signUpETLastName.getText().toString();
        String email = signUpETEmail.getText().toString();
        String cpf = signUPETCPF.getText().toString();
        String password = signUpETPassword.getText().toString();
        String confirmPassword = signUpETConfirmPassword.getText().toString();
        CpfValidator cpfValidator = new CpfValidator();
        if (firstName.equals("") || lastName.equals("") || email.equals("") || cpf.equals("") || password.equals("") || confirmPassword.equals("")) {
            Snackbar emptyFieldsSnackBar = Snackbar.make(findViewById(R.id.signUpActivity),
                    R.string.fill_in_the_empty_fields, Snackbar.LENGTH_SHORT);
            emptyFieldsSnackBar.show();
        }else if(!cpfValidator.cpfValidator(cpf)){
            Snackbar invalidCPFSnackBar = Snackbar.make(findViewById(R.id.signUpActivity),
                    R.string.invalid_cpf, Snackbar.LENGTH_SHORT);
            invalidCPFSnackBar.show();
            signUPETCPF.setText("");
        }
        else{
            if(password.length() < 5){
                Snackbar passwordStrengh = Snackbar.make(findViewById(R.id.signUpActivity),
                        R.string.password_5_lengh, Snackbar.LENGTH_SHORT);
                passwordStrengh.show();
            }else if(password.matches("[a-zA-Z0-9]+")){
                Snackbar passwordStrengh = Snackbar.make(findViewById(R.id.signUpActivity),
                        R.string.password_1_special_character, Snackbar.LENGTH_SHORT);
                passwordStrengh.show();
            }else if(!confirmPassword.equals(password)){
                Snackbar passwordStrengh = Snackbar.make(findViewById(R.id.signUpActivity),
                        R.string.passwords_dont_match, Snackbar.LENGTH_SHORT);
                passwordStrengh.show();
            }else {
                alreadyExistCpfOrEmail(firstName, lastName, email, cpf, password, confirmPassword);
            }
        }
    }

    public void alreadyExistCpfOrEmail(final String firstName, final String lastName, final String email, final String cpf, final String password, final String confirmPassword){
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("cpf", cpf);
            jsonObject.put("email", email);

            String json = jsonObject.toString();
            final String jsonSend = json;
            String url = lA.ipPC() + "userAlreadyExist";

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    JSONTokener jsonTokener = new JSONTokener(response);
                    try {
                        JSONObject jobj = (JSONObject) jsonTokener.nextValue();
                        if (!jobj.has("Error")) {
                            Snackbar alreadyExisteSnackbar = Snackbar.make(findViewById(R.id.signUpActivity),
                                    R.string.user_already_registered, Snackbar.LENGTH_SHORT);
                            alreadyExisteSnackbar.show();
                            signUpETEmail.setText("");
                            signUPETCPF.setText("");
                        } else {
                            userSignUp(firstName, lastName, email, cpf, password, confirmPassword);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return jsonSend == null ? null : jsonSend.getBytes("utf-8");
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

    public void userSignUp(String firstName, String lastName, String email, String cpf, String password, String confirmPassword){
        try{
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("firstName", firstName);
            jsonObj.put("lastName", lastName);
            jsonObj.put("email", email);
            jsonObj.put("cpf", cpf);
            jsonObj.put("password", password);
            String json = jsonObj.toString();
            final String jsonSend = json;
            String url = lA.ipPC() + "userSignUp";

            RequestQueue requestQueue =Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUpActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }
                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return jsonSend == null ? null : jsonSend.getBytes("utf-8");
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
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.signUpActivity),
                R.string.registered_successfully, Snackbar.LENGTH_LONG);
        mySnackbar.show();
        final Intent i = new Intent(this, SignUpActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }, 500);
    }
}