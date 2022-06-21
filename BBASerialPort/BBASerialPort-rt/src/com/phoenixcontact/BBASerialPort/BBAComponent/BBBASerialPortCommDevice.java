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
@NiagaraProperty(name = "switchSubBoard1", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchWaterPump", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve1", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve2", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve3", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve4", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve5", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve6", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve7", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve8", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve9", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve10", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve11", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve12", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve13", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve14", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve15", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "idSolenoidValve16", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve1", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve2", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve3", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve4", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve5", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve6", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve7", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve8", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve9", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve10", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve11", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve12", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve13", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve14", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve15", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve16", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
//@NiagaraProperty(name = "switchClearAlarm", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)
@NiagaraAction(name = "sendCommand",defaultValue = "BString.DEFAULT",parameterType = "BString")
@NiagaraAction(name = "powerSwitchSubBoard1",flags = Flags.ASYNC | Flags.HIDDEN)
@NiagaraAction(name = "powerSwitchSubBoard2",flags = Flags.ASYNC | Flags.HIDDEN)
@NiagaraAction(name = "reConnect",flags = Flags.ASYNC | Flags.SUMMARY)
@NiagaraAction(name = "readCurrentReponseMessage",flags = Flags.ASYNC | Flags.SUMMARY)
public class BBBASerialPortCommDevice extends BComponent implements BISerialHelperParent {

    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBAComponent.BBBASerialPortCommDevice(3258333159)1.0$ @*/
/* Generated Tue Jun 21 15:11:43 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "idSolenoidValve1"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve1} property.
   * @see #getIdSolenoidValve1
   * @see #setIdSolenoidValve1
   */
  public static final Property idSolenoidValve1 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve1} property.
   * @see #idSolenoidValve1
   */
  public String getIdSolenoidValve1() { return getString(idSolenoidValve1); }
  
  /**
   * Set the {@code idSolenoidValve1} property.
   * @see #idSolenoidValve1
   */
  public void setIdSolenoidValve1(String v) { setString(idSolenoidValve1, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve2"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve2} property.
   * @see #getIdSolenoidValve2
   * @see #setIdSolenoidValve2
   */
  public static final Property idSolenoidValve2 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve2} property.
   * @see #idSolenoidValve2
   */
  public String getIdSolenoidValve2() { return getString(idSolenoidValve2); }
  
  /**
   * Set the {@code idSolenoidValve2} property.
   * @see #idSolenoidValve2
   */
  public void setIdSolenoidValve2(String v) { setString(idSolenoidValve2, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve3"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve3} property.
   * @see #getIdSolenoidValve3
   * @see #setIdSolenoidValve3
   */
  public static final Property idSolenoidValve3 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve3} property.
   * @see #idSolenoidValve3
   */
  public String getIdSolenoidValve3() { return getString(idSolenoidValve3); }
  
  /**
   * Set the {@code idSolenoidValve3} property.
   * @see #idSolenoidValve3
   */
  public void setIdSolenoidValve3(String v) { setString(idSolenoidValve3, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve4"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve4} property.
   * @see #getIdSolenoidValve4
   * @see #setIdSolenoidValve4
   */
  public static final Property idSolenoidValve4 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve4} property.
   * @see #idSolenoidValve4
   */
  public String getIdSolenoidValve4() { return getString(idSolenoidValve4); }
  
  /**
   * Set the {@code idSolenoidValve4} property.
   * @see #idSolenoidValve4
   */
  public void setIdSolenoidValve4(String v) { setString(idSolenoidValve4, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve5"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve5} property.
   * @see #getIdSolenoidValve5
   * @see #setIdSolenoidValve5
   */
  public static final Property idSolenoidValve5 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve5} property.
   * @see #idSolenoidValve5
   */
  public String getIdSolenoidValve5() { return getString(idSolenoidValve5); }
  
  /**
   * Set the {@code idSolenoidValve5} property.
   * @see #idSolenoidValve5
   */
  public void setIdSolenoidValve5(String v) { setString(idSolenoidValve5, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve6"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve6} property.
   * @see #getIdSolenoidValve6
   * @see #setIdSolenoidValve6
   */
  public static final Property idSolenoidValve6 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve6} property.
   * @see #idSolenoidValve6
   */
  public String getIdSolenoidValve6() { return getString(idSolenoidValve6); }
  
  /**
   * Set the {@code idSolenoidValve6} property.
   * @see #idSolenoidValve6
   */
  public void setIdSolenoidValve6(String v) { setString(idSolenoidValve6, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve7"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve7} property.
   * @see #getIdSolenoidValve7
   * @see #setIdSolenoidValve7
   */
  public static final Property idSolenoidValve7 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve7} property.
   * @see #idSolenoidValve7
   */
  public String getIdSolenoidValve7() { return getString(idSolenoidValve7); }
  
  /**
   * Set the {@code idSolenoidValve7} property.
   * @see #idSolenoidValve7
   */
  public void setIdSolenoidValve7(String v) { setString(idSolenoidValve7, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve8"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve8} property.
   * @see #getIdSolenoidValve8
   * @see #setIdSolenoidValve8
   */
  public static final Property idSolenoidValve8 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve8} property.
   * @see #idSolenoidValve8
   */
  public String getIdSolenoidValve8() { return getString(idSolenoidValve8); }
  
  /**
   * Set the {@code idSolenoidValve8} property.
   * @see #idSolenoidValve8
   */
  public void setIdSolenoidValve8(String v) { setString(idSolenoidValve8, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve9"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve9} property.
   * @see #getIdSolenoidValve9
   * @see #setIdSolenoidValve9
   */
  public static final Property idSolenoidValve9 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve9} property.
   * @see #idSolenoidValve9
   */
  public String getIdSolenoidValve9() { return getString(idSolenoidValve9); }
  
  /**
   * Set the {@code idSolenoidValve9} property.
   * @see #idSolenoidValve9
   */
  public void setIdSolenoidValve9(String v) { setString(idSolenoidValve9, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve10"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve10} property.
   * @see #getIdSolenoidValve10
   * @see #setIdSolenoidValve10
   */
  public static final Property idSolenoidValve10 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve10} property.
   * @see #idSolenoidValve10
   */
  public String getIdSolenoidValve10() { return getString(idSolenoidValve10); }
  
  /**
   * Set the {@code idSolenoidValve10} property.
   * @see #idSolenoidValve10
   */
  public void setIdSolenoidValve10(String v) { setString(idSolenoidValve10, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve11"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve11} property.
   * @see #getIdSolenoidValve11
   * @see #setIdSolenoidValve11
   */
  public static final Property idSolenoidValve11 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve11} property.
   * @see #idSolenoidValve11
   */
  public String getIdSolenoidValve11() { return getString(idSolenoidValve11); }
  
  /**
   * Set the {@code idSolenoidValve11} property.
   * @see #idSolenoidValve11
   */
  public void setIdSolenoidValve11(String v) { setString(idSolenoidValve11, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve12"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve12} property.
   * @see #getIdSolenoidValve12
   * @see #setIdSolenoidValve12
   */
  public static final Property idSolenoidValve12 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve12} property.
   * @see #idSolenoidValve12
   */
  public String getIdSolenoidValve12() { return getString(idSolenoidValve12); }
  
  /**
   * Set the {@code idSolenoidValve12} property.
   * @see #idSolenoidValve12
   */
  public void setIdSolenoidValve12(String v) { setString(idSolenoidValve12, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve13"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve13} property.
   * @see #getIdSolenoidValve13
   * @see #setIdSolenoidValve13
   */
  public static final Property idSolenoidValve13 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve13} property.
   * @see #idSolenoidValve13
   */
  public String getIdSolenoidValve13() { return getString(idSolenoidValve13); }
  
  /**
   * Set the {@code idSolenoidValve13} property.
   * @see #idSolenoidValve13
   */
  public void setIdSolenoidValve13(String v) { setString(idSolenoidValve13, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve14"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve14} property.
   * @see #getIdSolenoidValve14
   * @see #setIdSolenoidValve14
   */
  public static final Property idSolenoidValve14 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve14} property.
   * @see #idSolenoidValve14
   */
  public String getIdSolenoidValve14() { return getString(idSolenoidValve14); }
  
  /**
   * Set the {@code idSolenoidValve14} property.
   * @see #idSolenoidValve14
   */
  public void setIdSolenoidValve14(String v) { setString(idSolenoidValve14, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve15"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve15} property.
   * @see #getIdSolenoidValve15
   * @see #setIdSolenoidValve15
   */
  public static final Property idSolenoidValve15 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve15} property.
   * @see #idSolenoidValve15
   */
  public String getIdSolenoidValve15() { return getString(idSolenoidValve15); }
  
  /**
   * Set the {@code idSolenoidValve15} property.
   * @see #idSolenoidValve15
   */
  public void setIdSolenoidValve15(String v) { setString(idSolenoidValve15, v, null); }

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve16"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve16} property.
   * @see #getIdSolenoidValve16
   * @see #setIdSolenoidValve16
   */
  public static final Property idSolenoidValve16 = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve16} property.
   * @see #idSolenoidValve16
   */
  public String getIdSolenoidValve16() { return getString(idSolenoidValve16); }
  
  /**
   * Set the {@code idSolenoidValve16} property.
   * @see #idSolenoidValve16
   */
  public void setIdSolenoidValve16(String v) { setString(idSolenoidValve16, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve1"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve1} property.
   * @see #getSwitchSolenoidValve1
   * @see #setSwitchSolenoidValve1
   */
  public static final Property switchSolenoidValve1 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve1} property.
   * @see #switchSolenoidValve1
   */
  public BStatusBoolean getSwitchSolenoidValve1() { return (BStatusBoolean)get(switchSolenoidValve1); }
  
  /**
   * Set the {@code switchSolenoidValve1} property.
   * @see #switchSolenoidValve1
   */
  public void setSwitchSolenoidValve1(BStatusBoolean v) { set(switchSolenoidValve1, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve2"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve2} property.
   * @see #getSwitchSolenoidValve2
   * @see #setSwitchSolenoidValve2
   */
  public static final Property switchSolenoidValve2 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve2} property.
   * @see #switchSolenoidValve2
   */
  public BStatusBoolean getSwitchSolenoidValve2() { return (BStatusBoolean)get(switchSolenoidValve2); }
  
  /**
   * Set the {@code switchSolenoidValve2} property.
   * @see #switchSolenoidValve2
   */
  public void setSwitchSolenoidValve2(BStatusBoolean v) { set(switchSolenoidValve2, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve3"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve3} property.
   * @see #getSwitchSolenoidValve3
   * @see #setSwitchSolenoidValve3
   */
  public static final Property switchSolenoidValve3 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve3} property.
   * @see #switchSolenoidValve3
   */
  public BStatusBoolean getSwitchSolenoidValve3() { return (BStatusBoolean)get(switchSolenoidValve3); }
  
  /**
   * Set the {@code switchSolenoidValve3} property.
   * @see #switchSolenoidValve3
   */
  public void setSwitchSolenoidValve3(BStatusBoolean v) { set(switchSolenoidValve3, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve4"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve4} property.
   * @see #getSwitchSolenoidValve4
   * @see #setSwitchSolenoidValve4
   */
  public static final Property switchSolenoidValve4 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve4} property.
   * @see #switchSolenoidValve4
   */
  public BStatusBoolean getSwitchSolenoidValve4() { return (BStatusBoolean)get(switchSolenoidValve4); }
  
  /**
   * Set the {@code switchSolenoidValve4} property.
   * @see #switchSolenoidValve4
   */
  public void setSwitchSolenoidValve4(BStatusBoolean v) { set(switchSolenoidValve4, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve5"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve5} property.
   * @see #getSwitchSolenoidValve5
   * @see #setSwitchSolenoidValve5
   */
  public static final Property switchSolenoidValve5 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve5} property.
   * @see #switchSolenoidValve5
   */
  public BStatusBoolean getSwitchSolenoidValve5() { return (BStatusBoolean)get(switchSolenoidValve5); }
  
  /**
   * Set the {@code switchSolenoidValve5} property.
   * @see #switchSolenoidValve5
   */
  public void setSwitchSolenoidValve5(BStatusBoolean v) { set(switchSolenoidValve5, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve6"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve6} property.
   * @see #getSwitchSolenoidValve6
   * @see #setSwitchSolenoidValve6
   */
  public static final Property switchSolenoidValve6 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve6} property.
   * @see #switchSolenoidValve6
   */
  public BStatusBoolean getSwitchSolenoidValve6() { return (BStatusBoolean)get(switchSolenoidValve6); }
  
  /**
   * Set the {@code switchSolenoidValve6} property.
   * @see #switchSolenoidValve6
   */
  public void setSwitchSolenoidValve6(BStatusBoolean v) { set(switchSolenoidValve6, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve7"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve7} property.
   * @see #getSwitchSolenoidValve7
   * @see #setSwitchSolenoidValve7
   */
  public static final Property switchSolenoidValve7 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve7} property.
   * @see #switchSolenoidValve7
   */
  public BStatusBoolean getSwitchSolenoidValve7() { return (BStatusBoolean)get(switchSolenoidValve7); }
  
  /**
   * Set the {@code switchSolenoidValve7} property.
   * @see #switchSolenoidValve7
   */
  public void setSwitchSolenoidValve7(BStatusBoolean v) { set(switchSolenoidValve7, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve8"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve8} property.
   * @see #getSwitchSolenoidValve8
   * @see #setSwitchSolenoidValve8
   */
  public static final Property switchSolenoidValve8 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve8} property.
   * @see #switchSolenoidValve8
   */
  public BStatusBoolean getSwitchSolenoidValve8() { return (BStatusBoolean)get(switchSolenoidValve8); }
  
  /**
   * Set the {@code switchSolenoidValve8} property.
   * @see #switchSolenoidValve8
   */
  public void setSwitchSolenoidValve8(BStatusBoolean v) { set(switchSolenoidValve8, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve9"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve9} property.
   * @see #getSwitchSolenoidValve9
   * @see #setSwitchSolenoidValve9
   */
  public static final Property switchSolenoidValve9 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve9} property.
   * @see #switchSolenoidValve9
   */
  public BStatusBoolean getSwitchSolenoidValve9() { return (BStatusBoolean)get(switchSolenoidValve9); }
  
  /**
   * Set the {@code switchSolenoidValve9} property.
   * @see #switchSolenoidValve9
   */
  public void setSwitchSolenoidValve9(BStatusBoolean v) { set(switchSolenoidValve9, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve10"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve10} property.
   * @see #getSwitchSolenoidValve10
   * @see #setSwitchSolenoidValve10
   */
  public static final Property switchSolenoidValve10 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve10} property.
   * @see #switchSolenoidValve10
   */
  public BStatusBoolean getSwitchSolenoidValve10() { return (BStatusBoolean)get(switchSolenoidValve10); }
  
  /**
   * Set the {@code switchSolenoidValve10} property.
   * @see #switchSolenoidValve10
   */
  public void setSwitchSolenoidValve10(BStatusBoolean v) { set(switchSolenoidValve10, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve11"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve11} property.
   * @see #getSwitchSolenoidValve11
   * @see #setSwitchSolenoidValve11
   */
  public static final Property switchSolenoidValve11 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve11} property.
   * @see #switchSolenoidValve11
   */
  public BStatusBoolean getSwitchSolenoidValve11() { return (BStatusBoolean)get(switchSolenoidValve11); }
  
  /**
   * Set the {@code switchSolenoidValve11} property.
   * @see #switchSolenoidValve11
   */
  public void setSwitchSolenoidValve11(BStatusBoolean v) { set(switchSolenoidValve11, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve12"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve12} property.
   * @see #getSwitchSolenoidValve12
   * @see #setSwitchSolenoidValve12
   */
  public static final Property switchSolenoidValve12 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve12} property.
   * @see #switchSolenoidValve12
   */
  public BStatusBoolean getSwitchSolenoidValve12() { return (BStatusBoolean)get(switchSolenoidValve12); }
  
  /**
   * Set the {@code switchSolenoidValve12} property.
   * @see #switchSolenoidValve12
   */
  public void setSwitchSolenoidValve12(BStatusBoolean v) { set(switchSolenoidValve12, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve13"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve13} property.
   * @see #getSwitchSolenoidValve13
   * @see #setSwitchSolenoidValve13
   */
  public static final Property switchSolenoidValve13 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve13} property.
   * @see #switchSolenoidValve13
   */
  public BStatusBoolean getSwitchSolenoidValve13() { return (BStatusBoolean)get(switchSolenoidValve13); }
  
  /**
   * Set the {@code switchSolenoidValve13} property.
   * @see #switchSolenoidValve13
   */
  public void setSwitchSolenoidValve13(BStatusBoolean v) { set(switchSolenoidValve13, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve14"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve14} property.
   * @see #getSwitchSolenoidValve14
   * @see #setSwitchSolenoidValve14
   */
  public static final Property switchSolenoidValve14 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve14} property.
   * @see #switchSolenoidValve14
   */
  public BStatusBoolean getSwitchSolenoidValve14() { return (BStatusBoolean)get(switchSolenoidValve14); }
  
  /**
   * Set the {@code switchSolenoidValve14} property.
   * @see #switchSolenoidValve14
   */
  public void setSwitchSolenoidValve14(BStatusBoolean v) { set(switchSolenoidValve14, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve15"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve15} property.
   * @see #getSwitchSolenoidValve15
   * @see #setSwitchSolenoidValve15
   */
  public static final Property switchSolenoidValve15 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve15} property.
   * @see #switchSolenoidValve15
   */
  public BStatusBoolean getSwitchSolenoidValve15() { return (BStatusBoolean)get(switchSolenoidValve15); }
  
  /**
   * Set the {@code switchSolenoidValve15} property.
   * @see #switchSolenoidValve15
   */
  public void setSwitchSolenoidValve15(BStatusBoolean v) { set(switchSolenoidValve15, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve16"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve16} property.
   * @see #getSwitchSolenoidValve16
   * @see #setSwitchSolenoidValve16
   */
  public static final Property switchSolenoidValve16 = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve16} property.
   * @see #switchSolenoidValve16
   */
  public BStatusBoolean getSwitchSolenoidValve16() { return (BStatusBoolean)get(switchSolenoidValve16); }
  
  /**
   * Set the {@code switchSolenoidValve16} property.
   * @see #switchSolenoidValve16
   */
  public void setSwitchSolenoidValve16(BStatusBoolean v) { set(switchSolenoidValve16, v, null); }

////////////////////////////////////////////////////////////////
// Action "sendCommand"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code sendCommand} action.
   * @see #sendCommand(BString parameter)
   */
  public static final Action sendCommand = newAction(0, BString.DEFAULT, null);
  
  /**
   * Invoke the {@code sendCommand} action.
   * @see #sendCommand
   */
  public void sendCommand(BString parameter) { invoke(sendCommand, parameter, null); }

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

    public void doSendCommand(BString v){
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
//        onSwitchSubBoard2();
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
        closeControlBoard();
        this.stopComm();
    }

    private  void closeControlBoard(){
        for(int i=1;i<=16;i++){
            offSwitchSolenoidValve(i);
        }
        offSwitchWaterPump();
        offSwitchSubBoard1();
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

         if(property.equals(switchWaterPump)){
          if (getSwitchWaterPump().getValue()) {
            onSwitchWaterPump();
          } else {
            offSwitchWaterPump();
          }
        }

        for(int i=1;i<=16;i++) {
            if (property.equals(getProperty("switchSolenoidValve" + i))) {
                if ( ((BStatusBoolean)get("switchSolenoidValve"+i)).getValue()) {
                    onSwitchSolenoidValve(i);
                } else {
                    offSwitchSolenoidValve(i);
                }
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
    public void onSwitchSolenoidValve(int i){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        _id  = stringToAscii(get("idSolenoidValve"+i).toString());
        byte[] _switch = {0x38};
        byte[] _message = {0x39,0x39};
        byte[] _number = {0x31};
        byte[] _end = {0x0D,0x0A};
        byte[] temp1 = addByte(_header,_id,_switch,_message,_number,_end);
        try {
            send(temp1);
        }catch (Exception e){
            log.warning(e.getMessage());
        }
    }
    public void offSwitchSolenoidValve(int i){
        byte[] _header = {0x3A};
        byte[] _id = {0x20};
        _id  = stringToAscii(get("idSolenoidValve"+i).toString());
        byte[] _switch = {0x39};
        byte[] _message = {0x39,0x39};
        byte[] _number = {0x31};
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
    public byte[] stringToAscii(String value){
        char[] chars = value.toCharArray();
        int count = chars.length;
        if(count <= 0){
            return  null;
        }
        byte[] _ret = new byte[count];
        for (int i = 0; i < count; i++) {
            _ret[i] = Byte.parseByte(Integer.toHexString((int) chars[i]));
        }
        return _ret;
    }

    public void init(){

        if (getSwitchSubBoard1().getValue()) {
            onSwitchSubBoard1();
        } else {
            offSwitchSubBoard1();
        }

        if (getSwitchWaterPump().getValue()) {
            onSwitchWaterPump();
        } else {
            offSwitchWaterPump();
        }

        for(int i=1;i<=16;i++){
          if ( ((BStatusBoolean)get("switchSolenoidValve"+i)).getValue() ) {
                onSwitchSolenoidValve(i);
            } else {
                offSwitchSolenoidValve(i);
            }
        }

        strTemp = "";

    }

    private Logger log = Logger.getLogger(getClass().getSimpleName());

    public BBBASerialPortCommDevice() {
    }
}
