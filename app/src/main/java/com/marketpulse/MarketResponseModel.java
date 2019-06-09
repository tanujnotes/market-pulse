package com.marketpulse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

public class MarketResponseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("criteria")
    @Expose
    private List<Criteria> criteria = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    public class Criteria {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("variable")
        @Expose
        private HashMap<String, Variable> variable;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public HashMap<String, Variable> getVariable() {
            return variable;
        }

        public void setVariable(HashMap variable) {
            this.variable = variable;
        }
    }

    public class Variable {
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("values")
        @Expose
        private List<Double> values = null;
        @SerializedName("study_type")
        @Expose
        private String studyType;
        @SerializedName("parameter_name")
        @Expose
        private String parameterName;
        @SerializedName("min_value")
        @Expose
        private Integer minValue;
        @SerializedName("max_value")
        @Expose
        private Integer maxValue;
        @SerializedName("default_value")
        @Expose
        private Integer defaultValue;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getValues() {
            return values;
        }

        public void setValues(List<Double> values) {
            this.values = values;
        }

        public String getStudyType() {
            return studyType;
        }

        public void setStudyType(String studyType) {
            this.studyType = studyType;
        }

        public String getParameterName() {
            return parameterName;
        }

        public void setParameterName(String parameterName) {
            this.parameterName = parameterName;
        }

        public Integer getMinValue() {
            return minValue;
        }

        public void setMinValue(Integer minValue) {
            this.minValue = minValue;
        }

        public Integer getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(Integer maxValue) {
            this.maxValue = maxValue;
        }

        public Integer getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Integer defaultValue) {
            this.defaultValue = defaultValue;
        }
    }
}
