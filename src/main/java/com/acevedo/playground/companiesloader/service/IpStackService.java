package com.acevedo.playground.companiesloader.service;

import org.springframework.stereotype.Service;

@Service
public class IpStackService {
    public String getCountryForHomepage(String homepageUrl){
        // TODO implement the real version instead of this mock
        switch(homepageUrl) {
            case "frenchUrl": return "France";
            case "spanishUrl": return "Spain";
            default: return "Default_country";
        }
    }
}
