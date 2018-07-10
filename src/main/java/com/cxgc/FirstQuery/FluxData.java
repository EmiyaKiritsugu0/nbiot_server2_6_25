package com.cxgc.FirstQuery;

import java.sql.Date;
import java.sql.Time;

public class FluxData {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlux() {
        return flux;
    }

    public void setFlux(String flux) {
        this.flux = flux;
    }

    public Date getDate_sql() {
        return date_sql;
    }

    public void setDate_sql(Date date_sql) {
        this.date_sql = date_sql;
    }

    public Time getTime_sql() {
        return time_sql;
    }

    public void setTime_sql(Time time_sql) {
        this.time_sql = time_sql;
    }

    public Integer getMy_timestamp() {
        return my_timestamp;
    }

    public void setMy_timestamp(Integer my_timestamp) {
        this.my_timestamp = my_timestamp;
    }

    public String getInstantflux() {
        return instantflux;
    }

    public void setInstantflux(String instantflux) {
        this.instantflux = instantflux;
    }

    private Integer id;//机器编号
    private String flux;
    private Date date_sql;
    private Time time_sql;
    private Integer my_timestamp;
    private String instantflux;
}
