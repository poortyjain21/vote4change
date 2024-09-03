/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

import java.io.InputStream;
import java.util.Objects;

/**
 *
 * @author asus
 */
public class UpdateCandidateDTO {

    @Override
    public String toString() {
        return "UpdateCandidateDTO{" + "cid=" + cid + ", city=" + city + ", party=" + party + ", symbol=" + symbol + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.cid);
        hash = 59 * hash + Objects.hashCode(this.city);
        hash = 59 * hash + Objects.hashCode(this.party);
        hash = 59 * hash + Objects.hashCode(this.symbol);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UpdateCandidateDTO other = (UpdateCandidateDTO) obj;
        if (!Objects.equals(this.cid, other.cid)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        return Objects.equals(this.symbol, other.symbol);
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    public UpdateCandidateDTO(String cid, String city, String party, InputStream symbol) {
        this.cid = cid;
        this.city = city;
        this.party = party;
        this.symbol = symbol;
    }

   

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

   

   

   

    public UpdateCandidateDTO() {
    }
    
    private String cid ;
    private String city ;
    private String party ;
    private InputStream symbol ; 
}
