package io.agora.openvcall.ui.layout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import io.agora.openvcall.R;
import io.agora.openvcall.model.Message;

public class InChannelMessageListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Message> mMsgList;

    private final LayoutInflater mInflater;

    public InChannelMessageListAdapter(Activity activity, ArrayList<Message> list) {
        mInflater = activity.getLayoutInflater();
        mMsgList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            return new ImageMessageHodler(mInflater.inflate(R.layout.in_channel_message, parent, false));
        }else {
            return new TextMessageHolder(mInflater.inflate(R.layout.image_message,parent,false));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mMsgList.get(position).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message msg = mMsgList.get(position);
        if (msg.getType()==1){
            ImageMessageHodler imageMessageHodler = (ImageMessageHodler) holder;
            imageMessageHodler.mMsgContent.setText(msg.getSender().name);
            ImageView imageView = new ImageView(mInflater.getContext());
            byte[] thumbnail = msg.getBytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(thumbnail, 0, thumbnail.length);
            imageView.setImageBitmap(bitmap);
            if (imageMessageHodler.container.getChildCount() <2) {
                imageMessageHodler.container.addView(imageView);
            }
        }else {
            TextMessageHolder textMessageHolder = (TextMessageHolder) holder;
            String name = msg.getSender().name;
            textMessageHolder.mMsgContent.setText(msg.getSender().name + ": " + msg.getContent());
            if (TextUtils.isEmpty(name)) {
                holder.itemView.setBackgroundResource(R.drawable.rounded_bg_blue);
            } else {
                holder.itemView.setBackgroundResource(R.drawable.rounded_bg);
            }
        }

    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    @Override
    public long getItemId(int position) {
        return mMsgList.get(position).hashCode();
    }

    public class TextMessageHolder extends RecyclerView.ViewHolder {
        TextView mMsgContent;

        TextMessageHolder(View v) {
            super(v);
            mMsgContent = (TextView) v.findViewById(R.id.msg_content);
        }
    }

    public class ImageMessageHodler extends RecyclerView.ViewHolder{
        TextView mMsgContent;
        LinearLayout container;

        public ImageMessageHodler(@NonNull View v) {
            super(v);
            mMsgContent = (TextView) v.findViewById(R.id.msg_content);
            container = ((LinearLayout) v);
        }
    }
}
