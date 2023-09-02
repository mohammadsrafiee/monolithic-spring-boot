package com.app.library.common.log.impl;

import com.app.library.common.layer.IModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.ArrayUtils;

import java.time.LocalDateTime;

public class LogModel implements IModel {

    private LocalDateTime time;
    private String agentId;
    private String threadId;
    private String ip;
    private String type;
    private String kind;
    private String method;
    private String pack;
    private String exMsg;
    private Object[] args;
    private String layer;
    private StackTraceElement stackTraceElement;

    private boolean pretty = false;

    public LogModel() {

    }

    public LogModel(LocalDateTime time,
                    String agentId,
                    String threadId,
                    String ip,
                    String type,
                    String kind,
                    String method,
                    String pack,
                    String exMsg,
                    Object[] args,
                    String layer,
                    StackTraceElement stackTraceElement) {
        this.time = time;
        this.agentId = agentId;
        this.threadId = threadId;
        this.ip = ip;
        this.type = type;
        this.kind = kind;
        this.method = method;
        this.pack = pack;
        this.exMsg = exMsg;
        this.args = args;
        this.layer = layer;
        this.stackTraceElement = stackTraceElement;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getExMsg() {
        return exMsg;
    }

    public void setExMsg(String exMsg) {
        this.exMsg = exMsg;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public StackTraceElement getStackTraceElement() {
        return stackTraceElement;
    }

    public void setStackTraceElement(StackTraceElement stackTraceElement) {
        this.stackTraceElement = stackTraceElement;
    }

    public boolean isPretty() {
        return pretty;
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            if (isPretty()) mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return
                    String.format("%25s", "[" + ip + "]::") +
                            String.format("%25s", "[" + ((agentId == null || agentId.isEmpty()) ? "unknown user" : agentId) + "]::") +
                            String.format("%15s", "[" + type + "]::") +
                            String.format("%15s", "[" + ((layer == null || layer.isEmpty()) ? "-" : layer) + "]::") +
                            String.format("%10s", "[" + threadId + "]::") +
                            String.format("%20s", "[" + kind + "]::") +
                            String.format("%70s", "[" + pack + "]::") +
                            String.format("%30s", "[" + method + "]::") +
                            String.format("%s", "[" + ArrayUtils.toString(args) + "]::") +
                            String.format("%20s", "[" + exMsg + "]::") +
                            String.format("%100s", "[" + ((stackTraceElement != null) ? stackTraceElement.toString() : "") + "]::");
        }
    }
}
