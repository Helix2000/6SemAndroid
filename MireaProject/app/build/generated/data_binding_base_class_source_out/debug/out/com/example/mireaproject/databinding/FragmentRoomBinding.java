// Generated by view binder compiler. Do not edit!
package com.example.mireaproject.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.example.mireaproject.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentRoomBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonInsert;

  @NonNull
  public final Button buttonRemove;

  @NonNull
  public final RecyclerView contactsRecyclerView;

  @NonNull
  public final EditText editFirstName;

  @NonNull
  public final EditText editLastName;

  @NonNull
  public final EditText editPhone;

  private FragmentRoomBinding(@NonNull LinearLayout rootView, @NonNull Button buttonInsert,
      @NonNull Button buttonRemove, @NonNull RecyclerView contactsRecyclerView,
      @NonNull EditText editFirstName, @NonNull EditText editLastName,
      @NonNull EditText editPhone) {
    this.rootView = rootView;
    this.buttonInsert = buttonInsert;
    this.buttonRemove = buttonRemove;
    this.contactsRecyclerView = contactsRecyclerView;
    this.editFirstName = editFirstName;
    this.editLastName = editLastName;
    this.editPhone = editPhone;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentRoomBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentRoomBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_room, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentRoomBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonInsert;
      Button buttonInsert = rootView.findViewById(id);
      if (buttonInsert == null) {
        break missingId;
      }

      id = R.id.buttonRemove;
      Button buttonRemove = rootView.findViewById(id);
      if (buttonRemove == null) {
        break missingId;
      }

      id = R.id.contactsRecyclerView;
      RecyclerView contactsRecyclerView = rootView.findViewById(id);
      if (contactsRecyclerView == null) {
        break missingId;
      }

      id = R.id.editFirstName;
      EditText editFirstName = rootView.findViewById(id);
      if (editFirstName == null) {
        break missingId;
      }

      id = R.id.editLastName;
      EditText editLastName = rootView.findViewById(id);
      if (editLastName == null) {
        break missingId;
      }

      id = R.id.editPhone;
      EditText editPhone = rootView.findViewById(id);
      if (editPhone == null) {
        break missingId;
      }

      return new FragmentRoomBinding((LinearLayout) rootView, buttonInsert, buttonRemove,
          contactsRecyclerView, editFirstName, editLastName, editPhone);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}