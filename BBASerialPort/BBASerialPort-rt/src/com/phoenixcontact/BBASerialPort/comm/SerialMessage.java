package com.phoenixcontact.BBASerialPort.comm;

import com.phoenixcontact.BBASerialPort.BBBASerialPortDevice;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.modbusCore.BModbusDevice;
import com.tridium.modbusCore.messages.ModbusInputStream;
import com.tridium.modbusCore.messages.ModbusReceivedMessage;
import com.tridium.modbusCore.messages.ModbusResponse;

import javax.baja.nre.util.ByteArrayUtil;
import javax.baja.nre.util.TextUtil;
import javax.baja.sys.BBoolean;
import javax.baja.sys.Property;
import java.io.IOException;
import java.io.OutputStream;

public class SerialMessage extends Message {

    static final char[] constCRCHi = new char[]{'\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@', '\u0001', 'À', '\u0080', 'A', '\u0001', 'À', '\u0080', 'A', '\u0000', 'Á', '\u0081', '@'};
    static final char[] constCRCLo = new char[]{'\u0000', 'À', 'Á', '\u0001', 'Ã', '\u0003', '\u0002', 'Â', 'Æ', '\u0006', '\u0007', 'Ç', '\u0005', 'Å', 'Ä', '\u0004', 'Ì', '\f', '\r', 'Í', '\u000f', 'Ï', 'Î', '\u000e', '\n', 'Ê', 'Ë', '\u000b', 'É', '\t', '\b', 'È', 'Ø', '\u0018', '\u0019', 'Ù', '\u001b', 'Û', 'Ú', '\u001a', '\u001e', 'Þ', 'ß', '\u001f', 'Ý', '\u001d', '\u001c', 'Ü', '\u0014', 'Ô', 'Õ', '\u0015', '×', '\u0017', '\u0016', 'Ö', 'Ò', '\u0012', '\u0013', 'Ó', '\u0011', 'Ñ', 'Ð', '\u0010', 'ð', '0', '1', 'ñ', '3', 'ó', 'ò', '2', '6', 'ö', '÷', '7', 'õ', '5', '4', 'ô', '<', 'ü', 'ý', '=', 'ÿ', '?', '>', 'þ', 'ú', ':', ';', 'û', '9', 'ù', 'ø', '8', '(', 'è', 'é', ')', 'ë', '+', '*', 'ê', 'î', '.', '/', 'ï', '-', 'í', 'ì', ',', 'ä', '$', '%', 'å', '\'', 'ç', 'æ', '&', '"', 'â', 'ã', '#', 'á', '!', ' ', 'à', ' ', '`', 'a', '¡', 'c', '£', '¢', 'b', 'f', '¦', '§', 'g', '¥', 'e', 'd', '¤', 'l', '¬', '\u00ad', 'm', '¯', 'o', 'n', '®', 'ª', 'j', 'k', '«', 'i', '©', '¨', 'h', 'x', '¸', '¹', 'y', '»', '{', 'z', 'º', '¾', '~', '\u007f', '¿', '}', '½', '¼', '|', '´', 't', 'u', 'µ', 'w', '·', '¶', 'v', 'r', '²', '³', 's', '±', 'q', 'p', '°', 'P', '\u0090', '\u0091', 'Q', '\u0093', 'S', 'R', '\u0092', '\u0096', 'V', 'W', '\u0097', 'U', '\u0095', '\u0094', 'T', '\u009c', '\\', ']', '\u009d', '_', '\u009f', '\u009e', '^', 'Z', '\u009a', '\u009b', '[', '\u0099', 'Y', 'X', '\u0098', '\u0088', 'H', 'I', '\u0089', 'K', '\u008b', '\u008a', 'J', 'N', '\u008e', '\u008f', 'O', '\u008d', 'M', 'L', '\u008c', 'D', '\u0084', '\u0085', 'E', '\u0087', 'G', 'F', '\u0086', '\u0082', 'B', 'C', '\u0083', 'A', '\u0081', '\u0080', '@'};
    private static Object idLock = new Object();
    private static int nextId = 0;
    public int deviceAddress = -1;
    public int startAddress;
    public int numberPoints;
    public BBBASerialPortDevice serialDevice;
    public int transactionIdentifier = -1;

    public SerialMessage( BBBASerialPortDevice serDevice) {
        this.serialDevice = serDevice;
    }

    public void writeRtu(OutputStream out) throws IOException {
    }

//    public void writeAscii(OutputStream out) throws IOException {
//    }
//
//    public void writeTcp(OutputStream out) throws IOException {
//    }

    public void write(OutputStream out) {
        try {
            this.writeRtu(out);
        } catch (Exception var3) {
            System.out.println("Exception writing Serial Message: ");
            var3.printStackTrace();
        }
    }

    public int getResponseMsgSize() {
        return 0;
    }

    @Override
    public Message toResponse(ReceivedMessage response) {

            SerialReceivedMessage serialResp = (SerialReceivedMessage)response;
            byte[] resp = serialResp.getBytes();
            SerialResponse respMessage = new SerialResponse(this.serialDevice);
            respMessage.deviceAddress = resp[0] & 255;
            respMessage.startAddress = this.startAddress;
            respMessage.numberPoints = this.numberPoints;

            if (respMessage.deviceAddress == this.deviceAddress && this.serialDevice.serialNet() != null && this.serialDevice.serialNet().getSerialLog().isTraceOn()) {
                StringBuffer sb = new StringBuffer();
                sb.append("Received invalid transaction ID in response (expected ");
                sb.append(this.transactionIdentifier).append(", received ");
                sb.append(serialResp.getTransactionId()).append(")");
                this.serialDevice.serialNet().getSerialLog().trace(sb.toString());
            }

            respMessage.transactionIdentifier = this.transactionIdentifier;

            try {
                if (resp[1] < 0) {
                    respMessage.exceptionCode = resp[2];
                    return respMessage;
                } else {
                    respMessage.exceptionCode = 0;
                    respMessage.byteCount = resp[2] & 255;
                    for(int i = 0; i < respMessage.byteCount; ++i) {
                        respMessage.data[i] = resp[3 + i];
                    }
                    return respMessage;
                }
            } catch (ArrayIndexOutOfBoundsException var10) {
                respMessage.exceptionCode = -4;
                return respMessage;
            }

    }

    public String toDebugString() {
        StringBuffer sb = new StringBuffer();
        String comTypeString = "RTU";

        sb.append("Serial " + comTypeString + " Message = " + TextUtil.getClassName(this.getClass()));
        sb.append("\n  Tag = " + this.getTag());
        sb.append("\n  Serial Device Address = " + (this.deviceAddress & 255));
        return sb.toString();
    }

    public static int calcLRC(byte[] msg) {
        int lrc = 0;

        for(int i = 0; i < msg.length; ++i) {
            lrc += msg[i] & 255;
        }

        lrc &= 255;
        lrc = 255 - lrc & 255;
        ++lrc;
        lrc &= 255;
        return lrc;
    }

    public static boolean verifyLRC(byte[] msg) {
        int lrc = 0;

        for(int i = 0; i < msg.length - 2; ++i) {
            lrc += msg[i] & 255;
        }

        lrc &= 255;
        lrc = 255 - lrc & 255;
        ++lrc;
        return (lrc & 255) == (msg[msg.length - 2] & 255);
    }

    public static int calcCRC(byte[] msg) {
        int hiCRC = 255;
        int loCRC = 255;

        for(int i = 0; i < msg.length; ++i) {
            int byteValue = msg[i] & 255;
            int index = hiCRC ^ byteValue;
            hiCRC = loCRC ^ constCRCHi[index];
            loCRC = constCRCLo[index];
        }

        return hiCRC * 256 + loCRC;
    }

    public static boolean verifyCRC(byte[] msg) {
        int hiCRC = 255;
        int loCRC = 255;

        for(int i = 0; i < msg.length - 2; ++i) {
            int byteValue = msg[i] & 255;
            int index = hiCRC ^ byteValue;
            hiCRC = loCRC ^ constCRCHi[index];
            loCRC = constCRCLo[index];
        }

        return (msg[msg.length - 2] & 255) == hiCRC && (msg[msg.length - 1] & 255) == loCRC;
    }

    protected static int getNextTransactionId(int maxId) {
        synchronized(idLock) {
            if (nextId >= maxId) {
                nextId = 0;
            }

            return ++nextId;
        }
    }
}
