/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author Aplal
 */
public class Categories {
    private int idc;
    private String namec;
    private Date createat, updateat;

    public Categories() {
    }

    public Categories(int idc, String namec, Date createat, Date updateat) {
        this.idc = idc;
        this.namec = namec;
        this.createat = createat;
        this.updateat = updateat;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public String getNamec() {
        return namec;
    }

    public void setNamec(String namec) {
        this.namec = namec;
    }

    public Date getCreateat() {
        return createat;
    }

    public void setCreateat(Date createat) {
        this.createat = createat;
    }

    public Date getUpdateat() {
        return updateat;
    }

    public void setUpdateat(Date updateat) {
        this.updateat = updateat;
    }

    @Override
    public String toString() {
        return "Categories{" + "idc=" + idc + ", namec=" + namec + ", createat=" + createat + ", updateat=" + updateat + '}';
    }
    
}
