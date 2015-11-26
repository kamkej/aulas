/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tads.foodservice;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author julio
 * 
 * create table payorder( payid int not null auto_increment, item varchar(30) not null, status int not null, tipo varchar(10), userid int, qtd int, valor real, primary key(payid));

 * 
 * 
 */
@Entity
@Table(name = "payorder")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Payorder.findAll", query = "SELECT p FROM Payorder p"),
    @NamedQuery(name = "Payorder.findByPayid", query = "SELECT p FROM Payorder p WHERE p.payid = :payid"),
    @NamedQuery(name = "Payorder.findByItem", query = "SELECT p FROM Payorder p WHERE p.item = :item"),
    @NamedQuery(name = "Payorder.findByStatus", query = "SELECT p FROM Payorder p WHERE p.status = :status"),
    @NamedQuery(name = "Payorder.findByTipo", query = "SELECT p FROM Payorder p WHERE p.tipo = :tipo"),
    @NamedQuery(name = "Payorder.findByUserid", query = "SELECT p FROM Payorder p WHERE p.userid = :userid and p.status = :status"),
    @NamedQuery(name = "Payorder.findByQtd", query = "SELECT p FROM Payorder p WHERE p.qtd = :qtd"),
    @NamedQuery(name = "Payorder.findByValor", query = "SELECT p FROM Payorder p WHERE p.valor = :valor")})
public class Payorder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "payid")
    private Integer payid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "item")
    private String item;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @Size(max = 10)
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "qtd")
    private Integer qtd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;

    public Payorder() {
    }

    public Payorder(Integer payid) {
        this.payid = payid;
    }

    public Payorder(Integer payid, String item, int status) {
        this.payid = payid;
        this.item = item;
        this.status = status;
    }

    public Integer getPayid() {
        return payid;
    }

    public void setPayid(Integer payid) {
        this.payid = payid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (payid != null ? payid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Payorder)) {
            return false;
        }
        Payorder other = (Payorder) object;
        if ((this.payid == null && other.payid != null) || (this.payid != null && !this.payid.equals(other.payid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tads.foodservice.Payorder[ payid=" + payid + " ]";
    }
    
}
