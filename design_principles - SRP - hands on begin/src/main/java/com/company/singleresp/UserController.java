package com.company.singleresp;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

//Handles incoming JSON requests that work on User resource/entity
public class UserController {
	private UserPersistenceService service = new UserPersistenceService();
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        User user = mapper.readValue(userJson, User.class);

        UserValidator validate = new UserValidator();
        boolean isValid = validate.validate(user);

        if(!isValid) {
            return "ERROR";
        }

        service.saveUser(user);
        
        return "SUCCESS";
    }
}