package example.android.preferencesample;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class PreferenceSampleActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_preference_sample);

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
        getMenuInflater().inflate(R.menu.preference_sample, menu);
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

        // プリファレンスファイル名
        private static final String FILE_NAME = "PreferenceSampleFile";

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_preference_sample, container, false);

            // 保存ボタンのクリックリスナー設定
            Button saveBtn = (Button)rootView.findViewById(R.id.bt_save);
            saveBtn.setTag("save");
            saveBtn.setOnClickListener(new ButtonClickListener());

            // 表示ボタンのクリックリスナー設定
            Button readBtn = (Button)rootView.findViewById(R.id.bt_display);
            readBtn.setTag("display");
            readBtn.setOnClickListener(new ButtonClickListener());

            // 削除ボタンのクリックリスナー設定
            Button delBtn = (Button)rootView.findViewById(R.id.bt_delete);
            delBtn.setTag("delete");
            delBtn.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // クリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v){
                // タグの取得
                String tag = (String)v.getTag();

                // プリファレンスオブジェクト取得
                SharedPreferences preference
                    = getActivity().getSharedPreferences(FILE_NAME, MODE_PRIVATE);

                // TextView取得
                TextView message = (TextView)getActivity().findViewById(R.id.tv_message);

                // 保存ボタンが押された場合
                if(tag.equals("save")){
                    // プリファレンスの編集用オブジェクト取得
                    SharedPreferences.Editor editor = preference.edit();

                    // 選択されたフォント取得
                    Spinner fontarray = (Spinner)getActivity().findViewById(R.id.sp_font);

                    String font = (String)fontarray.getSelectedItem();

                    // 選択されたスタイル取得
                    CheckBox italic = (CheckBox)getActivity().findViewById(R.id.cb_italic);
                    CheckBox bold = (CheckBox)getActivity().findViewById(R.id.cb_bold);
                    String check = "通常";
                    if(italic.isChecked()){
                        check = "斜体";
                    }
                    if(bold.isChecked()){
                        if(italic.isChecked()){
                            check += "|太字";
                        }else{
                            check = "太字";
                        }
                    }

                    // プリファレンスファイルに保存
                    editor.putString("FONT", font);
                    editor.putString("STYLE", check);
                    editor.commit();

                    // メッセージ表示
                    message.setText("保存しました！");

                // 表示ボタンが押された場合
                }else if(tag.equals("display")){
                    // 保存データ取得
                    String font = preference.getString("FONT", "見つかりません");
                    String style = preference.getString("STYLE", "見つかりません");

                    // メッセージ表示用フォント、スタイル設定
                    Typeface fonttype = Typeface.DEFAULT;
                    if(font.equals("明朝体")){
                        fonttype = Typeface.SERIF;
                    }else if(font.equals("ゴシック体")){
                        fonttype = Typeface.SANS_SERIF;
                    }else if(font.equals("等幅フォント")){
                        fonttype = Typeface.MONOSPACE;
                    }
                    int styleflg = Typeface.NORMAL;
                    if(style.equals("斜体")){
                        styleflg = Typeface.ITALIC;
                    }else if(style.equals("太字")){
                        styleflg = Typeface.BOLD;
                    }else if(style.equals("斜体|太字")){
                        styleflg = Typeface.BOLD_ITALIC;
                    }

                    // メッセージ表示
                    message.setText("Preference Sample\n" +
                            "フォント：" + font +
                            "\nスタイル：" + style);
                    message.setTypeface(Typeface.create(fonttype, styleflg));

                // 削除ボタンが押された場合
                }else if(tag.endsWith("delete")){
                    // プリファレンスの編集用オブジェクト取得
                    SharedPreferences.Editor editor = preference.edit();

                    // すべての保存データ削除
                    editor.clear();
                    editor.commit();

                    // メッセージ表示
                    message.setText("削除しました！");
                }
            }
        }
    }
}
