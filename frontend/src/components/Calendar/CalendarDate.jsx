import React, { useRef } from "react";
import { Button } from "../common";
import useGetDate from "../../hooks/useGetDate";

const CalendarDate = () => {
  const {
    dateList,
    currentYear,
    currentMonth,
    currentDataset,
    goToNextMonth,
    goToPrevMonth,
    formChanger,
    dateOnclick,
  } = useGetDate();

  const NOW = new Date();
  const YEAR = NOW.getFullYear();
  const DATE = NOW.getDate();
  const MONTH = NOW.getMonth() + 1;

  const dateRefs = useRef([]);

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
