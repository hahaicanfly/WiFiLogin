package akira.com.wifilogin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {



    String htmlString="<u>忘记密码</u>";
    String AccountString="<u>获取用户帐号</u>";

    int current =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_to_wi_fi);
                TextView forgotps = (TextView)findViewById(R.id.forgotpasswd);
        TextView getAccount = (TextView)findViewById(R.id.getAccount);
        Button   btnSubmit = (Button)findViewById(R.id.btnSubmit);

//        getAccount.setText(Html.fromHtml(AccountString));
//        forgotps.setText(Html.fromHtml(htmlString));

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }



}
