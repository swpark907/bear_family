import React, { useState } from "react";
import { useEffect } from "react";
import { Button } from "../common";

const CalendarDate = () => {
  const NOW = new Date();
  const YEAR = NOW.getFullYear();
  const DATE = NOW.getDate();
  const MONTH = NOW.getMonth() + 1;
  const LAST = new Date(YEAR, MONTH - 1, 0);
  const LASTDATE = LAST.getDate();
  const DAYOFFIRST = new Date(YEAR, MONTH - 1, 1).getDay();

  const [currentYear, setCurrentYear] = useState(YEAR);
  const [currentMonth, setCurrentMonth] = useState(MONTH);
  const [currentDate, setCurrentDate] = useState(DATE);

  const [dateList, setDateList] = useState([]);

  // 첫번째 날의 요일이 목요일(4) 이상이면 6줄, 이하이면 5줄
  // 이번달의 첫번째 날을 구한다.

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

  // useEffect(() => {
  //   console.log(dateList);
  // }, [dateList]);

  const goToNextMonth = () => {
    setCurrentMonth(currentMonth + 1);
  };

  const goToPrevMonth = () => {
    setCurrentMonth(currentMonth - 1);
  };

  return (
    <ul className="template__date-container">
      {dateList.map((date, key) => {
        return (
          <li
            className={"date-container__date"}
            key={key}
            id={Date.parse(date)}
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
              <span className="date">{date.getDate()}</span>
            </div>
          </li>
        );
      })}
      <Button onClick={goToPrevMonth}>이전</Button>
      <Button onClick={goToNextMonth}>다음</Button>
    </ul>
  );
};

export default CalendarDate;
