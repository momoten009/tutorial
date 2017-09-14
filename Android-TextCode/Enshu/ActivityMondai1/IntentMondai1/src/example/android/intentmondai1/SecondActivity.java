package example.android.intentmondai1;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

            // ボタンオブジェクト取得
            Button button = (Button)rootView.findViewById(R.id.bt_back);
            // ボタンオブジェクトにクリックリスナー設定
            button.setOnClickListener(new ButtonBackClickListener());

            return rootView;
        }

        // onStartメソッド(フラグメント表示前イベントハンドラ)
        @Override
        public void onStart() {
            // スーパークラスのonStartメソッド呼び出し
            super.onStart();

            // インテントの付加情報取得
            Bundle extra = getActivity().getIntent().getExtras();

            // 付加情報から入力データ取得
            String name = extra.getString("NAME");
            String address = extra.getString("ADDRESS");
            String month = extra.getString("MONTH");
            String day = extra.getString("DAY");
            String gendar = extra.getString("GENDAR");
            String apple = extra.getString("APPLE");
            String orange = extra.getString("ORANGE");
            String peach = extra.getString("PEACH");

            // 出力用テキストオブジェクト取得
            TextView tvName = (TextView)getActivity().findViewById(R.id.tv_name);
            TextView tvAddress = (TextView)getActivity().findViewById(R.id.tv_address);
            TextView tvMonth = (TextView)getActivity().findViewById(R.id.tv_month);
            TextView tvDay = (TextView)getActivity().findViewById(R.id.tv_day);
            TextView tvGendar = (TextView)getActivity().findViewById(R.id.tv_gender);
            TextView tvApple = (TextView)getActivity().findViewById(R.id.tv_apple);
            TextView tvOrange = (TextView)getActivity().findViewById(R.id.tv_orange);
            TextView tvPeach = (TextView)getActivity().findViewById(R.id.tv_peach);

            // 出力用テキストオブジェクに入力データ設定
            tvName.setText(name);
            tvAddress.setText(address);
            tvMonth.setText(month);
            tvDay.setText(day);
            tvGendar.setText(gendar);
            if(apple != null)tvApple.setText(apple);
            if(orange != null)tvOrange.setText(orange);
            if(peach != null)tvPeach.setText(peach);
        }

        // クリックリスナー定義
        private class ButtonBackClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // 画面をクローズ
                getActivity().finish();
            }
        }
    }
}
