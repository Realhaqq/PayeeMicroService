package com.haqq.payee.repositories;

import com.haqq.payee.entities.SettlementTransactions;
import com.haqq.payee.entities.SettlementWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementTransactionRepository extends JpaRepository<SettlementTransactions, Long> {

    List<SettlementTransactions> findAllByWalletId(String walletId);
}
