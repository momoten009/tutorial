package example.android.startservicesample;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class StartServiceSampleActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_start_service_sample);

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
        getMenuInflater().inflate(R.menu.start_service_sample, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_start_service_sample, container, false);

            // �J�n�{�^���I�u�W�F�N�g�擾
            Button button = (Button)rootView.findViewById(R.id.bt_start);
            // �J�n�{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            button.setOnClickListener(new StartButtonClickListener());

            // �I���{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            Button stopButton=(Button)rootView.findViewById(R.id.bt_stop);
            stopButton.setOnClickListener(new StopButtonClickListener());

            return rootView;
        }

        // �N���b�N���X�i�[��`
        private class StartButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v) {
                // �C���e���g����
                Intent intent = new Intent(getActivity(),
                                        StartServiceSampleService.class);

                // �I�����Ԃ��C���e���g�ɐݒ�
                EditText stopcount = (EditText)getActivity().findViewById(R.id.et_time);
                intent.putExtra("STOPTIME", stopcount.getText().toString());

                // �T�[�r�X�J�n
                getActivity().startService(intent);

            }
        }

        // �I���{�^���N���b�N���X�i�[��`
        private class StopButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g)
            public void onClick(View v) {
                // �C���e���g����
                Intent intent = new Intent(getActivity(),
                                         StartServiceSampleService.class);
                // �T�[�r�X�I��
                getActivity().stopService(intent);
            }
        }
    }
}
