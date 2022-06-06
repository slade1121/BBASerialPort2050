package com.phoenixcontact.BBASerialPort.comm;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.basicdriver.util.BBasicUnsolicitedReceive;

import javax.baja.nre.util.ByteArrayUtil;
import java.io.OutputStream;

public class SerialReceivedMessage extends ReceivedMessage {

    private byte[] data;
    private int len;
    private int transactionId = 0;

    public SerialReceivedMessage(byte[] data, int len) {
        this.data = data;
        this.len = len;
    }

    public byte[] getBytes() {
        return this.data;
    }

    public void setBytes(byte[] data) {
        this.data = data;
    }

    public int getLength() {
        return this.len;
    }

    public void setLength(int len) {
        this.len = len;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    private boolean unsolicited = false;
    private Object unsolicitedListenerCode;

    public SerialReceivedMessage() {
        this.unsolicitedListenerCode = BBasicUnsolicitedReceive.TYPE;
    }

    public void write(OutputStream out) {
    }

    public Message toResponse(ReceivedMessage resp) {
        return null;
    }

    public void setSuccessfulResponse(boolean b) {
        super.setSuccessfulResponse(b);
    }

    public boolean getUnsolicited() {
        return this.unsolicited;
    }

    public void setUnsolicited(boolean unsolicited) {
        this.unsolicited = unsolicited;
    }

    public void setUnsolicitedListenerCode(Object code) {
        this.unsolicitedListenerCode = code;
    }

    public Object getUnsolicitedListenerCode() {
        return this.unsolicitedListenerCode;
    }

    public String toDebugString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ReceivedMessage = " + this.toString());
        sb.append("\n  Tag = " + this.getTag());
        sb.append("\n  Known Unsolicited = " + this.getUnsolicited());
        sb.append("\n  Unsolicited Listener Code = " + this.getUnsolicitedListenerCode());
        return sb.toString();
    }

}
