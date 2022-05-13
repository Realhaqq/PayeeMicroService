package com.haqq.payee.repositories;

import com.haqq.payee.entities.SettlementWallet;
import com.haqq.payee.entities.Wallet;
import com.haqq.payee.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettlementWalletRepository extends JpaRepository<SettlementWallet, Long> {

    SettlementWallet findByRole(String roleClientInstitution);
}
