package com.zccoder.demo.es;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ES常用操作
 *
 * @author zc 2019-06-25
 */
public class TestClient extends AbstractTestSupport {

    @Autowired
    private RestHighLevelClient client;

    private static final String INDEX_NAME = "book";

    @Test
    public void doTest() throws Exception {
        GetRequest getRequest = new GetRequest("book", "1");
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void doTest1() throws Exception {
        // select * from index_name
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void doTest2() throws Exception {
        // select * from index_name where id = 1
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("id", "2"));
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    public void doTest3() throws Exception {
        // select * from index_name where name like %书籍%
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchQuery("name", "书籍"));
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response);
    }
}
