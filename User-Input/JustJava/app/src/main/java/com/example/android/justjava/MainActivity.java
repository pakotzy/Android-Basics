package com.example.android.justjava;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends Activity {
    int quantity = 2;
    int basePrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basePrice = Integer.parseInt(getResources().getString(R.string.base_price));
    }

    /**
     * This method is called when "+" button is clicked.
     *
     * @param view caller
     */
    public void increment(View view) {
        if (quantity <= 100) {
            quantity += 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method is called when "-" button is clicked.
     *
     * @param view caller
     */
    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
            displayQuantity(quantity);
        }
    }

    /**
     * This method goes through all Topping checkboxes and returns single formatted String
     *
     * @return formatted String for Summary
     */
    private String getToppings() {
        String toppings = "";
        CheckBox topping = findViewById(R.id.whipped_cream_check_box);
        if (topping.isChecked()) {
            toppings += getResources().getString(R.string.whipped_cream_text);
        }
        topping = findViewById(R.id.chocolate_check_box);
        if (topping.isChecked()) {
            if (!toppings.isEmpty()) {
                toppings += ", ";
            }
            toppings += getResources().getString(R.string.chocolate_text);
        }

        if (toppings.isEmpty()) {
            return "";
        } else {
            return getResources().getString(R.string.toppings_header) + ": " + toppings + "\n";
        }
    }

    /**
     * Calculates total basePrice for given quantity
     *
     * @return total basePrice
     */
    private int calculatePrice() {
        return quantity * basePrice;
    }

    /**
     * Get buyer name from corresponding field on the screen
     *
     * @return name
     */
    private String getBuyerName() {
        EditText nameEdit = findViewById(R.id.buyer_name_edit);
        return nameEdit.getText().toString();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(String.valueOf(quantity));
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displaySummary(createOrderSummary());
    }

    /**
     * This method creates the given basePrice value on the screen
     *
     * @return Order summary String
     */
    private String createOrderSummary() {
        Resources res = getResources();
        return res.getString(R.string.buyer_name) + ": " + getBuyerName() + "\n" +
                getToppings() +
                res.getString(R.string.quantity_header) + ": " + quantity + "\n" +
                res.getString(R.string.price) + ": " + NumberFormat.getCurrencyInstance().format(calculatePrice()) + "\n" +
                res.getString(R.string.tymessage);
    }

    /**
     * Sends email to recipient with order details
     *
     * @param orderSummary formatted text of the summary
     */
    private void displaySummary(String orderSummary) {
        /*TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(orderSummary);*/

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.mail_subject) + getBuyerName());
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * OnClick handler for Whipped Cream checkbox
     *
     * @param view caller
     */
    public void whippedCreamClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            basePrice += Integer.parseInt(getResources().getString(R.string.whipped_cream_price));
        } else {
            basePrice -= Integer.parseInt(getResources().getString(R.string.whipped_cream_price));
        }
    }

    /**
     * OnClick handler for Chocolate checkbox
     *
     * @param view caller
     */
    public void chocolateClick(View view) {
        CheckBox checkBox = (CheckBox) view;
        if (checkBox.isChecked()) {
            basePrice += Integer.parseInt(getResources().getString(R.string.chocolate_price));
        } else {
            basePrice -= Integer.parseInt(getResources().getString(R.string.chocolate_price));
        }
    }
}