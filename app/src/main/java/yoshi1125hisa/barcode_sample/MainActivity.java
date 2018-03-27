package yoshi1125hisa.barcode_sample;

 import android.app.Activity;
 import android.os.Bundle;
 import android.widget.TextView;
 import com.google.zxing.ResultPoint;
 import com.journeyapps.barcodescanner.BarcodeCallback;
 import com.journeyapps.barcodescanner.BarcodeResult;
 import com.journeyapps.barcodescanner.CompoundBarcodeView;
 import java.util.List;

public class MainActivity extends Activity {

    private TextView textView;
    private CompoundBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textViewScanned);
        barcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result)
            {
                textView.setText(result.getText());
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