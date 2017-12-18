package com.android.example.uichat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/*
 *为RecyclerView初始化一些数据，给发送按钮加入事件响应。
 * ①在initMsgs()方法中初始化几条数据用于在RecyclerView中显示。
 * ②在发送按钮的点击事件获取EditText中的内容，如果内容不为null，
 * 则创建一个新的Msg对象并添加到msgList列表中。
 * ③调用适配器的notifyItemInserted()方法用于通知列表有新的数据插入，
 * 新增消息才能在RecyclerView中显示。
 * ④调用RecyclerView的scrollToPosition()方法将显示的数据定位到最后一行，保证能看到最后消息。
 * ⑤调用EditText的setText()方法将输入内容清空。
 */

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();//初始化消息数据
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        //指定RecyclerView的布局方式（线性布局）
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        //将数据传入MsgAdapter的构造函数中，并完成适配器设置
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取EditText中的内容
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    //当有新消息时，刷新RecyclerView中的显示
                    adapter.notifyItemInserted(msgList.size()-1);
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    //清除输入框中的内容
                    inputText.setText("");
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello World!",Msg.TYPE_RECEIVER);
        msgList.add(msg1);
        Msg msg2 = new Msg("I'm fine,thank!",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("Are you ok?",Msg.TYPE_SENT);
        msgList.add(msg3);
        Msg msg4 = new Msg("What are you 弄啥嘞？",Msg.TYPE_RECEIVER);
        msgList.add(msg4);
    }
}
