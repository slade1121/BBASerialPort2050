package com.phoenixcontact.BBASerialPort.comm;

import com.phoenixcontact.BBASerialPort.BBBASerialPortDevice;
import com.tridium.modbusCore.BModbusDevice;
import com.tridium.modbusCore.messages.ModbusMessage;
import com.tridium.modbusCore.messages.ModbusOutputStream;

import javax.baja.nre.util.ByteArrayUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SerialWriteRequest extends SerialMessage {

    public int byteCount;
    public byte[] data;

    public SerialWriteRequest(BBBASerialPortDevice serilaDevice) {
        super(serilaDevice);
    }

    public SerialWriteRequest(BBBASerialPortDevice serilaDevice, int addr, int start, int count, byte[] data) {
        super(serilaDevice);
        this.deviceAddress = addr;
        this.startAddress = start;
        this.numberPoints = count;
        this.data = data;
    }

    public float getFloat(int index, int dataSize, boolean bigEndian) {
        if (index >= this.numberPoints) {
            throw new IllegalArgumentException("Point not returned: " + index);
        } else {
            int byteOffset = index * dataSize;
            if (byteOffset >= this.byteCount) {
                throw new IllegalArgumentException("Point not returned: " + index);
            } else {
                long bits;
                if (bigEndian) {
                    bits = (long)(this.data[byteOffset + 3] & 255 | (this.data[byteOffset + 2] & 255) << 8 | (this.data[byteOffset + 1] & 255) << 16 | (this.data[byteOffset + 0] & 255) << 24);
                } else {
                    bits = (long)(this.data[byteOffset + 1] & 255 | (this.data[byteOffset + 0] & 255) << 8 | (this.data[byteOffset + 3] & 255) << 16 | (this.data[byteOffset + 2] & 255) << 24);
                }

                return Float.intBitsToFloat((int)bits);
            }
        }
    }

    public int getRegister(int index, int dataSize, boolean bigEndian) {
        long value = 0L;
        if (index >= this.numberPoints) {
            throw new IllegalArgumentException("Point not returned: " + index);
        } else {
            int byteOffset = index * dataSize;
            if (byteOffset >= this.byteCount) {
                throw new IllegalArgumentException("Point not returned: " + index);
            } else {
                switch(dataSize) {
                    case 2:
                        return this.data[byteOffset + 1] & 255 | (this.data[byteOffset] & 255) << 8;
                    case 4:
                        if (bigEndian) {
                            value = (long)(this.data[byteOffset + 3] & 255 | (this.data[byteOffset + 2] & 255) << 8 | (this.data[byteOffset + 1] & 255) << 16 | (this.data[byteOffset + 0] & 255) << 24);
                        } else {
                            value = (long)(this.data[byteOffset + 1] & 255 | (this.data[byteOffset + 0] & 255) << 8 | (this.data[byteOffset + 3] & 255) << 16 | (this.data[byteOffset + 2] & 255) << 24);
                        }

                        value &= -1L;
                        return (int)value;
                    default:
                        throw new IllegalArgumentException("Unsupported data size: " + dataSize);
                }
            }
        }
    }

    public final void writeRtu(OutputStream out) throws IOException {
        SerialOutputStream serialOut = this.formatMessage();
        serialOut.writeCRC();
        out.write(serialOut.toByteArray());
    }

//    public final void writeTcp(OutputStream out) throws IOException {
//        byte[] msgArray = this.formatMessage().toByteArray();
//        ModbusOutputStream modOut = new ModbusOutputStream();
//        if (this.transactionIdentifier < 0) {
//            this.transactionIdentifier = getNextTransactionId(this.getMaxTransactionId());
//        }
//
//        modOut.write((byte)((this.transactionIdentifier & '\uff00') >> 8));
//        modOut.write((byte)(this.transactionIdentifier & 255));
//        modOut.write((byte)0);
//        modOut.write((byte)0);
//        int msgLen = msgArray.length;
//        modOut.write((byte)((msgLen & '\uff00') >> 8));
//        modOut.write((byte)(msgLen & 255));
//        modOut.write(msgArray);
//        out.write(modOut.toByteArray());
//    }
//
//    public final void writeAscii(OutputStream out) throws IOException {
//        SerialOutputStream modOut = this.formatMessage();
//        modOut.writeLRC();
//        out.write(modOut.toAsciiHexByteArray());
//    }

    private SerialOutputStream formatMessage() {
        SerialOutputStream out = new SerialOutputStream();
        out.write((byte)this.deviceAddress);
        out.write((byte)((this.startAddress & '\uff00') >> 8));
        out.write((byte)(this.startAddress & 255));
        this.byteCount = this.data.length;
        out.write((byte)this.byteCount);
        out.write(this.data, 0, this.byteCount);
        out.write((byte)((this.numberPoints & '\uff00') >> 8));
        out.write((byte)(this.numberPoints & 255));

        return out;
    }

    public String toDebugString() {
        StringBuffer sb = new StringBuffer();
        sb.append(super.toDebugString());
        sb.append("\n  Modbus Byte Count = " + this.byteCount);
        sb.append("\n  Modbus Data = " + ByteArrayUtil.toHexString(this.data));

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            this.write(out);
            sb.append("\n  Raw Bytes = " + ByteArrayUtil.toHexString(out.toByteArray()));
        } catch (Exception var3) {
        }

        return sb.toString();
    }

}
