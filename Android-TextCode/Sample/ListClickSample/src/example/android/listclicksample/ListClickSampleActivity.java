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

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_list_click_sample);

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
        getMenuInflater().inflate(R.menu.list_click_sample, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_list_click_sample, container, false);

            // ListView�I�u�W�F�N�g�擾
            ListView listview = (ListView)rootView.findViewById(R.id.lv_greeting);
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
                // �N���b�N����ListView�I�u�W�F�N�g�擾
                ListView listview = (ListView) parent;
                // �I�����ꂽ�l�擾
                String item = (String) listview.getItemAtPosition(position);

                // �I�����ꂽ�l���g�[�X�g�@�\�ŉ�ʕ\��
                Toast.makeText(getActivity(),
                               item,
                               Toast.LENGTH_SHORT).show();
            }
        }
    }
}
