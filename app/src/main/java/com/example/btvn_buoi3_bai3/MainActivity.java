    package com.example.btvn_buoi3_bai3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btvn_buoi3_bai3.database.FileDatabase;

import java.util.ArrayList;
import java.util.List;

    public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FileAdapter adapter;
    private List<File> mFileList;
    //private File file;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*mFileList = new ArrayList<>();
        mFileList.add(new File("Video"));
        mFileList.add(new File("Android"));
        mFileList.add(new File("Applock"));
        mFileList.add(new File("Books"));
        mFileList.add(new File("map"));
        mFileList.add(new File("Authenticate Using Google"));
        mFileList.add(new File("New folder"));
        mFileList.add(new File("New folder 1"));*/


        adapter = new FileAdapter(new FileAdapter.IClickItemFile() {
            @Override
            public void updateFileName(String name) {
                clickUpdateFileName(name);
            }

            @Override
            public void deleteFile(File file) {
                clickDeleteFile(file);
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.setData(mFileList);


        loadData();

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.add_file, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if(item.getItemId() == R.id.menu_Add){
                DialogAdd();
            }
            return super.onOptionsItemSelected(item);
        }

        private void DialogAdd(){
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_item);
            EditText edtName = dialog.findViewById(R.id.edt_add_file_name);
            Button btnDialogAdd = dialog.findViewById(R.id.btn_add_file);
            Button btnDialogCancel = dialog.findViewById(R.id.btn_cancel);

            btnDialogCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            btnDialogAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String strFileName = edtName.getText().toString();
                    if(strFileName.equals("")){
                        Toast.makeText(MainActivity.this, "Vui lòng nhập tên file", Toast.LENGTH_SHORT).show();
                    }else {
                        File file = new File(strFileName);
                        FileDatabase.getInstance(MainActivity.this).fileDAO().insertFile(file);
                        Toast.makeText(MainActivity.this, "Thêm file thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        loadData();
                    }
                }
            });
            dialog.show();
        }

        private void clickDeleteFile(File file) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm delete file")
                    .setMessage("Are you sure ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FileDatabase.getInstance(MainActivity.this).fileDAO().deleteFile(file);
                            Toast.makeText(MainActivity.this, "Delete user successfully", Toast.LENGTH_SHORT).show();
                            loadData();
                        }
                    }).setNegativeButton("No", null).show();
        }

        private void loadData() {
            mFileList = FileDatabase.getInstance(this).fileDAO().getListFile();
            adapter.setData(mFileList);
        }

        private void clickUpdateFileName(String name) {
            FileDatabase.getInstance(this).fileDAO().updateFileName(name);
            Toast.makeText(this, "Update user successfully", Toast.LENGTH_SHORT).show();
            loadData();
        }


    }