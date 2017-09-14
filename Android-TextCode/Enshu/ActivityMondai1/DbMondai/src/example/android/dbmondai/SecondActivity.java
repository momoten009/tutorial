package example.android.dbmondai;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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

        // 入力データ
        String name = "";
        String address = "";
        String month = "";
        String day = "";
        String gendar = "";
        String apple = "";
        String orange = "";
        String peach = "";

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);

            // 確認ボタンのクリックリスナー設定
            Button confirmbutton = (Button)rootView.findViewById(R.id.bt_confirm);
            confirmbutton.setTag("confirm");
            confirmbutton.setOnClickListener(new ButtonClickListener());

            // 戻るボタンのクリックリスナー設定
            Button backbutton = (Button)rootView.findViewById(R.id.bt_back);
            backbutton.setTag("back");
            backbutton.setOnClickListener(new ButtonClickListener());

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
            name = extra.getString("NAME");
            address = extra.getString("ADDRESS");
            month = extra.getString("MONTH");
            day = extra.getString("DAY");
            gendar = extra.getString("GENDAR");
            apple = extra.getString("APPLE");
            orange = extra.getString("ORANGE");
            peach = extra.getString("PEACH");

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

        // ボタンクリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v) {
                // タグの取得
                String tag = (String)v.getTag();

                // 確認ボタンが押された場合
                if(tag.equals("confirm")){
                    // DBアクセス
                    DBMondaiHelper helper = new DBMondaiHelper(getActivity());

                    // DBオブジェクト取得
                    SQLiteDatabase db = helper.getWritableDatabase();

                    // データ登録
                    try{
                        // テーブル作成
                        String sql
                            = "create table if not exists application(" +
                                "_id integer primary key autoincrement," +
                                "name text not null," +
                                "address text not null," +
                                "gendar text not null," +
                                "apple integer default 0," +
                                "orange integer default 0," +
                                "peach integer default 0)";
                        // SQL実行
                        db.execSQL(sql);

                        // トランザクション制御開始
                        db.beginTransaction();

                        // データ登録
                        ContentValues val = new ContentValues();
                        val.put("name", name);
                        val.put("address",address);
                        val.put("gendar", gendar);
                        val.put("apple", apple);
                        val.put("orange", orange);
                        val.put("peach", peach);
                        db.insert("application", null, val);

                        // コミット
                        db.setTransactionSuccessful();

                        // トランザクション制御終了
                        db.endTransaction();

                        // DBクローズ
                        db.close();
                    }catch(Exception e){
                        Log.e("DB_ERROR", "データベース登録に失敗しました");
                    }

                    // インテントの生成(呼び出すクラスの指定)
                    Intent intent = new Intent(getActivity(), ThirdActivity.class);

                    // 次のアクティビティの起動
                    startActivity(intent);

                // 戻るボタンが押された場合
                }else if(tag.endsWith("back")){
                    // 画面をクローズ
                    getActivity().finish();
                }
            }
        }
    }
}
