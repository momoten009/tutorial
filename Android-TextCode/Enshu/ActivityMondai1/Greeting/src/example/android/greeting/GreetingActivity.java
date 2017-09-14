package example.android.greeting;

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
import android.widget.Toast;

public class GreetingActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_greeting);

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
        getMenuInflater().inflate(R.menu.greeting, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_greeting, container, false);

            // �{�^���I�u�W�F�N�g�擾
            Button button = (Button)rootView.findViewById(R.id.bt_click);
            // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // �N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View arg0) {
                // ���͒l�̎擾
                EditText input = (EditText)getActivity().findViewById(R.id.et_hour);

                // �C���e���g�̐���
                Intent intent = new Intent(Intent.ACTION_VIEW);

                // ���͒l�̐U�蕪��
                int time = 0;
                try {
                    time = Integer.parseInt(input.getText().toString());
                } catch (NumberFormatException e) {
                    // ���͏����g�[�X�g�@�\�ŉ�ʕ\��
                    Toast.makeText(getActivity(),
                                    "�s���Ȓl�����͂���܂����I",
                                Toast.LENGTH_SHORT).show();
                    // �v���O�����̏I��
                    return;
                }

                String text = "";
                String prefix = "";
                if (time >= 4 && time <= 12) {
                    text = "���͂悤�I";
                    prefix = "goodmorning";
                } else if (time >= 13 && time <= 18) {
                    text = "����ɂ��́I";
                    prefix = "goodafternoon";
                } else if ( (time >= 19 && time <= 24) || (time >= 1 && time <= 3)) {
                    text = "����΂�́I";
                    prefix = "goodevening";
                } else {
                    // ���͏����g�[�X�g�@�\�ŉ�ʕ\��
                    Toast.makeText(getActivity(),
                                    "�s���Ȓl�����͂���܂����I",
                                Toast.LENGTH_SHORT).show();
                    // �v���O�����̏I��
                    return;
                }

                // URI�ݒ�
                Uri uri = Uri.parse("intentmondai:///"+prefix+"?greeting=" + text);

                // URI���C���e���g�ɐݒ�
                intent.setData(uri);

                // ���̃A�N�e�B�r�e�B�̋N��
                startActivity(intent);
            }
        }
    }
}
