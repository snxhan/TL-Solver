package hourglass.github.in.katex.activities;

import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import java.math.BigDecimal;

import hourglass.github.in.katex.ComplexBD;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class VariablesSolver extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_variablessolver);
        //shared prefs
        sharedPreferences = getSharedPreferences("appSettings",Context.MODE_PRIVATE);

        // Toolbar and Title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Solve for TL variables");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialise variables
        Button buttonSolve = findViewById(R.id.buttonSolve);
        MathView refCoeff_outputs_results = findViewById(R.id.refCoeff_outputs_results);
        EditText etZl_re_inputs = findViewById(R.id.etZl_re_inputs);
        EditText etZl_im_inputs = findViewById(R.id.etZl_im_inputs);
        EditText etCharImp_inputs = findViewById(R.id.etCharImp_inputs);
        // When Solve button has been clicked
        buttonSolve.setOnClickListener(V -> {
            // Get values from ET. Set default to zero, if there is input then replace
            BigDecimal zl_re = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal zl_im = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            BigDecimal zo = new BigDecimal(String.valueOf(BigDecimal.ZERO));

            if(etZl_re_inputs.getText().toString().trim().length() != 0)
                zl_re = new BigDecimal(String.valueOf(etZl_re_inputs.getText()));
            if(etZl_im_inputs.getText().toString().trim().length() != 0)
                zl_im = new BigDecimal(String.valueOf(etZl_im_inputs.getText()));
            if(etCharImp_inputs.getText().toString().trim().length() != 0)
                zo = new BigDecimal(String.valueOf(etCharImp_inputs.getText()));


            // COMPUTING REFLECTION COEFFICIENT
            ComplexBD complex_zl = new ComplexBD(zl_re, zl_im);
            ComplexBD complex_zo = new ComplexBD(zo, BigDecimal.ZERO); // put in zero for no imaginary parts

            MathView mvRefCoeffStepsBySteps = findViewById(R.id.mvRefCoeffStepsBySteps);
            String steps_refCoeff = "";

            // Track zl imaginary sign and put in imaginary sign
            String new_zl_im = "";
            if(zl_im.compareTo(BigDecimal.ZERO) < 0)
                new_zl_im = String.valueOf(zl_im) + "i"; // if negative then let it be
            else
                new_zl_im = "+" + zl_im + "i"; // if positive then add positive sign

            steps_refCoeff += "Step 1: Recall $\\Gamma$ (Gamma) formula:<br><span style='color:red;'>$\\Gamma = \\frac{\\Zeta_L -\\Zeta_0}{\\Zeta_L +\\Zeta_0}$</span><br><br>";
            steps_refCoeff += "Step 2: Compute with given variables:<br>";
            steps_refCoeff += "$\\Gamma =\\frac{(" + zl_re + new_zl_im +")-(" + zo + ")}{(" + zl_re + new_zl_im + ")+(" + zo + ")}$<br><br>";
            steps_refCoeff += "Step 3: Group real parts and imaginary parts and simplify: <br> <span style='color:red;'>Compute using calculator or expand to view detailed steps</span><br> ";
            steps_refCoeff += "$\\Gamma =\\frac{(" + zl_re.subtract(zo) +")+(" + new_zl_im + ")}{(" + zl_re.add(zo) + ")+(" + new_zl_im + ")}$<br><br>";
            steps_refCoeff += "Sub-step 3a: Multiply by the conjugate: <br>";
            steps_refCoeff += "$\\Gamma =\\frac{(" + zl_re.subtract(zo) +")+(" + new_zl_im + ")}{(" + zl_re.add(zo) + ")+(" + new_zl_im + ")} \\times \\frac{(" + zl_re.add(zo) + ")-(" + new_zl_im + ")}{(" + zl_re.add(zo) + ")-(" + new_zl_im + ")}$<br><br>";
            steps_refCoeff += "Sub-step 3b: Group real parts and imaginary parts and simplify: <br>";
            // new alpha and beta for complex object and conveniently get conjugate
            ComplexBD refCoeff_Steps_a_top = new ComplexBD((zl_re.subtract(zo)), zl_im);
            ComplexBD refCoeff_Steps_a_bottom = new ComplexBD((zl_re.add(zo)), zl_im);
            steps_refCoeff += "$\\Gamma =\\frac{(" + refCoeff_Steps_a_top.times(refCoeff_Steps_a_bottom.conjugate()).toPString() + ")}{(" + refCoeff_Steps_a_bottom.times(refCoeff_Steps_a_bottom.conjugate()) + ")}$<br><br>";
            steps_refCoeff += "Step 4: Answer:<br>";
            steps_refCoeff += "$" + ((complex_zl.minus(complex_zo)).div(complex_zl.plus(complex_zo))).toPString() + "$<br><br>";


//            mvRefCoeffStepsBySteps.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
            mvRefCoeffStepsBySteps.setDisplayText(steps_refCoeff);

            // set results
            refCoeff_outputs_results.setDisplayText(((complex_zl.minus(complex_zo)).div(complex_zl.plus(complex_zo))).toPString());
        });
        // Expandable and collapsible steps
        CardView cardView = findViewById(R.id.base_cardview);
        ImageView arrow = findViewById(R.id.show);
        Group hiddenGroup = findViewById(R.id.card_group);
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
    public static void saveDP(String new_dp) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("dp", new_dp);
        editor.apply();
    }

    public static String loadDP(){
        return sharedPreferences.getString("dp","");
    }
    // Back button
    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent a = new Intent(this,MainActivity.class);
                this.startActivity(a);
                return true;
            case R.id.variablesSolver_help:
                Intent b = new Intent(this,ComplexNumberAdd.class);
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
