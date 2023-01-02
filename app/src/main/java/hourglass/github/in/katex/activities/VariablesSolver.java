package hourglass.github.in.katex.activities;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.text.DecimalFormat;

import hourglass.github.in.katex.Complex;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class VariablesSolver extends AppCompatActivity {

//    static SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variablessolver);
        //shared prefs
//        sharedPreferences = getSharedPreferences("appSettings", Context.MODE_PRIVATE);

        // Toolbar and Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Solve for TL variables");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialise variables
        Button buttonSolve = findViewById(R.id.buttonSolve);
        Button buttonTest = findViewById(R.id.buttonLoadTestVariables);

        EditText etZl_re_inputs = findViewById(R.id.etZl_re_inputs);
        EditText etZl_im_inputs = findViewById(R.id.etZl_im_inputs);
        EditText etZ0_inputs = findViewById(R.id.etZ0_inputs);
        EditText etVg_re_inputs = findViewById(R.id.vg_inputs_re);
        EditText etVg_im_inputs = findViewById(R.id.vg_inputs_im);
        EditText etZg_re_inputs = findViewById(R.id.zg_inputs_re);
        EditText etZg_im_inputs = findViewById(R.id.zg_inputs_im);
        // When Solve button has been clicked
        buttonSolve.setOnClickListener(V -> {
            // Get values from ET. Set default to zero, if there is input then replace
            // FORMULA 1: COMPUTING REFLECTION COEFFICIENT (requires: Zl & Z0)
            double zl_re = 0;
            double zl_im = 0;
            double z0 = 0;
            // FORMULA 2: COMPUTING POWER LOAD (requires: Zl & Z0, Vg and Zg)
            double vg_re = 0;
            double vg_im = 0;
            double zg_re = 0;
            double zg_im = 0;

            if (etZl_re_inputs.getText().toString().trim().length() != 0)
                zl_re = Double.parseDouble(String.valueOf(etZl_re_inputs.getText()));
            if (etZl_im_inputs.getText().toString().trim().length() != 0)
                zl_im = Double.parseDouble(String.valueOf(etZl_im_inputs.getText()));
            if (etZ0_inputs.getText().toString().trim().length() != 0)
                z0 = Double.parseDouble(String.valueOf(etZ0_inputs.getText()));

            if (etVg_re_inputs.getText().toString().trim().length() != 0)
                vg_re = Double.parseDouble(String.valueOf(etVg_re_inputs.getText()));
            if (etVg_im_inputs.getText().toString().trim().length() != 0)
                vg_im = Double.parseDouble(String.valueOf(etVg_im_inputs.getText()));
            if (etZg_re_inputs.getText().toString().trim().length() != 0)
                zg_re = Double.parseDouble(String.valueOf(etZg_re_inputs.getText()));
            if (etZg_im_inputs.getText().toString().trim().length() != 0)
                zg_im = Double.parseDouble(String.valueOf(etZg_im_inputs.getText()));

            // Format whole number to remove .0
            DecimalFormat df = new DecimalFormat();
            df.setDecimalSeparatorAlwaysShown(false);

            // ==================================================================================================== //
            // FORMULA 1: COMPUTING REFLECTION COEFFICIENT (requires: Zl & Z0)
            // Step-by-step solution:
            // 1.1   : Default
            // 1.2   : Expansion of Complex Number
            // 1.1.1 : Default (Continuation)
            // 1.3   : Alternative Answers
            // ==================================================================================================== //

            // Take value from inputs and create new Complex Object
            Complex complex_zl = new Complex (zl_re, zl_im);
            Complex complex_z0 = new Complex (z0, 0); // put in zero for no imaginary parts

            // Init variables
            MathView refCoeff_outputs_results = findViewById(R.id.refCoeff_outputs_results);
            MathView mvRefCoeffStepsBySteps_1_1 = findViewById(R.id.mvRefCoeffStepsBySteps_1_1);
            MathView mvRefCoeffStepsBySteps_1_2 = findViewById(R.id.mvRefCoeffStepsBySteps_1_2);
            MathView mvRefCoeffStepsBySteps_1_1_1 = findViewById(R.id.mvRefCoeffStepsBySteps_1_1_1);
            MathView mvRefCoeffStepsBySteps_1_3 = findViewById(R.id.mvRefCoeffStepsBySteps_1_3);

            // Get answer and set results
            String refCoeff_answer = (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).toString();
            refCoeff_outputs_results.setDisplayText("$" + refCoeff_answer + "$");

            // Track zl imaginary sign and put in imaginary sign
            String new_zl_im = "";
            if (zl_im < 0)
                new_zl_im = String.valueOf(zl_im) + "i"; // if negative then let it be
            else
                new_zl_im = "+" + zl_im + "i"; // if positive then add positive sign

            // Get Step-by-step solution and set solution
            // Formula 1.1 : Default
            String steps_refCoeff_1_1 = "";
            steps_refCoeff_1_1 += "Step 1: Recall $\\Gamma$ (Gamma) formula:<br><span style='color:red;'>$\\Gamma = \\frac{\\Zeta_L -\\Zeta_0}{\\Zeta_L +\\Zeta_0}$</span><br><br>";
            steps_refCoeff_1_1 += "Step 2: Substitute with given inputs:<br>";
            steps_refCoeff_1_1 += "$\\Gamma =\\frac{(" + df.format(zl_re) + (new_zl_im) + ")-(" + df.format(z0) + ")}{(" + zl_re + new_zl_im + ")+(" + df.format(z0) + ")}$<br><br>";
            steps_refCoeff_1_1 += "Step 3: Group real parts and imaginary parts and simplify: <br> <span style='color:red;'><i>*Compute using calculator</i></span><br> ";
            steps_refCoeff_1_1 += "$\\Gamma =\\frac{(" + (zl_re - z0) + ")+(" + (new_zl_im) + ")}{(" + (zl_re + z0) + ")+(" + new_zl_im + ")}$<br>";

            // Formula 1.2: Expansion of Complex Number
            String steps_refCoeff_1_2 = "";
            steps_refCoeff_1_2 += "Sub-step 3a: Multiply by the conjugate: <br>";
            steps_refCoeff_1_2 += "$\\Gamma =\\frac{(" + (zl_re - z0) + ")+(" + new_zl_im + ")}{(" + (zl_re - z0) + ")+(" + new_zl_im + ")} \\times \\frac{(" + (zl_re + z0) + ")-(" + new_zl_im + ")}{(" + (zl_re + z0) + ")-(" + new_zl_im + ")}$<br><br>";
            steps_refCoeff_1_2 += "Sub-step 3b: Group real parts and imaginary parts and simplify: <br>";
            // new alpha and beta for complex object and conveniently get conjugate
            Complex refCoeff_Steps_a_top = new Complex((zl_re - z0), zl_im);
            Complex refCoeff_Steps_a_bottom = new Complex((zl_re+ z0), zl_im);
            steps_refCoeff_1_2 += "$\\Gamma =\\frac{(" + refCoeff_Steps_a_top.times(refCoeff_Steps_a_bottom.conjugate()) + ")}{(" + refCoeff_Steps_a_bottom.times(refCoeff_Steps_a_bottom.conjugate()) + ")}$<br>";

            // Formula 1.1.1: Default (Continuation)
            String steps_refCoeff_1_1_1 = "";
            steps_refCoeff_1_1_1 += "<br>Step 4: Answer:<br>";
            steps_refCoeff_1_1_1 += "$" + refCoeff_answer + "$<br>";

            // Formula 1.3: Alternative Answers
            String steps_refCoeff_1_3 = "";
            steps_refCoeff_1_3 += "Answer in Polar form<br>";
            steps_refCoeff_1_3 += "$" + (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs() + "\\angle" + (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).phase() + "\\text{ rad}$ <br>";
            steps_refCoeff_1_3 += "$" + (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs() + "\\angle" + (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).phaseDeg() + "\\degree$";

            // Set 1, 1.1, 1.2 step-by-step solution to Front-end
            mvRefCoeffStepsBySteps_1_1.setDisplayText(steps_refCoeff_1_1);
            mvRefCoeffStepsBySteps_1_2.setDisplayText(steps_refCoeff_1_2);
            mvRefCoeffStepsBySteps_1_1_1.setDisplayText(steps_refCoeff_1_1_1);
            mvRefCoeffStepsBySteps_1_3.setDisplayText(steps_refCoeff_1_3);

            // apply background colour to sub-expansions
            mvRefCoeffStepsBySteps_1_2.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
            mvRefCoeffStepsBySteps_1_3.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));

            // Example of setting background colour
            // mvRefCoeffStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));

            // ==================================================================================================== //
            // FORMULA 2: COMPUTING POWER LOAD (requires: Zl & Z0, Vg and Zg)
            // ==================================================================================================== //
            Complex complex_vg = new Complex (vg_re, vg_im);
            Complex complex_zg = new Complex (zg_re, zg_im);
            Complex static_eight = new Complex (8, 0);
            Complex static_one = new Complex (1, 0);

            Complex refCoeff = new Complex ((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).re, (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).im);

            // Init variables
            MathView mvPower_outputs_results = findViewById(R.id.power_outputs_results);
            MathView mvPowerStepsBySteps = findViewById(R.id.mvPowerStepsBySteps);

            // Pre-requisites initialisations for Answer and Step-by-step
            // to make 1 minus-able
            Complex abs_refCoeff_squared = new Complex(((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs()) * ((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs()),0);

            // Get answer and set result
            String power_answer = (((complex_vg.times(complex_vg)).divides(static_eight.times(complex_zg))).times(static_one.minus(abs_refCoeff_squared))).toString();
            mvPower_outputs_results.setDisplayText(power_answer);

            // Get Step-by-step solution and set solution
            String steps_power = "";
            steps_power += "Step 1: Recall $P_L$ (Power Load) formula:<br><span style='color:red;'>$P_L = \\frac{(V_g)^2}{8Z_g}(1-\\vert\\Gamma_L\\vert^2)$</span><br><br>";
            steps_power += "Step 2: Compute $\\Gamma_L$ (Reflection Coefficient) <br>Expand for detailed steps. <br>";
            steps_power += "$\\Gamma_L = " + ((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0))) + "$<br><br>";
            steps_power += "Step 3: Substitute inputs into $P_L$ formula:<br>";
            steps_power += "$P_L = \\frac{(" + vg_re + " + " + vg_im + "i)^2}{8(" + zg_re + " + " + zg_im + "i)}(1-\\vert " + refCoeff_answer + "\\vert^2)$ <br><br>";
            steps_power += "Step 4: Calculate value of $\\vert\\Gamma_L\\vert$:<br>";
            steps_power += "$P_L = \\frac{(" + vg_re + " + " + vg_im + "i)^2}{8(" + zg_re + " + " + zg_im + "i)}(1-( " + (complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs() + ")^2)$<br><br>";
            steps_power += "Step 5: Calculate value of $\\vert\\Gamma_L\\vert^2$:<br>";
            steps_power += "$P_L = \\frac{(" + vg_re + " + " + vg_im + "i)^2}{8(" + zg_re + " + " + zg_im + "i)}(1-( " + (((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs()) * ((complex_zl.minus(complex_z0)).divides(complex_zl.plus(complex_z0)).abs()))+ "))$<br><br>";
            steps_power += "Step 6: Expand:<br>";
            steps_power += "$P_L = \\frac{" + complex_vg.times(complex_vg) + "}{" + static_eight.times(complex_zg) + "}(" + static_one.minus(abs_refCoeff_squared)+ ")$<br><br>";
            steps_power += "Step 7: Answer:<br>";
            steps_power += "$P_L = " + (complex_vg.times(complex_vg).divides(static_eight.times(complex_zg))).times(static_one.minus(abs_refCoeff_squared)) +"\\text{ W}$<br><br>";
            mvPowerStepsBySteps.setDisplayText(steps_power);

        });
        // When test button has been pressed, load default inputs
        buttonTest.setOnClickListener(V -> {
            etZ0_inputs.setText(String.valueOf(75)); //z0
            etZl_re_inputs.setText(String.valueOf(60));
            etZl_im_inputs.setText(String.valueOf(-40));
            etVg_re_inputs.setText(String.valueOf(15));
            etVg_im_inputs.setText(String.valueOf(0));
            etZg_re_inputs.setText(String.valueOf(75));
            etZg_im_inputs.setText(String.valueOf(0));
        });
        // Scroll to bottom when expanding
        final ScrollView scrollview = findViewById(R.id.scrollView);

        // FORMULA 1 (REF COEFF) : Expandable and collapsible steps
        CardView cardView1 = findViewById(R.id.base_cardview_refCoeff);
        ImageView arrow1 = findViewById(R.id.show_refCoeff);
        Group hiddenGroup1 = findViewById(R.id.card_group1);
        arrow1.setOnClickListener(view -> {
            if (hiddenGroup1.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1.setVisibility(View.GONE);
                arrow1.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1.setVisibility(View.VISIBLE);
                arrow1.setImageResource(android.R.drawable.arrow_up_float);
                // scroll to cardview
                scrollview.postDelayed(new Runnable() {
                    public void run() {
                        scrollview.scrollTo(0, (int) cardView1.getY());
                    }
                }, 100);
            }
        });
        // FORMULA 1.2 (Expansion of Complex Number)
        Group hiddenGroup1_2 = findViewById(R.id.card_group1_2);
        ImageView arrow1_2 = findViewById(R.id.show_1_2);
        arrow1_2.setOnClickListener(view -> {
            if (hiddenGroup1_2.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1_2.setVisibility(View.GONE);
                arrow1_2.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1_2.setVisibility(View.VISIBLE);
                arrow1_2.setImageResource(android.R.drawable.arrow_up_float);

            }
        });
        // FORMULA 1.3 (Alternative Answer)
        Group hiddenGroup1_3 = findViewById(R.id.card_group1_3);
        ImageView arrow1_3 = findViewById(R.id.show_1_3);
        arrow1_3.setOnClickListener(view -> {
            if (hiddenGroup1_3.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1_3.setVisibility(View.GONE);
                arrow1_3.setImageResource(android.R.drawable.arrow_down_float);
            } else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenGroup1_3.setVisibility(View.VISIBLE);
                arrow1_3.setImageResource(android.R.drawable.arrow_up_float);

            }
        });
        // FORMULA 2 (POWER) : Expandable and collapsible steps
        CardView cardView2 = findViewById(R.id.base_cardview_power);
        ImageView arrow2 = findViewById(R.id.show_power);
        Group hiddenGroup2 = findViewById(R.id.card_group2);
        arrow2.setOnClickListener(view -> {
            if (hiddenGroup2.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenGroup2.setVisibility(View.GONE);
                arrow2.setImageResource(android.R.drawable.arrow_down_float);

            } else {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenGroup2.setVisibility(View.VISIBLE);
                arrow2.setImageResource(android.R.drawable.arrow_up_float);
                // scroll to cardview
                scrollview.postDelayed(new Runnable() {
                    public void run() {
                        scrollview.scrollTo(0, (int) cardView2.getY());
                    }
                }, 100);
            }
        });
    }

//    public static void saveDP(String new_dp) {
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("dp", new_dp);
//        editor.apply();
//    }
//
//    public static String loadDP() {
//        // 3 as default
//        return sharedPreferences.getString("dp", "3");
//    }

    // Back button
    public boolean onOptionsItemSelected(MenuItem item) {
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent a = new Intent(this, MainActivity.class);
                finish();
                return true;
            case R.id.variablesSolver_help:
                Intent b = new Intent(this, ComplexNumberAdd.class);
                this.startActivity(b);
                return true;
            case R.id.variablesSolver_settings:
                Intent c = new Intent(this, VariablesSolverSettings.class);
                this.startActivity(c);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Menu button in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.variablessolver_menu, menu);
        return true;
    }


}
