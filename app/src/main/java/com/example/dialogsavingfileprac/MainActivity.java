package com.example.dialogsavingfileprac;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText textDisplay;
    private Button saveBtn;
    private Button loadBtn;
    private Button clearBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textDisplay = (EditText)
                findViewById(R.id.textDisplay);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        loadBtn = (Button) findViewById(R.id.loadBtn);
        clearBtn = (Button) findViewById(R.id.clearBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Saving File");
                LayoutInflater li = LayoutInflater.from(
                        getApplicationContext());
                View promptsView = li.inflate(
                        R.layout.get_file_name_layout, null);
                builder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.
                        findViewById(R.id.editTextUserInput);
                builder.setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialog, int which)
                            { /*  ...  */  }
                        });
                builder.setPositiveButton("Confirm Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface
                                                        dialog, int which) {
                                String fileName = userInput.
                                        getText().toString();
                                saveFile(fileName);
                                Toast.makeText(MainActivity.this,
                                        "File saved", Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                builder.show();
            }
        });

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Load File");
                LayoutInflater li = LayoutInflater.from(
                        getApplicationContext());
                View promptsView = li.inflate(
                        R.layout.get_file_name_layout, null);
                builder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextUserInput);
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(
                                    DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this,
                                        "No clicked",Toast.LENGTH_LONG).show();
                            }
                        });

                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                String fileName = userInput.getText()
                                        .toString();
                                loadFile(fileName);
                                Toast.makeText(getApplicationContext(),
                                        "Yes clicked",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();
            } //onClick
        });

        clearBtn.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v) {
                textDisplay.setText(""); }
        });
    } // end of onCreate()

    private void saveFile(String fileName) {
        String data = textDisplay.getText().toString();
        try {
            FileOutputStream out =
                    this.openFileOutput(fileName, MODE_PRIVATE);
            out.write(data.getBytes());
            out.close();
            Toast.makeText(this,"File saved!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,"Error:",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFile(String fileName) {
        try {
            FileInputStream in =
                    this.openFileInput(fileName);
            BufferedReader br= new BufferedReader(
                    new InputStreamReader(in));
            StringBuilder sb= new StringBuilder();
            String s= null;
            while((s= br.readLine())!= null)  {
                sb.append(s).append("\n");
            }
            textDisplay.setText(sb.toString());
        } catch (Exception e) {
            Toast.makeText(this,"Error:",
                    Toast.LENGTH_SHORT).show();
        }
    }
} // end of MainActivity()



