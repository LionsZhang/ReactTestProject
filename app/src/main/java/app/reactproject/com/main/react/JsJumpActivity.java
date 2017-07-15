package app.reactproject.com.main.react;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import app.reactproject.com.R;
import app.reactproject.com.main.react.JSInteraction;

public class JsJumpActivity extends Activity {
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_jump);
        TextView tv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        if (intent.hasExtra(JSInteraction.JS_INFORMATION_KEY)) {
            msg = intent.getStringExtra(JSInteraction.JS_INFORMATION_KEY);
        }
        if (!TextUtils.isEmpty(msg))
            tv.setText(msg);

    }
}
