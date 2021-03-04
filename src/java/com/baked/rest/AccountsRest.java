/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.rest;

import com.baked.accounts.models.User;
import com.baked.accounts.service.AccountsServiceImp;
import com.baked.accounts.service.AccountsServiceInterface;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author khwezi
 */
@Path("/accounts")
public class AccountsRest {
    private AccountsServiceInterface accService;
    
    public AccountsRest(){
        accService = new AccountsServiceImp();
    }
    
    @GET
    @Path("/login/{userEmail}/{userPassword")
    @Produces(MediaType.APPLICATION_JSON)
    public User login(@PathParam("userEmail")String email,@PathParam("userPassword")String password){
        return accService.login(email, password);
    }
    
    
    
    
}
