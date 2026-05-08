package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.dao.AssetRepository;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    public List<Asset> findAllByUserId(int userId){
        return assetRepository.findAllByUserId(userId);
    }

    @Transactional
    public Asset save(Asset asset){
        return assetRepository.save(asset);
    }

    public Asset findById(int assetsId){
        return assetRepository.findById(assetsId);
    }

    @Transactional
    public void deleteById(int assetId){
        assetRepository.deleteById(assetId);
    }

}
