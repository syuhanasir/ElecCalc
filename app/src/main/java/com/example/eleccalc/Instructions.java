package com.example.eleccalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Instructions extends AppCompatActivity {

    TextView titleText, step1Text, step2Text, step3Text, step4Text, aboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_instructions);

        Toolbar myToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setLogo(R.mipmap.ic_launcher_icon);

        titleText = findViewById(R.id.titleText);
        step1Text = findViewById(R.id.step1Text);
        step2Text = findViewById(R.id.step2Text);
        step3Text = findViewById(R.id.step3Text);
        step4Text = findViewById(R.id.step4Text);
        aboutText = findViewById(R.id.aboutText);

        // Get the logo view and set the click listener
        myToolbar.setOnClickListener(v -> {
            // When the logo is clicked, navigate to MainActivity
            Intent intent = new Intent(Instructions.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You are redirected to the Home Page",Toast.LENGTH_SHORT).show();
        });

        // Enable back button in toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Instructions");
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected = item.getItemId();

        if (selected == R.id.menuAbout) {

            Toast.makeText(this, "About clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Instructions.this, About.class);
            startActivity(intent);

            return true;

        } else if (selected == R.id.menuInstructions) {
            Toast.makeText(this, "Instructions clicked", Toast.LENGTH_SHORT).show();
            return true;

        } else if (selected == android.R.id.home) {  // Use android.R.id.home for the system's back button
            finish();  // Close the activity and go back
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}