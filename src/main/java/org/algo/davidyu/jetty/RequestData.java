package org.algo.davidyu.jetty;

public class RequestData {
	String QueueSize;
	String Message;
	
	public RequestData(String queueSize, String message) {
		super();
		this.QueueSize = queueSize;
		this.Message = message;
	}

	public String getQueueSize() {
		return QueueSize;
	}

	public void setQueueSize(String queueSize) {
		this.QueueSize = queueSize;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		this.Message = message;
	}

	@Override
	public String toString() {
		return "RequestBody [QueueSize=" + QueueSize + ", Message=" + Message + "]";
	}
}
