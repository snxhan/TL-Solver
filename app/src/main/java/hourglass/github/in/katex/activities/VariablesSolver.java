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
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import org.w3c.dom.Text;

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
        EditText etLambda_inputs = findViewById(R.id.et_lambda_inputs);
        // When Solve button has been clicked
        buttonSolve.setOnClickListener(V -> {
            // Get values from ET. Set default to zero, if there is input then replace
            // FORMULA 1: COMPUTING REFLECTION COEFFICIENT (requires: Zl & Z0)
            double zl_re = 0.0;
            double zl_im = 0.0;
            double z0 = 0.0;
            // FORMULA 2: COMPUTING POWER LOAD (requires: Zl & Z0, Vg and Zg, Lambda)
            double vg_re = 0.0;
            double vg_im = 0.0;
            double zg_re = 0.0;
            double zg_im = 0.0;
            double lambda = 0.0;

            boolean isthere_zl = false;
            boolean isthere_zl_re = false;
            boolean isthere_zl_im = false;

            boolean isthere_z0 = false;

            boolean isthere_vg = false;
            boolean isthere_vg_re = false;
            boolean isthere_vg_im = false;

            boolean isthere_zg = false;
            boolean isthere_zg_re = false;
            boolean isthere_zg_im = false;

            boolean isthere_lambda = false;

            if (etZl_re_inputs.getText().toString().trim().length() != 0){
                zl_re = Double.parseDouble(String.valueOf(etZl_re_inputs.getText()));
                isthere_zl_re = true;
            }
            if (etZl_im_inputs.getText().toString().trim().length() != 0){
                zl_im = Double.parseDouble(String.valueOf(etZl_im_inputs.getText()));
                isthere_zl_im = true;
            }
            if (etZ0_inputs.getText().toString().trim().length() != 0){
                z0 = Double.parseDouble(String.valueOf(etZ0_inputs.getText()));
                isthere_z0 = true;
            }
            if (etVg_re_inputs.getText().toString().trim().length() != 0){
                vg_re = Double.parseDouble(String.valueOf(etVg_re_inputs.getText()));
                isthere_vg_re = true;
            }
            if (etVg_im_inputs.getText().toString().trim().length() != 0){
                vg_im = Double.parseDouble(String.valueOf(etVg_im_inputs.getText()));
                isthere_vg_im = true;
            }
            if (etZg_re_inputs.getText().toString().trim().length() != 0){
                zg_re = Double.parseDouble(String.valueOf(etZg_re_inputs.getText()));
                isthere_zg_re = true;
            }
            if (etZg_im_inputs.getText().toString().trim().length() != 0){
                zg_im = Double.parseDouble(String.valueOf(etZg_im_inputs.getText()));
                isthere_zg_im = true;
            }
            if (etLambda_inputs.getText().toString().trim().length() != 0){
                lambda = Double.parseDouble(String.valueOf(etLambda_inputs.getText()));
                isthere_lambda = true;
            }

            if (isthere_zl_re || isthere_zl_im){
                isthere_zl = true;
            }
            if(isthere_vg_im || isthere_vg_re){
                isthere_vg = true;
            }
            if(isthere_zg_im || isthere_zg_re){
                isthere_zg = true;
            }

            boolean isthere_refCoeff = false;
            if (isthere_zl && isthere_z0){
                isthere_refCoeff = true;
            }

            boolean isthere_power1 = false;
            if (isthere_vg && isthere_zg && isthere_refCoeff){
                isthere_power1 = true;
            }

            boolean isthere_power2 = false;
            if (isthere_vg && isthere_zg && isthere_z0 && isthere_zl && isthere_lambda){
                isthere_power2 = true;
            }

//            if (etZl_re_inputs.getText().toString().trim().length() != 0)
//                zl_re = Double.parseDouble(String.valueOf(etZl_re_inputs.getText()));
//            if (etZl_im_inputs.getText().toString().trim().length() != 0)
//                zl_im = Double.parseDouble(String.valueOf(etZl_im_inputs.getText()));
//            if (etZ0_inputs.getText().toString().trim().length() != 0)
//                z0 = Double.parseDouble(String.valueOf(etZ0_inputs.getText()));
//
//            if (etVg_re_inputs.getText().toString().trim().length() != 0)
//                vg_re = Double.parseDouble(String.valueOf(etVg_re_inputs.getText()));
//            if (etVg_im_inputs.getText().toString().trim().length() != 0)
//                vg_im = Double.parseDouble(String.valueOf(etVg_im_inputs.getText()));
//            if (etZg_re_inputs.getText().toString().trim().length() != 0)
//                zg_re = Double.parseDouble(String.valueOf(etZg_re_inputs.getText()));
//            if (etZg_im_inputs.getText().toString().trim().length() != 0)
//                zg_im = Double.parseDouble(String.valueOf(etZg_im_inputs.getText()));
//            if (etLambda_inputs.getText().toString().trim().length() != 0)
//                lambda = Double.parseDouble(String.valueOf(etLambda_inputs.getText()));

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
            if (isthere_refCoeff){
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
            }else{
                refCoeff_outputs_results.setDisplayText("Missing variables");
                String variablesStatus = "Formula, ";
                variablesStatus += "$\\Gamma = \\frac{\\Zeta_L -\\Zeta_0}{\\Zeta_L +\\Zeta_0}$ <br>";
                variablesStatus += "Variables Given: <br>";
                variablesStatus += "$\\Zeta_L$ = " + isthere_zl + "<br>";
                variablesStatus += "$\\Zeta_0$ = " + isthere_z0 + "";

                mvRefCoeffStepsBySteps_1_1.setDisplayText(variablesStatus);

//                TextView manualStepsTV = findViewById(R.id.tvExpand2);
//                manualStepsTV.setVisibility(View.GONE);
            }

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
            MathView mvPowerStepsBySteps2 = findViewById(R.id.mvPowerStepsBySteps2);

            if(!isthere_power1 || !isthere_power2){
                mvPower_outputs_results.setDisplayText("Missing variables");
            }

            if(isthere_power1){
                // Get (bug)EITHER answer and set result
                String power_answer = Formulas.powerLoad_answer(complex_zl, complex_z0, complex_vg, complex_zg);
                mvPower_outputs_results.setDisplayText(power_answer + "&nbsp;$W$");

                // Set steps
                mvPowerStepsBySteps.setDisplayText(Formulas.powerLoad_steps_1_1(complex_zl, complex_z0, complex_vg, complex_zg));
            }else{
                String variablesStatus = "Formula, ";
                variablesStatus += "$P_L = \\frac{(V_g)^2}{8Z_g}(1-\\vert\\Gamma_L\\vert^2)$ <br>";
                variablesStatus += "Variables Given: <br>";
                variablesStatus += "$\\Zeta_L$ = " + isthere_zl + "<br>";
                variablesStatus += "$\\Zeta_0$ = " + isthere_z0 + "<br>";
                variablesStatus += "$\\Zeta_g$ = " + isthere_zg + "<br>";
                variablesStatus += "$V_g$ = " + isthere_vg;

                mvPowerStepsBySteps.setDisplayText(variablesStatus);
            }

            if(isthere_power2){
                // Set steps
                mvPowerStepsBySteps2.setDisplayText(Formulas.powerLoad_steps_1_2(complex_zl, complex_z0, complex_vg, complex_zg, lambda));
            }else{
                String variablesStatus = "Formula, ";
                variablesStatus += "$P_{L} = \\frac{1}{2} \\vert \\frac{V_g}{Z_g + Z_{in}} \\vert ^2 \\real (Z_{in})$ <br>";
                variablesStatus += "Variables Given: <br>";
                variablesStatus += "$\\Zeta_L$ = " + isthere_zl + "<br>";
                variablesStatus += "$\\Zeta_0$ = " + isthere_z0 + "<br>";
                variablesStatus += "$\\Zeta_g$ = " + isthere_zg + "<br>";
                variablesStatus += "$V_g$ = " + isthere_vg + "<br>";
                variablesStatus += "$\\ell$ = " + isthere_lambda;

                mvPowerStepsBySteps2.setDisplayText(variablesStatus);
            }
        });

        // When test button is pressed, load default inputs
        buttonTest.setOnClickListener(V -> {
            etZ0_inputs.setText(String.valueOf(75)); //z0
            etLambda_inputs.setText(String.valueOf(0.7));
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
//            case R.id.variablesSolver_help:
//                Intent b = new Intent(this, ComplexNumberAdd.class);
//                this.startActivity(b);
//                return true;
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
