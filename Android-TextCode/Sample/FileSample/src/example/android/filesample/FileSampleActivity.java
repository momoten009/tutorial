package example.android.filesample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FileSampleActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_file_sample);

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
        getMenuInflater().inflate(R.menu.file_sample, menu);
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

        // �t�@�C����
        private static final String FILE_NAME = "FileSampleFile";

        public PlaceholderFragment() {
        }

        // onCreateView���\�b�h(�t���O�����g�����\���C�x���g�n���h��)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // �t���O�����g�ݒ���擾
            View rootView = inflater.inflate(R.layout.fragment_file_sample, container, false);

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

                // ���b�Z�[�W�\���p
                String str  = "";
                TextView label = (TextView)getActivity().findViewById(R.id.tv_message);

                // �ۑ��{�^���������ꂽ�ꍇ
                if(tag.equals("save")){
                    // ���͏��擾
                    EditText name = (EditText)getActivity().findViewById(R.id.et_name);
                    EditText score = (EditText)getActivity().findViewById(R.id.et_score);

                    // �t�@�C���Ƀf�[�^�ۑ�
                    try{
                        FileOutputStream stream
                            = getActivity().openFileOutput(FILE_NAME, MODE_APPEND);
                        BufferedWriter out
                            = new BufferedWriter(new OutputStreamWriter(stream));

                        String hantei = "�s���i";
                        if(Integer.parseInt(score.getText().toString()) >= 210){
                            hantei = "���i";
                        }

                        out.write(name.getText().toString() + "," +
                                    score.getText().toString() + "," +
                                    hantei);
                        out.newLine();
                        out.close();
                        str  = "�ۑ����܂����I";
                    }catch(Exception e){
                        str  = "�f�[�^�ۑ��Ɏ��s���܂����I";
                    }

                // �\���{�^���������ꂽ�ꍇ
                }else if(tag.equals("display")){
                    // �t�@�C������f�[�^�擾
                    try{
                        FileInputStream stream
                            = getActivity().openFileInput(FILE_NAME);
                        BufferedReader in
                            = new BufferedReader(new InputStreamReader(stream));

                        String line = "";
                        while((line = in.readLine())!=null){
                            str += line + "\n";
                        }
                        in.close();
                    }catch(Exception e){
                        str  = "�f�[�^�擾�Ɏ��s���܂����I";
                    }

                // �폜�{�^���������ꂽ�ꍇ
                }else if(tag.endsWith("delete")){
                    // �t�@�C���폜
                    try{
                        getActivity().deleteFile(FILE_NAME);
                        str = "�폜���܂����I";
                    }catch(Exception e){
                        str  = "�t�@�C���폜�Ɏ��s���܂����I";
                    }
                }
                // ���b�Z�[�W�\��
                label.setText(str);
            }
        }
    }
}
