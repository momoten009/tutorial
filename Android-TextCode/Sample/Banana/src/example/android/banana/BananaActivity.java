package example.android.banana;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class BananaActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_banana);

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
        getMenuInflater().inflate(R.menu.banana, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_banana, container, false);

            // ボタンオブジェクトオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_back);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // onStartメソッド(フラグメント表示前イベントハンドラ)
        @Override
        public void onStart() {
            // スーパークラスのonStartメソッド呼び出し
            super.onStart();

            // URI取得
            Uri uri = getActivity().getIntent().getData();
            if(uri!=null){
                // URIのQueryString情報取得
                String fruitname = uri.getQueryParameter("selecteditem");
                // TextViewオブジェクト取得
                TextView furittext = (TextView)getActivity().findViewById(R.id.tv_fruit);
                // 果物の名前表示
                furittext.setText(fruitname);
            }
        }

        // ボタンクリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // アクティビティ終了(画面クローズ)
                getActivity().finish();
            }
        }
    }
}
