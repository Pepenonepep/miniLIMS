package com.labware.minilims.domain.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import com.labware.minilims.domain.entities.ParameterAnalysis;
import com.labware.minilims.domain.entities.Specimen;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_result_analysis")
public class ResultAnalysis implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double measuredValue;
    private LocalDateTime dateAnalyzed;
    private boolean according;

    @ManyToOne
    @JoinColumn(name = "specimen_id")
    private Specimen specimen;

    @ManyToOne
    @JoinColumn(name = "parameter_id")
    private ParameterAnalysis parameterAnalysis;

    public ResultAnalysis() {
    }

    public ResultAnalysis(Long id, Double measuredValue, LocalDateTime dateAnalyzed, Specimen specimen,
            ParameterAnalysis parameterAnalysis) {
        this.id = id;
        this.measuredValue = measuredValue;
        this.dateAnalyzed = dateAnalyzed;
        this.specimen = specimen;
        this.parameterAnalysis = parameterAnalysis;
        validateConformity();
    }

    public boolean validateConformity() {
        if (this.parameterAnalysis != null && this.measuredValue != null) {
            this.according = this.measuredValue >= this.parameterAnalysis.getMinValue()
                    && this.measuredValue <= this.parameterAnalysis.getMaxValue();
        } else {
            this.according = false;
        }
        return this.according;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(Double measuredValue) {
        this.measuredValue = measuredValue;
        validateConformity();
    }

    public LocalDateTime getDateAnalyzed() {
        return dateAnalyzed;
    }

    public void setDateAnalyzed(LocalDateTime dateAnalyzed) {
        this.dateAnalyzed = dateAnalyzed;
    }

    public boolean isAccording() {
        return according;
    }

    public void setAccording(boolean according) {
        this.according = according;
    }

    public Specimen getSpecimen() {
        return specimen;
    }

    public void setSpecimen(Specimen specimen) {
        this.specimen = specimen;
    }

    public ParameterAnalysis getParameterAnalysis() {
        return parameterAnalysis;
    }

    public void setParameterAnalysis(ParameterAnalysis parameterAnalysis) {
        this.parameterAnalysis = parameterAnalysis;
        validateConformity();
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
        ResultAnalysis other = (ResultAnalysis) obj;
        return Objects.equals(id, other.id);
    }

}