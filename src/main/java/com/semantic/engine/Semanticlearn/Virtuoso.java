package com.semantic.engine.Semanticlearn;

import org.apache.jena.query.*;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.rdfconnection.RDFConnectionRemoteBuilder;

import java.net.UnknownHostException;


public class Virtuoso {
    public static void main(String[] args) throws UnknownHostException {
        RDFConnectionRemoteBuilder rdfConnection = RDFConnectionRemote.create();
        rdfConnection.destination("http://localhost:8890/sparql");

        String query = "SELECT * FROM <http://i3market/data> WHERE {" +
              //  "<http://i3-market.org/resource/uiot_dataoffering1>  " +
              //  " a  <http://i3-market.org/core#DataOffering>;" +
                "?s ?p ?o}";
        final Query query1 = QueryFactory.create(query);
     //   final QueryExecution queryExecution = QueryExecutionFactory.create(query1);
        final RDFConnection build = rdfConnection.build();
        final QueryExecution query2 = build.query(query1);
        final ResultSet resultSet = query2.execSelect();
        while(resultSet.hasNext()){
            final QuerySolution querySolution = resultSet.nextSolution();
         //  System.out.println(querySolution);

        }




    }
}
