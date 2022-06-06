package com.phoenixcontact.BBASerialPort.comm;

import com.tridium.basicdriver.comm.CommTransmitter;
import com.tridium.basicdriver.message.Message;
import com.tridium.modbusCore.messages.ModbusMessage;


public class BBASerialPortTxDriver extends CommTransmitter {
    public BBASerialPortTxDriver() {
    }

    public void writeMessage(Message message) {
        try {
            int firstByte = ((ModbusMessage)message).deviceAddress;
            int frameStart = firstByte & 255;
            ((BBASerialPortRxDriver)this.getComm().getCommReceiver()).setResponseSize(((ModbusMessage)message).getResponseMsgSize());
            ((BBASerialPortRxDriver)this.getComm().getCommReceiver()).setComType(((ModbusMessage)message).comType);
            ((BBASerialPortRxDriver)this.getComm().getCommReceiver()).setFrameStart(frameStart);
            Thread.currentThread().setPriority(7);
            super.writeMessage(message);
            Thread.currentThread().setPriority(5);
        } catch (Exception var4) {
            this.getComm().handleFailedTransmit(message, var4);
        }

    }
}