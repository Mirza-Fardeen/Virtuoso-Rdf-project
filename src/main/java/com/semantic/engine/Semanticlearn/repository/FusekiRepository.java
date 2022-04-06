package com.semantic.engine.Semanticlearn.repository;

import com.semantic.engine.Semanticlearn.domain.Organization;
import com.semantic.engine.Semanticlearn.domain.Provider;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class FusekiRepository {

   public   RDFConnectionRemoteBuilder builder = RDFConnectionRemote.create();
  Logger logger = LoggerFactory.getLogger(FusekiRepository.class);

//    }
    public  void addingProvider(String providerId , String name , String description){
        RDFConnection build = gettingConnection();
//        String queryString =  "PREFIX ab: <http://learningsparql.com/ns/addressbook#>\n" +
//                "INSERT DATA {\n" +
//                "   ab:provider ab:providerId "+providerId+";\n" +
//                "               ab:name "+name+";\n" +
//                "               ab:description "+description+".\n" +
//                "}";




        String queryString =  "PREFIX ab: <http://learningsparql.com/ns/addressbook#>\n" +
                "INSERT DATA {\n" +
                "   <http://learningsparql.com/ns/addressbook#"+providerId+  ">" +
                "               <http://learningsparql.com/ns/addressbook#name> \""   +name+   "\";\n" +
                "               <http://learningsparql.com/ns/addressbook#description> \""   +description+  "\".\n" +
                "}";




        UpdateRequest updates = UpdateFactory.create(queryString);

        build.update(updates);
    }

    public  void gettingTested(){
        String queryString ="SELECT * WHERE {?s ?p ?o}";
        final Query query = QueryFactory.create(queryString);
        RDFConnection build = gettingConnection();
        final QueryExecution queryExec = build.query(query);
        final ResultSet resultSet = queryExec.execSelect();
        while(resultSet.hasNext()){
            QuerySolution solution = resultSet.nextSolution();
            System.out.println(solution);
        }
    }

    public  void addingProviderTwo(String providerId , String name , String description , Organization organization){
        RDFConnection build = gettingConnection();
//        String queryString =  "PREFIX ab: <http://learningsparql.com/ns/addressbook#>\n" +
//                "INSERT DATA {\n" +
//                "   ab:provider ab:providerId "+providerId+";\n" +
//                "               ab:name "+name+";\n" +
//                "               ab:description "+description+".\n" +
//                "}";


        String org =  "<http://learningsparql.com/ns/addressbook#organization"
                +providerId+">";
        String queryString =  "PREFIX ab: <http://learningsparql.com/ns/addressbook#>\n" +
                "INSERT DATA {\n" +
                "   <http://learningsparql.com/ns/addressbook#"+providerId+  ">" +
                "               <http://learningsparql.com/ns/addressbook#name> \""   +name+   "\";\n" +
                "               <http://learningsparql.com/ns/addressbook#description> \""   +description+  "\";\n" +
//                "  <http://learningsparql.com/ns/addressbook#organizationUrl> <http://learningsparql.com/ns/addressbook#organization"
//                +providerId+">."+
                " <http://learningsparql.com/ns/addressbook#organizationId> \" "+ organization.getOrganizationId() + "\";"+



                "<http://learningsparql.com/ns/addressbook#organizationname> \" "+ organization.getName() + "\";"+
                "<http://learningsparql.com/ns/addressbook#organizationdescription> \" "+ organization.getDescription() + "\";"+
                "<http://learningsparql.com/ns/addressbook#organizationaddress> \" "+ organization.getAddress() + "\";"+
                "<http://learningsparql.com/ns/addressbook#organizationcontactPoint> \" "+ organization.getContactPoint() + "\"."+
                "}";




        UpdateRequest updates = UpdateFactory.create(queryString);

        build.update(updates);
    }
    public Provider gettingProvider(String providerId){
        final Model defaultModel = ModelFactory.createDefaultModel();
        boolean lit;
        ArrayList<String> arr = new ArrayList<>();
//        String providerId = providerID;
        String queryString ="PREFIX ab: <http://learningsparql.com/ns/addressbook#> " +
                "SELECT ?o WHERE {ab:"+providerId +"?p ?o}";
        final Query query = QueryFactory.create(queryString);
        RDFConnection build = gettingConnection();
        final QueryExecution queryExec = build.query(query);
        final ResultSet resultSet = queryExec.execSelect();
        while(resultSet.hasNext()){
            QuerySolution solution = resultSet.nextSolution();

            if(solution.get("?o").isLiteral()){
                System.out.println(solution.get("?o").asLiteral().inModel(defaultModel));
                arr.add(String.valueOf(solution.get("?o").asLiteral()));
            }
//
        }
        Organization or = new Organization();
        Provider pr = new Provider();
        pr.setProviderId(providerId);
        pr.setName(arr.get(0));
        pr.setDescription(arr.get(1));
        or.setOrganizationId(arr.get(2));
        or.setName(arr.get(3));
        or.setDescription(arr.get(4));
        or.setAddress(arr.get(5));
        or.setContactPoint(arr.get(6));
        pr.setOrganization(or);
        System.out.println(pr.toString());

        return pr;

    }


    public    RDFConnection gettingConnection(){
        builder.destination("http://localhost:3030/providerOne");
        try{
            final RDFConnection build = builder.build();
            return build;
        }
        catch (RuntimeException e){
            System.out.println(e.getStackTrace());
        }
        return null;
    }

}
