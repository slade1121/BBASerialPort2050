package com.phoenixcontact.BBASerialPort.comm;

import com.phoenixcontact.BBASerialPort.BBBASerialPortDevice;
import com.tridium.modbusCore.BModbusDevice;
import com.tridium.modbusCore.messages.ModbusOutputStream;
import com.tridium.modbusCore.messages.ModbusWriteRequest;

import javax.baja.nre.util.ByteArrayUtil;
import javax.baja.nre.util.TextUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class SerialResponse extends SerialMessage{

    public int exceptionCode;
    public int startAddress;
    public int numberPoints;
    public int byteCount;
    public byte[] data = new byte[255];

    public SerialResponse(BBBASerialPortDevice serialDevice) {
        super(serialDevice);
    }

    public SerialResponse(BBBASerialPortDevice serialDevice, SerialWriteRequest writeRequest) {
        super(serialDevice);
        this.deviceAddress = writeRequest.deviceAddress;
        this.startAddress = writeRequest.startAddress;
        this.numberPoints = writeRequest.numberPoints;
        this.byteCount = writeRequest.byteCount;
        this.data = new byte[writeRequest.data.length];

        for(int i = 0; i < writeRequest.data.length; ++i) {
            this.data[i] = writeRequest.data[i];
        }

    }

    public final void writeRtu(SerialOutputStream out) throws IOException {
        SerialOutputStream serialOut = this.formatBaseMessage();
        serialOut.writeCRC();
        out.write(serialOut.toByteArray());
    }

    protected SerialOutputStream formatBaseMessage() throws IOException {
        SerialOutputStream out = new SerialOutputStream();
        out.write((byte)this.deviceAddress);
        out.write(this.data);
        return out;
    }

    public boolean isError() {
        return this.exceptionCode != 0;
    }

    public String getString(int length) {
        if (length > this.byteCount) {
            throw new IllegalArgumentException("Incomplete string returned: ");
        } else {
            byte[] byteArray = new byte[this.byteCount];

            for(int i = 0; i < this.byteCount; ++i) {
                byteArray[i] = this.data[i];
            }

            return new String(byteArray, StandardCharsets.UTF_8);
        }
    }

    public boolean getBinary(int index) {
        if (index >= this.numberPoints) {
            throw new IllegalArgumentException("Point not returned: " + index);
        } else {
            int byteOffset = index / 8;
            int bitOffset = index % 8;
            if (byteOffset >= this.byteCount) {
                throw new IllegalArgumentException("Point not returned: " + index);
            } else {
                return (this.data[byteOffset] & 1 << bitOffset) != 0;
            }
        }
    }

    public long getRegister(int index, int dataSize) {
        return this.getRegister(index, dataSize, false);
    }

    public long getRegister(int index, int dataSize, boolean bigEndian) {
        return this.getRegister(index, dataSize, bigEndian, false);
    }

    public long getRegister(int index, int dataSize, boolean bigEndian, boolean signed) {
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
                        int iValue = this.data[byteOffset + 1] & 255 | (this.data[byteOffset] & 255) << 8;
                        if (signed && iValue >= 32768) {
                            iValue |= -65536;
                        }

                        return (long)iValue;
                    case 4:
                        if (bigEndian) {
                            value = (long)(this.data[byteOffset + 3] & 255 | (this.data[byteOffset + 2] & 255) << 8 | (this.data[byteOffset + 1] & 255) << 16 | (this.data[byteOffset + 0] & 255) << 24);
                        } else {
                            value = (long)(this.data[byteOffset + 1] & 255 | (this.data[byteOffset + 0] & 255) << 8 | (this.data[byteOffset + 3] & 255) << 16 | (this.data[byteOffset + 2] & 255) << 24);
                        }

                        if (signed) {
                            return value;
                        }

                        value &= 4294967295L;
                        return value;
                    default:
                        throw new IllegalArgumentException("Unsupported data size: " + dataSize);
                }
            }
        }
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

    public String toDebugString() {
        StringBuffer sb = new StringBuffer();
        String comTypeString = "RTU";
        sb.append("Modbus " + comTypeString + " Response Message = " + TextUtil.getClassName(this.getClass()));
        sb.append("\n  Tag = " + this.getTag());
        sb.append("\n  Modbus Device Address = " + (this.deviceAddress & 255));
        sb.append("\n  Modbus Exception Code = " + this.exceptionCode);
        sb.append("\n  Modbus Data Starting Address = " + this.startAddress);
        sb.append("\n  Modbus Number of Data Points = " + this.numberPoints);
        sb.append("\n  Modbus Byte Count = " + this.byteCount);
        sb.append("\n  Modbus Transaction ID = " + this.transactionIdentifier);
        sb.append("\n  Modbus Data = " + ByteArrayUtil.toHexString(this.data));

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            this.write(out);
            sb.append("\n  Raw Bytes = " + ByteArrayUtil.toHexString(out.toByteArray()));
        } catch (Exception var4) {
        }

        return sb.toString();
    }

    public String getExceptionString() {
        switch(this.exceptionCode) {
            case -8:
                return "disabled";
            case -7:
                return "fault";
            case -6:
                return "down";
            case -5:
                return "lrc error";
            case -4:
            default:
                return "other error";
            case -3:
                return "unknown";
            case -2:
                return "ok not active";
            case -1:
                return "crc error";
            case 0:
                return "ok";
            case 1:
                return "illegal function";
            case 2:
                return "illegal data address";
            case 3:
                return "illegal data value";
            case 4:
                return "slave device failure";
            case 5:
                return "acknowledge";
            case 6:
                return "slave device busy";
            case 7:
                return "negative acknowledge";
            case 8:
                return "memory parity error";
            case 9:
                return "device timeout";
            case 10:
                return "gateway path unavailable";
            case 11:
                return "gateway target device failed to respond";
        }
    }

}
