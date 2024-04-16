package com.telcobright.userresponse.repository;
public interface TokenBlacklist {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
