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

public class FirstActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_first);

        // �t���O�����g�̐ݒ�
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    // onCreateOptionsMenu���\�b�h(�I�v�V�������j���[����)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // �I�v�V�������j���[��ݒ�
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    // onOptionsItemSelected���\�b�h(�I�v�V�������j���[�I��)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // �I�v�V�������j���[ID�擾
        int id = item.getItemId();
        // �I�v�V�������j���[����
        if (id == R.id.action_settings) {
            return true;
        }
        // �X�[�p�[�N���X��onOptionsItemSelected���\�b�h�Ăяo��
        return super.onOptionsItemSelected(item);
    }

    // �t���O�����g�N���X��`
    private static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        // onCreateView���\�b�h(�t���O�����g�����\���C�x���g�n���h��)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // �t���O�����g�ݒ���擾
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            // ListView�I�u�W�F�N�g�擾
            ListView listview = (ListView)rootView.findViewById(R.id.fruitlist);
            // ListView�I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            listview.setOnItemClickListener(new ListItemClickListener());

            return rootView;
        }

        // �A�C�e���N���b�N���X�i�[��`
        private class ListItemClickListener implements OnItemClickListener {
            // onItemClick���\�b�h(���X�g�̒l�N���b�N���C�x���g�n���h��)
            public void onItemClick(AdapterView<?> parent,
                                        View view,
                                        int position,
                                        long id) {
                // ListView�I�u�W�F�N�g�擾
                ListView listview = (ListView) parent;
                // �I�����ꂽ�l�擾
                String item = (String) listview.getItemAtPosition(position);

                // �C���e���g�̐���(�f�[�^��\������A�N�V�����w��)
                Intent intent = new Intent(Intent.ACTION_VIEW);

                // URI�ݒ�
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

                // URI���C���e���g�ɐݒ�
                intent.setData(uri);

                // ���̃A�N�e�B�r�e�B�̋N��
                startActivity(intent);
            }
        }
    }
}
