package com.ece.yallashoot.Services;


import com.ece.yallashoot.entities.Request;
import com.ece.yallashoot.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImplementation  implements RequestService {


    @Autowired
    private RequestRepository requestRepository;


    @Override
    public List<Request> findRequestByPlayerId(String id) {
        return requestRepository.findRequestByPlayerId(id);
    }

    @Override
    public List<Request> findRequestByRequestedGameId(String id) {
        return requestRepository.findRequestByRequestedGameId(id);
    }

}
