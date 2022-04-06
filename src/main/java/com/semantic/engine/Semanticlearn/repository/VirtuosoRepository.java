package com.semantic.engine.Semanticlearn.repository;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VirtuosoRepository {

    public String getDescription(String id){

        RDFConnectionRemoteBuilder builder = RDFConnectionRemote.create();
        //  builder.httpContext(getVirtuosoContext())
        builder.destination("http://localhost:8890/sparql");


        String query = "PREFIX ab:<http://i3-market.org/core#>" +
                "select ?r ?o " + // FROM <http://i3market/data>  //JSON{'s': ?s , 'p': ?p , 'o':?o}
                "where {?s a ab:DataOffering;" +
                "           ab:providerId \""+id+"\";" +
                "            ab:dataOfferingDescription ?o}";


        final Query query1 = QueryFactory.create(query);
        final QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://localhost:8890/sparql", query,
                "http://i3market/data");      //    http://i3market/data     http://localhost:8890/DB#
        final ResultSet resultSet = queryExecution.execSelect();

        String sr=null;
        while(resultSet.hasNext()){
            final QuerySolution querySolution = resultSet.nextSolution();
            System.out.println(querySolution.getLiteral("?o"));
           sr=  querySolution.getLiteral("?o").getString();

        }
        return sr;
    }



    public List<String> getProviders(){

        RDFConnectionRemoteBuilder builder = RDFConnectionRemote.create();
        //  builder.httpContext(getVirtuosoContext())
        builder.destination("http://localhost:8890/sparql");


        String query = "PREFIX ab:<http://i3-market.org/core#>" +
                "select ?r ?o " + // FROM <http://i3market/data>  //JSON{'s': ?s , 'p': ?p , 'o':?o}
                "where {?s a ab:DataOffering;" +
                "           ab:providerId ?o." +
                //   "          ab:dataOfferingDescription ?o}"+
                "}";

        final Query query1 = QueryFactory.create(query);
        final QueryExecution queryExecution = QueryExecutionFactory.sparqlService("http://localhost:8890/sparql", query,
                "http://i3market/data");      //    http://i3market/data     http://localhost:8890/DB#
        final ResultSet resultSet = queryExecution.execSelect();

        List<String> sr = new ArrayList<>();
        while(resultSet.hasNext()){
            final QuerySolution querySolution = resultSet.nextSolution();
            System.out.println(querySolution.getLiteral("?o"));
            sr.add(querySolution.getLiteral("?o").getString())  ;

        }
        return sr;
    }



    private HttpContext getVirtuosoContext() {

        HttpContext httpContext=null;
        try {
            httpContext = new BasicHttpContext();
            CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(new AuthScope(AuthScope.ANY_HOST,
                    AuthScope.ANY_PORT), new UsernamePasswordCredentials("dba", "myDbaPassword"));
            httpContext.setAttribute(HttpClientContext.CREDS_PROVIDER, provider);
        }catch(Exception e){
            e.printStackTrace();
        }
        return httpContext;

    }
}
