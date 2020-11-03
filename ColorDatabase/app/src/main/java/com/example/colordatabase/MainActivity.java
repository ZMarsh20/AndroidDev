package com.example.colordatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ColorAdapter.Listener{
    RecyclerView recyclerView;
    ColorAdapter colorAdapter;
    ProgressBar progressBar;
    TextView textView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemAdd){
            colorAdapter.dialogAdd(this, progressBar, textView);
        }
        else {
            colorAdapter.dialogSort(this);
        }
        colorAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.colors_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        colorAdapter = new ColorAdapter(getApplicationContext());
        recyclerView.setAdapter(colorAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        colorAdapter.setListener(this);
        colorAdapter.notifyDataSetChanged();
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textViewCount);
        ColorDatabaseHelper colorDatabaseHelper = colorAdapter.colorDatabaseHelper;
        int count = colorDatabaseHelper.getCount();
        textView.setText(Integer.toString(count));
    }

    @Override
    public void onClick(int position) {
        ColorAdapter.fav(position);
        colorAdapter.notifyDataSetChanged();
    }
}