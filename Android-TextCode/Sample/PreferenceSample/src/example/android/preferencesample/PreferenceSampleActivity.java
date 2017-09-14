package example.android.preferencesample;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class PreferenceSampleActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_preference_sample);

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
        getMenuInflater().inflate(R.menu.preference_sample, menu);
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

        // �v���t�@�����X�t�@�C����
        private static final String FILE_NAME = "PreferenceSampleFile";

        public PlaceholderFragment() {
        }

        // onCreateView���\�b�h(�t���O�����g�����\���C�x���g�n���h��)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // �t���O�����g�ݒ���擾
            View rootView = inflater.inflate(R.layout.fragment_preference_sample, container, false);

            // �ۑ��{�^���̃N���b�N���X�i�[�ݒ�
            Button saveBtn = (Button)rootView.findViewById(R.id.bt_save);
            saveBtn.setTag("save");
            saveBtn.setOnClickListener(new ButtonClickListener());

            // �\���{�^���̃N���b�N���X�i�[�ݒ�
            Button readBtn = (Button)rootView.findViewById(R.id.bt_display);
            readBtn.setTag("display");
            readBtn.setOnClickListener(new ButtonClickListener());

            // �폜�{�^���̃N���b�N���X�i�[�ݒ�
            Button delBtn = (Button)rootView.findViewById(R.id.bt_delete);
            delBtn.setTag("delete");
            delBtn.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // �N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v){
                // �^�O�̎擾
                String tag = (String)v.getTag();

                // �v���t�@�����X�I�u�W�F�N�g�擾
                SharedPreferences preference
                    = getActivity().getSharedPreferences(FILE_NAME, MODE_PRIVATE);

                // TextView�擾
                TextView message = (TextView)getActivity().findViewById(R.id.tv_message);

                // �ۑ��{�^���������ꂽ�ꍇ
                if(tag.equals("save")){
                    // �v���t�@�����X�̕ҏW�p�I�u�W�F�N�g�擾
                    SharedPreferences.Editor editor = preference.edit();

                    // �I�����ꂽ�t�H���g�擾
                    Spinner fontarray = (Spinner)getActivity().findViewById(R.id.sp_font);

                    String font = (String)fontarray.getSelectedItem();

                    // �I�����ꂽ�X�^�C���擾
                    CheckBox italic = (CheckBox)getActivity().findViewById(R.id.cb_italic);
                    CheckBox bold = (CheckBox)getActivity().findViewById(R.id.cb_bold);
                    String check = "�ʏ�";
                    if(italic.isChecked()){
                        check = "�Α�";
                    }
                    if(bold.isChecked()){
                        if(italic.isChecked()){
                            check += "|����";
                        }else{
                            check = "����";
                        }
                    }

                    // �v���t�@�����X�t�@�C���ɕۑ�
                    editor.putString("FONT", font);
                    editor.putString("STYLE", check);
                    editor.commit();

                    // ���b�Z�[�W�\��
                    message.setText("�ۑ����܂����I");

                // �\���{�^���������ꂽ�ꍇ
                }else if(tag.equals("display")){
                    // �ۑ��f�[�^�擾
                    String font = preference.getString("FONT", "������܂���");
                    String style = preference.getString("STYLE", "������܂���");

                    // ���b�Z�[�W�\���p�t�H���g�A�X�^�C���ݒ�
                    Typeface fonttype = Typeface.DEFAULT;
                    if(font.equals("������")){
                        fonttype = Typeface.SERIF;
                    }else if(font.equals("�S�V�b�N��")){
                        fonttype = Typeface.SANS_SERIF;
                    }else if(font.equals("�����t�H���g")){
                        fonttype = Typeface.MONOSPACE;
                    }
                    int styleflg = Typeface.NORMAL;
                    if(style.equals("�Α�")){
                        styleflg = Typeface.ITALIC;
                    }else if(style.equals("����")){
                        styleflg = Typeface.BOLD;
                    }else if(style.equals("�Α�|����")){
                        styleflg = Typeface.BOLD_ITALIC;
                    }

                    // ���b�Z�[�W�\��
                    message.setText("Preference Sample\n" +
                            "�t�H���g�F" + font +
                            "\n�X�^�C���F" + style);
                    message.setTypeface(Typeface.create(fonttype, styleflg));

                // �폜�{�^���������ꂽ�ꍇ
                }else if(tag.endsWith("delete")){
                    // �v���t�@�����X�̕ҏW�p�I�u�W�F�N�g�擾
                    SharedPreferences.Editor editor = preference.edit();

                    // ���ׂĂ̕ۑ��f�[�^�폜
                    editor.clear();
                    editor.commit();

                    // ���b�Z�[�W�\��
                    message.setText("�폜���܂����I");
                }
            }
        }
    }
}
