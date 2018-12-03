package cn.com.hqep.bidder.model;

/**
 * Created by HQSpring on 2018-04-08.
 */
public class tblBbgsFlowStateRaceModel {
    private String id;
    private String content;
    private String flowState;
    private String alertMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlowState() {
        return flowState;
    }

    public void setFlowState(String flowState) {
        this.flowState = flowState;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    @Override
    public String toString() {
        return "tblBbgsFlowStateRaceModel{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", flowState='" + flowState + '\'' +
                ", alertMessage='" + alertMessage + '\'' +
                '}';
    }
}
