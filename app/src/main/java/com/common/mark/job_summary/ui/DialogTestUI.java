package com.common.mark.job_summary.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.common.mark.job_summary.R;

import java.util.ArrayList;


/**
 * Created by mark on 2017/3/3.
 */

public class DialogTestUI extends Activity {
    private static final int FINISH = 1 << 0;
    private int choice;
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_dialogtest);

    }

    /**
     * 普通的Dialog
     *
     * @param view
     */
    public void dialog1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.icon);
        builder.setTitle("普通Dialog");
        builder.setMessage("你确定要关闭?");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogTestUI.this, "即将关闭", Toast.LENGTH_LONG).show();
                //dialog.dismiss();
            }
        });

        builder.create().show();
    }

    /**
     * 列表Dialog
     *
     * @param view
     */
    public void dialog2(View view) {
        final String[] items = new String[]{"红色", "黄色", "蓝色"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.icon);
        builder.setTitle("列表Dialog");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogTestUI.this, "你点击了" + items[which], Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });

        builder.create().show();
    }

    /**
     * 单选Dialog
     *
     * @param view
     */
    public void dialog3(View view) {
        final String[] items = new String[]{"红色", "黄色", "蓝色"};
        choice = -1;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.icon);
        builder.setTitle("单选Dialog");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (choice != -1) {
                    Toast.makeText(DialogTestUI.this, "你点击了" + items[choice], Toast.LENGTH_SHORT).show();
                    //dialog.dismiss();
                }

            }
        });

        builder.create().show();
    }

    /**
     * 多选Dialog
     *
     * @param view
     */
    public void dialog4(View view) {
        final ArrayList<Integer> choices = new ArrayList();
        final String[] items = new String[]{"红色", "黄色", "蓝色"};
        // 设置默认选中的选项，全为false默认均未选中
        final boolean checkedItems[] = {false, false, false};
        choices.clear();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.drawable.icon);
        builder.setTitle("多选Dialog");
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(which);
                } else {
                    choices.remove(which);
                }
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String str = "";
                int size = choices.size();

                for (int i = 0; i < size; i++) {
                    str += items[choices.get(i)] + "";
                }
                Toast.makeText(DialogTestUI.this, "你点击了" + str, Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();

    }

    /**
     * 等待Dialog
     *
     * @param view
     */
    public void dialog5(View view) {

        ProgressDialog dialog = new ProgressDialog(this);

        dialog.setIcon(R.drawable.icon);
        dialog.setTitle("等待Dialog");
        dialog.setMessage("等待中...");
        dialog.setIndeterminate(true);
        // dialog.setCancelable(false);// false,点击屏幕或其他，不会退出对话框

        dialog.show();

    }

    /**
     * 进度条Dialog
     *
     * @param view
     */
    public void dialog6(View view) {
        final int MAX = 100;
        final ProgressDialog dialog = new ProgressDialog(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == FINISH) {
                    Toast.makeText(DialogTestUI.this, "加载完成", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            }
        };

        dialog.setIcon(R.drawable.icon);
        dialog.setTitle("进度条Dialog");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setProgress(MAX);
        dialog.show();

        // 模拟进度增加的过程
        new Thread(new Runnable() {
            @Override
            public void run() {
                int process = 0;
                while (process < MAX) {
                    try {
                        Thread.sleep(100);
                        process++;
                        dialog.setProgress(process);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (process == MAX) {
                    Message msg = Message.obtain();
                    msg.what = FINISH;

                    mHandler.sendMessage(msg);
                }

            }
        }).start();
    }

    /**
     * 编辑Dialog
     *
     * @param view
     */
    public void dialog7(View view) {

        final EditText editText = new EditText(DialogTestUI.this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon);
        builder.setTitle("编辑Dialog");
        builder.setView(editText);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(DialogTestUI.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();

    }

    /**
     * 自定义Dialog
     *
     * @param view
     */
    public void dialog8(View view) {

        final View dialogView = LayoutInflater.from(DialogTestUI.this).inflate(R.layout.item_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义Dialog");
        builder.setIcon(R.drawable.icon);
        builder.setView(dialogView);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) dialogView.findViewById(R.id.item_dialog_edittext);
                Toast.makeText(DialogTestUI.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

}
