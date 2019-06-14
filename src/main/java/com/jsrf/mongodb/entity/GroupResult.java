package com.jsrf.mongodb.entity;

import com.google.common.base.MoreObjects;

public class GroupResult {
    private Integer count;
    private String groupName;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("count", count)
                .add("groupName", groupName)
                .toString();
    }
}
