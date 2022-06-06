/*
 * Classname:   BBBASerialPortNetwork
 *
 * Version:     1.0
 *
 * Date:        2022/5/13
 *
 * Copyright (c) 2022 PHOENIX CONTACT
 */

package com.phoenixcontact.BBASerialPort;

import com.phoenixcontact.BBASerialPort.comm.BBASerialPortSerialComm;
import com.phoenixcontact.BBASerialPort.comm.SerialMessage;
import com.tridium.basicdriver.BBasicDevice;
import com.tridium.basicdriver.comm.Comm;
import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.serial.BSerialNetwork;
import javax.baja.driver.BDevice;
import javax.baja.driver.ping.BPingMonitor;
import javax.baja.log.Log;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.serial.BSerialHelper;
import javax.baja.status.BStatus;
import javax.baja.sys.*;

/**
 * TODO Description
 *
 * @author
 * @version 1.0
 * @since 2022/5/13
 */

@NiagaraType
@NiagaraProperty(name = "maxFailsUntilDeviceDown", type = "int", defaultValue = "3", flags = Flags.SUMMARY)
@NiagaraProperty(name = "rtuSnifferModeBufferSize", type = "int", defaultValue = "8", flags = Flags.SUMMARY)
@NiagaraProperty(name = "rxPriority", type = "boolean", defaultValue = "false", flags = Flags.HIDDEN)
@NiagaraProperty(name = "snifferMode", type = "boolean", defaultValue = "false", flags = Flags.SUMMARY)
@NiagaraProperty(name = "maxRxInterCharacterDelay", type = "BRelTime", defaultValue = "BRelTime.make(50L)", flags = Flags.SUMMARY,
        facets = {@Facet(
                name = "BFacets.SHOW_MILLISECONDS",
                value = "BBoolean.TRUE"
        ), @Facet(
                name = "BFacets.MIN",
                value = "BRelTime.make(0L)"

        ), @Facet(
                name = "BFacets.MAX",
                value = "BRelTime.SECOND"
        )})
@NiagaraProperty(name = "minRxFrameEnd",type = "BRelTime",defaultValue = "BRelTime.make(20L)",flags = Flags.SUMMARY,
        facets = {@Facet(
                name = "BFacets.SHOW_MILLISECONDS",
                value = "BBoolean.TRUE"
        ), @Facet(
                name = "BFacets.MIN",
                value = "BRelTime.make(0L)"
        ), @Facet(
                name = "BFacets.MAX",
                value = "BRelTime.SECOND"
        )})

public class BBBASerialPortNetwork extends BSerialNetwork {
    //region Slotomatic   
    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBBASerialPortNetwork(648150743)1.0$ @*/
/* Generated Thu May 19 16:54:52 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "maxFailsUntilDeviceDown"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code maxFailsUntilDeviceDown} property.
   * @see #getMaxFailsUntilDeviceDown
   * @see #setMaxFailsUntilDeviceDown
   */
  public static final Property maxFailsUntilDeviceDown = newProperty(Flags.SUMMARY, 3, null);
  
  /**
   * Get the {@code maxFailsUntilDeviceDown} property.
   * @see #maxFailsUntilDeviceDown
   */
  public int getMaxFailsUntilDeviceDown() { return getInt(maxFailsUntilDeviceDown); }
  
  /**
   * Set the {@code maxFailsUntilDeviceDown} property.
   * @see #maxFailsUntilDeviceDown
   */
  public void setMaxFailsUntilDeviceDown(int v) { setInt(maxFailsUntilDeviceDown, v, null); }

////////////////////////////////////////////////////////////////
// Property "rtuSnifferModeBufferSize"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code rtuSnifferModeBufferSize} property.
   * @see #getRtuSnifferModeBufferSize
   * @see #setRtuSnifferModeBufferSize
   */
  public static final Property rtuSnifferModeBufferSize = newProperty(Flags.SUMMARY, 8, null);
  
  /**
   * Get the {@code rtuSnifferModeBufferSize} property.
   * @see #rtuSnifferModeBufferSize
   */
  public int getRtuSnifferModeBufferSize() { return getInt(rtuSnifferModeBufferSize); }
  
  /**
   * Set the {@code rtuSnifferModeBufferSize} property.
   * @see #rtuSnifferModeBufferSize
   */
  public void setRtuSnifferModeBufferSize(int v) { setInt(rtuSnifferModeBufferSize, v, null); }

////////////////////////////////////////////////////////////////
// Property "rxPriority"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code rxPriority} property.
   * @see #getRxPriority
   * @see #setRxPriority
   */
  public static final Property rxPriority = newProperty(Flags.HIDDEN, false, null);
  
  /**
   * Get the {@code rxPriority} property.
   * @see #rxPriority
   */
  public boolean getRxPriority() { return getBoolean(rxPriority); }
  
  /**
   * Set the {@code rxPriority} property.
   * @see #rxPriority
   */
  public void setRxPriority(boolean v) { setBoolean(rxPriority, v, null); }

////////////////////////////////////////////////////////////////
// Property "snifferMode"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code snifferMode} property.
   * @see #getSnifferMode
   * @see #setSnifferMode
   */
  public static final Property snifferMode = newProperty(Flags.SUMMARY, false, null);
  
  /**
   * Get the {@code snifferMode} property.
   * @see #snifferMode
   */
  public boolean getSnifferMode() { return getBoolean(snifferMode); }
  
  /**
   * Set the {@code snifferMode} property.
   * @see #snifferMode
   */
  public void setSnifferMode(boolean v) { setBoolean(snifferMode, v, null); }

////////////////////////////////////////////////////////////////
// Property "maxRxInterCharacterDelay"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code maxRxInterCharacterDelay} property.
   * @see #getMaxRxInterCharacterDelay
   * @see #setMaxRxInterCharacterDelay
   */
  public static final Property maxRxInterCharacterDelay = newProperty(Flags.SUMMARY, BRelTime.make(50L), BFacets.make(BFacets.make(BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE), BFacets.make(BFacets.MIN, BRelTime.make(0L))), BFacets.make(BFacets.MAX, BRelTime.SECOND)));
  
  /**
   * Get the {@code maxRxInterCharacterDelay} property.
   * @see #maxRxInterCharacterDelay
   */
  public BRelTime getMaxRxInterCharacterDelay() { return (BRelTime)get(maxRxInterCharacterDelay); }
  
  /**
   * Set the {@code maxRxInterCharacterDelay} property.
   * @see #maxRxInterCharacterDelay
   */
  public void setMaxRxInterCharacterDelay(BRelTime v) { set(maxRxInterCharacterDelay, v, null); }

////////////////////////////////////////////////////////////////
// Property "minRxFrameEnd"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code minRxFrameEnd} property.
   * @see #getMinRxFrameEnd
   * @see #setMinRxFrameEnd
   */
  public static final Property minRxFrameEnd = newProperty(Flags.SUMMARY, BRelTime.make(20L), BFacets.make(BFacets.make(BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE), BFacets.make(BFacets.MIN, BRelTime.make(0L))), BFacets.make(BFacets.MAX, BRelTime.SECOND)));
  
  /**
   * Get the {@code minRxFrameEnd} property.
   * @see #minRxFrameEnd
   */
  public BRelTime getMinRxFrameEnd() { return (BRelTime)get(minRxFrameEnd); }
  
  /**
   * Set the {@code minRxFrameEnd} property.
   * @see #minRxFrameEnd
   */
  public void setMinRxFrameEnd(BRelTime v) { set(minRxFrameEnd, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  @Override
  public Type getType() { return TYPE; }

  public static final Type TYPE = Sys.loadType(BBBASerialPortNetwork.class);

  @Override
  public Type getDeviceType() {
      return BBBASerialPortDevice.TYPE;
  }

  @Override
  public Type getDeviceFolderType() {
        return BBBASerialPortDeviceFolder.TYPE;
    }

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    //endregion Slotomatic

    //region Attributes

    private static final BIcon icon = BIcon.make("module://emalyticsHVAC/icons/pxcLogo.png");

    protected Log serialLog = null;

    private boolean commActive = false;

    private boolean networkInitialized = false;

    private long totalCrcErrors = 0L;
    private long totalLrcErrors = 0L;
    private long totalTransactionIdErrors = 0L;
    private long totalPartialRxMsgs = 0L;

    private Comm comm = null;

    protected Subscriber subscriber;

    //endregion Attributes

    //region Constructors

    /**
     * Public parameterless constructor of the class.
     */
    public BBBASerialPortNetwork() {
    }

    public BBBASerialPortNetwork(BSerialHelper bSerialHelper) {
        this.setResponseTimeout(BRelTime.make(1000L));
        this.setSerialPortConfig(bSerialHelper);
    }


    //endregion Constructors

    //region Methods
    // Should be grouped by functionality rather than by scope or accessibility

    //region BComponent overrides

    /**
     * The started() method is called when a component's running state moves to true.
     * Components are started top-down, children after their parent.
     *
     * @throws Exception
     */
    @Override
    public void started() throws Exception {
        super.started();
        this.getMonitor().setNumRetriesUntilPingFail(0);
        this.getMonitor().setFlags(BPingMonitor.numRetriesUntilPingFail, this.getFlags(BPingMonitor.numRetriesUntilPingFail) | 4 | 1);

    }

    /**
     * Callback when a property (or possibly a descendant of that property) is modified
     * on this component via one of the set methods.
     *
     * @param property
     * @param context
     */
    @Override
    public void changed(Property property, Context context) {
        super.changed(property, context);
        if (!this.isRunning()) {
            return;
        }

        if (this.isRunning() && property.equals(rxPriority)) {
            Comm comm = this.getComm();
            if (comm instanceof BBASerialPortSerialComm) {
                Thread rxThread = ((BBASerialPortSerialComm)comm).getRxThread();
                if (rxThread != null) {
                    if (this.getRxPriority()) {
                        rxThread.setPriority(7);
                    } else {
                        rxThread.setPriority(5);
                    }
                }
            }
        }

        if(property.equals(serialPortConfig)){
            reopenPort();
        }
    }

    public void serviceStarted() throws Exception {
        super.serviceStarted();
        this.getNameSubscriber().subscribe(this.getSerialPortConfig());
        this.getSerialPortConfig().setSerialHelperParent(this);
        if (this.serialLog == null) {
            this.serialLog = this.getLog();
        }

        synchronized(this.serialLog) {
            this.serialLog = this.getLog();
        }
//        super.startComm();

    }

    public void serviceStopped() throws Exception {
        super.serviceStopped();
        this.subscriber.unsubscribeAll();
        this.subscriber = null;
        stopComm();
    }

    @Override
    protected Comm makeComm() {
        return new BBASerialPortSerialComm(this);
    }

    protected void initComm(Comm comm) throws Exception {
    }

    public void startComm() throws Exception {
        if (this.getSerialPortConfig().getPortName().equals("none")) {
            this.configFail("No port selected for Modbus communication.");
        } else {
            try {
                super.startComm();
            } catch (Exception var2) {
                this.configFail("Could not enable serial communication (" + var2 + ")");
                throw var2;
            }

            this.configOk();
        }

        if (!this.isDisabled() && !this.isDown() && !this.isFault()) {
            if (this.getSerialLog().isTraceOn()) {
                this.getSerialLog().trace("*** Starting serial comm for " + this.getName() + " ***");
            }

            if (!this.initializeNetwork()) {
                this.getSerialLog().warning("Unable to initialize network " + this.getName() + "!!");
            }
        }

    }

    public void stopComm() throws Exception {
        super.stopComm();
        this.commActive = false;
        this.networkInitialized = false;
    }

    private boolean initializeNetwork() {
        if (this.networkInitialized) {
            return true;
        } else {
            this.commActive = true;
            if (!this.getComm().isCommStarted()) {
                this.getSerialLog().warning("Unable to start Serial Comm for " + this.getName());
                setStatus(BStatus.down);
                this.commActive = false;
                return false;
            } else {
                try {
                    ((BBASerialPortSerialComm)this.getComm()).getSerialPort().enableReceiveThreshold(1);
                } catch (UnsupportedOperationException var2) {
                    this.getSerialLog().error(this.getName() + ", BBBASerialPortComm: Unable to perform enableReceiveThreshold = 1.  Exception- ", var2);
                    setStatus(BStatus.down);
                }
                this.networkInitialized = true;
                return true;
            }
        }
    }

    public boolean isCommActive() {
        return this.commActive && super.isCommActive();
    }

    public Message sendSync(Message msg, BRelTime responseTimeout, int retryCount) {
        if (this.isCommActive()) {
            Message response = super.sendSync(msg, responseTimeout, retryCount);

            try {
                BBBASerialPortDevice destDevice = (BBBASerialPortDevice)this.findDeviceInNetwork(((SerialMessage)msg).deviceAddress);
                if (destDevice != null) {
                    if (response != null) {
                        destDevice.pingOk();
                        destDevice.resetPingsFailed();
                    } else if (destDevice.incrementPingsFailed() > this.getMaxFailsUntilDeviceDown()) {
                        destDevice.pingFail(this.getLexicon().getText("pingFail"));
                    }
                }
            } catch (Exception var6) {
            }

            return response;
        } else {
            return null;
        }
    }

    public Log getSerialLog() {
        String serialLogName = this.getName();
        if (!SlotPath.isValidName(serialLogName)) {
            serialLogName = SlotPath.escape(serialLogName);
        }

        return Log.getLog(serialLogName);
    }

    protected void updateLog() {
        String modbusLogName = this.getName();
        if (!SlotPath.isValidName(modbusLogName)) {
            modbusLogName = SlotPath.escape(modbusLogName);
        }

        Log newLog = Log.getLog(modbusLogName);
        if (this.serialLog == null) {
            this.serialLog = this.getSerialLog();
        }

        synchronized(this.serialLog) {
            if (this.serialLog != null) {
                int severity = this.serialLog.getSeverity();
                if (!newLog.getLogName().equals(this.serialLog.getLogName())) {
                    Log.deleteLog(this.serialLog.getLogName());
                }

                newLog.setSeverity(severity);
            }

            this.serialLog = newLog;
        }
    }

    protected void processNameSubscriberEvent(BComponentEvent event) {
        try {
            if (event.getId() == 3 && event.getSlot().equals(this.getPropertyInParent())) {
                this.updateLog();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    protected Subscriber getNameSubscriber() {
        return this.subscriber;
    }

    public final void incrementCrcErrors() {
        ++this.totalCrcErrors;
    }

    public final void incrementLrcErrors() {
        ++this.totalLrcErrors;
    }

    public final void incrementTransactionIdErrors() {
        ++this.totalTransactionIdErrors;
    }

    public final void incrementPartialRxMsgs() {
        ++this.totalPartialRxMsgs;
    }

    public final long getPartialRxMsgs() {
        return this.totalPartialRxMsgs;
    }

    public final long getTotalCrcErrors() {
        return this.totalCrcErrors;
    }

    public final long geTotalLrcErrors() {
        return this.totalLrcErrors;
    }

    public final long geTotalTransactionIdErrors() {
        return this.totalTransactionIdErrors;
    }

    protected BBasicDevice findDeviceInNetwork(int address) {
        BDevice[] devices = this.getDevices();

        for(int i = 0; i < devices.length; ++i) {
            if (devices[i] instanceof BBBASerialPortDevice && ((BBBASerialPortDevice)devices[i]).getDeviceAddress() == address) {
                return (BBBASerialPortDevice)devices[i];
            }
        }

        return null;
    }


    /**
     * Get the icon.
     *
     * @return The icon.
     */
    @Override
    public BIcon getIcon() {
        return icon;
    }
    //endregion BComponents overrides
    //endregion Methods
}