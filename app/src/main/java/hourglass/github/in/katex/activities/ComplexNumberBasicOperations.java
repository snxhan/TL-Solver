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

import hourglass.github.in.katex.ComplexBD;
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
            BigDecimal alphaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal alphaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = new BigDecimal(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = new BigDecimal(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = new BigDecimal(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = new BigDecimal(String.valueOf(etBetaIm.getText()));

            ComplexBD a = new ComplexBD(alphaRe, alphaIm);
            ComplexBD b = new ComplexBD(betaRe, betaIm);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.plus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexBasicOperationsResults);
            mvComplexMultiplicationResults.setDisplayText("$" + a.plus(b).toPString() + "$");
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);
            String steps = "";

            steps += "Step 1: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)+(c+di)$</span><br> $= (a+c)+(b+d)i$<br>";
            steps += "$=(" + a.re + " + " + b.re + ") + (" + a.im + " + " + b.im + ")i$<br><br>";
            steps += "Step 2: Expand:<br>";
            steps += "$(" + a.re.add(b.re) + ") + (" + a.im.add(b.im) + ")i$<br><br>" ;
            steps += "Step 4: Answer:<br>";
            steps += "$" + (a.plus(b)).toPString() + "$" ;

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(steps);

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Subtract button has been clicked
        buttonSubtract.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            BigDecimal alphaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal alphaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = new BigDecimal(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = new BigDecimal(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = new BigDecimal(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = new BigDecimal(String.valueOf(etBetaIm.getText()));

            ComplexBD a = new ComplexBD(alphaRe, alphaIm);
            ComplexBD b = new ComplexBD(betaRe, betaIm);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.minus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexBasicOperationsResults);
            mvComplexMultiplicationResults.setDisplayText("$" + a.minus(b).toPString() + "$");
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);
            String steps = "";

            steps += "Step 1: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)-(c+di)$</span><br> $= (a-c)+(b-d)i$<br>";
            steps += "$=(" + a.re + " - " + b.re + ") + (" + a.im + " - " + b.im + ")i$<br><br>";
            steps += "Step 2: Expand:<br>";
            steps += "$(" + a.re.subtract(b.re) + ") + (" + a.im.subtract(b.im) + ")i$<br><br>" ;
            steps += "Step 4: Answer:<br>";
            steps += "$" + (a.minus(b)).toPString() + "$" ;

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(steps);

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Multiply button has been clicked
        buttonMultiply.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            BigDecimal alphaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal alphaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = new BigDecimal(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = new BigDecimal(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = new BigDecimal(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = new BigDecimal(String.valueOf(etBetaIm.getText()));

            ComplexBD a = new ComplexBD(alphaRe, alphaIm);
            ComplexBD b = new ComplexBD(betaRe, betaIm);

            MathView mathView = new MathView(getApplicationContext());
            mathView.setClickable(true);
            mathView.setTextSize(30);
            mathView.setDisplayText("$\\alpha + \\beta = "+ a.times(b) + "$ ");
            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexBasicOperationsResults);
            mvComplexMultiplicationResults.setDisplayText("$" + a.times(b).toPString() + "$");
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);
            String steps = "";
//            steps += "<p>Step 1: Apply complex arithmetic rule:<br>$(a+bi)(c+di)$<br> $= (ac-bd)+(ad+bc)i$<br>";
//            steps += "$=(" + a.re + " * " + b.re + " - " + a.im + " * " + b.im + ") + (" + a.re + " * " + b.im + " + " + a.im + " * " + b.re + ")i$</p>";
//            steps += "<p>Step 2: Expand:<br>";
//            steps += "$(" + a.re.multiply(b.re) + " - " + a.im.multiply(b.im) + ") + (" + a.re.multiply(b.im) + " + " + a.im.multiply(b.re) + ")i$</p>" ;
//            steps += "<p>Step 3: Simplify:<br>";
//            steps += "$(" + (a.re.multiply(b.re)).subtract(a.im.multiply(b.im)) + ") + (" + (a.re.multiply(b.im)).add(a.im.multiply(b.re)) + ")i$</p>" ;
//            steps += "<p>Step 4: Answer:<br>";
//            steps += "$" + a.times(b) + "$</p>" ;

            steps += "Step 1: Apply complex arithmetic rule:<br><span style='color:red;'>$(a+bi)(c+di)$</span><br> $= (ac-bd)+(ad+bc)i$<br>";
            steps += "$=(" + a.re + " * " + b.re + " - " + a.im + " * " + b.im + ") + (" + a.re + " * " + b.im + " + " + a.im + " * " + b.re + ")i$<br><br>";
            steps += "Step 2: Expand:<br>";
            steps += "$(" + a.re.multiply(b.re) + " - " + a.im.multiply(b.im) + ") + (" + a.re.multiply(b.im) + " + " + a.im.multiply(b.re) + ")i$<br><br>" ;
            steps += "Step 3: Simplify:<br>";
            steps += "$(" + (a.re.multiply(b.re)).subtract(a.im.multiply(b.im)) + ") + (" + (a.re.multiply(b.im)).add(a.im.multiply(b.re)) + ")i$<br><br>" ;
            steps += "Step 4: Answer:<br>";
            steps += "$" + (a.times(b)).toPString() + "$" ;

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(steps);

            // Make visible results
            tvOutputHeader.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.VISIBLE);
        });
        // When Divide button has been clicked
        buttonDivide.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            BigDecimal alphaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal alphaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaRe = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal betaIm = new BigDecimal(String.valueOf(BigDecimal.ZERO));

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = new BigDecimal(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = new BigDecimal(String.valueOf(etAlphaIm.getText()));
            if(etBetaRe.getText().toString().trim().length() != 0)
                betaRe = new BigDecimal(String.valueOf(etBetaRe.getText()));
            if(etBetaIm.getText().toString().trim().length() != 0)
                betaIm = new BigDecimal(String.valueOf(etBetaIm.getText()));

            ComplexBD a = new ComplexBD(alphaRe, alphaIm);
            ComplexBD b = new ComplexBD(betaRe, betaIm);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ a.plus(b) + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexMultiplicationResults = findViewById(R.id.mvComplexBasicOperationsResults);
            mvComplexMultiplicationResults.setDisplayText("$" + a.div(b).toPString() + "$");
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);
            String steps = "";

            // Track a.im and b.im imaginary sign and put in imaginary sign if positive
            String new_a_im = "";
            if(a.im.compareTo(BigDecimal.ZERO) < 0)
                new_a_im = String.valueOf(a.im) + "i"; // if negative then let it be
            else
                new_a_im = "+" + a.im + "i"; // if positive then add positive sign
            String new_b_im = "";
            if(b.im.compareTo(BigDecimal.ZERO) < 0)
                new_b_im = String.valueOf(b.im) + "i"; // if negative then let it be
            else
                new_b_im = "+" + b.im + "i"; // if positive then add positive sign

            steps += "Step 1: Multiply with conjugate: <br>$\\frac{"+ a.re + new_a_im +"}{"+ b.re + new_b_im +"}$<span style='color:red;'>$\\times\\frac{"+ b.conjugate() +"}{"+ b.conjugate() +"}$</span><br>";
            steps += "$=\\frac{(" + a.times(b.conjugate()).toPString() + ")}{(" + b.times(b.conjugate()) + ")}$<br><br>";
            steps += "Step 3: Rewrite in $(a+bi)$ form:<br>";
            steps += "$=\\frac{" + (a.times(b.conjugate())).re + "}{" + b.times(b.conjugate()) + "} + \\frac{" + (a.times(b.conjugate())).im + "}{" + b.times(b.conjugate()) + "}i$<br><br>" ;
            steps += "Step 4: Answer:<br>";
            steps += "$=" + a.div(b).toPString() + "$" ;

            // new alpha and beta for complex object and conveniently get conjugate
//            ComplexBD refCoeff_Steps_a_top = new ComplexBD((zl_re.subtract(zo)), zl_im);
//            ComplexBD refCoeff_Steps_a_bottom = new ComplexBD((zl_re.add(zo)), zl_im);
//            steps_refCoeff += "$\\Gamma =\\frac{(" + refCoeff_Steps_a_top.times(refCoeff_Steps_a_bottom.conjugate()).toPString() + ")}{(" + refCoeff_Steps_a_bottom.times(refCoeff_Steps_a_bottom.conjugate()) + ")}$<br><br>";
//            steps_refCoeff += "Step 4: Answer:<br>";
//            steps_refCoeff += "$" + ((complex_zl.minus(complex_zo)).div(complex_zl.plus(complex_zo))).toPString() + "$<br><br>";

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvComplexMultiplicationStepsBySteps.setDisplayText(steps);

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
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ComplexNumber.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
