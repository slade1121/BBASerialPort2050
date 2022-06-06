package com.phoenixcontact.BBASerialPort.comm;

import com.phoenixcontact.BBASerialPort.BBBASerialPortNetwork;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.basicdriver.serial.SerialComm;
import com.tridium.basicdriver.util.BasicException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BISerialService;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;
import javax.baja.sys.Sys;

public class BBASerialPortSerialComm extends SerialComm {
    private static final long MIN_SLEEP_TIME = 1L;
    protected BISerialPort serialPort;
    protected InputStream in;
    protected OutputStream out;
    protected Thread rxThread;
    private long lastRecvMessageTicks = 0L;

    public BBASerialPortSerialComm(BBBASerialPortNetwork serialNetwork) {
        super(serialNetwork, new BBASerialPortRxDriver(serialNetwork), new BBASerialPortTxDriver());
    }

    protected boolean started() throws Exception {
        try {
            BISerialService platSvc = (BISerialService)Sys.getService(BISerialService.TYPE);
            this.serialPort = ((BBBASerialPortNetwork)this.getNetwork()).getSerialPortConfig().open(this.getNetwork().getName());
            this.serialPort.enableReceiveTimeout(platSvc.getMinTimeout());
            this.in = this.serialPort.getInputStream();
            this.out = this.serialPort.getOutputStream();
        } catch (Exception var6) {
            String errMsg = "Error opening and configuring the serial port";
            this.getNetwork().getLog().error(errMsg, var6);
            if (this.in != null) {
                try {
                    this.in.close();
                } catch (Exception var5) {
                    this.getNetwork().getLog().error("Unable to close serial input stream.", var5);
                }
            }

            if (this.out != null) {
                try {
                    this.out.close();
                } catch (Exception var4) {
                    this.getNetwork().getLog().error("Unable to close serial output stream.", var4);
                }
            }

            if (this.serialPort != null) {
                this.serialPort.close();
            }

            throw var6;
        }

        this.getCommReceiver().setInputStream(this.in);
        this.getCommTransmitter().setOutputStream(this.out);
        this.rxThread = new Thread(this.getCommReceiver(), "SerialRcv:" + this.getNetwork().getName());
        this.getCommReceiver().setAlive(true);
        this.rxThread.start();
        if (((BBBASerialPortNetwork)this.getNetwork()).getRxPriority()) {
            this.rxThread.setPriority(7);
        } else {
            this.rxThread.setPriority(5);
        }

        return true;
    }

    public Thread getRxThread() {
        return this.rxThread;
    }
}

