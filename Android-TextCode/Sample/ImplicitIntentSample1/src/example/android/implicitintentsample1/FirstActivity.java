package example.android.implicitintentsample1;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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

            // ListViewオブジェクト取得
            ListView listview = (ListView)rootView.findViewById(R.id.fruitlist);
            // ListViewオブジェクトにクリックリスナー設定
            listview.setOnItemClickListener(new ListItemClickListener());

            return rootView;
        }

        // アイテムクリックリスナー定義
        private class ListItemClickListener implements OnItemClickListener {
            // onItemClickメソッド(リストの値クリック時イベントハンドラ)
            public void onItemClick(AdapterView<?> parent,
                                        View view,
                                        int position,
                                        long id) {
                // ListViewオブジェクト取得
                ListView listview = (ListView) parent;
                // 選択された値取得
                String item = (String) listview.getItemAtPosition(position);

                // インテントの生成(データを表示するアクション指定)
                Intent intent = new Intent(Intent.ACTION_VIEW);

                // URI設定
                String uriStr = "";
                if(item.equals("Apple")){
                    uriStr += "intentsample://fruit/apple?selecteditem="+item;
                }else if(item.equals("Banana")){
                    uriStr += "intentsample://fruit/banana?selecteditem="+item;
                }else if(item.equals("Grape")){
                    uriStr += "intentsample://fruit/grape?selecteditem="+item;
                }else{
                    uriStr += "intentsample://fruitall?selecteditem=all";
                }
                Uri uri = Uri.parse(uriStr);

                // URIをインテントに設定
                intent.setData(uri);

                // 次のアクティビティの起動
                startActivity(intent);
            }
        }
    }
}
