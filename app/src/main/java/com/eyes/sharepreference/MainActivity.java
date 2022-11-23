package com.eyes.sharepreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.eyes.sharepreference.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean status =false;
    String fileName="My Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.write.setOnClickListener(view -> {
           String name=binding.editText.getText().toString();
           if(name.equals(String.valueOf(""))){
               binding.editText.setError("Please Enter Your Name!");
           }else{
               try{
                   FileOutputStream fileOutputStream=openFileOutput(fileName,MODE_PRIVATE);
                   OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                   outputStreamWriter.write(name);
                   outputStreamWriter.close();
                   binding.editText.setText("");
                   Toast.makeText(this, "file save successfully", Toast.LENGTH_SHORT).show();

               } catch (Exception e) {
                   e.printStackTrace();

               }
           }

        });
        binding.read.setOnClickListener(view -> {
            try {
                FileInputStream fileInputStream=openFileInput(fileName);
                InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder=new StringBuilder();
                String line=null;
                while((line=bufferedReader.readLine()) !=null){
                    stringBuilder.append(line);
                }
                fileInputStream.close();
                inputStreamReader.close();

                Toast.makeText(this, "File Read Successfully", Toast.LENGTH_SHORT).show();

                binding.textView.setText("Name"+stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();

            }

        });
    }
}