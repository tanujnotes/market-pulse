package com.marketpulse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<Criteria> criteria = null;

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

    public ArrayList<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(ArrayList<Criteria> criteria) {
        this.criteria = criteria;
    }

    public static class Criteria implements Serializable, Parcelable {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type);
            dest.writeString(this.text);
            dest.writeSerializable(this.variable);
        }

        public Criteria() {
        }

        protected Criteria(Parcel in) {
            this.type = in.readString();
            this.text = in.readString();
            this.variable = (HashMap<String, Variable>) in.readSerializable();
        }

        public static final Parcelable.Creator<Criteria> CREATOR = new Parcelable.Creator<Criteria>() {
            @Override
            public Criteria createFromParcel(Parcel source) {
                return new Criteria(source);
            }

            @Override
            public Criteria[] newArray(int size) {
                return new Criteria[size];
            }
        };
    }

    public static class Variable implements Serializable, Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.type);
            dest.writeList(this.values);
            dest.writeString(this.studyType);
            dest.writeString(this.parameterName);
            dest.writeValue(this.minValue);
            dest.writeValue(this.maxValue);
            dest.writeValue(this.defaultValue);
        }

        public Variable() {
        }

        protected Variable(Parcel in) {
            this.type = in.readString();
            this.values = new ArrayList<Double>();
            in.readList(this.values, Double.class.getClassLoader());
            this.studyType = in.readString();
            this.parameterName = in.readString();
            this.minValue = (Integer) in.readValue(Integer.class.getClassLoader());
            this.maxValue = (Integer) in.readValue(Integer.class.getClassLoader());
            this.defaultValue = (Integer) in.readValue(Integer.class.getClassLoader());
        }

        public static final Parcelable.Creator<Variable> CREATOR = new Parcelable.Creator<Variable>() {
            @Override
            public Variable createFromParcel(Parcel source) {
                return new Variable(source);
            }

            @Override
            public Variable[] newArray(int size) {
                return new Variable[size];
            }
        };
    }
}
