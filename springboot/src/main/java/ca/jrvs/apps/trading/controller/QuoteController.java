package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.service.QuoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "quote", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
@RequestMapping("/quote")
public class QuoteController {

  private QuoteService quoteService;
  
  @Autowired
  public QuoteController (QuoteService quoteService) {
    this.quoteService = quoteService;
  }
  
  @ApiOperation(value = "Show iexQuote",  notes = "Show quote from IEX Cloud with provided " +
                                                      "ticker/symbol")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Provided ticker not found")})
  @GetMapping(path="/iex/ticker/{ticker}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public IexQuote getQuote (@PathVariable String ticker) {
    try {
      return quoteService.findIexQuoteByTicker(ticker);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
  
  @ApiOperation(value = "Update Quote table with latest market data from IEX Cloud",
      notes = "Updates all quotes via the IEX Cloud API")
  @PutMapping(path="/iexMarketData")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public void updateMarketData() {
    try {
      quoteService.updateMarketData();
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
  
  @ApiOperation(value = "Update the given quote in the Quote table",
      notes = "Manually update quote with IEX Cloud data")
  @PutMapping(path="/update")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Quote putQuote(@PathVariable Quote quote) {
    try {
      return quoteService.saveQuote(quote);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Add quote to the Quote table")
  @PutMapping(path="/ticker/{ticker}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Quote createQuote(@PathVariable  String ticker) {
    try {
      return quoteService.saveQuote(ticker);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Display daily list", notes = "Get and display stored quotes from the " +
                                                          "Quote table")
  @PutMapping(path="/dailyList")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<Quote> getDailyList() {
    try {
      return quoteService.findAllQuotes();
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
  
}
