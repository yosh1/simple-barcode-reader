package yoshi1125hisa.barcode_sample;

 import android.app.Activity;
 import android.app.SearchManager;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.net.Uri;
 import android.os.Bundle;
 import android.support.v4.app.ActivityCompat;
 import android.support.v4.content.ContextCompat;
 import android.view.View;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.google.zxing.ResultPoint;
 import com.journeyapps.barcodescanner.BarcodeCallback;
 import com.journeyapps.barcodescanner.BarcodeResult;
 import com.journeyapps.barcodescanner.CompoundBarcodeView;
 import java.util.List;

public class MainActivity extends Activity {

    private TextView textView;
    private CompoundBarcodeView barcodeView;
    public String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(
                this,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
            // 許可されている時の処理
        }else{
            //許可されていない時の処理
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
                //拒否された時 Permissionが必要な理由を表示して再度許可を求めたり、機能を無効にしたりします。
                Toast.makeText(this, "アプリの権限が拒否されたため\nこのアプリを使用できません", Toast.LENGTH_SHORT).show();
            } else {
                //まだ許可を求める前の時、許可を求めるダイアログを表示します。
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 0);

            }
        }

        textView = (TextView)findViewById(R.id.textViewScanned);

        barcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result)
            {
                textView.setText(result.getText());
                keyword = result.getText();

                findViewById(R.id.textViewScanned).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse("https://www.google.com/search?q=バーコード%"+keyword);
                        Intent i = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(i);
                    }
                });

            }

            @Override
            public void possibleResultPoints(List<ResultPoint> resultPoints) {
                // ???
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    public void onPause() {
        barcodeView.pause();
        super.onPause();
    }


}