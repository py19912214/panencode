// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package login.req;

import login.decodeAni.DecodeParams;

public class UserReq {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @DecodeParams
    private String name;

    public String getFirtName() {
        return firtName;
    }

    public void setFirtName(String firtName) {
        this.firtName = firtName;
    }

    private String firtName;

}
