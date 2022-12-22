package hourglass.github.in.katex.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.Group;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import hourglass.github.in.katex.R;

public class ComplexNumberAdd extends AppCompatActivity {
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complexnumber_add);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Add 2 Complex Numbers");

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
}
