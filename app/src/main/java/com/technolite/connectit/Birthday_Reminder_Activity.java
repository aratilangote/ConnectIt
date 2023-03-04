package com.technolite.connectit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Birthday_Reminder_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_READ_PHONE_STATE = 1;
    private static final int REQUEST_CODE_READ_CONTACTS = 2;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =3 ;
    Spinner sim;
    int count=0;
    TextInputEditText date, time, phone, msg;
    TextView smscnt;
    private SharedPreferences sharedPreferences;

    ListView mlistv;

    private ArrayList<Contact> mContacts;
    private ContactsAdapter mAdapter;
    private CallReceiver callReceiver;
    private SmsReceiver smsReceiver;
    MaterialButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_reminder);

        sim = findViewById(R.id.simchooser);
        msg = findViewById(R.id.message);
        phone = findViewById(R.id.contact);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        submit = findViewById(R.id.submit);
        smscnt=findViewById(R.id.smscount);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        count = sharedPreferences.getInt("count", 0);
        smscnt.setText(" SMS count: " + count);

        mlistv=findViewById(R.id.mlist);
        mContacts = new ArrayList<>();
        mAdapter = new ContactsAdapter(this, mContacts);
        mlistv.setAdapter(mAdapter);
       /* TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String simOperatorName = telephonyManager.getSimOperatorName();
        List<String> list = new ArrayList<>();
        list.add(simOperatorName);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sim.setAdapter(adapter);*/
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.SEND_SMS,Manifest.permission.READ_CONTACTS}, PackageManager.PERMISSION_GRANTED);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);

            }
            try {
                SubscriptionManager subscriptionManager = (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
                List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();

                List<String> list = new ArrayList<>();
                for (SubscriptionInfo subscriptionInfo : subscriptionInfoList) {
                    CharSequence name = subscriptionInfo.getCarrierName();
                    String number = subscriptionInfo.getNumber();
                    list.add(name + " " + number);
                }

                try {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sim.setAdapter(adapter);

                } catch (Exception e) {
                    Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        final SubscriptionInfo[] selectedSubscription = new SubscriptionInfo[1];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            SubscriptionManager subscriptionManager = null;
            subscriptionManager = (SubscriptionManager) getSystemService(Context.TELEPHONY_SUBSCRIPTION_SERVICE);
            List<SubscriptionInfo> subscriptionInfoList = subscriptionManager.getActiveSubscriptionInfoList();
            sim.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int selectedIndex = sim.getSelectedItemPosition();
                    selectedSubscription[0] = subscriptionInfoList.get(selectedIndex);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

       /* if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_READ_CONTACTS);
            }

        }else{

        }*/
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String keyword = editable.toString();
                mContacts.clear();
                mContacts.addAll(queryContacts(keyword));
                mAdapter.notifyDataSetChanged();
            }
        });
        mlistv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = (Contact) parent.getItemAtPosition(position);
                phone.setText(selectedContact.getNumber());
                mlistv.setVisibility(View.GONE);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Birthday_Reminder_Activity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String formattedHour = String.format("%02d", selectedHour);
                        String formattedMinute = String.format("%02d", selectedMinute);
                        time.setText(formattedHour + ":" + formattedMinute );
                        // time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString();
                String dateString = date.getText().toString();
                String timeString = time.getText().toString();
                String number = phone.getText().toString();

                if(date.getText().toString().isEmpty()){
                    date.setError("Please Select Date");
                } else if (time.getText().toString().isEmpty()) {
                    time.setError("Please Select Time");
                }else if(phone.getText().toString().isEmpty()){
                    phone.setError("Please Enter Number");

                } else if (msg.getText().toString().isEmpty()) {
                    msg.setError("Please Enter Massegge ");
                }
                // parse the date and time from the EditTexts

                try {
                    count++;
                    smscnt.setText("SMS count: " + count);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("count", count);
                    editor.apply();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                        Date dateTime = dateFormat.parse(dateString + " " + timeString);
                        long triggerTime = dateTime.getTime();
                        long requestCode = System.currentTimeMillis();

                        Intent intent = new Intent(Birthday_Reminder_Activity.this, SmsReceiver.class);
                        intent.putExtra("number", number);
                        intent.putExtra("message", message);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                            intent.putExtra("subscriptionId", selectedSubscription[0].getSubscriptionId());
                        }

                        PendingIntent pendingIntent = PendingIntent.getBroadcast(Birthday_Reminder_Activity.this, (int) requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                    }catch (Exception e){
                        Toast.makeText(Birthday_Reminder_Activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(MainActivity.this, "SMS Scheduled", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(Birthday_Reminder_Activity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                /*Intent intent = new Intent(MainActivity.this, CallReceiver.class);
                intent.putExtra("number", number);
                intent.putExtra("message", message);*/

                // create the Intent to send the SMS
         //       startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();

            }
        });
    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                Birthday_Reminder_Activity.this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
        date.setText(selectedDate);
    }
    private ArrayList<Contact> queryContacts(String keyword) {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver resolver = getContentResolver();

        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ? OR " +
                        ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?",
                new String[]{"%" + keyword + "%", "%" + keyword + "%"},
                null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(new Contact(name, number));
            }
            cursor.close();
        }
        return contacts;
    }
}