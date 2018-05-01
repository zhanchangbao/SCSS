package org.obsidian.scss.bean;

/**
 * Created by mobing  on 2017/7/18.
 */
public class ConversationEndReq {

    private int conversationId;

    private int clientId;

    public ConversationEndReq(int conversationId) {
        this.conversationId = conversationId;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public ConversationEndReq() {
    }

    @Override
    public String toString() {
        return "ConversationEndReq{" +
                "conversationId=" + conversationId +
                ", clientId=" + clientId +
                '}';
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public ConversationEndReq(int conversationId, int clientId) {
        this.conversationId = conversationId;
        this.clientId = clientId;
    }
}
