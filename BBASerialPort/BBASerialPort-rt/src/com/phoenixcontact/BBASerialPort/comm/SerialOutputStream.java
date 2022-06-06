package com.phoenixcontact.BBASerialPort.comm;

import com.tridium.modbusCore.messages.ModbusMessage;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class SerialOutputStream extends ByteArrayOutputStream {

    public SerialOutputStream() {
    }

    public SerialOutputStream(int size) {
        super(size);
    }

    public void write(byte i) {
        super.write(i);
    }

    public int getCount() {
        return this.count;
    }

    public byte[] toAsciiHexByteArray() {
        StringBuffer sb = new StringBuffer();
        sb.append(':');

        for(int i = 0; i < this.count; ++i) {
            if (this.buf[i] >= 0 && this.buf[i] < 16) {
                sb.append("0" + Integer.toHexString(this.buf[i] & 255));
            } else {
                sb.append(Integer.toHexString(this.buf[i] & 255));
            }
        }

        sb.append('\r');
        sb.append('\n');
        String s = sb.toString().toUpperCase();
        byte[] ba = new byte[sb.length()];
        System.arraycopy(s.getBytes(StandardCharsets.US_ASCII), 0, ba, 0, ba.length);
        return ba;
    }

    public void writeInt(int i) {
        this.write(i & 255);
        this.write(i >> 8 & 255);
    }

    public void writeWord(int i) {
        this.write(i >> 8 & 255);
        this.write(i & 255);
    }

    public void writeLRC() {
        byte[] msg = this.toByteArray();
        this.write((byte) ModbusMessage.calcLRC(msg));
    }

    public void writeCRC() {
        byte[] msg = this.toByteArray();
        int crc = ModbusMessage.calcCRC(msg);
        this.write((byte)((crc & '\uff00') >> 8));
        this.write((byte)(crc & 255));
    }

}
