package za.ac.iie.opsc.starsucks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OrderDetailsActivity extends AppCompatActivity {

    private EditText etCustomerName;
    private EditText etCustomerCell;
    private TextView placedOrder;
    private String orderedValue;
    private ImageView imgOrderedBeverage;
    private FloatingActionButton fab;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);


        order = new Order();
        fab = findViewById(R.id.fab_order);
        placedOrder = findViewById(R.id.tv_placedOrder);
        etCustomerName = findViewById(R.id.et_customerName);
        etCustomerCell = findViewById(R.id.et_customerCell);
        imgOrderedBeverage = findViewById(R.id.img_orderedBeverage);


        orderedValue = getIntent().getStringExtra("order");


        placedOrder.setText(orderedValue);

        switch (orderedValue) {
            case "Soy Latte":
                imgOrderedBeverage.setImageResource(R.drawable.sb1);
                break;
            case "Chocco Frappe":
                imgOrderedBeverage.setImageResource(R.drawable.sb2);
                break;
            case "Bottled Americano":
                imgOrderedBeverage.setImageResource(R.drawable.sb3);
                break;
            case "Rainbow Frapp":
                imgOrderedBeverage.setImageResource(R.drawable.sb4);
                break;
            case "Caramel Frapp":
                imgOrderedBeverage.setImageResource(R.drawable.sb5);
                break;
            case "Black Forest Frapp":
                imgOrderedBeverage.setImageResource(R.drawable.sb6);
                break;
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.shareIntent(OrderDetailsActivity.this, orderedValue);
            }
        });
    }
}