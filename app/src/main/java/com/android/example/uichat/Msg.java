package com.android.example.uichat;

/**
 * Created by 果粒橙 on 2017.12.14.
 *
 * 定义消息的实体类
 * content表示消息内容
 * type表示消息类型，TYPE_RECEIVED表示收到的消息，TYPE_SENT表示发出的消息
 *
 */

public class Msg {
    public static final int TYPE_RECEIVER = 0;
    public static final int TYPE_SENT = 1;

    private String content;
    private int type;

    public Msg(String content,int type){
        this.content = content;
        this.type = type;
    }

    public String getContent(){
        return content;
    }

    public int getType(){
        return type;
    }
}
