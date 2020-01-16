package com.holidu.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:tree-data.yaml")
public class TreeDataConfig {

    @Value("${base-url}")
    private String baseUrl;
    @Value("${resource-id}")
    private String resourceId;
    @Value("${decimal-rounding}")
    private Integer decimalRounding;
    @Value("${where-formula}")
    private String whereFormula;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getDecimalRounding() {
        return decimalRounding;
    }

    public void setDecimalRounding(Integer decimalRounding) {
        this.decimalRounding = decimalRounding;
    }

    public String getWhereFormula() {
        return whereFormula;
    }

    public void setWhereFormula(String whereFormula) {
        this.whereFormula = whereFormula;
    }

    @Override
    public String toString() {
        return "TreeDataConfig{" +
                "baseUrl='" + baseUrl + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", decimalRounding=" + decimalRounding +
                ", whereFormula='" + whereFormula + '\'' +
                '}';
    }
}
