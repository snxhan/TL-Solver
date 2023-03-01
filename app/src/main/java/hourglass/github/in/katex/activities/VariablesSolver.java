package hourglass.github.in.katex.activities;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
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
import hourglass.github.in.katex.Formulas;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class VariablesSolver extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variablessolver);
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
            double zl_re = 0.0;
            double zl_im = 0.0;
            double z0 = 0.0;
            // FORMULA 2: COMPUTING POWER LOAD (requires: Zl & Z0, Vg and Zg)
            double vg_re = 0.0;
            double vg_im = 0.0;
            double zg_re = 0.0;
            double zg_im = 0.0;

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
            String refCoeff_answer = Formulas.reflectionCoefficient_answer(complex_zl, complex_z0);
            refCoeff_outputs_results.setDisplayText(refCoeff_answer);

            // Set 1, 1.1, 1.2 step-by-step solution to Front-end
            mvRefCoeffStepsBySteps_1_1.setDisplayText(Formulas.reflectionCoefficient_steps_1_1(complex_zl, complex_z0));
            mvRefCoeffStepsBySteps_1_2.setDisplayText(Formulas.reflectionCoefficient_steps_1_2(complex_zl, complex_z0));
            mvRefCoeffStepsBySteps_1_1_1.setDisplayText(Formulas.reflectionCoefficient_steps_1_1_1(complex_zl, complex_z0));
            mvRefCoeffStepsBySteps_1_3.setDisplayText(Formulas.reflectionCoefficient_steps_1_3(complex_zl, complex_z0));

            // Apply background colour to sub-expansions
            mvRefCoeffStepsBySteps_1_2.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));
            mvRefCoeffStepsBySteps_1_3.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.gray));

            // ==================================================================================================== //
            // FORMULA 2: COMPUTING POWER LOAD (requires: Zl & Z0, Vg and Zg)
            // Step-by-Step solution:
            // 1.1.1 : Default
            // ==================================================================================================== //

            // Take value from inputs and create new Complex Object
            Complex complex_vg = new Complex (vg_re, vg_im);
            Complex complex_zg = new Complex (zg_re, zg_im);

            // Init variables
            MathView mvPower_outputs_results = findViewById(R.id.power_outputs_results);
            MathView mvPowerStepsBySteps = findViewById(R.id.mvPowerStepsBySteps);

            // Get answer and set result
            String power_answer = Formulas.powerLoad_answer(complex_zl, complex_z0, complex_vg, complex_zg);
            mvPower_outputs_results.setDisplayText( power_answer + "&nbsp;$W$");

            // Set steps
            mvPowerStepsBySteps.setDisplayText(Formulas.powerLoad_steps_1_1(complex_zl, complex_z0, complex_vg, complex_zg));

            // Apply background colour to sub-expansions
        });

        // When test button is pressed, load default inputs
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
