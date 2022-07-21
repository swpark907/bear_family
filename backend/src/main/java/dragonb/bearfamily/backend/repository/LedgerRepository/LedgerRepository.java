package dragonb.bearfamily.backend.repository.LedgerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dragonb.bearfamily.backend.model.ledger.Ledger;
import dragonb.bearfamily.backend.model.ledger.LedgerGroupByCategory;
import dragonb.bearfamily.backend.model.ledger.LedgerGroupByDate;

public interface LedgerRepository extends JpaRepository<Ledger, Long>{
    @Query("select l from Ledger l join fetch l.category join fetch l.kind join fetch l.payment where l.id = :id and l.userIdentity = :userIdentity")
    Optional<Ledger> findLedgerFetch(@Param("id") Long id, @Param("userIdentity") String userIdentity);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind join fetch l.payment where l.userIdentity = :userIdentity")
    List<Ledger> findLedgersFetch(@Param("userIdentity") String userIdentity);

    List<Ledger> findAllByUserIdentity(String userIdentity);

    //@Query("select to_char(l.date, 'YYYY-MM') as date, sum(l.price) as price from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM') order by date asc")
    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByDate(to_char(l.date, 'YYYY-MM') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM') order by date asc")
    List<LedgerGroupByDate> findLedgersSumGroupByMonth(@Param("userIdentity") String userIdentity);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByDate(to_char(l.date, 'YYYY-MM-DD') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY-MM-DD') order by date asc")
    List<LedgerGroupByDate> findLedgersSumGroupByDate(@Param("userIdentity") String userIdentity);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByDate(to_char(l.date, 'YYYY') as date, sum(l.price)) " +
    "from Ledger l where l.userIdentity = :userIdentity group by to_char(l.date, 'YYYY') order by date asc")
    List<LedgerGroupByDate> findLedgersSumGroupByYear(@Param("userIdentity") String userIdentity);

    List<Ledger> findTop5ByUserIdentityOrderByPriceDesc(String userIdentity);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind join fetch l.payment where l.userIdentity = :userIdentity and to_char(l.date, 'YYYY') = :year")
    List<Ledger> findLedgersByUserIdentityAndYear(@Param("userIdentity") String userIdentity, @Param("year") String year);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind join fetch l.payment where l.userIdentity = :userIdentity and to_char(l.date, 'YYYYMM') = :month")
    List<Ledger> findLedgersByUserIdentityAndMonth(@Param("userIdentity") String userIdentity, @Param("month") String month);

    @Query("select l from Ledger l join fetch l.category join fetch l.kind join fetch l.payment where l.userIdentity = :userIdentity and to_char(l.date, 'YYYYMMDD') = :date")
    List<Ledger> findLedgersByUserIdentityAndDate(@Param("userIdentity") String userIdentity, @Param("date") String date);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByCategory(to_char(l.date, 'YYYY') as date, sum(l.price), l.kind, l.category) " +
    "from Ledger l " + 
    "where l.userIdentity = :userIdentity and to_char(l.date, 'YYYY') = :year " + 
    "group by to_char(l.date, 'YYYY'), l.category, l.kind order by date asc")
    List<LedgerGroupByCategory> findLedgersSumGroupByCategoryAndYear(@Param("userIdentity") String userIdentity, @Param("year") String year);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByCategory(to_char(l.date, 'YYYY-MM') as date, sum(l.price), l.kind, l.category) " +
    "from Ledger l " + 
    "where l.userIdentity = :userIdentity and to_char(l.date, 'YYYYMM') = :month " + 
    "group by to_char(l.date, 'YYYY-MM'), l.category, l.kind order by date asc")
    List<LedgerGroupByCategory> findLedgersSumGroupByCategoryAndMonth(@Param("userIdentity") String userIdentity, @Param("month") String month);

    @Query("select new dragonb.bearfamily.backend.model.ledger.LedgerGroupByCategory(to_char(l.date, 'YYYY-MM-DD') as date, sum(l.price), l.kind, l.category) " +
    "from Ledger l " + 
    "where l.userIdentity = :userIdentity and to_char(l.date, 'YYYYMMDD') = :date " + 
    "group by to_char(l.date, 'YYYY-MM-DD'), l.category, l.kind order by date asc")
    List<LedgerGroupByCategory> findLedgersSumGroupByCategoryAndDate(@Param("userIdentity") String userIdentity, @Param("date") String date);
}