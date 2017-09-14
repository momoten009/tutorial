package example.android.activitymondai2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMondai2Activity extends Activity {

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_activity_mondai2);

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
        getMenuInflater().inflate(R.menu.activity_mondai2, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_activity_mondai2, container, false);

            // Spinnerオブジェクト取得
            Spinner spinner = (Spinner)rootView.findViewById(R.id.sp_greeting);
            // Spinnerオブジェクトにイベントリスナーを関連付け
            spinner.setOnItemSelectedListener(new SelectItemSelectedListener());

            return rootView;
        }

        // 選択リスナー定義
        private class SelectItemSelectedListener implements OnItemSelectedListener {
            // onItemSelectedメソッド(選択時イベントハンドラ)
            public void onItemSelected(AdapterView<?> parent,
                                    View view, int position,
                                    long id) {

                // テキストボックスの入力情報取得
                EditText name = (EditText)getActivity().findViewById(R.id.et_name);

                // 選択ボックスの入力情報取得
                Spinner spinner = (Spinner)getActivity().findViewById(R.id.sp_greeting);
                String greeting = (String)spinner.getItemAtPosition(position);

                // 選択された値をトースト機能で画面表示
                Toast.makeText(getActivity(),
                               greeting + "\n" + name.getText() + "さん",
                               Toast.LENGTH_SHORT).show();

            }
            // onNothingSelectedメソッド
            public void onNothingSelected(AdapterView<?> parent) {
            }
        }
    }
}
