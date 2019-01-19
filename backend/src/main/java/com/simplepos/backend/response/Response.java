package com.simplepos.backend.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Date;

/**
 * Builds a custom response object for success and error messages.
 */
public class Response {
    private Date timeStamp;
    private int status;
    private String message;
    private String error;
    private String path;

    public Response(Date timeStamp, int status, String message, String error, String path) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.message = message;
        this.error = error;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Builds the JSON string of the Response object
     *
     * @return response JSON string
     * @throws JsonProcessingException writeValueAsString
     */
    public String toJsonString() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("timestamp", timeStamp.toString());
        objectNode.put("status", status);
        //if error is null, no error in json
        if (error != null) {
            objectNode.put("error",error);
        }
        objectNode.put("message",message);
        objectNode.put("path",path);

        return objectMapper.writeValueAsString(objectNode);
    }
}
