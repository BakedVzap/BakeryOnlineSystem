/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.rest;

import com.baked.accounts.models.Address;
import com.baked.accounts.models.Payment;
import com.baked.accounts.models.User;
import com.baked.accounts.service.AccountsServiceImp;
import com.baked.accounts.service.AccountsServiceInterface;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    
    
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean signup(User user){
        return accService.signUp(user);
    }
    @POST
    @Path("/edit/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean editUserProfile(User user){
        return accService.editUserProfile(user);
    }
    @POST
    @Path("/delete/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteUser(@PathParam("userID")String userId){
        return accService.deleteUser(userId);
    }
    @GET
    @Path("/get-all-users")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getAllUsers(){
        return accService.getAllUsers();
    }
     @GET
    @Path("/get-all-users/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<User> getUserByName(@PathParam("name")String name){
        return accService.getUserByName(name);
    }
     @POST
    @Path("/add-payment/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserPayment(Payment payment){
        if(accService.addUserPayment(payment)){
            return Response.status(Response.Status.OK).entity("Payment has been Added").build();     
        }
         return Response.status(Response.Status.OK).entity("Adding Payment Unsuccessful").build();
           
         
    }
    @POST
    @Path("/delete-user-payment/{userID}/{cardNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteUserPayment(@PathParam("cardNo")String cardNo,@PathParam("userID")String userID){
        return accService.deleteUserPayment(cardNo, userID);
    }
    @POST
    @Path("/get-all-user-payment/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Payment> getAllUserPayments(@PathParam("userID")String userID){
        return accService.getAllUserPayments(userID);
    }

    @POST
    @Path("/add-user-address/{userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAddress(Address address,@PathParam("userID")String userID){

        if(accService.addAddress(address, userID)){
            return Response.status(Response.Status.OK).entity("Address has been Added").build(); 
        }
        return Response.status(Response.Status.OK).entity("Address has not been Added").build(); 
    }
    @GET
    @Path("/get-user-addtresses/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Address> getAllUserAddress(@PathParam("userID")String userID){
        return accService.getAllUserAddress(userID);
    }
    @POST
    @Path("/delete-user-address/{userAddressID}/{userID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean deleteUserAddress(int userAddressID,String userID){
        return accService.deleteUserAddress(userAddressID, userID);
    }
    public boolean passwordRecovery(String email){
        try {
            new EmailSender(email,"Password Recovery","To reset your password please click on this link: ");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
    public boolean passwordRest(String userID, String newPassword){
        return accDao.passwordRest(userID,newPassword);
    }
   
    
    
    
}
