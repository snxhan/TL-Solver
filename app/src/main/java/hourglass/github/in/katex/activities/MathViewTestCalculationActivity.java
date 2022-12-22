package hourglass.github.in.katex.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import hourglass.github.in.katex.ComplexBD;
import hourglass.github.in.katex.Helpers;
import hourglass.github.in.katex.R;
import katex.hourglass.in.mathlib.MathView;

public class MathViewTestCalculationActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout parent_layout;
    //for top calculations (imaginary)
    EditText editValA_re, editValA_im, editValB_re, editValB_im;
    TextView calResults;
    Button buttonSubmit, buttonReset;

    //for dynamic case calculations
    EditText editVarA, editVarB, editVarC, editVarD;
    TextView tvAlpha, tvBeta, tvGamma_BD, tvGamma_CD, tvEta, tvTheta;
    TextView tvAlphaCal, tvEtaCal, tvThetaCal; //detailed calculations
    TextView tvTheta_alpha, tvTheta_eta; // sub expansion of Theta
    Button buttonExpandAlpha, buttonCollapseAlpha, buttonExpandEta, buttonCollapseEta, buttonExpandTheta, buttonCollapseTheta, buttonExpandTheta_Alpha, buttonCollapseTheta_Alpha, buttonExpandTheta_Eta, buttonCollapseTheta_Eta  ; //expand button for detailed calculations
    Button buttonCalculate;

    // catch empty strings when parsing to double. if empty then assume zero
    double validateDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            return Double.parseDouble(strNumber);
        }
        else return 0;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testcalculation);
        setInitialViews();
        addMathView();

        //Step 1: back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editValA_re  = (EditText) findViewById(R.id.valueOfA_real);
        editValA_im  = (EditText) findViewById(R.id.valueOfA_imaginary);
        editValB_re  = (EditText) findViewById(R.id.valueOfB_real);
        editValB_im  = (EditText) findViewById(R.id.valueOfB_imaginary);

        calResults = (TextView) findViewById(R.id.calculationResults);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonReset = (Button) findViewById(R.id.buttonReset);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get string value of editText and convert to big decimal
//                BigDecimal valA_re = new BigDecimal(String.valueOf(editValA_re.getText()));
//                BigDecimal valA_im = new BigDecimal(String.valueOf(editValA_im.getText()));
//                BigDecimal valB_re = new BigDecimal(String.valueOf(editValB_re.getText()));
//                BigDecimal valB_im = new BigDecimal(String.valueOf(editValB_im.getText()));

                BigDecimal valA_re = new BigDecimal(String.valueOf(BigDecimal.ZERO));
                BigDecimal valA_im = new BigDecimal(String.valueOf(BigDecimal.ZERO));
                BigDecimal valB_re = new BigDecimal(String.valueOf(BigDecimal.ZERO));
                BigDecimal valB_im = new BigDecimal(String.valueOf(BigDecimal.ZERO));

                if(editValA_re.getText().toString().trim().length() != 0){
                    valA_re = new BigDecimal(String.valueOf(editValA_re.getText()));
                }
                if(editValA_im.getText().toString().trim().length() != 0){
                    valA_im = new BigDecimal(String.valueOf(editValA_im.getText()));
                }
                if(editValB_re.getText().toString().trim().length() != 0){
                    valB_re = new BigDecimal(String.valueOf(editValB_re.getText()));
                }
                if(editValB_im.getText().toString().trim().length() != 0){
                    valB_im = new BigDecimal(String.valueOf(editValB_im.getText()));
                }
//                String valA_re = String.valueOf(editValA_re.getText());
//                String valA_im = String.valueOf(editValA_im.getText());
//                String valB_re = String.valueOf(editValB_re.getText());
//                String valB_im = String.valueOf(editValB_im.getText());
                //validateDouble check if string is empty, if empty then assume zero
//                double valA_re = validateDouble(editValA_re.getText().toString());
//                double valA_im = validateDouble(editValA_im.getText().toString());
//                double valB_re = validateDouble(editValB_re.getText().toString());
//                double valB_im = validateDouble(editValB_im.getText().toString());

                //if there is no input of imaginary numbers, set to zero

//                Complex a = new Complex(valA_re, valA_im);
//                Complex b = new Complex(valB_re, valB_im);

                ComplexBD a = new ComplexBD(valA_re, valA_im);
                ComplexBD b = new ComplexBD(valB_re, valB_im);

                //https://introcs.cs.princeton.edu/java/97data/Complex.java.html
//                calResults.setText("Value A:\t" + valA_re + "+ j" + valA_im + "\nValue B:\t" + valB_re + "+ j" + valB_im);

//                String additionPossibility = "";
//                if (valA_re.isEmpty())
//                    additionPossibility += "Value of Real A not available";
//
//                if (valA_im.isEmpty())
//                    additionPossibility += "Value of Imaginary A not available";
//
//                if (valB_re.isEmpty())
//                    additionPossibility += "Value of Real B not available";
//
//                if (valB_im.isEmpty())
//                    additionPossibility += "Value of Imaginary B not available";
//
//                BigDecimal additionResults = null;
//                if (valA_re.isEmpty() || valA_im.isEmpty() || valB_re.isEmpty() || valB_im.isEmpty()) {
//                if (valA_re.isEmpty() || valA_im.isEmpty() || valB_re.isEmpty() || valB_im.isEmpty()) {
//                    calResults.setText(additionPossibility);
//                }else
//                    additionResults = addInRectangularForm(valA_re, valA_im, valB_re, valB_im);

                //Documentation of : Java.toPlainString()
                //returns a string representation of this BigDecimal without an exponent field. Returns 1230000 instead of 1.23E+7

//                calResults.setText(a.times_showSteps(b));
//                calResults.setText(a.times_showSteps(b));


                String steps = "";

                //step 1 a = a.re, b = a.im, c = b.re, d = b.im
//                steps += "Step 1: Apply complex arithmetic rule: (a+bi)(c+di) = (ac-bd)+(ad+bc)i \n";
//                steps += "(" + a.re + " * " + b.re + " - " + a.im + " * " + b.im + ") + (" + a.re + " * " + b.im + " + " + a.im + " * " + b.re + ")i \n";
//                steps += "Step 2: Expand \n";
//                steps += "(" + a.re.multiply(b.re) + " - " + a.im.multiply(b.im) + ") + (" + a.re.multiply(b.im) + " + " + a.im.multiply(b.re) + ")i\n" ;
//                steps += "Step 3: Simplify \n";
//                steps += "(" + (a.re.multiply(b.re)).subtract(a.im.multiply(b.im)) + ") + (" + (a.re.multiply(b.im)).add(a.im.multiply(b.re)) + ")i" ;

                steps += "<p>Step 1: Apply complex arithmetic rule: $(a+bi)(c+di) = (ac-bd)+(ad+bc)i$ </p>";
                steps += "(" + a.re + " * " + b.re + " - " + a.im + " * " + b.im + ") + (" + a.re + " * " + b.im + " + " + a.im + " * " + b.re + ")i \n";
                steps += "Step 2: Expand \n";
                steps += "(" + a.re.multiply(b.re) + " - " + a.im.multiply(b.im) + ") + (" + a.re.multiply(b.im) + " + " + a.im.multiply(b.re) + ")i\n" ;
                steps += "Step 3: Simplify \n";
                steps += "(" + (a.re.multiply(b.re)).subtract(a.im.multiply(b.im)) + ") + (" + (a.re.multiply(b.im)).add(a.im.multiply(b.re)) + ")i" ;

                MathView mathView = new MathView(getApplicationContext());
                mathView.setClickable(true);
                mathView.setTextSize(30);
                mathView.setDisplayText("$\\alpha + \\beta =$ " + a.times(b));
                mathView.setDisplayText(steps);
                mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.Color5));
                parent_layout.addView(mathView);
            }
        });

        editVarA  = (EditText) findViewById(R.id.editTextVarA);
        editVarB = (EditText) findViewById(R.id.editTextVarB);
        editVarC = (EditText) findViewById(R.id.editTextVarC);
        editVarD = (EditText) findViewById(R.id.editTextVarD);
        tvAlpha = (TextView) findViewById(R.id.textViewOPalpha);
        tvBeta = (TextView) findViewById(R.id.textViewOPbeta);
        tvGamma_BD = (TextView) findViewById(R.id.textViewOPgamma_BD);
        tvGamma_CD = (TextView) findViewById(R.id.textViewOPgamma_CD);
        tvEta = (TextView) findViewById(R.id.textViewOPeta);
        tvTheta = (TextView) findViewById(R.id.textViewOPtheta);

        //sub expansion
        tvTheta_alpha = (TextView) findViewById(R.id.textViewTheta_alphaDetailedCalculations);
        tvTheta_eta = (TextView) findViewById(R.id.textViewTheta_etaDetailedCalculations);

        //calculate button
        buttonCalculate = (Button) findViewById(R.id.buttonCal);

        //detailed calculations
        tvAlphaCal = (TextView) findViewById(R.id.textViewAlphaDetailedCalculations);
        tvEtaCal = (TextView) findViewById(R.id.textViewEtaDetailedCalculations);
        tvThetaCal = (TextView) findViewById(R.id.textViewThetaDetailedCalculations);

        // expand and collapse button
        buttonExpandAlpha = (Button) findViewById(R.id.alphaExpandButton);
        buttonCollapseAlpha = (Button) findViewById(R.id.alphaCollapseButton);
        buttonExpandEta = (Button) findViewById(R.id.etaExpandButton);
        buttonCollapseEta = (Button) findViewById(R.id.etaCollapseButton);
        buttonExpandTheta = (Button) findViewById(R.id.thetaExpandButton);
        buttonCollapseTheta = (Button) findViewById(R.id.thetaCollapseButton);
        //sub expansion for theta
        buttonExpandTheta_Alpha = (Button) findViewById(R.id.theta_alphaExpandButton);
        buttonCollapseTheta_Alpha = (Button) findViewById(R.id.theta_alphaCollapseButton);
        buttonExpandTheta_Eta = (Button) findViewById(R.id.theta_etaExpandButton);
        buttonCollapseTheta_Eta = (Button) findViewById(R.id.theta_etaCollapseButton);

        //hide expand button and detailed calculations until triggered
        buttonExpandAlpha.setVisibility(View.GONE);
        buttonCollapseAlpha.setVisibility(View.GONE);
        tvAlphaCal.setVisibility(View.GONE);
        buttonExpandEta.setVisibility(View.GONE);
        buttonCollapseEta.setVisibility(View.GONE);
        tvEtaCal.setVisibility(View.GONE);
        buttonExpandTheta.setVisibility(View.GONE);
        buttonCollapseTheta.setVisibility(View.GONE);
        tvThetaCal.setVisibility(View.GONE);
        tvTheta_alpha.setVisibility(View.GONE);
        tvTheta_eta.setVisibility(View.GONE);
        buttonExpandTheta_Alpha.setVisibility(View.GONE);
        buttonCollapseTheta_Alpha.setVisibility(View.GONE);
        buttonExpandTheta_Eta.setVisibility(View.GONE);
        buttonCollapseTheta_Eta.setVisibility(View.GONE);

        //When calculate button is pressed
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get all possible values
                String valA = editVarA.getText().toString();
                String valB = editVarB.getText().toString();
                String valC = editVarC.getText().toString();
                String valD = editVarD.getText().toString();

                //LOOP THROUGH ALL COMPUTE FUNCTION if value is not null AND make visible expand button
                tvAlpha.setText(computeAlpha(valA, valB));
                // only make expand button visible if computeAlpha result is available
                if (computeAlpha(valA, valB) != "Both Var A and Var B are not avail"){
                    buttonExpandAlpha.setVisibility(View.VISIBLE);
                }else // hide it when re-calculating!
                    buttonExpandAlpha.setVisibility(View.GONE);
                tvAlphaCal.setText(computeAlphaInDetailedSteps(valA, valB));

                tvBeta.setText(computeBeta(valB, valC));

                // multiple solutions for GAMMA bd
                if(computeGamma_BD(valB, valD) != "Both Var B and Var D are not available")
                    tvGamma_BD.setText(computeGamma_BD(valB, valD));
                else
                    tvGamma_BD.setText("Gamma 1 calculation not available. Both Var B and Var D are not available");
                // multiple solutions for GAMMA cd
                if(computeGamma_CD(valC, valD) != "Both Var C and Var D are not available")
                    tvGamma_CD.setText(computeGamma_CD(valC, valD));
                else
                    tvGamma_CD.setText("Gamma 2 calculation not available.Both Var C and Var D are not available");

                tvEta.setText(computeEta(valA, valD));
                if (computeEta(valA, valD) != "Both Var A and Var D are not available"){
                    buttonExpandEta.setVisibility(View.VISIBLE);
                }else{
                    buttonExpandEta.setVisibility(View.GONE);
                }
                tvEtaCal.setText(computeEtaInDetailedSteps(valA, valD));

                tvTheta.setText(computeTheta(valA, valB, valD));
                if (computeTheta(valA, valB, valD) != "Both Alpha and Eta are not avail"){
                    buttonExpandTheta.setVisibility(View.VISIBLE);
                    if (computeTheta(valA, valB, valD) != "Alpha not available"){
                        buttonExpandTheta_Alpha.setVisibility(View.VISIBLE);
                    }else
                        buttonExpandTheta_Alpha.setVisibility(View.GONE);
                    tvTheta_eta.setText(computeEtaInDetailedSteps(valA, valD));
                    if (computeTheta(valA, valB, valD) != "Eta not available"){
                        buttonExpandTheta_Eta.setVisibility(View.VISIBLE);
                    }else
                        buttonExpandTheta_Eta.setVisibility(View.GONE);
                }else{
                    buttonExpandTheta.setVisibility(View.GONE);
                    buttonExpandTheta_Alpha.setVisibility(View.GONE);
                    buttonExpandTheta_Eta.setVisibility(View.GONE);
                }
                tvThetaCal.setText(computeThetaInDetailedSteps(valA, valB, valD));
                tvTheta_alpha.setText(computeAlphaInDetailedSteps(valA, valB));

            }
        });

        //When Alpha expand button is clicked
        buttonExpandAlpha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvAlphaCal.setVisibility(View.VISIBLE);
                buttonExpandAlpha.setVisibility(View.GONE);
                buttonCollapseAlpha.setVisibility(View.VISIBLE);
            }
        });
        //When Alpha collapse button is clicked
        buttonCollapseAlpha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvAlphaCal.setVisibility(View.GONE);
                buttonExpandAlpha.setVisibility(View.VISIBLE);
                buttonCollapseAlpha.setVisibility(View.GONE);
            }
        });

        //When eta expand button is clicked
        buttonExpandEta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvEtaCal.setVisibility(View.VISIBLE);
                buttonExpandEta.setVisibility(View.GONE);
                buttonCollapseEta.setVisibility(View.VISIBLE);
            }
        });
        //When eta collapse button is clicked
        buttonCollapseEta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvEtaCal.setVisibility(View.GONE);
                buttonExpandEta.setVisibility(View.VISIBLE);
                buttonCollapseEta.setVisibility(View.GONE);
            }
        });

        //When Theta expand button is clicked
        buttonExpandTheta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv theta detailed calculations
                tvThetaCal.setVisibility(View.VISIBLE);
                buttonExpandTheta.setVisibility(View.GONE);
                buttonCollapseTheta.setVisibility(View.VISIBLE);

                //make visible alpha and eta expand button
                buttonExpandTheta_Alpha.setVisibility(View.VISIBLE);
                buttonExpandTheta_Eta.setVisibility(View.VISIBLE);
            }
        });
        //When Theta collapse button is clicked
        buttonCollapseTheta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvThetaCal.setVisibility(View.GONE);
                buttonExpandTheta.setVisibility(View.VISIBLE);
                buttonCollapseTheta.setVisibility(View.GONE);

                //make gone alpha and eta expand button
                buttonExpandTheta_Alpha.setVisibility(View.GONE);
                buttonExpandTheta_Eta.setVisibility(View.GONE);
                buttonCollapseTheta_Alpha.setVisibility(View.GONE);
                buttonCollapseTheta_Eta.setVisibility(View.GONE);
                tvTheta_alpha.setVisibility(View.GONE);
                tvTheta_eta.setVisibility(View.GONE);
            }
        });

        //When Theta-Alpha expand button is clicked
        buttonExpandTheta_Alpha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv theta detailed calculations
                tvTheta_alpha.setVisibility(View.VISIBLE);
                buttonExpandTheta_Alpha.setVisibility(View.GONE);
                buttonCollapseTheta_Alpha.setVisibility(View.VISIBLE);
            }
        });
        //When Theta-Alpha collapse button is clicked
        buttonCollapseTheta_Alpha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvTheta_alpha.setVisibility(View.GONE);
                buttonExpandTheta_Alpha.setVisibility(View.VISIBLE);
                buttonCollapseTheta_Alpha.setVisibility(View.GONE);
            }
        });

        //When Theta-Eta expand button is clicked
        buttonExpandTheta_Eta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv theta detailed calculations
                tvTheta_eta.setVisibility(View.VISIBLE);
                buttonExpandTheta_Eta.setVisibility(View.GONE);
                buttonCollapseTheta_Eta.setVisibility(View.VISIBLE);
            }
        });
        //When Theta-Eta collapse button is clicked
        buttonCollapseTheta_Eta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //make visible tv alpha detailed calculations
                tvTheta_eta.setVisibility(View.GONE);
                buttonExpandTheta_Eta.setVisibility(View.VISIBLE);
                buttonCollapseTheta_Eta.setVisibility(View.GONE);
            }
        });
    }

    private BigDecimal addInRectangularForm(String a_re, String a_im, String b_re, String b_im){
//        BigDecimal results = new BigDecimal(0);
//        BigDecimal a_real = new BigDecimal(String.valueOf(a_re));
//        BigDecimal a_imaginary = new BigDecimal(String.valueOf(a_im));
//        BigDecimal b_real = new BigDecimal(String.valueOf(b_re));
//        BigDecimal b_imaginary = new BigDecimal(String.valueOf(b_im));
//
//        String additionPossibility = "";

//        if (valA_re.isEmpty())
//            additionPossibility += "Value of Real A not available";
//
//        if (valA_im.isEmpty())
//            additionPossibility += "Value of Imaginary A not available";
//
//        if (valB_re.isEmpty())
//            additionPossibility += "Value of Real B not available";
//
//        if (valB_im.isEmpty())
//            additionPossibility += "Value of Imaginary B not available";
//
//        BigDecimal additionResults = null;
//                if (valA_re.isEmpty() || valA_im.isEmpty() || valB_re.isEmpty() || valB_im.isEmpty()) {
//        if (valA_re.isEmpty() || valA_im.isEmpty() || valB_re.isEmpty() || valB_im.isEmpty()) {
//            calResults.setText(additionPossibility);
//        }else
//            additionResults = addInRectangularForm(valA_re, valA_im, valB_re, valB_im);
//
//        if(a_im == ""  && b_im == ""){
//            return a_real.add(b_real).stripTrailingZeros();
//        }
//        return results;
        return null;
    }

    private String computeAlpha(String A, String B){
        String results ="";

        if(A.isEmpty() || B.isEmpty()){
            if(A.isEmpty() && !B.isEmpty())
                results = "Var A not available";
            else if(!A.isEmpty() && B.isEmpty())
                results = "Var B not available";
            else
                results = "Both Var A and Var B are not avail";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(A) * Integer.valueOf(B));
        }
        return results;
    }

    private String computeAlphaInDetailedSteps(String A, String B){
        String alphaFormula = "alpha = A * B";
        return alphaFormula + "\n" + A + " * " + B + " = " + computeAlpha(A, B);
    }

    private String computeEtaInDetailedSteps(String A, String D){
        String etaFormula = "eta = A * D";
        return etaFormula + "\n" +A + " * " + D + " = " + computeEta(A, D);
    }

    private String computeThetaInDetailedSteps(String A, String B, String D){
        String thetaFormula = "theta = alpha * eta";
        return thetaFormula + "\n" + computeAlpha(A, B) + " * " + computeEta(A, D) + " = " + computeTheta(A, B, D);
    }

    private String computeBeta(String A, String B){
        String results ="";

        if(A.isEmpty() || B.isEmpty()){
            if(A.isEmpty() && !B.isEmpty())
                results = "Var B not available";
            else if(!A.isEmpty() && B.isEmpty())
                results = "Var C not available";
            else
                results = "Both Var B and Var C are not avail";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(A) * Integer.valueOf(B));
        }
        return results;
    }

    private String computeGamma_BD(String B, String D){
        String results ="";

        if(B.isEmpty() || D.isEmpty()){
            if(B.isEmpty() && !D.isEmpty())
                results = "Var B not available";
            else if(!B.isEmpty() && D.isEmpty())
                results = "Var D not available";
            else
                results = "Both Var B and Var D are not available";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(B) * Integer.valueOf(D));
        }
        return results;
    }
    private String computeGamma_CD(String C, String D){
        String results ="";

        if(C.isEmpty() || D.isEmpty()){
            if(C.isEmpty() && !D.isEmpty())
                results = "Var C not available";
            else if(!C.isEmpty() && D.isEmpty())
                results = "Var D not available";
            else
                results = "Both Var C and Var D are not available";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(C) * Integer.valueOf(D));
        }
        return results;
    }

    private String computeEta(String A, String D){
        String results ="";

        if(A.isEmpty() || D.isEmpty()){
            if(A.isEmpty() && !D.isEmpty())
                results = "Var A not available";
            else if(!A.isEmpty() && D.isEmpty())
                results = "Var D not available";
            else
                results = "Both Var A and Var D are not available";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(A) * Integer.valueOf(D));
        }
        return results;
    }
    private String computeTheta(String A, String B, String D){
        String results ="";

        String alpha = computeAlpha(A, B);
        String eta = computeEta(A, D);

        if(alpha.length() > 10 || eta.length() > 10 ){
            if(alpha.length() > 10 && eta.length() < 10)
                results = "Alpha not available";
            else if(alpha.length() < 10 && eta.length() > 10)
                results = "Eta not available";
            else
                results = "Both Alpha and Eta are not avail";
        }else{
            //Formula for compute Alpha
            results = String.valueOf(Integer.valueOf(alpha) * Integer.valueOf(eta));
        }
        return results;
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        addMathView();
//    }

    private void addMathView() {
        MathView mathView = new MathView(getApplicationContext());
        mathView.setClickable(false);
        mathView.setTextSize(30);
        mathView.setTextColor(ContextCompat.getColor(getApplicationContext(),android.R.color.white));
        //mathView.setDisplayText(DataHelpers.getSimpleMath());
        //mathView.setDisplayText(getResources().getString(R.string.runtime_formula));
        mathView.setDisplayText("$\\int_0^\\infty e^{-x^2} dx=\\frac{\\sqrt{\\pi}}{2}$");
        //mathView.setViewBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.Color5));
        mathView.setViewBackgroundColor(Helpers.getRandomColor(getApplicationContext(),3));
//        parent_layout.addView(mathView);
        parent_layout.refreshDrawableState();
    }

    private void setInitialViews() {
        parent_layout = (LinearLayout) findViewById(R.id.linear_parent_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Test calculation");
    }

    @Override
    public void onClick(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Calculations expanded.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    //Step 2: back button!
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}