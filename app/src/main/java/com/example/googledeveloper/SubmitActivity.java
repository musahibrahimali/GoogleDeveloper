package com.example.googledeveloper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubmitActivity extends AppCompatActivity {

    Button finalSubmitButton;
    EditText firstName, lastName, user_email, user_github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.back_toolbar);
        // set the action bar
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        finalSubmitButton = findViewById(R.id.final_submit_button);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        user_email = findViewById(R.id.email_address);
        user_github = findViewById(R.id.github_link_address);

        finalSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(SubmitActivity.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure you want to submit your project");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

    }

    public void SubmitToForms() {
        // get the user data
        String FirstName = firstName.getText().toString();
        String LastName = lastName.getText().toString();
        String EmailAddress = user_email.getText().toString();
        String GithubLink = user_github.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        Webservice webservice = retrofit.create(Webservice.class);
        Call<Void> call = webservice.postValues(FirstName, LastName, EmailAddress, GithubLink);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int responseCode = response.code();
                if (responseCode == 200) {
                    Toast.makeText(SubmitActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    AlertDialog.Builder alert = new AlertDialog.Builder(SubmitActivity.this);
                    alert.setTitle("Thank you");
                    alert.setMessage("Submission Successful");
                    alert.setIcon(R.drawable.ic_baseline_check);
                    alert.show();
                }
            }


            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                String throwableResponse = t.getMessage();

                AlertDialog.Builder alert = new AlertDialog.Builder(SubmitActivity.this);
                alert.setTitle("Failed");
                alert.setMessage("Submission Unsuccessful");
                alert.setIcon(R.drawable.ic_baseline_clear);
                alert.show();
            }


        });

    }

}
