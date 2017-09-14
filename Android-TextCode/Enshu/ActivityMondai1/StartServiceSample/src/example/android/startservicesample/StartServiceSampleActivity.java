package example.android.startservicesample;

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

public class StartServiceSampleActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_start_service_sample);

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
        getMenuInflater().inflate(R.menu.start_service_sample, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_start_service_sample, container, false);

            // 開始ボタンオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_start);
            // 開始ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new StartButtonClickListener());

            // 終了ボタンオブジェクトにクリックリスナー設定
            Button stopButton=(Button)rootView.findViewById(R.id.bt_stop);
            stopButton.setOnClickListener(new StopButtonClickListener());

            return rootView;
        }

        // クリックリスナー定義
        private class StartButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // インテント生成
                Intent intent = new Intent(getActivity(),
                                        StartServiceSampleService.class);

                // 終了時間をインテントに設定
                EditText stopcount = (EditText)getActivity().findViewById(R.id.et_time);
                intent.putExtra("STOPTIME", stopcount.getText().toString());

                // サービス開始
                getActivity().startService(intent);

            }
        }

        // 終了ボタンクリックリスナー定義
        private class StopButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベント)
            public void onClick(View v) {
                // インテント生成
                Intent intent = new Intent(getActivity(),
                                         StartServiceSampleService.class);
                // サービス終了
                getActivity().stopService(intent);
            }
        }
    }
}
