package ghazimoradi.soheil.jalali_date_converter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatePicker gregorianDatePicker;
    private TextView jalaliDateTxt;
    private Button btnJalaliConvert;
    private DateConverterHelper helper;
    private Calendar today;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        helper = new DateConverterHelper();
        today = Calendar.getInstance();
        selectedDate = today.get(Calendar.YEAR)
                + "/" + (today.get(Calendar.MONTH) + 1)
                + "/" + today.get(Calendar.DAY_OF_MONTH);

        gregorianDatePicker = findViewById(R.id.gregorianDatePicker);
        jalaliDateTxt = findViewById(R.id.jalaliDateTxt);
        btnJalaliConvert = findViewById(R.id.btnJalaliConvert);
        setUpView();
    }

    private void setUpView() {
        gregorianDatePicker.setMinDate(helper.getDateMilliseconds("1970/01/01", null));
        gregorianDatePicker.init(
                today.get(Calendar.YEAR),
                today.get(Calendar.MONTH),
                today.get(Calendar.DAY_OF_MONTH),
                (view, year, month, day) -> {
                    String monthPrefix = "";
                    String dayPrefix = "";
                    if (month < 10) {
                        monthPrefix = "0";
                    }
                    if (day < 10) {
                        dayPrefix = "0";
                    }
                    selectedDate = year + "/" + monthPrefix + (month + 1) + "/" + dayPrefix + day;
                }
        );
        btnJalaliConvert.setOnClickListener(
                v -> {
                    jalaliDateTxt.setVisibility(View.VISIBLE);
                    jalaliDateTxt.setText(helper.g2j(selectedDate));
                }
        );
    }
}