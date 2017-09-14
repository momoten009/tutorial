package example.android.implicitintentsample2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ImplicitlntentSample2Activity extends Activity {

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_implicitlntent_sample2);

        // フラグメントの設定
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    // onCreateOptionsMenuメソッド(オプションメニュー生成)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // オプションメニューを設定
        getMenuInflater().inflate(R.menu.implicitlntent_sample2, menu);
        return true;
    }

    // onOptionsItemSelectedメソッド(オプションメニュー選択)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // オプションメニューID取得
        int id = item.getItemId();
        // オプションメニュー判定
        if (id == R.id.action_settings) {
            return true;
        }
        // スーパークラスのonOptionsItemSelectedメソッド呼び出し
        return super.onOptionsItemSelected(item);
    }

    // フラグメントクラス定義
    private static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_implicitlntent_sample2, container, false);

            // ボタンオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_execute);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // クリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // テキストボックスの入力情報取得
                EditText content = (EditText)getActivity().findViewById(R.id.et_content);
                String contentStr = content.getText().toString();

                // 選択リストの選択情報取得
                Spinner application = (Spinner)getActivity().findViewById(R.id.sp_application);
                String applicationStr = (String)application.getSelectedItem();

                // アクションの設定
                String action = null;
                if(applicationStr.equals("電話")){
                    action = "android.intent.action.DIAL";
                    contentStr = "tel:"+content.getText().toString();
                }else if(applicationStr.equals("WEBブラウザ")){
                    action = "android.intent.action.VIEW";
                }else if(applicationStr.equals("メッセージ")){
                    action = "android.intent.action.VIEW";
                }

             // インテントの生成
                Intent intent = null;

                if(applicationStr.equals("メッセージ")){
                    intent = new Intent(action);
                    intent.putExtra("sms_body", content.getText().toString());
                    intent.setType("vnd.android-dir/mms-sms");
                }else{
                    intent = new Intent(action, Uri.parse(contentStr));
                }

                // 次のアクティビティの起動
                startActivity(intent);
            }
        }
    }
}
