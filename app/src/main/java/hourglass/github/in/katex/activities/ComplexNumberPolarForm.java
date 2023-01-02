package hourglass.github.in.katex.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import hourglass.github.in.katex.Complex;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class ComplexNumberPolarForm extends AppCompatActivity{
    // Global variables

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexnumber_polarform);
        // Initialise
//        LinearLayout parent_layout = findViewById(R.id.linear_parent_layout);
        Button buttonConvert = findViewById(R.id.buttonConvert);
        TextView tvOutputHeader = findViewById(R.id.tvOutputHeader);
        EditText etAlphaRe = findViewById(R.id.etAlphaRe);
        EditText etAlphaIm = findViewById(R.id.etAlphaIm);

        // Expandable and Collapsible
        CardView cardView = findViewById(R.id.base_cardview);
        ImageView arrow = findViewById(R.id.show);
        Group hiddenGroup = findViewById(R.id.card_group);

        // Toolbar and Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Complex Number in Polar Form");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set items to GONE
        tvOutputHeader.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);

        // When Multiply button has been clicked
        buttonConvert.setOnClickListener(v -> {
            // Get values from ET. Set default to zero, if there is input then replace
            double alphaRe = 0;
            double alphaIm = 0;

            if(etAlphaRe.getText().toString().trim().length() != 0)
                alphaRe = Double.parseDouble(String.valueOf(etAlphaRe.getText()));
            if(etAlphaIm.getText().toString().trim().length() != 0)
                alphaIm = Double.parseDouble(String.valueOf(etAlphaIm.getText()));

            Complex complexAlpha = new Complex(alphaRe, alphaIm);

//            MathView mathView = new MathView(getApplicationContext());
//            mathView.setClickable(true);
//            mathView.setTextSize(30);
//            mathView.setDisplayText("$\\alpha + \\beta = "+ complexAlpha.abs() + "$ ");
//            mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
//            parent_layout.addView(mathView);

            // Display results and step by step solution
            MathView mvComplexConversionResults = findViewById(R.id.mvComplexConversionResults);
            mvComplexConversionResults.setDisplayText("$" + complexAlpha.abs() + "&nbsp;\\angle&nbsp;" + complexAlpha.phase() + "\\text{ rad}$");
            MathView mvSBSComplexNumberToPolarForm = findViewById(R.id.mvSBSComplexNumberToPolarForm);

            String steps = "";
            steps += "<b>Step 1: To convert Complex Number $z$ to Polar Form $r \\angle\\theta$ recall that: </b><br>" +
                    "<span style='color:red;'>$(z = x+iy) = (r\\angle\\theta) $</span><br><br>" +
                    "To find $r$; which is the Modulus of $z$,<br><span style='color:red;'>$r$</span> $= \\vert z\\vert = $<span style='color:red;'>$&nbsp;\\sqrt{x^2+y^2}$</span><br>" +
                    "To find $\\theta;$ which is the Argument of $z$, <br><span style='color:red;'>$\\theta$</span> $= \\arg(z) =$<span style='color:red;'>$&nbsp;\\tan^{-1} \\frac{y}{x}$</span><br><br>";
            steps += "<b>Step 2: Solve for $r$ and $\\theta$ :</b><br>" +
                    "$r = \\sqrt{" + alphaRe + "^2 + " + alphaIm + "^2} =$ <span style='color:green;'>$" + complexAlpha.abs() + "$</span> <br>" +
                    "$\\theta = \\tan^{-1} \\frac{" + alphaIm + "}{"  + alphaRe + "} = $ <span style='color:green;'>$" + complexAlpha.phase() + "$</span>$\\text{ rad}$<br>" +
                    "$\\theta = \\tan^{-1} \\frac{" + alphaIm + "}{"  + alphaRe + "} = $ <span style='color:green;'>$" + complexAlpha.phaseDeg() + "$</span>$\\degree$<br><br>";
            steps += "<b>Step 3: Answer:</b><br>" +
                    "$" + complexAlpha.abs() + "&nbsp;\\angle&nbsp;" + complexAlpha.phase() + "\\text{ rad}$<br>";

//            mvComplexMultiplicationStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvSBSComplexNumberToPolarForm.setDisplayText(steps);

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
