package com.example.eleccalc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.URL;

public class About extends AppCompatActivity {

    TextView textQuote, textName, textSID, textGp, textProg, textCR;
    Button buttonGH;
    ImageView imageSyu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_about);

        Toolbar myToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setLogo(R.mipmap.ic_launcher_icon);

        myToolbar.setOnClickListener(v -> {
            Intent intent = new Intent(About.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You are redirected to the Home Page",Toast.LENGTH_SHORT).show();
        });

        setTitle("About");

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        textQuote = findViewById(R.id.textQuote);
        textName = findViewById(R.id.textName);
        textSID = findViewById(R.id.textSID);
        textGp = findViewById(R.id.textGp);
        textProg = findViewById(R.id.textProg);
        buttonGH = findViewById(R.id.buttonGH);
        textCR = findViewById(R.id.textCR);
        imageSyu = findViewById(R.id.imageSyu);

        textName.setText("NUR AIN SYUHADA BINTI MOHD NASIR");
        textSID.setText("2022887518");
        textGp.setText("RCDCS2405B");
        textProg.setText("CDCS240");
        textCR.setText("© 2024 syuhada. All rights reserved");

        buttonGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/syuhanasir"));
                startActivity(intent);
            }
        });

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

            Toast.makeText(this, "About clicked",Toast.LENGTH_SHORT).show();

            return true;

        }else if (selected == R.id.menuInstructions){
            Toast.makeText(this, "Instructions clicked",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(About.this, Instructions.class);
            startActivity(intent);

            return true;

        }else if (selected == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }


}