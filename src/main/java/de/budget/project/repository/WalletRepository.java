package de.budget.project.repository;

import de.budget.project.model.wallet.Wallet;
import de.budget.project.model.wallet.WalletWebResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT new de.budget.project.model.wallet.WalletWebResponse " +
            "(w.currency)" +
            "FROM Wallet w WHERE w.id = :id")
    WalletWebResponse getWalletById(Long id);

    @Transactional
    @Modifying
    @Query(value = "insert into wallet(USER_ID, CURRENCY) values (:userId, :currency)",
            nativeQuery = true)
    Long createWallet(@Param("userId") Long userId, @Param("currency") String currency);



    List<Wallet> getAllByUserId(Long userId);
}