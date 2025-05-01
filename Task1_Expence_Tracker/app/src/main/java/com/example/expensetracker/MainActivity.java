package com.example.expensetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextAmount, editTextCategory;
    Button buttonAdd;
    TextView textViewResult;
    ArrayList<String> expenseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        editTextCategory = findViewById(R.id.editTextCategory);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewResult = findViewById(R.id.textViewResult);

        buttonAdd.setOnClickListener(v -> {
            String amount = editTextAmount.getText().toString();
            String category = editTextCategory.getText().toString();

            if (!amount.isEmpty() && !category.isEmpty()) {
                String item = "₹" + amount + " - " + category;
                expenseList.add(item);
                displayExpenses();
                editTextAmount.setText("");
                editTextCategory.setText("");
            } else {
                textViewResult.setText("Please enter both fields.");
            }
        });
    }

    private void displayExpenses() {
        StringBuilder builder = new StringBuilder("Expenses:\n");
        for (String s : expenseList) {
            builder.append(s).append("\n");
        }
        textViewResult.setText(builder.toString());
    }
}
