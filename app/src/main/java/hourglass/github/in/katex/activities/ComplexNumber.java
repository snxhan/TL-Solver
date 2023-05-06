package hourglass.github.in.katex.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import hourglass.github.in.katex.R;

public class ComplexNumber extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexnumber);
        // Toolbar and Title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Complex Number");
        // Back button in toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button  = findViewById(R.id.pdfopen);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PdfActivity.class);
                startActivity(intent);
            }
        });
    }

    // Menu button in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.complexnumber_menu, menu);
        return true;
    }

    // Back button direct back to MainActivity
    public boolean onOptionsItemSelected(MenuItem item){
//        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivityForResult(myIntent, 0);
//        return true;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
//            case R.id.complexNumber_add:
//                Intent b = new Intent(this,ComplexNumberAdd.class);
//                this.startActivity(b);
//                return true;
            case R.id.complexNumber_basicOperations:
                Intent c = new Intent(this, ComplexNumberBasicOperations.class);
                this.startActivity(c);
                return true;
            case R.id.complexNumber_polarForm:
                Intent d = new Intent(this, ComplexNumberPolarForm.class);
                this.startActivity(d);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
