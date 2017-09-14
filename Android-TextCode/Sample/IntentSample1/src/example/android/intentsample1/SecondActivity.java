package example.android.intentsample1;

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
import android.widget.ImageView;

public class SecondActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_second);

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
        getMenuInflater().inflate(R.menu.second, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);

            // �{�^���I�u�W�F�N�g�擾
            Button button = (Button)rootView.findViewById(R.id.bt_back);
            // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            button.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // onStart���\�b�h(�t���O�����g�\���O�C�x���g�n���h��)
        @Override
        public void onStart() {
            // �X�[�p�[�N���X��onStart���\�b�h�Ăяo��
            super.onStart();

            // �C���e���g�擾
            Intent data = getActivity().getIntent();

            // �C���e���g�̕t�����擾
            Bundle extras = data.getExtras();
            // �t����񂩂�I�����ꂽ�l�擾
            String disp_pict = extras != null ? extras.getString("SELECTED_PICT") : "";

            // ImageView�I�u�W�F�N�g�擾
            ImageView image = (ImageView) getActivity().findViewById(R.id.iv_fruit);

            // �\������摜�̐ݒ�
            if ( disp_pict.equals("Apple") ) {
                image.setImageResource(R.drawable.apple);
            }
            else if ( disp_pict.equals("Banana") ) {
                image.setImageResource(R.drawable.banana);
            }
            else if ( disp_pict.equals("Grape") ) {
                image.setImageResource(R.drawable.grape);
            }
        }

        // �N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v) {
                // �A�N�e�B�r�e�B�I��(��ʃN���[�Y)
                getActivity().finish();
            }
        }
    }
}
