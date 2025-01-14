/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author asus
 */
public class VoteDTO {

    @Override
    public String toString() {
        return "VoteDTO{" + "candidateId=" + candidateId + ", voterId=" + voterId + '}';
    }

    public VoteDTO(String candidateId, String voterId) {
        this.candidateId = candidateId;
        this.voterId = voterId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }
    private String candidateId;
    private String voterId;
}
