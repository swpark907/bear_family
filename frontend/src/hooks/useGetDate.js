import { useState, useEffect } from "react";
import { apiWithToken } from "../services/api/api";
import { calendarReducer, changeDate } from "../reducers/calendarReducer";
import { useDispatch } from "react-redux";

const useGetDate = () => {
  const NOW = new Date();
  const YEAR = NOW.getFullYear();
  const DATE = NOW.getDate();
  const MONTH = NOW.getMonth() + 1;
  const LAST = new Date(YEAR, MONTH - 1, 0);
  const LASTDATE = LAST.getDate();
  const DAYOFFIRST = new Date(YEAR, MONTH - 1, 1).getDay();

  const [currentYear, setCurrentYear] = useState(YEAR); // 현재 연도
  const [currentMonth, setCurrentMonth] = useState(MONTH); // 현재 달
  const [currentDate, setCurrentDate] = useState(DATE);

  const [currentDataset, setCurrentDataset] = useState(0); // 0을 넣는게 맞나?

  const [dateList, setDateList] = useState([]);

  const dispatch = useDispatch();

  useEffect(async () => {
    try {
      const instance = apiWithToken();
      const responseList = await instance.get("/api/ledger/items/date", {
        params: {
          year: currentYear,
          month: currentMonth,
          date: currentDate,
        },
      });
      const data = responseList.data.data;
      dispatch(changeDate(data));

      const responseTotal = await instance.get(
        "/api/ledger/items/groupbymonth"
      );
    } catch (e) {
      console.log("날짜 데이터를 받아올 때 생긴 오류입니다", e);
    }
  }, [currentDataset]);

  useEffect(() => {
    setCurrentDataset(formChanger(NOW));
  }, []);

  const getCalendarLine = () => {
    // 현재 달의 첫째날의 요일
    const dayOfFirst = new Date(currentYear, currentMonth - 1).getDay();

    let dateOfCount = 0;
    dayOfFirst >= 4 ? (dateOfCount = 41) : (dateOfCount = 34);

    const data = [];

    for (let i = -dayOfFirst + 1; i <= dateOfCount - dayOfFirst + 1; i++) {
      const day = new Date(currentYear, currentMonth - 1, i);
      data.push(day);
    }
    setDateList(data);
  };

  useEffect(() => {
    getCalendarLine();
  }, [currentMonth]);

  const goToNextMonth = () => {
    if (currentMonth >= 12) {
      setCurrentMonth(1);
      setCurrentYear(currentYear + 1);
    } else {
      setCurrentMonth(currentMonth + 1);
    }
  };

  const goToPrevMonth = () => {
    if (currentMonth <= 1) {
      setCurrentMonth(12);
      setCurrentYear(currentYear - 1);
    } else {
      setCurrentMonth(currentMonth - 1);
    }
  };

  const formChanger = (date) => {
    const year = date.getFullYear().toString();
    const month =
      date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1).toString()
        : (date.getMonth() + 1).toString();
    const day =
      date.getDate() < 10
        ? "0" + date.getDate().toString()
        : date.getDate().toString();
    return Number(year + month + day);
  };

  const dateOnclick = (e) => {
    const target = e.currentTarget;

    const dateData = target.dataset.date;
    const year = Number(dateData.substr(0, 4));
    const month = Number(dateData.substr(4, 2));
    const date = Number(dateData.substr(6));
    setCurrentYear(Number(year));
    setCurrentMonth(Number(month));
    setCurrentDate(Number(date));
    setCurrentDataset(dateData);
  };

  return {
    dateList,
    currentYear,
    currentMonth,
    currentDataset,
    goToNextMonth,
    goToPrevMonth,
    formChanger,
    dateOnclick,
  };
};

export default useGetDate;