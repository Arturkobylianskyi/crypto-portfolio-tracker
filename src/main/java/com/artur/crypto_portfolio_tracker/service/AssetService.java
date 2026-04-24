package com.artur.crypto_portfolio_tracker.service;

import com.artur.crypto_portfolio_tracker.dao.AssetRepository;
import com.artur.crypto_portfolio_tracker.entity.Asset;
import com.artur.crypto_portfolio_tracker.entity.User;

import java.util.List;

public interface AssetService extends AssetRepository {
    List<Asset> findAllByUser(User user);
}
