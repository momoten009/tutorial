package example.android.preferencemondai;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

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

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            // ボタンオブジェクト取得
            Button button1=(Button)rootView.findViewById(R.id.bt_send);
            // ボタンオブジェクトにクリックリスナー設定
            button1.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // クリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // 名前取得
                EditText name = (EditText)getActivity().findViewById(R.id.et_name);

                // 住所取得
                EditText address = (EditText)getActivity().findViewById(R.id.et_address);

                // 生年月日取得
                Spinner month = (Spinner)getActivity().findViewById(R.id.sp_month);
                Spinner day = (Spinner)getActivity().findViewById(R.id.sp_day);

                // 性別取得
                RadioGroup radio = (RadioGroup)getActivity().findViewById(R.id.rg_gender);
                RadioButton radiobutton =
                    (RadioButton)getActivity().findViewById(radio.getCheckedRadioButtonId());


                // 希望商品取得
                CheckBox applecheck = (CheckBox)getActivity().findViewById(R.id.cb_apple);
                CheckBox orangecheck = (CheckBox)getActivity().findViewById(R.id.cb_orange);
                CheckBox peachcheck = (CheckBox)getActivity().findViewById(R.id.cb_peach);

                // 注文数量取得
                EditText appleqty = (EditText)getActivity().findViewById(R.id.et_apple);
                EditText orangeqty = (EditText)getActivity().findViewById(R.id.et_orange);
                EditText peachqty = (EditText)getActivity().findViewById(R.id.et_peach);

                // インテントの生成(呼び出すクラスの指定)
                Intent intent = new Intent(getActivity(), SecondActivity.class);

                // 入力データをインテントに設定
                intent.putExtra("NAME", name.getText().toString());
                intent.putExtra("ADDRESS", address.getText().toString());
                intent.putExtra("MONTH", month.getSelectedItem().toString());
                intent.putExtra("DAY", day.getSelectedItem().toString());
                intent.putExtra("GENDAR", radiobutton.getText().toString());
                if(applecheck.isChecked())
                    intent.putExtra("APPLE", appleqty.getText().toString());
                if(orangecheck.isChecked())
                    intent.putExtra("ORANGE", orangeqty.getText().toString());
                if(peachcheck.isChecked())
                    intent.putExtra("PEACH", peachqty.getText().toString());

                // 次のアクティビティの起動
                startActivity(intent);
            }
        }
    }
}
