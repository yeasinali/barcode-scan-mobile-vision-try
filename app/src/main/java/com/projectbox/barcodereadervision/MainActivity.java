package com.projectbox.barcodereadervision;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScanner;
import com.edwardvanraak.materialbarcodescanner.MaterialBarcodeScannerBuilder;
import com.google.android.gms.vision.barcode.Barcode;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.EmptyPermissionListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txt_Result)
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        addPermission();
    }

    private void addPermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new EmptyPermissionListener())
                .check();
    }

    @OnClick(R.id.btn_Scan)
    protected void onScanCliked() {
        MaterialBarcodeScanner scanner = new MaterialBarcodeScannerBuilder()
                .withActivity(this)
                .withEnableAutoFocus(true)
                .withBleepEnabled(true)
                .withBackfacingCamera()
                .withText("Scanning...")
                .withCenterTracker()
                .withOnly2DScanning()
                .withResultListener(new MaterialBarcodeScanner.OnResultListener() {
                    @Override
                    public void onResult(Barcode barcode) {
                        if (barcode != null) {
                            txtResult.setText(barcode.rawValue);
                        }
                    }
                })
                .build();
        scanner.startScan();
    }
}
