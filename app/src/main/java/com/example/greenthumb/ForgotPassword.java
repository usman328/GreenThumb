package com.example.greenthumb;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPassword extends AppCompatActivity {
    Button resetEmail;
    EditText editTextResetEmailAddress2;
    FirebaseAuth mAuth;

    /***
     * This method creates and shows content of the forgot password page.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        resetEmail = findViewById(R.id.resetEmail);
        editTextResetEmailAddress2 = findViewById(R.id.editTextResetEmailAddress2);
        // this is reset email button's onclick listener. it checks if the email is associated with existing users. if the it is not or email is in bad format respective error is displayed to user.

    }

    public void forgotListener(View forgotView) {
        if (forgotView.getId() == R.id.resetEmail) {
                /***
                 * This method starts firebase communication to reset user password
                 *
                 */
                    String email = editTextResetEmailAddress2.getText().toString();
                    if (email.isEmpty()) {
                        Toast.makeText(ForgotPassword.this, "please enter email address", Toast.LENGTH_LONG).show();

                    } else if (!(editTextResetEmailAddress2.getText().toString().isEmpty())) {
                        //this is where the app is communicating with firebase to confirm user's email's existence in the app.
                        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                            /***
                             *This method displays the result of communication with firebase.
                             * @param task
                             */
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword.this, " password reset email is sent", Toast.LENGTH_LONG).show();
                                    Intent backToHomePage = new Intent(ForgotPassword.this, MainActivity.class);
                                    startActivity(backToHomePage);
                                } else {
                                    Toast.makeText(ForgotPassword.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }

        }

    }
}