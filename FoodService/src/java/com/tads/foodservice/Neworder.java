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
import javax.persistence.Lob;
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
 * create table neworder( orderID int not null auto_increment, img text not null, nome varchar(30) not null,valor real ,primary key(orderID));
  insert into neworder(img,nome,valor)values('coca.jpg','Coca-cola',2.25);
  insert into neworder(img,nome,valor)values('fanta.jpg','Fanta',2.25);
   insert into neworder(img,nome,valor)values('chocolate.jpg','Bolo de Chocolate',5.35);
   insert into neworder(img,nome,valor)values('sorvete.jpg','Sorvete',8.50); 
   insert into neworder(img,nome,valor)values('pasta.jpg','Macarrao',12.89); 
   insert into neworder(img,nome,valor)values('lasanha.jpg','Lazanha Bolonhesa',17.39);

 */
@Entity
@Table(name = "neworder")
//@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Neworder.findAll", query = "SELECT n FROM Neworder n"),
    @NamedQuery(name = "Neworder.findByOrderID", query = "SELECT n FROM Neworder n WHERE n.orderID = :orderID"),
    @NamedQuery(name = "Neworder.findByNome", query = "SELECT n FROM Neworder n WHERE n.nome = :nome"),
    @NamedQuery(name = "Neworder.findByValor", query = "SELECT n FROM Neworder n WHERE n.valor = :valor")})
public class Neworder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "orderID")
    private Integer orderID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "img")
    private String img;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nome")
    private String nome;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private Double valor;

    public Neworder() {
    }

    public Neworder(Integer orderID) {
        this.orderID = orderID;
    }

    public Neworder(Integer orderID, String img, String nome) {
        this.orderID = orderID;
        this.img = img;
        this.nome = nome;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Neworder)) {
            return false;
        }
        Neworder other = (Neworder) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tads.foodservice.Neworder[ orderID=" + orderID + " ]";
    }
    
}
