package example.android.dbsample;

import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DBSampleActivity extends Activity { // Activityクラスを継承

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_dbsample);

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
        getMenuInflater().inflate(R.menu.dbsample, menu);
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

        CreateProductHelper helper = null;
        SQLiteDatabase db = null;

        public PlaceholderFragment() {
        }

        // onCreateViewメソッド(フラグメント初期表示イベントハンドラ)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // フラグメント設定情報取得
            View rootView = inflater.inflate(R.layout.fragment_dbsample, container, false);

            // 登録ボタンのクリックリスナー設定
            Button insertBtn = (Button)rootView.findViewById(R.id.bt_insert);
            insertBtn.setTag("insert");
            insertBtn.setOnClickListener(new ButtonClickListener());

            // 更新ボタンのクリックリスナー設定
            Button updatetBtn = (Button)rootView.findViewById(R.id.bt_update);
            updatetBtn.setTag("update");
            updatetBtn.setOnClickListener(new ButtonClickListener());

            // 削除ボタンのクリックリスナー設定
            Button delBtn = (Button)rootView.findViewById(R.id.bt_delete);
            delBtn.setTag("delete");
            delBtn.setOnClickListener(new ButtonClickListener());

            // 表示ボタンのクリックリスナー設定
            Button selectBtn = (Button)rootView.findViewById(R.id.bt_display);
            selectBtn.setTag("display");
            selectBtn.setOnClickListener(new ButtonClickListener());

            // DB作成
            helper = new CreateProductHelper(rootView.getContext());

            return rootView;
        }

        // クリックリスナー定義
        private class ButtonClickListener implements OnClickListener {
            // onClickメソッド(ボタンクリック時イベントハンドラ)
            public void onClick(View v){
                // タグの取得
                String tag = (String)v.getTag();

                // メッセージ表示用
                String message  = "";
                TextView label = (TextView)getActivity().findViewById(R.id.tv_message);

                // 入力情報取得
                EditText productid = (EditText)getActivity().findViewById(R.id.et_id);
                EditText name = (EditText)getActivity().findViewById(R.id.et_name);
                EditText price = (EditText)getActivity().findViewById(R.id.et_price);

                // テーブルレイアウトオブジェクト取得
                TableLayout tablelayout = (TableLayout)getActivity().findViewById(R.id.tl_list);

                // テーブルレイアウトのクリア
                tablelayout.removeAllViews();

                // 該当DBオブジェクト取得
                db = helper.getWritableDatabase();

                // 登録ボタンが押された場合
                if(tag.equals("insert")){
                    // テーブル作成
                    try{
                        // SQL文定義
                        String sql
                            = "create table product (" +
                                    "_id integer primary key autoincrement," +
                                    "productid text not null," +
                                    "name text not null," +
                                    "price integer default 0)";
                        // SQL実行
                        db.execSQL(sql);

                        // メッセージ設定
                        message = "テーブルを作成しました！\n";
                    }catch(Exception e){
                        message  = "テーブルは作成されています！\n";
                        Log.e("ERROR",e.toString());
                    }

                    // データ登録
                    try{
                        // トランザクション制御開始
                        db.beginTransaction();

                        // 登録データ設定
                        ContentValues val = new ContentValues();
                        val.put("productid", productid.getText().toString());
                        val.put("name", name.getText().toString());
                        val.put("price", price.getText().toString());
                        // データ登録
                        db.insert("product", null, val);

                        // コミット
                        db.setTransactionSuccessful();

                        // トランザクション制御終了
                        db.endTransaction();

                        // メッセージ設定
                        message += "データを登録しました！";

                    }catch(Exception e){
                        message  = "データ登録に失敗しました！";
                        Log.e("ERROR",e.toString());
                    }

                // 更新ボタンが押された場合
                }else if(tag.endsWith("update")){
                    // ファイルのデータ削除
                    try{
                        // 更新条件
                        String condition = null;
                        if(productid != null && !productid.equals("")){
                            condition = "productid = '" + productid.getText().toString() + "'";
                        }

                        // トランザクション制御開始
                        db.beginTransaction();

                        // 更新データ設定
                        ContentValues val = new ContentValues();
                        val.put("name", name.getText().toString());
                        val.put("price", price.getText().toString());
                        // データ更新
                        db.update("product", val, condition, null);

                        // コミット
                        db.setTransactionSuccessful();

                        // トランザクション制御終了
                        db.endTransaction();

                        // メッセージ設定
                        message = "データを更新しました！";
                    }catch(Exception e){
                        // メッセージ設定
                        message = "データ更新に失敗しました！";
                        Log.e("ERROR",e.toString());
                    }

                // 削除ボタンが押された場合
                }else if(tag.endsWith("delete")){
                    // ファイルのデータ削除
                    try{
                        // 削除条件
                        String condition = null;
                        if(productid != null && !productid.equals("")){

                            condition = "productid = '" + productid.getText().toString() + "'";
                        }

                        // トランザクション制御開始
                        db.beginTransaction();

                        // データ削除
                        db.delete("product", condition, null);

                        // コミット
                        db.setTransactionSuccessful();

                        // トランザクション制御終了
                        db.endTransaction();

                        // メッセージ設定
                        message = "データを削除しました！";
                    }catch(Exception e){
                        // メッセージ設定
                        message = "データ削除に失敗しました！";
                        Log.e("ERROR",e.toString());
                    }

                // 表示ボタンが押された場合
                }else if(tag.equals("display")){
                    // データ取得
                    try{
                        // 該当DBオブジェクト取得
                        db = helper.getReadableDatabase();

                        // 列名定義
                        String columns[] = {"productid","name","price"};

                        // データ取得
                        Cursor cursor = db.query(
                                "product", columns, null, null, null, null, "productid");

                        // テーブルレイアウトの表示範囲を設定
                        tablelayout.setStretchAllColumns(true);

                        // テーブル一覧のヘッダ部設定
                        TableRow headrow = new TableRow(getActivity());

                        TextView headtxt1 = new TextView(getActivity());
                        headtxt1.setText("商品ID");
                        headtxt1.setGravity(Gravity.CENTER_HORIZONTAL);
                        headtxt1.setWidth(60);
                        TextView headtxt2 = new TextView(getActivity());
                        headtxt2.setText("商品名");
                        headtxt2.setGravity(Gravity.CENTER_HORIZONTAL);
                        headtxt2.setWidth(100);
                        TextView headtxt3 = new TextView(getActivity());
                        headtxt3.setText("価格");
                        headtxt3.setGravity(Gravity.CENTER_HORIZONTAL);
                        headtxt3.setWidth(60);
                        headrow.addView(headtxt1);
                        headrow.addView(headtxt2);
                        headrow.addView(headtxt3);
                        tablelayout.addView(headrow);

                        // 取得したデータをテーブル明細部に設定
                        while(cursor.moveToNext()){

                            TableRow row = new TableRow(getActivity());
                            TextView productidtxt
                                    = new TextView(getActivity());
                            productidtxt.setGravity(Gravity.CENTER_HORIZONTAL);
                            productidtxt.setText(cursor.getString(0));
                            TextView nametxt = new TextView(getActivity());
                            nametxt.setGravity(Gravity.CENTER_HORIZONTAL);
                            nametxt.setText(cursor.getString(1));
                            TextView pricetxt = new TextView(getActivity());
                            pricetxt.setGravity(Gravity.CENTER_HORIZONTAL);
                            pricetxt.setText(cursor.getString(2));
                            row.addView(productidtxt);
                            row.addView(nametxt);
                            row.addView(pricetxt);
                            tablelayout.addView(row);

                            // メッセージ設定
                            message = "データを取得しました！";
                        }

                    }catch(Exception e){
                        // メッセージ設定
                        message = "データ取得に失敗しました！";
                        Log.e("ERROR",e.toString());
                    }
                }

                // DBオブジェクトクローズ
                db.close();

                // メッセージ表示
                label.setText(message);
            }
        }
    }
}
