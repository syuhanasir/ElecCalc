package com.example.eleccalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etRebate, etValue;
    Button btnCalculate, btnClear;
    TextView textTotalRebate, textTotal, textRebate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_main);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        etRebate = findViewById(R.id.etRebate);
        etValue = findViewById(R.id.etValue);
        textTotalRebate = findViewById(R.id.textTotalRebate);
        textTotal = findViewById(R.id.textTotal);
        textRebate = findViewById(R.id.textRebate);

        Toolbar myToolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        myToolbar.setLogo(R.mipmap.ic_launcher_icon);

        myToolbar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "You are already in the Home Page",Toast.LENGTH_SHORT).show();
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String electricUsed = etValue.getText().toString();
                String rebateCharge = etRebate.getText().toString();
                double TotalUsed = 0.0;
                double totalRebate = 0.0;
                try {
                    double value = Double.parseDouble(electricUsed);
                    if (value <= 200) {
                        TotalUsed = value * 0.218;
                    } else if (value > 200 && value < 301) {
                        TotalUsed = (200 * 0.218) + ((value - 200) * 0.334);
                    } else if (value > 300 && value < 600) {
                        TotalUsed = (200 * 0.218) + (100 * 0.334) + ((value - 300) * 0.516);
                    } else if (value > 600) {
                        TotalUsed = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((value - 600) * 0.546);
                    }

                    double rebate = Double.parseDouble(rebateCharge);

                    if (rebate <0 || rebate >5){
                        etRebate.setError("Please enter a rebate value between 0 and 5");
                        Toast.makeText(getApplicationContext(), "Rebate must be between 0 and 5",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double percentRebate = TotalUsed * (rebate / 100.0);

                    totalRebate = TotalUsed - percentRebate;

                    textTotalRebate.setText("Total Payment: RM" + String.format("%.2f", totalRebate));
                    textTotal.setText("Total: RM" + String.format("%.2f", TotalUsed));
                    textRebate.setText("Total Rebate: RM" + String.format("%.2f", percentRebate));
                } catch(NumberFormatException nfe){

                    etValue.setText("");
                    etRebate.setText("");
                    etRebate.setError("Please enter a valid number");
                    etValue.setError("Please enter a a valid number");
                    Toast.makeText(getApplicationContext(), "Please input valid numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etValue.setText("");
                etRebate.setText("");
                textTotalRebate.setText("Total Payment: RM");
                textTotal.setText("Total: RM");
                textRebate.setText("Total Rebate: RM");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editRebate), (v, insets) -> {
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

            Intent intent = new Intent(MainActivity.this, About.class);
            startActivity(intent);

            Toast.makeText(this, "About clicked",Toast.LENGTH_SHORT).show();

            return true;

        }else if (selected == R.id.menuInstructions){
            Toast.makeText(this, "Instructions clicked",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(MainActivity.this, Instructions.class);
            startActivity(intent);

            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}