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
@RequestMapping("/api/users")
public class AssetRestController {

    private final AssetService assetService;
    private final UserService userService;

    @Autowired
    public AssetRestController(AssetService assetService, UserService userService) {
        this.assetService = assetService;
        this.userService = userService;
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

        Asset bdAsset = assetService.save(asset);

        AssetDTO tempAsset = new AssetDTO();
        tempAsset.setId(bdAsset.getId());
        tempAsset.setSymbol(bdAsset.getSymbol());
        tempAsset.setPurchasePrice(bdAsset.getPurchasePrice());
        tempAsset.setQuantity(bdAsset.getQuantity());

        return tempAsset;
    }

}
