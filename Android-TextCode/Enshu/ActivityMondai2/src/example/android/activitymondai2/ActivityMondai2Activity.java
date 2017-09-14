package example.android.activitymondai2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityMondai2Activity extends Activity {

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_activity_mondai2);

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
        getMenuInflater().inflate(R.menu.activity_mondai2, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_activity_mondai2, container, false);

            // Spinner�I�u�W�F�N�g�擾
            Spinner spinner = (Spinner)rootView.findViewById(R.id.sp_greeting);
            // Spinner�I�u�W�F�N�g�ɃC�x���g���X�i�[���֘A�t��
            spinner.setOnItemSelectedListener(new SelectItemSelectedListener());

            return rootView;
        }

        // �I�����X�i�[��`
        private class SelectItemSelectedListener implements OnItemSelectedListener {
            // onItemSelected���\�b�h(�I�����C�x���g�n���h��)
            public void onItemSelected(AdapterView<?> parent,
                                    View view, int position,
                                    long id) {

                // �e�L�X�g�{�b�N�X�̓��͏��擾
                EditText name = (EditText)getActivity().findViewById(R.id.et_name);

                // �I���{�b�N�X�̓��͏��擾
                Spinner spinner = (Spinner)getActivity().findViewById(R.id.sp_greeting);
                String greeting = (String)spinner.getItemAtPosition(position);

                // �I�����ꂽ�l���g�[�X�g�@�\�ŉ�ʕ\��
                Toast.makeText(getActivity(),
                               greeting + "\n" + name.getText() + "����",
                               Toast.LENGTH_SHORT).show();

            }
            // onNothingSelected���\�b�h
            public void onNothingSelected(AdapterView<?> parent) {
            }
        }
    }
}
