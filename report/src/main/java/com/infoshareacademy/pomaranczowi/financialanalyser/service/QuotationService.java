package com.infoshareacademy.pomaranczowi.financialanalyser.service;

import com.infoshareacademy.pomaranczowi.financialanalyser.model.Quotation;
import com.infoshareacademy.pomaranczowi.financialanalyser.model.QuotationStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.Optional;

@Path("/")
public class QuotationService {

    private Logger LOG = LoggerFactory.getLogger(QuotationService.class);

    @Context
    private UriInfo uriInfo;

    @Inject
    private QuotationStore quotationStore;

    public QuotationService() {
    }

    @GET
    @Path("/hi/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello(@PathParam("name") String name) {
        LOG.info("Saying hello to {}!", name);

        LOG.info("Absolute Path: {}", uriInfo.getAbsolutePath());
        LOG.info("Base URI: {}", uriInfo.getBaseUri());
        LOG.info("Path Parameters: {}", uriInfo.getPathParameters());

        return Response.ok("Saying hello to " + name + "!").build();
    }

    @GET
    @Path("/agent")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserAgent(@HeaderParam("user-agent") String userAgent) {
        LOG.info("User agent: {}", userAgent);

        return Response.ok(userAgent).build();
    }

    @GET
    @Path("/quotations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuotations() {

        Collection<Quotation> quotations = quotationStore.getBase().values();
        if (quotations.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(quotations).build();
    }

    @GET
    @Path("/quotation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuotation(@QueryParam("id") Long id) {
        Optional<Quotation> quotation = quotationStore.findById(id);
        if (quotation.isPresent()) {
            return Response.ok(quotation.get()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Path("/addquotation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQuotation(Quotation quotation) {

        long newId = quotationStore.getNewId();
        LOG.info("New ID: {}", newId);

        quotationStore.add(new Quotation(newId,
                quotation.getName(),
                quotation.getCode(),
                quotation.getQuotationType()));

        return getQuotations();
    }

}
