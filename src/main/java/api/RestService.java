package api;

import api.dto.LoginRequestDto;
import api.dto.RegisterRequestDto;
import api.dto.UpdateScoreRequestDto;
import api.dto.ValidateRequestDto;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/submarine/rest/")
public class RestService {

    private RestLogic restLogic;

    public RestService()
    {
        restLogic = new RestLogic();
    }

    @GET
    @Path("/getScore/{UserId}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getScore(@PathParam("UserId") String userid){
        String score = restLogic.getScore(userid);

        if(score == null)
        {
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(ResponseHelper.getScoreResponseString(score)).build();
    }

    @GET
    @Path("/getTop/{amount}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getTop(@PathParam("amount") String amount){

        List<String> topx = restLogic.getTopX(amount);

        if(topx == null)
        {
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(ResponseHelper.getTopXResponseString(topx)).build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(LoginRequestDto loginRequest){
        if(loginRequest == null)
        {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }

        int id = restLogic.login(loginRequest.getUserName(), loginRequest.getPassword());
        if(id == -1)
        {
            //TODO log failed login requests
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }
        String token = restLogic.getValidationKey(id);
        return  Response.status(200).entity(ResponseHelper.getLoginResultDtoResponseString(id, token)).build();
    }

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(RegisterRequestDto registerRequest) {
        if(registerRequest == null)
        {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }
        boolean success = restLogic.register(registerRequest.getUserName(), registerRequest.getPassword());

        if(!success)
        {
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(ResponseHelper.getSuccessResponseString()).build();
    }


    @POST
    @Path("/validate")
    @Consumes("application/json")
    @Produces("application/json")
    public Response validate(ValidateRequestDto validateRequest){
        if(validateRequest == null)
        {
            return Response.status(400).entity(ResponseHelper.getErrorResponseString()).build();
        }

        boolean validated = restLogic.validateLogin(validateRequest.getValidationToken(), validateRequest.getUserId());

        if(!validated)
        {
            return Response.status(401).entity(ResponseHelper.getErrorResponseString()).build();
        }

        return  Response.status(200).entity(ResponseHelper.getSuccessResponseString()).build();
    }

    @PUT
    @Path("/updateScore")
    @Consumes("application/json")
    public void updateScore(UpdateScoreRequestDto updateScoreRequest){
        if (updateScoreRequest == null){
            return;
        }

        restLogic.updateScore(updateScoreRequest.getUserId());
    }
}
