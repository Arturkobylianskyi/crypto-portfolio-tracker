package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.client.CryptoApiClient;
import com.artur.crypto_portfolio_tracker.dto.AssetDTO;
import com.artur.crypto_portfolio_tracker.dto.CryptoApiResponse;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.dto.PortfolioSummaryDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import com.artur.crypto_portfolio_tracker.service.AssetService;
import com.artur.crypto_portfolio_tracker.service.PortfolioService;
import com.artur.crypto_portfolio_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PortfolioRestController {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private final AssetService assetService;

    @Autowired
    public PortfolioRestController(PortfolioService portfolioService, UserService userService, AssetService assetService) {
        this.assetService = assetService;
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{userId}/portfolio")
    public List<PortfolioItemDTO> giveMyPortfolio(@PathVariable int userId){
        return portfolioService.getPortfolio(userId);
    }


    @GetMapping("/{userId}/summary")
    public PortfolioSummaryDTO giveStatistic(@PathVariable int userId){
        return portfolioService.getPortfolioOverview(userId);
    }

    @GetMapping("/{userId}/assets")
    public List<AssetDTO> showAssets(@PathVariable int userId){
        List<Asset> assetList = userService.giveAssetsByUserId(userId);

        List<AssetDTO> assetsDTO = new ArrayList<>();
        for(Asset asset: assetList){
            AssetDTO tempAsset = new AssetDTO();
            tempAsset.setId(asset.getId());
            tempAsset.setSymbol(asset.getSymbol());
            tempAsset.setPurchasePrice(asset.getPurchasePrice());
            tempAsset.setQuantity(asset.getQuantity());
            assetsDTO.add(tempAsset);
        }

        return assetsDTO;
    }

    @PostMapping("/{userId}/assets")
    public AssetDTO addAsset(@PathVariable int userId, @RequestBody Asset asset){
        asset.setId(0);
        asset.setUser(userService.findById(userId));

        Asset dbAsset = assetService.save(asset);

        AssetDTO tempAsset = new AssetDTO();
        tempAsset.setId(dbAsset.getId());
        tempAsset.setSymbol(dbAsset.getSymbol());
        tempAsset.setPurchasePrice(dbAsset.getPurchasePrice());
        tempAsset.setQuantity(dbAsset.getQuantity());

        return tempAsset;
    }

}
