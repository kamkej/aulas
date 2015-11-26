/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.tads.foodservice.Payorder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author julio
 */
@Stateless
@Path("payorder")
public class PayorderFacadeREST extends AbstractFacade<Payorder> {
    @PersistenceContext(unitName = "FoodServicePU")
    private EntityManager em;

    public PayorderFacadeREST() {
        super(Payorder.class);
    }

    @POST
    @Override
    @Consumes({"application/json"})
    public void create(Payorder entity) {     
        super.create(entity);

    }

    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public void edit(@PathParam("id") Integer id, Payorder entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{userid}")
    @Consumes({"application/json"})
    public List<Payorder> find(@PathParam("userid") Integer userid) {
        return super.findbyuser("Payorder.findByUserid",userid,0);
    }

    @GET
    @Override
    @Consumes({"application/json"})
    public List<Payorder> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Consumes({"application/json"})
    public List<Payorder> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
