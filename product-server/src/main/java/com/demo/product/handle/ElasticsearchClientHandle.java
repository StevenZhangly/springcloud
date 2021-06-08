package com.demo.product.handle;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName ElasticsearchClientHandle
 * @Description: TODO
 * @Author zly
 * @Date 2021/6/1
 **/
@Component
public class ElasticsearchClientHandle {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void createIndex(String indexName) throws IOException {
        logger.info("创建index:" + indexName);
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }

    public void checkIndexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        logger.info("检查index:" + indexName + "是否存在:" + exists);
    }

    public void deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        DeleteIndexResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        logger.info("删除index:" + indexName + "是否成功:" + response.isAcknowledged());
    }

    public void createDocument(String indexName, String documentJson) throws IOException {
        logger.info("给索引:" +indexName + "添加文档:" + documentJson);
        IndexRequest request = new IndexRequest(indexName, "_doc");
        request.id(UUID.randomUUID().toString());
        request.source(documentJson, XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    public String getDocument(String indexName, String documentId) throws IOException {
        GetRequest request = new GetRequest(indexName, "_doc", documentId);
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        return response.getSourceAsString();
    }

    public void bulkCreateDocument(String indexName, List<String> documentJsonList) throws IOException {
        BulkRequest request = new BulkRequest();
        for (String document : documentJsonList) {
            IndexRequest indexRequest = new IndexRequest(indexName, "_doc");
            indexRequest.id(UUID.randomUUID().toString());
            indexRequest.source(document, XContentType.JSON);
            request.add(indexRequest);
        }
        BulkResponse responses = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        logger.info("批量创建文档:" + JSON.toJSONString(responses));
    }

    public List<String> searchDocument(String indexName, String[] searchParams, Date beginDate, Date endDate) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(50);
        searchSourceBuilder.sort(new FieldSortBuilder("createTime").order(SortOrder.ASC));
        //设置需要获取的字段（列）和不需要获取的列
        searchSourceBuilder.fetchSource(new String[]{"id","name","store","createTime"}, new String[]{});
        //MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", searchParam);
        TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery("name", searchParams);
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("createTime");
        rangeQueryBuilder.from(beginDate.getTime()).to(endDate.getTime());
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //boolQueryBuilder.must(matchQueryBuilder);
        boolQueryBuilder.must(termsQueryBuilder);
        boolQueryBuilder.must(rangeQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.types("_doc");
        searchRequest.source(searchSourceBuilder);
        logger.info("searchSourceBuilder:" + searchSourceBuilder.toString());
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<String> resultList = new ArrayList<>();
        searchResponse.getHits().forEach(item -> resultList.add(item.getSourceAsString()));
        return resultList;
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static void main(String[] args) {
        Date date1 = localDateTimeToDate(LocalDateTime.now().minusDays(5));
        System.out.println(date1);
        System.out.println(date1.getTime());
        Date date2 = localDateTimeToDate(LocalDateTime.now());
        System.out.println(date2.getTime());
    }
}
