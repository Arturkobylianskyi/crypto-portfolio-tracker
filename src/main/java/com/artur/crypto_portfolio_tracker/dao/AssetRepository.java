package com.artur.crypto_portfolio_tracker.dao;

import com.artur.crypto_portfolio_tracker.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Integer> {
    List<Asset> findAllByUserId(int userId);
    Asset findById(int assetId);
    void deleteById(int assetId);
}
