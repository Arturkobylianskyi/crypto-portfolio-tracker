package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.dao.AssetRepository;
import com.artur.crypto_portfolio_tracker.dao.UserRepository;
import com.artur.crypto_portfolio_tracker.service.AssetService;
import com.artur.crypto_portfolio_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/api")
public class CryptoPortfolioRestController {

    private final JsonMapper jsonMapper;

    private final AssetService assetService;

    private final UserService userService;

    @Autowired
    public CryptoPortfolioRestController(JsonMapper jsonMapper, AssetService assetService, UserService userService) {
        this.jsonMapper = jsonMapper;
        this.assetService = assetService;
        this.userService = userService;
    }


}
