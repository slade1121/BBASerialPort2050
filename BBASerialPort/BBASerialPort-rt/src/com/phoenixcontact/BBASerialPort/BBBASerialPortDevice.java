package com.phoenixcontact.BBASerialPort;

import com.phoenixcontact.BBASerialPort.comm.SerialMessage;
import com.tridium.basicdriver.BBasicDevice;
import com.tridium.basicdriver.message.Message;
import com.tridium.modbusAsync.BModbusAsyncDeviceFolder;
import com.tridium.modbusAsync.BModbusAsyncNetwork;
import com.tridium.sys.station.Station;
import javax.baja.driver.ping.BPingMonitor;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.spy.SpyWriter;
import javax.baja.sys.*;

@NiagaraType

@NiagaraProperty(name = "deviceAddress", type = "int", defaultValue = "1", flags = Flags.SUMMARY)


public class BBBASerialPortDevice extends BBasicDevice {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBBASerialPortDevice(4018102576)1.0$ @*/
/* Generated Wed May 18 13:28:30 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "deviceAddress"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code deviceAddress} property.
   * @see #getDeviceAddress
   * @see #setDeviceAddress
   */
  public static final Property deviceAddress = newProperty(Flags.SUMMARY, 1, null);
  
  /**
   * Get the {@code deviceAddress} property.
   * @see #deviceAddress
   */
  public int getDeviceAddress() { return getInt(deviceAddress); }
  
  /**
   * Set the {@code deviceAddress} property.
   * @see #deviceAddress
   */
  public void setDeviceAddress(int v) { setInt(deviceAddress, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBBASerialPortDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    private long totalMessages = 0L;
    private long totalCrcErrors = 0L;
    private long totalLrcErrors = 0L;
    private long totalTransactionIdErrors = 0L;
    private long timeouts = 0L;
    private int pingsFailed = 0;

    public BBBASerialPortDevice() {
        this.setFlags(upload, 4);
        this.setFlags(download, 4);
    }

    public Type getNetworkType() {
        return BBBASerialPortNetwork.TYPE;
    }

    @Override
    public void started() throws Exception {
        super.started();
        this.getMonitor().setNumRetriesUntilPingFail(0);
        this.getMonitor().setFlags(BPingMonitor.numRetriesUntilPingFail, this.getFlags(BPingMonitor.numRetriesUntilPingFail) | 4 | 1);
    }

    @Override
    public void changed(Property property, Context context) {
        super.changed(property, context);
        if (this.isRunning()) {

        }
    }

    public String toString(Context context) {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getName()).append("[" + this.getDeviceAddress() + "]");
        return sb.toString();
    }

//    public boolean isParentLegal(BComponent parent) {
//        return parent instanceof BBBASerialPortNetwork || parent instanceof BBBASerialPortDeviceFolder;
//    }

    public Message sendSerialMessage(Message msg) {
        BBBASerialPortNetwork net = this.serialNet();
        if (net == null) {
            return null;
        } else {
            this.incrementRequest();
            BValue saveDebug = this.get("saveDebug");
            if (saveDebug != null && saveDebug instanceof BBoolean && ((BBoolean)saveDebug).getBoolean() && Station.inSave) {
                long ticks = Clock.ticks();
                System.out.println("sendSerialMessage:: Wait for station save to complete");

                while(Station.inSave && Clock.ticks() - ticks < 10000L) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var7) {
                        var7.printStackTrace();
                    }
                }

                if (Station.inSave && Clock.ticks() - ticks > 10000L) {
                    System.out.println("sendSerialMessage:: Gave up on station stave to complete");
                }
            }

//            Message rspMsg = net.sendSync(msg);
            Message rspMsg = net.sendSync(msg, net.getResponseTimeout(), net.getRetryCount());
            if (rspMsg == null) {
                this.incrementTimeouts();
            }

            return rspMsg;
        }
    }

    public BBBASerialPortNetwork serialNet() {
        return (BBBASerialPortNetwork)this.getNetwork();
    }

    public void spy(SpyWriter out) throws Exception {
        super.spy(out);
        out.startProps();
        out.trTitle("SerialDevice", 2);
        out.prop("Total Timeouts", new Long(this.timeouts));
        out.prop("Total CRC Errors", new Long(this.totalCrcErrors));
        out.prop("Total LRC Errors", new Long(this.totalLrcErrors));
        out.prop("Total Transaction ID Errors", new Long(this.totalTransactionIdErrors));
        out.endProps();
    }

    public final void incrementCrcErrors() {
        ++this.totalCrcErrors;
        if (this.serialNet() != null) {
            this.serialNet().incrementCrcErrors();
        }

    }

    public final void incrementLrcErrors() {
        ++this.totalLrcErrors;
        if (this.serialNet() != null) {
            this.serialNet().incrementLrcErrors();
        }

    }

    public final void incrementTransactionIdErrors() {
        ++this.totalTransactionIdErrors;
        if (this.serialNet() != null) {
            this.serialNet().incrementTransactionIdErrors();
        }

    }


    public void incrementRequest() {
        ++this.totalMessages;
    }

    public void incrementTimeouts() {
        ++this.timeouts;
    }

    public long getTotalMessages() {
        return this.totalMessages;
    }

    public long getTotalCrcErrors() {
        return this.totalCrcErrors;
    }

    public long getTotalLrcErrors() {
        return this.totalLrcErrors;
    }

    public long getTotalTransactionIdErrors() {
        return this.totalTransactionIdErrors;
    }

    public long getTotalTimeouts() {
        return this.timeouts;
    }

    public void doPing() {
        if(!(this.serialNet()).getSnifferMode()){
            return;
        }
        if (!this.isDisabled() && !this.isFault()) {
            if (this.serialNet() == null) {
                this.pingFail("No serial network found");
            } else if (this.serialNet().isCommActive()) {
                Message req;
                try {
                    req = new SerialMessage(this);
                } catch (Exception var4) {
                    this.serialNet().getSerialLog().error("Error in " + this.getName() + " pinging.  Check Device Status Monitor Address: ", var4);
                    return;
                }

                Message rsp = this.sendSerialMessage(req);
                if (this.serialNet().getSerialLog().isTraceOn()) {
                    this.serialNet().getSerialLog().trace("ping(): " + this.getName() + "[" + this.getDeviceAddress() + "] is " + (rsp == null ? "down" : "up"));
                }

                if (rsp != null && rsp.getSuccessfulResponse() && !rsp.getResponseExpected()) {
                    this.pingOk();
                    this.resetPingsFailed();
                } else if (this.incrementPingsFailed() > (this.serialNet()).getMaxFailsUntilDeviceDown()) {
                    String cause = this.getLexicon().getText("pingFail");
                    if (rsp != null) {
                        cause = cause + ": " + rsp.toDebugString();
                    }

                    this.pingFail(cause);
                }

            }
        }
    }

    public int incrementPingsFailed() {
        ++this.pingsFailed;
        return this.pingsFailed;
    }

    public void resetPingsFailed() {
        this.pingsFailed = 0;
    }



}
