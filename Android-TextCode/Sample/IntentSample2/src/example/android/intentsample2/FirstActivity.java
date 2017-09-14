package example.android.intentsample2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
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

public class FirstActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_first);

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
        getMenuInflater().inflate(R.menu.first, menu);
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

        // リクエストコード定義
        private static final int SHOSW_CALC = 0;

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            // ボタンオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_next);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // ボタンクリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // インテントの生成(呼び出すクラスの指定)
                Intent intent = new Intent(getActivity(), SecondActivity.class);

                // 次のアクティビティの起動
                startActivityForResult(intent, SHOSW_CALC);
            }
        }

        // onActivityResultメソッド(フラグメント再表示時イベントハンドラ)
        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == SHOSW_CALC) {
                if (resultCode == RESULT_OK) {
                    // インテントから付加情報取得
                    Bundle extra = data.getExtras();
                    // 選択されたあいさつ取得
                    String selectedGreeting =extra.getString("SELECTED_GREETING");
                    // テキストボックスの入力情報取得
                    EditText input = (EditText)getActivity().findViewById(R.id.et_name);

                    // トースト機能で画面表示
                    Toast.makeText(getActivity(),
                                   selectedGreeting+"\n"+input.getText()+"さん",
                                   Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
