package example.android.implicitintentsample2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ImplicitlntentSample2Activity extends Activity {

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_implicitlntent_sample2);

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
        getMenuInflater().inflate(R.menu.implicitlntent_sample2, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_implicitlntent_sample2, container, false);

            // �{�^���I�u�W�F�N�g�擾
            Button button = (Button)rootView.findViewById(R.id.bt_execute);
            // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // �N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v) {
                // �e�L�X�g�{�b�N�X�̓��͏��擾
                EditText content = (EditText)getActivity().findViewById(R.id.et_content);
                String contentStr = content.getText().toString();

                // �I�����X�g�̑I�����擾
                Spinner application = (Spinner)getActivity().findViewById(R.id.sp_application);
                String applicationStr = (String)application.getSelectedItem();

                // �A�N�V�����̐ݒ�
                String action = null;
                if(applicationStr.equals("�d�b")){
                    action = "android.intent.action.DIAL";
                    contentStr = "tel:"+content.getText().toString();
                }else if(applicationStr.equals("WEB�u���E�U")){
                    action = "android.intent.action.VIEW";
                }else if(applicationStr.equals("���b�Z�[�W")){
                    action = "android.intent.action.VIEW";
                }

             // �C���e���g�̐���
                Intent intent = null;

                if(applicationStr.equals("���b�Z�[�W")){
                    intent = new Intent(action);
                    intent.putExtra("sms_body", content.getText().toString());
                    intent.setType("vnd.android-dir/mms-sms");
                }else{
                    intent = new Intent(action, Uri.parse(contentStr));
                }

                // ���̃A�N�e�B�r�e�B�̋N��
                startActivity(intent);
            }
        }
    }
}
