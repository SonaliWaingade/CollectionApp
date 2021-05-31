package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class QRcodeActivity extends AppCompatActivity {
    Button btn_generate;
    EditText et_email;
    ImageView iv_qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_rcode);

        btn_generate = findViewById(R.id.QR_Btn_generate);
        et_email = findViewById(R.id.QR_et_email);
        iv_qrcode = findViewById(R.id.QR_imgView);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
                Display display = windowManager.getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int x = point.x;
                int y = point.y;

                int icon = x < y? x:y;
                icon = icon *3/4;

                QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(email, null, Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), icon);

                try {
                    Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                    iv_qrcode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
