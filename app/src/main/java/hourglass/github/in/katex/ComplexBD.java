package hourglass.github.in.katex;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import hourglass.github.in.katex.activities.VariablesSolver;
import hourglass.github.in.katex.dialogs.VariablesSolverSettingsDialog;

public class ComplexBD extends AppCompatActivity {
    public final BigDecimal re;   // the real part
    public final BigDecimal im;   // the imaginary part


    // create a new object with the given real and imaginary parts
    public ComplexBD(BigDecimal real, BigDecimal imag) {
        re = real.stripTrailingZeros();
        im = imag.stripTrailingZeros();
    }

    // return a string representation of the invoking Complex object
    public String toPString() {
        if (im.compareTo(BigDecimal.ZERO) == 0) return re.toPlainString() + "";
        if (re.compareTo(BigDecimal.ZERO) == 0) return im.toPlainString() + "i";
        if (im.compareTo(BigDecimal.ZERO) <  0) return re.toPlainString() + " - " + (im.negate().toPlainString()) + "i";
        return re.toPlainString() + " + " + im.toPlainString() + "i";
    }

    // return a string representation of the invoking Complex object
    public String toString() {
        if (im.compareTo(BigDecimal.ZERO) == 0) return re + "";
        if (re.compareTo(BigDecimal.ZERO) == 0) return im + "i";
        if (im.compareTo(BigDecimal.ZERO) <  0) return re + " - " + (im.negate()) + "i";
        return re + " + " + im + "i";
    }

    // return a new Complex object whose value is (this * b)
    public ComplexBD times(ComplexBD b) {
        // invoking object
        ComplexBD a = this;
        // variables
        BigDecimal one = a.re;
        BigDecimal two = a.im;
        BigDecimal three = b.re;
        BigDecimal four = b.im;

        BigDecimal real = (a.re.multiply(b.re)).subtract(a.im.multiply(b.im));
        BigDecimal imag = (a.re.multiply(b.im)).add(a.im.multiply(b.re));
        return new ComplexBD(real, imag);
    }

    // return a new Complex object whose value is (this + b)
    public ComplexBD plus(ComplexBD b) {
        // invoking object
        ComplexBD a = this;
        // variables
        BigDecimal one = a.re;
        BigDecimal two = a.im;
        BigDecimal three = b.re;
        BigDecimal four = b.im;

        BigDecimal real = one.add(three);
        BigDecimal imag = two.add(four);
        return new ComplexBD(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public ComplexBD minus(ComplexBD b) {
        // invoking object
        ComplexBD a = this;
        // variables
        BigDecimal one = a.re;
        BigDecimal two = a.im;
        BigDecimal three = b.re;
        BigDecimal four = b.im;

        BigDecimal real = one.subtract(three);
        BigDecimal imag = two.subtract(four);
        return new ComplexBD(real, imag);
    }

    // return a new Complex object whose value is (this - b)
    public ComplexBD div(ComplexBD b) {
        // invoking object
        ComplexBD a = this;
        return a.times(b.reciprocal());
    }

    // return a new Complex object whose value is the reciprocal of this
    public ComplexBD reciprocal() {
        BigDecimal scale = (re.multiply(re)).add(im.multiply(im));
        // To prevent division by zero if no inputs (since initialised as zero)
        if(scale == BigDecimal.ZERO)
            return new ComplexBD(BigDecimal.ZERO, BigDecimal.ZERO);
        else
            return new ComplexBD((re.divide(scale, Integer.valueOf(VariablesSolver.loadDP())+1, RoundingMode.HALF_UP)), ((im.negate()).divide(scale, Integer.valueOf(VariablesSolver.loadDP())+1, RoundingMode.HALF_UP)));
    }

    // return a complex object whose value is the conjugate of this
    public ComplexBD conjugate(){
        return new ComplexBD(re, im.negate());
    }

    }



