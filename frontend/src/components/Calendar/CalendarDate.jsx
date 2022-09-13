import React, { useState, useRef } from "react";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { calendarReducer, changeDate } from "../../reducers/calendarReducer";
import { apiWithToken } from "../../services/api/api";
import { Button } from "../common";

const CalendarDate = () => {
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

  const dateRefs = useRef([]);

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

      const responseTotal = await instance.get("/api/ledger/items/groupbymonth")
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

  const ledger = {
    // data 자체를 좀 거대하게 미리 받아놓는다
    // 전부 받는다 전역으로 저장해놓을 수 있는 무언가
    // 달 옮길때마다 통신을 할거냐 => 네이버의 경우 이렇지 않은 듯
    // 생각을 좀 더 해보겠음
    // 캐러셀로 이전 다음거는 무조건 받아놔야되
    // 생성은 년, 월, 일
    // created: 'Date.parse() 형태로'
    // 날짜를
  };

  return (
    <ul className="template__date-container">
      <span></span>
      {dateList.map((date, key) => {
        return (
          <li
            className={
              "date-container__date" +
              (formChanger(date) === Number(currentDataset) ? " selected" : "")
            }
            key={key}
            // id={Date.parse(date)}
            data-date={formChanger(date)}
            onClick={dateOnclick}
            ref={(el) => dateRefs.current.push(el)}
          >
            <div
              className={
                "date__inner" +
                (date.getMonth() === currentMonth - 1 ? " current" : "") +
                (date.getDay() === 0 ? " sunday" : "") +
                (date.getDay() === 6 ? " saturday" : "") +
                (date.getFullYear() === YEAR &&
                date.getMonth() === MONTH - 1 &&
                date.getDate() === DATE
                  ? " today"
                  : "")
              }
            >
              <div className="date">{date.getDate()}</div>
              <div className="usage">-10000</div>
              <div className="tags"></div>
            </div>
          </li>
        );
      })}
      {currentYear}년 {currentMonth} 월
      <Button onClick={goToPrevMonth}>이전</Button>
      <Button onClick={goToNextMonth}>다음</Button>
    </ul>
  );
};

export default CalendarDate;
