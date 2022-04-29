package com.twitter.app.message.listener;

public class DestinationInfo {

    private String exchange;
    private String routeKey;

    public DestinationInfo() {
    }

    public DestinationInfo(String exchange, String routeKey) {
        this.exchange = exchange;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRouteKey() {
        return routeKey;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

}