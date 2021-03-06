package ru.mirea.petrov.mireaproject;

import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoomFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoomFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentActivity fa;
    private static final int RC_CREATE_CONTACT = 1;
    private static final int RC_UPDATE_CONTACT = 2;
    private static ContactDAO dao;
    private ContactRecyclerAdapter mContactRecyclerAdapter;
    private EditText phoneText, firstNameText, lastNameText;

    public RoomFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoomFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoomFragment newInstance(String param1, String param2) {
        RoomFragment fragment = new RoomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        dao = Database.getInstance(getContext()).getContactDAO();
    }

    @Override
    public void onStart() {
        super.onStart();
        fa = getActivity();

        RecyclerView mContactsRecyclerView = fa.findViewById(R.id.contactsRecyclerView);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(fa));

        mContactRecyclerAdapter = new ContactRecyclerAdapter(fa, new ArrayList<Contact>());
        mContactRecyclerAdapter.addActionCallback(new ContactRecyclerAdapter.ActionCallback() {
            @Override
            public void onLongClickListener(Contact contact) {
                phoneText.setText(contact.getPhoneNumber());
                firstNameText.setText(contact.firstName);
                lastNameText.setText(contact.lastName);
            }
        });
        mContactsRecyclerView.setAdapter(mContactRecyclerAdapter);

        Button insertButton = fa.findViewById(R.id.buttonInsert);
        insertButton.setOnClickListener(this::onInsertClick);

        Button removeButton = fa.findViewById(R.id.buttonRemove);
        removeButton.setOnClickListener(this::onRemoveClick);

        phoneText = fa.findViewById(R.id.editPhone);
        firstNameText = fa.findViewById(R.id.editFirstName);
        lastNameText = fa.findViewById(R.id.editLastName);

        loadContacts();
    }

    private boolean checkNumber() {
        boolean phonePresent = phoneText.getText() != null;
        if (!phonePresent) {
            Toast.makeText(fa, "?????????????????? ?????????? ????????????????", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean phoneIsNumber = true;
        try {
            Long.parseLong(phoneText.getText().toString());
        } catch (NumberFormatException nfe) {
            phoneIsNumber = false;
        }
        if (!phoneIsNumber) {
            Toast.makeText(fa, "?????????? ????????????????\n???????????? ???????? ????????????", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void onInsertClick(View view) {
        if (!checkNumber())
            return;
        Contact contact = new Contact();
        contact.setPhoneNumber(phoneText.getText().toString());
        contact.firstName = firstNameText.getText().toString();
        contact.lastName = lastNameText.getText().toString();
        contact.dateCreated = new Date();

        try {
            dao.insert(contact);
        } catch (SQLiteConstraintException e) {
            Toast.makeText(fa, "?????????????? ?? ?????????? ?????????????? ?????? ????????????????????", Toast.LENGTH_SHORT).show();
        }
        loadContacts();
    }

    private Contact getContact() {
        if (!checkNumber())
            return null;

        try {
            Contact contact = dao.getContactWithId(phoneText.getText().toString());
            return contact;
        } catch (SQLiteException e) {
            Toast.makeText(fa, "??????-???? ?????????? ???? ??????", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    private void onRemoveClick(View view) {
        Contact contact = getContact();
        if (contact != null)
            dao.delete(contact);
        loadContacts();
    }

    private void loadContacts() {
        mContactRecyclerAdapter.updateData(dao.getContacts());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false);
    }
}