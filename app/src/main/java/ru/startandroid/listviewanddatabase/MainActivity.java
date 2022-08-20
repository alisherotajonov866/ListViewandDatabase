package ru.startandroid.listviewanddatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);
        mDatabaseHelper = new DatabaseHelper(this);

        /*
        Bazaga malumot qo'shmoqchi bo'lganimizda (button va edittext yordamida)
        1- edittext dan malumot olinadi va u bazaga malumot qo'shish vazifasini bajaruvchi methodga yuboriladi
         */
        // (ADD)
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("Siz mahsulot kiritishingiz zarur!");
                }

            }
        });


        /*
        View ning item orqali yoki button orqali bir interfeysdan ikkinchisiga o'tish INTENT orqali osongina amalga oshiriladi
         */
        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    // Foydalanuvchiga bazaga malumot qo'shilgani haqida xabar berish uchun:
    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Malumot Saqlandi");
        } else {
            toastMessage("Nimadir xato ketdi!");
        }
    }

    // interfeysda malumot chiqarish uchun:
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}