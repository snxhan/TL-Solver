package hourglass.github.in.katex.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import hourglass.github.in.katex.Complex;
import hourglass.github.in.katex.ComplexBD;
import hourglass.github.in.katex.Formulas;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class ComplexNumberBasicOperations extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexnumber_basicoperations);
        // Toolbar and Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Compute basic operations (+/-/x/div)");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialise
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        TextView tvOutputHeader = findViewById(R.id.tvOutputHeader);
        EditText etAlphaRe = findViewById(R.id.etAlphaRe);
        EditText etAlphaIm = findViewById(R.id.etAlphaIm);
        EditText etBetaRe = findViewById(R.id.etBetaRe);
        EditText etBetaIm = findViewById(R.id.etBetaIm);
        // Expandable and Collapsible
        CardView cardView = findViewById(R.id.base_cardview);
        ImageView arrow = findViewById(R.id.show);
        Group hiddenGroup = findViewById(R.id.card_group);
        // Set items to GONE
        tvOutputHeader.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        // When Add button has been clicked
        buttonAdd.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            double alphaRe = 0.0;
            double alphaIm = 0.0;
            double betaRe = 0.0;
            double betaIm = 0.0;

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = Double.parseDouble(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = Double.parseDouble(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = Double.parseDouble(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = Double.parseDouble(String.valueOf(etBetaIm.getText()));

            Complex a = new Complex(alphaRe, alphaIm);
            Complex b = new Complex(betaRe, betaIm);

            // Format whole number to remove .0
            DecimalFormat df = new DecimalFormat();
            df.setDecimalSeparatorAlwaysShown(false);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.plus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexConversionResults);
            mvComplexMultiplicationResults.setDisplayText(Formulas.addComplex_answer(a, b));
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(Formulas.addComplex_steps(a, b));

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Subtract button has been clicked
        buttonSubtract.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            double alphaRe = 0.0;
            double alphaIm = 0.0;
            double betaRe = 0.0;
            double betaIm = 0.0;

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = Double.parseDouble(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = Double.parseDouble(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = Double.parseDouble(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = Double.parseDouble(String.valueOf(etBetaIm.getText()));

            Complex a = new Complex(alphaRe, alphaIm);
            Complex b = new Complex(betaRe, betaIm);

            // Format whole number to remove .0
            DecimalFormat df = new DecimalFormat();
            df.setDecimalSeparatorAlwaysShown(false);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.minus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexConversionResults);
            mvComplexMultiplicationResults.setDisplayText(Formulas.subtractComplex_answer(a, b));
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(Formulas.subtractComplex_steps(a, b));

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Multiply button has been clicked
        buttonMultiply.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            double alphaRe = 0.0;
            double alphaIm = 0.0;
            double betaRe = 0.0;
            double betaIm = 0.0;

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = Double.parseDouble(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = Double.parseDouble(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = Double.parseDouble(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = Double.parseDouble(String.valueOf(etBetaIm.getText()));

            Complex a = new Complex(alphaRe, alphaIm);
            Complex b = new Complex(betaRe, betaIm);

            // Format whole number to remove .0
            DecimalFormat df = new DecimalFormat();
            df.setDecimalSeparatorAlwaysShown(false);

            MathView mathView = new MathView(getApplicationContext());
            mathView.setClickable(true);
            mathView.setTextSize(30);
            mathView.setDisplayText("$\\alpha + \\beta = "+ a.times(b) + "$ ");
            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexConversionResults);
            mvComplexMultiplicationResults.setDisplayText(Formulas.multiplyComplex_answer(a, b));
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(Formulas.multiplyComplex_steps(a, b));

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Divide button has been clicked
        buttonDivide.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            double alphaRe = 0.0;
            double alphaIm = 0.0;
            double betaRe = 0.0;
            double betaIm = 0.0;

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = Double.parseDouble(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = Double.parseDouble(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = Double.parseDouble(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = Double.parseDouble(String.valueOf(etBetaIm.getText()));

            Complex a = new Complex(alphaRe, alphaIm);
            Complex b = new Complex(betaRe, betaIm);

            // Format whole number to remove .0
            DecimalFormat df = new DecimalFormat();
            df.setDecimalSeparatorAlwaysShown(false);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.plus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexConversionResults);
            mvComplexMultiplicationResults.setDisplayText(Formulas.divideComplex_answer(a, b));
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(Formulas.divideComplex_steps(a, b));

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // Expandable and collapsible steps
        arrow.setOnClickListener(view -> {
            if(hiddenGroup.getVisibility() == View.VISIBLE){
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenGroup.setVisibility(View.GONE);
                arrow.setImageResource(android.R.drawable.arrow_down_float);
            }
            else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenGroup.setVisibility(View.VISIBLE);
                arrow.setImageResource(android.R.drawable.arrow_up_float);
            }
        });
    }
    // Back button
    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent a = new Intent(this, MainActivity.class);
//                this.startActivity(a);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
