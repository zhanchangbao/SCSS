package org.obsidian.scss.service;

/**
 * Created by mobing  on 2017/7/11.
 */
public interface ClientFlagService {
    int insertClientFlag(int clientId,String name);

    int deleteClientFlag(int clientId,int flagId);

}
