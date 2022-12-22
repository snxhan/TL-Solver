package hourglass.github.in.katex.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import hourglass.github.in.katex.ComplexBD;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class ComplexNumberMultiply extends AppCompatActivity{
    // Global variables

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexnumber_multiply);
        // Initialise
//        LinearLayout parent_layout = findViewById(R.id.linear_parent_layout);
        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        TextView tvOutputHeader = findViewById(R.id.tvOutputHeader);
        EditText etAlphaRe = findViewById(R.id.etAlphaRe);
        EditText etAlphaIm = findViewById(R.id.etAlphaIm);
        EditText etBetaRe = findViewById(R.id.etBetaRe);
        EditText etBetaIm = findViewById(R.id.etBetaIm);

        // Expandable and Collapsible
        CardView cardView = findViewById(R.id.base_cardview);
        ImageView arrow = findViewById(R.id.show);
        Group hiddenGroup = findViewById(R.id.card_group);

        // Toolbar and Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Multiply 2 Complex Numbers");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set items to GONE
        tvOutputHeader.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);

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
            MathView mvComplexMultiplicationStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps);
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
