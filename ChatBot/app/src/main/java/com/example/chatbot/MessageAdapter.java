package com.example.chatbot;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbot.Message;

import java.util.List;



import android.os.Bundle;
import android.util.Log;
import android.widget.Button ;

import org.w3c.dom.Text;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    // create the layout of the message using item_message, and put it in the recyclerview
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    // put the inteface at the current message sent
    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.bind(message);
        if (message.getSendByUser()) {
            // Set layout params to align the message to the right side
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMarginStart(0); // Adjust this value as needed for spacing
            holder.itemView.setLayoutParams(layoutParams);
        } else {
            // Reset layout params if the message is from the user
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMarginStart(500); // Reset margin to default
            holder.itemView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    // show each item of the table, in the recyclerView
    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTextView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }

        public void bind(Message message) {
            messageTextView.setText(message.getText());
        }
    }
}
