package com.oceanboa.stock.stockservice.resource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/stock")
public class StockResource {

    @Autowired
    private Environment env;

//    private String getStockFromAPI(String quote_symbol){
//        String apiKey = env.getProperty("alphavantage.apikey");
//
//        String urlString = "https://www.alphavantage.co/query?" +
//                "function=TIME_SERIES_DAILY" +
//                "&symbol="+quote_symbol+
//                "&apikey="+apiKey;
//
//
//        try {
//            URL url = new URL(urlString);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private String getStock2(String quote_symbol){
//        String apiKey = env.getProperty("alphavantage.apikey");
//
//        Map<String, String> vars = new HashMap<String, String>();
//        vars.put("quote_symbol", quote_symbol);
//        vars.put("booking", apiKey);
//
//        String result = restTemplate.getForObject("https://www.alphavantage.co/query?" +
//                "function=TIME_SERIES_DAILY&symbol{quote_symbol}&apikey={apiKey}"
//                , String.class, vars);
//
//        System.out.println(result);
//        return result;
//    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{username}")
    public List<Quote> getStocks(@PathVariable("username") final String username){


         ResponseEntity<List<String>> quoteResponse = restTemplate.exchange(
                 "http://db2-service/rest/db/"+username,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {});

         List<String> quotes = quoteResponse.getBody();

         return quotes.stream()
                 .map( quote -> {
                     Stock stock = getStock(quote);
                     stock.print();

                    return new Quote(quote, stock.getQuote().getPrice());
                 })
                 .collect(Collectors.toList());
    }

    private class Quote {
        private String quote;
        private BigDecimal price;

        public String getQuote() {
            return quote;
        }

        public void setQuote(String quote) {
            this.quote = quote;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public Quote(String quote, BigDecimal price) {

            this.quote = quote;
            this.price = price;
        }
    }

    private Stock getStock(String name){
        Stock stock = null;
        try {
            stock = YahooFinance.get(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stock;

//        BigDecimal price = stock.getQuote().getPrice();
//        BigDecimal change = stock.getQuote().getChangeInPercent();
//        BigDecimal peg = stock.getStats().getPeg();
//        BigDecimal dividend = stock.getDividend().getAnnualYieldPercent();

//        stock.print();
    }

}
