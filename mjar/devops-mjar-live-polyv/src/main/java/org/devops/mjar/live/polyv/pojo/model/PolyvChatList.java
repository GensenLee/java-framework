package org.devops.mjar.live.polyv.pojo.model;

import lombok.Data;

import java.util.List;

@Data
public class PolyvChatList {

    private Integer count;

    private List<PolyvChatUserV3> userlist;

}
