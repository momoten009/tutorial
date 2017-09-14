package example.android.listclicksample;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListClickSampleActivity extends Activity {

    // onCreateメソッド(画面初期表示イベントハンドラ)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // スーパークラスのonCreateメソッド呼び出し
        super.onCreate(savedInstanceState);
        // レイアウト設定ファイルの指定
        setContentView(R.layout.activity_list_click_sample);

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
        getMenuInflater().inflate(R.menu.list_click_sample, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_list_click_sample, container, false);

            // ListViewオブジェクト取得
            ListView listview = (ListView)rootView.findViewById(R.id.lv_greeting);
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
                // クリック時のListViewオブジェクト取得
                ListView listview = (ListView) parent;
                // 選択された値取得
                String item = (String) listview.getItemAtPosition(position);

                // 選択された値をトースト機能で画面表示
                Toast.makeText(getActivity(),
                               item,
                               Toast.LENGTH_SHORT).show();
            }
        }
    }
}
