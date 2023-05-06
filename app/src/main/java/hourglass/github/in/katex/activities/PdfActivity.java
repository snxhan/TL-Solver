package hourglass.github.in.katex.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import hourglass.github.in.katex.R;

public class PdfActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = findViewById(R.id.pdfview);
        pdfView.fromAsset("cn.pdf").load();

    }
}
