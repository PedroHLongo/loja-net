package com.example.lojanet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lojanet.Entities.CpfValidator;
import com.google.android.material.snackbar.Snackbar;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpETFirstName;
    EditText signUpETLastName;
    EditText signUpETEmail;
    EditText signUPETCPF;
    EditText signUpETPassword;
    EditText signUpETConfirmPassword;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpETFirstName = findViewById(R.id.signUpEditTextTextFirstName);
        signUpETLastName = findViewById(R.id.signUpEditTextTextLastName);
        signUpETEmail = findViewById(R.id.signUpEditTextTextEmail);
        signUPETCPF = findViewById(R.id.signUpEditTextTextCpf);
        signUpETPassword = findViewById(R.id.signUpEditTextTextPassword);
        signUpETConfirmPassword = findViewById(R.id.signUpEditTextTextConfirmPassword);
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
                    "Fill in the empty fields.", Snackbar.LENGTH_SHORT);
            emptyFieldsSnackBar.show();
        }else if(!cpfValidator.cpfValidator(cpf)){
            Snackbar invalidCPFSnackBar = Snackbar.make(findViewById(R.id.signUpActivity),
                    "Invalid CPF.", Snackbar.LENGTH_SHORT);
            invalidCPFSnackBar.show();
            signUPETCPF.setText("");
        }//FAZER VERIFICACAO SE EMAIL OU CPF JA EXISTE NO BANCO//
        else{
            Snackbar passwordSnackBar = Snackbar.make(findViewById(R.id.signUpActivity),
                    "" + strengh(password, confirmPassword), Snackbar.LENGTH_SHORT);
            passwordSnackBar.show();
        }
    }

    public String strengh(String password, String confirmPassword){

        if(password.length() < 5){
            return "Password must have at least 5 characters";
        }else if(password.matches("[a-zA-Z0-9]+")){
            return "Password must have at least 1 special character";
        }else if(!confirmPassword.equals(password)){
            return "Passwords don't match";
        }else{
            return "Sign up completed!";
        }
    }

}