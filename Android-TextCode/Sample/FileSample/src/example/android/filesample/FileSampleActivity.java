package example.android.filesample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
import android.widget.EditText;
import android.widget.TextView;

public class FileSampleActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_file_sample);

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
        getMenuInflater().inflate(R.menu.file_sample, menu);
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

        // ファイル名
        private static final String FILE_NAME = "FileSampleFile";

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_file_sample, container, false);

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

                // メッセージ表示用
                String str  = "";
                TextView label = (TextView)getActivity().findViewById(R.id.tv_message);

                // 保存ボタンが押された場合
                if(tag.equals("save")){
                    // 入力情報取得
                    EditText name = (EditText)getActivity().findViewById(R.id.et_name);
                    EditText score = (EditText)getActivity().findViewById(R.id.et_score);

                    // ファイルにデータ保存
                    try{
                        FileOutputStream stream
                            = getActivity().openFileOutput(FILE_NAME, MODE_APPEND);
                        BufferedWriter out
                            = new BufferedWriter(new OutputStreamWriter(stream));

                        String hantei = "不合格";
                        if(Integer.parseInt(score.getText().toString()) >= 210){
                            hantei = "合格";
                        }

                        out.write(name.getText().toString() + "," +
                                    score.getText().toString() + "," +
                                    hantei);
                        out.newLine();
                        out.close();
                        str  = "保存しました！";
                    }catch(Exception e){
                        str  = "データ保存に失敗しました！";
                    }

                // 表示ボタンが押された場合
                }else if(tag.equals("display")){
                    // ファイルからデータ取得
                    try{
                        FileInputStream stream
                            = getActivity().openFileInput(FILE_NAME);
                        BufferedReader in
                            = new BufferedReader(new InputStreamReader(stream));

                        String line = "";
                        while((line = in.readLine())!=null){
                            str += line + "\n";
                        }
                        in.close();
                    }catch(Exception e){
                        str  = "データ取得に失敗しました！";
                    }

                // 削除ボタンが押された場合
                }else if(tag.endsWith("delete")){
                    // ファイル削除
                    try{
                        getActivity().deleteFile(FILE_NAME);
                        str = "削除しました！";
                    }catch(Exception e){
                        str  = "ファイル削除に失敗しました！";
                    }
                }
                // メッセージ表示
                label.setText(str);
            }
        }
    }
}
