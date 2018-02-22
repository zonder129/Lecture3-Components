package ru.mail.park.lecture3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalcInputActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private EditText operand1;
    private EditText operand2;

    private final View.OnClickListener operationClick = new View.OnClickListener() {
        @Override
        public void onClick(final View view) {
            double op1 = Double.parseDouble(operand1.getText().toString());
            double op2 = Double.parseDouble(operand2.getText().toString());
            String action;
            switch (view.getId()) {
                case R.id.btn_add:
                    action = CalcActivity.ACTION_ADD;
                    break;
                case R.id.btn_sub:
                    action = CalcActivity.ACTION_SUB;
                    break;
                case R.id.btn_mul:
                    action = CalcActivity.ACTION_MUL;
                    break;
                case R.id.btn_div:
                    action = CalcActivity.ACTION_DIV;
                    break;
                default:
                    action = "";
            }

            final Intent intent = new Intent(CalcInputActivity.this, CalcActivity.class);
            intent.putExtra(CalcActivity.EXTRA_OPERAND1, op1);
            intent.putExtra(CalcActivity.EXTRA_OPERAND2, op2);
            intent.setAction(action);
            startActivityForResult(intent, REQUEST_CODE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_input);

        operand1 = findViewById(R.id.operand1);
        operand2 = findViewById(R.id.operand2);

        findViewById(R.id.btn_add).setOnClickListener(operationClick);
        findViewById(R.id.btn_sub).setOnClickListener(operationClick);
        findViewById(R.id.btn_mul).setOnClickListener(operationClick);
        findViewById(R.id.btn_div).setOnClickListener(operationClick);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final double res = data.getDoubleExtra(CalcActivity.EXTRA_RESULT, Double.NaN);
                Toast.makeText(this, String.valueOf(res), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.oops, Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
