package com.artur.crypto_portfolio_tracker.rest;

import com.artur.crypto_portfolio_tracker.dto.AssetDTO;
import com.artur.crypto_portfolio_tracker.dto.PortfolioItemDTO;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import com.artur.crypto_portfolio_tracker.service.AssetService;
import com.artur.crypto_portfolio_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetRestController {

    private final AssetService assetService;
    private final UserService userService;
    private final JsonMapper jsonMapper;

    @Autowired
    public AssetRestController(AssetService assetService, UserService userService, JsonMapper jsonMapper) {
        this.assetService = assetService;
        this.userService = userService;
        this.jsonMapper = jsonMapper;
    }


    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public AssetDTO updateAsset(@RequestBody Asset asset){
        Asset dbAsset = assetService.save(asset);

        AssetDTO tempAsset = new AssetDTO();
        tempAsset.setId(dbAsset.getId());
        tempAsset.setSymbol(dbAsset.getSymbol());
        tempAsset.setPurchasePrice(dbAsset.getPurchasePrice());
        tempAsset.setQuantity(dbAsset.getQuantity());

        return tempAsset;
    }

    @PatchMapping("/{assetId}")
    @PreAuthorize("isAuthenticated()")
    public AssetDTO patchAsset(@PathVariable int assetId,
                               @RequestBody Map<String, Object> patchPayload){

        Asset tempAsset = assetService.findById(assetId);

        if(tempAsset == null){
            throw new RuntimeException("asset id not found - "+assetId );
        }
        if(patchPayload.containsKey("id")){
            throw new RuntimeException("asset id not allowed in request body");
        }

        Asset patchedAsset = jsonMapper.updateValue(tempAsset, patchPayload);
        Asset dbAsset = assetService.save(patchedAsset);

        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setId(dbAsset.getId());
        assetDTO.setSymbol(dbAsset.getSymbol());
        assetDTO.setPurchasePrice(dbAsset.getPurchasePrice());
        assetDTO.setQuantity(dbAsset.getQuantity());

        return assetDTO;
    }

    @DeleteMapping("/{assetId}")
    @PreAuthorize("isAuthenticated()")
    public String deleteItem(@PathVariable int assetId){

        assetService.deleteById(assetId);

        return "asset with id: "+assetId +" was deleted succesfuly";
    }

}
