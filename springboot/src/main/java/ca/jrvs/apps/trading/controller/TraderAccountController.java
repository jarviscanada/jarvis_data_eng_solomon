package ca.jrvs.apps.trading.controller;

import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
import ca.jrvs.apps.trading.service.TraderAccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;

@Api(value = "Trader", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Controller
@RequestMapping("/trader")
public class TraderAccountController {
  
  private TraderAccountService traderAccountService;
  
  @Autowired
  public TraderAccountController (TraderAccountService traderAccountService) {
    this.traderAccountService = traderAccountService;
  }
 
  @ApiOperation(value = "Create trader using provided information",
      notes = "The user will be provided with a traderId as well as an accountId. For this project"
                  + " a Trader can have only one associated Account. Date of birth must be in the" +
                  " format yyyy-MM-dd; an example 2020-01-01")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PutMapping(
      path = "/firstname/{firstname}/lastname/{lastname}/dob/{dob}/country/{country}/email/{email}",
      produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  public TraderAccountView createTrader (
      @PathVariable String firstname,
      @PathVariable String lastname,
      @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
      @PathVariable String country,
      @PathVariable String email) {
    try{
      Trader trader = new Trader();
      trader.setFirstName(firstname);
      trader.setLastName(lastname);
      trader.setDob(Date.valueOf(dob));
      trader.setCountry(country);
      trader.setEmail(email);
      return traderAccountService.createTraderAndAccount(trader);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
  
  @ApiOperation(value = "Create account for the provided trader",
      notes = "An Account will be created for the existing trader.")
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  @PostMapping(path = "/existing", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
  public TraderAccountView createTrader (@RequestBody Trader trader) {
    try {
      return traderAccountService.createTraderAndAccount(trader);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Delete trader associated with the provided id",
      notes = "Deletion is permitted assuming the trader has no funds in their account, "
                  + "open positions. Security orders and Accounts associated with this trader"
                  + " will be deleted as well.")
  @ApiResponse(code = 404, message = "Trader provided does not meet deletion criteria.")
  @DeleteMapping(path = "/traderId/{traderId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteTrader (@PathVariable Integer traderId) {
    try {
      traderAccountService.deleteTraderById(traderId);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
  
  @ApiOperation(value = "Deposit funds into the account associated with the provided trader id.",
      notes = "Deposit amount must be a positive number and must not be less than or equal to 0.")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Trader could not be found with "
                                                                + "provided traderId."),
      @ApiResponse(code = 400, message = "Unable to perform deposit due to user input.")})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @PutMapping(path = "/deposit/traderId/{traderId}/amount/{amount}")
  public Account depositFunds (@PathVariable Integer traderId, @PathVariable Double amount) {
    try {
      return  traderAccountService.deposit(traderId, amount);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }

  @ApiOperation(value = "Withdraw funds from the account associated with the provided trader id.",
  notes = "Withdrawal amount must be a positive number and must not be greater than the current "
              + "account balance.")
  @ApiResponses(value = {@ApiResponse(code = 404, message = "Trader could not be found with "
                                                              + "provided traderId."),
    @ApiResponse(code = 400, message = "Unable to perform withdraw due to user input.")})
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @PutMapping(path = "/withdraw/traderId/{traderId}/amount/{amount}")
  public Account withdrawFunds (@PathVariable Integer traderId, @PathVariable Double amount) {
    try {
      return traderAccountService.withdraw(traderId, amount);
    } catch (Exception e) {
      throw ResponseExceptionUtil.getResponseStatusException(e);
    }
  }
}
