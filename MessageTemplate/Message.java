package MessageTemplate;

import FSM.IMessage;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class Message implements IMessage, Cloneable {
    private int messageId;
    private int toId;
    private int fromId;
    private String toAddress; //ip:port
    private String fromAddress;
    private String param;
    private String tito;

    //private String lokacijaChat;
    private String time;


    public Message(){

    }
    public Message(int messageId) {
        this.messageId = messageId;
    }
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
    @Override
    public int getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public void setTime(String vrijeme) {
        this.time = time;
    }

    @Override
    public int getToId() {
        return toId;
    }

    @Override
    public void setToId(int toId) {
        this.toId = toId;
    }

    @Override
    public int getFromId() {
        return fromId;
    }

    @Override
    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    @Override
    public String getToAddress() {
        return toAddress;
    }

    @Override
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    @Override
    public String getFromAddress() {
        return fromAddress;
    }

    @Override
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Override
    public void parseTransportMessage(byte[] messageData, int length) {
        ByteArrayInputStream input = new ByteArrayInputStream(messageData);
        XMLDecoder decoder = new XMLDecoder(input);
        Message m = (Message)decoder.readObject();
        decoder.close();
        messageId = m.getMessageId();
        fromAddress = m.getFromAddress();
        param = m.getParam();
    }

    @Override
    public byte[] buildTransportMessage() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(output);
        encoder.writeObject(this);
        encoder.close();
        return output.toByteArray();
    }

    @Override
    public boolean equals(IMessage message) {
        return message.getMessageId() == messageId;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Message clone = (Message) super.clone();
        return clone;
    }
}
