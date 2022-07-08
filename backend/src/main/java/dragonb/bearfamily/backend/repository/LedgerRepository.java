package dragonb.bearfamily.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dragonb.bearfamily.backend.model.Ledger;

public interface LedgerRepository extends JpaRepository<Ledger, Long>{
    @Query("select l from Ledger l join fetch l.category join fetch l.kind where l.id = :id and l.userIdentity = :userIdentity")
    Optional<Ledger> findLedgerFetch(@Param("id") Long id, @Param("userIdentity") String userIdentity);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind where l.userIdentity = :userIdentity")
    List<Ledger> findLedgersFetch(@Param("userIdentity") String userIdentity);

    List<Ledger> findAllByUserIdentity(String userIdentity);

    @Query("select to_char(l.date, 'YYYY-MM') as date, sum(l.price) as price from Ledger l group by to_char(l.date, 'YYYY-MM') order by date asc")
    List<Object> findLedgersSumGroupByMonth(String userIdentity);
}
