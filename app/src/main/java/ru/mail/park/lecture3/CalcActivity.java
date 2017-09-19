package ru.mail.park.lecture3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class CalcActivity extends AppCompatActivity {

    public static final String ACTION_ADD = "+";
    public static final String ACTION_SUB = "-";
    public static final String ACTION_MUL = "*";
    public static final String ACTION_DIV = "/";

    public static final String EXTRA_OPERAND1 = "operand_1";
    public static final String EXTRA_OPERAND2 = "operand_2";
    public static final String EXTRA_RESULT = "result";

    public static final int RESULT_ERROR = RESULT_FIRST_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        final double op1 = getIntent().getDoubleExtra(EXTRA_OPERAND1, Double.NaN);
        final double op2 = getIntent().getExtras().getDouble(EXTRA_OPERAND2, Double.NaN);
        final double res;
        boolean ok = true;

        final String action = getIntent().getAction();
        switch (action) {
            case ACTION_ADD:
                res = op1 + op2;
                break;
            case ACTION_SUB:
                res = op1 - op2;
                break;
            case ACTION_MUL:
                res = op1 * op2;
                break;
            case ACTION_DIV:
                res = op1 / op2;
                break;
            default:
                res = Double.NaN;
                ok = false;
                break;
        }

        final TextView text = (TextView) findViewById(R.id.text);

        if (ok) {
            setResult(RESULT_OK, new Intent().putExtra(EXTRA_RESULT, res));
            text.setText(String.format(Locale.getDefault(), "%f %s %f = %f", op1, action, op2, res));
        } else {
            setResult(RESULT_ERROR);
            text.setText(R.string.title_error);
        }

        findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                finish();
            }
        });
    }
}
