package example.android.greeting;

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
import android.widget.Toast;

public class GreetingActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_greeting);

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
        getMenuInflater().inflate(R.menu.greeting, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_greeting, container, false);

            // ボタンオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_click);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // クリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View arg0) {
                // 入力値の取得
                EditText input = (EditText)getActivity().findViewById(R.id.et_hour);

                // インテントの生成
                Intent intent = new Intent(Intent.ACTION_VIEW);

                // 入力値の振り分け
                int time = 0;
                try {
                    time = Integer.parseInt(input.getText().toString());
                } catch (NumberFormatException e) {
                    // 入力情報をトースト機能で画面表示
                    Toast.makeText(getActivity(),
                                    "不正な値が入力されました！",
                                Toast.LENGTH_SHORT).show();
                    // プログラムの終了
                    return;
                }

                String text = "";
                String prefix = "";
                if (time >= 4 && time <= 12) {
                    text = "おはよう！";
                    prefix = "goodmorning";
                } else if (time >= 13 && time <= 18) {
                    text = "こんにちは！";
                    prefix = "goodafternoon";
                } else if ( (time >= 19 && time <= 24) || (time >= 1 && time <= 3)) {
                    text = "こんばんは！";
                    prefix = "goodevening";
                } else {
                    // 入力情報をトースト機能で画面表示
                    Toast.makeText(getActivity(),
                                    "不正な値が入力されました！",
                                Toast.LENGTH_SHORT).show();
                    // プログラムの終了
                    return;
                }

                // URI設定
                Uri uri = Uri.parse("intentmondai:///"+prefix+"?greeting=" + text);

                // URIをインテントに設定
                intent.setData(uri);

                // 次のアクティビティの起動
                startActivity(intent);
            }
        }
    }
}
