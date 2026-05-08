package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.dto.AssetDTO;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import com.artur.crypto_portfolio_tracker.service.AssetService;
import com.artur.crypto_portfolio_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetRestController {

    private final AssetService assetService;
    private final UserService userService;

    @Autowired
    public AssetRestController(AssetService assetService, UserService userService) {
        this.assetService = assetService;
        this.userService = userService;
    }


    @PutMapping()
    public AssetDTO updateAsset(@RequestBody Asset asset){
        Asset dbAsset = assetService.save(asset);

        AssetDTO tempAsset = new AssetDTO();
        tempAsset.setId(dbAsset.getId());
        tempAsset.setSymbol(dbAsset.getSymbol());
        tempAsset.setPurchasePrice(dbAsset.getPurchasePrice());
        tempAsset.setQuantity(dbAsset.getQuantity());

        return tempAsset;
    }

}
