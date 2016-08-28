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
import com.example.cristi.fiborv.presenter.MainPresenter;
import com.example.cristi.fiborv.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView {

    MainPresenter presenter;
    FiboAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl();
        presenter.attach(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new FiboAdapter(new String[0]);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
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
                presenter.onUserInput(input);
            }
        });

        builder.show();
    }

    @Override
    public void displaySequence(String[] sequence) {
        adapter.updateDataSet(sequence);
    }

    @Override
    public void onInvalidInput() {
        Toast.makeText(this, R.string.invalid_input_toast, Toast.LENGTH_LONG).show();
    }
}
