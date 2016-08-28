package com.example.cristi.fiborv.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cristi.fiborv.R;
import com.example.cristi.fiborv.data.FiboSource;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FiboAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new FiboAdapter(new String[0]);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_set_n:
                showSetNDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSetNDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);
        final View customView = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        builder.setView(customView);
        builder.setCancelable(false);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText etInput = (EditText) customView.findViewById(R.id.user_input);
                String input = etInput.getText().toString();
                updateNValue(input);
            }
        });

        builder.show();
    }

    private void updateNValue(String input) {
        try {
            int nValue = Integer.parseInt(input);
            List<Integer> newFiboSequence = FiboSource.getFibonacciSequence(nValue);
            adapter.updateDataSet(intListToStringArray(newFiboSequence));
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.invalid_input_toast, Toast.LENGTH_LONG).show();
        }
    }

    private String[] intListToStringArray(List<Integer> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = String.valueOf(list.get(i));
        }

        return arr;
    }
}
