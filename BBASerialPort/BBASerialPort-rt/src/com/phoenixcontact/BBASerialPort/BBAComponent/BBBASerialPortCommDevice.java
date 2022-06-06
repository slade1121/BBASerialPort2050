package com.phoenixcontact.BBASerialPort.BBAComponent;
/*
 * Classname:   BBBASerialPortCommDevice
 *
 * Version:     1.0
 *
 * Date:        5/24/2022
 *
 * Copyright (c) 2022 PHOENIX CONTACT
 */


import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.Array;
import javax.baja.serial.BISerialHelperParent;
import javax.baja.serial.BISerialPort;
import javax.baja.serial.BISerialService;
import javax.baja.serial.BSerialHelper;
import javax.baja.status.BStatus;
import javax.baja.status.BStatusBoolean;
import javax.baja.sys.*;
import javax.baja.xml.XInputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * TODO Description
 *
 * @author songyantao@phoenixcontact.com.cn
 * @version 1.0
 * @since 5/24/2022
 */

@NiagaraType
@NiagaraProperty(name = "settings", type = "BSerialHelper", defaultValue = "new BSerialHelper()")
@NiagaraProperty(name = "lastReponseMessage", type = "BString", defaultValue = "", flags = Flags.READONLY)
@NiagaraProperty(name = "currentReponseMessage", type = "BString", defaultValue = "", flags = Flags.READONLY)
@NiagaraProperty(name = "idSolenoidValve", type = "int", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSubBoard1", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSubBoard2", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchWaterPump", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValveSubBoard1", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValveSubBoard2", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
//@NiagaraProperty(name = "switchClearAlarm", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraAction(name = "Send",defaultValue = "BString.DEFAULT",parameterType = "BString")
@NiagaraAction(name = "powerSwitchSubBoard1",flags = Flags.ASYNC | Flags.HIDDEN)
@NiagaraAction(name = "powerSwitchSubBoard2",flags = Flags.ASYNC | Flags.HIDDEN)
@NiagaraAction(name = "reConnect",flags = Flags.ASYNC | Flags.SUMMARY)
@NiagaraAction(name = "readCurrentReponseMessage",flags = Flags.ASYNC | Flags.SUMMARY)
public class BBBASerialPortCommDevice extends BComponent implements BISerialHelperParent {

    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBAComponent.BBBASerialPortCommDevice(3286046667)1.0$ @*/
/* Generated Mon Jun 06 11:14:34 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "settings"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code settings} property.
   * @see #getSettings
   * @see #setSettings
   */
  public static final Property settings = newProperty(0, new BSerialHelper(), null);
  
  /**
   * Get the {@code settings} property.
   * @see #settings
   */
  public BSerialHelper getSettings() { return (BSerialHelper)get(settings); }
  
  /**
   * Set the {@code settings} property.
   * @see #settings
   */
  public void setSettings(BSerialHelper v) { set(settings, v, null); }

////////////////////////////////////////////////////////////////
// Property "lastReponseMessage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code lastReponseMessage} property.
   * @see #getLastReponseMessage
   * @see #setLastReponseMessage
   */
  public static final Property lastReponseMessage = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code lastReponseMessage} property.
   * @see #lastReponseMessage
   */
  public String getLastReponseMessage() { return getString(lastReponseMessage); }
  
  /**
   * Set the {@code lastReponseMessage} property.
   * @see #lastReponseMessage
   */
  public void setLastReponseMessage(String v) { setString(lastReponseMessage, v, null); }

////////////////////////////////////////////////////////////////
// Property "currentReponseMessage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code currentReponseMessage} property.
   * @see #getCurrentReponseMessage
   * @see #setCurrentReponseMessage
   */
  public static final Property currentReponseMessage = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code currentReponseMessage} property.
   * @see #currentReponseMessage
   */
  public String getCurrentReponseMessage() { return getString(currentReponseMessage); }
  
  /**
   * Set the {@code currentReponseMessage} property.
   * @see #currentReponseMessage
   */
  public void setCurrentReponseMessage(String v) { setString(currentReponseMessage, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve} property.
   * @see #getIdSolenoidValve
   * @see #setIdSolenoidValve
   */
  public static final Property idSolenoidValve = newProperty(Flags.SUMMARY, 0, null);
  
  /**
   * Get the {@code idSolenoidValve} property.
   * @see #idSolenoidValve
   */
  public int getIdSolenoidValve() { return getInt(idSolenoidValve); }
  
  /**
   * Set the {@code idSolenoidValve} property.
   * @see #idSolenoidValve
   */
  public void setIdSolenoidValve(int v) { setInt(idSolenoidValve, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSubBoard1"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSubBoard1} property.
   * @see #getSwitchSubBoard1
   * @see #setSwitchSubBoard1
   */
  public static final Property switchSubBoard1 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSubBoard1} property.
   * @see #switchSubBoard1
   */
  public BStatusBoolean getSwitchSubBoard1() { return (BStatusBoolean)get(switchSubBoard1); }
  
  /**
   * Set the {@code switchSubBoard1} property.
   * @see #switchSubBoard1
   */
  public void setSwitchSubBoard1(BStatusBoolean v) { set(switchSubBoard1, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSubBoard2"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSubBoard2} property.
   * @see #getSwitchSubBoard2
   * @see #setSwitchSubBoard2
   */
  public static final Property switchSubBoard2 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSubBoard2} property.
   * @see #switchSubBoard2
   */
  public BStatusBoolean getSwitchSubBoard2() { return (BStatusBoolean)get(switchSubBoard2); }
  
  /**
   * Set the {@code switchSubBoard2} property.
   * @see #switchSubBoard2
   */
  public void setSwitchSubBoard2(BStatusBoolean v) { set(switchSubBoard2, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchWaterPump"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchWaterPump} property.
   * @see #getSwitchWaterPump
   * @see #setSwitchWaterPump
   */
  public static final Property switchWaterPump = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchWaterPump} property.
   * @see #switchWaterPump
   */
  public BStatusBoolean getSwitchWaterPump() { return (BStatusBoolean)get(switchWaterPump); }
  
  /**
   * Set the {@code switchWaterPump} property.
   * @see #switchWaterPump
   */
  public void setSwitchWaterPump(BStatusBoolean v) { set(switchWaterPump, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValveSubBoard1"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValveSubBoard1} property.
   * @see #getSwitchSolenoidValveSubBoard1
   * @see #setSwitchSolenoidValveSubBoard1
   */
  public static final Property switchSolenoidValveSubBoard1 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValveSubBoard1} property.
   * @see #switchSolenoidValveSubBoard1
   */
  public BStatusBoolean getSwitchSolenoidValveSubBoard1() { return (BStatusBoolean)get(switchSolenoidValveSubBoard1); }
  
  /**
   * Set the {@code switchSolenoidValveSubBoard1} property.
   * @see #switchSolenoidValveSubBoard1
   */
  public void setSwitchSolenoidValveSubBoard1(BStatusBoolean v) { set(switchSolenoidValveSubBoard1, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValveSubBoard2"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValveSubBoard2} property.
   * @see #getSwitchSolenoidValveSubBoard2
   * @see #setSwitchSolenoidValveSubBoard2
   */
  public static final Property switchSolenoidValveSubBoard2 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValveSubBoard2} property.
   * @see #switchSolenoidValveSubBoard2
   */
  public BStatusBoolean getSwitchSolenoidValveSubBoard2() { return (BStatusBoolean)get(switchSolenoidValveSubBoard2); }
  
  /**
   * Set the {@code switchSolenoidValveSubBoard2} property.
   * @see #switchSolenoidValveSubBoard2
   */
  public void setSwitchSolenoidValveSubBoard2(BStatusBoolean v) { set(switchSolenoidValveSubBoard2, v, null); }

////////////////////////////////////////////////////////////////
// Action "Send"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code Send} action.
   * @see #Send(BString parameter)
   */
  public static final Action Send = newAction(0, BString.DEFAULT, null);
  
  /**
   * Invoke the {@code Send} action.
   * @see #Send
   */
  public void Send(BString parameter) { invoke(Send, parameter, null); }

////////////////////////////////////////////////////////////////
// Action "powerSwitchSubBoard1"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code powerSwitchSubBoard1} action.
   * @see #powerSwitchSubBoard1()
   */
  public static final Action powerSwitchSubBoard1 = newAction(Flags.ASYNC | Flags.HIDDEN, null);
  
  /**
   * Invoke the {@code powerSwitchSubBoard1} action.
   * @see #powerSwitchSubBoard1
   */
  public void powerSwitchSubBoard1() { invoke(powerSwitchSubBoard1, null, null); }

////////////////////////////////////////////////////////////////
// Action "powerSwitchSubBoard2"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code powerSwitchSubBoard2} action.
   * @see #powerSwitchSubBoard2()
   */
  public static final Action powerSwitchSubBoard2 = newAction(Flags.ASYNC | Flags.HIDDEN, null);
  
  /**
   * Invoke the {@code powerSwitchSubBoard2} action.
   * @see #powerSwitchSubBoard2
   */
  public void powerSwitchSubBoard2() { invoke(powerSwitchSubBoard2, null, null); }

////////////////////////////////////////////////////////////////
// Action "reConnect"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code reConnect} action.
   * @see #reConnect()
   */
  public static final Action reConnect = newAction(Flags.ASYNC | Flags.SUMMARY, null);
  
  /**
   * Invoke the {@code reConnect} action.
   * @see #reConnect
   */
  public void reConnect() { invoke(reConnect, null, null); }

////////////////////////////////////////////////////////////////
// Action "readCurrentReponseMessage"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code readCurrentReponseMessage} action.
   * @see #readCurrentReponseMessage()
   */
  public static final Action readCurrentReponseMessage = newAction(Flags.ASYNC | Flags.SUMMARY, null);
  
  /**
   * Invoke the {@code readCurrentReponseMessage} action.
   * @see #readCurrentReponseMessage
   */
  public void readCurrentReponseMessage() { invoke(readCurrentReponseMessage, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBBASerialPortCommDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

    BISerialPort serialPort = null;
    protected InputStream in;
    protected OutputStream out;
    Thread rxThread = null;
    CommReceiver commReceiver = null;
    Clock.Ticket ss1 = null;
    Clock.Ticket ss2 = null;

    List<Integer> readMessage;
    byte[] bufferRead = new byte[1];
    byte[] bytes = {};
    StringBuilder str = null;
    String strTemp = "";

    public void onMessage(int data) {
        System.out.println(data);
        System.out.println(Integer.toHexString(data));
        String strTemp = Integer.toHexString(data);
//        str.append(strTemp);
        System.out.println(str);
        if(data == 10){
            str = null;
        }

//        readMessage.add(data);
//        intTemp = readMessage.toArray();
//        int lengthTemp = intTemp.length;
//        if(lengthTemp>2 && intTemp[lengthTemp-1].toString().equals("10") && intTemp[lengthTemp-2].toString().equals("13") ){
//            System.out.println(readMessage);
//            readMessage.clear();
//        }

    }

    public void onMessage(byte[] message) {
//        System.out.println(message[0]);
//        System.out.println(Integer.toHexString(message[0]));

        bytes = addByte(bytes,message);

        int lengthTemp = bytes.length;
//        if(bytes.length >= 6 && Integer.toHexString(bytes[lengthTemp-1]).equals("a") && Integer.toHexString(bytes[lengthTemp-2]).equals("d") ){
        if(Integer.toHexString(bytes[lengthTemp-1]).equals("a") && Integer.toHexString(bytes[lengthTemp-2]).equals("d") ){

//            System.out.println(Integer.toHexString(bytes[lengthTemp - 1]));
//            System.out.println(Integer.toHexString(bytes[lengthTemp - 2]));
//            System.out.println(Integer.toHexString(bytes[lengthTemp - 1]).equals("a"));
//            System.out.println(Integer.toHexString(bytes[lengthTemp - 2]).equals("d"));

            strTemp = "";
            strTemp = new String(bytes);

            readCurrentReponseMessage();
//            System.out.println(strTemp);

            bytes = null;
        }
    }

    public void onMessage(List<Integer> message) {
        System.out.println(message.toArray().toString());
    }

    public void send(byte[] data) throws IOException {
        if (this.out != null) {
            this.out.write(data);
        }
    }

    public void doSend(BString v){
        try {
            this.send(v.getString().getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){
            this.log.warning(e.getMessage());
        }
    }

    public void doPowerSwitchSubBoard1(){
        onSwitchSubBoard1();
    }

    public void doPowerSwitchSubBoard2(){
        onSwitchSubBoard2();
    }

    public void doReConnect() {
        if(getSettings().getStatus().isOk()){
            return;
        }
        try {
            String newPort = this.getSettings().getPortName();
            if (newPort.equals("none")) {
                this.log.warning("No port selected for serial communication.");
                this.stopComm();
                return;
            }

            this.stopComm();
             serialPort = null;
             commReceiver = null;
            this.startComm();
        } catch (Exception var2) {
            this.log.warning("BSerialNetwork caught exception in reopenPort(): "+ var2);
        }
    }

   public void doReadCurrentReponseMessage(){
        setCurrentReponseMessage(strTemp);
   }

    class CommReceiver implements Runnable {

        boolean _stop = false;

//        @Override
//        public void run() {
//            while (!this._stop) {
//                try {
//                    int data = in.read();
//                    if(data == -1){
//                        Thread.sleep(1000);
//                        continue;
//                    }
//                    BBBASerialPortCommDevice.this.onMessage(data);
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        @Override
        public void run() {
            byte[] byteTemp = new byte[1];
            while (!this._stop) {
                try {
                    int data = in.read();
                    if(data == -1){
                        Thread.sleep(1000);
                        continue;
                    }
                    byteTemp[0] = (byte)(data&0xFF);
                    BBBASerialPortCommDevice.this.onMessage(byteTemp);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


//        @Override
//        public void run() {
//            while (!this._stop) {
//                try {
//                    int data = in.read(bufferRead);
//                    if(data > 0){
//                        BBBASerialPortCommDevice.this.onMessage(bufferRead);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        public void setAlive(boolean alive) {
            this._stop = !alive;
        }
    }

    protected void stopComm() {
        commReceiver.setAlive(false);
        if (this.rxThread != null) {
            this.rxThread.interrupt();
        }
        if (this.in != null) {
            try {
                this.in.close();
            } catch (Exception ignore) {

            }
        }
        if (this.out != null) {
            try {
                this.out.close();
            } catch (Exception ignore) {

            }
        }
        if (this.serialPort != null) {
            this.serialPort.disableReceiveTimeout();
            this.serialPort.close();
        }
        this.in = null;
        this.out = null;
    }

    protected void startComm() throws Exception {
        commReceiver.setAlive(true);
        try {
            BISerialService platSvc = (BISerialService) Sys.getService(BISerialService.TYPE);
            this.serialPort = getSettings().open(this.getName());
            this.serialPort.enableReceiveTimeout(platSvc.getMinTimeout());
            this.in = this.serialPort.getInputStream();
            this.out = this.serialPort.getOutputStream();
        } catch (Exception e) {
            this.log.warning("Error opening and configuring the serial port "+e);
            this.log.warning(e.getMessage());
            if (this.in != null) {
                try {
                    this.in.close();
                } catch (Exception ignore) {

                }
            }
            if (this.out != null) {
                try {
                    this.out.close();
                } catch (Exception ignore) {

                }
            }
            if (this.serialPort != null) {
                this.serialPort.close();
            }
            return;
        }
        this.rxThread = new Thread(commReceiver, "commReceiver");
        this.commReceiver.setAlive(true);
        this.rxThread.start();
        this.rxThread.setPriority(5);
        this.log.info("comm started");
    }

    @Override
    public void started() throws Exception {
        commReceiver = new CommReceiver();
        startComm();
        super.started();
        if(getSettings().getStatus().isOk()) {
            init();
        }
    }

    @Override
    public void stopped(){
        closeComntrolBoard();
        this.stopComm();
    }

    private  void closeComntrolBoard(){
        offSwitchSolenoidValveSubBoard1();
        offSwitchSolenoidValveSubBoard2();
        offSwitchWaterPump();
        offSwitchSubBoard1();
        offSwitchSubBoard2();
    }

    @Override
    public void reopenPort() {
        try {
            String newPort = this.getSettings().getPortName();
            if (newPort.equals("none")) {
                this.log.warning("No port selected for serial communication.");
                this.stopComm();
                return;
            }

            this.stopComm();
            this.startComm();
        } catch (Exception var2) {
            this.log.warning("BSerialNetwork caught exception in reopenPort(): "+ var2);
            var2.printStackTrace();
        }
    }

    @Override
    public void changed(Property property, Context context) {
        if (!this.isRunning()) {
            return;
        }

        if(!property.equals(currentReponseMessage)) {
            setLastReponseMessage(strTemp);
        }

        if(property.equals(switchSubBoard1)){
          if (getSwitchSubBoard1().getValue()) {
            onSwitchSubBoard1();
          } else {
            offSwitchSubBoard1();
          }
        }

        if(property.equals(switchSubBoard2)){
            if (getSwitchSubBoard2().getValue()) {
                onSwitchSubBoard2();
            } else {
                offSwitchSubBoard2();
            }
        }

         if(property.equals(switchWaterPump)){
          if (getSwitchWaterPump().getValue()) {
            onSwitchWaterPump();
          } else {
            offSwitchWaterPump();
          }
        }

        if(property.equals(switchSolenoidValveSubBoard1)){
            if (getSwitchSolenoidValveSubBoard1().getValue()) {
                onSwitchSolenoidValveSubBoard1();
            } else {
                offSwitchSolenoidValveSubBoard1();
            }
        }

         if(property.equals(switchSolenoidValveSubBoard2)){
          if (getSwitchSolenoidValveSubBoard2().getValue()) {
            onSwitchSolenoidValveSubBoard2();
          } else {
            offSwitchSolenoidValveSubBoard2();
          }
        }

        super.changed(property, context);

    }

    public void onSwitchSubBoard1(){
        byte[] _header = {0x40};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x37};
        byte[] _message = {0x20,0x20};
        byte[]  _number = {0x31};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
        if(ss1 != null) {
            ss1.cancel();
            ss1 = null;
        }
        ss1 = Clock.schedulePeriodically(this,BRelTime.make(0,0,1,30),powerSwitchSubBoard1,null);
    }
    public void offSwitchSubBoard1(){
        if(ss1 != null) {
            ss1.cancel();
            ss1 = null;
        }
        byte[] _header = {0x40};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x36};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x31};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void onSwitchSubBoard2(){
        byte[] _header = {0x40};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x37};
        byte[] _message = {0x20,0x20};
        byte[]  _number = {0x32};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
        if(ss2 != null) {
            ss2.cancel();
            ss2 = null;
        }
        ss2 = Clock.schedulePeriodically(this,BRelTime.make(0,0,1,30),powerSwitchSubBoard2,null);
    }
    public void offSwitchSubBoard2(){
        if(ss2 != null) {
            ss2.cancel();
            ss2 = null;
        }
        byte[] _header = {0x40};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x36};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x32};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void onSwitchWaterPump(){
        byte[] _header = {0x23};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x31};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x20};
        byte[] _end = {0x0D,0x0A};

        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);

        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void offSwitchWaterPump(){
        byte[] _header = {0x23};
        byte[] _id = {0x20,0x20,0x20,0x20,0x20};
        byte[] _switch = {0x32};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x20};
        byte[] _end = {0x0D,0x0A};

        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);

        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void onSwitchSolenoidValveSubBoard1(){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        if(getIdSolenoidValve() > 0){
            _id  = Integer.toHexString(getIdSolenoidValve()).getBytes(StandardCharsets.UTF_8);
        }
        byte[] _switch = {0x38};
        byte[] _message = {0x31,0x32};
        byte[] _number = {0x31};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void offSwitchSolenoidValveSubBoard1(){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        if(getIdSolenoidValve() > 0){
            _id  = Integer.toHexString(getIdSolenoidValve()).getBytes(StandardCharsets.UTF_8);
        }
        byte[] _switch = {0x39};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x31};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void onSwitchSolenoidValveSubBoard2(){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        if(getIdSolenoidValve() > 0){
            _id  = Integer.toHexString(getIdSolenoidValve()).getBytes(StandardCharsets.UTF_8);
        }
        byte[] _switch = {0x38};
        byte[] _message = {0x31,0x32};
        byte[] _number = {0x32};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void offSwitchSolenoidValveSubBoard2(){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        if(getIdSolenoidValve() > 0){
            _id  = Integer.toHexString(getIdSolenoidValve()).getBytes(StandardCharsets.UTF_8);
        }
        byte[] _switch = {0x39};
        byte[] _message = {0x20,0x20};
        byte[] _number = {0x32};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }

    public byte[] addByte(byte[] data1,byte[] data2,byte[] data3,byte[] data4,byte[] data5,byte[] data6){
               return   addByte(addByte(addByte(addByte(addByte(data1,data2),data3),data4),data5),data6);
    }
    public byte[] addByte(byte[] data1,byte[] data2){
        if(data1 == null || data2 == null){
            if(data1 != null){
                return data1;
            }
            if(data2 != null){
               return data2;
            }
            return  null;
        }
        byte[] data = new byte[data1.length + data2.length];
        System.arraycopy(data1,0,data,0,data1.length);
        System.arraycopy(data2,0,data,data1.length,data2.length);
        return data;
    }

    public void init(){

        if (getSwitchSubBoard1().getValue()) {
            onSwitchSubBoard1();
        } else {
            offSwitchSubBoard1();
        }

        if (getSwitchSubBoard2().getValue()) {
            onSwitchSubBoard2();
        } else {
            offSwitchSubBoard2();
        }

        if (getSwitchWaterPump().getValue()) {
            onSwitchWaterPump();
        } else {
            offSwitchWaterPump();
        }

        if (getSwitchSolenoidValveSubBoard1().getValue()) {
            onSwitchSolenoidValveSubBoard1();
        } else {
            offSwitchSolenoidValveSubBoard1();
        }

        if (getSwitchSolenoidValveSubBoard2().getValue()) {
            onSwitchSolenoidValveSubBoard2();
        } else {
            offSwitchSolenoidValveSubBoard2();
        }

        strTemp = "";

    }

    private Logger log = Logger.getLogger(getClass().getSimpleName());

    public BBBASerialPortCommDevice() {
    }
}
