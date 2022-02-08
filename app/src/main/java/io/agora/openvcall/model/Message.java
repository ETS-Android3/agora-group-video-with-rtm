package io.agora.openvcall.model;

public class Message {
    private User mSender;

    private String mContent;

    private byte[] bytes;

    private int mType;

    public Message(int type, User sender, String content) {
        mType = type;
        mSender = sender;
        mContent = content;
    }

    public Message(User sender, String content) {
        this(0, sender, content);
    }

    public Message(User sender, byte[] bytes) {
        this.bytes = bytes;
        this.mType = 1;
        this.mSender = sender;
    }

    public User getSender() {
        return mSender;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getContent() {
        return mContent;
    }

    public int getType() {
        return mType;
    }

    public static final int MSG_TYPE_TEXT = 1; // CHANNEL
}
