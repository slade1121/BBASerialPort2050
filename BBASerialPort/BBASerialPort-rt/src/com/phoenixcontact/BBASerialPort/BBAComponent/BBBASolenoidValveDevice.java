/*
 * Classname:   BBBASolenoidValveDevice
 *
 * Version:     1.0
 *
 * Date:        2022/6/22
 *
 * Copyright (c) 2022 PHOENIX CONTACT
 */

package com.phoenixcontact.BBASerialPort.BBAComponent;

import javax.baja.driver.BDriverContainer;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.status.BStatus;
import javax.baja.status.BStatusBoolean;
import javax.baja.sys.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * TODO Description
 *
 * @author slade
 * @version 1.0
 * @since 2022/6/22
 */

@NiagaraType

@NiagaraProperty(name = "idSolenoidValve", type = "BString", defaultValue = "0", flags = Flags.SUMMARY)
@NiagaraProperty(name = "switchSolenoidValve", type = "BStatusBoolean", defaultValue = "new BStatusBoolean(false, BStatus.ok)", flags = Flags.SUMMARY)

public class BBBASolenoidValveDevice extends BComponent {
    //region Slotomatic   
    
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.phoenixcontact.BBASerialPort.BBAComponent.BBBASolenoidValveDevice(3805294540)1.0$ @*/
/* Generated Wed Jun 22 11:19:23 CST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "idSolenoidValve"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code idSolenoidValve} property.
   * @see #getIdSolenoidValve
   * @see #setIdSolenoidValve
   */
  public static final Property idSolenoidValve = newProperty(Flags.SUMMARY, "0", null);
  
  /**
   * Get the {@code idSolenoidValve} property.
   * @see #idSolenoidValve
   */
  public String getIdSolenoidValve() { return getString(idSolenoidValve); }
  
  /**
   * Set the {@code idSolenoidValve} property.
   * @see #idSolenoidValve
   */
  public void setIdSolenoidValve(String v) { setString(idSolenoidValve, v, null); }

////////////////////////////////////////////////////////////////
// Property "switchSolenoidValve"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code switchSolenoidValve} property.
   * @see #getSwitchSolenoidValve
   * @see #setSwitchSolenoidValve
   */
  public static final Property switchSolenoidValve = newProperty(Flags.SUMMARY, new BStatusBoolean(false, BStatus.ok), null);
  
  /**
   * Get the {@code switchSolenoidValve} property.
   * @see #switchSolenoidValve
   */
  public BStatusBoolean getSwitchSolenoidValve() { return (BStatusBoolean)get(switchSolenoidValve); }
  
  /**
   * Set the {@code switchSolenoidValve} property.
   * @see #switchSolenoidValve
   */
  public void setSwitchSolenoidValve(BStatusBoolean v) { set(switchSolenoidValve, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBBASolenoidValveDevice.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/
    //endregion Slotomatic

    //region Attributes


    private static final BIcon icon = BIcon.make("module://emalyticsHVAC/icons/pxcLogo.png");

    private Logger log = Logger.getLogger(getClass().getSimpleName());

    //endregion Attributes

    //region Constructors

    /**
     * Public parameterless constructor of the class.
     */
    public BBBASolenoidValveDevice() {
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
        if (!this.isRunning()) {
            return;
        }
//        if(property.equals(idSolenoidValve)){
////          Property p = this.getParent().getProperty("idSolenoidValve");
////          BValue b = get("idSolenoidValve");
////          set(p,b);
//        }

        if(property.equals(switchSolenoidValve)){
          if (getSwitchSolenoidValve().getValue()) {
            BBBASerialPortCommDevice.getInstance().onSwitchSolenoidValve(getIdSolenoidValve());
          } else {
            BBBASerialPortCommDevice.getInstance().offSwitchSolenoidValve(getIdSolenoidValve());
          }

        }

    }

  @Override
  public void stopped(){
    BBBASerialPortCommDevice.getInstance().offSwitchSolenoidValve(getIdSolenoidValve());
  }


//    @Override
//    public boolean isParentLegal(BComponent parent) {
//        return parent instanceof BBBASerialPortCommDevice;
//    }

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