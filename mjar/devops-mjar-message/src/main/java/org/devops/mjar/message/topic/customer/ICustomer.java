package org.devops.mjar.message.topic.customer;

public interface ICustomer {

	public boolean onMsg(String tag,String body);
}
