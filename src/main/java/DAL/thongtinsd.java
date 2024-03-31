/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thongtinsd")
public class thongtinsd implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;
    @ManyToOne
    @JoinColumn(name = "maTV")
    private int maTV;
    @ManyToOne
    @JoinColumn(name = "maTB")
    private int maTB;
    @Column(name = "TGVao")
    private Date tgVao;
    @Column(name = "TGMuon")
    private Date tgMuon;
    @Column(name = "TGTra")
    private Date tgTra;

    public thongtinsd() {
    }

    public thongtinsd(int maTT, int maTV, int maTB, Date tgVao, Date tgMuon, Date tgTra) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maTB = maTB;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public Date getTgVao() {
        return tgVao;
    }

    public void setTgVao(Date tgVao) {
        this.tgVao = tgVao;
    }

    public Date getTgMuon() {
        return tgMuon;
    }

    public void setTgMuon(Date tgMuon) {
        this.tgMuon = tgMuon;
    }

    public Date getTgTra() {
        return tgTra;
    }

    public void setTgTra(Date tgTra) {
        this.tgTra = tgTra;
    }
    
    

}
