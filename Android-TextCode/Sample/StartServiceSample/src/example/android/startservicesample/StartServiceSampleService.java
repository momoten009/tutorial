package example.android.startservicesample;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class StartServiceSampleService extends Service {
    // Timer�I�u�W�F�N�g
    private Timer timer = null;
    // �o�ߎ���
    private int countTime;
    // �I������
    private int stopTime;
    // �n���h���p�T�[�r�X�I�u�W�F�N�g
    private static Service serviceObj;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    // onCreate���\�b�h(�T�[�r�X�����N���C�x���g�n���h��)
    @Override
    public void onCreate() {
        // �g�[�X�g�\��
        Toast.makeText(this,
            "�T�[�r�X���N�����܂��B",
            Toast.LENGTH_SHORT).show();

        // �^�C�}�[�ƌo�ߎ��ԏ�����
        timer = new Timer();
        countTime = 0;

        // �n���h���p�T�[�r�X�I�u�W�F�N�g��ێ�
        serviceObj = this;
    }

    // onStartCommand���\�b�h(�T�[�r�X�J�n�C�x���g�n���h��)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // �g�[�X�g�\��
        Toast.makeText(this,
            "�T�[�r�X���J�n���܂��B",
            Toast.LENGTH_SHORT).show();

        // �^�C�}�[�ݒ�(10�b���Ƃ�run���\�b�h�Ăяo��)
        timer.schedule(task, 10000, 10000);

        // �I�����Ԏ擾
        Bundle bundle = intent.getExtras();
        stopTime = Integer.parseInt(bundle.getString("STOPTIME"));

        // �������f(�����I��)���ɏ����̍ĊJ���@�ɂ��Ďw��
        return START_NOT_STICKY;
    }

    // onDestroy���\�b�h(�T�[�r�X�I���C�x���g�n���h��)
    @Override
    public void onDestroy() {
        // �g�[�X�g�\��
        Toast.makeText(this,
            "�T�[�r�X���I�����܂��B",
            Toast.LENGTH_SHORT).show();

        // �^�C�}�[�ݒ����
        timer.cancel();
        timer.purge();
    }

    // �n���h���[����
    private static Handler handler = new Handler(){
        // ���b�Z�[�W�\��
        public void handleMessage(Message msg) {
            Toast.makeText(serviceObj,
                (String)msg.obj,
                Toast.LENGTH_SHORT).show();
        }
    };

    // TimerTask�I�u�W�F�N�g����
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            // 10�b���ƂɃJ�E���g�A�b�v
            countTime += 10;

            if(countTime / 60 == stopTime){
                // �T�[�r�X�I��
                stopSelf();
            }else{
                // handler�Ƀ��b�Z�[�W�𑗐M
                handler.sendMessage(
                    Message.obtain(handler,
                        0,
                        countTime / 60 +
                        "��"+
            countTime % 60+"�b�o�߂��܂����I"));
            }
        }
    };
}