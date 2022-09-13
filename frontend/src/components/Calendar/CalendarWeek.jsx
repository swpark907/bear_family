import React from "react";

const CalendarWeek = () => {
  const Week = ["일", "월", "화", "수", "목", "금", "토"];
  return (
    <ul className="template__weeks">
      {Week.map((week, key) => (
        <li className="week" key={key}>{week}</li>
      ))}
    </ul>
  );
};

export default CalendarWeek;
