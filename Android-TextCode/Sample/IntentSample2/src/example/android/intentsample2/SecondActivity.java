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
import android.widget.Spinner;

public class SecondActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_second);

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
        getMenuInflater().inflate(R.menu.second, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);

            // ボタンオブジェクトオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_back);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        //ボタンクリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // インテント取得
                Intent intent = getActivity().getIntent();

                // Spinnerオブジェクト取得
                Spinner spinner = (Spinner)getActivity().findViewById(R.id.sp_greeting);
                // 選択されたあいさつ取得
                String greeting = (String)spinner.getSelectedItem();

                // 選択された値をインテントに設定
                intent.putExtra("SELECTED_GREETING", greeting);

                // 結果情報の設定
                getActivity().setResult(RESULT_OK, intent);

                // アクティビティ終了(画面クローズ)
                getActivity().finish();
            }
        }
    }
}
