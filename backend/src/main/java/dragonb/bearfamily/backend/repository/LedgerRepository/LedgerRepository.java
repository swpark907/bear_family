package dragonb.bearfamily.backend.repository.LedgerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerMath;

public interface LedgerRepository extends JpaRepository<Ledger, Long>{
    @Query("select l from Ledger l join fetch l.category join fetch l.kind where l.id = :id and l.userIdentity = :userIdentity")
    Optional<Ledger> findLedgerFetch(@Param("id") Long id, @Param("userIdentity") String userIdentity);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind where l.userIdentity = :userIdentity")
    List<Ledger> findLedgersFetch(@Param("userIdentity") String userIdentity);

    List<Ledger> findAllByUserIdentity(String userIdentity);

    //@Query("select to_char(l.date, 'YYYY-MM') as date, sum(l.price) as price from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM') order by date asc")
    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerMath(to_char(l.date, 'YYYY-MM') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM') order by date asc")
    List<LedgerMath> findLedgersSumGroupByMonth(@Param("userIdentity") String userIdentity);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerMath(to_char(l.date, 'YYYY-MM-DD') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM-DD') order by date asc")
    List<LedgerMath> findLedgersSumGroupByDate(@Param("userIdentity") String userIdentity);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerMath(to_char(l.date, 'YYYY') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY') order by date asc")
    List<LedgerMath> findLedgersSumGroupByYear(@Param("userIdentity") String userIdentity);

    List<Ledger> findTop5ByUserIdentityOrderByPriceDesc(String userIdentity);
}
