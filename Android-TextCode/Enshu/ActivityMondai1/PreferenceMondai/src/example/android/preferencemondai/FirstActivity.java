package example.android.preferencemondai;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FirstActivity extends Activity { // Activity�N���X���p��

    // onCreate���\�b�h(��ʏ����\���C�x���g�n���h��)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // �X�[�p�[�N���X��onCreate���\�b�h�Ăяo��
        super.onCreate(savedInstanceState);
        // ���C�A�E�g�ݒ�t�@�C���̎w��
        setContentView(R.layout.activity_first);

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
        getMenuInflater().inflate(R.menu.first, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            // �{�^���I�u�W�F�N�g�擾
            Button button1=(Button)rootView.findViewById(R.id.bt_send);
            // �{�^���I�u�W�F�N�g�ɃN���b�N���X�i�[�ݒ�
            button1.setOnClickListener(new ButtonClickListener());

            return rootView;
        }

        // �N���b�N���X�i�[��`
        private class ButtonClickListener implements OnClickListener {
            // onClick���\�b�h(�{�^���N���b�N���C�x���g�n���h��)
            public void onClick(View v) {
                // ���O�擾
                EditText name = (EditText)getActivity().findViewById(R.id.et_name);

                // �Z���擾
                EditText address = (EditText)getActivity().findViewById(R.id.et_address);

                // ���N�����擾
                Spinner month = (Spinner)getActivity().findViewById(R.id.sp_month);
                Spinner day = (Spinner)getActivity().findViewById(R.id.sp_day);

                // ���ʎ擾
                RadioGroup radio = (RadioGroup)getActivity().findViewById(R.id.rg_gender);
                RadioButton radiobutton =
                    (RadioButton)getActivity().findViewById(radio.getCheckedRadioButtonId());


                // ��]���i�擾
                CheckBox applecheck = (CheckBox)getActivity().findViewById(R.id.cb_apple);
                CheckBox orangecheck = (CheckBox)getActivity().findViewById(R.id.cb_orange);
                CheckBox peachcheck = (CheckBox)getActivity().findViewById(R.id.cb_peach);

                // �������ʎ擾
                EditText appleqty = (EditText)getActivity().findViewById(R.id.et_apple);
                EditText orangeqty = (EditText)getActivity().findViewById(R.id.et_orange);
                EditText peachqty = (EditText)getActivity().findViewById(R.id.et_peach);

                // �C���e���g�̐���(�Ăяo���N���X�̎w��)
                Intent intent = new Intent(getActivity(), SecondActivity.class);

                // ���̓f�[�^���C���e���g�ɐݒ�
                intent.putExtra("NAME", name.getText().toString());
                intent.putExtra("ADDRESS", address.getText().toString());
                intent.putExtra("MONTH", month.getSelectedItem().toString());
                intent.putExtra("DAY", day.getSelectedItem().toString());
                intent.putExtra("GENDAR", radiobutton.getText().toString());
                if(applecheck.isChecked())
                    intent.putExtra("APPLE", appleqty.getText().toString());
                if(orangecheck.isChecked())
                    intent.putExtra("ORANGE", orangeqty.getText().toString());
                if(peachcheck.isChecked())
                    intent.putExtra("PEACH", peachqty.getText().toString());

                // ���̃A�N�e�B�r�e�B�̋N��
                startActivity(intent);
            }
        }
    }
}
