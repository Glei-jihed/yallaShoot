package com.ece.yallashoot.Services;


import com.ece.yallashoot.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImplementation  implements RequestService {


    @Autowired
    private RequestRepository requestRepository;




}
