package example.android.filemondai;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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

        // �t�@�C����
        private static final String FILE_NAME = "FileMondai";

        // ���̓f�[�^
        String name = "";
        String address = "";
        String month = "";
        String day = "";
        String gendar = "";
        String apple = "";
        String orange = "";
        String peach = "";

        public PlaceholderFragment() {
        }

        // onCreateView���\�b�h(�t���O�����g�����\���C�x���g�n���h��)
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // �t���O�����g�ݒ���擾
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);

            // �m�F�{�^���̃N���b�N���X�i�[�ݒ�
            Button confirmbutton = (Button)rootView.findViewById(R.id.bt_confirm);
            confirmbutton.setTag("confirm");
            confirmbutton.setOnClickListener(new ButtonClickListener());

            // �߂�{�^���̃N���b�N���X�i�[�ݒ�
            Button backbutton = (Button)rootView.findViewById(R.id.bt_back);
            backbutton.setTag("back");
            backbutton.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // onStart���\�b�h(�t���O�����g�\���O�C�x���g�n���h��)
        @Override
        public void onStart() {
            // �X�[�p�[�N���X��onStart���\�b�h�Ăяo��
            super.onStart();

            // �C���e���g�̕t�����擾
            Bundle extra = getActivity().getIntent().getExtras();

            // �t����񂩂���̓f�[�^�擾
            name = extra.getString("NAME");
            address = extra.getString("ADDRESS");
            month = extra.getString("MONTH");
            day = extra.getString("DAY");
            gendar = extra.getString("GENDAR");
            apple = extra.getString("APPLE");
            orange = extra.getString("ORANGE");
            peach = extra.getString("PEACH");

            // �o�͗p�e�L�X�g�I�u�W�F�N�g�擾
            TextView tvName = (TextView)getActivity().findViewById(R.id.tv_name);
            TextView tvAddress = (TextView)getActivity().findViewById(R.id.tv_address);
            TextView tvMonth = (TextView)getActivity().findViewById(R.id.tv_month);
            TextView tvDay = (TextView)getActivity().findViewById(R.id.tv_day);
            TextView tvGendar = (TextView)getActivity().findViewById(R.id.tv_gender);
            TextView tvApple = (TextView)getActivity().findViewById(R.id.tv_apple);
            TextView tvOrange = (TextView)getActivity().findViewById(R.id.tv_orange);
            TextView tvPeach = (TextView)getActivity().findViewById(R.id.tv_peach);

            // �o�͗p�e�L�X�g�I�u�W�F�N�ɓ��̓f�[�^�ݒ�
            tvName.setText(name);
            tvAddress.setText(address);
            tvMonth.setText(month);
            tvDay.setText(day);
            tvGendar.setText(gendar);
            if(apple != null)tvApple.setText(apple);
            if(orange != null)tvOrange.setText(orange);
            if(peach != null)tvPeach.setText(peach);
        }

        // �{�^���N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v) {
                // �^�O�̎擾
                String tag = (String)v.getTag();

                // �m�F�{�^���������ꂽ�ꍇ
                if(tag.equals("confirm")){
                    // �t�@�C���ɕۑ�
                    try{
                        FileOutputStream stream
                            = getActivity().openFileOutput(FILE_NAME, MODE_APPEND);
                        BufferedWriter out
                            = new BufferedWriter(new OutputStreamWriter(stream));

                        out.write(name + "," +
                            address + "," +
                            month + "/" + day + "," +
                            gendar + "," +
                            apple + "," +
                            orange + "," +
                            peach);
                        out.newLine();
                        out.close();

                    }catch(Exception e){
                        Log.e("FILE_ERROR", "�t�@�C���������݂Ɏ��s���܂���");
                    }

                    // �C���e���g�̐���(�Ăяo���N���X�̎w��)
                    Intent intent = new Intent(getActivity(), ThirdActivity.class);

                    // ���̃A�N�e�B�r�e�B�̋N��
                    startActivity(intent);

                // �߂�{�^���������ꂽ�ꍇ
                }else if(tag.endsWith("back")){
                    // ��ʂ��N���[�Y
                    getActivity().finish();
                }
            }
        }
    }
}
