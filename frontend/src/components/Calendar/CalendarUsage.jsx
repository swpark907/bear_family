import React from "react";
import { useEffect, useState } from "react";
import { apiWithToken } from "../../services/api/api";

const useGetMonthTotal = () => {
  const NOW = new Date();
  const YEAR = NOW.getFullYear();
  const MONTH = NOW.getMonth() + 1;

  const [currentMonthTotal, setCurrentMonthTotal] = useState(0);
  const [prevMonthTotal, setPrevMonthTotal] = useState(0);

  useEffect(async () => {
    try {
      const instance = apiWithToken();
      const response = await instance.get("/api/ledger/items/groupbymonth");
      
      const { data } = response.data;
      setCurrentMonthTotal(
        data.find((element) => element.date === `${YEAR}-0${MONTH}`).price
      );
      setPrevMonthTotal(data.find((element) => element.date === `${YEAR}-0${MONTH-1}`).price)

    } catch (e) {
      console.log(e);
    }
  }, []);

  return { currentMonthTotal, prevMonthTotal };
};

const CalendarUsage = () => {
  const { currentMonthTotal, prevMonthTotal } = useGetMonthTotal();
  const difference = currentMonthTotal - prevMonthTotal;

  return (
    <div className="calendar__usage">
      <div className="usage__current">
        <span className="current__title">이번달 수입 / 지출 내역</span>
        <span className={`current__total ${currentMonthTotal >= 0 ? "profit" : "loss"}`}>{
          `${currentMonthTotal >= 0 ? `+ ${currentMonthTotal}` : `- ${currentMonthTotal}`}`
        }</span>
      </div>
      <div className="usage__prev">
        <span className="prev__title">지난달 내역 / 비교</span>
        <span className={`prev__total prev ${prevMonthTotal >= 0 ? "profit" : "loss"}`}>{
          `${prevMonthTotal >= 0 ? `+ ${prevMonthTotal}` : `- ${prevMonthTotal}`}`
        }</span>
        <span className={`prev__compare prev ${difference >= 0 ? "profit" : "loss"}`}>{
          `${difference >= 0 ? `+ ${difference}` : `- ${difference}`}`
        }</span>
      </div>
    </div>
  );
};

export default CalendarUsage;
