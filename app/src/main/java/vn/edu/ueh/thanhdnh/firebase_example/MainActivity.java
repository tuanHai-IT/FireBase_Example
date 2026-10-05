package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  FirebaseFirestore db;
  Button btAdd, btShow;
  EditText etTitle, etDescription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });

    FirebaseApp.initializeApp(this);
    db = FirebaseFirestore.getInstance();
    btAdd = findViewById(R.id.btAdd);
    btShow = findViewById(R.id.btShow);
    etTitle = findViewById(R.id.etName);
    etDescription = findViewById(R.id.etPhone);
    btAdd.setOnClickListener(this);
    btShow.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    if (view.getId() == R.id.btAdd) {
      String title = etTitle.getText().toString().trim();
      String description = etDescription.getText().toString().trim();

      if (title.isEmpty() || description.isEmpty()) {
        Toast.makeText(this, "Vui lòng nhập đầy đủ Title và Description!", Toast.LENGTH_SHORT).show();
        return;
      }

      Article article = new Article(title, description);
      db.collection("articles").add(article)
          .addOnSuccessListener(documentReference -> {
            Toast.makeText(MainActivity.this, "Đã thêm thành công!", Toast.LENGTH_SHORT).show();
            etTitle.setText("");
            etDescription.setText("");
          })
          .addOnFailureListener(e -> {
            Toast.makeText(MainActivity.this, "Lỗi thêm: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("FirestoreError", "Error adding document", e);
          });
    } else if (view.getId() == R.id.btShow) {
      Intent intent = new Intent(getBaseContext(), ShowDataActivity.class);
      startActivity(intent);
    }
  }
}
