package hourglass.github.in.katex;

import java.text.DecimalFormat;

import hourglass.github.in.katex.activities.MainActivity;

/**
 * @since 03 January 2023
 * @author Soh Xin Han
 */
public class Formulas {

    // To remove $ sign in String
    public static String removeDollarSign(String input){
        return input.replace("$", "");
    }

    /**
     * Description:
     *  - Each Formula will provide both ANSWER and STEPS. (Steps might be broken down)
     *  - Search format: xy(a/s) for:
     *    x = (b = basic operations / t = Transmission line)
     *    y = (refer to below "Table of Formulas" for number)
     *    a/s = (a = answer) / (s = steps)
     *
     * Table of Formulas:
     *  b. Basic operations
     *    1. Add Complex
     *    2. Subtract Complex
     *    3. Multiply Complex
     *    4. Divide Complex
     *    5. Convert Complex to Polar
     *  t. Transmission line
     *    1. Reflection Coefficient
     *    2. Power Load
     *
     * @return always return as String type - so that it can be displayed as MathView
     */

    /**
     * FORMULA b1a : Add Complex
     */
    public static String addComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.plus(b)).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA b1s : Add Complex
     */
    public static String addComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step a: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)+(c+di)$</span><br> $= (a+c)+(b+d)i$<br>" +
                "$=(" + a.re() + " + " + b.re() + ") + (" + a.im() + " + " + b.im() + ")i$<br><br>";
        steps += "Step b: Expand:<br>" +
                "$(" + (a.re() + b.re()) + ") + (" + (a.im() + b.im()) + ")i$<br><br>" ;
        steps += "Step c: Answer:<br>" +
                "$" + (a.plus(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b2a : Subtract Complex
     */
    public static String subtractComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.minus(b)).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA b2s : Subtract Complex
     */
    public static String subtractComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step a: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)-(c+di)$</span><br> $= (a-c)+(b-d)i$<br>" +
                "$=(" + a.re() + " - " + b.re() + ") + (" + a.im() + " - " + b.im() + ")i$<br><br>";
        steps += "Step b: Expand:<br>" +
                "$(" + (a.re() - b.re()) + ") + (" + (a.im() - b.im()) + ")i$<br><br>" ;
        steps += "Step c: Answer:<br>" +
                "$" + (a.minus(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b3a : Multiply Complex
     */
    public static String multiplyComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.times(b)).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA b3s : Multiply Complex
     */
    public static String multiplyComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step a: Apply complex arithmetic rule:<br><span style='color:red;'>$(a+bi)(c+di)$</span><br> $= (ac-bd)+(ad+bc)i$<br>" +
                "$=(" + a.re() + " * " + b.re() + " - " + a.im() + " * " + b.im() + ") + (" + a.re() + " * " + b.im() + " + " + a.im() + " * " + b.re() + ")i$<br><br>";
        steps += "Step b: Expand:<br>" +
                "$(" + a.re()*(b.re()) + " - " + a.im()*(b.im()) + ") + (" + a.re()*(b.im()) + " + " + a.im()*(b.re()) + ")i$<br><br>" ;
        steps += "Step c: Simplify:<br>" +
                "$(" + ((a.re()*(b.re()))-(a.im()*(b.im()))) + ") + (" + ((a.re()*(b.im()))+(a.im()*(b.re()))) + ")i$<br><br>" ;
        steps += "Step d: Answer:<br>" +
                "$" + (a.times(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b4a : Divide Complex
     */
    public static String divideComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.divides(b)).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA b4s : Divide Complex
     */
    public static String divideComplex_steps(Complex a, Complex b){
        // Track a.im and b.im imaginary sign and put in imaginary sign if positive
        String new_a_im = "";
        if(a.im() < 0)
            new_a_im = String.valueOf(a.im()) + "i"; // if negative then let it be
        else
            new_a_im = "+" + a.im() + "i"; // if positive then add positive sign
        String new_b_im = "";
        if(b.im() < 0)
            new_b_im = String.valueOf(b.im()) + "i"; // if negative then let it be
        else
            new_b_im = "+" + b.im() + "i"; // if positive then add positive sign
        String steps = "";
        steps += "Step a: Multiply with conjugate: <br>$\\frac{"+ a.re() + new_a_im +"}{"+ b.re() + new_b_im +"}$<span>$\\times\\frac{"+ b.conjugate() +"}{"+ b.conjugate() +"}$</span><br><br>";
        steps += "Step b: Multiply Complex Numbers: <br>" +
                "$\\frac{(" + a.re() + new_a_im + ")\\times(" + b.conjugate() + ")}{(" + b.re() + new_b_im + ")\\times(" + b.conjugate() + ")}$<br><br>";
//        steps += (multiplyComplex_steps(a, b.conjugate())) + "<br><br>";
        steps += "Step c: Multiplication results: <br>" +
                "$=\\frac{(" + removeDollarSign(multiplyComplex_answer(a, b.conjugate())) + ")}{(" + removeDollarSign(multiplyComplex_answer(b, b.conjugate())) + ")}$<br><br>";
        steps += "Step d: Rewrite in $(a+bi)$ form:<br>" +
                "$=\\frac{" + (a.times(b.conjugate())).re() + "}{" + b.times(b.conjugate()) + "} + \\frac{" + (a.times(b.conjugate())).im() + "}{" + b.times(b.conjugate()) + "}i$<br><br>" ;
        steps += "Step e: Answer:<br>" +
                "$=" + a.divides(b).sprintf().toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b5a
     */
    public static String convertComplexToPolar_answer(Complex a){
        String answer = "";
        answer = "$" + a.abs() + "\\angle" + a.phase() + "\\text{ rad}$<br>" +
                "$" + a.abs() + "\\angle" + a.phaseDeg() + "\\degree$";
        return answer;
    }
    /**
     * FORMULA b5s
     */
    public static String convertComplexToPolar_steps(Complex a){
        String steps = "";
        steps += "Step a: To convert Complex Number $z$ to Polar Form $r \\angle\\theta$ recall that:<br>" +
                "<span style='color:red;'>$(z = x+iy) = (r\\angle\\theta) $</span><br><br>" +
                "To find $r$; which is the Modulus of $z$,<br><span style='color:red;'>$r$</span> $= \\vert z\\vert = $<span style='color:red;'>$&nbsp;\\sqrt{x^2+y^2}$</span><br>" +
                "To find $\\theta;$ which is the Argument of $z$, <br><span style='color:red;'>$\\theta$</span> $= \\arg(z) =$<span style='color:red;'>$&nbsp;\\tan^{-1} \\frac{y}{x}$</span><br><br>";
        steps += "Step b: Solve for $r$ and $\\theta$ :<br>" +
                "$r = \\sqrt{" + a.sprintf().re() + "^2 + " + a.sprintf().im() + "^2} =$ <span style='color:green;'>$" + a.sprintf().abs() + "$</span> <br>" +
                "$\\theta = \\tan^{-1} \\frac{" + a.sprintf().im() + "}{"  + a.sprintf().re() + "} = $ <span style='color:green;'>$" + a.sprintf().phase() + "$</span>$\\text{ rad}$<br>" +
                "$\\theta = \\tan^{-1} \\frac{" + a.sprintf().im() + "}{"  + a.sprintf().re() + "} = $ <span style='color:green;'>$" + a.sprintf().phaseDeg() + "$</span>$\\degree$<br><br>";
        steps += "Step c: Answer:<br>" +
                "$" + a.abs() + "&nbsp;\\angle&nbsp;" + a.phase() + "\\text{ rad}$<br>";
        return steps;
    }

    /**
     * FORMULA t1a : Reflection Coefficient
     */
    public static String reflectionCoefficient_answer(Complex zl, Complex z0){
        String answer = "";
        answer = "$" + zl.minus(z0).divides(zl.plus(z0)).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA t1s_1 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_1(Complex zl, Complex z0){
        String steps = "";

        // Format whole number to remove .0
        DecimalFormat df = new DecimalFormat();

        // Track zl imaginary sign and put in imaginary sign
        String new_zl_im = "";
        if (zl.im() < 0)
            new_zl_im = zl.im() + "i"; // if negative then let it be
        else
            new_zl_im = "+" + zl.im() + "i"; // if positive then add positive sign

        steps += "Step 1: Recall $\\Gamma$ (Gamma) formula:<br><span style='color:blue;'>$\\Gamma = \\frac{\\Zeta_L -\\Zeta_0}{\\Zeta_L +\\Zeta_0}$</span><br><br>";
        steps += "Step 2: Substitute with given inputs:<br>";
        steps += "$\\Gamma =\\frac{(" + (zl.re()) + (new_zl_im) + ")-(" + (z0.re()) + ")}{(" + zl.re() + new_zl_im + ")+(" + (z0.re()) + ")}$<br><br>";
        steps += "Step 3: Group real parts and imaginary parts and simplify: <br> <span0><i>*Compute using calculator</i></span><br> ";
        steps += "$\\Gamma =\\frac{(" + (zl.re() - z0.re()) + ")+(" + (new_zl_im) + ")}{(" + (zl.re() + z0.re()) + ")+(" + new_zl_im + ")}$<br>";

        return steps;
    }
    /**
     * FORMULA t1s_2 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_2(Complex zl, Complex z0){
        String steps = "";

        // Track zl imaginary sign and put in imaginary sign
        String new_zl_im = "";
        if (zl.im() < 0)
            new_zl_im = zl.im() + "i"; // if negative then let it be
        else
            new_zl_im = "+" + zl.im() + "i"; // if positive then add positive sign

//        steps += "Sub-step 3a: Multiply by the conjugate: <br>";
//        steps += "$\\Gamma =\\frac{(" + (zl.re() - z0.re()) + ")+(" + new_zl_im + ")}{(" + (zl.re() + z0.re()) + ")+(" + new_zl_im + ")} \\times \\frac{(" + (zl.re() + z0.re()) + ")-(" + new_zl_im + ")}{(" + (zl.re() + z0.re()) + ")-(" + new_zl_im + ")}$<br><br>";
//        steps += "Sub-step 3b: Group real parts and imaginary parts and simplify: <br>";
//        // new alpha and beta for complex object and conveniently get conjugate
//        Complex refCoeff_Steps_a_top = new Complex((zl.re() - z0.re()), zl.im());
//        Complex refCoeff_Steps_a_bottom = new Complex((zl.re() + z0.re()), zl.im());
//        steps += "$\\Gamma =\\frac{(" + refCoeff_Steps_a_top.times(refCoeff_Steps_a_bottom.conjugate()) + ")}{(" + refCoeff_Steps_a_bottom.times(refCoeff_Steps_a_bottom.conjugate()) + ")}$<br>";
        Complex top = new Complex ((zl.re() - z0.re()), zl.im());
        Complex bottom = new Complex ((zl.re() + z0.re()), zl.im());
        steps = divideComplex_steps(top, bottom);
        return steps;
    }
    /**
     * FORMULA t1s_3 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_1_1(Complex zl, Complex z0){
        String steps = "";
        steps += "Step 4: Answer:<br>";
        steps += reflectionCoefficient_answer(zl, z0) + "<br>";
        return steps;
    }
    /**
     * FORMULA t1s_3 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_3(Complex zl, Complex z0){
        String steps = "";
        steps += "Answer in Polar form:<br>";
        steps += Formulas.convertComplexToPolar_answer((zl.minus(z0)).divides(zl.plus(z0)));
        steps += "<br><br>";
        steps += Formulas.convertComplexToPolar_steps((zl.minus(z0)).divides(zl.plus(z0)));
        return steps;
    }

    /**
     * FORMULA t2a : Power Load
     */
    public static String powerLoad_answer(Complex zl, Complex z0, Complex vg, Complex zg){
        String answer = "";
        Complex static_eight = new Complex (8, 0);
        Complex static_one = new Complex (1, 0);
        Complex abs_refCoeff_squared = new Complex(((zl.minus(z0)).divides(zl.plus(z0)).abs()) * ((zl.minus(z0)).divides(zl.plus(z0)).abs()),0);
        answer = "$" + (((vg.times(vg)).divides(static_eight.times(zg))).times(static_one.minus(abs_refCoeff_squared))).sprintf().toString() + "$";
        return answer;
    }
    /**
     * FORMULA t2s_1 : Power Load
     */
    public static String powerLoad_steps_1_1(Complex zl, Complex z0, Complex vg, Complex zg){
        String steps = "";

        Complex static_eight = new Complex (8, 0);
        Complex static_one = new Complex (1, 0);
        Complex abs_refCoeff_squared = new Complex(((zl.minus(z0)).divides(zl.plus(z0)).abs()) * ((zl.minus(z0)).divides(zl.plus(z0)).abs()),0);
        String reflectionCoefficient_answer = Formulas.reflectionCoefficient_answer(zl, z0).replace("$", "");

        steps += "Step 1: Recall $P_L$ (Power Load) formula:<br><span style='color:red;'>$P_L = \\frac{(V_g)^2}{8Z_g}(1-\\vert\\Gamma_L\\vert^2)$</span><br><br>";
        steps += "Step 2: Compute $\\Gamma_L$ (Reflection Coefficient) <br>Refer to Ref Coeff Steps for detailed steps. <br>";
        steps += "$\\Gamma_L = " + ((zl.minus(z0)).divides(zl.plus(z0))).sprintf() + "$<br><br>";
        steps += "Step 3: Substitute inputs into $P_L$ formula:<br>";
        steps += "$P_L = \\frac{(" + vg.re() + " + " + vg.im() + "i)^2}{8(" + zg.re() + " + " + zg.im() + "i)}(1-\\vert " + reflectionCoefficient_answer + "\\vert^2)$ <br><br>";
        steps += "Step 4: Calculate value of $\\vert\\Gamma_L\\vert$:<br>";
        steps += "$P_L = \\frac{(" + vg.re() + " + " + vg.im() + "i)^2}{8(" + zg.re() + " + " + zg.im() + "i)}(1-( " + (zl.minus(z0)).divides(zl.plus(z0)).abs() + ")^2)$<br><br>";
        steps += "Step 5: Calculate value of $\\vert\\Gamma_L\\vert^2$:<br>";
        steps += "$P_L = \\frac{(" + vg.re() + " + " + vg.im() + "i)^2}{8(" + zg.re() + " + " + zg.im() + "i)}(1-( " + (((zl.minus(z0)).divides(zl.plus(z0)).abs()) * ((zl.minus(z0)).divides(zl.plus(z0)).abs()))+ "))$<br><br>";
        steps += "Step 6: Expand:<br>";
        steps += "$P_L = \\frac{" + vg.times(vg) + "}{" + static_eight.times(zg) + "}(" + static_one.minus(abs_refCoeff_squared)+ ")$<br><br>";
        steps += "Step 7: Answer:<br>";
        steps += "$P_L = " + (vg.times(vg).divides(static_eight.times(zg))).times(static_one.minus(abs_refCoeff_squared)) +"\\text{ W}$<br><br><br>";


        return steps;
    }
    /**
     * FORMULA t2s_2 : Power Load
     */
    public static String powerLoad_steps_1_2(Complex zl, Complex z0, Complex vg, Complex zg, double lambda){
        String steps = "";

        //alternative calculation
        steps += "<b>Alternative Calculation:</b><br>";
        steps += "Step 1: Recall $P_L$ (Power Load) formula:<br>" +
                "<span style='color:red;'>$P_{L} = \\frac{1}{2} \\vert \\frac{V_g}{Z_g + Z_{in}} \\vert ^2 \\real (Z_{in})$</span><br><br>";
        steps += "Step 2: To use Power Load formula, we need to compute $Z_{in}$ <br>" +
                " <span style='color:blue;'>$\\Zeta_{in} = \\Zeta_0\\frac{\\Zeta_L + j\\Zeta_0\\tan (\\beta \\ell)}{\\Zeta_0 + j\\Zeta_L\\tan (\\beta \\ell)}$</span> <br><br>";
        steps += "Step 3: Recall $\\beta$ formula<br> " +
                "<span style='color:blue;'>$\\beta = \\frac{2\\pi}{\\lambda}$</span> <br><br>";
        steps += "Step 4: Substitute inputs into formula <br>" +
                "$\\Zeta_{in} = "  +  z0 + "\\frac{(" + zl + ")+ j&nbsp;(" + z0 + ")&nbsp;tan(\\frac{2\\pi}{\\lambda}" + lambda +"\\lambda)}{(" + z0 + ")+ j&nbsp;(" + zl + ")&nbsp;tan(\\frac{2\\pi}{\\lambda}" + lambda +"\\lambda)}$ <br><br>";
        steps += "Step 5: Cancel out $\\lambda$<br>" +
                "$\\Zeta_{in} = "  +  z0 + "\\frac{(" + zl + ")+ j&nbsp;(" + z0 + ")&nbsp;tan(2\\pi \\times" + lambda +")}{(" + z0 + ")+ j&nbsp;(" + zl + ")&nbsp;tan(2\\pi \\times" + lambda +")}$ <br><br>";
        steps += "Step 6: Use Calculator in Pi mode for Complex Operations <br>";

        Complex static_two = new Complex (2, 0);
        Complex static_pie = new Complex (Math.PI, 0);
        Complex static_lambda = new Complex (lambda, 0);
        Complex static_half = new Complex (0.5, 0);

        Complex static_imag = new Complex(0, 1);

        //for answer
        Complex zin = new Complex(z0.times(((zl.plus(static_imag.times(z0).times((static_two.times(static_pie).times(static_lambda)).tan()))).divides(z0.plus(static_imag.times(zl).times((static_two.times(static_pie).times(static_lambda)).tan()))))).re(), z0.times(((zl.plus(static_imag.times(z0).times((static_two.times(static_pie).times(static_lambda)).tan()))).divides(z0.plus(static_imag.times(zl).times((static_two.times(static_pie).times(static_lambda)).tan()))))).im());
        //for steps
        Complex zin2 = new Complex(z0.times(((zl.plus(static_imag.times(z0).times((static_two.times(static_pie).times(static_lambda)).tan()))).divides(z0.plus(static_imag.times(zl).times((static_two.times(static_pie).times(static_lambda)).tan()))))).re(), z0.times(((zl.plus(static_imag.times(z0).times((static_two.times(static_pie).times(static_lambda)).tan()))).divides(z0.plus(static_imag.times(zl).times((static_two.times(static_pie).times(static_lambda)).tan()))))).im());

        steps += "$\\Zeta_{in} = " + zin2.sprintf() + "$ <br><br>";

//        tan calculation
//        ((static_two.times(static_pie).times(static_lambda)).tan());
//        top calculation
//        steps += zl.plus(static_imag.times(z0).times((static_two.times(static_pie).times(static_lambda)).tan())) + "<br>";
//        bottom calculation
//        steps += z0.plus(static_imag.times(zl).times((static_two.times(static_pie).times(static_lambda)).tan())) + "<br>";

        steps += "Step 7: Substitute inputs into $P_{L}$ formula: <br>";
        steps += "$P_{L} = \\frac{1}{2} \\vert \\frac{V_g}{Z_g + Z_{in}} \\vert ^2 \\real (Z_{in})$ <br>";
        steps += "$P_{L} = \\frac{1}{2} \\vert \\frac{ " + vg + "}{" + zg  + " + (" + zin2 + ")} \\vert ^2 (" + zin2.re() + ")$ <br><br>";

        steps += "Step 8: Expand inner abs equation: <br>";
        steps += "$P_{L} = \\frac{1}{2} \\vert "  + (vg.divides(zg.plus(zin2))).sprintf() + " \\vert ^2 (" + zin2.re() + ")$ <br><br>";

        Complex temporary_ans = new Complex((vg.divides(zg.plus(zin))).abs(), 0);

        steps += "Step 9: Calculate abs value of complex number: <br>";
        steps += "$P_{L} = \\frac{1}{2} (" + temporary_ans + ") ^2 (" + zin2.re() + ")$ <br><br>";

        steps += "Step 10: Calculate squared value of complex number: <br>";
        steps += "$P_{L} = \\frac{1}{2} (" + temporary_ans.times(temporary_ans).sprintf() + ") (" + zin2.re() + ")$ <br><br>";

        Complex zin_real = new Complex(zin.re(), 0);

        steps += "Step 11: Answer: <br>";
        steps += "$P_{L} = " + (static_half.times(temporary_ans.times(temporary_ans))).times(zin_real) + "\\text{ W}$<br>";

        return steps;
    }
}
