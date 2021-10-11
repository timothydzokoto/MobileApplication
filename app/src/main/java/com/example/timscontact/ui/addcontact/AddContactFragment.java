package com.example.timscontact.ui.addcontact;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timscontact.MainActivity;
import com.example.timscontact.PrefConfig;
import com.example.timscontact.R;
import com.example.timscontact.ui.contacts.ContactAdapter;
import com.example.timscontact.ui.contacts.ContactRecord;
import com.example.timscontact.ui.contacts.ConvertImage;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddContactFragment extends Fragment {

    public static AddContactFragment newInstance() {
        return new AddContactFragment();
    }

    private AddContactViewModel mViewModel;

    private TextView name, company, email, address;
    private TextView number;
    private Button addBtn, cancelBtn, btnAddImg;
    private ImageView displayImage;
    private NavigationView navigationView;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    private String convertedImage;
    private Bitmap defaultImage;


    private ContactAdapter contactAdapter;

    private List<ContactRecord> contactList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_add_contact, container, false);

        name = root.findViewById(R.id.edt_name);
        company = root.findViewById(R.id.edt_company);
        number = root.findViewById(R.id.edt_number);
        email = root.findViewById(R.id.edt_email);
        address = root.findViewById(R.id.edt_address);
        addBtn = root.findViewById(R.id.btn_add);
        cancelBtn = root.findViewById(R.id.btn_cancel);
        btnAddImg = root.findViewById(R.id.btn_add_img);
        displayImage = root.findViewById(R.id.contact_img);
        navigationView = getActivity().findViewById(R.id.nav_view);
        defaultImage = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.contact);



        contactList = PrefConfig.readListFromPref(getContext());

        if(contactList == null)
            contactList = new ArrayList<>();


        addBtn.setOnClickListener(view -> {

            if(name.getText().toString().isEmpty() || number.getText().toString().isEmpty()) {
                Snackbar.make(getView(), "Name and Number feilds are required.", Snackbar.LENGTH_LONG).show();
            }else{
                ContactRecord record = new ContactRecord(
                        name.getText().toString(),
                        address.getText().toString(),
                        email.getText().toString(),
                        company.getText().toString(),
                        number.getText().toString(),
                        convertedImage);
                if(imageToStore == null){
                    record.setContactImg(ConvertImage.BitMapToString(defaultImage));
                }
                contactList.add(record);
                PrefConfig.writeListInPref(getContext(), contactList);
                navigationView.getMenu().performIdentifierAction(R.id.nav_contacts, 1);
            }

        });

        btnAddImg.setOnClickListener(view -> {
            chooseImage(view);
        });
        displayImage.setOnClickListener(view -> {
            chooseImage(view);
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AddContactViewModel.class);
        // TODO: Use the ViewModel
    }

    public void chooseImage(View view){
        Intent objIntent = new Intent();
        objIntent.setType("image/*");
        objIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(objIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == MainActivity.RESULT_OK && data != null && data.getData() != null){

            try {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), imageFilePath);
                displayImage.setImageBitmap(imageToStore);

                convertedImage = ConvertImage.BitMapToString(imageToStore);
                System.out.println("Display part of the app.");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }




}