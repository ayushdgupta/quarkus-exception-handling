package com.guptaji.resource;

import com.guptaji.customException.BusinessException;
import com.guptaji.entity.StudentEntity;
import com.guptaji.repository.StudentRepo;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentResource {

    /*
    For exception handling we need to follow below 3 steps -
    Step - 1 --> first create an Error Message DTO that will go in output.
    Step - 2 --> Now create a custom Business exception class that will take the things that you wanna pass in
    ur error message json.
    Step - 3 --> Now we need to create an Exception Mapper which will map the error message DTO to Exception 
    response.
     */
    
    @Inject
    public StudentRepo studentRepo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudenDetails(){
        List<StudentEntity> studentEntities = studentRepo.listAll();
        if (studentEntities == null || studentEntities.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(studentEntities).build();
        }
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertOneNewStudent(StudentEntity studentEntity){
        studentRepo.persist(studentEntity);
        if (studentRepo.isPersistent(studentEntity)){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudenDetailsById(@PathParam("id") int id){
        StudentEntity studentEntity = studentRepo.findById(id);
        if (studentEntity == null){
//            return Response.status(Response.Status.NO_CONTENT).build();
            throw new BusinessException("Student with id "+id+" does n't exist in db so nikalle yha se",
                    Response.Status.NOT_FOUND.getStatusCode(), "getAllStudenDetailsById("+id+")");
        } else {
            return Response.ok(studentEntity).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteById(@PathParam("id") int id){
        boolean dataDeleted = studentRepo.deleteById(id);
        if (dataDeleted){
            return Response.ok("Done dana done done").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    // getById method we tackle a business exception but if we need to deal with an technical exception then
//    we need to follow same steps like creating a new erro message dto if old one can n't be reused and a
    // exception class and a exception mapper (separate).
    // so for enduser we can send the json response from mapper but for our use case we can print the exception
    // in logs.error() so both will be happy like we will get the erro message and the end user will get some
    // simple json. like below
    /*
    try{
    int out = 8/0;
    return Response.ok(-------------).build();
    } catch(Exception e){
        Log.error("-------------------", e);   this is for us.
        throw new TechnicalException(e.getmessage(), -------------- other params ---------);  this is for end user.
    }
     */


}
