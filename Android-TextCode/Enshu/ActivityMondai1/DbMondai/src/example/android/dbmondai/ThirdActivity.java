package example.android.dbmondai;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ThirdActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_third);

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
        getMenuInflater().inflate(R.menu.third, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_third, container, false);

            return rootView;
        }
    }
}
