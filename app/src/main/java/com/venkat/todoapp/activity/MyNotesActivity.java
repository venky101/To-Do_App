package com.venkat.todoapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.venkat.todoapp.constant.AppConstants;
import com.venkat.todoapp.constant.PreferenceConstants;
import com.venkat.todoapp.R;
import com.venkat.todoapp.adapter.NotesAdapter;
import com.venkat.todoapp.clicklisteners.ItemClickListener;
import com.venkat.todoapp.model.Notes;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyNotesActivity extends AppCompatActivity {

    private String fullName;
    private FloatingActionButton fabAddNotes;
    private SharedPreferences sharedPreferences;
    private RecyclerView recyclerViewNotes;
    private ArrayList<Notes> notesList = new ArrayList<>();
    private String TAG = "MyNotes";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        bindViews();
        setSharedPreference();
        setNotesTitle();
        fabAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupDialog();
            }
        });
    }

    private void bindViews() {
        fabAddNotes = findViewById(R.id.addNotes);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
    }

    private void setNotesTitle() {
        fullName = getIntent().getStringExtra(AppConstants.FULL_NAME);
        if(TextUtils.isEmpty(fullName)) {
            fullName = sharedPreferences.getString(PreferenceConstants.FULL_NAME, "");
        }
        getSupportActionBar().setTitle(fullName);
    }

    private void setSharedPreference() {
        sharedPreferences = getSharedPreferences(PreferenceConstants.NAME, MODE_PRIVATE);
    }

    private void setupDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.add_notes_dialog, null);
        final EditText title = view.findViewById(R.id.Title);
        final EditText description = view.findViewById(R.id.Description);
        Button submit = view.findViewById(R.id.submitButton);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                                    .setCancelable(false)
                                    .setView(view)
                                    .create();
        dialog.show();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titles = title.getText().toString();
                String Descriptions = description.getText().toString();
                if(!TextUtils.isEmpty(titles) && !TextUtils.isEmpty(Descriptions)) {
                    Notes notes = new Notes(titles, Descriptions);
                    notesList.add(notes);
                    setUpRecyclerView();
                    dialog.hide();
                }
                else {
                    Toast.makeText(MyNotesActivity.this, "Please enter title and description", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setUpRecyclerView() {
        ItemClickListener itemClickListeners = new ItemClickListener() {
            @Override
            public void onCLick(Notes notes) {
                Intent intent = new Intent(MyNotesActivity.this, DetailActivity.class);
                intent.putExtra(AppConstants.TITLE, notes.getTitle());
                intent.putExtra(AppConstants.DESCRIPTION, notes.getDescription());
                startActivity(intent);
            }
        };
        NotesAdapter notesAdapter = new NotesAdapter(notesList, itemClickListeners);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewNotes.setLayoutManager(linearLayoutManager);
        recyclerViewNotes.setAdapter(notesAdapter);
    }
}
