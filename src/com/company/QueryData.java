package com.company;

import java.util.ArrayList;
import java.util.List;

public class QueryData {

    public String name;
    public int alarmColor;
    public int id;
    public int datasourceCount;
    public String _alertIcon;
    public int elementCount;
    public String uniqueId;

    public List<Parameter> parameterList = new ArrayList<Parameter>();


    public QueryData(String name, int alarmColor, int id, int datasourceCount, String _alertIcon, int elementCount, String uniqueId, List<Parameter> parameterList){

        this.name = name;
        this.alarmColor = alarmColor;
        this.id = id;
        this.datasourceCount = datasourceCount;
        this._alertIcon = _alertIcon;
        this.elementCount = elementCount;
        this.uniqueId = uniqueId;
        this.parameterList = parameterList;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlarmColor() {
        return alarmColor;
    }

    public void setAlarmColor(int alarmColor) {
        this.alarmColor = alarmColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDatasourceCount() {
        return datasourceCount;
    }

    public void setDatasourceCount(int datasourceCount) {
        this.datasourceCount = datasourceCount;
    }

    public String get_alertIcon() {
        return _alertIcon;
    }

    public void set_alertIcon(String _alertIcon) {
        this._alertIcon = _alertIcon;
    }

    public int getElementCount() {
        return elementCount;
    }

    public void setElementCount(int elementCount) {
        this.elementCount = elementCount;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }
}
