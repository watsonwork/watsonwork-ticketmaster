package com.ibm.watsonwork.client;

import com.ibm.watsonwork.model.graphql.GraphQLResponse;
import com.ibm.watsonwork.model.graphql.GraphQLQuery;
import com.ibm.watsonwork.model.graphql.Message;
import retrofit2.Call;
import retrofit2.http.*;


public interface GraphQLClient {

    String CONTENT_JSON_TYPE = "Content-Type: application/json";
    String GRAPHQL_ACTIONS_VIEW = "x-graphql-view: ACTIONS,PUBLIC";
    String GRAPHQL_PUBLIC_VIEW = "x-graphql-view: PUBLIC";
    String GRAPHQL_BETA_VIEW = "x-graphql-view: PUBLIC,BETA";
    String GRAPHQL_PATH = "/graphql";

    @Headers({ CONTENT_JSON_TYPE, GRAPHQL_ACTIONS_VIEW })
    @POST(GRAPHQL_PATH)
    Call<GraphQLResponse> createMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);


    @Headers({ CONTENT_JSON_TYPE, GRAPHQL_BETA_VIEW })
    @POST(GRAPHQL_PATH)
    Call<GraphQLResponse> createTargetedMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);


    @Headers({ CONTENT_JSON_TYPE, GRAPHQL_PUBLIC_VIEW })
    @POST(GRAPHQL_PATH)
    Call<GraphQLResponse> getMessage(@Header("Authorization") String authToken, @Body GraphQLQuery query);

}
