package com.example.ggc_marketplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresExtension;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment {
    //
    View view;
    Button btnPickImage;
    Button btnEditDescription;
    ImageView imageView;
    TextView txtDescription;
    ActivityResultLauncher<Intent> resultLauncher;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_account, container, false);

        btnPickImage = view.findViewById(R.id.btnPickImage);
        imageView = view.findViewById(R.id.imageView);
        btnEditDescription = view.findViewById(R.id.btnEditDescription);
        txtDescription = view.findViewById(R.id.txtDescription);

        btnEditDescription.setOnClickListener(v -> editDescription());

        registerResult();
        btnPickImage.setOnClickListener(view -> pickImage());

        return view;
    }

    //Used to pick a profile image from phone's gallery
    @RequiresExtension(extension = Build.VERSION_CODES.R, version = 2)
    private void pickImage() {
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    private void registerResult() {
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try {
                            if (result.getResultCode() == requireActivity().RESULT_OK
                                    && result.getData() != null) {

                                Uri imageUri = result.getData().getData();
                                if (imageUri != null) {
                                    imageView.setImageURI(imageUri);
                                }
                            }
                        } catch (Exception e) {
                            Toast.makeText(requireContext(),
                                    "Unable to select image",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    // Edit profile description
    private void editDescription() {

        android.widget.EditText editText =
                new android.widget.EditText(requireContext());

        editText.setText(txtDescription.getText().toString());
        editText.setHint("Enter your profile description");

        new androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle("Edit Profile Description")
                .setView(editText)

                .setPositiveButton("Save", (dialog, which) -> {

                    String description = editText.getText().toString().trim();

                    if (!description.isEmpty()) {

                        txtDescription.setText(description);

                        Toast.makeText(
                                requireContext(),
                                "Profile description updated!",
                                Toast.LENGTH_SHORT
                        ).show();

                    } else {
                        Toast.makeText(
                                requireContext(),
                                "Description cannot be empty!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                })

                .setNegativeButton("Cancel", null)
                .show();
    }
}
