package android.xxxeonm.testvideostreaming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InputUrlActivity extends AppCompatActivity {

    TextView etUrl;
    Button btnUrlSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_url);

        etUrl = (TextView)findViewById(R.id.et_inputurl);
        btnUrlSubmit = (Button)findViewById(R.id.btn_inputurl_submit);
        btnUrlSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*try {

            } catch (Exception e) {

            }*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("validUrl", String.valueOf(etUrl.getText()));
                startActivity(intent);
                Log.d("InputUrlActivity***", String.valueOf(etUrl.getText()));
            }
        });
    }


}
