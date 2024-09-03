/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

import java.io.InputStream;

/**
 *
 * @author asus
 */
public class CandidateDTO {

    public CandidateDTO(){}
    @Override
    public String toString() {
        return "CandidateDTO{" + "CandidateId=" + CandidateId + ", party=" + party + ", city=" + city + ", userid=" + userid + ", symbol=" + symbol + '}';
    }

    public String getCandidateId() {
        return CandidateId;
    }

    public void setCandidateId(String CandidateId) {
        this.CandidateId = CandidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    public CandidateDTO(String CandidateId, String party, String city, String userid, InputStream symbol) {
        this.CandidateId = CandidateId;
        this.party = party;
        this.city = city;
        this.userid = userid;
        this.symbol = symbol;
    }
    
    private String CandidateId ;
    private String party ;
    private String city ;
    private String userid ;
    private InputStream symbol ;
}
