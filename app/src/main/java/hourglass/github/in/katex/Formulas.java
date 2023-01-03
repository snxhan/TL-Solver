package hourglass.github.in.katex;

import java.text.DecimalFormat;

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
        answer = "$" + (a.plus(b)).toString() + "$";
        return answer;
    }
    /**
     * FORMULA b1s : Add Complex
     */
    public static String addComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step 1: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)+(c+di)$</span><br> $= (a+c)+(b+d)i$<br>" +
                "$=(" + a.re() + " + " + b.re() + ") + (" + a.im() + " + " + b.im() + ")i$<br><br>";
        steps += "Step 2: Expand:<br>" +
                "$(" + (a.re() + b.re()) + ") + (" + (a.im() + b.im()) + ")i$<br><br>" ;
        steps += "Step 3: Answer:<br>" +
                "$" + (a.plus(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b2a : Subtract Complex
     */
    public static String subtractComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.minus(b)).toString() + "$";
        return answer;
    }
    /**
     * FORMULA b2s : Subtract Complex
     */
    public static String subtractComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step 1: Group real part and imaginary part of complex number:<br><span style='color:red;'>$(a+bi)-(c+di)$</span><br> $= (a-c)+(b-d)i$<br>" +
                "$=(" + a.re() + " - " + b.re() + ") + (" + a.im() + " - " + b.im() + ")i$<br><br>";
        steps += "Step 2: Expand:<br>" +
                "$(" + (a.re() - b.re()) + ") + (" + (a.im() - b.im()) + ")i$<br><br>" ;
        steps += "Step 3: Answer:<br>" +
                "$" + (a.minus(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b3a : Multiply Complex
     */
    public static String multiplyComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.times(b)).toString() + "$";
        return answer;
    }
    /**
     * FORMULA b3s : Multiply Complex
     */
    public static String multiplyComplex_steps(Complex a, Complex b){
        String steps = "";
        steps += "Step 1: Apply complex arithmetic rule:<br><span style='color:red;'>$(a+bi)(c+di)$</span><br> $= (ac-bd)+(ad+bc)i$<br>" +
                "$=(" + a.re() + " * " + b.re() + " - " + a.im() + " * " + b.im() + ") + (" + a.re() + " * " + b.im() + " + " + a.im() + " * " + b.re() + ")i$<br><br>";
        steps += "Step 2: Expand:<br>" +
                "$(" + a.re()*(b.re()) + " - " + a.im()*(b.im()) + ") + (" + a.re()*(b.im()) + " + " + a.im()*(b.re()) + ")i$<br><br>" ;
        steps += "Step 3: Simplify:<br>" +
                "$(" + ((a.re()*(b.re()))-(a.im()*(b.im()))) + ") + (" + ((a.re()*(b.im()))+(a.im()*(b.re()))) + ")i$<br><br>" ;
        steps += "Step 4: Answer:<br>" +
                "$" + (a.times(b)).toString() + "$" ;
        return steps;
    }

    /**
     * FORMULA b4a : Divide Complex
     */
    public static String divideComplex_answer(Complex a, Complex b){
        String answer = "";
        answer = "$" + (a.divides(b)).toString() + "$";
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
        steps += "Step 1: Multiply with conjugate: <br>$\\frac{"+ a.re() + new_a_im +"}{"+ b.re() + new_b_im +"}$<span style='color:red;'>$\\times\\frac{"+ b.conjugate() +"}{"+ b.conjugate() +"}$</span><br><br>";
        steps += "Step 2: Multiply Complex Numbers: <br>" +
                "$\\frac{(" + a.re() + new_a_im + ")\\times(" + b.conjugate() + ")}{(" + b.re() + new_b_im + ")\\times(" + b.conjugate() + ")}$<br><br>";
//        steps += (multiplyComplex_steps(a, b.conjugate())) + "<br><br>";
        steps += "Step 3: Multiplication results: <br>" +
                "$=\\frac{(" + removeDollarSign(multiplyComplex_answer(a, b.conjugate())) + ")}{(" + removeDollarSign(multiplyComplex_answer(b, b.conjugate())) + ")}$<br><br>";
        steps += "Step 4: Rewrite in $(a+bi)$ form:<br>" +
                "$=\\frac{" + (a.times(b.conjugate())).re() + "}{" + b.times(b.conjugate()) + "} + \\frac{" + (a.times(b.conjugate())).im() + "}{" + b.times(b.conjugate()) + "}i$<br><br>" ;
        steps += "Step 5: Answer:<br>" +
                "$=" + a.divides(b).toString() + "$" ;
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
        steps += "<b>Step 1: To convert Complex Number $z$ to Polar Form $r \\angle\\theta$ recall that: </b><br>" +
                "<span style='color:red;'>$(z = x+iy) = (r\\angle\\theta) $</span><br><br>" +
                "To find $r$; which is the Modulus of $z$,<br><span style='color:red;'>$r$</span> $= \\vert z\\vert = $<span style='color:red;'>$&nbsp;\\sqrt{x^2+y^2}$</span><br>" +
                "To find $\\theta;$ which is the Argument of $z$, <br><span style='color:red;'>$\\theta$</span> $= \\arg(z) =$<span style='color:red;'>$&nbsp;\\tan^{-1} \\frac{y}{x}$</span><br><br>";
        steps += "<b>Step 2: Solve for $r$ and $\\theta$ :</b><br>" +
                "$r = \\sqrt{" + a.re() + "^2 + " + a.im() + "^2} =$ <span style='color:green;'>$" + a.abs() + "$</span> <br>" +
                "$\\theta = \\tan^{-1} \\frac{" + a.im() + "}{"  + a.re() + "} = $ <span style='color:green;'>$" + a.phase() + "$</span>$\\text{ rad}$<br>" +
                "$\\theta = \\tan^{-1} \\frac{" + a.im() + "}{"  + a.re() + "} = $ <span style='color:green;'>$" + a.phaseDeg() + "$</span>$\\degree$<br><br>";
        steps += "<b>Step 3: Answer:</b><br>" +
                "$" + a.abs() + "&nbsp;\\angle&nbsp;" + a.phase() + "\\text{ rad}$<br>";
        return steps;
    }

    /**
     * FORMULA t1a : Reflection Coefficient
     */
    public static String reflectionCoefficient_answer(Complex zl, Complex z0){
        String answer = "";
        answer = "$" + zl.minus(z0).divides(zl.plus(z0)).toString() + "$";
        return answer;
    }
    /**
     * FORMULA t1s_1 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_1(Complex zl, Complex z0){
        String steps = "";

        // Format whole number to remove .0
        DecimalFormat df = new DecimalFormat();
        df.setDecimalSeparatorAlwaysShown(false);

        // Track zl imaginary sign and put in imaginary sign
        String new_zl_im = "";
        if (zl.im() < 0)
            new_zl_im = zl.im() + "i"; // if negative then let it be
        else
            new_zl_im = "+" + zl.im() + "i"; // if positive then add positive sign

        steps += "Step 1: Recall $\\Gamma$ (Gamma) formula:<br><span style='color:red;'>$\\Gamma = \\frac{\\Zeta_L -\\Zeta_0}{\\Zeta_L +\\Zeta_0}$</span><br><br>";
        steps += "Step 2: Substitute with given inputs:<br>";
        steps += "$\\Gamma =\\frac{(" + df.format(zl.re()) + (new_zl_im) + ")-(" + (z0.re()) + ")}{(" + zl.re() + new_zl_im + ")+(" + (z0.re()) + ")}$<br><br>";
        steps += "Step 3: Group real parts and imaginary parts and simplify: <br> <span style='color:red;'><i>*Compute using calculator</i></span><br> ";
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
        steps += "<br>Step 4: Answer:<br>";
        steps += reflectionCoefficient_answer(zl, z0) + "<br>";
        return steps;
    }
    /**
     * FORMULA t1s_3 : Reflection Coefficient
     */
    public static String reflectionCoefficient_steps_1_3(Complex zl, Complex z0){
        String steps = "";
        steps += "Answer in Polar form<br>";
        steps += Formulas.convertComplexToPolar_answer((zl.minus(z0)).divides(zl.plus(z0)));
        steps += "<br>";
        steps += Formulas.convertComplexToPolar_steps((zl.minus(z0)).divides(zl.plus(z0)));
        return steps;
    }

}
