package com.phoenixcontact.BBASerialPort;

import javax.baja.driver.BDeviceFolder;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;


@NiagaraType
public class BBBASerialPortDeviceFolder extends BDeviceFolder {
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBBASerialPortDeviceFolder(2979906276)1.0$ @*/
/* Generated Fri May 20 15:16:03 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBBASerialPortDeviceFolder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


    public BBBASerialPortDeviceFolder() {
    }

//    public boolean isParentLegal(BComponent parent) {
//        return parent instanceof BBBASerialPortNetwork || parent instanceof BBBASerialPortDeviceFolder;
//    }

}
