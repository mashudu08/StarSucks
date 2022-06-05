package za.ac.iie.opsc.starsucks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class OrderDetailsActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference starSucksRef = database.getReference("orders");

    private EditText etCustomerName;
    private EditText etCustomerCell;
    private TextView placedOrder;
    private String orderedValue;
    private ImageView imgOrderedBeverage;
    private FloatingActionButton fab;
    private FloatingActionButton fabCalendar;
    private FloatingActionButton fabCloud;
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
        fabCalendar = findViewById(R.id.fab_calendar);
        fabCloud = findViewById(R.id.fab_cloud);

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

        fabCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a calendar to get today's date
                Calendar datePickerCalendar = Calendar.getInstance();
                int year = datePickerCalendar.get(Calendar.YEAR);
                int month = datePickerCalendar.get(Calendar.MONTH);
                int day = datePickerCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog ordersDatePicker = new DatePickerDialog(
                        OrderDetailsActivity.this,
                        android.R.style.Theme_Light_Panel,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int month, int dayOfMonth) {

                                order.setOrderDate(year + "-" + month + "-" + dayOfMonth);
                            }
                        }, year, month, day);

                ordersDatePicker.show();
            }
        });

        fabCloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerCell = etCustomerCell.getText().toString();
                String customerName = etCustomerName.getText().toString();

                if (!TextUtils.isEmpty(customerName) && !TextUtils.isEmpty(customerCell) &&
                        !TextUtils.isEmpty(order.getOrderDate()) &&
                        !TextUtils.isEmpty(orderedValue)) {
                    order.setProductName(orderedValue);
                    order.setCustomerName(customerName);
                    order.setCustomerCell(customerCell);

                    starSucksRef.push().setValue(order);
                }
                else {

                    Toast.makeText(OrderDetailsActivity.this,
                            "Please complete all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}