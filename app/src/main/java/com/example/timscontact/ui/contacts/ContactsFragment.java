package com.example.timscontact.ui.contacts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.timscontact.PrefConfig;
import com.example.timscontact.R;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private ContactsViewModel mViewModel;

    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private ArrayList<ContactRecord> contactList;



    public static ContactsFragment newInstance() {
        return new ContactsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        contactList = (ArrayList<ContactRecord>) PrefConfig.readListFromPref(getContext());

        if(contactList == null){
            contactList = new ArrayList<>();
            Bitmap defaultImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.default_contact);
            ContactRecord record = new ContactRecord("Micheal Kelly", "Oyibi", "JJ Nortey", "Student", "0241", null);
            ContactRecord record2 = new ContactRecord("Johnson Bawa", "Adentan", "JJ Nortey", "Student", "0201", null);


            contactList.add(record);
            contactList.add(record2);
        }
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(contactAdapter);



        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                contactAdapter.getFilter().filter(s);
                return false;
            }
        });
    }
}