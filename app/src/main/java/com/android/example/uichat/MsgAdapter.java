package com.android.example.uichat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 果粒橙 on 2017.12.15.
 *
 * RecyclerView的适配器类
 * ①定义内部类ViewHolder继承自RecyclerView.ViewHolder。
 * ②ViewHolder构造函数要传入View参数，即RecyclerView子项最外层布局，通过findViewById()
 * 获取布局中的leftLayout，rightLayout，leftMsg，rightMsg。
 * ③MsgAdapter有个构造函数，用于把要展示的数据传进去，赋值给一个全局变量mFruitList。
 * ④重写onCreateViewHolder(将msg_item布局加载进来并传入接下来创建ViewHolder实例中，返回)、
 * onBindViewHolder(用于对子项的数据进行布局，会在每个子项被滚动到屏幕内的时候执行，
 * 通过position参数得到当前项的msg实例，再将数据设置到ViewHolder的内容去，
 * 再对消息类型的判断：收到则显示左边消息布局，发出则显示右边消息布局)、
 * getItemCount(直接返回数据源的长度)。
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg);
            rightMsg = (TextView) view.findViewById(R.id.right_msg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.msg_item, parent,false);//false必须有，否则启动失败
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVER){
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }else if (msg.getType() == Msg.TYPE_SENT){
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }
}
