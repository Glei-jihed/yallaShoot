package com.ece.yallashoot.Services;

import com.ece.yallashoot.entities.Request;

import java.util.List;

public interface RequestService {

    List<Request> findRequestByPlayerId(String id);

    List<Request> findRequestByRequestedGameId(String id);

}
