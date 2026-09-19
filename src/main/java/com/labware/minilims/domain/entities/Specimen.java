package com.labware.minilims.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.labware.minilims.domain.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_specimen")
public class Specimen implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trackingCode;
    private String description;
    private LocalDateTime dateReceived;

    @Enumerated(EnumType.STRING)
    private Status statusSpecimen;

    @OneToMany(mappedBy = "specimen", cascade = CascadeType.ALL)
    private List<ResultAnalysis> resultAnalysis = new ArrayList<>();

    public Specimen() {
    }

    public Specimen(Long id, String trackingCode, String description, LocalDateTime dateReceived,
            Status statusSpecimen) {
        this.id = id;
        this.trackingCode = trackingCode;
        this.description = description;
        this.dateReceived = dateReceived;
        this.statusSpecimen = statusSpecimen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(LocalDateTime dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Status getStatusSpecimen() {
        return statusSpecimen;
    }

    public void setStatusSpecimen(Status statusSpecimen) {
        this.statusSpecimen = statusSpecimen;
    }

    public List<ResultAnalysis> getResultAnalysis() {
        return resultAnalysis;
    }

    public void addResult(ResultAnalysis result) {
        this.resultAnalysis.add(result);
        result.setSpecimen(this);
        checkAllStatus();
    }

    private void checkAllStatus() {
        if (this.resultAnalysis.isEmpty()) {
            this.statusSpecimen = Status.RECEIVED;
            return;
        }

        boolean hasAnalysisInProgress = this.resultAnalysis.stream().anyMatch(r -> r.getMeasuredValue() == null);

        if (hasAnalysisInProgress) {
            this.statusSpecimen = Status.ANALYSIS;
            return;
        }

        boolean hasRejection = this.resultAnalysis.stream().anyMatch(r -> !r.isAccording());

        if (hasRejection) {
            this.statusSpecimen = Status.REJECTED;
            return;
        }

        this.statusSpecimen = Status.APPROVED;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Specimen other = (Specimen) obj;
        return Objects.equals(id, other.id);
    }
}