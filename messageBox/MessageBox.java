package me.stelios.messageBox;

public class MessageBox {

    public boolean completed;
    public Type messageType;
    public String[] text;
    public String UID;

    public MessageBox(Type messageType, String UID) {
        this.messageType = messageType;
        this.UID = UID;

        completed = false;
    }

}
