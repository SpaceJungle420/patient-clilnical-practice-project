package com.example.clinicals.clinicalsapi.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CliniicalData {
    @Id
    private int id;
    private String componentName;
    private String componentValue;
    private Timestamp measureDateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public Timestamp getMeasureDateTime() {
        return measureDateTime;
    }

    public void setMeasureDateTime(Timestamp measureDateTime) {
        this.measureDateTime = measureDateTime;
    }
}
