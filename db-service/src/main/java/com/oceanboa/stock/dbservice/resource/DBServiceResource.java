package com.oceanboa.stock.dbservice.resource;

import com.oceanboa.stock.dbservice.model.Quote;
import com.oceanboa.stock.dbservice.model.Quotes;
import com.oceanboa.stock.dbservice.repository.QuotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/db")
public class DBServiceResource {

    @Autowired
    private QuotesRepository quotesRepository;

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username")
                                  final String username){

        return getQuotesByUserName(username);
    }

    @PostMapping("/delete/{username}")
    public List<String>  delete(@PathVariable("username")
                               final String username){

        List<Quote> quotes = quotesRepository.findByUserName(username);
        quotesRepository.deleteAll(quotes);

        return getQuotesByUserName(username);
    }

    @PostMapping("/add")
    public List<String> add(@RequestBody final Quotes quotes){

        quotes.getQuotes()
                .stream()
                .map(quote -> new Quote(quotes.getUserName(), quote))
                .forEach(quote -> quotesRepository.save(quote));
//                .forEach(quote -> {
//                    quotesRepository.save(new Quote(quotes.getUserName(), quote));
//                });

         return getQuotesByUserName(quotes.getUserName());
    }

    private List<String> getQuotesByUserName(@PathVariable("username") String username) {
        return quotesRepository.findByUserName(username)
                .stream()
                .map(Quote::getQuote)
                .collect(Collectors.toList());
    }
}
