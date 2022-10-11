package com.example.demo.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.payload.response.CryptoPriceResponse;

@FeignClient(url = "https://api.binance.com/api/v3/ticker/price", name="CryptoPrice")
public interface CryptoPriceInterface {
    

    //Consumindo dados da API de cotação com Query Parameter
    // https://api.binance.com/api/v3/ticker/price?symbol=BTCBRL
    @GetMapping
    CryptoPriceResponse getPriceBySymbol(@RequestParam("symbol") String symbol);
}
