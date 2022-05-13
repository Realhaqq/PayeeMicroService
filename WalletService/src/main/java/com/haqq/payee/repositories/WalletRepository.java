package com.haqq.payee.repositories;

import com.haqq.payee.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Wallet findAllByWalletId(String walletId);
    Optional<Wallet> findByWalletId(String walletId);

    Wallet findByUserId(String uuid);
}
