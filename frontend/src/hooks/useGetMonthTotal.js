import { useState, useEffect } from "react";
import { apiWithToken } from "../services/api/api";

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

export default useGetMonthTotal;